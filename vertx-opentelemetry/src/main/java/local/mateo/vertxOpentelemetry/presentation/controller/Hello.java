package local.mateo.vertxOpentelemetry.presentation.controller;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
//import org.jboss.logging.Logger;

@Path("/hello")
public class Hello {

    //private static final Logger LOG = Logger.getLogger(Hello.class);

    private final LongCounter counter;

    public Hello(Meter meter) { 
        counter = meter.counterBuilder("hello") 
                .setDescription("hello-metrics")
                .setUnit("invocations")
                .build();
    }

    @GET
    @Path("/1")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello1() {
        counter.add(1,Attributes.of(AttributeKey.stringKey("hello"), "1")); 
        return "hello 1";
    }
    @GET
    @Path("/2")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2() {
        counter.add(1,Attributes.of(AttributeKey.stringKey("hello"), "2")); 
        return "hello 2";
    }
}