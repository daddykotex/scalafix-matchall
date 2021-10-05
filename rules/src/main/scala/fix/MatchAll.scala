package fix

import scalafix.v1._
import scala.meta._

case class AvoidWildcardMatch(pos: Position) extends Diagnostic {
  override def position: Position = pos
  override def message: String =
    s"""Avoid wildcard pattern matching. Prefer exhaustivity checking. To disable, annotate with @SuppressWarnings("MatchAll")"""
}


class MatchAll extends SyntacticRule("MatchAll") {

  def isInvalidVar(pat: Pat): Boolean = pat match {
    case Pat.Var(name) => !name.value.startsWith("_")
    case _ => false
  }

  def matches(c: Case): Boolean =
    c.cond.isEmpty && (c.pat.is[Pat.Wildcard] || isInvalidVar(c.pat))

  override def fix(implicit doc: SyntacticDocument): Patch = {
    doc.tree
      .collect {
        case c: Case if matches(c) => c
      }
      .map(c => Patch.lint(AvoidWildcardMatch(c.pat.pos)))
      .asPatch
  }

}
