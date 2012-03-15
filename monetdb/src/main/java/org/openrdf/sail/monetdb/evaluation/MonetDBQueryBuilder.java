/*
 * Copyright Aduna (http://www.aduna-software.com/) (c) 2008.
 *
 * Licensed under the Aduna BSD-style license.
 */
package org.openrdf.sail.monetdb.evaluation;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.query.algebra.QueryModelNode;
import org.openrdf.query.algebra.evaluation.function.spatial.StrabonPolyhedron;
import org.openrdf.sail.generaldb.algebra.GeneralDBColumnVar;
import org.openrdf.sail.generaldb.algebra.GeneralDBDoubleValue;
import org.openrdf.sail.generaldb.algebra.GeneralDBLabelColumn;
import org.openrdf.sail.generaldb.algebra.GeneralDBNumericColumn;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlAbove;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlAnd;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlAnyInteract;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlBelow;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlCase;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlContains;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlCoveredBy;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlCovers;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlDisjoint;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlEqualsSpatial;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoArea;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoAsText;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoBoundary;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoBuffer;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoConvexHull;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoDifference;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoDimension;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoDistance;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoEnvelope;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoGeometryType;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoIntersection;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoIsEmpty;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoIsSimple;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoSrid;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoSymDifference;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoTransform;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlGeoUnion;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlInside;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlIsNull;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlLeft;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlMathExpr;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlNot;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlNull;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlOr;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlOverlap;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlRelate;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlRight;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlSpatialConstructBinary;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlSpatialConstructUnary;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlSpatialMetricBinary;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlSpatialMetricUnary;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlSpatialProperty;
import org.openrdf.sail.generaldb.algebra.GeneralDBSqlTouch;
import org.openrdf.sail.generaldb.algebra.GeneralDBStringValue;
import org.openrdf.sail.generaldb.algebra.GeneralDBUnionItem;
import org.openrdf.sail.generaldb.algebra.base.BinaryGeneralDBOperator;
import org.openrdf.sail.generaldb.algebra.base.GeneralDBFromItem;
import org.openrdf.sail.generaldb.algebra.base.GeneralDBSqlExpr;
import org.openrdf.sail.generaldb.algebra.base.TripleGeneralDBOperator;
import org.openrdf.sail.generaldb.algebra.base.UnaryGeneralDBOperator;
import org.openrdf.sail.generaldb.algebra.egenhofer.GeneralDBSqlEgenhofer_Contains;
import org.openrdf.sail.generaldb.algebra.egenhofer.GeneralDBSqlEgenhofer_CoveredBy;
import org.openrdf.sail.generaldb.algebra.egenhofer.GeneralDBSqlEgenhofer_Covers;
import org.openrdf.sail.generaldb.algebra.egenhofer.GeneralDBSqlEgenhofer_Disjoint;
import org.openrdf.sail.generaldb.algebra.egenhofer.GeneralDBSqlEgenhofer_Equals;
import org.openrdf.sail.generaldb.algebra.egenhofer.GeneralDBSqlEgenhofer_Inside;
import org.openrdf.sail.generaldb.algebra.egenhofer.GeneralDBSqlEgenhofer_Meet;
import org.openrdf.sail.generaldb.algebra.egenhofer.GeneralDBSqlEgenhofer_Overlap;
import org.openrdf.sail.generaldb.algebra.rcc8.GeneralDBSqlRCC8_Dc;
import org.openrdf.sail.generaldb.algebra.rcc8.GeneralDBSqlRCC8_Ec;
import org.openrdf.sail.generaldb.algebra.rcc8.GeneralDBSqlRCC8_Eq;
import org.openrdf.sail.generaldb.algebra.rcc8.GeneralDBSqlRCC8_Ntpp;
import org.openrdf.sail.generaldb.algebra.rcc8.GeneralDBSqlRCC8_Ntppi;
import org.openrdf.sail.generaldb.algebra.rcc8.GeneralDBSqlRCC8_Po;
import org.openrdf.sail.generaldb.algebra.rcc8.GeneralDBSqlRCC8_Tpp;
import org.openrdf.sail.generaldb.algebra.rcc8.GeneralDBSqlRCC8_Tppi;
import org.openrdf.sail.generaldb.algebra.sf.GeneralDBSqlSF_Contains;
import org.openrdf.sail.generaldb.algebra.sf.GeneralDBSqlSF_Crosses;
import org.openrdf.sail.generaldb.algebra.sf.GeneralDBSqlSF_Disjoint;
import org.openrdf.sail.generaldb.algebra.sf.GeneralDBSqlSF_Equals;
import org.openrdf.sail.generaldb.algebra.sf.GeneralDBSqlSF_Intersects;
import org.openrdf.sail.generaldb.algebra.sf.GeneralDBSqlSF_Overlaps;
import org.openrdf.sail.generaldb.algebra.sf.GeneralDBSqlSF_Touches;
import org.openrdf.sail.generaldb.algebra.sf.GeneralDBSqlSF_Within;
import org.openrdf.sail.generaldb.evaluation.GeneralDBQueryBuilder;
import org.openrdf.sail.generaldb.evaluation.GeneralDBSqlBracketBuilder;
import org.openrdf.sail.generaldb.evaluation.GeneralDBSqlExprBuilder;
import org.openrdf.sail.generaldb.evaluation.GeneralDBSqlJoinBuilder;
import org.openrdf.sail.generaldb.evaluation.GeneralDBSqlQueryBuilder;
import org.openrdf.sail.rdbms.exceptions.RdbmsException;
import org.openrdf.sail.rdbms.exceptions.UnsupportedRdbmsOperatorException;

/**
 * Constructs an SQL query from {@link GeneralDBSqlExpr}s and {@link GeneralDBFromItem}s.
 * 
 * @author James Leigh
 * 
 */
public class MonetDBQueryBuilder extends GeneralDBQueryBuilder {

