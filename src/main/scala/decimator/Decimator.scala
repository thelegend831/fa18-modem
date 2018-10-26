package cfo

import chisel3._
import chisel3.experimental.FixedPoint
import chisel3.util._

import dsptools.numbers._
import freechips.rocketchip.diplomacy.LazyModule
import freechips.rocketchip.subsystem.BaseSubsystem

/**
 * Base class for CORDIC parameters
 *
 * These are type generic
 */
trait DecimatorParams[T <: Data] extends PacketBundleParams[T] {
  val protoIn: DspComplex[T]
  val protoout: DspComplex[T]
  val nDecimation: Int
}

class DecimateByN[T<:Data:Real](val params: DecimatorParams[T]) extends Module {
  val io = IO(new Bundle{
    val in = Flipped(Decoupled(SerialPacketBundle(params)))
    val out = Decoupled(SerialPacketBundle(params))
  })
  val count = Counter(params.nDecimation)
  io.in.ready := true.B

  when(io.in.valid){
    when(count.inc()){
      io.out.bits.iq := io.in.bits.iq
      io.out.valid := io.in.valid
    }
  }
}
