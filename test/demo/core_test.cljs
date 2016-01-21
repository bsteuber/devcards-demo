(ns demo.core-test
  (:require
   [reagent.core :as r]
   [demo.core     :as core])
  (:require-macros
   [devcards.core :refer [defcard-rg defcard-doc deftest]]
   [cljs.test :refer [is]]))

(defcard-rg hello
  [core/hello-component])

(deftest fixme
  (is (= 1 2)))
