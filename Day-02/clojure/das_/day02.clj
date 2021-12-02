(ns day02
  (:require [clojure.string :as str]))

(defn read-input-1 [input]
  (->> input
       (str/split-lines)
       (map #(str/split % #" "))
       (map #(list (first %) (Integer/parseInt (second %))))
       (group-by first)))

(defn sum-map [key map]
  (->> (get map key)
       (clojure.core/map second)
       (reduce +)))

(defn part-1 [input]
  (let [actions (read-input-1 input)
        horizontal (sum-map "forward" actions)
        vertical-plus (sum-map "down" actions)
        vertical-minus (sum-map "up" actions)
        vertical (- vertical-plus vertical-minus)]
    (* horizontal vertical)))

(defn read-input-2 [input]
  (->> input
       (str/split-lines)
       (map #(str/split % #" "))
       (map #(list (first %) (Integer/parseInt (second %))))))

(defn part-2 [input]
  (loop [inputs (read-input-2 input)
         horizontal 0
         vertical 0
         aim 0]
    (if (empty? inputs)
      (* horizontal vertical)
      (let [value (second (first inputs))]
        (condp = (first (first inputs))
          "up" (recur (rest inputs) horizontal vertical (- aim value))
          "down" (recur (rest inputs) horizontal vertical (+ aim value))
          "forward" (recur (rest inputs) (+ horizontal value) (+ vertical (* aim value)) aim))))))


(defn -main [& args]
  (println (part-1 (slurp (first args))))
  (println (part-2 (slurp (first args)))))
