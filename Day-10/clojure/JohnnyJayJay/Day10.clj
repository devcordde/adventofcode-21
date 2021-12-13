(ns Day10
  (:require [clojure.string :as str]))

(def brackets
  (zipmap ")]}>" "([{<"))

(defn process-bracket [stack bracket]
  (if-some [opening (brackets bracket)]
    (if (= (peek stack) opening)
      (pop stack)
      (reduced bracket))
    (conj stack bracket)))

(def checker-scores
  {\) 3
   \] 57
   \} 1197
   \> 25137})

(defn part-one [input]
  (->> input
       (remove coll?)
       (map checker-scores)
       (reduce +)))

(def completer-scores
  {\( 1
   \[ 2
   \{ 3
   \< 4})

(defn score-step [acc score]
  (+ (* 5 acc) score))

(defn part-two [input]
  (let [scores (->> input
                    (filter coll?)
                    (map (partial map completer-scores))
                    (map (partial reduce score-step 0))
                    sort
                    vec)]
    (nth scores (quot (count scores) 2))))

(let [input (->> *command-line-args*
                 first
                 slurp
                 str/split-lines
                 (map (partial reduce process-bracket ())))]
  (println (part-one input))
  (println (part-two input)))
