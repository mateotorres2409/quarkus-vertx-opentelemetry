package local.mateo.vertxOpentelemetry.application.port.output;

import io.smallrye.mutiny.Uni;

public interface ExternalServicePort {
    Uni<String> fetchData();
}