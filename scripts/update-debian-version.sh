#!/bin/bash
#
# Should be run without arguments from inside the endpoint-exec directory.
#

VERSION=`grep version pom.xml | head -1 | sed 's/\(.*\)<version>\(.*\)<\/version>/\2/'`

sed -i "s/^Version.*$/Version: ${VERSION}/" ./src/deb/control/control

