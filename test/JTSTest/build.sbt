name := "JTSTest"

version := "0.1"

scalaVersion := "2.12.11"

libraryDependencies += "org.locationtech.jts" % "jts-core" % "1.16.1"
libraryDependencies += "org.wololo" % "jts2geojson" % "0.12.0"

resolvers += "Open Source Geospatial Foundation Repository" at "https://repo.osgeo.org/repository/release/"
libraryDependencies ++= Seq(
  "org.geotools" % "gt-main" % "23.1",
  "org.geotools" % "gt-referencing" % "23.1",
)
