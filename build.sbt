val scala3Version = "2.13.8"

scalacOptions ++= Seq("-language:postfixOps")

lazy val root = project
  .in(file("."))
  .settings(
    name := "udemy-scala-advanced",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
    )
  )