	public enum SpatialOperandsMonetDB { left, right, above, below; }
	public enum SpatialFunctionsMonetDB 
	{ 	//Spatial Relationships
		ST_Disjoint, 
		ST_Touches,
		ST_Covers,
		ST_CoveredBy,
		ST_Overlaps,
		ST_Relate,
		// These Spatial Relations are implemented in MonetDB as operands and they apply in MBB of a geometry
		anyInteract, 
		equals, 
		contains, 
		inside,		
		//Spatial Constructs - Binary
		ST_Union,
		ST_Intersection,
		ST_Difference,
		ST_Buffer,
		ST_Transform,
		ST_SymDifference,
		//Spatial Constructs - Unary
		ST_Envelope,
		ST_ConvexHull,
		ST_Boundary,
		//Spatial Metrics - Binary
		ST_Distance,
		//Spatial Metrics - Unary
		ST_Area,
		//Spatial Properties - All Unary
		ST_Dimension,
		ST_GeometryType,
		ST_AsText,
		ST_SRID,
		ST_IsEmpty,
		ST_IsSimple,
		//GeoSPARQL
		//Simple Features
		SF_Equals,
		SF_Disjoint,
		SF_Intersects,
		SF_Touches,
		SF_Within,
		SF_Contains,
		SF_Overlaps,
		SF_Crosses,
		//RCC8
		RCC8_Eq,
		RCC8_Dc,
		RCC8_Ec,
		RCC8_Po,
		RCC8_Tppi,
		RCC8_Tpp,
		RCC8_Ntppi,
		RCC8_Ntpp,
		//Egenhofer
		EH_Equals,
		EH_Disjoint,
		EH_Meet,
		EH_Overlap,
		EH_Covers,
		EH_CoveredBy,
		EH_Inside,
		EH_Contains
		; 
	}

	public MonetDBQueryBuilder() {
		super();
	}
	
	public MonetDBQueryBuilder(GeneralDBSqlQueryBuilder builder) {
		super(builder);
		this.query = builder;
	}
	
	@Override
	protected void append(GeneralDBSqlNull expr, GeneralDBSqlExprBuilder filter) {
		QueryModelNode parent = expr.getParentNode();
		String	before = null,
				after = null;
		
		if (	parent instanceof GeneralDBSqlOr ||
				parent instanceof GeneralDBSqlAnd
			) {
			before = " CAST(";
			after= " AS boolean) ";
		}
		
		if ( before != null )
			filter.append(before);
		filter.appendNull();
		if ( after != null )
			filter.append(after);
	}
	
	protected void appendWithCastDouble(GeneralDBLabelColumn var, GeneralDBSqlExprBuilder filter) {
		if (var.getRdbmsVar().isResource()) {
			filter.appendNull();
		}
		else {
			//XXX original/default case
			filter.append(" CAST( ");
			String alias = getLabelAlias(var.getRdbmsVar());
			filter.column(alias, "value");
			filter.append(" AS DOUBLE) ");
		}
	}
	
	@Override
	protected void append(GeneralDBLabelColumn var, GeneralDBSqlExprBuilder filter) {
		if (var.getRdbmsVar().isResource()) {
			filter.appendNull();
		}
		else {
			if(var.isSpatial())
			{
				filter.appendFunction("asBinary");
				filter.openBracket();
				String alias = getLabelAlias(var.getRdbmsVar());

				filter.column(alias, "strdfgeo");
				filter.closeBracket();

			}
			else
			{
				//XXX original/default case
				String alias = getLabelAlias(var.getRdbmsVar());
				filter.column(alias, "value");
			}
		}
	}

	protected void append(GeneralDBSqlAnd expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		GeneralDBSqlBracketBuilder open = filter.open();
		dispatch(expr.getLeftArg(), (GeneralDBSqlExprBuilder) open);
		open.and();
		dispatch(expr.getRightArg(), (GeneralDBSqlExprBuilder) open);
		open.close();
	}
	
	@Override
	protected void append(GeneralDBSqlIsNull expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		GeneralDBSqlBracketBuilder open = filter.open();
		dispatch(expr.getArg(), (GeneralDBSqlExprBuilder) open);
		open.isNull();
		open.close();
	}
	
	@Override
	protected void append(GeneralDBSqlNot expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		if (expr.getArg() instanceof GeneralDBSqlIsNull) {
			GeneralDBSqlBracketBuilder open = filter.open();
			GeneralDBSqlIsNull arg = (GeneralDBSqlIsNull)expr.getArg();
			dispatch(arg.getArg(), (GeneralDBSqlExprBuilder) open);
			open.isNotNull();
			open.close();
		}
		else {
			GeneralDBSqlBracketBuilder open = filter.not();
			dispatch(expr.getArg(), (GeneralDBSqlExprBuilder) open);
			open.close();
		}
	}

	@Override
	protected GeneralDBSqlJoinBuilder subJoinAndFilter(GeneralDBSqlJoinBuilder query, GeneralDBFromItem from)
	throws RdbmsException, UnsupportedRdbmsOperatorException
	{
		if (from instanceof GeneralDBUnionItem) {
			GeneralDBUnionItem union = (GeneralDBUnionItem)from;
			List<String> names = union.getSelectVarNames();
			List<GeneralDBColumnVar> vars = union.appendVars(new ArrayList<GeneralDBColumnVar>());
			GeneralDBSqlQueryBuilder subquery = query.subquery();
			for (GeneralDBFromItem item : union.getUnion()) {
				for (int i = 0, n = names.size(); i < n; i++) {
					GeneralDBColumnVar var = item.getVar(names.get(i));
					GeneralDBSqlExprBuilder select = subquery.select();
					if (var == null) {
						select.append("CAST( "); //change
						select.appendNull();
						select.append(" AS INTEGER) "); //change
					}
					else if (var.isImplied()) {
						select.appendNumeric(vf.getInternalId(var.getValue()));
					}
					else {
						select.column(var.getAlias(), var.getColumn());
					}
					select.as(vars.get(i).getColumn());
				}
				from(subquery, item);
				subquery = subquery.union();
			}
		}
		for (GeneralDBFromItem join : from.getJoins()) {
			join(query, join);
		}
		for (GeneralDBSqlExpr expr : from.getFilters()) {
			dispatch(expr, query.on().and());
		}
		return query;
	}

	
	//FIXME my addition from here on
	@Override
	public GeneralDBQueryBuilder construct(GeneralDBSqlExpr expr)
	throws UnsupportedRdbmsOperatorException
	{
		if(!(expr instanceof GeneralDBSqlSpatialMetricBinary) 
				&&!(expr instanceof GeneralDBSqlSpatialMetricUnary)
				&&!(expr instanceof GeneralDBSqlMathExpr)
				&&!(expr instanceof GeneralDBSqlSpatialProperty))
		{
			query.select().appendFunction("asBinary");
		}
		else
		{
			query.select();
		}
		if(expr instanceof BinaryGeneralDBOperator)
		{
			dispatchBinarySqlOperator((BinaryGeneralDBOperator) expr, (MonetDBSqlExprBuilder)query.select);
		}
		else if(expr instanceof UnaryGeneralDBOperator)
		{
			dispatchUnarySqlOperator((UnaryGeneralDBOperator) expr, (MonetDBSqlExprBuilder)query.select);
		}
		return this;
	}

