package io.github.richardstartin.varints;

public class BasicVarIntState extends VarIntState{
  @Override
  public void write(long value) {
    int i = 0;
    while (true) {
      if ((value & ~0x7FL) == 0) {
        buffer[i] = (byte) value;
        return;
      } else {
        buffer[i++] = (byte) ((value & 0x7F) | 0x80);
        value >>>= 7;
      }
    }
  }
}
