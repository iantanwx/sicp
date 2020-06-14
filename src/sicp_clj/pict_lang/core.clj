;;; port of a subset of the picture language necessary to complete SICP exercises 2.43 - 2.52 
;;; the code in this file is inspired by and at times lifted entirely from the following:
;;; - https://github.com/globulon/sicp-in-clojure
;;; - https://github.com/karls/collage
;;; - https://github.com/IvanIvanov/JavaScript-SICP-Picture-Language
;;; Needless to say, this code is _not_ production ready.
(ns sicp-clj.pict-lang.core
  (:require [clojure.java.io :refer [as-file file]])
  (:import java.awt.Color
           java.awt.image.BufferedImage
           java.awt.geom.AffineTransform
           java.awt.geom.Line2D$Float
           java.awt.geom.Point2D$Float
           javax.imageio.ImageIO
           sicp_clj.Frame))

(defn make-vect [x y]
  "Creates a two-coordinate vector"
  [x y])
(defn xcor-vect [[x _]] x)
(defn ycor-vect [[_ y]] y)

(defn make-frame
  "Creates a frame for a painter to draw a painting into"
  [origin edge1 edge2]
  [origin edge1 edge2])
(defn origin-frame [[origin _ _]] origin)
(defn edge1-frame [[_ edge1 _]] edge1)
(defn edge2-frame [[_ _ edge2]] edge2)

(defn- add-vect
  "Vector addition"
  [v1 v2]
  (let [[x1 y1] v1
        [x2 y2] v2]
    [(+ x1 x2) (+ y1 y2)]))

(defn- sub-vect
  "Vector subtraction"
  [v1 v2]
  (let [[x1 y1] v1
        [x2 y2] v2]
    [(- x1 x2) (- y1 y2)]))

(defn- scale-vect
  "scalar * vector"
  [s v]
  (let [[x y] v]
    [(* s x) (* s y)]))

(defn frame-coord-map
  "Given a frame in coordinate system S, returns a procedure that maps a vector (x,y)
   to a vector in S."
  [frame]
  (fn [v]
    (add-vect (origin-frame frame)
              (add-vect
               (scale-vect (xcor-vect v)
                           (edge1-frame frame))
               (scale-vect (ycor-vect v)
                           (edge2-frame frame))))))

(defn make-segment
  [start end]
  [start end])

(defn start-segment
  [[start]]
  start)

(defn end-segment
  [[_ end]]
  end)

(defn connect
  [& vects]
  (loop [v (first vects)
         vs (rest vects)
         segments []]
    (if (empty? vs)
      segments
      (recur (first vs) (rest vs) (cons (make-segment v (first vs)) segments)))))

(defn- trans
  "Affine transformations are represented by
   (struct trans (xx xy yx yy x0 y0) ...))
   The point (x,y) is transformed to:
     xnew = xx*x + xy*y + x0
     ynew = yx*x + yy*y + y0
   Think of an affine transformation as a linear transformation followed by a translation."
  [xx xy yx yy x0 y0]
  [xx xy yx yy x0 y0])

(defn- frame->transformation
  [frame]
  (let [[[ox oy] [e1x e1y] [e2x e2y]] frame]
    (trans e1x e2x e1y e2y ox oy)))

(defn- frame->affine-transform
  [frame]
  (let [[e1x e2x e1y e2y ox oy] (frame->transformation frame)]
    (AffineTransform. (float-array [e1x e1y e2x e2y ox oy]))))

(def ^:private identity-frame
  (make-frame (make-vect 0.0 0.0)
              (make-vect 1.0 0.0)
              (make-vect 0.0 1.0)))

(def ^:dynamic current-bm)
(def ^:dynamic current-dc)

(defn- ^BufferedImage load-image
  "Loads an image from the file system as a BufferedImage"
  [path]
  (ImageIO/read (as-file path)))

(defn- load-painter
  "Creates a painter from a path to an image"
  [path]
  (fn [frame]
    (let [image (load-image path)
          w (.getWidth image)
          h (.getHeight image)
          trans (AffineTransform.)]
      (doto trans
        (.concatenate (AffineTransform. (float-array [w 0 0 h 0 0])))
        (.concatenate (frame->affine-transform frame)))
      (doto current-dc
        (.setTransform trans)
        (.drawImage image 0 0 1 1 nil)))))

