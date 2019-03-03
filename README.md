# Sample Data Generator for Elasticsearch Indices
The purpose of this project is to create a program that can drive some sample ingest into Elasticsearch.
Its multi-threaded so you can generate (depending on system resources) a fair amount of load.

This project will create various indices (dda, fintech-debit, fintech-credit) with some "sort of random" data in each message delivered to Elasticsearch.  The load (as seen in code) uses random timings to simulate _some_ variations in load.

Note: This code should not be used as a benchmarking tool for your Elasticsearch instances. You should use a tool like Rally (https://github.com/elastic/rally)

## Requirements
* Java OpenJDK
* Gradle

## Setup 
1.) Use the provided bash script to create a keystore for your java code.  
```
# ./build_keystore.bash <keystore_password> <elasticsearch_host> <elasticsearch_port>
```
2.) Update src/main/resources/config.properties
```
elasticsearch.host=
elasticsearch.port=
elasticsearch.user=
elasticsearch.password=
elasticsearch.scheme=https
keystore.location=
keystore.password=
```
2.) Compile project
```
gradle clean; gradle build fatJar
```

## Run Examples
1. ) Run debit only transactions with 2 threads (this will also generate some fraud flagged transactions)
```
java -jar build/libs/fintech-generator-all-1.0-SNAPSHOT.jar 2 debit
```
2.) Run credit only transactions with 10 threads (this will also generate some fraud flagged transactions)
```
java -jar build/libs/fintech-generator-all-1.0-SNAPSHOT.jar 10 credit
```
3.) Run dda account generation with 5 threads 
```
java -jar build/libs/fintech-generator-all-1.0-SNAPSHOT.jar 5 dda
```
4.) Run fraud-only account generation with 5 threads (will run both debit and credit fraud)
```
java -jar build/libs/fintech-generator-all-1.0-SNAPSHOT.jar 3 fraud
``` 
