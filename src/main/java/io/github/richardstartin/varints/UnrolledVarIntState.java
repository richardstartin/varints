package io.github.richardstartin.varints;

public class UnrolledVarIntState extends VarIntState {
  @Override
  public void write(long value) {
    if (value < 0) {
      buffer[0] = (byte)(value & 0x7F | 0x80);
      buffer[1] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[2] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[3] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[4] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[5] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[6] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[7] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[8] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[9] = (byte)(value >>> 7);
    } else if (value < 1L << 7) {
      buffer[0] = (byte)(value);
    } else if (value < 1L << 14) {
      buffer[0] = (byte)(value & 0x7F | 0x80);
      buffer[1] = (byte)(value >>> 7);
    } else if (value < 1L << 21) {
      buffer[0] = (byte)(value & 0x7F | 0x80);
      buffer[1] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[2] = (byte)(value >>> 7);
    } else if (value < 1L << 28) {
      buffer[0] = (byte)(value & 0x7F | 0x80);
      buffer[1] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[2] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[3] = (byte)(value >>> 7);
    } else if (value < 1L << 35) {
      buffer[0] = (byte)(value & 0x7F | 0x80);
      buffer[1] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[2] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[3] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[4] = (byte)(value >>> 7);
    } else if (value < 1L << 42) {
      buffer[0] = (byte)(value & 0x7F | 0x80);
      buffer[1] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[2] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[3] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[4] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[5] = (byte)(value >>> 7);
    } else if (value < 1L << 49) {
      buffer[0] = (byte)(value & 0x7F | 0x80);
      buffer[1] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[2] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[3] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[4] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[5] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[6] = (byte)(value >>> 7);
    } else if (value < 1L << 56) {
      buffer[0] = (byte)(value & 0x7F | 0x80);
      buffer[1] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[2] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[3] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[4] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[5] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[6] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[7] = (byte)(value >>> 7);
    } else {
      buffer[0] = (byte)(value & 0x7F | 0x80);
      buffer[1] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[2] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[3] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[4] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[5] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[6] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[7] = (byte)((value >>>= 7) & 0x7F | 0x80);
      buffer[8] = (byte)(value >>> 7);
    }
  }
}
