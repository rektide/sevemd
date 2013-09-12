package com.voodooware.eve

import com.google.common.io.Files

import com.jcraft.jzlib._

import org.apache.hadoop.mapreduce.Job

import parquet.avro.{AvroParquetOutputFormat, AvroWriteSupport, AvroReadSupport}
import parquet.filter.{RecordFilter, UnboundRecordFilter}
import parquet.hadoop.{ParquetOutputFormat, ParquetInputFormat}
import parquet.column.ColumnReader
import parquet.filter.ColumnRecordFilter._
import parquet.filter.ColumnPredicates._

import spark.SparkContext
import spark.SparkContext._

import java.lang.Iterable
import java.io.File

object SEveMd {

	def main(args: Array[String]) {
		val ssc = new StreamingContext("local", "SEveMd", Seconds(30))
		val emdrIn = ssc.zeroMQStream("tcp://relay-us-east-1.eve-emdr.com:8050", Subscribe(""), val emdrIn = val
		val deser = emdrIn.flatMap(ZJson(MarketRecord))
		deser.print()
		ssc.start()
	}
}
