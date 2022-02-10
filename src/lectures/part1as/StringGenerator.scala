package lectures.part1as

import scala.annotation.tailrec

object StringGenerator extends App {

//  def generator = (s: String) => Seq[String]

  val input = "Scala"

  (for {
    i <- 0 to input.length - 1
    j <- 1 to input.length - i
    sub = input.slice(i, i + j)
  } yield sub) //.foreach(println)

  def size[T](l: List[T], acc: Int = 0): Int =
    if (l.isEmpty) acc
    else size(l.tail, acc + 1)

  @tailrec
  def concat(l1: List[Int], l2: List[Int], acc: List[Int] = Nil): List[Int] =
    if (l1.isEmpty) l2
    else if (l2.isEmpty) l1
    else concat(l1.tail, l2.tail, l1.head :: l2.head :: acc)

  @tailrec
  def reverse[A](l: List[A], acc: List[A] = Nil): List[A] = l match {
    case Nil      => acc
    case x :: Nil => x +: acc
    case x :: xs  => reverse(xs, x +: acc)
  }

  def filter(l: List[Int], f: Int => Boolean, acc: List[Int] = Nil): List[Int] =
    if (l.nonEmpty) {
      filter(l.tail, f, if (f(l.head)) l.head +: acc else acc)
    } else acc.reverse

  def predicate(x: Int): Boolean = x % 2 == 0
  println(filter((1 to 10).toList, predicate))
  // List(2, 4, 6, 8, 10)

  val f: (Int, Int) => Int = (a, b) => a + b
  f(3, _)

  /*
  @tailrec
  def takeWhile[A](l: List[A], acc: List[A] = Nil)(f: A => Boolean): List[A] = l match {
    case Nil             => acc.reverse
    case x :: xs if f(x) => takeWhile(xs, x :: acc)(f)
  }

  println(takeWhile(List(1, 2, 3, 4))(_ % 2 == 0))*/

  def map[A, B](l: List[A])(f: A => B): List[B] =
    l.foldRight(List.empty[B])((x, l) => f(x) :: l)

  def filter[A](l: List[A])(f: A => Boolean): List[A] =
    l.foldRight(List.empty[A])((x, l) => if (f(x)) x :: l else l)

  def size[A](l: List[A]): Int = l.foldLeft(0)((b, _) => b + 1)

  @tailrec
  def mapMatch[A, B](l: List[A], acc: List[B] = Nil)(f: A => B): List[B] = l match {
    case Nil     => acc.reverse
    case x :: xs => mapMatch(xs, f(x) :: acc)(f)
  }

  def filterMatch[A](l: List[A], acc: List[A] = List.empty[A])(f: A => Boolean): List[A] = l match {
    case Nil              => acc.reverse
    case ::(h, t) if f(h) => filterMatch(t, h :: acc)(f)
    case ::(_, t)         => filterMatch(t, acc)(f)
  }

  println(mapMatch(List(1, 2, 3))(identity))

}
