/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * 
 * Copyright (C) 2010, 2011, 2012, Pyravlos Team
 * 
 * http://www.strabon.di.uoa.gr/
 */
package eu.earthobservatory.constants;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import eu.earthobservatory.vocabulary.SimpleFeatures;

/**
 * This class is a placeholder for various constants around geometries. These
 * constants range from URIs of namespaces, functions, representations, etc.,
 * to other constants, such as the default spatial reference system (SRID) that
 * is assumed in Strabon.
 * 
 * @author Charalampos Nikolaou <charnik@di.uoa.gr>
 * @author Kostis Kyzirakos <kkyzir@di.uoa.gr>
 * @author Kallirroi Dogani <kallirroi@di.uoa.gr>
 */
public class GeoConstants {
	/**																		*
	 *  						Namespaces									*
	 * 																		*/
	
	/**
	 * The namespace for stRDF data model
	 */
	public static final String stRDF					= "http://strdf.di.uoa.gr/ontology#";
	
	/**
	 * The namespace for the RDFi framework
	 */
	public static final String rdfi						= "http://rdfi.di.uoa.gr/ontology#";
	
	
	/**																*
	 *  GeoSPARQL	Version 1.0.1	Document#  11-052r4 			*
	 *	http://schemas.opengis.net/geosparql/geosparql-1_0_1.zip	*/
	
	/**
	 * The namespace for GeoSPARQL ontology
	 */
	public static final String GEO						= "http://www.opengis.net/ont/geosparql#";
	
	/**
	 * The namespace for geometry functions declared by GeoSPARQL
	 */
	public static final String GEOF						= "http://www.opengis.net/def/function/geosparql/";
	
	/**
	 * The namespace for the ontology of simple features
	 */
	public static final String SF						= SimpleFeatures.NAMESPACE;
	
	/**
	 * 
	 * The namespace of GML.
	 * 
	 * Initially, it was set to "http://www.opengis.net/def/geometryType/OGC-GML/3.2/".
	 * Afterwards, it was set to "http://www.opengis.net/gml/3.2/" in order to be compliant
	 * with GML version 3.3, as defined by OGC in the document with title
	 * <tt>"OGC® Geography Markup Language (GML) — Extended schemas and encoding rules"</tt>
	 * ({@link https://portal.opengeospatial.org/files/?artifact_id=46568}). However, none
	 * of these work with the parser provided by JTS, which assumes that the namespace for
	 * GML should be only "http://www.opengis.net/gml" and nothing else. In every other case,
	 * an exception is thrown by the GML parser.
	 * 
	 * UPDATE: The most recent value for the GML namespace by OGC is 
	 * "http://www.opengis.net/ont/gml#".
	 * 
	 * @see {@link org.openrdf.query.algebra.evaluation.util.JTSWrapper.GMLReader}, {@link GMLReader}
	 */
	public static final String GML_OGC					= "http://www.opengis.net/gml";
	
	
	
	
	/**																		*
	 *  						URIs										*
	 * 																		*/
	
	/**																*
	 *  GeoSPARQL	Version 1.0.1	Document#  11-052r4 			*
	 *	http://schemas.opengis.net/geosparql/geosparql-1_0_1.zip	*/

	/** The following GeoSPARQL classes and properties are       
	 *  commented out because they are not currently used.			
 	 */
	
	/**
	 * The URIs for GeoSPARQL classes
	 */
/*	public static final String SpatialObject			=  GEO + "SpatialObject";
	public static final String Geometry				    =  GEO + "Geometry";
	public static final String Feauture					=  GEO + "Feature";
*/	
		
	/**
	 * The URIs for GeoSPARQL properties
	 */
/*	public static final String hasGeometry_OGC				=  GEO + "hasGeometry";
	public static final String defaultGeometry_OGC			=  GEO + "defaultGeometry";
	public static final String dimension_OGC				=  GEO + "dimension";
	public static final String coordinateDimension_OGC		=  GEO + "coordinateDimension";
	public static final String spatialDimension_OGC		    =  GEO + "spatialDimension";
	public static final String isEmpty_OGC					=  GEO + "isEmpty";
	public static final String isSimple_OGC					=  GEO + "isSimple";
	public static final String asWKT_OGC					=  GEO + "asWKT";
	public static final String asGML_OGC					=  GEO + "asGML";
*/
	
	
	/**																		*
	 *  						URIs for datatypes							*
	 * 																		*/
	
	
	/**
	 * The URI for the datatype SemiLinearPointSet
	 * (linear constraint-based representation of geometries)
	 */
	@Deprecated
	public static final String stRDFSemiLinearPointset	= stRDF + "SemiLinearPointSet";
	
	
	/**
	 * The URI for the datatype Well-Known Text (WKT)
	 */
	public static final String WKT 						= stRDF + "WKT";

