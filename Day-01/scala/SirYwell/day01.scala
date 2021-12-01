import scala.io.Source.fromFile
// scala day01 <path-to-file>
@main def day01(filePath: String): Unit = {
  val list = fromFile(filePath).getLines().map(_.toInt).toList
  val printCount = (iterator: Iterator[Int]) => println(
    iterator
      .sliding(2)
      .count(s => s.head < s.tail.head)
  )
  printCount(list.iterator)
  printCount(list
    .sliding(3)
    .map(_.sum)
  )
}
