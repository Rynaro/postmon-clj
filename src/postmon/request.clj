(ns postmon.request
  (:require [postmon.resource :as resource]
            [clj-http.client :as client]
            [cheshire.core :refer :all])
  (:use [slingshot.slingshot :only [throw+ try+]]))

(defn perform-request [route-string]
  (try+
    (client/get route-string)
    (catch [:status 404] {:keys [request-time headers body]}
      (throw+ {:type ::nothing-returned :message "nothing returned from postmon"}))))

(defn query [route-string]
  (-> (perform-request route-string)
      (:body)
      (parse-string true)))

(defn query-cep [cep-string]
  (query (resource/cep cep-string)))

(defn query-pack-deliver-tracker [provider code]
  (query (resource/pack-deliver-tracker provider code)))

(defn query-uf [uf-symbol]
  (query (resource/uf uf-symbol)))

(defn query-city [uf-symbol city-name]
  (query (resource/city uf-symbol city-name)))
