(ns Day08
  (:require [clojure.set :as sets]
            [clojure.string :as str]))

(def signals (set "abcdefg"))

(def steps (map set ["cf" "acf" "bcdf" "adg" "abfg"]))

(def numbers ["abcefg" "cf" "acdeg" "acdfg" "bcdf" "abdfg" "abdefg" "acf" "abcdefg" "abcdfg"])

(def signal->number
  (zipmap (map set numbers) (range)))

(def init (zipmap signals (repeat signals)))

(defn candidate-update [cand-map [affected cand-update]]
  (reduce-kv (fn [cand-map char _]
               (update cand-map char (if (contains? affected char) sets/intersection sets/difference) cand-update))
             cand-map
             cand-map))

(defn candidates->translation [cand-map]
  (into {} (map (juxt (comp first second) first) cand-map)))

(defn decode-digits [patterns signals]
  (let [trans (->> patterns
                   (map set)
                   (group-by count)
                   vals
                   (sort-by (comp count first))
                   (map (partial apply sets/intersection))
                   (map vector steps)
                   (reduce candidate-update init)
                   candidates->translation)]
    (->> signals
         (map (partial map trans))
         (map (comp signal->number set)))))

(defn part-one [decoded-signals]
  (->> decoded-signals
       (mapcat identity)
       (filter #{1 4 7 8})
       count))

(def dec-powers (map #(int (Math/pow 10 %)) (range)))

(defn part-two [decoded-signals]
  (->> decoded-signals
       (map reverse)
       (map (partial map * dec-powers))
       (map (partial reduce +))
       (reduce +)))

(defn parse-input-line [line]
  (map #(str/split % #" ") (str/split line #" \| ")))

(let [input (map parse-input-line (str/split-lines (slurp (first *command-line-args*))))
      decoded (map (partial apply decode-digits) input)]
  (println (part-one decoded))
  (println (part-two decoded)))
