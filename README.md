# Cassandra-Scala-Activator-Template

This is an activator template that is designed to demonstrate how you can connect to cassandra and perform CRUD operations from an activator-scala project.

## Steps to run this Project

1) Install Cassandra : You can install Cassandra on Ubuntu from [Installation Guide - Cassandra](http://cassandra.apache.org/download/)

2) Start Cassandra Query Language Shell using command cqlsh to connect to a test cluster in cassandra which is started by default at localhost on 9042 port.
 
3) Create Keyspace and Table and insert-record (optional) into it using below commands :
 
 ```$xslt

CREATE KEYSPACE first_keyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};

use first_keyspace ;

create table first_table(id int PRIMARY KEY, name text, age int);

insert into first_keyspace.first_table(id,age,name) values(1,23,'sangeeta');

```
 
4) Clone project and Run

```$xslt
git clone git@github.com:SangeetaGulia/activator-cassandra-scala-template.git
```
 
5) Run Project

```$xslt
cd activator-cassandra-scala-template
bin/activator run
```

For any issue please raise a ticket @ [Github Issue](https://github.com/SangeetaGulia/activator-cassandra-scala-template/issues)