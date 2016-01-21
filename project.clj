(defproject devcards-demo "0.1.0-SNAPSHOT"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [devcards "0.2.1-4"]
                 [reagent "0.6.0-alpha"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-1"]]
  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]
  :source-paths ["src" "test"]
  :cljsbuild {:builds
              [{:id "devcards"
                :source-paths ["src" "test"]
                :figwheel {:devcards true}
                :compiler {:main       "demo.core-test"
                           :asset-path "js/compiled/devcards_out"
                           :output-to  "resources/public/js/compiled/demo_devcards.js"
                           :output-dir "resources/public/js/compiled/devcards_out"
                           :source-map-timestamp true}}
               {:id "dev"
                :source-paths ["src" "test"]
                :figwheel true
                :compiler {:main       "demo.core"
                           :asset-path "js/compiled/out"
                           :output-to  "resources/public/js/compiled/demo.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}]}
  :figwheel {:css-dirs ["resources/public/css"] })
