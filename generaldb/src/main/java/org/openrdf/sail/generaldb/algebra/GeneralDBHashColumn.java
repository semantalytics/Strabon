/*
 * Copyright Aduna (http://www.aduna-software.com/) (c) 2008.
 *
 * Licensed under the Aduna BSD-style license.
 */
package org.openrdf.sail.generaldb.algebra;

import org.openrdf.query.algebra.Var;
import org.openrdf.sail.generaldb.algebra.base.GeneralDBQueryModelVisitorBase;
import org.openrdf.sail.generaldb.algebra.base.GeneralDBValueColumnBase;

/**
 * Represents the Hash value of a variable.
 * 
 * @author James Leigh
 * 
 */
public class GeneralDBHashColumn extends GeneralDBValueColumnBase {

	public GeneralDBHashColumn(GeneralDBColumnVar var) {
		super(var);
	}

	public GeneralDBHashColumn(Var var) {
		super(var);
	}

	@Override
	public <X extends Exception> void visit(GeneralDBQueryModelVisitorBase<X> visitor)
		throws X
	{
		visitor.meet(this);
	}
}
