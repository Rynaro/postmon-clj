(defproject postmon "1.0.0"
  :description "Simple wrapper for Postmon API"
  :url "http://github.com/Rynaro/postmon-clj"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.10.0"]
    [clj-http "3.10.0"]
    [cheshire "5.9.0"]
    [slingshot "0.12.2"]]
  :profiles {:dev {:plugins [[lein-cloverage "1.1.2"]]}}
  :repl-options {:init-ns postmon.core}
  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :sign-releases false}]
                        ["releases" :clojars]
                        ["snapshots" :clojars]])
