(ns aoc-2020.1-report-repair.core
  (:gen-class))

(defn sumsTo2020
  "If a summed with any of the elements in list equal 2020, return matched number, else 0"
  [a list]
  (if (= (count list) 0)
    0
    (if (= (+ (first list) a) 2020)
      (first list)
      (sumsTo2020 a (rest list)))))

(defn find
  [a list]
  (if (= (count list) 0)
    0
    (if (= (sumsTo2020 (+ a (first list)) (rest list)) 0)
      (find a (rest list))
      (* (* (max a 1) (first list)) (sumsTo2020 (+ a (first list)) (rest list))))))

(defn find2
  [list]
  (if (= (count list) 0)
    0
    (if (= (find (first list) (rest list)) 0)
      (find2 (rest list))
      (find (first list) (rest list)))))


