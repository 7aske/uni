use std::fmt::{Formatter, Error};

#[derive(PartialEq, Debug, Copy, Clone)]
pub enum TagType {
    /// 315// ASCII
    Artist,
    /// 326// SHORT or LONG
    BadFaxLines,
    /// 258// SHORT
    BitsPerSample,
    /// 265// SHORT
    CellLength,
    /// 264// SHORT
    CellWidth,
    /// 327// SHORT
    CleanFaxData,
    /// 320// SHORT
    ColorMap,
    /// 301// SHORT
    //    ColorResponseCurve,
    /// 300// SHORT
    ColorResponseUnit,
    /// 259// SHORT
    Compression,
    /// 1//
    Uncompressed,
    /// 2//
    CCITT1D,
    /// 3//
    CCITTGroup3,
    /// 4// 4
    CCITTGroup4,
    /// 5//
    LZW,
    /// 6//
    JPEG,
    /// 32773//
    Packbits,
    /// 328// LONG or SHORT
    ConsecutiveBadFaxLines,
    /// 33432// ASCII
    Copyright,
    /// 306// ASCII
    DateTime,
    /// 269// ASCII
    DocumentName,
    /// 336// BYTE or SHORT
    DotRange,
    /// 338// BYTE
    ExtraSamples,
    /// 266// SHORT
    FillOrder,
    /// 289// LONG
    FreeByteCounts,
    /// 288// LONG
    FreeOffsets,
    /// 291// SHORT
    GrayResponseCurve,
    /// 290// SHORT
    GrayResponseUnit,
    /// 321// SHORT
    HalftoneHints,
    /// 316// ASCII
    HostComputer,
    /// 270// ASCII
    ImageDescription,
    /// 257// SHORT or LONG
    ImageHeight,
    /// 256// SHORT or LONG
    ImageWidth,
    /// 333// ASCII
    InkNames,
    /// 332// SHORT
    InkSet,
    /// 521// LONG
    JPEGACTTables,
    /// 520// LONG
    JPEGDCTTables,
    /// 513// LONG
    JPEGInterchangeFormat,
    /// 514// LONG
    JPEGInterchangeFormatLength,
    /// 517// SHORT
    JPEGLosslessPredictors,
    /// 518// SHORT
    JPEGPointTransforms,
    /// 512// SHORT
    JPEGProc,
    /// 515// SHORT
    JPEGRestartInterval,
    /// 519// LONG
    JPEGQTables,
    /// 271// ASCII
    Make,
    /// 281// SHORT
    MaxSampleValue,
    /// 280// SHORT
    MinSampleValue,
    /// 272// ASCII
    Model,
    /// 254// LONG
    NewSubFileType,
    /// 334// SHORT
    NumberOfInks,
    /// 274// SHORT
    Orientation,
    /// 285// ASCII
    PageName,
    /// 297// SHORT
    PageNumber,
    /// 262// SHORT
    PhotometricInterpretation,
    /// 0//
    WhiteIsZero,
    /// 1//
    BlackIsZero,
    /// 2//
    RGB,
    /// 3//
    RgbPalette,
    /// // 4
    TransparencyMask,
    /// 5//
    CMYK,
    /// 6//
    YCbCr,
    /// 8//
    CIELab,
    /// 284// SHORT
    PlanarConfiguration,
    /// 317// SHORT
    Predictor,
    /// 319// RATIONAL
    PrimaryChromatics,
    /// 532// LONG
    ReferenceBlackWhite,
    /// 296// SHORT
    ResolutionUnit,
    /// 278// SHORT or LONG
    RowsPerStrip,
    /// 339// SHORT
    SampleFormat,
    /// 277// SHORT
    SamplesPerPixel,
    /// 341// Any
    SMaxSampleValue,
    /// 340// Any
    SMinSampleValue,
    /// 305// ASCII
    Software,
    /// 279// LONG or SHORT
    StripByteCounts,
    /// 273// SHORT or LONG
    StripOffsets,
    /// 255// SHORT
    SubFileType,
    /// 292// LONG
    T4Options,
    /// 293// LONG
    T6Options,
    /// 337// ASCII
    TargetPrinter,
    /// 263// SHORT
    Thresholding,
    /// 325// SHORT or LONG
    TileByteCounts,
    /// 323// SHORT or LONG
    TileLength,
    /// 324// LONG
    TileOffsets,
    /// 322// SHORT or LONG
    TileWidth,
    ///[//4] 301 SHORT
    TransferFunction,
    /// 342// SHORT
    TransferRange,
    /// 286// RATIONAL
    XPosition,
    /// 282// RATIONAL
    XResolution,
    /// 529// RATIONAL
    YCbCrCoefficients,
    /// 531// SHORT
    YCbCrPositioning,
    /// 530// SHORT
    YCbCrSubSampling,
    /// 287// RATIONAL
    YPosition,
    /// 283// RATIONAL
    YResolution,
    /// 318// RATIONAL
    WhitePoint,
    /// 36864 9000 UNDEFINED 4
    ExifVersion,
    /// 40960 A000 UNDEFINED 4
    FlashpixVersion,
    /// 40961 A001 SHORT 1
    ColorSpace,
    /// 37121 9101 UNDEFINED 4
    ComponentsConfiguration,
    /// 37122 9102 RATIONAL 1
    CompressedBitsPerPixel,
    /// 40962 A002 SHORT or LONG 1
    PixelXDimension,
    /// 40963 A003 SHORT or LONG 1
    PixelYDimension,
    /// 37500 927C UNDEFINED Any
    MakerNote,
    /// 37510 9286 UNDEFINED Any
    UserComment,
    /// 40964 A004 ASCII 13
    RelatedSoundFile,
    /// 36867 9003 ASCII 20
    DateTimeOriginal,
    /// 36868 9004 ASCII 20
    DateTimeDigitized,
    /// 37520 9290 ASCII Any
    SubSecTime,
    /// 37521 9291 ASCII Any
    SubSecTimeOriginal,
    /// 37522 9292 ASCII Any
    SubSecTimeDigitized,
    /// 42016 A420 ASCII 33
    ImageUniqueID,
    ///33434 829A RATIONAL 1
    ExposureTime,
    ///33437 829D RATIONAL 1
    FNumber,
    ///34850 8822 SHORT 1
    ExposureProgram,
    ///34852 8824 ASCII Any
    SpectralSensitivity,
    ///34855 8827 SHORT Any
    ISOSpeedRatings,
    ///34856 8828 UNDEFINED Any
    OECF,
    ///37377 9201 SRATIONAL 1
    ShutterSpeedValue,
    ///37378 9202 RATIONAL 1
    ApertureValue,
    ///37379 9203 SRATIONAL 1
    BrightnessValue,
    ///37380 9204 SRATIONAL 1
    ExposureBiasValue,
    ///37381 9205 RATIONAL 1
    MaxApertureValue,
    ///37382 9206 RATIONAL 1
    SubjectDistance,
    ///37383 9207 SHORT 1
    MeteringMode,
    ///37384 9208 SHORT 1
    LightSource,
    ///37385 9209 SHORT 1
    Flash,
    ///37386 920A RATIONAL 1
    FocalLength,
    ///37396 9214 SHORT 2 or 3 or 4
    SubjectArea,
    ///41483 A20B RATIONAL 1
    FlashEnergy,
    ///41484 A20C UNDEFINED Any
    SpatialFrequencyResponse,
    ///41486 A20E RATIONAL 1
    FocalPlaneXResolution,
    ///41487 A20F RATIONAL 1
    FocalPlaneYResolution,
    ///41488 A210 SHORT 1
    FocalPlaneResolutionUnit,
    ///41492 A214 SHORT 2
    SubjectLocation,
    ///41493 A215 RATIONAL 1
    ExposureIndex,
    ///41495 A217 SHORT 1
    SensingMethod,
    ///41728 A300 UNDEFINED 1
    FileSource,
    ///41729 A301 UNDEFINED 1
    SceneType,
    ///41730 A302 UNDEFINED Any
    CFAPattern,
    ///41985 A401 SHORT 1
    CustomRendered,
    ///41986 A402 SHORT 1
    ExposureMode,
    ///41987 A403 SHORT 1
    WhiteBalance,
    ///41988 A404 RATIONAL 1
    DigitalZoomRatio,
    ///41989 A405 SHORT 1
    FocalLengthIn35mmFilm,
    ///41990 A406 SHORT 1
    SceneCaptureType,
    ///41991 A407 RATIONAL 1
    GainControl,
    ///41992 A408 SHORT 1
    Contrast,
    ///41993 A409 SHORT 1
    Saturation,
    ///41994 A40A SHORT 1
    Sharpness,
    ///41995 A40B UNDEFINED Any
    DeviceSettingDescription,
    ///41996 A40C SHORT 1
    SubjectDistanceRange,
    /// 34665 8769 M M M M
    ExifIFDPointer,
    /// 34853 8825 O O O O
    GPSInfoIFDPointer,
    ERROR,
}
//Uncompressed ,// 32771//

