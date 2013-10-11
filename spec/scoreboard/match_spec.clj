(ns scoreboard.match-spec
  (:require [speclj.core            :refer :all]
            [scoreboard.spec-helper :refer [make-score]]
            [scoreboard.match       :refer :all]
            [scoreboard.game        :as game]))

(describe "scoreboard.match"
  (with won-game (make-score 15 0))
  (with in-progress-game (make-score 5 2))
  (with unplayed-game game/zero)

  (context "/new-match"
    (it "contains three zero games"
      (should= [game/zero game/zero game/zero]
               new-match)))

  (context "/current-game-index"
    (it "returns 0 as the current game index of a new match"
      (should= 0 (current-game-index new-match)))

    (it "returns 1 as the current game index"
      (should= 1 (current-game-index [@won-game @in-progress-game @unplayed-game])))

    (it "returns 2 as the current game index"
      (should= 2 (current-game-index [@won-game @won-game @in-progress-game]))))

  (context "/current-game"
    (it "returns the current game"
      (should= @in-progress-game
               (current-game [@won-game @in-progress-game @unplayed-game]))))

  (context "/score-point"
    (with in-progress-match [@won-game @unplayed-game @unplayed-game])

    (it "adds a point to player 1 in the first game"
      (should= [(make-score 1 0) game/zero game/zero]
               (score-point new-match 1)))

    (it "adds a point to player 2 in the second game"
      (should= [@won-game (make-score 0 1) @unplayed-game]
               (score-point @in-progress-match 2))))

  (context "/match-completed?"
    (it "determines match is not completed"
      (should= false
               (match-completed? new-match)))))
