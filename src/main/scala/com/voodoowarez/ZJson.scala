package com.voodoowarez

import java.util.zip.Inflater
import com.fasterxml.jackson.databind.ObjectMapper

class ZJson[T](klass: Class[T]) extends(Seq[Seq[Byte]] => Iterator[T]) {
	val mapper = new ObjectMapper()
	val inflater = new Inflater()
	val buff = Array.fill[Byte](262144)(0)

	def apply(x: Seq[Seq[Byte]]) : Iterator[T] = (
		x.map(ent => {
			inflater.setInput(ent.toArray)
			val len = inflater.inflate(buff)
			// http://fasterxml.github.io/jackson-databind/javadoc/2.0.0/com/fasterxml/jackson/databind/ObjectMapper.html#readValue(byte[], java.lang.Class)
			val v : T = mapper.readValue(buff, 0, len, klass)
			return v
		}).iterator
	)
}
