(defproject scoreboard "0.1.0-SNAPSHOT"
  :description "Ping Pong Scoreboard"
  :url "http://spadin.github.io/ping-pong-scoreboard"
  :license {:name "Apache Licence, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :plugins [[speclj "2.5.0"]
            [lein-marginalia "0.7.1"]]
  :test-paths ["spec/"]
  :profiles {:dev {:dependencies [[speclj "2.5.0"]]}}
  :main scoreboard.core)
