# JDBCLogger
View what, when, and where JDBC execution occurs.

### Purpose
Made so users can analyze the execution of JDBC calls in proprietary code, ORM, or any technology that hides JDBC functions.
This allows you to properly assess the frequency, duration, and complexity of each JDBC call.

# Arguments
Node: These are set via system properties.

- printCallStack [true/false] `default: true`

### Quick Start
Add the following arguments to the JVM:

- `-Xbootclasspath/p:<Directory>\JDBCLogger-X.X.X.jar`
- `-javaagent:<Directory>\JDBCLogger-X.X.X.jar`

and then set your logger to `DEBUG` on the package `com.hkamran.jdbclogger`

### Releases:
Visit [releases](http://hkamran.info/projects/jdbclogger/releases) page
	
	
