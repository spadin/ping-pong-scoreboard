(ns scoreboard.match
  (:require [scoreboard.game :as game]))

(def new-match
  [game/zero game/zero game/zero])

(def ^{:private true} first-index
  (comp first keep-indexed))

(defn- game-in-play [index game]
  (if-not (game/finished? game) index))

(defn current-game-index
  "Returns the current game index in the match."
  [match]
  (let [length (count match)]
    (first-index
      game-in-play
      match)))

(defn current-game
  "Returns the current game in the match."
  [match]
  (nth match (current-game-index match)))

(defn score-point
  "Score a point for a side in the current game."
  [match side]
  (let [index     (current-game-index match)
        game      (current-game match)
        new-score (game/score-point game side)]
    (assoc match index new-score)))

(defn- count-wins-for-side [match side]
  (reduce (fn [acc value]
            (if (= (game/winner value) side)
              (inc acc) acc))
          0 match))

(defn- side-won? [match side]
  (>= (count-wins-for-side match side) 2))

(defn finished?
  "Check if match is finished."
  [match]
  (or
    (side-won? match 1)
    (side-won? match 2)))

(defn winner
  "Determines the winner of the match."
  [match]
  (if (finished? match)
    (if (side-won? match 1) 1 2)
    nil))
