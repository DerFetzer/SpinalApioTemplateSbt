package de.derfetzer.fpga.spinal.blink

import spinal.core._

class TopLevel extends Component {
  val io = new Bundle {
    val CLK = in Bool
    val LEDR_N = out Bool
  }

  // Create own clock-domain for the
  // 12 Mhz Clock from the FTDI chip
  val extClockDomain = ClockDomain(
    clock = io.CLK,
    config = ClockDomainConfig(
      clockEdge = RISING,
      resetKind = BOOT,
      resetActiveLevel = LOW
    )
  )

  val area = new ClockingArea(extClockDomain) {
    val blink = new Blink

    io.LEDR_N := blink.io.led
  }
}

//Generate the MyTopLevel's Verilog
object TopLevel {
  def main(args: Array[String]) {
    SpinalVerilog(new TopLevel)
  }
}