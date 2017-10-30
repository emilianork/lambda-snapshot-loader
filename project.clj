(defproject lambda-snapshot-loader "0.1.0-SNAPSHOT"
  :description "Lambda sanpshot loader example"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]

                 [amazonica "0.3.113" :exclusions [com.amazonaws/aws-java-sdk
                                                   com.amazonaws/amazon-kinesis-client]]

                 [com.amazonaws/aws-java-sdk-core "1.10.49"]
                 [com.amazonaws/aws-java-sdk-s3 "1.10.49"]
                 [com.amazonaws/aws-lambda-java-events "2.0.1"]
                 [com.amazonaws/aws-lambda-java-core "1.1.0"]

                 [org.clojure/java.jdbc "0.0.6"]
                 [mysql/mysql-connector-java "5.1.6"]]

  :java-source-paths ["src/lambda_snapshot_loader"]
  :aot :all)
