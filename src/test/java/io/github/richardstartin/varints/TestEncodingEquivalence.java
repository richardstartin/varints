package io.github.richardstartin.varints;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestEncodingEquivalence {

  public static Stream<Arguments> params() {
    return Stream.of(CountedVarIntState.class,
            CountedSwitchVarIntState.class,
            SmartUnrolledVarIntState.class,
            SmartNoDataDependencyVarIntState.class,
            UnrolledVarIntState.class)
            .map(clazz -> {
              try {
                VarIntState state = clazz.getDeclaredConstructor().newInstance();
                state.init();
                return state;
              } catch (Throwable t) {
                return null;
              }
            }).map(Arguments::of);
  }

  @ParameterizedTest
  @MethodSource("params")
  public void testEquivalence(VarIntState candidate) {
    BasicVarIntState baseline = new BasicVarIntState();
    baseline.init();
    for (long l : new long[]{-1L, 0L,
            0xFFL, 0xFFFL, 0xFFFFL, 0xFFFFFL, 0xFFFFFL, 0xFFFFFFL, 0xFFFFFFFL, 0xFFFFFFFFL, 0xFFFFFFFFFL, 0xFFFFFFFFFFL, 0xFFFFFFFFFFFL, 0xFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFL}) {
      baseline.write(l);
      candidate.write(l);
      assertArrayEquals(baseline.buffer, candidate.buffer, l + "");
    }
    for (int i = 0; i < 1_000_000; ++i) {
      long l = ThreadLocalRandom.current().nextLong();
      baseline.write(l);
      candidate.write(l);
      assertArrayEquals(baseline.buffer, candidate.buffer, l + "");
    }
  }
}
