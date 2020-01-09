(ns postmon.request
  (:require [postmon.resource :as resource]
            [clj-http.client :as client]
            [cheshire.core :refer :all])
  (:use [slingshot.slingshot :only [throw+ try+]]))

(defn perform-request [route]
  (try+
    (client/get route)
    (catch [:status 404] {:keys [request-time headers body]}
      (println "asas")
      (throw+ {:type ::nothing-returned :message "nothing returned from postmon"}))))

(defn query-cep [cep-string]
  (-> (resource/cep cep-string)
      (perform-request)
      (:body)
      (parse-string true)))

(defn query-pack-deliver-tracker [provider code]
  (-> (resource/pack-deliver-tracker provider code)
      (perform-request)
      (:body)
      (parse-string true)))
