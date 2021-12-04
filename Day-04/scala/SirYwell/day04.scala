import scala.io.Source.fromFile

@main def day04(filePath: String): Unit = {
  val input = fromFile(filePath).getLines().toList
  val numbers = input.head.split(",").map(_.toInt)
  val boardsRaw = for start <- Range(2, input.length, 6) yield {
    for i <- start until (start + 5) yield input(i).split(" ").filterNot(_.isEmpty).map(_.toInt).toList
  }.toList

  type BoardList = List[List[List[(Int, Boolean)]]]
  val filterLinewise = (_: BoardList).filter(!_.forall(!_.forall(_ (1))))
  val sumWinner = ((b: BoardList, f: (Int => Unit)) =>
    if b.nonEmpty then f(b.head.flatten.filterNot(_ (1)).map(_ (0)).sum)).curried

  var state = boardsRaw.toList.map(_.map(_.map(i => (i, false))))
  for v <- numbers do {
    val shouldPrint = state.size == boardsRaw.size || state.size == 1 // first winner (part 1) || last winner (part 2)
    state = state.map(_.map(_.map(p => if p(0) == v then (p(0), true) else p)))
    val boardsWithWinningRow = filterLinewise(state)
    val rowWinner = sumWinner(boardsWithWinningRow)
    val boardsWithWinningColumn = filterLinewise(state.map(_.transpose))
    val columnWinner = sumWinner(boardsWithWinningColumn)
    if shouldPrint then
      rowWinner(a =>    println("Found winning board    row: %d".format(v * a)))
      columnWinner(a => println("Found winning board column: %d".format(v * a)))
    state = state.filterNot(b => boardsWithWinningRow.contains(b) || boardsWithWinningColumn.contains(b.transpose))
  }
}