(defn segment->painter
  [segment-list]
  (fn [frame]
    (let [w (.getWidth current-bm)
          h (.getHeight current-bm)
          trans (AffineTransform.)]
      (doto trans
        (.concatenate (AffineTransform. (float-array [w 0 0 (* -1 h) 0 h])))
        (.concatenate (frame->affine-transform frame)))
      (doseq [segment segment-list]
        (let [start (start-segment segment)
              end (end-segment segment)
              p1s (Point2D$Float. (xcor-vect start)
                                  (ycor-vect start))
              p2s (Point2D$Float. (xcor-vect end)
                                  (ycor-vect end))
              p1d (Point2D$Float.)
              p2d (Point2D$Float.)]
          (doto trans
            (.transform p1s p1d)
            (.transform p2s p2d))
          (.draw current-dc (Line2D$Float. p1d p2d)))))))

(defn paint
  [painter & {:keys [width height]
              :or {width 200 height 200}}]
  (let [bm (BufferedImage. width height BufferedImage/TYPE_INT_ARGB)
        dc (.createGraphics bm)]
    ;;; scale the entire coordinate space by 0.99.
    ;;; otherwise, top and right borders of the unit square are hidden.
    (doto dc
      (.setColor Color/white)
      (.fillRect 0 0 width height)
      (.setColor Color/black)
      (.scale 0.99 0.99))
    (binding [current-bm bm current-dc dc]
      (painter identity-frame)
      (Frame/createImageFrame "Quickview" current-bm))))

;;; Higher-order painters
;;; * transform-painter
;;; * beside
;;; * below
;;; * flip-vert
;;; * rotate-90
;;; * squish-inwards
;;; * right-split
(defn transform-painter
  "Transforms a painter according to a given origin and edges.
   Allows for composition of painters + transformations."
  [painter o e1 e2]
  (fn [frame]
    (let [m (frame-coord-map frame)
          new-origin (m o)]
      (painter (make-frame new-origin
                           (sub-vect (m e1) new-origin)
                           (sub-vect (m e2) new-origin))))))

(defn beside
  [painter1 painter2]
  (let [split-point (make-vect 0.5 0.0)
        paint-left (transform-painter painter1
                                      (make-vect 0.0 0.0)
                                      split-point
                                      (make-vect 0.0 1.0))
        paint-right (transform-painter painter2
                                       split-point
                                       (make-vect 1.0 0.0)
                                       (make-vect 0.5 1.0))]
    (fn [frame]
      (paint-left frame)
      (paint-right frame))))

(defn below
  [painter1 painter2]
  (let [split-point (make-vect 0.0 0.5)
        paint-top (transform-painter painter1
                                     split-point
                                     (make-vect 1.0 0.5)
                                     (make-vect 0.0 1.0))
        paint-bottom (transform-painter painter2
                                        (make-vect 0.0 0.0)
                                        (make-vect 1.0 0.0)
                                        split-point)]
    (fn [frame]
      (paint-top frame)
      (paint-bottom frame))))

(defn flip-vert
  [painter]
  (transform-painter painter
                     (make-vect 0.0 1.0)
                     (make-vect 1.0 1.0)
                     (make-vect 0.0 0.0)))

(defn flip-horiz
  [painter]
  (transform-painter painter
                     (make-vect 1.0 0.0)
                     (make-vect 0.0 0.0)
                     (make-vect 1.0 1.0)))
(defn rotate-90
  [painter]
  (transform-painter painter
                     (make-vect 1.0 0.0)
                     (make-vect 1.0 1.0)
                     (make-vect 0.0 0.0)))

(defn rotate-180
  [painter]
  (transform-painter painter
                     (make-vect 1.0 1.0)
                     (make-vect 0.0 1.0)
                     (make-vect 1.0 0.0)))

(defn squish-inwards
  [painter]
  (transform-painter painter
                     (make-vect 0.0 0.0)
                     (make-vect 0.65 0.35)
                     (make-vect 0.35 0.65)))

(defn right-split
  [painter n]
  (if (= n 0)
    painter
    (let [smaller (right-split painter (dec n))]
      (beside painter (below smaller smaller)))))

(defn up-split
  [painter n]
  (if (= n 0)
    painter
    (let [smaller (up-split painter (dec n))]
      (below painter (beside smaller smaller)))))

(defn corner-split
  [painter n]
  (if (= n 0)
    painter
    (let [up (up-split painter (dec n))
          right (right-split painter (dec n))]
      (let [top-left (beside up up)
            bottom-right (below right right)
            corner (corner-split painter (dec n))]
        (beside (below painter top-left)
                (below bottom-right corner))))))

(defn square-of-four
  [tl tr bl br]
  (fn [painter]
    (let [top (beside (tl painter) (tr painter))
          bottom (beside (bl painter) (br painter))]
      (below bottom top))))

(defn square-limit
  [painter n]
  (let [combine4 (square-of-four flip-horiz identity
                                 rotate-180 flip-vert)]
    (combine4 (corner-split painter n))))


;;; Predefined painters


(def will (load-painter "resources/will.gif"))
