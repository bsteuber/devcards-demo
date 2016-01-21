(ns demo.core
  (:require [clojure.string :as str]
            [reagent.core   :as r]))

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

(defn move [[x y]]
  (str "M" x " " y " "))

(defn line [[x y]]
  (str "L" x " " y " "))

(defn close []
  (str "Z"))

(defn path [points]
  (apply str
         (move (first points))
         (map line (rest points))))

(def unit-square [[0 0]
                  [0 1]
                  [1 1]
                  [1 0]
                  [0 0]])

(defn square [[x y]]
  [:path {:d (path unit-square)
          :stroke "black"
          :fill :black
          :transform (str "translate("x","y")")}])

(defn grid [{:keys [width height alive]}]
  (pane :width width
        :height height
        :contents (for [cell alive]
                    ^{:key cell}
                    (square cell))))

(defn all-points [{:keys [width height]}]
  (for [x (range width)
        y (range height)]
    [x y]))

(defn build-field [field alive?]
  (->> (all-points field)
       (filter alive?)
       (into #{})
       (assoc field :alive)))

(defn random-field [field]
  (build-field field
               (fn [_]
                 (< (rand)
                    0.3))))

(defn random-grid []
  (grid (random-field {:width 100
                       :height 100})))

(defn neighbours [[x y] {:keys [height width]}]
  (->> (for [neighb-x (->> (range (- x 1) (+ x 2))
                           (filter #(< -1 % width)))
             neighb-y (->> (range (- y 1) (+ y 2))
                           (filter #(< -1 % height)))]
         [neighb-x neighb-y])
       (remove #{[x y]})))

(defn alive-neighbour-count [cell {:keys [alive]
                                   :as field}]
  (->> (neighbours cell field)
       (filter alive)
       count))

(defn alive-in-next-state? [cell {:keys [alive]
                                   :as field}]
  (let [live-neighbour-counts (if (alive cell)
                                #{2 3}
                                #{3})]
    (-> cell
        (alive-neighbour-count field)
        live-neighbour-counts)))

(defn next-state [field]
  (build-field field #(alive-in-next-state? % field)))

(defn animated-random-grid [width height update-interval]
  (r/with-let [field (r/atom (random-field {:width width
                                          :height height}))
               interval (js/setInterval #(do (swap! field next-state)
                                           (prn "updated"))
                                      update-interval)]
    [grid @field]
    (finally
      (prn "kill")
      (js/clearInterval interval))))
