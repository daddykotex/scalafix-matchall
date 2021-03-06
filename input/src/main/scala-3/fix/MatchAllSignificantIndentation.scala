/*
rule = MatchAll
*/
package fix

object MatchAllSignificantIndentation:
  def someFunction(): Unit = "some string" match
    case _ => println("someFunction") // assert: MatchAll


  val pf: PartialFunction[String, Unit] =
    case _ => println("pf") /* assert: MatchAll
         ^
Avoid wildcard pattern matching. Prefer exhaustivity checking. To disable, annotate with @SuppressWarnings(Array("MatchAll"))
*/


  @SuppressWarnings(Array("MatchAll"))
  val f: Function1[String, Unit] =
    case _ => println("f")

