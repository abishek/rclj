(ns rclj.topics
  (:require [libpython-clj2.python  :refer [py. py.. py.-] :as py]
            [taoensso.timbre        :as logger]))

(defn create-publisher
  "Creates a ROS topic publisher."
  [node topic-type topic-name & [qos-depth]]
  ;; FIXME: the use of 10 as qos/depth is somewhat arbitrary.
  (let [qos (if (nil? qos-depth) 10 qos-depth)
        publisher (py. node create_publisher topic-type topic-name qos)]
    (logger/info (str "Created publisher for topic " topic-name))
    publisher))

(defn create-subscription
  "Creates a ROS topic subscription."
  ([node msg-type topic-name callback-fn qos-depth]
   (py. node create_subscription msg-type topic-name callback-fn qos-depth))
  ([node msg-type topic-name callback-fn]
   ;; FIXME: the use of 1 for qos/depth is somewhat arbitrary.
   (create-subscription node msg-type topic-name callback-fn 1)))

(defn destroy-publisher
  "Destroys a ROS topic publisher."
  [node publisher]
  (py. node destroy_publisher publisher))

(defn destroy-subscription
  "Destroys a ROS topic subscription."
  [node subscription]
  (py. node destroy_subscription subscription))
