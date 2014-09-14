(defproject clubmanager "0.1.0-SNAPSHOT"
  :description "College club member manager"
  :url "http://www.saklee.net/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.csv "0.1.2"]]
  :main ^:skip-aot clubmanager.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
