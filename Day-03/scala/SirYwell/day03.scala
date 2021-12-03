import scala.io.Source.fromFile

@main def day03(filePath: String) = {
  val input = fromFile(filePath).getLines().toList
  val digits = input.map(_.toList.map(_ - '0'))
  val countToBinary = (list: List[Int]) => list.map(i => if i * 2 >= input.length then 1 else 0)
  val binaryToTuple = (list: List[Int]) => list.reverse.zipWithIndex.foldLeft((0, 0)) {
    case ((g, e), (v, i)) => (g | (v << i), e | (((v - 1) & 1) << i))
  }
  val counts = countOnes(digits)
  val (gamma, epsilon) = (countToBinary andThen binaryToTuple) (counts)
  println(gamma * epsilon)
  val listToInt = binaryToTuple andThen (_ (0))
  val reduce = reduceByCount(digits)
  println(listToInt(reduce(1)) * listToInt(reduce(0)))
}

def countOnes(list: List[List[Int]]): List[Int] = list.reduce((l, r) => l.zip(r).map { case (a, b) => a + b })

def reduceByCount(inputList: List[List[Int]])(toKeep: Int): List[Int] =
  var list = inputList
  val length = list(0).length
  for (i <- 0 until length if list.length > 1) {
    val c = countOnes(list)(i) * 2
    list = list.filter(e => (e(i) == toKeep) == c >= list.length)
  }
  list(0)
