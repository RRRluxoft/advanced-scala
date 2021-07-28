package lectures.part4implicits

import scala.language.implicitConversions

/**
  * Created by Daniel.
  */
object ImplicitsIntro extends App {

  val pair = "Daniel" -> "555"
  val intPair = 1 -> 2

  case class Person(name: String) {
    def greet = s"Hi, my name is $name!"
  }

  object Person {
    implicit def fromStringToPerson(str: String): Person = Person(str)
  }

  import Person._
  println(("Peter").greet) // println(fromStringToPerson("Peter").greet)

  class A {
    def greet: Int = 2
  }

  object A {
    implicit def fromStringToA(str: String): A = new A
  }
  // implicit parameters
  def increment(x: Int)(implicit amount: Int) = x + amount
  implicit val defaultAmount = 10

  increment(2)(7)
  // NOT default args

}
