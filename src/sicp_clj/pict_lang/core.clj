;;; port of a subset of the picture language necessary to complete SICP exercises 2.43 - 2.52 
;;; the code in this file is inspired by and at times lifted entirely from the following:
;;; - https://github.com/globulon/sicp-in-clojure
;;; - https://github.com/karls/collage
;;; - https://github.com/IvanIvanov/JavaScript-SICP-Picture-Language
;;; Needless to say, this code is _not_ production ready.
(ns sicp-clj.pict-lang.core
  (:require [clojure.java.io :refer [as-file file]]
            [clojure.math.numeric-tower :as math])
  (:import java.awt.image.BufferedImage
           java.awt.image.Raster
           java.awt.geom.AffineTransform
           javax.imageio.ImageIO
           sicp_clj.Frame))

(defn make-vect [x y]
  "Creates a two-coordinate vector"
  [x y])
(defn x-vect [[x _]] x)
(defn y-vect [[_ y]] y)

(defn make-frame
  "Creates a frame for a painter to draw a painting into"
  [origin edge1 edge2]
  [origin edge1 edge2])
(defn origin-frame [[origin _ _]] origin)
(defn edge1-frame [[_ edge1 _]] edge1)
(defn edge2-frame [[_ _ edge2]] edge2)

(defn trans
  "Affine transformations are represented by
   (struct trans (xx xy yx yy x0 y0) ...))
   The point (x,y) is transformed to:
     xnew = xx*x + xy*y + x0
     ynew = yx*x + yy*y + y0
   Think of an affine transformation as a linear transformation followed by a translation."
  [xx xy yx yy x0 y0]
  [xx xy yx yy x0 y0])

(defn add-vect
  "Vector addition"
  [v1 v2]
  (let [[x1 y1] v1
        [x2 y2] v2]
    [(+ x1 x2) (+ y1 y2)]))

(defn sub-vect
  "Vector subtraction"
  [v1 v2]
  (let [[x1 y1] v1
        [x2 y2] v2]
    [(- x1 x2) (- y1 y2)]))

(defn scale-vect
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
               (scale-vect (x-vect v)
                           (edge1-frame frame))
               (scale-vect (y-vect v)
                           (edge2-frame frame))))))

; 1.0 0.0 0.0 0.0 1.0 0.0 -> [[1.0, 0.0, 1.0], [0.0, 0.0, 0.0]]
; form 0x1e9f4e49 AffineTransform[[1.0, 0.0, 1.0], [0.0, 0.0, 0.0]]]
(defn frame->transformation
  [frame]
  (let [[[ox oy] [e1x e1y] [e2x e2y]] frame]
    (trans e1x e2x e1y e2y ox oy)))

(defn frame->affine-transform
  [frame w h]
  (let [[e1x e2x e1y e2y ox oy] (frame->transformation frame)]
    (AffineTransform. (float-array [e1x e1y e2x e2y (* w ox) (* h oy)]))))

(def ^:dynamic current-bm)
(def ^:dynamic current-dc)

(defn ^BufferedImage load-image
  "Loads an image from the file system as a BufferedImage"
  [path]
  (ImageIO/read (as-file path)))

(defn load-painter
  "Creates a painter from a path to an image"
  [path]
  (let [image (load-image path)
        w (.getWidth image)
        h (.getHeight image)]
    (fn [frame]
      (let [transform (frame->affine-transform frame w h)]
        (doto current-dc
          (.drawImage image transform nil))))))

(defn paint
  [painter & {:keys [width height] :or {width 200 height 200}}]
  (let [bm (BufferedImage. width height BufferedImage/TYPE_INT_ARGB)
        dc (.createGraphics bm)]
    (binding [current-bm bm current-dc dc]
      (.fill3DRect dc 0 0 200 200 false)
      (painter (make-frame (make-vect 0.0 0.0)
                           (make-vect 1.0 0.0)
                           (make-vect 0.0 1.0)))
      (Frame/createImageFrame "Quickview" current-bm))))

;;; Higher-order painters
;;; Only contains implementations for
;;; * beside
;;; * below
;;; * flip-vert
;;; * up-split
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

;;; Predefined painters
(def will (load-painter "resources/will.gif"))

(paint (beside will will))
; (paint will)
