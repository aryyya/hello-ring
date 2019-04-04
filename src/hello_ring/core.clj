(ns hello-ring.core)
(use 'ring.adapter.jetty)

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello, world! From Clojure."})

(defn get-port
  [port]
  (cond (instance? String port) (Integer/parseInt port)
        (instance? Long port) port
        :else 8080))

(defn -main
  [& args]
  (let [port (get-port (first args))]
    (println (format "Listening for connections on port %d." port))
    (run-jetty handler {:port port})))
