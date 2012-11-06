/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright (C) 2012, Pyravlos Team
 *
 * http://www.strabon.di.uoa.gr/
 */
package org.openrdf.query.algebra.evaluation.function.temporal.stsparql.construct;

import org.openrdf.query.algebra.evaluation.function.temporal.stsparql.relation.TemporalConstants;
import org.openrdf.query.algebra.evaluation.function.temporal.stsparql.relation.TemporalRelationFunc;

/**
 * @author Konstantina Bereta <Konstantina.Bereta@di.uoa.gr>
 *
 */
public class PeriodIntersectionFunc  extends TemporalConstructFunc {
	
    
	@Override
	public String getURI() {
	
		return TemporalConstants.periodIntersection;
	}

	//there is no operator assigned to this function
	@Override
	public String getOperator() {
		return null;
	}

	
	@Override
	public String getPostgresFunction() {
		return "period_intersect";
	}

}