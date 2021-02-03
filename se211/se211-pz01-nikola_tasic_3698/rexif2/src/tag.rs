use crate::endianness::{Endianness, BigEndian, LittleEndian, Endian};
use std::io::{Read, BufReader, Seek, SeekFrom};
use std::fs::File;
use crate::tagtype::{TagType, TagTypeSize};
use core::fmt;
use byteorder::ByteOrder;

/// Rust struct representation of a TIFF IFD Tag
///
/// C equivalent:
/// ```c
/// typedef struct _TifTag
/// {
/// 	WORD TagId;
/// 	WORD DataType;
/// 	DWORD DataCount;
/// 	DWORD DataOffset;
/// } TIFTAG;
/// ```
#[derive(Clone)]
pub struct Tag {
    pub tid: TagType,
    pub ttype: u16,
    pub count: u32,
    pub offset: u32,
    pub data: Vec<u8>,
}

impl Tag {
    /// Parses a TIFF IDF Tag from the current seek offset of `BufReader<&mut File>`.
    ///
    /// # Arguments
    ///
    /// * `file_reader` - File reader with a seek offset from which tag is read.
    ///
    /// * `endianness` - Endianness to parse the data with
    ///
    /// * `offset` - offset in bytes required for some vendor file types to skip arbitrary header eg. 160 for Fujifilm RAF
    ///
    pub fn new(file_reader: &mut BufReader<&mut File>, endianness: Endianness, foffset: u64) -> Self {
        let tid;
        let ttype;
        let count;
        let offset;


        match endianness {
            Endianness::BigEndian => {
                tid = BigEndian::read_u16(file_reader);
                ttype = BigEndian::read_u16(file_reader);
                count = BigEndian::read_u32(file_reader);
                offset = BigEndian::read_u32(file_reader);
            }
            Endianness::LittleEndian => {
                tid = LittleEndian::read_u16(file_reader);
                ttype = LittleEndian::read_u16(file_reader);
                count = LittleEndian::read_u32(file_reader);
                offset = LittleEndian::read_u32(file_reader);
            }
        }

        let mut data;
        let tid = TagType::from(tid);
        let size = TagTypeSize::from(ttype).size();

        if size * count > 4 {
            data = vec![0u8; (count * size) as usize];
            let curr = file_reader.seek(SeekFrom::Current(0)).unwrap();
            file_reader.seek(SeekFrom::Start((offset as u64) + foffset)).expect(format!("cannot seek file to {}", offset).as_str());
            file_reader.read_exact(&mut data);
            file_reader.seek(SeekFrom::Start(curr));
        } else {
            data = offset.to_be_bytes().to_vec();
        }

        Tag {
            tid,
            ttype,
            count,
            offset,
            data,
        }
    }

    pub fn size(&self) -> usize {
        TagTypeSize::from(self.ttype as u16) as usize
    }
}

impl fmt::Debug for Tag {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> Result<(), fmt::Error> {
        match self.tid.type_size() {
            TagTypeSize::ASCII => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), String::from_utf8_lossy(&self.data)),
            TagTypeSize::UNDEFINED =>  write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), String::from_utf8_lossy(&self.data)),
            TagTypeSize::RATIONAL => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {:?}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), self.data),
            TagTypeSize::SRATIONAL => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {:?}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), self.data),
            TagTypeSize::FLOAT => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), byteorder::BigEndian::read_f32(&self.data)),
            TagTypeSize::DOUBLE => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), byteorder::BigEndian::read_f64(&self.data)),
            TagTypeSize::BYTE => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), self.data[0]),
            TagTypeSize::SHORT => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), byteorder::BigEndian::read_u16(&self.data)),
            TagTypeSize::LONG => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), byteorder::BigEndian::read_u32(&self.data)),
            TagTypeSize::SBYTE => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), self.data[0]),
            TagTypeSize::SSHORT => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), byteorder::BigEndian::read_i16(&self.data)),
            TagTypeSize::SLONG => write!(f, "Tag {:6} {:#4} {:#5} {:#10x} {:32} {}", self.tid.val(), self.ttype, self.count, self.offset, self.tid.as_str(), byteorder::BigEndian::read_i32(&self.data)),
        }
    }
}

