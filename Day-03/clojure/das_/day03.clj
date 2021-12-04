(ns day03
  (:require [clojure.string :as str]))

(defn string->bits [coll]
  (mapv #(Character/digit % 2) coll))

(defn read-input [file-content]
  (->> file-content
       str/split-lines
       (mapv string->bits)))

(defn get-column [index coll]
  (mapv #(get % (int index)) coll))

(defn get-relevant-bits [index frequencies]
  (mapv #(first (nth % index)) frequencies))

(defn binary->int [string]
  (Integer/parseInt (apply str string) 2))

(defn part-1 [file-content]
  (let [input (read-input file-content)
        bits-by-index (for [i (range (count (first input)))] (get-column i input))
        sorted-frequencies (mapv #(sort-by val (frequencies %)) bits-by-index)
        gamma (get-relevant-bits 1 sorted-frequencies)
        epsilon (get-relevant-bits 0 sorted-frequencies)]
    (* (binary->int gamma) (binary->int epsilon))))

(defn -main [& args]
  (println "Part 1:" (part-1 (slurp (first args)))))
