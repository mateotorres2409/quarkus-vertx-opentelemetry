package local.mateo.vertxOpentelemetry.application.usecase;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import local.mateo.vertxOpentelemetry.application.port.output.ExternalServicePort;

@ApplicationScoped
public class FetchDataUseCase {

    private final ExternalServicePort externalServicePort;

    @Inject
    public FetchDataUseCase(ExternalServicePort externalServicePort) {
        this.externalServicePort = externalServicePort;
    }

    public Uni<String> execute() {
        return externalServicePort.fetchData();
    }
}

