package io.github.richardstartin.varints;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.SplittableRandom;

@State(Scope.Benchmark)
public abstract class VarIntState {

  @Param({"2048", "4096"})
  int size;

  @Param({"1", "3", "5", "7"})
  int lastNonZeroByte;

  @Param("42")
  int seed;

  private long[] data;
  protected byte[] buffer;


  @Setup(Level.Trial)
  public void init() {
    data = new long[size];
    buffer = new byte[10];
    SplittableRandom random = new SplittableRandom(seed);
    for (int i = 0; i < data.length; ++i) {
      data[i] = random.nextLong() & ((1L << (8 * lastNonZeroByte)) - 1);
    }
  }

  public void execute(Blackhole bh) {
    for (long datum : data) {
      write(datum);
      bh.consume(buffer);
    }
  }

  public abstract void write(long value);

  public byte[] buffer() {
    return buffer;
  }
}
