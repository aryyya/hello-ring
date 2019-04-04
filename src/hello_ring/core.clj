(ns hello-ring.core)
(require '[ring.adapter.jetty :as jetty])

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello, world! From Clojure."})

(defn get-port
  [port]
  (let [default 8080]
    (try
      (cond (instance? String port) (Integer/parseInt port)
            (instance? Long port) port
            :else default)
      (catch Exception Exception
        default))))

(def server nil)

(defn start-server
  ([port]
   (alter-var-root
    #'server
    (fn [server]
      (when server (.stop server))
      (jetty/run-jetty handler {:port port :join? false})))
   (println (format "Listening for connections on port %d." port)))
  ([]
   (start-server 8080)))

(defn -main
  [& args]
  (let [port (get-port (first args))]
    (start-server port)))