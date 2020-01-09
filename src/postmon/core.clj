(ns postmon.core
  (:require [postmon.request :as request]))

(defn cep [cep-string]
  (request/query-cep cep-string))

(defn pack-deliver-tracker [provider code]
  (request/query-pack-deliver-tracker provider code))
