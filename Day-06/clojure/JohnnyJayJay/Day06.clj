(ns Day06
  (:require [clojure.string :as str]))

(def fish
  (lazy-cat (repeat 7 1) [2 2] (map + fish (drop 2 fish))))


(defn fish-count [days init-timer]
  (nth fish (+ days (- 6 init-timer))))

(defn solve [days timers]
  (reduce + (map (partial fish-count days) timers)))

(defn parse [file]
  (map #(Long/parseLong %) (-> file slurp str/trim (str/split #","))))

(let [input (parse (first *command-line-args*))]
    (println (solve 80 input))
    (println (solve 256 input)))
