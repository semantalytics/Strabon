/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * 
 * Copyright (C) 2010, 2011, 2012, 2013 Pyravlos Team
 * 
 * http://www.strabon.di.uoa.gr/
 */
package org.openrdf.sail.generaldb;
 
/**
 * Checks the database product name and version to be compatible with this
 * Sesame store.
 * 
 * @author Manos Karpathiotakis <mk@di.uoa.gr>
 * 
 */
public abstract class GeneralDBProvider  {

	public abstract GeneralDBConnectionFactory createRdbmsConnectionFactory(String dbName, String dbVersion);

}
