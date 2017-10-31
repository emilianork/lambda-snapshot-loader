(ns lambda-snapshot-loader.handler
  (:gen-class
   :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler])

  (:require [clojure.data.json :as json]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [lambda-snapshot-loader.s3 :as s3]
            [lambda-snapshot-loader.mysql :as mysql])

  (:import java.io.File))

(def filename (java.io.File. "/tmp/snapshot.sql"))

(defn handle-event [event]
  (prn "--------------------------------------")
  (prn "Event:")
  (json/pprint event)

  (s3/get-snapshot filename)
  (mysql/load-snapshot filename)

  {:event-time (get-in event [:time])
   :source (get-in event [:source])}

  (prn "--------------------------------------"))

(defn key->keyword [key-string]
  (-> key-string
      (s/replace #"([a-z])([A-Z])" "$1-$2")
      (s/replace #"([A-Z]+)([A-Z])" "$1-$2")
      (s/lower-case)
      (keyword)))

(defn -handleRequest [this is os context]
  (let [w (io/writer os)]
    (-> (json/read (io/reader is) :key-fn key->keyword)
        (handle-event)
        (json/write w))
    (.flush w)))