	//Spatial Relationship Functions
	@Override
	protected void append(GeneralDBSqlAnyInteract expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.anyInteract);
//		appendMonetDBSpatialOperand(expr, filter, SpatialOperandsMonetDB.anyInteract);
	}

	@Override
	protected void append(GeneralDBSqlContains expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException {

		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.contains);
//		appendMonetDBSpatialOperand(expr, filter, SpatialOperandsMonetDB.contains);
	}

	@Override
	protected void append(GeneralDBSqlEqualsSpatial expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException {

		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.equals);
//		appendMonetDBSpatialOperand(expr, filter, SpatialOperandsMonetDB.inside);
	}

	@Override
	protected void append(GeneralDBSqlInside expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException {

		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.inside);
//		appendMonetDBSpatialOperand(expr, filter, SpatialOperandsMonetDB.inside);
	}

	@Override
	protected void append(GeneralDBSqlCovers expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException {

		appendgeoSPARQLSpatialRelation(expr, filter, SpatialFunctionsMonetDB.ST_Covers);
	}

	@Override
	protected void append(GeneralDBSqlCoveredBy expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException {

		appendgeoSPARQLSpatialRelation(expr, filter, SpatialFunctionsMonetDB.ST_CoveredBy);
	}

	@Override
	protected void append(GeneralDBSqlTouch expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException {

		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.ST_Touches);
	}

	@Override
	protected void append(GeneralDBSqlOverlap expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException {

		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.ST_Overlaps);
	}

	@Override
	protected void append(GeneralDBSqlDisjoint expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException {

		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.ST_Disjoint);
	}

	@Override
	protected void append(GeneralDBSqlRelate expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionTriple(expr, filter, SpatialFunctionsMonetDB.ST_Relate);
	}

	@Override
	protected void append(GeneralDBSqlLeft expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		// FIXME
		throw new UnsupportedRdbmsOperatorException("left operator not supported in MonetDB");
//		appendStSPARQLSpatialOperand(expr, filter, SpatialOperandsMonetDB.left);
	}
	
	@Override
	protected void append(GeneralDBSqlRight expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		// FIXME
		throw new UnsupportedRdbmsOperatorException("right operator not supported in MonetDB");
//		appendStSPARQLSpatialOperand(expr, filter, SpatialOperandsMonetDB.right);
	}
	
	@Override
	protected void append(GeneralDBSqlAbove expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		// FIXME
		throw new UnsupportedRdbmsOperatorException("above operator not supported in MonetDB");
//		appendStSPARQLSpatialOperand(expr, filter, SpatialOperandsMonetDB.above);
	}

	@Override
	protected void append(GeneralDBSqlBelow expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		// FIXME
		throw new UnsupportedRdbmsOperatorException("below operator not supported in MonetDB");
//		appendStSPARQLSpatialOperand(expr, filter, SpatialOperandsMonetDB.above);
	}
	
	//GeoSPARQL - Spatial Relationship Functions 
	//Simple Features
	@Override
	protected void append(GeneralDBSqlSF_Contains expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.SF_Contains);
	}

	@Override
	protected void append(GeneralDBSqlSF_Crosses expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.SF_Crosses);
	}

	@Override
	protected void append(GeneralDBSqlSF_Disjoint expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.SF_Disjoint);
	}

	@Override
	protected void append(GeneralDBSqlSF_Equals expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.SF_Equals);
	}

	@Override
	protected void append(GeneralDBSqlSF_Intersects expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.SF_Intersects);
	}

	@Override
	protected void append(GeneralDBSqlSF_Overlaps expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.SF_Overlaps);
	}

	@Override
	protected void append(GeneralDBSqlSF_Touches expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.SF_Touches);
	}

	@Override
	protected void append(GeneralDBSqlSF_Within expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.SF_Within);
	}

	//Egenhofer
	@Override
	protected void append(GeneralDBSqlEgenhofer_CoveredBy expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.EH_CoveredBy);
	}
	
	@Override
	protected void append(GeneralDBSqlEgenhofer_Covers expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.EH_Covers);
	}
	
	@Override
	protected void append(GeneralDBSqlEgenhofer_Contains expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.EH_Contains);
	}
	
	@Override
	protected void append(GeneralDBSqlEgenhofer_Disjoint expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.EH_Disjoint);
	}
	
	@Override
	protected void append(GeneralDBSqlEgenhofer_Equals expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.EH_Equals);
	}
	
	@Override
	protected void append(GeneralDBSqlEgenhofer_Inside expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.EH_Inside);
	}
	
	@Override
	protected void append(GeneralDBSqlEgenhofer_Meet expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.EH_Meet);
	}

	@Override
	protected void append(GeneralDBSqlEgenhofer_Overlap expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.EH_Overlap);
	}
	
	//RCC8

	@Override
	protected void append(GeneralDBSqlRCC8_Dc expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.RCC8_Dc);
	}

	@Override
	protected void append(GeneralDBSqlRCC8_Eq expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.RCC8_Eq);
	}

	@Override
	protected void append(GeneralDBSqlRCC8_Ec expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.RCC8_Ec);
	}

	@Override
	protected void append(GeneralDBSqlRCC8_Po expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.RCC8_Po);
	}

	@Override
	protected void append(GeneralDBSqlRCC8_Tppi expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.RCC8_Tppi);
	}

	@Override
	protected void append(GeneralDBSqlRCC8_Tpp expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.RCC8_Tpp);
	}

	@Override
	protected void append(GeneralDBSqlRCC8_Ntpp expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.RCC8_Ntpp);
	}

	@Override
	protected void append(GeneralDBSqlRCC8_Ntppi expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendgeoSPARQLSpatialRelation(expr, filter,SpatialFunctionsMonetDB.RCC8_Ntppi);
	}
	
	//Spatial Construct Functions
	@Override
	protected void append(GeneralDBSqlGeoUnion expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.ST_Union);
	}

	@Override
	protected void append(GeneralDBSqlGeoBuffer expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.ST_Buffer);
	}
	
	@Override
	protected void append(GeneralDBSqlGeoTransform expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.ST_Transform);
	}

	@Override
	protected void append(GeneralDBSqlGeoEnvelope expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionUnary(expr, filter, SpatialFunctionsMonetDB.ST_Envelope);
	}

	@Override
	protected void append(GeneralDBSqlGeoConvexHull expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionUnary(expr, filter, SpatialFunctionsMonetDB.ST_ConvexHull);
	}

	@Override
	protected void append(GeneralDBSqlGeoBoundary expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionUnary(expr, filter, SpatialFunctionsMonetDB.ST_Boundary);
	}

	@Override
	protected void append(GeneralDBSqlGeoIntersection expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.ST_Intersection);
	}

	@Override
	protected void append(GeneralDBSqlGeoDifference expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.ST_Difference);
	}

	@Override
	protected void append(GeneralDBSqlGeoSymDifference expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.ST_SymDifference);
	}

	@Override
	//Spatial Metric Functions
	protected void append(GeneralDBSqlGeoDistance expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionBinary(expr, filter, SpatialFunctionsMonetDB.ST_Distance);
	}

	@Override
	protected void append(GeneralDBSqlGeoArea expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionUnary(expr, filter, SpatialFunctionsMonetDB.ST_Area);
	}

	@Override
	//Spatial Property Functions
	protected void append(GeneralDBSqlGeoDimension expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionUnary(expr, filter, SpatialFunctionsMonetDB.ST_Dimension);
	}

	@Override
	protected void append(GeneralDBSqlGeoGeometryType expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionUnary(expr, filter, SpatialFunctionsMonetDB.ST_GeometryType);
	}

	@Override
	protected void append(GeneralDBSqlGeoAsText expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionUnary(expr, filter, SpatialFunctionsMonetDB.ST_AsText);
	}

	@Override
	protected void append(GeneralDBSqlGeoSrid expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionUnary(expr, filter, SpatialFunctionsMonetDB.ST_SRID);
	}

	@Override
	protected void append(GeneralDBSqlGeoIsSimple expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionUnary(expr, filter, SpatialFunctionsMonetDB.ST_IsSimple);
	}

	@Override
	protected void append(GeneralDBSqlGeoIsEmpty expr, GeneralDBSqlExprBuilder filter)
	throws UnsupportedRdbmsOperatorException
	{
		appendMonetDBSpatialFunctionUnary(expr, filter, SpatialFunctionsMonetDB.ST_IsEmpty);
	}


	/**
	 * 'helper' functions
	 */

	@Override
	protected String appendWKT(GeneralDBSqlExpr expr, GeneralDBSqlExprBuilder filter)
	{
		GeneralDBStringValue arg = (GeneralDBStringValue) expr;
		String raw = arg.getValue();

		StrabonPolyhedron poly = null;
		try{
			poly = new StrabonPolyhedron(raw);
		} catch (Exception e) {
			e.printStackTrace();
		}

		filter.append(" GeomFromText('"+poly.toWKT() +"',4326)");

		return raw;
	}

	//Used in all the generaldb boolean spatial functions of the form ST_Function(?GEO1,?GEO2) 
	
	protected void appendMonetDBSpatialFunctionBinary(BinaryGeneralDBOperator expr, GeneralDBSqlExprBuilder filter, SpatialFunctionsMonetDB func)
	throws UnsupportedRdbmsOperatorException
	{
		filter.openBracket();

		boolean check1 = expr.getLeftArg().getClass().getCanonicalName().equals("org.openrdf.sail.monetdb.algebra.MonetDBSqlNull");
		if(check1)
		{
			this.append((GeneralDBSqlNull)expr.getLeftArg(), filter);

		}
		else
		{
			switch(func)
			{
			//XXX Careful: ST_Transform support MISSING!!!
			case ST_Difference: filter.appendFunction("Difference"); break;
			case ST_Intersection: filter.appendFunction("Intersection"); break;
			case ST_Union: filter.appendFunction("\"Union\""); break;
			case ST_SymDifference: filter.appendFunction("SymDifference"); break;
			case ST_Buffer: filter.appendFunction("Buffer"); break;
			case ST_Distance: filter.appendFunction("Distance"); break;
			case ST_Touches: filter.appendFunction("Touches"); break;
			case ST_Disjoint: filter.appendFunction("Disjoint"); break;
			case ST_Covers: filter.appendFunction(""); break; // FIXME (with relate ?)
			case ST_CoveredBy: filter.appendFunction(""); break; // FIXME (with relate ?)
			case ST_Overlaps: filter.appendFunction("Overlaps"); break;
			case anyInteract: filter.appendFunction("NOT Disjoint"); break;
			case equals: filter.appendFunction("Equals"); break;
			case contains: filter.appendFunction("Contains"); break;
			case inside: filter.appendFunction("Within"); break;
			}
			//filter.appendFunction("ST_Union");
			filter.openBracket();
			if(expr.getLeftArg() instanceof GeneralDBStringValue)
			{
				appendWKT(expr.getLeftArg(),filter);
			}
			else if(expr.getLeftArg() instanceof GeneralDBSqlSpatialConstructBinary)
			{
				appendConstructFunction(expr.getLeftArg(), filter);
			}
			else if(expr.getLeftArg() instanceof GeneralDBSqlSpatialConstructUnary)
			{
				appendConstructFunction(expr.getLeftArg(), filter);
			}
			else if(expr.getLeftArg() instanceof GeneralDBSqlCase)
			{
				GeneralDBLabelColumn onlyLabel = (GeneralDBLabelColumn)((GeneralDBSqlCase)expr.getLeftArg()).getEntries().get(0).getResult();
				appendMBB(onlyLabel,filter); 
			}
			else
			{
				appendMBB((GeneralDBLabelColumn)(expr.getLeftArg()),filter);
			}
			filter.appendComma();
			boolean check2 = expr.getRightArg().getClass().getCanonicalName().equals("org.openrdf.sail.monetdb.algebra.MonetDBSqlNull");
			if(check2)
			{
				this.append((GeneralDBSqlNull)expr.getRightArg(), filter);
			}
			else
			{
				if(expr.getRightArg() instanceof GeneralDBStringValue)
				{
					appendWKT(expr.getRightArg(),filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialConstructUnary)
				{
					appendConstructFunction(expr.getRightArg(), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialConstructBinary)
				{
					appendConstructFunction(expr.getRightArg(), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlCase)
				{
					GeneralDBLabelColumn onlyLabel = (GeneralDBLabelColumn)((GeneralDBSqlCase)expr.getRightArg()).getEntries().get(0).getResult();
					appendMBB(onlyLabel,filter);					 
				}
				else if(expr.getRightArg() instanceof GeneralDBDoubleValue) //case met in buffer!
				{
					append(((GeneralDBDoubleValue)expr.getRightArg()), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBNumericColumn) //case met in buffer!
				{
					append(((GeneralDBNumericColumn)expr.getRightArg()), filter);
				}
				//case met in buffer when in select -> buffer(?spatial,?thematic)
				else if(expr.getRightArg() instanceof GeneralDBLabelColumn && !((GeneralDBLabelColumn)expr.getRightArg()).isSpatial())
				{
					appendWithCastDouble(((GeneralDBLabelColumn)expr.getRightArg()),filter);
//					append(((GeneralDBLabelColumn)expr.getRightArg()),filter);
//					appendCastToDouble(filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialMetricBinary)
				{
					appendMetricFunction(expr.getRightArg(), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialMetricUnary)
				{
					appendMetricFunction(expr.getRightArg(), filter);
				}
				else
				{
					appendMBB((GeneralDBLabelColumn)(expr.getRightArg()),filter);
				}

			}
			filter.closeBracket();
		}

		filter.closeBracket();
	}

	//Used in all the generaldb boolean spatial functions of the form ST_Function(?GEO1) 
	protected void appendMonetDBSpatialFunctionUnary(UnaryGeneralDBOperator expr, GeneralDBSqlExprBuilder filter, SpatialFunctionsMonetDB func)
	throws UnsupportedRdbmsOperatorException
	{
		filter.openBracket();

		boolean check1 = expr.getArg().getClass().getCanonicalName().equals("org.openrdf.sail.monetdb.algebra.MonetDBSqlNull");
		if(check1)
		{
			this.append((GeneralDBSqlNull)expr.getArg(), filter);

		}
		else
		{
			switch(func)
			{
			case ST_Envelope: filter.appendFunction("Envelope"); break;
			case ST_ConvexHull: filter.appendFunction("ConvexHull"); break;
			case ST_Boundary: filter.appendFunction("Boundary"); break;
			case ST_Area: filter.appendFunction("Area"); break;
			case ST_Dimension: filter.appendFunction("Dimension"); break;
			case ST_GeometryType: filter.appendFunction("GeometryTypeId"); break;
			case ST_AsText:  break; // Do nothing MonetDB by default returns geometris in WKT format
			case ST_SRID: filter.appendFunction("SRID"); break;
			case ST_IsEmpty: filter.appendFunction("IsEmpty"); break;
			case ST_IsSimple: filter.appendFunction("IsSimple"); break;
			}
			//filter.appendFunction("ST_Union");
			filter.openBracket();
			if(expr.getArg() instanceof GeneralDBStringValue)
			{
				appendWKT(expr.getArg(),filter);
			}
			else if(expr.getArg() instanceof GeneralDBSqlSpatialConstructBinary)
			{
				appendConstructFunction(expr.getArg(), filter);
			}
			else if(expr.getArg() instanceof GeneralDBSqlSpatialConstructUnary)
			{
				appendConstructFunction(expr.getArg(), filter);
			}
			else if(expr.getArg() instanceof GeneralDBSqlCase)
			{
				GeneralDBLabelColumn onlyLabel = (GeneralDBLabelColumn)((GeneralDBSqlCase)expr.getArg()).getEntries().get(0).getResult();
				appendMBB(onlyLabel,filter); 
			}
			else
			{
				appendMBB((GeneralDBLabelColumn)(expr.getArg()),filter);
			}

			filter.closeBracket();
		}

		filter.closeBracket();
	}
	
	//Used in all the generaldb boolean spatial functions of the form ST_Function(?GEO1,?GEO2) 
	protected void appendMonetDBSpatialFunctionTriple(TripleGeneralDBOperator expr, GeneralDBSqlExprBuilder filter, SpatialFunctionsMonetDB func)
	throws UnsupportedRdbmsOperatorException
	{
		filter.openBracket();

		boolean check1 = expr.getLeftArg().getClass().getCanonicalName().equals("org.openrdf.sail.monetdb.algebra.MonetDBSqlNull");
		if(check1)
		{
			this.append((GeneralDBSqlNull)expr.getLeftArg(), filter);

		}
		else
		{
			switch(func)
			{
			case ST_Relate: filter.appendFunction("Relate"); break;
			}
			filter.openBracket();
			if(expr.getLeftArg() instanceof GeneralDBStringValue)
			{
				appendWKT(expr.getLeftArg(),filter);
			}
			else if(expr.getLeftArg() instanceof GeneralDBSqlSpatialConstructBinary)
			{
				appendConstructFunction(expr.getLeftArg(), filter);
			}
			else if(expr.getLeftArg() instanceof GeneralDBSqlSpatialConstructUnary)
			{
				appendConstructFunction(expr.getLeftArg(), filter);
			}
			else if(expr.getLeftArg() instanceof GeneralDBSqlCase)
			{
				GeneralDBLabelColumn onlyLabel = (GeneralDBLabelColumn)((GeneralDBSqlCase)expr.getLeftArg()).getEntries().get(0).getResult();
				appendMBB(onlyLabel,filter); 
			}
			else
			{
				appendMBB((GeneralDBLabelColumn)(expr.getLeftArg()),filter);
			}
			filter.appendComma();
			boolean check2 = expr.getRightArg().getClass().getCanonicalName().equals("org.openrdf.sail.monetdb.algebra.GeneralDBSqlNull");
			if(check2)
			{
				this.append((GeneralDBSqlNull)expr.getRightArg(), filter);
			}
			else
			{
				if(expr.getRightArg() instanceof GeneralDBStringValue)
				{
					appendWKT(expr.getRightArg(),filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialConstructUnary)
				{
					appendConstructFunction(expr.getRightArg(), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialConstructBinary)
				{
					appendConstructFunction(expr.getRightArg(), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlCase)
				{
					GeneralDBLabelColumn onlyLabel = (GeneralDBLabelColumn)((GeneralDBSqlCase)expr.getRightArg()).getEntries().get(0).getResult();
					appendMBB(onlyLabel,filter);					 
				}
				else if(expr.getRightArg() instanceof GeneralDBDoubleValue) //case met in buffer!
				{
					append(((GeneralDBDoubleValue)expr.getRightArg()), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBNumericColumn) //case met in buffer!
				{
					append(((GeneralDBNumericColumn)expr.getRightArg()), filter);
				}
				//case met in buffer when in select -> buffer(?spatial,?thematic)
				else if(expr.getRightArg() instanceof GeneralDBLabelColumn && !((GeneralDBLabelColumn)expr.getRightArg()).isSpatial())
				{
					appendWithCastDouble(((GeneralDBLabelColumn)expr.getRightArg()),filter);
//					append(((GeneralDBLabelColumn)expr.getRightArg()),filter);
//					appendCastToDouble(filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialMetricBinary)
				{
					appendMetricFunction(expr.getRightArg(), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialMetricUnary)
				{
					appendMetricFunction(expr.getRightArg(), filter);
				}
				else
				{
					appendMBB((GeneralDBLabelColumn)(expr.getRightArg()),filter);
				}

			}
			//3rd arg
			filter.appendComma();
			boolean check3 = expr.getRightArg().getClass().getCanonicalName().equals("org.openrdf.sail.monetdb.algebra.GeneralDBSqlNull");
			if(check3)
			{
				this.append((GeneralDBSqlNull)expr.getThirdArg(), filter);
			}
			else
			{
				
				if(expr.getThirdArg() instanceof GeneralDBStringValue)
				{
					append(((GeneralDBStringValue)expr.getThirdArg()),filter);	
				}
				else if(expr.getThirdArg() instanceof GeneralDBSqlCase)
				{
					append(((GeneralDBSqlCase)expr.getThirdArg()),filter);				 
				}
				//case met in buffer when in select -> buffer(?spatial,?thematic)
				else if(expr.getThirdArg() instanceof GeneralDBLabelColumn )//&& !((GeneralDBLabelColumn)expr.getThirdArg()).isSpatial())
				{
					
					append(((GeneralDBLabelColumn)expr.getThirdArg()),filter);
				}
				

			}
			filter.closeBracket();
		}

		filter.closeBracket();
	}
	
	//GeoSPARQL
	//XXX
	
	protected void appendRelate(BinaryGeneralDBOperator expr, GeneralDBSqlExprBuilder filter, char[] intersectionPattern)
	throws UnsupportedRdbmsOperatorException
	{
		filter.openBracket();

		boolean check1 = expr.getLeftArg().getClass().getCanonicalName().equals("org.openrdf.sail.monetdb.algebra.GeneralDBSqlNull");
		if(check1)
		{
			this.append((GeneralDBSqlNull)expr.getLeftArg(), filter);

		}
		else
		{	
			filter.appendFunction("Relate");


			filter.openBracket();
			if(expr.getLeftArg() instanceof GeneralDBStringValue)
			{
				appendWKT(expr.getLeftArg(),filter);
			}
			else if(expr.getLeftArg() instanceof GeneralDBSqlSpatialConstructBinary)
			{
				appendConstructFunction(expr.getLeftArg(), filter);
			}
			else if(expr.getLeftArg() instanceof GeneralDBSqlSpatialConstructUnary)
			{
				appendConstructFunction(expr.getLeftArg(), filter);
			}
			else if(expr.getLeftArg() instanceof GeneralDBSqlCase)
			{
				GeneralDBLabelColumn onlyLabel = (GeneralDBLabelColumn)((GeneralDBSqlCase)expr.getLeftArg()).getEntries().get(0).getResult();
				appendMBB(onlyLabel,filter); 
			}
			else
			{
				appendMBB((GeneralDBLabelColumn)(expr.getLeftArg()),filter);
			}
			filter.appendComma();
			boolean check2 = expr.getRightArg().getClass().getCanonicalName().equals("org.openrdf.sail.monetdb.algebra.GeneralDBSqlNull");
			if(check2)
			{
				this.append((GeneralDBSqlNull)expr.getRightArg(), filter);
			}
			else
			{
				if(expr.getRightArg() instanceof GeneralDBStringValue)
				{
					appendWKT(expr.getRightArg(),filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialConstructUnary)
				{
					appendConstructFunction(expr.getRightArg(), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialConstructBinary)
				{
					appendConstructFunction(expr.getRightArg(), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlCase)
				{
					GeneralDBLabelColumn onlyLabel = (GeneralDBLabelColumn)((GeneralDBSqlCase)expr.getRightArg()).getEntries().get(0).getResult();
					appendMBB(onlyLabel,filter);					 
				}
				else if(expr.getRightArg() instanceof GeneralDBDoubleValue) //case met in buffer!
				{
					append(((GeneralDBDoubleValue)expr.getRightArg()), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBNumericColumn) //case met in buffer!
				{
					append(((GeneralDBNumericColumn)expr.getRightArg()), filter);
				}
				//case met in buffer when in select -> buffer(?spatial,?thematic)
				else if(expr.getRightArg() instanceof GeneralDBLabelColumn && !((GeneralDBLabelColumn)expr.getRightArg()).isSpatial())
				{
					appendWithCastDouble(((GeneralDBLabelColumn)expr.getRightArg()),filter);
//					append(((GeneralDBLabelColumn)expr.getRightArg()),filter);
//					appendCastToDouble(filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialMetricBinary)
				{
					appendMetricFunction(expr.getRightArg(), filter);
				}
				else if(expr.getRightArg() instanceof GeneralDBSqlSpatialMetricUnary)
				{
					appendMetricFunction(expr.getRightArg(), filter);
				}
				else
				{
					appendMBB((GeneralDBLabelColumn)(expr.getRightArg()),filter);
				}

			}
			//3rd arg
			filter.appendComma();

			//must turn the table of characters I have to a valid sql value!
			filter.append("'");
			for(int i = 0; i< intersectionPattern.length; i++)
			{
				filter.append(intersectionPattern[i]+"");
			}
			filter.append("'");

			filter.closeBracket();
		}

		filter.closeBracket();
	}
	
	
	protected void appendgeoSPARQLSpatialRelation(BinaryGeneralDBOperator expr, GeneralDBSqlExprBuilder filter, SpatialFunctionsMonetDB func)
	throws UnsupportedRdbmsOperatorException
	{
		filter.openBracket();
		boolean check1 = expr.getLeftArg().getClass().getCanonicalName().equals("org.openrdf.sail.monetdb.algebra.GeneralDBSqlNull");
		if(check1)
		{
			this.append((GeneralDBSqlNull)expr.getLeftArg(), filter);

		}
		else
		{
			char[][] intersectionPattern = null;
			switch(func)
			{
			case ST_Covers: 
				intersectionPattern = new char[4][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = '*';
				intersectionPattern[0][2] = '*';
				intersectionPattern[0][3] = '*';
				intersectionPattern[0][4] = '*';
				intersectionPattern[0][5] = '*';
				intersectionPattern[0][6] = 'F';
				intersectionPattern[0][7] = 'F';
				intersectionPattern[0][8] = '*';
				//
				intersectionPattern[1][0] = '*';
				intersectionPattern[1][1] = 'T';
				intersectionPattern[1][2] = '*';
				intersectionPattern[1][3] = '*';
				intersectionPattern[1][4] = '*';
				intersectionPattern[1][5] = '*';
				intersectionPattern[1][6] = 'F';
				intersectionPattern[1][7] = 'F';
				intersectionPattern[1][8] = '*';
				//
				intersectionPattern[2][0] = '*';
				intersectionPattern[2][1] = '*';
				intersectionPattern[2][2] = '*';
				intersectionPattern[2][3] = 'T';
				intersectionPattern[2][4] = '*';
				intersectionPattern[2][5] = '*';
				intersectionPattern[2][6] = 'F';
				intersectionPattern[2][7] = 'F';
				intersectionPattern[2][8] = '*';
				//
				intersectionPattern[3][0] = '*';
				intersectionPattern[3][1] = '*';
				intersectionPattern[3][2] = '*';
				intersectionPattern[3][3] = '*';
				intersectionPattern[3][4] = 'T';
				intersectionPattern[3][5] = '*';
				intersectionPattern[3][6] = 'F';
				intersectionPattern[3][7] = 'F';
				intersectionPattern[3][8] = '*';
				break;
			case ST_CoveredBy: 
				intersectionPattern = new char[4][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = '*';
				intersectionPattern[0][2] = 'F';
				intersectionPattern[0][3] = '*';
				intersectionPattern[0][4] = '*';
				intersectionPattern[0][5] = 'F';
				intersectionPattern[0][6] = '*';
				intersectionPattern[0][7] = '*';
				intersectionPattern[0][8] = '*';
				//
				intersectionPattern[1][0] = '*';
				intersectionPattern[1][1] = 'T';
				intersectionPattern[1][2] = 'F';
				intersectionPattern[1][3] = '*';
				intersectionPattern[1][4] = '*';
				intersectionPattern[1][5] = 'F';
				intersectionPattern[1][6] = '*';
				intersectionPattern[1][7] = '*';
				intersectionPattern[1][8] = '*';
				//
				intersectionPattern[2][0] = '*';
				intersectionPattern[2][1] = '*';
				intersectionPattern[2][2] = 'F';
				intersectionPattern[2][3] = 'T';
				intersectionPattern[2][4] = '*';
				intersectionPattern[2][5] = 'F';
				intersectionPattern[2][6] = '*';
				intersectionPattern[2][7] = '*';
				intersectionPattern[2][8] = '*';
				//
				intersectionPattern[3][0] = '*';
				intersectionPattern[3][1] = '*';
				intersectionPattern[3][2] = 'F';
				intersectionPattern[3][3] = '*';
				intersectionPattern[3][4] = 'T';
				intersectionPattern[3][5] = 'F';
				intersectionPattern[3][6] = '*';
				intersectionPattern[3][7] = '*';
				intersectionPattern[3][8] = '*';
				break;
			case SF_Contains:  
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = '*';
				intersectionPattern[0][2] = '*';
				intersectionPattern[0][3] = '*';
				intersectionPattern[0][4] = '*';
				intersectionPattern[0][5] = '*';
				intersectionPattern[0][6] = 'F';
				intersectionPattern[0][7] = 'F';
				intersectionPattern[0][8] = '*';
				break;
			case SF_Crosses:  
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = '*';
				intersectionPattern[0][2] = 'T';
				intersectionPattern[0][3] = '*';
				intersectionPattern[0][4] = '*';
				intersectionPattern[0][5] = '*';
				intersectionPattern[0][6] = '*';
				intersectionPattern[0][7] = '*';
				intersectionPattern[0][8] = '*';
				break;
			case SF_Disjoint:
			case EH_Disjoint:
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'F';
				intersectionPattern[0][1] = 'F';
				intersectionPattern[0][2] = '*';
				intersectionPattern[0][3] = 'F';
				intersectionPattern[0][4] = 'F';
				intersectionPattern[0][5] = '*';
				intersectionPattern[0][6] = '*';
				intersectionPattern[0][7] = '*';
				intersectionPattern[0][8] = '*';
				break;
			case SF_Equals: 
			case EH_Equals:
			case RCC8_Eq:	
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = 'F';
				intersectionPattern[0][2] = 'F';
				intersectionPattern[0][3] = 'F';
				intersectionPattern[0][4] = 'T';
				intersectionPattern[0][5] = 'F';
				intersectionPattern[0][6] = 'F';
				intersectionPattern[0][7] = 'F';
				intersectionPattern[0][8] = 'T';
				break;
			case SF_Overlaps:
			case EH_Overlap:
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = '*';
				intersectionPattern[0][2] = 'T';
				intersectionPattern[0][3] = '*';
				intersectionPattern[0][4] = '*';
				intersectionPattern[0][5] = '*';
				intersectionPattern[0][6] = 'T';
				intersectionPattern[0][7] = '*';
				intersectionPattern[0][8] = '*';
				break;
			case SF_Within: 
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = '*';
				intersectionPattern[0][2] = 'F';
				intersectionPattern[0][3] = '*';
				intersectionPattern[0][4] = '*';
				intersectionPattern[0][5] = 'F';
				intersectionPattern[0][6] = '*';
				intersectionPattern[0][7] = '*';
				intersectionPattern[0][8] = '*';
				break;
			case EH_Covers: 
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = '*';
				intersectionPattern[0][2] = 'T';
				intersectionPattern[0][3] = 'F';
				intersectionPattern[0][4] = 'T';
				intersectionPattern[0][5] = '*';
				intersectionPattern[0][6] = 'F';
				intersectionPattern[0][7] = 'F';
				intersectionPattern[0][8] = '*';
				break;
			case EH_CoveredBy: 
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = 'F';
				intersectionPattern[0][2] = 'F';
				intersectionPattern[0][3] = '*';
				intersectionPattern[0][4] = 'T';
				intersectionPattern[0][5] = 'F';
				intersectionPattern[0][6] = 'T';
				intersectionPattern[0][7] = '*';
				intersectionPattern[0][8] = '*';
				break;
			case EH_Inside: 
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = 'F';
				intersectionPattern[0][2] = 'F';
				intersectionPattern[0][3] = '*';
				intersectionPattern[0][4] = 'F';
				intersectionPattern[0][5] = 'F';
				intersectionPattern[0][6] = 'T';
				intersectionPattern[0][7] = '*';
				intersectionPattern[0][8] = '*';
				break;
			case EH_Contains: 
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = '*';
				intersectionPattern[0][2] = 'T';
				intersectionPattern[0][3] = 'F';
				intersectionPattern[0][4] = 'F';
				intersectionPattern[0][5] = '*';
				intersectionPattern[0][6] = 'F';
				intersectionPattern[0][7] = 'F';
				intersectionPattern[0][8] = '*';
				break;
			case RCC8_Dc:	
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'F';
				intersectionPattern[0][1] = 'F';
				intersectionPattern[0][2] = 'T';
				intersectionPattern[0][3] = 'F';
				intersectionPattern[0][4] = 'F';
				intersectionPattern[0][5] = 'T';
				intersectionPattern[0][6] = 'T';
				intersectionPattern[0][7] = 'T';
				intersectionPattern[0][8] = 'T';
				break;
			case RCC8_Ec:	
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'F';
				intersectionPattern[0][1] = 'F';
				intersectionPattern[0][2] = 'T';
				intersectionPattern[0][3] = 'F';
				intersectionPattern[0][4] = 'T';
				intersectionPattern[0][5] = 'T';
				intersectionPattern[0][6] = 'T';
				intersectionPattern[0][7] = 'T';
				intersectionPattern[0][8] = 'T';
				break;
			case RCC8_Po:	
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = 'T';
				intersectionPattern[0][2] = 'T';
				intersectionPattern[0][3] = 'T';
				intersectionPattern[0][4] = 'T';
				intersectionPattern[0][5] = 'T';
				intersectionPattern[0][6] = 'T';
				intersectionPattern[0][7] = 'T';
				intersectionPattern[0][8] = 'T';
				break;
			case RCC8_Tppi:	
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = 'T';
				intersectionPattern[0][2] = 'T';
				intersectionPattern[0][3] = 'F';
				intersectionPattern[0][4] = 'T';
				intersectionPattern[0][5] = 'T';
				intersectionPattern[0][6] = 'F';
				intersectionPattern[0][7] = 'F';
				intersectionPattern[0][8] = 'T';
				break;
			case RCC8_Tpp:	
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = 'F';
				intersectionPattern[0][2] = 'F';
				intersectionPattern[0][3] = 'T';
				intersectionPattern[0][4] = 'T';
				intersectionPattern[0][5] = 'F';
				intersectionPattern[0][6] = 'T';
				intersectionPattern[0][7] = 'T';
				intersectionPattern[0][8] = 'T';
				break;
			case RCC8_Ntpp:	
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = 'F';
				intersectionPattern[0][2] = 'F';
				intersectionPattern[0][3] = 'T';
				intersectionPattern[0][4] = 'F';
				intersectionPattern[0][5] = 'F';
				intersectionPattern[0][6] = 'T';
				intersectionPattern[0][7] = 'T';
				intersectionPattern[0][8] = 'T';
				break;
			case RCC8_Ntppi:	
				intersectionPattern = new char[1][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = 'T';
				intersectionPattern[0][2] = 'T';
				intersectionPattern[0][3] = 'F';
				intersectionPattern[0][4] = 'F';
				intersectionPattern[0][5] = 'T';
				intersectionPattern[0][6] = 'F';
				intersectionPattern[0][7] = 'F';
				intersectionPattern[0][8] = 'T';
				break;
			case SF_Intersects:   
				intersectionPattern = new char[4][9];
				intersectionPattern[0][0] = 'T';
				intersectionPattern[0][1] = '*';
				intersectionPattern[0][2] = '*';
				intersectionPattern[0][3] = '*';
				intersectionPattern[0][4] = '*';
				intersectionPattern[0][5] = '*';
				intersectionPattern[0][6] = '*';
				intersectionPattern[0][7] = '*';
				intersectionPattern[0][8] = '*';
				//
				intersectionPattern[1][0] = '*';
				intersectionPattern[1][1] = 'T';
				intersectionPattern[1][2] = '*';
				intersectionPattern[1][3] = '*';
				intersectionPattern[1][4] = '*';
				intersectionPattern[1][5] = '*';
				intersectionPattern[1][6] = '*';
				intersectionPattern[1][7] = '*';
				intersectionPattern[1][8] = '*';
				//
				intersectionPattern[2][0] = '*';
				intersectionPattern[2][1] = '*';
				intersectionPattern[2][2] = '*';
				intersectionPattern[2][3] = 'T';
				intersectionPattern[2][4] = '*';
				intersectionPattern[2][5] = '*';
				intersectionPattern[2][6] = '*';
				intersectionPattern[2][7] = '*';
				intersectionPattern[2][8] = '*';
				//
				intersectionPattern[3][0] = '*';
				intersectionPattern[3][1] = '*';
				intersectionPattern[3][2] = '*';
				intersectionPattern[3][3] = '*';
				intersectionPattern[3][4] = 'T';
				intersectionPattern[3][5] = '*';
				intersectionPattern[3][6] = '*';
				intersectionPattern[3][7] = '*';
				intersectionPattern[3][8] = '*';
				break;

			case SF_Touches: 
			case EH_Meet:
				intersectionPattern = new char[3][9];
				intersectionPattern[0][0] = 'F';
				intersectionPattern[0][1] = 'T';
				intersectionPattern[0][2] = '*';
				intersectionPattern[0][3] = '*';
				intersectionPattern[0][4] = '*';
				intersectionPattern[0][5] = '*';
				intersectionPattern[0][6] = '*';
				intersectionPattern[0][7] = '*';
				intersectionPattern[0][8] = '*';
				//
				intersectionPattern[1][0] = 'F';
				intersectionPattern[1][1] = '*';
				intersectionPattern[1][2] = '*';
				intersectionPattern[1][3] = 'T';
				intersectionPattern[1][4] = '*';
				intersectionPattern[1][5] = '*';
				intersectionPattern[1][6] = '*';
				intersectionPattern[1][7] = '*';
				intersectionPattern[1][8] = '*';
				//
				intersectionPattern[2][0] = 'F';
				intersectionPattern[2][1] = '*';
				intersectionPattern[2][2] = '*';
				intersectionPattern[2][3] = '*';
				intersectionPattern[2][4] = 'T';
				intersectionPattern[2][5] = '*';
				intersectionPattern[2][6] = '*';
				intersectionPattern[2][7] = '*';
				intersectionPattern[2][8] = '*';
				//

			}

			for(int i = 0; i < intersectionPattern.length ; i++)
			{
				appendRelate(expr, filter, intersectionPattern[i]);
				if(i < intersectionPattern.length - 1)
				{
					//append OR and continue
					filter.or();
				}
			}
			//			filter.appendFunction("ST_Relate");
			//			filter.openBracket();
			//			
			//			filter.closeBracket();
		}
		filter.closeBracket();
	}	
}