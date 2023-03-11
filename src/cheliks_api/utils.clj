(ns cheliks-api.utils
  (:require [namejen.names :refer [generic-name]]
            [clojure.edn]))

(defn gen-chel []
  {:name (generic-name)
   :gender (rand-nth [:male :female])
   :skills {:intellect      (inc (rand-int 10))
            :reflex         (inc (rand-int 10))
            :technique      (inc (rand-int 10))
            :cool           (inc (rand-int 10))
            :attractiveness (inc (rand-int 10))
            :luck           (inc (rand-int 10))
            :move           (inc (rand-int 10))
            :body           (inc (rand-int 10))
            :empathy        (inc (rand-int 10))}})

(defn read-chels
  ([]
   (read-chels "chels.txt"))
  ([file]
    (clojure.edn/read-string (slurp file))))

(defn save-chel
  ([chel]
   (save-chel "chels.txt" chel))
  ([file chel]
   (let [all-chels (read-chels file)]
     (spit file (conj all-chels chel))
     chel)))

(defn find-chel-by-name
  ([name]
   (find-chel-by-name "chels.txt" name))
  ([file name]
   (let [chel (first (filter #(= name (:name %)) (read-chels file)))]
     (if chel
       chel
       "Chel not found"))))

(defn delete-chel-by-name
  ([name]
   (delete-chel-by-name "chels.txt" name))
  ([file name]
   (let [chels (read-chels file)
         chel (first (filter #(= name (:name %)) chels))
         new-chels (seq (remove #(= name (:name %)) chels))]
     (spit file new-chels)
     chel)))

(defn delete-random-chel
  ([]
   (delete-random-chel "chels.txt"))
  ([file]
   (let [chels (read-chels file)
         chel (rand-nth chels)
         new-chels (seq (remove #(= (:name chel) (:name %)) chels))]
     (spit file new-chels)
     chel)))

(defn delete-random-n-chels
  ([n]
   (delete-random-n-chels "chels.txt" n))
  ([file n]
   (remove nil?
           (for [_ (range n)]
             (delete-random-chel file)))))

(defn get-random-chel
  ([]
   (get-random-chel "chels.txt"))
  ([file]
   (rand-nth (read-chels file))))
