package local.mateo.vertxOpentelemetry.infrastructure.rest.client;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import local.mateo.vertxOpentelemetry.application.port.output.ExternalServicePort;

@ApplicationScoped
public class ExternalServiceAdapter implements ExternalServicePort {

    private final WebClient webClient;

    @ConfigProperty(name = "dummy200.url")
    private String urlDummy200;

    @Inject
    public ExternalServiceAdapter(Vertx vertx) {
        WebClientOptions options = new WebClientOptions()
            .setFollowRedirects(true); // Configuración opcional
        this.webClient = WebClient.create(vertx, options);
    }

    public Uni<String> fetchData() {
        String url = this.urlDummy200;
        return Uni.createFrom().completionStage(
            webClient.getAbs(url)
                .send()
                .toCompletionStage()
                .thenApply(response -> {
                    if (response.statusCode() >= 400) {
                        throw new RuntimeException("HTTP Error: " + response.statusCode());
                    }
                    return response.bodyAsString();
                })
        )
        .onFailure(throwable -> throwable.getMessage().contains("HTTP Error"))
        .recoverWithItem("Error: Servicio no disponible");
    }
}

