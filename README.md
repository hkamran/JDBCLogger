# JDBCLogger
View when JDBC executes queries and where it makes those calls.

##Purpose
Made to analyze execution of JDBC calls in proprietary code, ORM, or any technology that hides JDBC execution.
Allows you to properly assess frequency, duration, and complexity of each JDBC call.

##Quick Start
Add the following arguments to the JVM:

'-Xbootclasspath/p:<Directory>\JDBCLogger-X.X.X.jar' 
'-javaagent:<Directory>\JDBCLogger-X.X.X.jar'

##Releases
