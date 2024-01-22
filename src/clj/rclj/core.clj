(ns rclj.core
  (:require [libpython-clj2.python  :refer [py. py.. py.-] :as py]
            [libpython-clj2.require :refer [require-python]]
            [taoensso.timbre        :as logger]
            [clojure.core.async     :as a]))

(py/initialize!)

(require-python '[rclpy               :as rclpy])
(require-python '[rclpy.node          :as rclnode])
(require-python '[rclpy.action.client :as rcl-action-client])
(require-python '[std_msgs.msg        :as std-msgs])

(defn initialize
  "Initialize ROS2"
  []
  (rclpy/init))

(defn shutdown
  "Shutdown ROS2"
  []
  (rclpy/shutdown))

(defn start-node
  "Start a ROS node and leave it spinning in its own thread."
  [name namespace]
  (let [node (rclnode/Node :node_name name :namespace namespace :start_parameter_service false)]
    (a/go (rclpy/spin node))
    (logger/info "Started ROS node.")
    node))

(defn stop-node
  "Stop a ROS node."
  [node]
  (py. node destroy_node))
