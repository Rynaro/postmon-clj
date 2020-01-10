(ns postmon.resource)

(def postmon-domain
  "api.postmon.com.br")

(def api-version
  "v1")

(def api-root
  (str "https://" postmon-domain "/" api-version))

(defn cep [cep-string]
  (str api-root "/cep/" cep-string))

(defn pack-deliver-tracker [provider code]
  (str api-root "/rastreio/" provider "/" code))

(defn uf [uf-symbol]
  (str api-root "/uf/" uf-symbol))

(defn city [uf-symbol city-name]
  (str api-root "/cidade/" uf-symbol "/" city-name ))
