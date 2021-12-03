import scala.io.Source
import scala.io.Source.fromFile

@main def day02(filePath: String) = {
  val input = fromFile(filePath).getLines()
  val mapToVec = (a: Array[String]) => a.apply(0) match {
    case "forward" => (a.apply(1).toInt, 0)
    case "down" => (0, a.apply(1).toInt)
    case "up" => (0, -a.apply(1).toInt)
  }
  val vecs = input.map(_.split(" "))
    .map(mapToVec).toList
  val (x1, y1) = vecs.reduce { case ((xo, yo), (xn, yn)) => (xo + xn, yo + yn) }
  println(x1 * y1)
  val (x2, y2, _) = vecs.foldLeft((0, 0, 0)) { case ((x, y, aim), (f, a)) => (x + f, y + f * aim, aim + a) }
  println(x2 * y2)
}
