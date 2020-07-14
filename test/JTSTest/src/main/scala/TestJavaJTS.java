import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.iso.text.WKTParser;
import org.geotools.geometry.jts.*;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.util.AffineTransformation;
import org.locationtech.jts.io.*;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.operation.IsSimpleOp;
import org.locationtech.jts.operation.buffer.BufferParameters;
import org.locationtech.jts.operation.valid.IsValidOp;
import org.locationtech.jts.precision.GeometryPrecisionReducer;
import org.locationtech.jts.simplify.TopologyPreservingSimplifier;
import org.locationtech.jts.algorithm.distance.DiscreteHausdorffDistance;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import org.wololo.jts2geojson.GeoJSONReader;
import org.locationtech.jts.operation.buffer.OffsetCurveBuilder;
import scala.Array;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.StrictMath.acos;

public class TestJavaJTS {
    public static void test_geotools_curve() throws ParseException {
        Geometry fdsgdg = new org.geotools.geometry.jts.WKTReader2().read("CURVEPOLYGON(CIRCULARSTRING(0 0, 4 0, 4 4, 0 4, 0 0))");
        String dgdfgdfh = new WKTWriter().write(fdsgdg);
        String fdsgf = "dfsdgf";
    }

    public static void test_transform() throws FactoryException, ParseException, TransformException {
        System.setProperty("org.geotools.referencing.forceXY", "true");
        Geometry geometry = new WKTReader().read("POINT (1 1)");
        System.out.println(geometry);
        Geometry res = org.geotools.geometry.jts.JTS.transform(geometry, org.geotools.referencing.CRS.findMathTransform(org.geotools.referencing.CRS.decode("EPSG:4326"), org.geotools.referencing.CRS.decode("EPSG:3857")));
    }

    public static void test_transform2() {
        Geometry geometry = null;
        Geometry res = null;
        try {
            System.setProperty("org.geotools.referencing.forceXY", "true");
            System.out.println(geometry);
            res = org.geotools.geometry.jts.JTS.transform(geometry, org.geotools.referencing.CRS.findMathTransform(org.geotools.referencing.CRS.decode("EPSG:4326"), org.geotools.referencing.CRS.decode("EPSG:3857")));
        } catch (FactoryException | TransformException e) {
            e.printStackTrace();
        }
    }

    public static void testGeoFromText() throws ParseException {
        LinearRing fdsgdg = (LinearRing)new org.geotools.geometry.jts.WKTReader2().read("LINEARRING (0 0, 1 0, 1 1, 0 1, 0 0)");
        String tdyhdfh = fdsgdg.getGeometryType();
        LineString aaaaa = new GeometryFactory().createLineString(fdsgdg.getCoordinates());
        LineString bbbbb = new org.locationtech.jts.geom.GeometryFactory().createLineString(new org.locationtech.jts.geom.GeometryFactory().createPolygon(fdsgdg.getCoordinates()).getExteriorRing().getCoordinates());
        String gfh = "dfgdfghd";
    }

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

    public static void test_collection() throws ParseException {
        Geometry point = new WKTReader().read("POINT (-1 -1)");
        Geometry lineString = new WKTReader().read("LINESTRING (0 0, 10 10, 20 20)");
        Geometry polygon = new WKTReader().read("POLYGON ((1 1,2 1,2 2,2 1,1 1))");
        Geometry multiPolygon = new WKTReader().read("MULTIPOLYGON ( ((0 0, 1 0, 1 1, 0 1, 0 0)) )");
        Geometry[] geometries = {point, lineString};
        ArrayList<Geometry> geometriesList = new ArrayList<Geometry>(Arrays.asList(geometries));
        GeometryCollection geometryCollection = new GeometryFactory().createGeometryCollection(geometries);
        Double gfgh = geometryCollection.getCoordinates()[0].x;
        String s = geometryCollection.getGeometryType();
        geometryCollection = new GeometryFactory().createGeometryCollection();
        Geometry g1 = new WKTReader().read("POLYGON ((1 1,2 1,2 2,1 2,1 1))");
        Geometry ggggggg = new WKTReader().read("POLYGON EMPTY");
        Geometry aaaaaaa = new WKTReader().read("POINT (-1 -1)");
        Geometry asdf = new WKTReader().read("MULTIPOLYGON (((0 0, 1 0, 1 1, 0 1, 0 0)))");
        Geometry g2 = new WKTReader().read("MULTIPOLYGON (((0 0, 0 1, 1 1, 1 0, 0 0)), ((10 10, 10 20, 20 20, 20 10, 10 10)))");
        Geometry g3 = g1.union(g2);
        String ssfdgfg = geometryCollection.getGeometryType();
        String asffdsgdfgfg = asdf.getGeometryType();
        Geometry dfdgdfghfgh = ggggggg.union(aaaaaaa);
        String dfgfgh = asdf.getGeometryType();

        MultiPolygon dfghdfgh = (MultiPolygon) new WKTReader().read("MULTIPOLYGON (((0 0, 10 0, 10 10, 0 10, 0 0)), ((11 11, 20 11, 20 20, 20 11, 11 11)))");
        Geometry gdfgdfh = dfghdfgh.getEnvelope();
        String fghg = "fsdgdfhdfh";
        double df = java.lang.Math.pow(10, 3);
        GeometryCollection dfgdfg = new org.locationtech.jts.geom.GeometryFactory().createGeometryCollection();
    }

