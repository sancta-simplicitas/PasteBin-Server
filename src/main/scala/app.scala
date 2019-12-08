import io.vertx.core.Vertx
import io.vertx.ext.web.{Router, RoutingContext}
import web.controller.mux._
import web.service.Redis

object app {
    def main(args: Array[String]): Unit = {
        println("Hello, World")
        // Init
        val app = Vertx.vertx()
        val router = registerRoute(app)

        app.createHttpServer().requestHandler(router).listen(8080)

    }
}