#[derive(PartialEq, Debug)]
pub enum TagTypeSize {
    BYTE,
    ASCII,
    SHORT,
    LONG,
    RATIONAL,
    SBYTE,
    UNDEFINED,
    SSHORT,
    SLONG,
    SRATIONAL,
    FLOAT,
    DOUBLE,
}

impl TagTypeSize {
    pub fn size(&self) -> u32 {
        const BYTE: u32 = 1;
        const ASCII: u32 = 1;
        const SHORT: u32 = 2;
        const LONG: u32 = 4;
        const RATIONAL: u32 = 8;
        const SBYTE: u32 = 1;
        const UNDEFINED: u32 = 1;
        const SSHORT: u32 = 2;
        const SLONG: u32 = 4;
        const SRATIONAL: u32 = 8;
        const FLOAT: u32 = 4;
        const DOUBLE: u32 = 8;
        match self {
            TagTypeSize::BYTE => BYTE,
            TagTypeSize::ASCII => ASCII,
            TagTypeSize::SHORT => SHORT,
            TagTypeSize::LONG => LONG,
            TagTypeSize::RATIONAL => RATIONAL,
            TagTypeSize::SBYTE => SBYTE,
            TagTypeSize::UNDEFINED => UNDEFINED,
            TagTypeSize::SSHORT => SSHORT,
            TagTypeSize::SLONG => SLONG,
            TagTypeSize::SRATIONAL => SRATIONAL,
            TagTypeSize::FLOAT => FLOAT,
            TagTypeSize::DOUBLE => DOUBLE,
        }
    }
}

