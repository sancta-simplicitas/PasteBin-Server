package com.github.sancta.simplicitas.pastebin.web.verticle

import io.vertx.lang.scala.VertxExecutionContext
import io.vertx.scala.core.Vertx
import com.github.sancta.simplicitas.pastebin.web.controller.Mux.registerRoute
import scala.util.{Failure, Success}
import com.github.sancta.simplicitas.pastebin.ext.Fn.KtStd

class HttpVerticle {
    def start(): Unit = {
        Vertx.vertx().let(app => registerRoute(app).let { router =>
            implicit val ec = VertxExecutionContext(app.getOrCreateContext())
            app.createHttpServer().requestHandler(router).listenFuture(8080).onComplete {
                case Failure(_) => println("Failure")
                case Success(_) => println("Started")
            }
        })
    }
}
