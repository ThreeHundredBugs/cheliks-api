(defproject cheliks-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring/ring-core "1.9.5"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [ring/ring-json "0.5.1"]
                 [ring-cors "0.1.13"]
                 [compojure "1.7.0"]
                 [clj-http "3.12.3"]
                 [cheshire "5.10.2"]
                 [eigenhombre/namejen "0.1.23"]]
  :repl-options {:init-ns cheliks-api.core}
  :main cheliks-api.core)
