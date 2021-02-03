use byteorder::ByteOrder;
use core::fmt;
use crate::endianness::Endianness;
use crate::endianness::{BigEndian, LittleEndian, Endian};
use crate::tag::Tag;
use crate::tagtype::TagType;
use std::fs::File;
use std::io::{Read, BufReader};

/// Rust representation of TIFF IFD (Image File Directory)
///
/// C equivalent:
/// ```c
/// typedef struct _TifIfd
/// {
/// 	WORD NumDirEntries;
/// 	TIFTAG TagList[];
/// 	DWORD NextIFDOffset;
/// } TIFIFD;
/// ```
#[derive(Clone)]
pub struct Ifd {
    num_dir_ent: u16,
    pub tags: Vec<Tag>,
    next_ifd: u32,
}

impl Ifd {
    /// Parses IFD struct from  a file
    ///
    /// # Arguments
    ///
    /// * `file_reader` - File reader from which we parse IFD
    ///
    /// * `endianness` - Endianness to parse the data with
    ///
    /// * `offset` - offset in bytes required for some vendor file types to skip arbitrary header eg. 160 for Fujifilm RAF
    ///
    pub fn new(file_reader: &mut BufReader<&mut File>, endianness: Endianness, offset: u64) -> Self {
        let num_dir_ent: u16;
        let mut tags: Vec<Tag> = Vec::new();
        let next_ifd: u32;

        // Get the number of IFD entries
        match endianness {
            Endianness::BigEndian => num_dir_ent = BigEndian::read_u16(file_reader),
            Endianness::LittleEndian => num_dir_ent = LittleEndian::read_u16(file_reader),
        }

        // Read all the tags based on the number of directory entries
        let mut tag;
        for _ in 0..num_dir_ent {
            tag = Tag::new(file_reader, endianness, offset);
            tags.push(tag);
        }

        // Read the position of next IFD if there is one
        // Value should be non 0x00 if IFD is found
        match endianness {
            Endianness::BigEndian => next_ifd = BigEndian::read_u32(file_reader),
            Endianness::LittleEndian => next_ifd = LittleEndian::read_u32(file_reader),
        }

        return Ifd {
            num_dir_ent,
            tags,
            next_ifd,
        };
    }

    /// Gets the location of next EXIF IFD from tags if a tag of type `ExifIFDPointer` is present.
    /// Otherwise returns 0x00
    ///
    pub fn next_exif_ifd_ptr(&self) -> u32 {
        let out: u32 = 0;
        for tag in &self.tags {
            if tag.tid == TagType::ExifIFDPointer {
                return byteorder::BigEndian::read_u32(&tag.data);
            }
        }
        return out;
    }
}


impl fmt::Debug for Ifd {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> Result<(), fmt::Error> {
        write!(f, "Dirs:{:?}\n[Tags:\n            ID TYPE   NUM        OFF NAME{:#?}\n{}", self.num_dir_ent, self.tags, self.next_ifd)
    }
}
