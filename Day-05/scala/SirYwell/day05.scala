import scala.io.Source.fromFile

@main def day05(filePath: String): Unit = {
  val input = fromFile(filePath).getLines()
    .map(_.split(" -> "))
    .map(a => a.map(_.split(",")).map(b => (b(0).toInt, b(1).toInt)))
    .map(a => (a(0), a(1)))
    .toList

  val step = (a: Int, b: Int) => if a == b then 0 else if a < b then 1 else -1
  val linesToCount = (lines: List[((Int, Int), (Int, Int))]) => lines.flatMap { case ((x1, y1), (x2, y2)) =>
    val xStep = step(x1, x2)
    val yStep = step(y1, y2)
    for s <- 0 to Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)) yield (x1 + s * xStep, y1 + s * yStep)
  }.groupBy(identity).mapValues(_.size).count(_ (1) >= 2)

  println(s"Part 1: ${linesToCount(input.filter { case ((x1, y1), (x2, y2)) => x1 == x2 || y1 == y2 })}")
  println(s"Part 2: ${linesToCount(input)}")
}
