# JDBCLogger
View what, when, and where JDBC execution occurs.

## Purpose
Made to analyze execution of JDBC calls in proprietary code, ORM, or any technology that hides JDBC execution.
Allows you to properly assess frequency, duration, and complexity of each JDBC call.

## Quick Start
Add the following arguments to the JVM:

`-Xbootclasspath/p:<Directory>\JDBCLogger-X.X.X.jar`
`-javaagent:<Directory>\JDBCLogger-X.X.X.jar`

and then set your logger to `DEBUG` on the package `com.hkamran.jdbclogger`

### Releases:
Visit [releases](http://hkamran.info/projects/jdbclogger/releases) page
	
	
