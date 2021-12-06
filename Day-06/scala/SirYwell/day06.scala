import scala.collection.mutable.Queue
import scala.io.Source.fromFile

@main def day06(filePath: String): Unit = {
  val input = fromFile(filePath).getLines().toList.head.split(",").map(_.toInt).toList

  val map = input.groupBy(_ % 7).view.mapValues(_.length.toLong)
  var state = (0 to 7).map(i => map.getOrElse(i, 0L))
  val toAdd = new Queue[Long]()
  (0 to 8).foreach(i => toAdd += 0)
  for day <- 0 until 256 do
    state = state.zipWithIndex.map { case (l, i) => l + (if day % 7 == i then toAdd.dequeue() else 0) }
    toAdd += state(day % 7)
    if day == 79 || day == 255 then println(s"Population after ${day + 1} days: ${state.sum + toAdd.sum}")
}
