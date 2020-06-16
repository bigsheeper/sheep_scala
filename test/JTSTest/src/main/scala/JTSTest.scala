import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.locationtech.jts.io.WKTReader
import org.locationtech.jts.io.WKBWriter
import org.locationtech.jts.io.WKBReader


object JTSTest {
  def main(args:Array[String]):Unit={
    val g1:Geometry = new WKTReader().read("LINESTRING (0 0, 10 10, 20 20)")
    println(s"geometry g1 : ${g1}")

    val coordinates = Array[Coordinate](
    new Coordinate(0, 0),
    new Coordinate(10, 10),
    new Coordinate(20, 20))

    val g2 = new GeometryFactory().createLineString(coordinates)
    println(s"geometry g2 : ${g2}")

    val g3 = g1.intersection(g2)
    println(s"g1 intersection g2 : ${g3}")

    val point:Point = new GeometryFactory().createPoint(new Coordinate(1,1))
    println(s"point geometry: ${point}")

    println(s"point within g1 ${g1.within(point)}")

    val wkb:Array[Byte] = new WKBWriter().write(point)
    println(wkb)

    val g4 = new WKBReader().read(wkb)
    println(s"geometry from wkb : ${g4}")

    val point1 = new GeometryFactory().createPoint(new Coordinate(1, 1))
  }

}
