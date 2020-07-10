package tech.mednikov.userservicex.http;

import io.vavr.control.Validation;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import tech.mednikov.userservicex.model.AuthRequest;
import tech.mednikov.userservicex.validations.AuthRequestValidator;
import tech.mednikov.userservicex.validations.ValidatonError;

public class HttpVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    router.route("/users/*").handler(BodyHandler.create());
    router.route("/users/signup").handler(this::signupHandler);
    server.requestHandler(router);
    server.listen(8080, result -> {
      if (result.succeeded()) {
        System.out.println("Http server listens port 8080");
        startPromise.complete();
      } else {
        startPromise.fail(result.cause());
      }
    });
  }

  private void signupHandler (RoutingContext context) {
    JsonObject body = context.getBodyAsJson();
    AuthRequest request = Json.decodeValue(body.toBuffer(), AuthRequest.class);
    AuthRequestValidator validator = new AuthRequestValidator();
    Validation<ValidatonError, AuthRequest> validation = validator.validate(request);
    if (validation.isValid()) {
      JsonObject payload = new JsonObject().put("message", "User was created");
      context.response().setStatusCode(200).end(payload.encodePrettily());
    } else {
      String message = validation.getError().getMessage();
      JsonObject payload = new JsonObject().put("reason", message);
      context.response().setStatusCode(400).end(payload.encodePrettily());
    }
  }
}
