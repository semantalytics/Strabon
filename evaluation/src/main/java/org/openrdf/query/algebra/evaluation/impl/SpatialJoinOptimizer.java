/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * 
 * Copyright (C) 2010, 2011, 2012, Pyravlos Team
 * 
 * http://www.strabon.di.uoa.gr/
 */
package org.openrdf.query.algebra.evaluation.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openrdf.query.BindingSet;
import org.openrdf.query.Dataset;
import org.openrdf.query.algebra.Compare;
import org.openrdf.query.algebra.Extension;
import org.openrdf.query.algebra.Filter;
import org.openrdf.query.algebra.FunctionCall;
import org.openrdf.query.algebra.Join;
import org.openrdf.query.algebra.LeftJoin;
import org.openrdf.query.algebra.MathExpr;
import org.openrdf.query.algebra.Or;
import org.openrdf.query.algebra.QueryModelNode;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.query.algebra.StatementPattern.Scope;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.ValueExpr;
import org.openrdf.query.algebra.Var;
import org.openrdf.query.algebra.evaluation.function.Function;
import org.openrdf.query.algebra.evaluation.function.FunctionRegistry;
import org.openrdf.query.algebra.evaluation.function.spatial.SpatialConstructFunc;
import org.openrdf.query.algebra.evaluation.function.spatial.SpatialMetricFunc;
import org.openrdf.query.algebra.evaluation.function.spatial.SpatialPropertyFunc;
import org.openrdf.query.algebra.evaluation.function.spatial.SpatialRelationshipFunc;
import org.openrdf.query.algebra.helpers.QueryModelVisitorBase;
import org.openrdf.query.algebra.helpers.StatementPatternCollector;

/**
 * A query optimizer that re-orders nested Joins.
 * 
 * @author Manos Karpathiotakis <mk@di.uoa.gr>
 * 
 * @author Konstantina Bereta <Konstantina.Bereta@di.uoa.gr>
 */
public class SpatialJoinOptimizer extends SpatioTemporalJoinOptimizer
//implements QueryOptimizer //Removed it consciously 
{


	//private Set<String> existingVars = new TreeSet<String>();
	/**
	 * Applies generally applicable optimizations: path expressions are sorted
	 * from more to less specific.
	 * 
	 * @param tupleExpr
	 */
	public void optimize(TupleExpr tupleExpr, Dataset dataset, BindingSet bindings, List<TupleExpr> spatialJoins) {
		tupleExpr.visit(new JoinVisitor(spatialJoins));
	}


	protected boolean isRelevantSTFunc(FunctionCall functionCall)  
	{
		Function function = FunctionRegistry.getInstance().get(functionCall.getURI());
		if(function instanceof SpatialConstructFunc)
		{
			//TODO may have to comment this part again
			//uncommented because I use this function in the case of metrics
			return true;
		}
		else if(function instanceof SpatialRelationshipFunc)
		{
			return true;
		}
		else if(function instanceof SpatialPropertyFunc) //1 argument
		{
			return true;
		}
		else if(function instanceof SpatialMetricFunc) //Arguments # depends on the function selected
		{
			return true;
		}
		return false;
	}


	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return this.getClass().getCanonicalName();
	}

}
