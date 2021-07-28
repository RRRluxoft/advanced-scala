package lectures.part4implicits

/**
  * Created by Daniel.
  */
object OrganizingImplicits extends App {

  implicit def reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
//  implicit val normalOrdering: Ordering[Int] = Ordering.fromLessThan(_ < _)

  println(List(1, 4, 5, 3, 2).sorted(reverseOrdering))

  // scala.Predef

  /*
    Implicits (used as implicit parameters):
      - val/var
      - object
      - accessor methods = defs with no parentheses
   */

  // Exercise
  case class Person(name: String, age: Int)

  val persons = List(
    Person("Steve", 30),
    Person("Amy", 22),
    Person("John", 66)
  )

  object Person {
    implicit def personOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.name.compareTo(p2.name) < 0)
  }
  import Person._
  println(s"[Person ordering]: ${persons.sorted(personOrdering)}")

//  object Person {
//    implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
//  }
//  implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.age < b.age)
//  println(persons.sorted)

  /*
    Implicit scope
    - normal scope = LOCAL SCOPE
    - imported scope
    - companions of all types involved in the method signature
      - List
      - Ordering
      - all the types involved = A or any supertype
   */
  // def sorted[B >: A](implicit ord: Ordering[B]): List[B]

  object AlphabeticNameOrdering {
    implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  }

  object AgeOrdering {
    implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.age < b.age)
  }

  import AgeOrdering._
  println(persons.sorted(ageOrdering))

  /*
    Exercise.

    - totalPrice = most used (50%)
    - by unit count = 25%
    - by unit price = 25%

   */
  case class Purchase(nUnits: Int, unitPrice: Double)

  object Purchase {
    def totalPrice: Purchase => Double = (p: Purchase) => p.unitPrice * p.nUnits
    implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => totalPrice(a) < totalPrice(b))
  }

  object UnitCountOrdering {
    implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.nUnits < _.nUnits)
  }

  object UnitPriceOrdering {
    implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.unitPrice < _.unitPrice)
  }

}
