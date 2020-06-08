// before geometryFromArcternExpr
org.locationtech.jts.geom.Geometry project_value_2_geo = null;
ArrayData project_value_2 = null;
UTF8String rdd_value_1 = rdd_row_0.getUTF8String(1);

project_value_2_geo = org.apache.spark.sql.arctern.GeometryUDT.FromWkt(rdd_value_1.toString());
if (project_value_2_geo != null) project_value_2 = org.apache.spark.sql.arctern.GeometryUDT.GeomSerialize(project_value_2_geo);
       
boolean project_isNull_2 = (project_value_2_geo == null);



// geoName: project_value_2_geo
// geoDeclare: org.locationtech.jts.geom.Geometry project_value_2_geo = null;

// new code string
ArrayData project_value_2 = null;
UTF8String rdd_value_1 = rdd_row_0.getUTF8String(1);

project_value_2_geo = org.apache.spark.sql.arctern.GeometryUDT.FromWkt(rdd_value_1.toString());
       
boolean project_isNull_2 = (project_value_2_geo == null);




// leftGeoCode
ArrayData project_value_2 = null;
UTF8String rdd_value_1 = rdd_row_0.getUTF8String(1);

project_value_2_geo = org.apache.spark.sql.arctern.GeometryUDT.FromWkt(rdd_value_1.toString());
       
boolean project_isNull_2 = (project_value_2_geo == null);

// rightGeoCode
ExprCode(org.locationtech.jts.geom.Geometry project_value_4_geo = null;
ArrayData project_value_4 = null;
UTF8String rdd_value_2 = rdd_row_0.getUTF8String(2);

project_value_4_geo = org.apache.spark.sql.arctern.GeometryUDT.FromWkt(rdd_value_2.toString());
if (project_value_4_geo != null) project_value_4 = org.apache.spark.sql.arctern.GeometryUDT.GeomSerialize(project_value_4_geo);
       
boolean project_isNull_4 = (project_value_4_geo == null);,project_isNull_4,project_value_4)


// assignment code
project_value_1 = project_value_2_geo.within(project_value_4_geo);


// null safe eval

ArrayData project_value_2 = null;
UTF8String rdd_value_1 = rdd_row_0.getUTF8String(1);

project_value_2_geo = org.apache.spark.sql.arctern.GeometryUDT.FromWkt(rdd_value_1.toString());
       
boolean project_isNull_2 = (project_value_2_geo == null);

if (!project_isNull_2) {
	ArrayData project_value_4 = null;
	UTF8String rdd_value_2 = rdd_row_0.getUTF8String(2);

	project_value_4_geo = org.apache.spark.sql.arctern.GeometryUDT.FromWkt(rdd_value_2.toString());

	boolean project_isNull_4 = (project_value_4_geo == null);

	if (!project_isNull_4) {
		project_isNull_1 = false; // resultCode could change nullability.
		project_value_1 = project_value_2_geo.within(project_value_4_geo);
	}
}
      