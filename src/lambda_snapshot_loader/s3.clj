(ns lambda-snapshot-loader.s3

  (:use [amazonica.aws.s3]
        [amazonica.core]
        [clojure.pprint])

  (:import java.io.File))

(def bucket-name "lambda-snapshot-launcher")
(def object-name "snapshot-testing.sql")
(def download-file (java.io.File. (str "/tmp/" object-name)))

(defn get-snapshot []
  (let [obj (get-object
             :bucket-name bucket-name
             :key object-name
             :region "us-west-1")

        in (:input-stream obj)]
    (clojure.java.io/copy in download-file)))
