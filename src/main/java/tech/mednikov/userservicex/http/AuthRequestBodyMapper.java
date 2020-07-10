package tech.mednikov.userservicex.http;

import java.util.Optional;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import tech.mednikov.userservicex.model.AuthRequest;

class AuthRequestBodyMapper {

  Optional<AuthRequest> mapFromJson (RoutingContext context) {
    JsonObject body = context.getBodyAsJson();
    if (body == null || body.isEmpty()) {
      return Optional.empty();
    }
    AuthRequest request = Json.decodeValue(body.toBuffer(), AuthRequest.class);
    return Optional.of(request);
  }
}