impl From<u16> for TagTypeSize {
    fn from(num: u16) -> Self {
        return match num {
            1 => TagTypeSize::BYTE,
            2 => TagTypeSize::ASCII,
            3 => TagTypeSize::SHORT,
            4 => TagTypeSize::LONG,
            5 => TagTypeSize::RATIONAL,
            6 => TagTypeSize::SBYTE,
            7 => TagTypeSize::UNDEFINED,
            8 => TagTypeSize::SSHORT,
            9 => TagTypeSize::SLONG,
            10 => TagTypeSize::SRATIONAL,
            11 => TagTypeSize::FLOAT,
            12 => TagTypeSize::DOUBLE,
            _ => TagTypeSize::UNDEFINED
        };
    }
}


impl From<u16> for TagType {
    fn from(num: u16) -> Self {
        return match num {
//            301 => TiffTagsId::ColorResponseCurve,
            0 => TagType::WhiteIsZero,
            1 => TagType::BlackIsZero,
            1 | 32771 => TagType::Uncompressed,
            2 => TagType::CCITT1D,
            2 => TagType::RGB,
            3 => TagType::CCITTGroup3,
            3 => TagType::RgbPalette,
            4 => TagType::CCITTGroup4,
            4 => TagType::TransparencyMask,
            5 => TagType::CMYK,
            5 => TagType::LZW,
            6 => TagType::JPEG,
            6 => TagType::YCbCr,
            8 => TagType::CIELab,
            254 => TagType::NewSubFileType,
            255 => TagType::SubFileType,
            256 => TagType::ImageWidth,
            257 => TagType::ImageHeight,
            258 => TagType::BitsPerSample,
            259 => TagType::Compression,
            262 => TagType::PhotometricInterpretation,
            263 => TagType::Thresholding,
            264 => TagType::CellWidth,
            265 => TagType::CellLength,
            266 => TagType::FillOrder,
            269 => TagType::DocumentName,
            270 => TagType::ImageDescription,
            271 => TagType::Make,
            272 => TagType::Model,
            273 => TagType::StripOffsets,
            274 => TagType::Orientation,
            277 => TagType::SamplesPerPixel,
            278 => TagType::RowsPerStrip,
            279 => TagType::StripByteCounts,
            280 => TagType::MinSampleValue,
            281 => TagType::MaxSampleValue,
            282 => TagType::XResolution,
            283 => TagType::YResolution,
            284 => TagType::PlanarConfiguration,
            285 => TagType::PageName,
            286 => TagType::XPosition,
            287 => TagType::YPosition,
            288 => TagType::FreeOffsets,
            289 => TagType::FreeByteCounts,
            290 => TagType::GrayResponseUnit,
            291 => TagType::GrayResponseCurve,
            292 => TagType::T4Options,
            293 => TagType::T6Options,
            296 => TagType::ResolutionUnit,
            297 => TagType::PageNumber,
            300 => TagType::ColorResponseUnit,
            301 => TagType::TransferFunction,
            305 => TagType::Software,
            306 => TagType::DateTime,
            315 => TagType::Artist,
            316 => TagType::HostComputer,
            317 => TagType::Predictor,
            318 => TagType::WhitePoint,
            319 => TagType::PrimaryChromatics,
            320 => TagType::ColorMap,
            321 => TagType::HalftoneHints,
            322 => TagType::TileWidth,
            323 => TagType::TileLength,
            324 => TagType::TileOffsets,
            325 => TagType::TileByteCounts,
            326 => TagType::BadFaxLines,
            327 => TagType::CleanFaxData,
            328 => TagType::ConsecutiveBadFaxLines,
            332 => TagType::InkSet,
            333 => TagType::InkNames,
            334 => TagType::NumberOfInks,
            336 => TagType::DotRange,
            337 => TagType::TargetPrinter,
            338 => TagType::ExtraSamples,
            339 => TagType::SampleFormat,
            340 => TagType::SMinSampleValue,
            341 => TagType::SMaxSampleValue,
            342 => TagType::TransferRange,
            512 => TagType::JPEGProc,
            513 => TagType::JPEGInterchangeFormat,
            514 => TagType::JPEGInterchangeFormatLength,
            515 => TagType::JPEGRestartInterval,
            517 => TagType::JPEGLosslessPredictors,
            518 => TagType::JPEGPointTransforms,
            519 => TagType::JPEGQTables,
            520 => TagType::JPEGDCTTables,
            521 => TagType::JPEGACTTables,
            529 => TagType::YCbCrCoefficients,
            530 => TagType::YCbCrSubSampling,
            531 => TagType::YCbCrPositioning,
            532 => TagType::ReferenceBlackWhite,
            32773 => TagType::Packbits,
            33432 => TagType::Copyright,
            34665 => TagType::ExifIFDPointer,
            34853 => TagType::GPSInfoIFDPointer,
            36864 => TagType::ExifVersion,
            36867 => TagType::DateTimeOriginal,
            36868 => TagType::DateTimeDigitized,
            37121 => TagType::ComponentsConfiguration,
            37122 => TagType::CompressedBitsPerPixel,
            37500 => TagType::MakerNote,
            37510 => TagType::UserComment,
            37520 => TagType::SubSecTime,
            37521 => TagType::SubSecTimeOriginal,
            37522 => TagType::SubSecTimeDigitized,
            40960 => TagType::FlashpixVersion,
            40961 => TagType::ColorSpace,
            40962 => TagType::PixelXDimension,
            40963 => TagType::PixelYDimension,
            40964 => TagType::RelatedSoundFile,
            42016 => TagType::ImageUniqueID,
            _ => {
                println!("Unknown ID - dec: {}, hex: {0:x}", num);
                TagType::ERROR
            }
        };
    }
}

