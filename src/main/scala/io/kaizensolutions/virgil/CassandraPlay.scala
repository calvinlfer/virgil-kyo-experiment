package io.kaizensolutions.virgil

import kyo.*
import com.datastax.oss.driver.api.core.CqlSession
import io.kaizensolutions.virgil.cql.*
import io.kaizensolutions.virgil.codecs.CqlRowDecoder

object CassandraPlay extends KyoApp:
  run:
    val program: Unit < (Envs[CQLExecutor] & Fibers) =
      val insertAlice = cql"INSERT INTO example (id, info) VALUES (1, 'Alice')".mutation
      val insertBob   = cql"INSERT INTO example (id, info) VALUES (2, 'Bob')".mutation
      val query       = cql"SELECT id, info FROM example".query[ExampleRow]
      for
        _        <- CQLExecutor.executeMutation(insertAlice)
        _        <- CQLExecutor.executeMutation(insertBob)
        res      <- CQLExecutor.execute(query).runSeq
        (data, _) = res
        _        <- IOs(println(data))
        page     <- CQLExecutor.executePage(query)
        _        <- IOs(println(page.data))
      yield ()

    for
      executor <- CQLExecutor.resource(CqlSession.builder().withKeyspace("virgil"))
      result   <- Envs.run(executor)(program)
    yield result

final case class ExampleRow(id: Int, info: String)
object ExampleRow:
  given CqlRowDecoder.Object[ExampleRow] = CqlRowDecoder.derive[ExampleRow]
