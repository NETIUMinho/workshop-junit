Readme File
2017/04/09-13:16:12
This package contains HyperSQL v. 2.4.0

HyperSQL Database is a relational database management system and a set of tools
written in Java.
HyperSQL is also known as HSQLDB.

The file "index.html" explains the contents of this distribution and has
links to documentation and support resources.

---------------------------------------------------------------------------------------------------------------------
EXECUTE:

All tools can be run in the standard way for archived Java classes. In the following example the AWT version of the Database Manager, the hsqldb.jar is located in the directory ../lib relative to the current directory.

    java -cp ../lib/hsqldb.jar org.hsqldb.util.DatabaseManager

If hsqldb.jar is in the current directory, the command would change to:

    java -cp hsqldb.jar org.hsqldb.util.DatabaseManager

---------------------------------------------------------------------------------------------------------------------

Hsqldb Server
This is the preferred way of running a database server and the fastest one. A proprietary communications protocol is used for this mode. A command similar to those used for running tools and described above is used for running the server. The following example of the command for starting the server starts the server with one (default) database with files named "mydb.*".

    java -cp ../lib/hsqldb.jar org.hsqldb.Server -database.0 file:mydb -dbname.0 xdb
The command line argument -? can be used to get a list of available arguments.
