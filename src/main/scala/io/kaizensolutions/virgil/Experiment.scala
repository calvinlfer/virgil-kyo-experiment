package io.kaizensolutions.virgil

import kyo.*

object Experiment extends KyoApp:
  val channel: Channel[Chunk[String] | Stream.Done] < IOs = Channels.init[Chunk[String] | Stream.Done](1024, Access.Spsc)

  run:
    channel.map { channel =>
      for
        _   <- channel.put(Chunks.init("Hello, World!"))
        _   <- channel.put(Chunks.init("Bye bye world!"))
        _   <- channel.put(Stream.Done)
        s    = Streams.initChannel(channel)
        out <- s.runSeq
        _   <- IOs(println(out))
      yield ()
    }
