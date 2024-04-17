lazy val root = (project in file(".")).settings(
  organization                 := "fr.beglarian",
  scalaVersion                 := "3.4.0",
  version                      := "0.0.6",
  libraryDependencies ++= Seq(
    "io.spray" %% "spray-json" % "1.3.6" % "compile"
  ),
  name                         := "beglarian-amocrm-model",
  Compile / mappings           := Seq(),
  packageDoc / mappings        := Seq(),
  packageDoc / publishArtifact := false,
  githubOwner                  := "greenhost87",
  githubRepository             := "beglarian-amocrm-model",
  scalacOptions                := Seq(
    "-deprecation",
    "-feature",
    "-Xfatal-warnings",
    "-Xmax-inlines:64",
    "-Wunused:imports"
  )
)
