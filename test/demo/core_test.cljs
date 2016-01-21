(ns demo.core-test
  (:require
   [reagent.core :as r]
   [demo.core     :as core])
  (:require-macros
   [devcards.core :refer [defcard-rg defcard-doc deftest]]
   [cljs.test :refer [is]]))

(defcard-rg pane
  [core/pane
   :bg-color :yellow
   :height 500
   :width 500
   :title "Test Bild"
   :contents [(core/path :data [(core/move 0 0)
                                (core/line 100 100)
                                (core/line 0 200)
                                (core/close)]
                         :transform "translate(0,-1)rotate(18)")]])
