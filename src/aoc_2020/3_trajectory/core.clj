(ns aoc-2020.3-trajectory.core)
(require '[clojure.string :as str])

(defn flip [f]
  (fn [& xs]
    (apply f (reverse xs))))

(defn split-cycle
  [grid]
  (map cycle (str/split grid #"\n")))

(defn count-trees-in-trajectory
  [nav-fn grid]
  (count
   (filter
    (partial = \#)
    (map-indexed
     nav-fn
     (split-cycle grid)))))

(def count-ordinary-trajectory (partial count-trees-in-trajectory #(nth %2 (* 3 %1))))

(defn count-all-trajectories [grid]
  (reduce
   *
   (map
    #(count-trees-in-trajectory %1 grid)
    [#(nth %2 (* 1 %1))
     #(nth %2 (* 3 %1))
     #(nth %2 (* 5 %1))
     #(nth %2 (* 7 %1))
     #(if (even? %1)
        \.
        (nth %2 (* 1 %1)))])))
