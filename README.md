# RCL - Clojure Bridge


## What does this do?

[Clojure](https://clojure.org) is one of my favourite lisps. And lisps are generally better prototyping environments than many others. __Of course, am not starting a war on this topic - your mileage will vary_. I started working with [ROS2](https://github.com/ros2/ros2) in early 2023 and found that, unlike `ROS1`, there are no supported lisp bindings. The application I was building/prototyping was in a lisp (clojure) and I didn't want to redo that part. So I decided to spend some energy and create the binding I needed to proceed.

While my solution is far from optimal, this is a first way forward. And am already working on a better solution that will also be pushed into this repo.

## How does this work?

In its current form, this library is built on top of [libpython-clj](https://github.com/clj-python/libpython-clj) and [rclpy](https://github.com/ros2/rclpy). `libpythonclj` is a fantastic solution from the clojure community of datascience folks. `rclpy` is pretty much the official `ROS2` binding for `python` - mature and feature complete in a sense. I just call `rclpy` via the clojure-python bridge and move along for now. The solution is functional (both in terms of its ability to do the job and the fact that it uses Clojure).

### If it works, then why is there another approach?

The purpose of [RCL](https://github.com/ros2/rcl), as I understand, is to create a baseline implementation of ROS features such that all other language environments should just use FFI (Foreign Function Interface) to do the job. Both `rclpy` and [`rclnodejs`](https://github.com/RobotWebTools/rclnodejs) are written this way. If I get it right, [rcljava](https://github.com/ros2-java/ros2_java) is written this way too. So, the better solution is to write a clojure - FFI to rcl. This is the approach am currently working on.

But if `rcljava` is present, should `rclj` just be a wrapper on that? Ideally, yes. But fwiw, I am unable to get `rcljava` to compile in my setup. Perhaps someone could submit a pull request on how to do that and I will gladly add that as a solution approach.

### Can I use this?

Yes, you can. The API is somewhat primitive. I wrote exactly the features/calls I needed in my prototype. So if something is missing or not handled properly, feel free to raise an issue and I will fix it asap.

## Status

The library is not yet published to [clojars](https://clojars.org/). Once I get it feature complete, I will submit it into `clojars`. I will also need to engage a better clojure developer to review and help improve the code. Again, if you are that person - please reach out to me.
