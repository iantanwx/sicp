(ns sicp-clj.chapter-2.42
  (:require
   [sicp-clj.chapter-2.40 :refer [flatmap]]
   [clojure.math.numeric-tower :as math]))

(def empty-board (list nil))

(defn make-position [row col] (list row col))

(defn position-row [pos] (first pos))

(defn position-col [pos] (second pos))

(defn adjoin-position
  [new-row col positions]
  (cons (make-position new-row col) positions))

(defn safe?
  [_ positions]
  (let [[proposed-queen & other-queens] positions]
    (every? (fn [pos]
              (let [r (position-row pos) c (position-col pos)]
                (and (not (= r (position-row proposed-queen)))
                     (not (= (math/abs (- (position-row proposed-queen) r))
                             (- (position-col proposed-queen) c))))))
            other-queens)))

(defn n-queens
  [board-size]
  (defn queen-cols
    [k]
    (if (= k 0)
      empty-board
      (filter
        ; only return true if the newly added postion is 'safe' boards
       #(safe? k %1)
       (flatmap
        (fn [positions]
            ; add a proposed position to the board (positions) for each row of the current column
            ; a board is a list of positions
          (map (fn [new-row]
                 (adjoin-position new-row k positions))
               (range 1 (inc board-size))))
        (queen-cols (- k 1))))))
  (queen-cols board-size))

(n-queens 4)
