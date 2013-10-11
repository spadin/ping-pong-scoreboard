(ns scoreboard.game)

(defn- side-one-score [score]
  (nth score 0))

(defn- side-two-score [score]
  (nth score 1))

(defn- score-difference [score]
  (Math/abs (- (side-one-score score) (side-two-score score))))

(defn- highest-score [score]
  (max (side-one-score score) (side-two-score score)))

(defn- winning-side [score]
  (if (= (highest-score score) (side-one-score score)) 1 2))

(def zero
  "Returns the base score for a game [0 0]."
  [0 0])

(defn score-point
  "Add a point to a side"
  [score side]
  (let [index  (dec side)
        points (nth score index)]
    (assoc score index (inc points))))

(defn finished?
  "Determines if the game is over based on the score."
  [score]
  (cond
    (<= (score-difference score)  1) false
    (>= (highest-score    score) 11) true
    :else false))

(defn winner
  "Determines the winner of the game."
  [score]
  (if (finished? score)
    (winning-side score)))
