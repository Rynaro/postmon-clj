(ns postmon.resource-test
  (:require [clojure.test :refer :all]
            [postmon.resource :refer :all]))

(deftest postmon-domain-checkage
  (is (= postmon-domain "api.postmon.com.br")))

(deftest api-version-checkage
  (is (= api-version "v1")))

(deftest cep-resource-string-building
  (let [expected-string "https://api.postmon.com.br/v1/cep/01211020"]
    (is (= (cep "01211020") expected-string))))

(deftest pack-deliver-tracker-resource-string-building
  (let [expected-string "https://api.postmon.com.br/v1/rastreio/jadlog/a1b2c3"]
    (is (= (pack-deliver-tracker "jadlog" "a1b2c3") expected-string))))

(deftest UF-resource-string-building
  (let [expected-string "https://api.postmon.com.br/v1/uf/SP"]
    (is (= (uf "SP") expected-string))))

(deftest city-resource-string-building
  (let [expected-string "https://api.postmon.com.br/v1/cidade/SP/Leme"]
    (is (= (city "SP" "Leme") expected-string))))
