import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.operation.IsSimpleOp;
import org.locationtech.jts.operation.buffer.BufferParameters;
import org.locationtech.jts.operation.valid.IsValidOp;
import org.locationtech.jts.precision.GeometryPrecisionReducer;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.simplify.TopologyPreservingSimplifier;
import org.locationtech.jts.io.WKBWriter;
import org.locationtech.jts.algorithm.distance.DiscreteHausdorffDistance;
import org.locationtech.jts.io.WKBReader;
import org.wololo.geojson.Feature;
import org.wololo.geojson.GeoJSONFactory;
import org.wololo.jts2geojson.GeoJSONReader;
import org.wololo.jts2geojson.GeoJSONWriter;
import org.locationtech.jts.operation.buffer.OffsetCurveBuilder;

//import org.geotools.geometry.jts.JTS;
//import org.geotools.referencing.CRS;

public class TestJavaJTS {
    public static void test_curve() throws ParseException {
        Geometry geometry = new WKTReader().read("CURVEPOLYGON(CIRCULARSTRING(0 0, 4 0, 4 4, 0 4, 0 0))");

        BufferParameters bufferParameters = new BufferParameters();
        bufferParameters.setEndCapStyle(BufferParameters.CAP_FLAT);
        bufferParameters.setJoinStyle(BufferParameters.JOIN_ROUND);
        bufferParameters.setQuadrantSegments(18);

        OffsetCurveBuilder curveBuilder = new OffsetCurveBuilder(new PrecisionModel(), bufferParameters);
        Coordinate[] dfgfdfgh = geometry.getCoordinates();
        Coordinate[] fdsfgdfg = curveBuilder.getLineCurve(dfgfdfgh, 1);
        Geometry dfgdfg = new GeometryFactory().createPolygon(fdsfgdfg);
    }

    public static void main(String[] args) throws ParseException {
        Coordinate[] coordinates = new Coordinate[5];
        coordinates[0] = new Coordinate(2, 2);
        coordinates[1] = new Coordinate(1, 1);
        coordinates[2] = new Coordinate(1, 1);
        coordinates[3] = new Coordinate(1, 1);
        coordinates[4] = coordinates[0];

        Polygon p = new GeometryFactory().createPolygon(coordinates);
        p.getArea();

        Geometry geometry;
        String s = "{\"type\":\"Point\",\"coordinates\":[1.0,1.0]}";
        geometry = new GeoJSONReader().read(s);

        String json = new org.wololo.jts2geojson.GeoJSONWriter().write(geometry).toString();

        geometry.getGeometryType();

        Boolean isvalidop = geometry != null && new IsValidOp(geometry).isValid();

        System.out.println(isvalidop);

        Boolean isSimple = geometry != null && geometry.isSimple();

        String type = geometry.getGeometryType();

        int a = geometry.getNumPoints();

        Geometry empty_geo = new WKTReader().read("POLYGON EMPTY");

        Geometry asfd = empty_geo.getEnvelope();

        Geometry buffer = geometry.buffer(1);

        PrecisionModel sddf = new PrecisionModel(34);
        Geometry stedrytr = org.locationtech.jts.precision.GeometryPrecisionReducer.reduce(geometry, new org.locationtech.jts.geom.PrecisionModel(34));

        Geometry geometry1 =  new WKTReader().read("POLYGON EMPTY");;

        Geometry inter = new WKTReader().read("POINT (50 50)").intersection(new WKTReader().read("POLYGON ((0 0, 40 0, 40 40, 0 40, 0 0))"));

        Geometry makf = org.locationtech.jts.simplify.TopologyPreservingSimplifier.simplify(geometry, 1);

        Geometry fdgfh = geometry.convexHull();

        Double gdfhfgh = geometry.getLength();

        Double gfg = org.locationtech.jts.algorithm.distance.DiscreteHausdorffDistance.distance(geometry, geometry1);

        Boolean agfsdg = geometry.crosses(geometry1);

//        Geometry gdfgdfhfgh = JTS.Trans



        Geometry point = new WKTReader().read("POINT (1 1)");

        String fssdfg = point.getGeometryType();

        if (!geometry.getGeometryType().equals("Point") || !point.getGeometryType().equals("Point")) {
            a = 1;
        }
        double fromlat = point.getInteriorPoint().getX();
        double tolat = point.getInteriorPoint().getY();
        double fromlon = point.getInteriorPoint().getX();
        double tolon = point.getInteriorPoint().getX();
double latitudeArc = (fromlat - tolat) * 0.017453292519943295769236907684886;
double longitudeArc = (fromlon - tolon) * 0.017453292519943295769236907684886;
double latitudeH = java.lang.Math.sin(latitudeArc * 0.5);
latitudeH *= latitudeH;
double lontitudeH = java.lang.Math.sin(longitudeArc * 0.5);
lontitudeH *= lontitudeH;
double tmp = java.lang.Math.cos(fromlat * 0.017453292519943295769236907684886) *
        java.lang.Math.cos(tolat * 0.017453292519943295769236907684886);
double res =  6372797.560856 * (2.0 * java.lang.Math.asin(java.lang.Math.sqrt(latitudeH + tmp * lontitudeH)));

if ((fromlat > 180) || (fromlat < -180) || (fromlon > 90) || (fromlon < -90) ||
        (tolat > 180) || (tolat < -180) || (tolon > 90) || (tolon < -90)) {

}


        test_curve();

        System.out.println("dsfdsgdfgdfghdf");
    }
}
