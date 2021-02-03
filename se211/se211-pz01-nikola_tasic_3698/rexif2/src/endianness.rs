use std::fs::File;
use std::io;
use std::io::{BufReader, Read, Error};
use byteorder::ByteOrder;

#[derive(PartialEq, Copy, Clone, Debug)]
pub enum Endianness {
    BigEndian,
    LittleEndian,
}

pub struct BigEndian;

pub struct LittleEndian;


pub trait Endian {
    fn read_u8(r: &mut BufReader<&mut File>) -> u8;
    fn read_u16(r: &mut BufReader<&mut File>) -> u16;
    fn read_u32(r: &mut BufReader<&mut File>) -> u32;
    fn read_u64(r: &mut BufReader<&mut File>) -> u64;
}

impl Endian for BigEndian {
    fn read_u8(r: &mut BufReader<&mut File>) -> u8 {
        let mut buf = [0u8];
        return match r.read_exact(&mut buf) {
            Ok(_) => buf[0],
            Err(_) => 0u8,
        };
    }

    fn read_u16(r: &mut BufReader<&mut File>) -> u16 {
        let mut buf = [0u8; 2];
        return match r.read_exact(&mut buf) {
            Ok(_) => byteorder::BigEndian::read_u16(&buf),
            Err(_) => 0u16,
        };
    }

    fn read_u32(r: &mut BufReader<&mut File>) -> u32 {
        let mut buf = [0u8; 4];
        return match r.read_exact(&mut buf) {
            Ok(_) => byteorder::BigEndian::read_u32(&mut buf),
            Err(_) => 0u32,
        };
    }

    fn read_u64(r: &mut BufReader<&mut File>) -> u64 {
        let mut buf = [0u8; 8];
        return match r.read_exact(&mut buf) {
            Ok(_) => byteorder::BigEndian::read_u64(&buf),
            Err(_) => 0u64,
        };
    }
}

impl Endian for LittleEndian {
    fn read_u8(r: &mut BufReader<&mut File>) -> u8 {
        let mut buf = [0u8];
        return match r.read_exact(&mut buf) {
            Ok(_) => buf[0],
            Err(_) => 0u8,
        };
    }

    fn read_u16(r: &mut BufReader<&mut File>) -> u16 {
        let mut buf = [0u8; 2];
        return match r.read_exact(&mut buf) {
            Ok(_) => byteorder::LittleEndian::read_u16(&buf),
            Err(_) => 0u16,
        };
    }

    fn read_u32(r: &mut BufReader<&mut File>) -> u32 {
        let mut buf = [0u8; 4];
        return match r.read_exact(&mut buf) {
            Ok(_) => byteorder::LittleEndian::read_u32(&buf),
            Err(_) => 0u32,
        };
    }

    fn read_u64(r: &mut BufReader<&mut File>) -> u64 {
        let mut buf = [0u8; 8];
        return match r.read_exact(&mut buf) {
            Ok(_) => byteorder::LittleEndian::read_u64(&buf),
            Err(_) => 0u64,
        };
    }
}

#[derive(Debug, Clone)]
pub struct EndiannessError;

impl From<io::Error> for EndiannessError {
    fn from(_: Error) -> Self {
       EndiannessError
    }
}