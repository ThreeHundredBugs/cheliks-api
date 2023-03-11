(ns cheliks-api.utils-test
  (:require [clojure.test :as t]
            [cheliks-api.utils :as sut]))

(def test-file "test.txt")

(defn fixture [f]
  (spit test-file "")
  (f)
  (clojure.java.io/delete-file test-file))

(t/use-fixtures :each fixture)

(t/deftest test-save-and-read
  (let [chel (sut/gen-chel)]
    (sut/save-chel test-file chel)
    (let [all-chels (sut/read-chels test-file)]
      (t/is (= 1 (count all-chels)))
      (t/is (= chel (first all-chels))))))

(t/deftest test-save-and-read-multiple
  (let [chel1 (sut/gen-chel)
        chel2 (sut/gen-chel)]
    (sut/save-chel test-file chel1)
    (sut/save-chel test-file chel2)
    (let [all-chels (sut/read-chels test-file)]
      (t/is (= 2 (count all-chels)))
      (t/is (= chel1 (second all-chels)))
      (t/is (= chel2 (first all-chels))))))

(t/deftest test-find-by-name
  (let [chel1 (sut/gen-chel)
        chel2 (sut/gen-chel)]
    (sut/save-chel test-file chel1)
    (sut/save-chel test-file chel2)
    (let [found-chel (sut/find-chel-by-name test-file (:name chel1))]
      (t/is (= chel1 found-chel)))))

(t/deftest test-find-by-name-not-found
    (let [chel1 (sut/gen-chel)
            chel2 (sut/gen-chel)]
        (sut/save-chel test-file chel1)
        (sut/save-chel test-file chel2)
        (let [found-chel (sut/find-chel-by-name test-file "not-found")]
        (t/is (= "Chel not found" found-chel)))))

(t/deftest test-delete-by-name
  (let [chel1 (sut/gen-chel)
        chel2 (sut/gen-chel)]
    (sut/save-chel test-file chel1)
    (sut/save-chel test-file chel2)
    (sut/delete-chel-by-name test-file (:name chel1))
    (let [all-chels (sut/read-chels test-file)]
      (t/is (= 1 (count all-chels)))
      (t/is (= chel2 (first all-chels))))))

(t/deftest test-delete-by-name-not-found
    (let [chel1 (sut/gen-chel)
            chel2 (sut/gen-chel)]
        (sut/save-chel test-file chel1)
        (sut/save-chel test-file chel2)
        (sut/delete-chel-by-name test-file "not-found")
        (let [all-chels (sut/read-chels test-file)]
        (t/is (= 2 (count all-chels)))
        (t/is (= chel1 (second all-chels)))
        (t/is (= chel2 (first all-chels))))))

(t/deftest test-delete-random-chel
  (let [chel1 (sut/gen-chel)
        chel2 (sut/gen-chel)]
    (sut/save-chel test-file chel1)
    (sut/save-chel test-file chel2)
    (sut/delete-random-chel test-file)
    (let [all-chels (sut/read-chels test-file)]
      (t/is (= 1 (count all-chels)))
      (t/is (or (= chel1 (first all-chels))
                (= chel2 (first all-chels)))))))
