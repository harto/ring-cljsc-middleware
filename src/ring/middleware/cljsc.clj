(ns ring.middleware.cljsc
  "Ring middleware that automatically (re)compiles your ClojureScript files."
  (:require [clojure.tools.logging :as log]
            [cljs.closure :as cljsc]))

(defonce ^:private last-compile
  (atom nil))

(defn wrap-recompile
  "Recompiles ClojureScript files in `src-dir` if one of `files` is modified
   since last compile time.

   An optional map of options may be provided, which is passed directly to
   `cljs.closure/build`."
  ([handler files src-dir]
     (wrap-recompile files src-dir {}))
  ([handler files src-dir opts]
     (fn [request]
       (let [newest (apply max 0 (map #(.lastModified %) files))]
         (when (> newest (or @last-compile 0))
           (log/info "recompiling")
           (reset! last-compile newest)
           (cljsc/build src-dir opts)))
       (handler request))))
