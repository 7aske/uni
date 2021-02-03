mod endianness;
mod tiff;
mod header;
mod ifd;
mod tag;
mod tagtype;
mod constants;

#[cfg(test)]
mod tests {
    use crate::tiff::Tiff;
    use std::fs::File;
    use std::time::Instant;

    #[test]
    fn test() {
        let now = Instant::now();
        let filename = "test/D3200.NEF";
        // let filename = "test/X10.RAF";
        // let filename = "test/MIA1.JPG";
        let mut file = File::open(filename.to_string()).expect("Cannot open file.");
        let tiff = Tiff::new_from_file(&mut file, 0x00).expect("Cannot parse TIFF file");
        let now = now.elapsed();
        println!("Time 0.{:#06}μs", now.as_micros());
        println!("{:?}", tiff);
    }
}

