package com.voodooware.eve

import akka.zeromq.Subscribe

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds

import com.voodoowarez.ZJson

object SEveMd {

	def main(args: Array[String]) {
		val ssc = new StreamingContext("local", "SEveMd", Seconds(30))
		val emdrIn = ssc.zeroMQStream("tcp://relay-us-east-1.eve-emdr.com:8050", Subscribe(""), new ZJson[MarketRecord](classOf[MarketRecord]))
		emdrIn.print()
		ssc.start()
	}
}
