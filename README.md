# lambda-snapshot-loader

A Clojure Lambda that downloads from S3 a SQL snapshot, which is then 
execute into the aurora database call `testing`.

## Usage

#### Build using 

```$ lein uberjar```

The file to be upload is: ```target/lambda-snapshot-loader-0.1.0-SNAPSHOT-standalone.jar```
