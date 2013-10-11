(ns scoreboard.match
  (:require [scoreboard.game :as game]))

(def new-match
  [game/zero game/zero game/zero])

(def ^{:private true} first-index
  (comp first keep-indexed))

(defn- game-in-play [index game]
  (if-not (game/gameover? game) index))

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
  "Score a point for a player in the current game"
  [match player]
  (let [index     (current-game-index match)
        game      (current-game match)
        new-score (game/score-point game player)]
    (assoc match index new-score)))

(defn match-completed?
  "Check if match is finished"
  [match]
  false)