impl TagType {
    pub fn val(&self) -> u16 {
        return match self {
            TagType::Artist => 315,
            TagType::BadFaxLines => 326,
            TagType::BitsPerSample => 258,
            TagType::CellLength => 265,
            TagType::CellWidth => 264,
            TagType::CleanFaxData => 327,
            TagType::ColorMap => 320,
            //TiffTagsId::ColorResponseCurve => 301,
            TagType::ColorResponseUnit => 300,
            TagType::Compression => 259,
            TagType::Uncompressed => 1,
            TagType::CCITT1D => 2,
            TagType::CCITTGroup3 => 3,
            TagType::CCITTGroup4 => 4,
            TagType::LZW => 5,
            TagType::JPEG => 6,
            TagType::Packbits => 32773,
            TagType::ConsecutiveBadFaxLines => 328,
            TagType::Copyright => 33432,
            TagType::DateTime => 306,
            TagType::DocumentName => 269,
            TagType::DotRange => 336,
            TagType::ExtraSamples => 338,
            TagType::FillOrder => 266,
            TagType::FreeByteCounts => 289,
            TagType::FreeOffsets => 288,
            TagType::GrayResponseCurve => 291,
            TagType::GrayResponseUnit => 290,
            TagType::HalftoneHints => 321,
            TagType::HostComputer => 316,
            TagType::ImageDescription => 270,
            TagType::ImageHeight => 257,
            TagType::ImageWidth => 256,
            TagType::InkNames => 333,
            TagType::InkSet => 332,
            TagType::JPEGACTTables => 521,
            TagType::JPEGDCTTables => 520,
            TagType::JPEGInterchangeFormat => 513,
            TagType::JPEGInterchangeFormatLength => 514,
            TagType::JPEGLosslessPredictors => 517,
            TagType::JPEGPointTransforms => 518,
            TagType::JPEGProc => 512,
            TagType::JPEGRestartInterval => 515,
            TagType::JPEGQTables => 519,
            TagType::Make => 271,
            TagType::MaxSampleValue => 281,
            TagType::MinSampleValue => 280,
            TagType::Model => 272,
            TagType::NewSubFileType => 254,
            TagType::NumberOfInks => 334,
            TagType::Orientation => 274,
            TagType::PageName => 285,
            TagType::PageNumber => 297,
            TagType::PhotometricInterpretation => 262,
            TagType::WhiteIsZero => 0,
            TagType::BlackIsZero => 1,
            TagType::RGB => 2,
            TagType::RgbPalette => 3,
            TagType::TransparencyMask => 4,
            TagType::CMYK => 5,
            TagType::YCbCr => 6,
            TagType::CIELab => 8,
            TagType::PlanarConfiguration => 284,
            TagType::Predictor => 317,
            TagType::PrimaryChromatics => 319,
            TagType::ReferenceBlackWhite => 532,
            TagType::ResolutionUnit => 296,
            TagType::RowsPerStrip => 278,
            TagType::SampleFormat => 339,
            TagType::SamplesPerPixel => 277,
            TagType::SMaxSampleValue => 341,
            TagType::SMinSampleValue => 340,
            TagType::Software => 305,
            TagType::StripByteCounts => 279,
            TagType::StripOffsets => 273,
            TagType::SubFileType => 255,
            TagType::T4Options => 292,
            TagType::T6Options => 293,
            TagType::TargetPrinter => 337,
            TagType::Thresholding => 263,
            TagType::TileByteCounts => 325,
            TagType::TileLength => 323,
            TagType::TileOffsets => 324,
            TagType::TileWidth => 322,
            TagType::TransferFunction => 301,
            TagType::TransferRange => 342,
            TagType::XPosition => 286,
            TagType::XResolution => 282,
            TagType::YCbCrCoefficients => 529,
            TagType::YCbCrPositioning => 531,
            TagType::YCbCrSubSampling => 530,
            TagType::YPosition => 287,
            TagType::YResolution => 283,
            TagType::WhitePoint => 318,
            TagType::ExifVersion => 36864,
            TagType::FlashpixVersion => 40960,
            TagType::ColorSpace => 40961,
            TagType::ComponentsConfiguration => 37121,
            TagType::CompressedBitsPerPixel => 37122,
            TagType::PixelXDimension => 40962,
            TagType::PixelYDimension => 40963,
            TagType::MakerNote => 37500,
            TagType::UserComment => 37510,
            TagType::RelatedSoundFile => 40964,
            TagType::DateTimeOriginal => 36867,
            TagType::DateTimeDigitized => 36868,
            TagType::SubSecTime => 37520,
            TagType::SubSecTimeOriginal => 37521,
            TagType::SubSecTimeDigitized => 37522,
            TagType::ImageUniqueID => 42016,
            TagType::ExposureTime => 33434,
            TagType::FNumber => 33437,
            TagType::ExposureProgram => 34850,
            TagType::SpectralSensitivity => 34852,
            TagType::ISOSpeedRatings => 34855,
            TagType::OECF => 34856,
            TagType::ShutterSpeedValue => 37377,
            TagType::ApertureValue => 37378,
            TagType::BrightnessValue => 37379,
            TagType::ExposureBiasValue => 37380,
            TagType::MaxApertureValue => 37381,
            TagType::SubjectDistance => 37382,
            TagType::MeteringMode => 37383,
            TagType::LightSource => 37384,
            TagType::Flash => 37385,
            TagType::FocalLength => 37386,
            TagType::SubjectArea => 37396,
            TagType::FlashEnergy => 41483,
            TagType::SpatialFrequencyResponse => 41484,
            TagType::FocalPlaneXResolution => 41486,
            TagType::FocalPlaneYResolution => 41487,
            TagType::FocalPlaneResolutionUnit => 41488,
            TagType::SubjectLocation => 41492,
            TagType::ExposureIndex => 41493,
            TagType::SensingMethod => 41495,
            TagType::FileSource => 41728,
            TagType::SceneType => 41729,
            TagType::CFAPattern => 41730,
            TagType::CustomRendered => 41985,
            TagType::ExposureMode => 41986,
            TagType::WhiteBalance => 41987,
            TagType::DigitalZoomRatio => 41988,
            TagType::FocalLengthIn35mmFilm => 41989,
            TagType::SceneCaptureType => 41990,
            TagType::GainControl => 41991,
            TagType::Contrast => 41992,
            TagType::Saturation => 41993,
            TagType::Sharpness => 41994,
            TagType::DeviceSettingDescription => 41995,
            TagType::SubjectDistanceRange => 41996,
            TagType::ExifIFDPointer => 34665,
            TagType::GPSInfoIFDPointer => 34853,
            TagType::ERROR => 0
        };
    }
    pub fn as_str(&self) -> &str {
        return match self {
            TagType::Artist => "Artist",
            TagType::BadFaxLines => "BadFaxLines",
            TagType::BitsPerSample => "BitsPerSample",
            TagType::CellLength => "CellLength",
            TagType::CellWidth => "CellWidth",
            TagType::CleanFaxData => "CleanFaxData",
            TagType::ColorMap => "ColorMap",
            //TiffTagsId::ColorResponseCurve => "ColorResponseCurve",
            TagType::ColorResponseUnit => "ColorResponseUnit",
            TagType::Compression => "Compression",
            TagType::Uncompressed => "Uncompressed",
            TagType::CCITT1D => "CCITT1d",
            TagType::CCITTGroup3 => "CCITTGroup3",
            TagType::CCITTGroup4 => "CCITTGroup4",
            TagType::LZW => "LZW",
            TagType::JPEG => "JPEG",
            TagType::Packbits => "Packbits",
            TagType::ConsecutiveBadFaxLines => "ConsecutiveBadFaxLines",
            TagType::Copyright => "Copyright",
            TagType::DateTime => "DateTime",
            TagType::DocumentName => "DocumentName",
            TagType::DotRange => "DotRange",
            TagType::ExtraSamples => "ExtraSamples",
            TagType::FillOrder => "FillOrder",
            TagType::FreeByteCounts => "FreeByteCounts",
            TagType::FreeOffsets => "FreeOffsets",
            TagType::GrayResponseCurve => "GrayResponseCurve",
            TagType::GrayResponseUnit => "GrayResponseUnit",
            TagType::HalftoneHints => "HalftoneHints",
            TagType::HostComputer => "HostComputer",
            TagType::ImageDescription => "ImageDescription",
            TagType::ImageHeight => "ImageHeight",
            TagType::ImageWidth => "ImageWidth",
            TagType::InkNames => "InkNames",
            TagType::InkSet => "InkSet",
            TagType::JPEGACTTables => "JPEGACTTables",
            TagType::JPEGDCTTables => "JPEGDCTTables",
            TagType::JPEGInterchangeFormat => "JPEGInterchangeFormat",
            TagType::JPEGInterchangeFormatLength => "JPEGInterchangeFormatLength",
            TagType::JPEGLosslessPredictors => "JPEGLosslessPredictors",
            TagType::JPEGPointTransforms => "JPEGPointTransforms",
            TagType::JPEGProc => "JPEGProc",
            TagType::JPEGRestartInterval => "JPEGRestartInterval",
            TagType::JPEGQTables => "JPEGQTables",
            TagType::Make => "Make",
            TagType::MaxSampleValue => "MaxSampleValue",
            TagType::MinSampleValue => "MinSampleValue",
            TagType::Model => "Model",
            TagType::NewSubFileType => "NewSubFileType",
            TagType::NumberOfInks => "NumberOfInks",
            TagType::Orientation => "Orientation",
            TagType::PageName => "PageName",
            TagType::PageNumber => "PageNumber",
            TagType::PhotometricInterpretation => "PhotometricInterpretation",
            TagType::WhiteIsZero => "WhiteIsZero",
            TagType::BlackIsZero => "BlackIsZero",
            TagType::RGB => "RGB",
            TagType::RgbPalette => "RgbPalette",
            TagType::TransparencyMask => "TransparencyMask",
            TagType::CMYK => "CMYK",
            TagType::YCbCr => "YCbCr",
            TagType::CIELab => "CIELab",
            TagType::PlanarConfiguration => "PlanarConfiguration",
            TagType::Predictor => "Predictor",
            TagType::PrimaryChromatics => "PrimaryChromatics",
            TagType::ReferenceBlackWhite => "ReferenceBlackWhite",
            TagType::ResolutionUnit => "ResolutionUnit",
            TagType::RowsPerStrip => "RowsPerStrip",
            TagType::SampleFormat => "SampleFormat",
            TagType::SamplesPerPixel => "SamplesPerPixel",
            TagType::SMaxSampleValue => "SMaxSampleValue",
            TagType::SMinSampleValue => "SMinSampleValue",
            TagType::Software => "Software",
            TagType::StripByteCounts => "StripByteCounts",
            TagType::StripOffsets => "StripOffsets",
            TagType::SubFileType => "SubFileType",
            TagType::T4Options => "T4Options",
            TagType::T6Options => "T6Options",
            TagType::TargetPrinter => "TargetPrinter",
            TagType::Thresholding => "Thresholding",
            TagType::TileByteCounts => "TileByteCounts",
            TagType::TileLength => "TileLength",
            TagType::TileOffsets => "TileOffsets",
            TagType::TileWidth => "TileWidth",
            TagType::TransferFunction => "TransferFunction",
            TagType::TransferRange => "TransferRange",
            TagType::XPosition => "XPosition",
            TagType::XResolution => "XResolution",
            TagType::YCbCrCoefficients => "YCbCrCoefficients",
            TagType::YCbCrPositioning => "YCbCrPositioning",
            TagType::YCbCrSubSampling => "YCbCrSubSampling",
            TagType::YPosition => "YPosition",
            TagType::YResolution => "YResolution",
            TagType::WhitePoint => "WhitePoint",
            TagType::ExifVersion => "ExifVersion",
            TagType::FlashpixVersion => "FlashpixVersion",
            TagType::ColorSpace => "ColorSpace",
            TagType::ComponentsConfiguration => "ComponentsConfiguration",
            TagType::CompressedBitsPerPixel => "CompressedBitsPerPixel",
            TagType::PixelXDimension => "PixelXDimension",
            TagType::PixelYDimension => "PixelYDimension",
            TagType::MakerNote => "MakerNote",
            TagType::UserComment => "UserComment",
            TagType::RelatedSoundFile => "RelatedSoundFile",
            TagType::DateTimeOriginal => "DateTimeOriginal",
            TagType::DateTimeDigitized => "DateTimeDigitized",
            TagType::SubSecTime => "SubSecTime",
            TagType::SubSecTimeOriginal => "SubSecTimeOriginal",
            TagType::SubSecTimeDigitized => "SubSecTimeDigitized",
            TagType::ImageUniqueID => "ImageUniqueID",
            TagType::ExposureTime => "ExposureTime",
            TagType::FNumber => "FNumber",
            TagType::ExposureProgram => "ExposureProgram",
            TagType::SpectralSensitivity => "SpectralSensitivity",
            TagType::ISOSpeedRatings => "ISOSpeedRatings",
            TagType::OECF => "OECF",
            TagType::ShutterSpeedValue => "ShutterSpeedValue",
            TagType::ApertureValue => "ApertureValue",
            TagType::BrightnessValue => "BrightnessValue",
            TagType::ExposureBiasValue => "ExposureBiasValue",
            TagType::MaxApertureValue => "MaxApertureValue",
            TagType::SubjectDistance => "SubjectDistance",
            TagType::MeteringMode => "MeteringMode",
            TagType::LightSource => "LightSource",
            TagType::Flash => "Flash",
            TagType::FocalLength => "FocalLength",
            TagType::SubjectArea => "SubjectArea",
            TagType::FlashEnergy => "FlashEnergy",
            TagType::SpatialFrequencyResponse => "SpatialFrequencyResponse",
            TagType::FocalPlaneXResolution => "FocalPlaneXResolution",
            TagType::FocalPlaneYResolution => "FocalPlaneYResolution",
            TagType::FocalPlaneResolutionUnit => "FocalPlaneResolutionUnit",
            TagType::SubjectLocation => "SubjectLocation",
            TagType::ExposureIndex => "ExposureIndex",
            TagType::SensingMethod => "SensingMethod",
            TagType::FileSource => "FileSource",
            TagType::SceneType => "SceneType",
            TagType::CFAPattern => "CFAPattern",
            TagType::CustomRendered => "CustomRendered",
            TagType::ExposureMode => "ExposureMode",
            TagType::WhiteBalance => "WhiteBalance",
            TagType::DigitalZoomRatio => "DigitalZoomRatio",
            TagType::FocalLengthIn35mmFilm => "FocalLengthIn35mmFilm",
            TagType::SceneCaptureType => "SceneCaptureType",
            TagType::GainControl => "GainControl",
            TagType::Contrast => "Contrast",
            TagType::Saturation => "Saturation",
            TagType::Sharpness => "Sharpness",
            TagType::DeviceSettingDescription => "DeviceSettingDescription",
            TagType::SubjectDistanceRange => "SubjectDistanceRange",
            TagType::ExifIFDPointer => "ExifIFDPointer",
            TagType::GPSInfoIFDPointer => "GPSInfoIFDPointer",
            TagType::ERROR => "ERROR"
        };
    }
    pub fn type_size(&self) -> TagTypeSize {
        match self {
            TagType::Artist => TagTypeSize::ASCII,
            TagType::BadFaxLines => TagTypeSize::LONG,
            TagType::BitsPerSample => TagTypeSize::SHORT,
            TagType::CellLength => TagTypeSize::SHORT,
            TagType::CellWidth => TagTypeSize::SHORT,
            TagType::CleanFaxData => TagTypeSize::SHORT,
            TagType::ColorMap => TagTypeSize::SHORT,
            TagType::ColorResponseUnit => TagTypeSize::SHORT,
            TagType::Compression => TagTypeSize::SHORT,
            TagType::Uncompressed => TagTypeSize::UNDEFINED,
            TagType::CCITT1D => TagTypeSize::UNDEFINED,
            TagType::CCITTGroup3 => TagTypeSize::UNDEFINED,
            TagType::CCITTGroup4 => TagTypeSize::UNDEFINED,
            TagType::LZW => TagTypeSize::UNDEFINED,
            TagType::JPEG => TagTypeSize::UNDEFINED,
            TagType::Packbits => TagTypeSize::UNDEFINED,
            TagType::ConsecutiveBadFaxLines => TagTypeSize::LONG,
            TagType::Copyright => TagTypeSize::ASCII,
            TagType::DateTime => TagTypeSize::ASCII,
            TagType::DocumentName => TagTypeSize::ASCII,
            TagType::DotRange => TagTypeSize::SHORT,
            TagType::ExtraSamples => TagTypeSize::BYTE,
            TagType::FillOrder => TagTypeSize::SHORT,
            TagType::FreeByteCounts => TagTypeSize::LONG,
            TagType::FreeOffsets => TagTypeSize::LONG,
            TagType::GrayResponseCurve => TagTypeSize::SHORT,
            TagType::GrayResponseUnit => TagTypeSize::SHORT,
            TagType::HalftoneHints => TagTypeSize::SHORT,
            TagType::HostComputer => TagTypeSize::ASCII,
            TagType::ImageDescription => TagTypeSize::ASCII,
            TagType::ImageHeight => TagTypeSize::LONG,
            TagType::ImageWidth => TagTypeSize::LONG,
            TagType::InkNames => TagTypeSize::ASCII,
            TagType::InkSet => TagTypeSize::LONG,
            TagType::JPEGACTTables => TagTypeSize::LONG,
            TagType::JPEGDCTTables => TagTypeSize::LONG,
            TagType::JPEGInterchangeFormat => TagTypeSize::LONG,
            TagType::JPEGInterchangeFormatLength => TagTypeSize::LONG,
            TagType::JPEGLosslessPredictors => TagTypeSize::SHORT,
            TagType::JPEGPointTransforms => TagTypeSize::SHORT,
            TagType::JPEGProc => TagTypeSize::SHORT,
            TagType::JPEGRestartInterval => TagTypeSize::SHORT,
            TagType::JPEGQTables => TagTypeSize::LONG,
            TagType::Make => TagTypeSize::ASCII,
            TagType::MaxSampleValue => TagTypeSize::SHORT,
            TagType::MinSampleValue => TagTypeSize::SHORT,
            TagType::Model => TagTypeSize::ASCII,
            TagType::NewSubFileType => TagTypeSize::LONG,
            TagType::NumberOfInks => TagTypeSize::SHORT,
            TagType::Orientation => TagTypeSize::SHORT,
            TagType::PageName => TagTypeSize::ASCII,
            TagType::PageNumber => TagTypeSize::SHORT,
            TagType::PhotometricInterpretation => TagTypeSize::SHORT,
            TagType::WhiteIsZero => TagTypeSize::UNDEFINED,
            TagType::BlackIsZero => TagTypeSize::UNDEFINED,
            TagType::RGB => TagTypeSize::UNDEFINED,
            TagType::RgbPalette => TagTypeSize::UNDEFINED,
            TagType::TransparencyMask => TagTypeSize::UNDEFINED,
            TagType::CMYK => TagTypeSize::UNDEFINED,
            TagType::YCbCr => TagTypeSize::UNDEFINED,
            TagType::CIELab => TagTypeSize::UNDEFINED,
            TagType::PlanarConfiguration => TagTypeSize::SHORT,
            TagType::Predictor => TagTypeSize::SHORT,
            TagType::PrimaryChromatics => TagTypeSize::RATIONAL,
            TagType::ReferenceBlackWhite => TagTypeSize::LONG,
            TagType::ResolutionUnit => TagTypeSize::SHORT,
            TagType::RowsPerStrip => TagTypeSize::LONG,
            TagType::SampleFormat => TagTypeSize::SHORT,
            TagType::SamplesPerPixel => TagTypeSize::SHORT,
            TagType::SMaxSampleValue => TagTypeSize::UNDEFINED,
            TagType::SMinSampleValue => TagTypeSize::UNDEFINED,
            TagType::Software => TagTypeSize::ASCII,
            TagType::StripByteCounts => TagTypeSize::LONG,
            TagType::StripOffsets => TagTypeSize::LONG,
            TagType::SubFileType => TagTypeSize::SHORT,
            TagType::T4Options => TagTypeSize::LONG,
            TagType::T6Options => TagTypeSize::LONG,
            TagType::TargetPrinter => TagTypeSize::ASCII,
            TagType::Thresholding => TagTypeSize::SHORT,
            TagType::TileByteCounts => TagTypeSize::LONG,
            TagType::TileLength => TagTypeSize::LONG,
            TagType::TileOffsets => TagTypeSize::LONG,
            TagType::TileWidth => TagTypeSize::LONG,
            TagType::TransferFunction => TagTypeSize::SHORT,
            TagType::TransferRange => TagTypeSize::SHORT,
            TagType::XPosition => TagTypeSize::RATIONAL,
            TagType::XResolution => TagTypeSize::RATIONAL,
            TagType::YCbCrCoefficients => TagTypeSize::RATIONAL,
            TagType::YCbCrPositioning => TagTypeSize::SHORT,
            TagType::YCbCrSubSampling => TagTypeSize::SHORT,
            TagType::YPosition => TagTypeSize::RATIONAL,
            TagType::YResolution => TagTypeSize::RATIONAL,
            TagType::WhitePoint => TagTypeSize::RATIONAL,
            TagType::ExifVersion => TagTypeSize::UNDEFINED,
            TagType::FlashpixVersion => TagTypeSize::UNDEFINED,
            TagType::ColorSpace => TagTypeSize::SHORT,
            TagType::ComponentsConfiguration => TagTypeSize::UNDEFINED,
            TagType::CompressedBitsPerPixel => TagTypeSize::RATIONAL,
            TagType::PixelXDimension => TagTypeSize::LONG,
            TagType::PixelYDimension => TagTypeSize::LONG,
            TagType::MakerNote => TagTypeSize::UNDEFINED,
            TagType::UserComment => TagTypeSize::UNDEFINED,
            TagType::RelatedSoundFile => TagTypeSize::ASCII,
            TagType::DateTimeOriginal => TagTypeSize::ASCII,
            TagType::DateTimeDigitized => TagTypeSize::ASCII,
            TagType::SubSecTime => TagTypeSize::ASCII,
            TagType::SubSecTimeOriginal => TagTypeSize::ASCII,
            TagType::SubSecTimeDigitized => TagTypeSize::ASCII,
            TagType::ImageUniqueID => TagTypeSize::ASCII,
            TagType::ExposureTime => TagTypeSize::RATIONAL,
            TagType::FNumber => TagTypeSize::RATIONAL,
            TagType::ExposureProgram => TagTypeSize::SHORT,
            TagType::SpectralSensitivity => TagTypeSize::ASCII,
            TagType::ISOSpeedRatings => TagTypeSize::SHORT,
            TagType::OECF => TagTypeSize::UNDEFINED,
            TagType::ShutterSpeedValue => TagTypeSize::SRATIONAL,
            TagType::ApertureValue => TagTypeSize::RATIONAL,
            TagType::BrightnessValue => TagTypeSize::SRATIONAL,
            TagType::ExposureBiasValue => TagTypeSize::SRATIONAL,
            TagType::MaxApertureValue => TagTypeSize::RATIONAL,
            TagType::SubjectDistance => TagTypeSize::RATIONAL,
            TagType::MeteringMode => TagTypeSize::SHORT,
            TagType::LightSource => TagTypeSize::SHORT,
            TagType::Flash => TagTypeSize::SHORT,
            TagType::FocalLength => TagTypeSize::RATIONAL,
            TagType::SubjectArea => TagTypeSize::SHORT,
            TagType::FlashEnergy => TagTypeSize::RATIONAL,
            TagType::SpatialFrequencyResponse => TagTypeSize::UNDEFINED,
            TagType::FocalPlaneXResolution => TagTypeSize::RATIONAL,
            TagType::FocalPlaneYResolution => TagTypeSize::RATIONAL,
            TagType::FocalPlaneResolutionUnit => TagTypeSize::SHORT,
            TagType::SubjectLocation => TagTypeSize::SHORT,
            TagType::ExposureIndex => TagTypeSize::RATIONAL,
            TagType::SensingMethod => TagTypeSize::SHORT,
            TagType::FileSource => TagTypeSize::UNDEFINED,
            TagType::SceneType => TagTypeSize::UNDEFINED,
            TagType::CFAPattern => TagTypeSize::UNDEFINED,
            TagType::CustomRendered => TagTypeSize::SHORT,
            TagType::ExposureMode => TagTypeSize::SHORT,
            TagType::WhiteBalance => TagTypeSize::SHORT,
            TagType::DigitalZoomRatio => TagTypeSize::RATIONAL,
            TagType::FocalLengthIn35mmFilm => TagTypeSize::SHORT,
            TagType::SceneCaptureType => TagTypeSize::SHORT,
            TagType::GainControl => TagTypeSize::RATIONAL,
            TagType::Contrast => TagTypeSize::SHORT,
            TagType::Saturation => TagTypeSize::SHORT,
            TagType::Sharpness => TagTypeSize::SHORT,
            TagType::DeviceSettingDescription => TagTypeSize::UNDEFINED,
            TagType::SubjectDistanceRange => TagTypeSize::SHORT,
            TagType::ExifIFDPointer => TagTypeSize::LONG,
            TagType::GPSInfoIFDPointer => TagTypeSize::LONG,
            TagType::ERROR => TagTypeSize::UNDEFINED,
        }
    }
}

impl std::fmt::LowerHex for TagType {
    fn fmt(&self, f: &mut Formatter<'_>) -> Result<(), Error> {
        write!(f, "{:x}", self.val())
    }
}

impl std::fmt::Display for TagType {
    fn fmt(&self, f: &mut Formatter<'_>) -> Result<(), Error> {
        write!(f, "{}", self.as_str())
    }
}