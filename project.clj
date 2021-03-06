(defproject sicp-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [org.clojure/tools.namespace "1.0.0"]
                 [org.clojure/core.match "1.0.0"]
                 [cider/cider-nrepl "0.24.0"]]
  :java-source-paths ["src/sicp_clj/java"]
  :main ^:skip-aot sicp-clj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
