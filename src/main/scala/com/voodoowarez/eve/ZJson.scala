class ZJson[T](klass: Class[T]) extends(Seq[Seq[Byte]] => Iterator[T]) {
	val mapper = ObjectMapper()
	val inflater = Inflater()
	val buff = Array.fill[Byte](262144)(0)

	def apply(x: Seq[Seq[Byte]]) : Iterator[T] = {
		x.map(ent => {
			inflater.setInput(ent.toArray)
			val len = inflater.inflate(buff)
			// http://fasterxml.github.io/jackson-databind/javadoc/2.0.0/com/fasterxml/jackson/databind/ObjectMapper.html#readValue(byte[], java.lang.Class)
			return mapper.readValue(buff, 0, len, klass)
		}).iterator
	}
}
