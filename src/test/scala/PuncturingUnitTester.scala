package modem

import dsptools.DspTester

class PuncturingUnitTester[T <: chisel3.Data](c: Puncturing[T]) extends DspTester(c) {
  poke(c.io.inReady, 0)
  poke(c.io.isHead, 1)
  poke(c.io.stateIn, 2)
  poke(c.io.puncMatrix(0), 0)
  poke(c.io.puncMatrix(1), 0)
  poke(c.io.puncMatrix(2), 1)
  poke(c.io.puncMatrix(3), 1)
  step(1)
  poke(c.io.inReady, 1)
  poke(c.io.stateIn, 0)
  poke(c.io.isHead, 0)
  poke(c.io.in(0), 1)
  poke(c.io.in(1), 1)
  expect(c.io.out(0), 0)
  expect(c.io.out(1), 0)
  expect(c.io.out(2), 0)
  expect(c.io.out(3), 0)
  expect(c.io.out(4), 0)
  expect(c.io.out(5), 0)
  expect(c.io.stateOut, 0)
  step(1)
  poke(c.io.in(0), 1)
  poke(c.io.in(1), 1)
  expect(c.io.out(0), 1)
  expect(c.io.out(1), 1) // check point. next: 1
  expect(c.io.out(2), 0)
  expect(c.io.out(3), 0)
  expect(c.io.out(4), 0)
  expect(c.io.out(5), 0)
  expect(c.io.stateOut, 0)
  step(1)
  poke(c.io.in(0), 0)
  poke(c.io.in(1), 1)
  expect(c.io.out(0), 1)
  expect(c.io.out(1), 1)
  expect(c.io.out(2), 1)  // check point. next: 1
  expect(c.io.out(3), 0)
  expect(c.io.out(4), 0)
  expect(c.io.out(5), 0)
  expect(c.io.stateOut, 0)
  step(1)
  poke(c.io.in(0), 1)
  poke(c.io.in(1), 0)
  expect(c.io.out(0), 1)
  expect(c.io.out(1), 1)
  expect(c.io.out(2), 1)
  expect(c.io.out(3), 1)  // check point. next: 2
  expect(c.io.out(4), 0)
  expect(c.io.out(5), 0)
  expect(c.io.stateOut, 2)
  step(1)
  poke(c.io.in(0), 0)
  poke(c.io.in(1), 1)
  expect(c.io.out(0), 1)
  expect(c.io.out(1), 1)
  expect(c.io.out(2), 1)
  expect(c.io.out(3), 1)
  expect(c.io.out(4), 1)
  expect(c.io.out(5), 0)  // check point. next: 1
  expect(c.io.stateOut, 0)
  step(1)
  poke(c.io.in(0), 1)
  poke(c.io.in(1), 0)
  expect(c.io.out(0), 0)  // check point. next: 1
  expect(c.io.out(1), 1)
  expect(c.io.out(2), 1)
  expect(c.io.out(3), 1)
  expect(c.io.out(4), 1)
  expect(c.io.out(5), 0)
  expect(c.io.stateOut, 0)
  step(1)
  poke(c.io.in(0), 1)
  poke(c.io.in(1), 1)
  expect(c.io.out(0), 0)
  expect(c.io.out(1), 0)  // check point. next: 2
  expect(c.io.out(2), 1)
  expect(c.io.out(3), 1)
  expect(c.io.out(4), 1)
  expect(c.io.out(5), 0)
  expect(c.io.stateOut, 0)
  step(1)
  poke(c.io.in(0), 1)
  poke(c.io.in(1), 1)
  expect(c.io.out(0), 0)
  expect(c.io.out(1), 0)
  expect(c.io.out(2), 1)
  expect(c.io.out(3), 1)  // check point. next: 1
  expect(c.io.out(4), 1)
  expect(c.io.out(5), 0)
  expect(c.io.stateOut, 0)
  step(1)
  poke(c.io.in(0), 1)
  poke(c.io.in(1), 1)
  expect(c.io.out(0), 0)
  expect(c.io.out(1), 0)
  expect(c.io.out(2), 1)
  expect(c.io.out(3), 1)
  expect(c.io.out(4), 1)  // check point. next: 1
  expect(c.io.out(5), 0)
  expect(c.io.stateOut, 2)
  step(1)
  poke(c.io.in(0), 1)
  poke(c.io.in(1), 1)
  expect(c.io.out(0), 0)
  expect(c.io.out(1), 0)
  expect(c.io.out(2), 1)
  expect(c.io.out(3), 1)
  expect(c.io.out(4), 1)
  expect(c.io.out(5), 1)  // check point. next: 1
  expect(c.io.stateOut, 0)
  step(1)
  poke(c.io.in(0), 1)
  poke(c.io.in(1), 1)
  expect(c.io.out(0), 1)
  expect(c.io.out(1), 1)  // check point. next: 1
  expect(c.io.out(2), 1)
  expect(c.io.out(3), 1)
  expect(c.io.out(4), 1)
  expect(c.io.out(5), 1)
  expect(c.io.stateOut, 0)
}

  /**
    * Convenience function for running tests
    */
