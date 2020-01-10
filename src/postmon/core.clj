(ns postmon.core
  (:require [postmon.request :as request]))

(defn cep [cep-string]
  (request/query-cep cep-string))

(defn pack-deliver-tracker [provider code]
  (request/query-pack-deliver-tracker provider code))

(defn uf-data [uf-symbol]
  (request/query-uf uf-symbol))

(defn city-data [uf-symbol city-name]
  (request/query-city uf-symbol city-name))
