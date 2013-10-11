(ns scoreboard.game-spec
  (:require [speclj.core     :refer :all]
            [scoreboard.game :refer :all]))

(defn make-score [player-one player-two]
  [player-one player-two])

(describe "scoreboard.game"
  (context "/zero"
    (it "is [0 0]"
      (should= [0 0] zero)))

  (context "/score-point"
    (it "adds a point to player one's score"
      (should= [1 0] (score-point zero 1)))

    (it "adds a point to player two's score"
      (should= [0 1] (score-point zero 2))))

  (context "/gameover?"
    (let [test-cases [[0  0  false]
                      [11 0  true]
                      [0  11 true]
                      [10 11 false]
                      [11 10 false]
                      [12 10 true]
                      [12 12 false]]]
      (for [[player-one player-two expected] test-cases]
        (it (str "determines game is " (if (= expected false) "not ") "over when score is [" player-one " " player-two "]")
          (should= expected (gameover? (make-score player-one player-two)))))))

  (context "/winner"
    (it "returns nil when game is not over"
      (should= nil (winner zero)))

    (it "returns 1 when player one has won the game"
      (should= 1 (winner (make-score 11 0))))

    (it "returns 2 when player two has won the game"
      (should= 2 (winner (make-score 0 11))))))