object FixedPuncturingTester {
  def apply(params: FixedCoding): Boolean = {
    chisel3.iotesters.Driver.execute(Array("-tbn", "firrtl", "-fiwv"), () => new Puncturing(params)) {
      c => new PuncturingUnitTester(c)
    }
  }
}

// Appendix:
// confirm that when puncturing is disabled, everything works fine.
// ****** puncturing disabled ******
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 1)
//poke(c.io.stateIn, 0)
//expect(c.io.out(0), 0)
//expect(c.io.out(1), 0)
//expect(c.io.out(2), 0)
//expect(c.io.out(3), 0)
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 0)
//expect(c.io.out(0), 1)
//expect(c.io.out(1), 1)  // check point. next: 2
//expect(c.io.out(2), 0)
//expect(c.io.out(3), 0)
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 0)
//poke(c.io.in(1), 1)
//expect(c.io.out(0), 1)
//expect(c.io.out(1), 1)
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 0)  // check point. next: 2
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 2)
//step(1)
//poke(c.io.in(0), 0)
//poke(c.io.in(1), 0)
//expect(c.io.out(0), 1)
//expect(c.io.out(1), 1)
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 0)
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 1)  // check point. next: 2
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 0)
//poke(c.io.in(1), 0)
//expect(c.io.out(0), 0)
//expect(c.io.out(1), 0)  // check point. next: 2
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 0)
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 1)
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 0)
//poke(c.io.in(1), 0)
//expect(c.io.out(0), 0)
//expect(c.io.out(1), 0)
//expect(c.io.out(2), 0)
//expect(c.io.out(3), 0)  // check point. next: 2
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 1)
//expect(c.io.stateOut, 2)
//step(1)
//poke(c.io.in(0), 0)
//poke(c.io.in(1), 0)
//expect(c.io.out(0), 0)
//expect(c.io.out(1), 0)
//expect(c.io.out(2), 0)
//expect(c.io.out(3), 0)  // check point. next: 2
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 0)


// ****** puncturing enabled ******
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 1)
//poke(c.io.stateIn, 0)
//expect(c.io.out(0), 0)
//expect(c.io.out(1), 0)
//expect(c.io.out(2), 0)
//expect(c.io.out(3), 0)
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 0)
//expect(c.io.out(0), 1)
//expect(c.io.out(1), 1) // check point. next: 1
//expect(c.io.out(2), 0)
//expect(c.io.out(3), 0)
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 0)
//poke(c.io.in(1), 1)
//expect(c.io.out(0), 1)
//expect(c.io.out(1), 1)
//expect(c.io.out(2), 1)  // check point. next: 1
//expect(c.io.out(3), 0)
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 0)
//expect(c.io.out(0), 1)
//expect(c.io.out(1), 1)
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 1)  // check point. next: 2
//expect(c.io.out(4), 0)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 2)
//step(1)
//poke(c.io.in(0), 0)
//poke(c.io.in(1), 1)
//expect(c.io.out(0), 1)
//expect(c.io.out(1), 1)
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 1)
//expect(c.io.out(4), 1)
//expect(c.io.out(5), 0)  // check point. next: 1
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 0)
//expect(c.io.out(0), 0)  // check point. next: 1
//expect(c.io.out(1), 1)
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 1)
//expect(c.io.out(4), 1)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 1)
//expect(c.io.out(0), 0)
//expect(c.io.out(1), 0)  // check point. next: 2
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 1)
//expect(c.io.out(4), 1)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 1)
//expect(c.io.out(0), 0)
//expect(c.io.out(1), 0)
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 1)  // check point. next: 1
//expect(c.io.out(4), 1)
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 1)
//expect(c.io.out(0), 0)
//expect(c.io.out(1), 0)
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 1)
//expect(c.io.out(4), 1)  // check point. next: 1
//expect(c.io.out(5), 0)
//expect(c.io.stateOut, 2)
//step(1)
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 1)
//expect(c.io.out(0), 0)
//expect(c.io.out(1), 0)
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 1)
//expect(c.io.out(4), 1)
//expect(c.io.out(5), 1)  // check point. next: 1
//expect(c.io.stateOut, 0)
//step(1)
//poke(c.io.in(0), 1)
//poke(c.io.in(1), 1)
//expect(c.io.out(0), 1)
//expect(c.io.out(1), 1)  // check point. next: 1
//expect(c.io.out(2), 1)
//expect(c.io.out(3), 1)
//expect(c.io.out(4), 1)
//expect(c.io.out(5), 1)
//expect(c.io.stateOut, 0)