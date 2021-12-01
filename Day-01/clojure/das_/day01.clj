(ns day01
  (:require [clojure.string :as str]))

(defn read-numbers-from-file [file]
  (->> file
      (slurp)
      (str/split-lines)
      (map #(Integer/valueOf %))))

(defn count-increasing-numbers [window coll]
  (->> coll
       (partition window 1)
       (map #(reduce + %))
       (partition-all 2 1)
       (drop-last)
       (filter #(> (second %) (first %)))
       (count)))

(defn part-1 [input] (count-increasing-numbers 1 input))

(defn part-2 [input] (count-increasing-numbers 3 input))

(defn -main [& args]
  (if (not= (count args) 0)
      (let [input (read-numbers-from-file (first args))]
        (println "Part 1:" (part-1 input))
        (println "Part 2:" (part-2 input)))
      (println "Please enter the input file as an argument!")))
