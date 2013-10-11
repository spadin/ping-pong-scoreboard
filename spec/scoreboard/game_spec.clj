(ns scoreboard.game-spec
  (:require [speclj.core            :refer :all]
            [scoreboard.spec-helper :refer [make-score]]
            [scoreboard.game        :refer :all]))

(describe "scoreboard.game"
  (context "/zero"
    (it "is [0 0]"
      (should= [0 0] zero)))

  (context "/score-point"
    (it "adds a point to side one's score"
      (should= [1 0] (score-point zero 1)))

    (it "adds a point to side two's score"
      (should= [0 1] (score-point zero 2))))

  (context "/finished?"
    (let [test-cases [[0  0  false]
                      [11 0  true]
                      [0  11 true]
                      [10 11 false]
                      [11 10 false]
                      [12 10 true]
                      [12 12 false]]]
      (for [[side-one side-two expected] test-cases]
        (it (str "determines game is " (if (= expected false) "not ") "over when score is [" side-one " " side-two "]")
          (should= expected (finished? (make-score side-one side-two)))))))

  (context "/winner"
    (it "returns nil when game is not over"
      (should= nil (winner zero)))

    (it "returns 1 when side one has won the game"
      (should= 1 (winner (make-score 11 0))))

    (it "returns 2 when side two has won the game"
      (should= 2 (winner (make-score 0 11))))))

