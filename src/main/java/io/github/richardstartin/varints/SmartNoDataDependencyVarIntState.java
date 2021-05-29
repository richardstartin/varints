package io.github.richardstartin.varints;

public class SmartNoDataDependencyVarIntState extends VarIntState {
  @Override
  public void write(long value) {
    int i = 0;
      if ((value & (0xFFFFFFFFFFFFFFFFL << 7)) == 0) {
        buffer[i] = (byte)(value);
      } else {
        buffer[i++] = (byte)((value & 0x7F) | 0x80);
        if ((value & (0xFFFFFFFFFFFFFFFFL << 14)) == 0) {
          buffer[i] = (byte)(value >>> 7);
        } else {
          buffer[i++] = (byte)(((value >>> 7) & 0x7F) | 0x80);
          if ((value & (0xFFFFFFFFFFFFFFFFL << 21)) == 0) {
            buffer[i] = (byte)(value >>> 14);
          } else {
            buffer[i++] = (byte)(((value >>> 14) & 0x7F) | 0x80);
            if ((value & (0xFFFFFFFFFFFFFFFFL << 28)) == 0) {
              buffer[i] = (byte)(value >>> 21);
            } else {
              buffer[i++] = (byte)(((value >>> 21) & 0x7F) | 0x80);
              if ((value & (0xFFFFFFFFFFFFFFFFL << 35)) == 0) {
                buffer[i] = (byte)(value >>> 28);
              } else {
                buffer[i++] = (byte)(((value >>> 28) & 0x7F) | 0x80);
                if ((value & (0xFFFFFFFFFFFFFFFFL << 42)) == 0) {
                  buffer[i] = (byte)(value >>> 35);
                } else {
                  buffer[i++] = (byte)(((value >>> 35) & 0x7F) | 0x80);
                  if ((value & (0xFFFFFFFFFFFFFFFFL << 49)) == 0) {
                    buffer[i] = (byte)(value >>> 42);
                  } else {
                    buffer[i++] = (byte)(((value >>> 42) & 0x7F) | 0x80);
                    if ((value & (0xFFFFFFFFFFFFFFFFL << 56)) == 0) {
                      buffer[i] = (byte)(value >>> 49);
                    } else {
                      buffer[i++] = (byte)(((value >>> 49) & 0x7F) | 0x80);
                      if ((value & (0xFFFFFFFFFFFFFFFFL << 63)) == 0) {
                        buffer[i] = (byte)(value >>> 56);
                      } else {
                        buffer[i++] = (byte)((value >>> 56) & 0x7F | 0x80);
                        buffer[i] = (byte)(value >>> 63);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
  }
}
