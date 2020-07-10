package tech.mednikov.userservicex;

import io.vertx.core.Vertx;
import tech.mednikov.userservicex.http.HttpVerticle;

class Application {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HttpVerticle(), res -> {
      if (res.succeeded()) {
        System.out.println("Verticle was deployed");
      } else {
        res.cause().printStackTrace();
      }
    });
  }
}
