package io.github.richardstartin.varints;

public class CountedSwitchVarIntState extends VarIntState {

  private static final int[] VAR_INT_LENGTHS = new int[65];
  static {
    for (int i = 0; i <= 64; ++i) {
      VAR_INT_LENGTHS[i] = ((63 - i) / 7);
    }
  }

  @Override
  public void write(long value) {
    int length = VAR_INT_LENGTHS[Long.numberOfLeadingZeros(value)];
    buffer[length] = (byte)(value >>> (length * 7));
    switch (length - 1) {
      case 8:
        buffer[8] = (byte)((value >>> 56) | 0x80);
      case 7:
        buffer[7] = (byte)((value >>> 49) | 0x80);
      case 6:
        buffer[6] = (byte)((value >>> 42) | 0x80);
      case 5:
        buffer[5] = (byte)((value >>> 35) | 0x80);
      case 4:
        buffer[4] = (byte)((value >>> 28) | 0x80);
      case 3:
        buffer[3] = (byte)((value >>> 21) | 0x80);
      case 2:
        buffer[2] = (byte)((value >>> 14) | 0x80);
      case 1:
        buffer[1] = (byte)((value >>> 7) | 0x80);
      case 0:
        buffer[0] = (byte)(value | 0x80);
    }
  }
}