	/**
	 * The URI for the datatype Geography Markup Language (GML) as it defined
	 * in the model stRDF and query language stSPARQL
	 */
	public static final String GML						= stRDF + "GML";
	
	/**
	 * The URI for the datatype wktLiteral
	 */
	public static final String WKTLITERAL				=  GEO + "wktLiteral";
	
	/**
	 * The URI for the datatype gmlLiteral
	 */
	public static final String GMLLITERAL				=  GEO + "gmlLiteral";
	
	/**																		*
	 *  						Extended functions 							*
	 *  							stSPARQL								*
	 * 																		*/
	// Spatial Relationships
	public static final String stSPARQLequals 		= stRDF + "equals";
	public static final String stSPARQLdisjoint 		= stRDF + "disjoint";
	public static final String stSPARQLintersects 	= stRDF + "intersects";
	public static final String stSPARQLtouches 		= stRDF + "touches";
	public static final String stSPARQLwithin 		= stRDF + "within";
	public static final String stSPARQLcontains 		= stRDF + "contains";
	public static final String stSPARQLoverlaps 		= stRDF + "overlaps";
	public static final String stSPARQLcrosses 		= stRDF + "crosses";
	
	// The generic relate function
	public static final String stSPARQLrelate			= stRDF + "relate";
	
	// Topological Relationships utilizing mbb
	public static final String stSPARQLmbbIntersects	= stRDF + "mbbIntersects";
	public static final String stSPARQLmbbContains 	= stRDF + "mbbContains";
	public static final String stSPARQLmbbEquals 		= stRDF + "mbbEquals";
	public static final String stSPARQLmbbWithin 		= stRDF + "mbbWithin";
	
	// Directional functions
	public static final String stSPARQLleft 			= stRDF + "left";
	public static final String stSPARQLright			= stRDF + "right";
	public static final String stSPARQLabove 			= stRDF + "above";
	public static final String stSPARQLbelow			= stRDF + "below";

	// Spatial Constructs
	public static final String stSPARQLunion 			= stRDF + "union";
	public static final String stSPARQLbuffer 		= stRDF + "buffer";
	public static final String stSPARQLenvelope 		= stRDF + "envelope";
	public static final String stSPARQLconvexHull		= stRDF + "convexHull";
	public static final String stSPARQLboundary 		= stRDF + "boundary";
	public static final String stSPARQLintersection 	= stRDF + "intersection";
	public static final String stSPARQLdifference 	= stRDF + "difference";
	public static final String stSPARQLsymDifference	= stRDF + "symDifference";
	public static final String stSPARQLtransform 		= stRDF + "transform";
	
	// Spatial Metric Functions
	public static final String stSPARQLdistance 		= stRDF + "distance";
	public static final String stSPARQLarea 			= stRDF + "area";

	// Spatial Properties
	public static final String stSPARQLdimension 		= stRDF + "dimension";
	public static final String stSPARQLgeometryType 	= stRDF + "geometryType";
	public static final String stSPARQLasText 		= stRDF + "asText";
	public static final String stSPARQLasGML 			= stRDF + "asGML";
	public static final String stSPARQLsrid 			= stRDF + "srid";
	public static final String stSPARQLisEmpty 		= stRDF + "isEmpty";
	public static final String stSPARQLisSimple 		= stRDF + "isSimple";

	// Spatial Aggregate Functions
	public static final String stSPARQLextent 		= stRDF + "extent";
	
	/**
	 * WGS 84 latitude-longitude (EPSG:4326)
	 */
	public static final String WGS84_LAT_LON	= "http://www.opengis.net/def/crs/EPSG/0/4326";
	
	/**
	 * WGS 84 longitude-longitude
	 * (used as the default CRS for GeoSPARQL geometries)
	 */
	public static final String WGS84_LON_LAT	= "http://www.opengis.net/def/crs/OGC/1.3/CRS84";

	/**
	 * EPSG:4326
	 */
	public static final Integer WGS84_LAT_LON_SRID = 4326;
	
	/**
	 * EPSG:3857 (not sure whether this is correct for WGS84_LON_LAT)
	 * http://spatialreference.org/ref/sr-org/7483/
	 */
	public static final Integer WGS84_LON_LAT_SRID = 3857;

	/**
	 * Default SRID (WGS84 latitude-longitude)
	 */
	public static final Integer defaultSRID 	= WGS84_LAT_LON_SRID;
	
	/**																		*
	 *  						Extended functions 							*
	 *  							GeoSPARQL								*
	 * 																		*/	
	// Non-topological
	public static final String geoSparqlDistance 				= GEOF + "distance"; //3 arguments
	public static final String geoSparqlBuffer 				= GEOF + "buffer"; //3 arguments
	public static final String geoSparqlConvexHull 			= GEOF + "convexHull";
	public static final String geoSparqlIntersection 			= GEOF + "intersection";
	public static final String geoSparqlUnion 				= GEOF + "union";
	public static final String geoSparqlDifference 			= GEOF + "difference";
	public static final String geoSparqlSymmetricDifference 	= GEOF + "symDifference";
	public static final String geoSparqlEnvelope 				= GEOF + "envelope";
	public static final String geoSparqlBoundary 				= GEOF + "boundary";
	public static final String geoSparqlGetSRID				= GEOF + "getSRID";

