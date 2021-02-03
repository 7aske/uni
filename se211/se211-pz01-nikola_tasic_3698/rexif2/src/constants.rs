/// The TIFF Image File Header (IFH) contains
/// three fields of information and is a total of only eight bytes in length.
/// Constants representing the beginning of the TIFF file header
///
/// MM(Motorola) - Big endian byte order
/// II(Intel)    - Little endian byte order
///
/// C library representation
///
/// typedef struct _TiffHeader {
///     WORD  Identifier; /* Byte-order Identifier     (MM or II)     - 2 bytes */
///     WORD  Version;    /* TIFF version number       (always 2A)    - 2 bytes */
///     DWORD IFDOffset;  /* Offset of the first Image File Directory - 4 bytes */
/// } TIFHEAD;
///
pub const TIFF_BE_MM: [u8; 4] = [0x4d, 0x4d, 0x00, 0x2a];
pub const TIFF_LE_II: [u8; 4] = [0x49, 0x49, 0x2a, 0x00];
pub const TIFF_II: [u8; 2] = [0x49, 0x49];
pub const TIFF_MM: [u8; 2] = [0x4d, 0x4d];
