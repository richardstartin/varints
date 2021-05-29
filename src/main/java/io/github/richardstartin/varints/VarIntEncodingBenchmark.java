package io.github.richardstartin.varints;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@OutputTimeUnit(MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 1, jvmArgsPrepend = {"-Xms1G", "-Xmx1G", "-XX:+AlwaysPreTouch", "-XX:+UseSerialGC"})
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class VarIntEncodingBenchmark {

  @Benchmark
  public void counted(CountedVarIntState state, Blackhole bh) {
    state.execute(bh);
  }

  @Benchmark
  public void countedSwitch(CountedSwitchVarIntState state, Blackhole bh) {
    state.execute(bh);
  }

  @Benchmark
  public void basic(BasicVarIntState state, Blackhole bh) {
    state.execute(bh);
  }

  @Benchmark
  public void unrolled(UnrolledVarIntState state, Blackhole bh) {
    state.execute(bh);
  }

  @Benchmark
  public void smartUnrolled(SmartUnrolledVarIntState state, Blackhole bh) {
    state.execute(bh);
  }

  @Benchmark
  public void smartUnrolledNoDataDependency(SmartNoDataDependencyVarIntState state, Blackhole bh) {
    state.execute(bh);
  }
}
