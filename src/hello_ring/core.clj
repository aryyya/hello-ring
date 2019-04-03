(ns hello-ring.core)

(use 'ring.adapter.jetty)

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello, world! From Clojure."})

(defn get-port-or-default
  [default]
  (or (Integer/parseInt (System/getenv "PORT"))
      default))

(defn -main
  []
  (let [port (get-port-or-default 8080)]
    (println (format "Listening for connections on port %d." port))
    (run-jetty handler {:port port})))
