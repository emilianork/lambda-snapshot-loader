(ns lambda-snapshot-loader.mysql
  (require [clojure.java.jdbc :as jdbc]
           [clojure.string :as s]
           [jdbc.pool.c3p0 :as pool])

  (:import com.mchange.v2.c3p0.ComboPooledDataSource))

(def dbname (System/getenv "DB_NAME"))
(def user (System/getenv "DB_USER"))
(def password (System/getenv "DB_PASSWORD"))
(def host (System/getenv "DB_HOST"))

(def mysql-db-spec
  (pool/make-datasource-spec
   {:classname "com.mysql.jdbc.Driver"
    :subprotocol "mysql"
    :subname (str "//" host ":3306/" dbname)
    :user user
    :password password
    :initial-pool-size 3}))

(defn load-snapshot [filename]
  (let [statements (s/split
                    (s/replace
                     (s/replace (slurp filename) #"\n" "")
                     #"\t" "")
                    #";")]

    (for [query statements]
      (do
        (jdbc/execute! mysql-db-spec query)))))


