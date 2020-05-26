(ns sicp-clj.chapter-2.29)

(defn make-mobile
  [left right]
  (list left right))

(defn make-branch
  [length structure]
  (list length structure))

(defn left-branch
  [m]
  (first m))

(defn right-branch
  [m]
  (second m))

(defn branch-length
  [b]
  (first b))

(defn branch-structure
  [b]
  (second b))

(defn total-weight
  [m]
  (defn branch-weight
    [branch]
    (let [structure (branch-structure branch)]
      (cond
        (number? structure) structure
        :else (+ (branch-weight (left-branch structure))
                 (branch-weight (right-branch structure))))))
  (+ (branch-weight (left-branch m))
     (branch-weight (right-branch m))))

(def x (make-mobile
        (make-branch
         5 (make-mobile
            (make-branch
             4 4)
            (make-branch
             3 3)))
        (make-branch
         6 6)))

(total-weight x)

(defn torque
  [branch]
  (let [length (branch-length branch)
        structure (branch-structure branch)]
    (cond
      (number? structure) (* length structure)
      :else (* length (total-weight structure)))))

(def y (make-mobile
        (make-branch
         10 (make-mobile
             (make-branch 2 3)
             (make-branch 2 3)))
        (make-branch 12 5)))

(defn balanced?
  [m]
  (defn branch-balanced?
    [branch]
    (let [structure (branch-structure branch)]
      (cond
        (number? structure) true
        :else (balanced? structure))))
  (and (= (torque (left-branch m))
          (torque (right-branch m)))
       (branch-balanced? (left-branch m))
       (branch-balanced? (right-branch m))))
(balanced? x)
(balanced? y)

