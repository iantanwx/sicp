(ns sicp-clj.chapter-2.2)

(defn make-point
  "Creates a point of x,y coordinates"
  [x y]
  [x y])
(defn x-point
  "Evaluates the x coordinate of a point"
  [point]
  (first point))
(defn y-point
  "Evaluates the y coordinate of a point"
  [point]
  (second point))
(defn print-point
  [p]
  (println (format "(%s,%s)" (x-point p) (y-point p))))

(defn make-segment
  "Creates a line segment on a plane from point a to b"
  [a b]
  [a b])
(defn start-segment
  "Evaluates the starting point of the segment"
  [s]
  (first s))
(defn end-segment
  "Evaluates the end point of the segment"
  [s]
  (second s))
(defn midpoint-segment
  "Gets the midpoint of the segment"
  [s]
  (let [start (start-segment s) end (end-segment s)]
    (make-point (/ (+ (x-point start) (x-point end)) 2.0)
                (/ (+ (y-point start) (y-point end)) 2.0))))

(def start (make-point 2 3))
(def end (make-point 10 15))
(def line (make-segment start end))
(print-point (midpoint-segment line))
