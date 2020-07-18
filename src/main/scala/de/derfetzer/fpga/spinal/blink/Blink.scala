package de.derfetzer.fpga.spinal.blink

import spinal.core._
import spinal.core.sim._

class Blink extends Component {
  val io = new Bundle {
    val led  = out Bool
  }
  val counter = Reg(UInt(21 bits)) init(0) simPublic()

  counter := counter + 1

  io.led := counter(20)
}
