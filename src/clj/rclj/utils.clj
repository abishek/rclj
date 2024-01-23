(ns rclj.utils
  (:require [libpython-clj2.python  :refer [py. py.. py.-] :as py]
            [taoensso.timbre        :as logger]))

(defn -has-fields-and-field-types-method?
  "All RCL message objects have a method called get_fields_and_field_types."
  [data]
  (py/has-attr? data "get_fields_and_field_types"))

(defn -is-simple-python-type?
  [data]
  (cond (= type :int)  true
        (= type :float true)
        (= type :str   true)
        (= type :bool  true)
        (= type :bytes true)
        :else          false))

(defn -is-iterable?
  "Iterables need to stepped into. So identify them."
  [data]
  (py/has-attr? data "__iter__"))


(defn get-fields
  "Returns a dictionary of fieldname and value. Recursive implementation. Assumes data is an object."
  [data]
  (if (-has-fields-and-field-types-method? data)
    (into {} (map (fn [[k v]]
                    (let [val  (py/get-attr data k)
                          ksym (keyword k)]
                      (cond (-has-fields-and-field-types-method? val) {ksym (get-fields val)}
                            (-is-simple-python-type? val)             {ksym val}
                            (-is-iterable? val)                       {ksym (map get-fields val)}
                            :else                                     {ksym val})))
                  (py. data get_fields_and_field_types)))
    data))
