ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.4.1"

lazy val root = (project in file("."))
  .settings(
    name := "virgil-kyo",
    libraryDependencies ++= Seq(
      "io.getkyo"           %% "kyo-core"    % "0.0.0+1599-b0cbf0b9-SNAPSHOT",
      "io.kaizen-solutions" %% "virgil-core" % "1.0.5"
    )
  )
