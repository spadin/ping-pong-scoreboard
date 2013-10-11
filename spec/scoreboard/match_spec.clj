(ns scoreboard.match-spec
  (:require [speclj.core            :refer :all]
            [scoreboard.spec-helper :refer [make-score make-match]]
            [scoreboard.match       :refer :all]
            [scoreboard.game        :as game]))

(describe "scoreboard.match"
  (with won-game (make-score 15 0))
  (with in-progress-game (make-score 5 2))
  (with unplayed-game game/zero)
  (with side-1-win (make-score 15 0))
  (with side-2-win (make-score 0 15))
  (with side-1-winning-match (make-match @side-1-win @side-1-win @unplayed-game))
  (with side-2-winning-match (make-match @side-2-win @side-2-win @unplayed-game))

  (context "/new-match"
    (it "contains three zero games"
      (should= (make-match game/zero game/zero game/zero)
               new-match)))

  (context "/current-game-index"
    (it "returns 0 as the current game index of a new match"
      (should= 0 (current-game-index new-match)))

    (it "returns 1 as the current game index"
      (should= 1 (current-game-index (make-match @won-game @in-progress-game @unplayed-game))))

    (it "returns 2 as the current game index"
      (should= 2 (current-game-index (make-match @won-game @won-game @in-progress-game)))))

  (context "/current-game"
    (it "returns the current game"
      (should= @in-progress-game
               (current-game (make-match @won-game @in-progress-game @unplayed-game)))))

  (context "/score-point"
    (with in-progress-match (make-match @won-game @unplayed-game @unplayed-game))

    (it "adds a point to side 1 in the first game"
      (should= (make-match (make-score 1 0) game/zero game/zero)
               (score-point new-match 1)))

    (it "adds a point to side 2 in the second game"
      (should= (make-match @won-game (make-score 0 1) @unplayed-game)
               (score-point @in-progress-match 2))))

  (context "/finished?"
    (it "determines a new match is not finished"
      (should= false
               (finished? new-match)))

    (it "determines match is completed when side 1 wins two games"
      (should= true
              (finished? @side-1-winning-match)))

    (it "determines match is completed when side 2 wins two games"
      (should= true
              (finished? @side-2-winning-match))))

  (context "/winner"
    (it "determines nobody has won a new match"
      (should= nil
               (winner new-match)))

    (it "determines side 1 has won the match"
      (should= 1
               (winner @side-1-winning-match)))

    (it "determines side 2 has won the match"
      (should= 2
               (winner @side-2-winning-match)))))
