ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.4.1"

lazy val root = (project in file("."))
  .settings(
    name := "virgil-kyo",
    libraryDependencies ++= Seq(
      "io.getkyo"           %% "kyo-core"    % "0.9.3+93-94dfc223-SNAPSHOT",
      "io.kaizen-solutions" %% "virgil-core" % "1.0.5"
    )
  )
