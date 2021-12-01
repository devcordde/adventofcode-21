(ns day01
  (:require [clojure.string :as str]))

(defn read-numbers [input]
  (map #(Integer/valueOf %) (str/split-lines input)))

(defn count-increasing-numbers [coll]
  (->> coll
       (partition-all 2 1)
       (drop-last)
       (filter #(> (second %) (first %)))
       (count)))

(defn part-1 [input]
  (->> input
       (read-numbers)
       (count-increasing-numbers)))

(defn part-2 [input]
  (->> input
       (read-numbers)
       (partition 3 1)
       (map #(reduce + %))
       (count-increasing-numbers)))


(defn -main [& args]
  (if (not= (count args) 0)
      (do (println "Part 1:" (part-1 (slurp (first args))))
          (println "Part 2:" (part-2 (slurp (first args)))))
      (println "Please enter the input file as an argument!")))
