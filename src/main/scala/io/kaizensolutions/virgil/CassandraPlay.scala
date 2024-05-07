package io.kaizensolutions.virgil

import kyo.*
import com.datastax.oss.driver.api.core.CqlSession
import io.kaizensolutions.virgil.cql.*
import io.kaizensolutions.virgil.codecs.CqlRowDecoder

object CassandraPlay extends KyoApp:
  run:
    for
      executor <- CQLExecutor.resource(CqlSession.builder().withKeyspace("virgil"))
      insert    = cql"INSERT INTO example (id, info) VALUES (2, 'Bob')".mutation
      _        <- executor.executeMutation(insert)
      _        <- executor.executeMutation(insert)
      query     = cql"SELECT id, info FROM example".query[ExampleRow]
      res      <- executor.execute[ExampleRow](query).runSeq
      _        <- IOs(println(res))
    yield ()
end CassandraPlay

final case class ExampleRow(id: Int, info: String)
object ExampleRow:
  given CqlRowDecoder.Object[ExampleRow] = CqlRowDecoder.derive[ExampleRow]
