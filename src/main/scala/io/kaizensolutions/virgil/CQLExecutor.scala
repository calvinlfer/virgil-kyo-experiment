package io.kaizensolutions.virgl

import io.kaizensolutions.virgil.CQL
import kyo.*

trait CQLExecutor:
  def execute[A](in: CQL[A]): Unit = ()
