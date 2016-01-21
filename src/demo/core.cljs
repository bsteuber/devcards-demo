(ns demo.core
  (:require [clojure.string :as str]))

(defn pane [& {:keys [width
                      height
                      contents
                      bg-color
                      title]
               :or {width 300
                    height 200
                    bg-color :white
                    title ""}}]
  (into [:svg {:width  width
               :height height
               :xmlns "http://www.w3.org/2000/svg"
               :xmlns:xlink "http://www.w3.org/1999/xlink"
               :title title
               :style {:background-color bg-color}}]
        contents))

(defn path [& {:keys [id
                      data
                      transform
                      stroke]
               :or {stroke :black}}]
  [:path {:id id
          :d (str/join data)
          :transform transform
          :stroke stroke}])

(defn move [x y]
  (str "M" x " " y " "))

(defn line [x y]
  (str "L" x " " y " "))

(defn close []
  (str "Z"))
