(ns aoc-2020.2-password.core)

(require '[clojure.string :as str])

(defn parse-int [s]
  (Integer. (re-find  #"\d+" s)))

(defn count-occurences [char string]
  (count (filter (partial = (first  char)) string)))

(defn count-occurences-threaded
  [char string]
  (->> string
       (filter (partial = (first char)))
       (count)))

(count-occurences "a" "aa")
(def string "abocoaaa")
(def char "a")
(def pos "1")

(defn is-valid-1 [min-chars max-chars char string]
  (let [char-count (count-occurences char string)]
    (<= (parse-int min-chars) char-count (parse-int max-chars))))

(defn char-at [char string pos] (= (first char) (nth string (dec (parse-int pos)))))

(defn is-valid-2 [pos1 pos2 char string]
  (= (count (filter true?
                    (map (partial char-at char string)
                         [pos1 pos2])))
     1))

(defn check-password-string [checker password] (apply checker (rest (re-matches #"(\d+)-(\d+) (\S+): (\S+)" password))))

(defn count-passwords [checker password-string] (count (filter (partial check-password-string checker) (str/split password-string #"\n"))))

(def sled-rental-checker (partial count-passwords is-valid-1))
(def tobbogan-checker (partial count-passwords is-valid-2))
