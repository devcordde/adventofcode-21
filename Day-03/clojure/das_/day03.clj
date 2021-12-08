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

(defn sorted-bit-frequencies [coll]
  (mapv #(sort-by val (frequencies %)) (for [i (range (count (first coll)))] (get-column i coll))))

(defn find-bit [index preferred-bit coll]
  (if (and (= (last (first coll)) (last (last coll))) (not= (first coll) (last coll)))
    preferred-bit
    (first (nth coll index (first coll)))))

(defn get-relevant-bits [index preferred-bit frequencies]
  (mapv #(find-bit index preferred-bit %) frequencies))

(defn binary->int [string]
  (Integer/parseInt (apply str string) 2))

(defn part-1 [file-content]
  (let [input (read-input file-content)
        sorted-frequencies (sorted-bit-frequencies input)
        gamma (get-relevant-bits 1 1 sorted-frequencies)
        epsilon (get-relevant-bits 0 0 sorted-frequencies)]
    (* (binary->int gamma) (binary->int epsilon))))

(defn filter-binary [bit index coll]
  (filter #(= bit (nth % index)) coll))

(defn filter-input-by-frequency-index [index input]
  (loop [i 0
         coll input]
    (if (= i (count (first input)))
      coll
      (recur (inc i) (filter-binary (nth (get-relevant-bits index index (sorted-bit-frequencies coll)) i) i coll)))))

(defn part-2 [file-content]
  (let [input (read-input file-content)
        oxygen-generator-rating (->> input (filter-input-by-frequency-index 1) first binary->int)
        co2-scrubber-rating (->> input (filter-input-by-frequency-index 0) first binary->int)]
    (* oxygen-generator-rating co2-scrubber-rating)))

(defn -main [& args]
  (println "Part 1:" (-> args first slurp part-1))
  (println "Part 2:" (-> args first slurp part-2)))
