package com.github.sancta.simplicitas.pastebin.web.controller

import com.github.sancta.simplicitas.pastebin.web.model.PasteContent
import io.vertx.scala.core.Vertx
import io.vertx.scala.ext.web.{Router, RoutingContext}
import io.vertx.scala.ext.web.handler.BodyHandler

object Mux {
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
              |    <meta name="viewport" content="width=device-width, initial-scale=1.0">
              |    <meta http-equiv="X-UA-Compatible" content="ie=edge">
              |    <style>body{
              |        background: #f06292;
              |        margin: 0
              |    }
              |
              |    .container {
              |
              |        height:100%;
              |        width:100%;
              |        position:fixed;
              |        background: #f06292;
              |    }
              |    .submit {
              |        background: #b92864;
              |        border: 0;
              |        padding: 0.4em 0.8em;
              |        margin: 0.3em;
              |        border-radius: 5%;
              |        box-shadow: 0 1px 4px rgba(0, 0, 0, .5);;
              |        font-family: Arial, Roboto, Helvetica, sans-serif;
              |        letter-spacing: 0.25em;
              |        font-size: 1em;
              |        color: #ffffff;
              |        vertical-align: text-bottom;
              |    }
              |    .submit:hover {
              |        background-color:#9c0044;
              |        cursor: pointer;
              |    }
              |    .submit:active {
              |        position:relative;
              |        top:1px;
              |    }
              |    textarea{
              |        width: 100%;
              |        height: 100%;
              |        border: 0;
              |        padding: 0;
              |        outline: none;
              |        overflow: auto;
              |        background-color: #ff94c2;
              |        color: #ffffff;
              |        font-family: 'Fira Code','NotoSansMono Nerd Font', 'DejaVuSansMono Nerd Font', Roboto, Arial;
              |        font-size: 16px;
              |    }</style>
              |</head>
              |<body></body>
              |<form class="container"action="paste" method="POST">
              |    <input class="submit" type="submit" value="PASTE"/>
              |    <select class="submit" name="duration">
              |        <option value ="600">10 Mins</option>
              |        <option value ="1800" selected="selected">30 Mins</option>
              |        <option value="3600">1 Hr</option>
              |        <option value="18000">5 Hrs</option>
              |    </select>
              |    <br/>
              |    <textarea name="text"></textarea>
              |    <br/>
              |    <div class="center">
              |    </div>
              |</form>
              |</body>
              |</html>
              |""".stripMargin

        routingContext.response().end(co)
    }

    def pasteHandler(routingContext: RoutingContext): Unit = {
        import routingContext._
        val content = request().getParam("text")
        val link = PasteContent.write(content.get)
        response().putHeader("location", s"http://127.0.0.1:8080/s/$link").setStatusCode(302).end()
    }

    def sendHandler(routingContext: RoutingContext): Unit = {
        import routingContext._
        val hash = request().getParam("hash")
        response().end(PasteContent.recv(hash.get))
    }
}

