use crate::endianness::{Endianness, EndiannessError};
use byteorder as byte;
use byteorder::ByteOrder;
use core::fmt;
use std::error::Error;
use std::{error, io};
use crate::constants::{TIFF_II, TIFF_MM};
use std::fs::read_to_string;

/// Rust struct representation of TIFF standard header.
///
/// C equivalent:
/// ```c
/// typedef struct _TiffHeader {
/// 	WORD Identifier;
/// 	WORD Version;
/// 	DWORD IFDOffset;
/// } TIFHEAD;
/// ```
pub struct Header {
    pub identifier: u16,
    pub version: u16,
    pub ifd_offset: u32,
}

impl Header {
    /// Parses a TIFF Header struct from an 8-byte buffer reference.
    ///
    /// # Arguments
    ///
    /// * `buf` - Buffer reference that should conform with TIFF standard.
    ///
    pub fn new(buf: &[u8; 8]) -> Result<Self, EndiannessError> {
        let identifier: u16;
        let version: u16;
        let ifd_offset: u32;

        // Parse endianness from the first 2-byte part of the header
        match Header::endianness(&buf[..2]) {
            Ok(endianness) => match endianness {
                Endianness::BigEndian => {
                    identifier = byte::BigEndian::read_u16(&buf[..2]);
                    version = byte::BigEndian::read_u16(&buf[2..4]);
                    ifd_offset = byte::BigEndian::read_u32(&buf[4..8]);
                }
                Endianness::LittleEndian => {
                    identifier = byte::LittleEndian::read_u16(&buf[..2]);
                    version = byte::LittleEndian::read_u16(&buf[2..4]);
                    ifd_offset = byte::LittleEndian::read_u32(&buf[4..8]);
                }
            },
            Err(_) => return Err(EndiannessError),
        }

        // Verify that the file has a valid version identifier
        // Should always be 42
        if version != 42 {
            return Err(EndiannessError::from(io::Error::new(io::ErrorKind::Other, "invalid version identifier")));
        }

        return Ok(Header {
            identifier,
            version,
            ifd_offset,
        });
    }

    /// Parses TIFF standard endianness from a 2-byte buffer slice reference
    /// Valid buffers are [0x49, 0x49] and [0x4D, 0x4D].
    ///
    /// # Arguments
    ///
    /// * `buf` - Buffer to be parsed as endianness.
    ///
    pub fn endianness(buf: &[u8]) -> Result<Endianness, EndiannessError> {
        return if TIFF_MM.iter().zip(buf.iter()).all(|(a, b)| a == b) {
            Ok(Endianness::BigEndian)
        } else if TIFF_II.iter().zip(buf.iter()).all(|(a, b)| a == b) {
            Ok(Endianness::LittleEndian)
        } else {
            Err(EndiannessError)
        };
    }
}

impl fmt::Debug for Header {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> Result<(), fmt::Error> {
        if self.identifier == 0x4d4d {
            write!(f, "{:x?}{:x?}[{:#x}]", self.identifier.to_be_bytes(), self.version.to_be_bytes(), self.ifd_offset)
        } else if self.identifier == 0x4949 {
            write!(f, "{:x?}{:x?}[{:#x}]", self.identifier.to_le_bytes(), self.version.to_le_bytes(), self.ifd_offset)
        } else {
            Err(fmt::Error)
        }
    }
}

#[cfg(test)]
mod tests {
    use crate::header::Header;
    use crate::endianness::EndiannessError;

    #[test]
    pub fn test_mm() {
        let buf = [0x4D, 0x4D, 0x00, 0x2A, 0x00, 0x00, 0x00, 0x00];
        let mm_header = Header::new(&buf).expect("Failed to parse MM header.");
        println!("Parsed identifier of value {:?} from buffer {:x?}", mm_header.identifier, buf);
        assert_eq!(mm_header.identifier, 0x4D4D);
        assert_eq!(mm_header.version, 42);
    }

    #[test]
    pub fn test_ii() {
        let buf = [0x49, 0x49, 0x2A, 0x00, 0x00, 0x00, 0x00, 0x00];
        let ii_header = Header::new(&buf).expect("Failed to parse II header.");
        println!("Parsed identifier of value {:?} from buffer {:x?}", ii_header.identifier, buf);
        assert_eq!(ii_header.identifier, 0x4949);
        assert_eq!(ii_header.version, 42);
    }

    #[test]
    pub fn test_zero_offset_le() {
        let buf = [0x49, 0x49, 0x2A, 0x00, 0x00, 0x00, 0x00, 0x00];
        let header = Header::new(&buf).expect("Failed to parse header.");
        assert_eq!(header.ifd_offset, 0x00000000);
        assert_eq!(header.version, 42);
    }

    #[test]
    pub fn test_zero_offset_be() {
        let buf = [0x4D, 0x4D, 0x00, 0x2A, 0x00, 0x00, 0x00, 0x00];
        let header = Header::new(&buf).expect("Failed to parse header.");
        assert_eq!(header.ifd_offset, 0x00000000);
        assert_eq!(header.version, 42);
    }

    #[test]
    pub fn test_offset_le() {
        let buf = [0x49, 0x49, 0x2A, 0x00, 0x00, 0x00, 0x72, 0x2A];
        let header = Header::new(&buf).expect("Failed to parse header.");
        assert_eq!(header.ifd_offset, 0x2A720000);
        assert_eq!(header.version, 0x002A);
    }

    #[test]
    pub fn test_offset_be() {
        let buf = [0x4d, 0x4d, 0x00, 0x2A, 0x00, 0x00, 0x72, 0x2A];
        let header = Header::new(&buf).expect("Failed to parse header.");
        assert_eq!(header.ifd_offset, 0x0000722A);
        assert_eq!(header.version, 42);
    }


    #[test]
    pub fn test_invalid_version_buf() {
        let buf = [0x4D, 0x4D, 0x43, 0x29, 0x00, 0x00, 0x72, 0x2A];
        let header = Header::new(&buf);
        match header {
            Ok(_) => { assert!(false, "Parsing of invalid header didn't throw an error") }
            Err(_) => { assert!(true, "Parsing of invalid header failed") }
        }
    }

    #[test]
    pub fn test_invalid_identifier_buf() {
        let buf = [0x47, 0x52, 0x2A, 0x00, 0x00, 0x00, 0x72, 0x2A];
        let header = Header::new(&buf);
        match header {
            Ok(_) => { assert!(false, "Parsing of invalid header didn't throw an error") }
            Err(_) => { assert!(true, "Parsing of invalid header failed") }
        }
    }
}
