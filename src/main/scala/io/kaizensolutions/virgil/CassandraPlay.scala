package io.kaizensolutions.virgil

import kyo.*
import com.datastax.oss.driver.api.core.CqlSession
import io.kaizensolutions.virgil.cql.*
import io.kaizensolutions.virgil.codecs.CqlRowDecoder

object CassandraPlay extends KyoApp:
  run:
    for
      executor   <- CQLExecutor.resource(CqlSession.builder().withKeyspace("virgil"))
      insertAlice = cql"INSERT INTO example (id, info) VALUES (1, 'Alice')".mutation
      insertBob   = cql"INSERT INTO example (id, info) VALUES (2, 'Bob')".mutation
      query       = cql"SELECT id, info FROM example".query[ExampleRow]
      _          <- executor.executeMutation(insertAlice)
      _          <- executor.executeMutation(insertBob)
      res        <- executor.execute[ExampleRow](query).runSeq
      (data, _)   = res
      _          <- IOs(println(data))
    yield ()

final case class ExampleRow(id: Int, info: String)
object ExampleRow:
  given CqlRowDecoder.Object[ExampleRow] = CqlRowDecoder.derive[ExampleRow]
