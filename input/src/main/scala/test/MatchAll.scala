/*
rule = MatchAll
*/
package test

object MatchAll {
  def someFunction(): Unit = "some string" match {
    case _ => println("someFunction") // assert: MatchAll
  }

  val pf: PartialFunction[String, Unit] = {
    case _ => println("pf") /* assert: MatchAll
         ^
Avoid wildcard pattern matching. Prefer exhaustivity checking. To disable, annotate with @SuppressWarnings("MatchAll")
*/
  }

  @SuppressWarnings(Array("MatchAll"))
  val f: Function1[String, Unit] = {
    case _ => println("f")
  }

  val someCond: Boolean = ???

  //ignored because of guards
  def withGuards(): Unit = "some string" match {
    case _ if someCond => println("yeah")
    case _ if !someCond => println("yeah")
  }

  def withOneGuard(): Unit = "some string" match {
    case _ if someCond => println("yeah")
    case _ => println("yeah") // assert: MatchAll
  }

  def catchAllVariable(): Unit = "some string" match {
    case SomeExtractor(s) => println(s)
    case catchAll => println(catchAll) // assert: MatchAll
  }

  def catchAllIgnore(): Unit = "some string" match {
    case SomeExtractor(s) => println(s)
    case _ignore => println(_ignore)
  }
}

private object SomeExtractor {
  def unapply(s: String): Option[String] = Some(s)
}
