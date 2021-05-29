package io.github.richardstartin.varints;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.SplittableRandom;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

@OutputTimeUnit(NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 1, jvmArgsPrepend = {"-Xms1G", "-Xmx1G", "-XX:+AlwaysPreTouch", "-XX:+UseSerialGC"})
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Benchmark)
public class LatencyBenchmark {

  @Param({"7", "14", "21", "28", "35", "42", "49", "56", "62"})
  private int bits;

  private static final int[] VAR_INT_LENGTHS = new int[65];

  static {
    for (int i = 0; i <= 64; ++i) {
      VAR_INT_LENGTHS[i] = (63 - i) / 7;
    }
  }

  private long[] data;
  private final byte[] target = new byte[10];

  int position = 0;

  @Setup(Level.Trial)
  public void setup() {
    SplittableRandom random = new SplittableRandom(0);
    data = new long[1 << 16];
    for (int i = 0; i < data.length; ++i) {
      data[i] = random.nextLong(1L << (bits - 7),1L << bits);
    }
    data[0] = 0;
  }

  @Benchmark
  public void varInt(Blackhole bh) {
    long value = nextValue();
    int i = 0;
    while (true) {
      if ((value & ~0x7FL) == 0) {
        target[i] = (byte) value;
        break;
      } else {
        target[i++] = (byte) ((value & 0x7F) | 0x80);
        value >>>= 7;
      }
    }
    bh.consume(target);
  }

  @Benchmark
  public void varIntCounted(Blackhole bh) {
    long value = nextValue();
    int length = VAR_INT_LENGTHS[Long.numberOfLeadingZeros(value)];
    for (int i = 0; i < length; ++i) {
      target[i] = (byte) ((value & 0x7F) | 0x80);
      value >>>= 7;
    }
    target[length] = (byte) value;
    bh.consume(target);
  }

  private long nextValue() {
    return data[(position++) & (data.length - 1)];
  }
}

