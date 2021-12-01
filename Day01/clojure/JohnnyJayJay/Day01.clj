(ns Day01
  (:require [clojure.string :as str]))

(def input (map #(Long/parseLong %) (str/split-lines (slurp (first *command-line-args*)))))

(defn part-one [input]
  (->> input
       (partition 2 1)
       (filter (partial apply <))
       count))

(defn part-two [input]
  (->> input
       (partition 3 1)
       (map (partial apply +))
       part-one))
