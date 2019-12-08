import io.vertx.lang.scala.VertxExecutionContext
import io.vertx.scala.core.Vertx
import web.controller.mux.registerRoute
import scala.util.{Failure, Success}

object app {
    def main(args: Array[String]): Unit = {
        println("Hello, World")
        // Init
        val app = Vertx.vertx()
        val router = registerRoute(app)

        implicit val ec = VertxExecutionContext(app.getOrCreateContext())

        app.createHttpServer().requestHandler(router).listenFuture(8080).onComplete {
            case Failure(_) => println("Failure")
            case Success(_) => println("Started")
        }

    }
}

