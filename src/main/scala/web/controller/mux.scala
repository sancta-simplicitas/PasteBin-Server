package web.controller

import io.vertx.scala.core.Vertx
import io.vertx.scala.ext.web.{Router, RoutingContext}
import io.vertx.scala.ext.web.handler.BodyHandler
import web.model.PasteContent

object mux {
    def registerRoute(app: Vertx): Router = {

        val router = Router.router(app)

        // Route table
        import router._
        route.handler(BodyHandler.create())

        get("/").handler(indexHandler)
        post("/paste").handler(pasteHandler)
        get("/s/:hash").handler(sendHandler)

        router
    }

    def indexHandler(routingContext: RoutingContext): Unit = {
        val co =
            """
              |<!DOCTYPE html>
              |<html lang="en">
              |<head>
              |    <meta charset="UTF-8">
              |    <title>Title</title>
              |</head>
              |<body>
              |<form method="post" action="http://127.0.0.1:8080/paste">
              |    <input name="content">
              |    <input type="submit">
              |</form>
              |
              |</body>
              |</html>
              |""".stripMargin
        routingContext.response().end(co)
    }

    def pasteHandler(routingContext: RoutingContext): Unit = {
        import routingContext._
        val content = request().getParam("content")
        PasteContent.write(content.get)
        response().end("Write succeed.")
    }

    def sendHandler(routingContext: RoutingContext): Unit = {
        import routingContext._
        val hash = request().getParam("hash")
        response().end(PasteContent.recv(hash.get))
    }
}

