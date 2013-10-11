(ns scoreboard.game)

(defn- player-one-score [score]
  (nth score 0))

(defn- player-two-score [score]
  (nth score 1))

(defn- score-difference [score]
  (Math/abs (- (player-one-score score) (player-two-score score))))

(defn- highest-score [score]
  (max (player-one-score score) (player-two-score score)))

(defn- winning-player [score]
  (if (= (highest-score score) (player-one-score score)) 1 2))

(def zero
  "Returns the base score for a game [0 0]."
  [0 0])

(defn score-point
  "Add a point to a player"
  [score player]
  (let [index  (dec player)
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
    (winning-player score)))
