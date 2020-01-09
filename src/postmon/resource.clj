(ns postmon.resource)

(def postmon-domain
  "api.postmon.com.br")

(def api-version
  "v1")

(def api-root
  (str "https://" postmon-domain "/" api-version))

(def cep-resource
  (str api-root "/cep"))

(def pack-deliver-tracker-resource
  (str api-root "/rastreio"))

(defn cep [cep-string]
  (str cep-resource "/" cep-string))

(defn pack-deliver-tracker [provider code]
  (str pack-deliver-tracker-resource "/" provider "/" code))
