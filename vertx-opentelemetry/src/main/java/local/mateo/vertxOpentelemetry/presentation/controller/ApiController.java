package local.mateo.vertxOpentelemetry.presentation.controller;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import local.mateo.vertxOpentelemetry.application.usecase.FetchDataUseCase;

@Path("/api")
public class ApiController {

    @Inject
    FetchDataUseCase fetchDataUseCase;

    @GET
    @Path("/dummyjson-200")
    public Uni<String> callExternalService() {
        return fetchDataUseCase.execute();
    }
}
