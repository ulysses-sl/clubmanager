(ns clubmanager.core
  (:gen-class))

(require '[clojure.data.csv :as csv]
         '[clojure.string :as string]
         '[clojure.java.io :as io])

(defn split-whitespace [s]
  (string/split s #"\s+"))

(def inputfile "input.csv")

(def outputfile "output.csv")

(def datafile "data.csv")

(def attendancefile "attendance.csv")

(def option (-> "option.txt"
                slurp
                split-whitespace
                first))

(defn load-data [infile]
  (with-open [in-file (io/reader infile)]
    (doall
      (csv/read-csv in-file))))

(defn save-query [query-result]
  (with-open [out-file (io/writer outputfile)]
    (csv/write-csv out-file
                   query-result)))

(defn match-entry [query]
  (fn [entry]
    (every? #(or (= (first %) "") (apply = %)) (map vector query entry))))

(defn query []
  (let [data (load-data datafile)
        query (rest (load-data inputfile))]
    (-> (mapcat #(sort (filter (match-entry %) data)) query)
        distinct
        save-query)))

;(query)

(defn -main [& args]
  (case option
        "query" (query)
        "default"))
