(ns cheliks-api.routes
  (:require [cheliks-api.handlers :refer :all]
            [compojure.core :refer [GET POST PUT DELETE ANY defroutes]]))

(defroutes router
  (GET    "/api/v1/hello"         request (hello-handler request))
  (GET    "/api/v1/create"        request (create-chel-handler request))
  (GET    "/api/v1/all"           request (all-chels-handler request))
  (GET    "/api/v1/random"        request (random-chel-handler request))
  (GET    "/api/v1/find"          request (find-chel-by-name-handler request))
  (DELETE "/api/v1/delete/name"   request (delete-chel-by-name-handler request))
  (DELETE "/api/v1/delete/random" request (delete-random-chel-handler request))
  )
