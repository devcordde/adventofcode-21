import scala.annotation.tailrec
import scala.io.Source.fromFile

@main def day16(filePath: String): Unit = {
  val input = fromFile(filePath).getLines().next()
  val bits = input.map(c => Character.digit(c, 16))
    .flatMap(i => "%4s".formatted(i.toBinaryString).replace(' ', '0').map(_ - '0')).toList
  val packet = parsePacket(bits)
  println(s"version sum: ${sum(packet._1)}")
  println(s"eval result: ${evaluate(packet._1)}")
}

def sum(packet: Packet): Int = packet match {
  case Literal(version, _) => version
  case PacketContainer(version, _, packets) => version + packets.map(sum).sum
}
def evaluate(packet: Packet): Long = packet match {
  case PacketContainer(_, 0, packets) => packets.map(evaluate).sum
  case PacketContainer(_, 1, packets) => packets.map(evaluate).product
  case PacketContainer(_, 2, packets) => packets.map(evaluate).min
  case PacketContainer(_, 3, packets) => packets.map(evaluate).max
  case Literal(_, v) => v
  case PacketContainer(_, 5, List(l, r)) => if evaluate(l) > evaluate(r) then 1 else 0
  case PacketContainer(_, 6, List(l, r)) => if evaluate(l) < evaluate(r) then 1 else 0
  case PacketContainer(_, 7, List(l, r)) => if evaluate(l) == evaluate(r) then 1 else 0
}

def parsePacket(bits: List[Int]): (Packet, List[Int]) = {
  val (version, (typeId, rest)) = parseVersion(bits) map (p => (p._1, parseTypeId(p._2)))
  if typeId == 4 then parseLiteral(list = rest) map (p => (Literal(version, p._1), p._2))
  else
    val (packets, r) =
      if rest.head == 0 then parseBits(rest.tail, 15) map parsePacketsByLength
      else parseBits(rest.tail, 11) map parsePacketsByAmount
    (PacketContainer(version, typeId, packets), r)
}
def parseBits(value: List[Int], n: Int): (Int, List[Int]) =
  if n == 1 then (value.head, value.tail)
  else parseBits(value.tail, n - 1) map (p => ((value.head << (n - 1)) | p._1, p._2))

def parseVersion(list: List[Int]): (Int, List[Int]) = parseBits(list, 3)
def parseTypeId(list: List[Int]): (Int, List[Int]) = parseBits(list, 3)
@tailrec
def parseLiteral(value: Long = 0L, list: List[Int]): (Long, List[Int]) = list.head match {
  case 0 => parseBits(list.tail, 4) map (p => ((value << 4) | p._1, p._2))
  case 1 =>
    val (v, r) = parseBits(list.tail, 4)
    parseLiteral((value << 4) | v, r)
}
def parsePacketsByLength(length: Int, list: List[Int]): (List[Packet], List[Int]) = {
  val (packets, remaining) = list.splitAt(length)
  var p = parsePacket(packets)
  var l = List(p._1)
  while p._2.nonEmpty do {
    p = parsePacket(p._2)
    l = l.appended(p._1)
  }
  (l, remaining)
}
def parsePacketsByAmount(count: Int, list: List[Int]): (List[Packet], List[Int]) = {
  val (packet, remaining) = parsePacket(list)
  if count == 1 then (List(packet), remaining)
  else parsePacketsByAmount(count - 1, remaining) map (e => (packet :: e._1, e._2))
}

trait Packet:
  def version: Int

case class PacketContainer(version: Int, typeId: Int, packets: List[Packet]) extends Packet

case class Literal(version: Int, literalValue: Long) extends Packet

extension[I <: (?, ?)] (t: I)
  def map[O](f: I => O): O = f(t)
