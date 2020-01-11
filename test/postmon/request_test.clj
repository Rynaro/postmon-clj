(ns postmon.request-test
  (:require [clojure.test :refer :all]
            [clj-http.client :as client]
            [slingshot.test]
            [postmon.request :refer :all]))

(deftest successfully-perform-request
  (with-redefs [client/get (fn [url] {:status 200})]
    (is (= {:status 200} (perform-request "https://api.postmon.com.br/v1/cep/02013010")))))

(deftest not-found-perform-request
  (with-redefs [client/get (fn [url] (throw (ex-info "404 error" {:status 404})))]
    (is
      (thrown+?
        (= :postmon.request/nothing-returned (:type %))
          (perform-request "https://api.postmon.com.br/v1/cep/11111111")))))

(deftest successfully-query
  (with-redefs [client/get (fn [url] {:status 200 :body "{\"cep\": \"02013010\"}"})]
    (is (= {:cep "02013010"} (query "https://api.postmon.com.br/v1/cep/02013010")))))

(def expected-cep-response
  {
    :bairro "Santana" :cidade "São Paulo"
    :logradouro "Rua Henrique Bernardelli"
    :estado_info {:codigo_ibge "35"}
    :cep "02013010"
    :cidade_info {:codigo_ibge "3550308"}
    :estado "SP"})

(deftest existent-cep-query
  (with-redefs [client/get (fn [url] {:status 200 :body expected-cep-response})]
    (let [cep-response (perform-request "https://api.postmon.com.br/v1/cep/02013010")]
      (is (= 200 (:status cep-response)))
      (is (= "Santana" (get-in cep-response [:body :bairro])))
      (is (= "Rua Henrique Bernardelli" (get-in cep-response [:body :logradouro])))
      (is (= "São Paulo" (get-in cep-response [:body :cidade])))
      (is (= "SP" (get-in cep-response [:body :estado])))
      (is (= "02013010" (get-in cep-response [:body :cep])))
      (is (= "35" (get-in cep-response [:body :estado_info :codigo_ibge])))
      (is (= "3550308" (get-in cep-response [:body :cidade_info :codigo_ibge]))))))

(def expected-city-response
  {:area_km2 "402,871" :codigo_ibge "3526704"})

(deftest existent-city-query
  (with-redefs [client/get (fn [url] {:status 200 :body expected-city-response})]
    (let [city-response (perform-request "https://api.postmon.com.br/v1/cidade/SP/Leme")]
      (is (= 200 (:status city-response)))
      (is (= "402,871" (get-in city-response [:body :area_km2])))
      (is (= "3526704" (get-in city-response [:body :codigo_ibge]))))))

(def expected-uf-response
  {:area_km2 "248.221,996", :codigo_ibge "35", :nome "São Paulo"})

(deftest existent-uf-query
  (with-redefs [client/get (fn [url] {:status 200 :body expected-uf-response})]
    (let [uf-response (perform-request "https://api.postmon.com.br/v1/uf/SP")]
      (is (= 200 (:status uf-response)))
      (is (= "248.221,996" (get-in uf-response [:body :area_km2])))
      (is (= "35" (get-in uf-response [:body :codigo_ibge])))
      (is (= "São Paulo" (get-in uf-response [:body :nome]))))))


(def expected-pack-deliver-tracker-response
  {
    :codigo "mocked"
    :service "ect"
    :historico {
                :local "AG Liberdade - Sao Paulo/SP"
                :data "02/01/2020 13:23:03"
                :situacao "Postado"}})

(deftest existent-pack-deliver-tracker-query
  (with-redefs [client/get (fn [url] {:status 200 :body expected-pack-deliver-tracker-response})]
    (let [pack-tracker-response (perform-request "https://api.postmon.com.br/v1/rastreio/ect/mocked")]
      (is (= 200 (:status pack-tracker-response)))
      (is (= "mocked" (get-in pack-tracker-response [:body :codigo])))
      (is (= "ect" (get-in pack-tracker-response [:body :service])))
      (is (= "AG Liberdade - Sao Paulo/SP" (get-in pack-tracker-response [:body :historico :local])))
      (is (= "02/01/2020 13:23:03" (get-in pack-tracker-response [:body :historico :data])))
      (is (= "Postado" (get-in pack-tracker-response [:body :historico :situacao]))))))
