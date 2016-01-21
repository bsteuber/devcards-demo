(ns demo.core-test
  (:require
   [reagent.core :as r]
   [demo.core     :as core])
  (:require-macros
   [devcards.core :refer [defcard-rg defcard-doc deftest]]
   [cljs.test :refer [is]]))

(defcard-rg grid
  [core/random-grid])

(defcard-rg animated
  [core/animated-random-grid 200 200 1000])

(deftest neighbours
  (let [fld {:width 10
             :height 10}]
    (is (= [[0 0]
            [0 1]
            [0 2]
            [1 0]
            [1 2]
            [2 0]
            [2 1]
            [2 2]]
           (core/neighbours [1 1] fld)))
    (is (= [[0 0]
            [0 2]
            [1 0]
            [1 1]
            [1 2]]
           (core/neighbours [0 1] fld)))
    (is (= [[8 8]
            [8 9]
            [9 8]]
           (core/neighbours [9 9] fld)))))

(deftest alive-neighbour-count
  (let [fld {:width 10
             :height 10
             :alive #{[1 1]
                      [1 2]
                      [1 3]}}]
    (is (= 1 (core/alive-neighbour-count [1 1] fld)))
    (is (= 2 (core/alive-neighbour-count [1 2] fld)))
    (is (= 3 (core/alive-neighbour-count [2 2] fld)))))

(deftest alive-in-next-state?
  (let [fld {:width 10
             :height 10
             :alive #{[1 1]
                      [1 2]
                      [1 3]}}]
    (is (core/alive-in-next-state? [1 2] fld))
    (is (core/alive-in-next-state? [2 2] fld))
    (is (not (core/alive-in-next-state? [1 3] fld)))))

(deftest next-state
  (let [fld {:width 10
             :height 10
             :alive #{[1 1]
                      [1 2]
                      [1 3]}}
        next-fld {:width 10
                  :height 10
                  :alive #{[0 2]
                           [1 2]
                           [2 2]}}]
    (is (= next-fld (core/next-state fld)))))