	// Simple Features - 8 functions - all with 2 arguments + boolean
	public static final String sfEquals 						= GEOF + "sfEquals";
	public static final String sfDisjoint 					= GEOF + "sfDisjoint";
	public static final String sfIntersects 					= GEOF + "sfIntersects";
	public static final String sfTouches 						= GEOF + "sfTouches";
	public static final String sfCrosses 						= GEOF + "sfCrosses";
	public static final String sfWithin 						= GEOF + "sfWithin";
	public static final String sfContains 					= GEOF + "sfContains";
	public static final String sfOverlaps 					= GEOF + "sfOverlaps";

	// Egenhofer - 8 functions - all with 2 arguments + boolean
	public static final String ehEquals 						= GEOF + "ehEquals";
	public static final String ehDisjoint 					= GEOF + "ehDisjoint";
	public static final String ehMeet 						= GEOF + "ehMeet";
	public static final String ehOverlap 						= GEOF + "ehOverlap";
	public static final String ehCovers 						= GEOF + "ehCovers";
	public static final String ehCoveredBy 					= GEOF + "ehCoveredBy";
	public static final String ehInside 						= GEOF + "ehInside";
	public static final String ehContains 					= GEOF + "ehContains";

	// RCC8 - 8 functions - all with 2 arguments + boolean
	public static final String rccEquals 						 		= GEOF + "rcc8eq";
	public static final String rccDisconnected 				 		= GEOF + "rcc8dc";
	public static final String rccExternallyConnected 		 		= GEOF + "rcc8ec";
	public static final String rccPartiallyOverlapping 		 		= GEOF + "rcc8po";
	public static final String rccTangentialProperPartInverse			= GEOF + "rcc8tppi";
	public static final String rccTangentialProperPart 		 		= GEOF + "rcc8tpp";
	public static final String rccNonTangentialProperPart 		 	= GEOF + "rcc8ntpp";
	public static final String rccNonTangentialProperPartInverse 		= GEOF + "rcc8ntppi";
	
	// The generic relate function
	public static final String geoSparqlRelate 					 	= GEOF + "relate";

	/**
	 * Addition for datetime metric functions
	 * 
	 * @author George Garbis <ggarbis@di.uoa.gr>
	 * 
	 */
	public static final String diffDateTime = "http://strdf.di.uoa.gr/extensions/ontology#diffDateTime";
	/** End of addition **/

	/**
	 * RCC-8 relations for the RDFi framework
	 */
	public static final String rdfiDC						= rdfi + "DC";
	public static final String rdfiEC						= rdfi + "EC";
	public static final String rdfiPO						= rdfi + "PO";
	public static final String rdfiNTPP					= rdfi + "NTPP";
	public static final String rdfiNTPPi					= rdfi + "NTPPi";
	public static final String rdfiTPP					= rdfi + "TPP";
	public static final String rdfiTPPi					= rdfi + "TPPi";
	public static final String rdfiEQ						= rdfi + "EQ";
	
	/**
	 * List of stSPARQL spatial extension functions 
	 */
	public static final List<String> STSPARQLSpatialExtFunc = new ArrayList<String>();
	
	/**
	 * List of stSPARQL temporal extension functions
	 */
	//public static final List<String> stSPARQLTemporalExtFunc = new ArrayList<String>();
	
	/**
	 * List of GeoSPARQL extension functions
	 */
	public static final List<String> GEOSPARQLExtFunc = new ArrayList<String>();
	
	// declare spatial and temporal extension functions
	static {
		Class<GeoConstants> geoConstants = GeoConstants.class;	
		
		try {
			Field[] field = geoConstants.getDeclaredFields();
		
			for (int i = 0; i < field.length; i++) {
				// stSPARQL
				if (field[i].getName().startsWith("stSPARQL")) {
					STSPARQLSpatialExtFunc.add((String) field[i].get(null));
					
				} else if (field[i].getName().startsWith("geoSparql") || 
						field[i].getName().startsWith("sf") ||
						field[i].getName().startsWith("eh") ||
						field[i].getName().startsWith("rcc")) { // GeoSPARQL
					GEOSPARQLExtFunc.add((String) field[i].get(null));
				}
			}
		} catch (SecurityException e) {
			// suppress exception; it should not reach here
		} catch (IllegalArgumentException e) {
			// suppress exception; it should not reach here 
		} catch (IllegalAccessException e) {
			// suppress exception; it should not reach here
		}
	}
}
