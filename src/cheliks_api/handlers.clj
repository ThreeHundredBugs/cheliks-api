(ns cheliks-api.handlers
  (:require
   [cheliks-api.utils :refer :all]))

(defn hello-handler [request]
  (let [json-params request]
    {:status  200
     :headers {"content-type" "application/json"}
     :body    (str request)}))

(defn create-chel-handler [request]
  (let [n     (if-let [n (get-in request [:params :n])]
               (Integer/parseInt n)
               1)
        chels (map (fn [_] (save-chel (gen-chel))) (range n))]
    {:status  200
     :headers {"content-type" "application/json"}
     :body    chels}))

(defn all-chels-handler [request]
  {:status  200
   :headers {"content-type" "application/json"}
   :body    (read-chels)})

(defn find-chel-by-name-handler [request]
  (let [name (get-in request [:params :name])]
    {:status  200
     :headers {"content-type" "application/json"}
     :body    (find-chel-by-name name)}))

(defn delete-chel-by-name-handler [request]
  (let [name (get-in request [:params :name])
        chel (delete-chel-by-name name)]
    {:status  200
     :headers {"content-type" "application/json"}
     :body    chel}))

(defn delete-random-chel-handler [request]
  (let [n    (if-let [n (get-in request [:params :n])]
               (Integer/parseInt n)
               1)
        chel (delete-random-n-chels n)]
    {:status  200
     :headers {"content-type" "application/json"}
     :body    chel}))

(defn random-chel-handler [request]
  {:status  200
   :headers {"content-type" "application/json"}
   :body    (get-random-chel)})
