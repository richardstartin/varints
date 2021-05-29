package io.github.richardstartin.varints;

public class CountedVarIntState extends VarIntState {
  private static final int[] VAR_INT_LENGTHS = new int[65];
  static {
    for (int i = 0; i <= 64; ++i) {
      VAR_INT_LENGTHS[i] = ((63 - i) / 7);
    }
  }

  @Override
  public void write(long value) {
    int length = VAR_INT_LENGTHS[Long.numberOfLeadingZeros(value)];
    for (int i = 0; i < length; ++i) {
      buffer[i] = (byte)((value & 0x7F) | 0x80);
      value >>>= 7;
    }
    buffer[length] = (byte)value;
  }
}
