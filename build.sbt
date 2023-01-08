lazy val root = (project in file(".")).settings(
  organization                 := "fr.beglarian",
  scalaVersion                 := "3.2.1",
  name                         := "beglarian-amocrm-model",
  version                      := "0.0.3",
  libraryDependencies ++= Seq(
    ("io.spray" %% "spray-json" % "1.3.6" % "compile").cross(CrossVersion.for3Use2_13)
  ),
  Compile / mappings           := Seq(),
  packageDoc / mappings        := Seq(),
  packageDoc / publishArtifact := false,
  githubOwner                  := "greenhost87",
  githubRepository             := "beglarian-amocrm-model"
)
