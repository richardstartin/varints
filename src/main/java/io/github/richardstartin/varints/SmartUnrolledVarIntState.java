package io.github.richardstartin.varints;

public class SmartUnrolledVarIntState extends VarIntState {
  @Override
  public void write(long value) {
    int i = 0;
    if ((value & ~0x7FL) == 0) {
      buffer[i] = (byte)(value);
    } else {
      buffer[i++] = (byte)(value & 0x7F | 0x80);
      value >>>= 7;
      if ((value & ~0x7FL) == 0) {
        buffer[i] = (byte)(value);
      } else {
        buffer[i++] = (byte)(value & 0x7F | 0x80);
        value >>>= 7;
        if ((value & ~0x7FL) == 0) {
          buffer[i] = (byte)(value);
        } else {
          buffer[i++] = (byte)(value & 0x7F | 0x80);
          value >>>= 7;
          if ((value & ~0x7FL) == 0) {
            buffer[i] = (byte)(value);
          } else {
            buffer[i++] = (byte)(value & 0x7F | 0x80);
            value >>>= 7;
            if ((value & ~0x7FL) == 0) {
              buffer[i] = (byte)(value);
            } else {
              buffer[i++] = (byte)(value & 0x7F | 0x80);
              value >>>= 7;
              if ((value & ~0x7FL) == 0) {
                buffer[i] = (byte)(value);
              } else {
                buffer[i++] = (byte)(value & 0x7F | 0x80);
                value >>>= 7;
                if ((value & ~0x7FL) == 0) {
                  buffer[i] = (byte)(value);
                } else {
                  buffer[i++] = (byte)(value & 0x7F | 0x80);
                  value >>>= 7;
                  if ((value & ~0x7FL) == 0) {
                    buffer[i] = (byte)(value);
                  } else {
                    buffer[i++] = (byte)(value & 0x7F | 0x80);
                    value >>>= 7;
                    if ((value & ~0x7FL) == 0) {
                      buffer[i] = (byte)(value);
                    } else {
                      buffer[i++] = (byte)(value & 0x7F | 0x80);
                      value >>>= 7;
                      buffer[i] = (byte)(value);
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
