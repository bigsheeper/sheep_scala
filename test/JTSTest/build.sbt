import sbt.Keys.resolvers
import sbt.Keys.artifact

name := "JTSTest"

version := "0.1"

scalaVersion := "2.12.11"

libraryDependencies += "org.locationtech.jts" % "jts-core" % "1.16.1"
libraryDependencies += "org.wololo" % "jts2geojson" % "0.12.0"

resolvers ++= Seq(
  "GeoSolutions" at "http://maven.geo-solutions.it/",
  "GeoTools" at "http://download.osgeo.org/webdav/geotools/"
)
libraryDependencies ++= Seq(
//  "org.geotools" % "gt-swing" % "16.1"
    "org.geotools" % "gt-main" % "17.0",
    "org.geotools" % "gt-api" % "17.0"
)

//resolvers +=
//  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
//resolvers += "mvnrepository" at "http://mvnrepository.com/artifact/"
//libraryDependencies += "org.geotools" % "geotools" % "16.1" pomOnly()

//// https://mvnrepository.com/artifact/org.geotools/geotools
//resolvers += "mvnrepository" at "http://mvnrepository.com/artifact/"
////resolvers += "central" at "http://repo1.maven.org/maven2/"
//resolvers +=
//libraryDependencies += "org.geotools" % "geotools" % "21.0"

//resolvers ++= Seq(
//  ("geosolutions" at "http://maven.geo-solutions.it/"),
//  "Osgeo Repo" at "https://nexus.osgeo.org/repository/Geoserver-releases/",
//  ("geomajas" at "http://maven.geomajas.org")
//)
//libraryDependencies ++= Seq(
//  "org.geotools" % "gt-swing" % "21.0"
//)

//resolvers += DefaultMavenRepository
//resolvers += JavaNet2Repository
//
////resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
//resolvers += "Open Source Geospatial Foundation Repository" at "https://download.osgeo.org/webdav/geotools/"
//allDependencies ++= Seq(
//  "org.geotools" % "gt-main" % "17.0",
//  "org.geotools" % "gt-api" % "17.0"
//)
//
//lazy val geotools = "17.0"
//
////resolvers += "osgeo" at "http://download.osgeo.org/webdav/geotools"
////resolvers += "boundless" at "http://repo.boundlessgeo.com/main"
////resolvers += "imageio" at "http://maven.geo-solutions.it"
////resolvers += Resolver.mavenLocal
//
//allDependencies ++= Seq(
//  "org.geotools" % "gt-main" % geotools,
//  "org.geotools" % "gt-arcgrid" % geotools,
//  "org.geotools" % "gt-process-raster" % geotools)
//  .map(_.excludeAll(
//    ExclusionRule("com.vividsolutions", "jts")
//  ))
