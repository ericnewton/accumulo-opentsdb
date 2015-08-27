#! /bin/sh
mvn package
jar=$(cd target ; ls *.jar)
md5sum target/$jar > ../opentsdb/third_party/accumulo-opentsdb/$jar.md5
cp target/$jar ../opentsdb/third_party/accumulo-opentsdb
