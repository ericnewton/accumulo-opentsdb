README.md
Last Updated: 9/12/2013

This readme file gives you an overview of the steps to add the accumulo-opentsdb on top of your opentsdb install to put time series data into accumulo instead of HBase.

DEPENDENCIES
------------
Before adding this patch we assume you already have the following software installed:

* Maven 3.1.0 (See http://maven.apache.org for Download and install instructions)

* Accumulo 1.5.0 (See http://accumulo.apache.org for Download, and configuration instructions)

* Hadoop 1.2.1 (See http://hadoop.apache.org for Download, and configuration instructions)

* Zookeeper 3.4.5 (See http://zookeeper.apache.org for Download, and configuration instructions)

* Apache Thrift 0.9.0 (See http://thrift.apache.org for Download, and configuration instructions)

* Gnuplot 4.2 minimum, 4.4 recommended (Run sudo yum install gnuplot as root, or see http://www.gnuplot.info)

* Git 1.7.1 minimum (Run sudo yum install git as root, or see http://git-scm.com)

* OpenTSDB "next" git branch (See https://github.com/OpenTSDB/opentsdb/tree/next)
   Info about OpenTSDB can be found at http://opentsdb.net

* Maven 3.1.0 (See http://maven.apache.org for Download, and configuration instructions)


INSTALLATION
------------
The following steps add the accumulo-opentsdb patch on top of your existing OpenTSDB install

1) Grab the accumulo-opentsdb git repo by typing the following on command line:
      git clone git://github.com/ericnewton/accumulo-opentsdb.git

2) Build accumulo-opentsdb by typing the following commands:
      $ cd accumulo-opentsdb
      $ mvn package

3) Make an md5 file and copy the jar file into the appropriate third_party directory:
      $ md5sum target/accumulo-opentsdb-0.0.1-SNAPSHOT.jar > ../opentsdb/third_party/accumulo-opentsdb/accumulo-opentsdb-0.0.1-SNAPSHOT.jar.md5
      $ cp target/accumulo-opentsdb-0.0.1-SNAPSHOT.jar ../opentsdb/third_party/accumulo-opentsdb

NOTE: The top-level OpenTSDB directory shown here i.e., /opentsdb is for reference.  Your actual OpenTSDB top-level directory might actually be opentsdb-next, or opentsdb-2.0.0, etc. based on the OpenTSDB version you are using.


4) TBD....  Working through all the issues on how all the include.mk files, etc. defined in opentsdb.patch are added to the appropriate OpenTSDB third_party directories.  Still doing it manually right now.

5) Use the OpenTSDB build system as usual:
      $ ./build.sh

6) Create the first few tables.  Presumably using a command similar to the one under "Checkout, compile, and start OpenTSDB" described under http://opentsdb.net/getting-started.html

7) Optionally modify the configuration to specify the Accumulo username and password:
	Default:  tsd.storage.hbase.zk_quorum = localhost
	  which implies the default connection string for Accumulo:
	Accumulo: tsd.storage.hbase.zk_quorum = accumulo://root:secret@localhost:2181/test

	The format for the Accumulo URI is:
		accumulo://<username>:<password>@<zookeeper host>:<zookeeper port>/<instance ID>


       


