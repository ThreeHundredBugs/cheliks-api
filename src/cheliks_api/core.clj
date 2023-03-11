(ns cheliks-api.core
  (:require
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-params]]
            [ring.middleware.cors :refer [wrap-cors]]
            [compojure.core :refer [routes]]
            [cheliks-api.routes :refer [router]])
  (:gen-class))

(def naked-app
  (routes router))

(def app
  (-> naked-app
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete])

      wrap-keyword-params
      wrap-params
      wrap-json-params
      wrap-json-response))

(def server nil)

(defn start! []
  (.createNewFile (clojure.java.io/as-file "chels.txt"))
  (alter-var-root
    (var server)
    (fn [server]
      (if-not server
        (run-jetty app {:port 8080 :join? false})
        server))))

(defn stop!
  []
  (alter-var-root
    (var server)
    (fn [server]
      (when server
        (.stop server))
      nil)))

(defn run-server []
  (stop!)
  #_(reset! db/table-name-atom :patients)
  (start!))

(defn -main []
  (run-server))

(comment
  (start!)

  (stop!)

  )
