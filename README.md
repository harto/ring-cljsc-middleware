Ring middleware that automatically (re)compiles your ClojureScript files.

This library provides one function, `ring.middleware.cljsc/wrap-recompile`,
which recompiles files using `cljs.closure/build`.

Leiningen dependency
---

    [ring-cljsc-middleware "0.0.3"]