    public static void testOthers() throws ParseException {
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

        Boolean isSimple = geometry != null && geometry.isSimple();

        String type = geometry.getGeometryType();

        int a = geometry.getNumPoints();

        Geometry empty_geo = new WKTReader().read("POLYGON EMPTY");

        Geometry asfd = empty_geo.getEnvelope();

        Geometry buffer = geometry.buffer(1);

        PrecisionModel sddf = new PrecisionModel(34);
        Geometry stedrytr = org.locationtech.jts.precision.GeometryPrecisionReducer.reduce(geometry, new org.locationtech.jts.geom.PrecisionModel(34));

        Geometry geometry1 = new WKTReader().read("POLYGON EMPTY");

        Geometry inter = new WKTReader().read("POINT (50 50)").intersection(new WKTReader().read("POLYGON ((0 0, 40 0, 40 40, 0 40, 0 0))"));

        Geometry makf = org.locationtech.jts.simplify.TopologyPreservingSimplifier.simplify(geometry, 1);

        Geometry fdgfh = geometry.convexHull();

        Double gdfhfgh = geometry.getLength();

        Double gfg = org.locationtech.jts.algorithm.distance.DiscreteHausdorffDistance.distance(geometry, geometry1);

        Boolean agfsdg = geometry.crosses(geometry1);

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
        double res = 6372797.560856 * (2.0 * java.lang.Math.asin(java.lang.Math.sqrt(latitudeH + tmp * lontitudeH)));

        if ((fromlat > 180) || (fromlat < -180) || (fromlon > 90) || (fromlon < -90) ||
                (tolat > 180) || (tolat < -180) || (tolon > 90) || (tolon < -90)) {

        }

        test_collection();
        Geometry fgfg = new org.locationtech.jts.geom.util.AffineTransformation().translate(-5, 24).transform(geometry);

        Geometry rotate1 = new WKTReader().read("LINESTRING (0 0,5 0)");

        Geometry dfgfhdfa = new org.locationtech.jts.geom.util.AffineTransformation().rotate(2 * acos(0.0), 1, 0).transform(rotate1);

        double scalaX = rotate1.getEnvelopeInternal().centre().x;
        double scalaY = rotate1.getEnvelopeInternal().centre().y;
        Geometry fdfgfhfjf = org.locationtech.jts.geom.util.AffineTransformation.scaleInstance(2, 2, scalaX, scalaY).transform(rotate1);

        System.out.println(dfgfhdfa.getCoordinates()[0]);

        geometry.symDifference(geometry);

        geometry.difference(geometry);

        geometry.union(geometry);

        geometry.disjoint(geometry);

        geometry.isEmpty();

        geometry.getBoundary();

        Geometry geometry2 = new org.locationtech.jts.io.WKTReader().read("POLYGON ((0 0, 1 0, 1 1, 0 1, 0 0))");
        Geometry polygon = geometry2.getGeometryType().equals("Polygon") ? geometry2 : new org.locationtech.jts.geom.GeometryFactory().createPolygon(geometry2.getCoordinates()).getExteriorRing();

        p.getExteriorRing();

        Geometry dfgfhfghj = new org.locationtech.jts.geom.util.AffineTransformation().scale(1, 0).transform(rotate1);

        Geometry fsvertdg = new org.locationtech.jts.geom.util.AffineTransformation(2, 2, 2, 2, 2, 2).transform(rotate1);
    }

    public static void testValid() throws ParseException {
        Geometry geometry = new WKTReader2().read("POLYGON ((0, 1 0, 1 1, 0 1, 0))");
        String s = geometry.toString();
        System.out.println(s);
    }

    public static void testEmpty() throws ParseException {
        Geometry geometry = new org.locationtech.jts.io.WKTReader().read("GEOMETRY EMPTY");
        System.out.println(geometry.toText());
    }

    public static void testCollection() throws ParseException {
        org.apache.spark.sql.catalyst.util.GenericArrayData aaa = new org.apache.spark.sql.catalyst.util.GenericArrayData(new java.util.ArrayList());
        final int project_numElements_0 = aaa.numElements();
        System.out.println(project_numElements_0);
        Geometry geometry = new WKTReader2().read("CURVEPOLYGON(CIRCULARSTRING(0 0, 4 0, 4 4, 0 4, 0 0),(1 1, 3 3, 3 1, 1 1))");
//        Geometry geometry = new WKTReader2().read("MULTISURFACE(CURVEPOLYGON(CIRCULARSTRING(0 0, 4 0, 4 4, 0 4, 0 0),(1 1, 3 3, 3 1, 1 1)))");
        System.out.println(geometry.toText());
        System.out.println(geometry.getGeometryType());
        System.out.println(geometry.getLength());
    }

    public static void TestExteriorring() throws ParseException {
        Geometry geometry = new WKTReader2().read("POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))");
        org.locationtech.jts.geom.Polygon p = (org.locationtech.jts.geom.Polygon)geometry;
        Geometry rst = p.getExteriorRing();
        System.out.println(rst.toText());
    }

    public static void main(String[] args) throws ParseException {
        // testGeoFromText();
        // testValid();
        // testEmpty();
        // testCollection();
        TestExteriorring();
    }
}
