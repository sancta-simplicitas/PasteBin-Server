package web.controller

import utils.extFn.KtStd
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.{Router, RoutingContext}
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
        PasteContent.write(content)
        response().end("Write succeed.")
    }

    def sendHandler(routingContext: RoutingContext): Unit = {
        import routingContext._
        val hash = request().getParam("hash")
//        PasteContent.recv()
        response().end(PasteContent.recv(hash))
    }
}

