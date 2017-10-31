(ns lambda-snapshot-loader.s3

  (:use [amazonica.aws.s3]
        [amazonica.core]
        [clojure.pprint]))

(def bucket-name "lambda-snapshot-launcher")
(def object-name "snapshot-testing.sql")

(defn get-snapshot [filename]
  (let [obj (get-object
             :bucket-name bucket-name
             :key object-name
             :region "us-west-1")

        in (:input-stream obj)]
    (clojure.java.io/copy in filename)))
