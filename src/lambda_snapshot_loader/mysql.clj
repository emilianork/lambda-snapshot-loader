(ns lambda-snapshot-loader.mysql
  (require [clojure.java.jdbc :as jdbc]
           [clojure.string :as s]))

(def dbname (System/getenv "DB_NAME"))
(def user (System/getenv "DB_USER"))
(def password (System/getenv "DB_PASSWORD"))
(def host (System/getenv "DB_HOST"))

(def mysql-db-spec {:dbtype "mysql"
                    :dbname dbname
                    :user user
                    :password password
                    :host host})

(defn load-snapshot [filename]
  (let [statements (s/split
                    (s/replace
                     (s/replace (slurp filename) #"\n" "")
                     #"\t" "")
                    #";")]

    (jdbc/db-do-commands mysql-db-spec statements)))



