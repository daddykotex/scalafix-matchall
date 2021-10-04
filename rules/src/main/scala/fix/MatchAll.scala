package fix

import scalafix.v1._
import scala.meta._

case class AvoidWildcardMatch(pos: Position) extends Diagnostic {
  override def position: Position = pos
  override def message: String =
    s"""Avoid wildcard pattern matching. Prefer exhaustivity checking. To disable, annotate with @SuppressWarnings("MatchAll")"""
}


class MatchAll extends SyntacticRule("MatchAll") {
  override def fix(implicit doc: SyntacticDocument): Patch = {
    doc.tree
      .collect {
        case c: Case if c.pat.is[Pat.Wildcard] => c
      }
      .map(c => Patch.lint(AvoidWildcardMatch(c.pat.pos)))
      .asPatch
  }

}
