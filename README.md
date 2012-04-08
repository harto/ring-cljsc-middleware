Ring middleware that automatically (re)compiles your ClojureScript
files.

There are two functions in the `ring.middleware.cljsc` namespace:

  * `wrap-recompile` is a wrapper around `cljs.closure/build` that
    recompiles files as required.

  * `wrap-encoding` adds a UTF-8 encoding header to all JavaScript
    file responses.

Leiningen dependency
---

    [ring-cljsc-middleware "0.0.1"]

Usage
---

    (ns foo
      (:require [ring.middleware.cljsc :as cljsc])
    
    (def app (-> my-route-handler
                 (cljsc/wrap-recompile "src")
                 (cljsc/wrap-encoding)))
