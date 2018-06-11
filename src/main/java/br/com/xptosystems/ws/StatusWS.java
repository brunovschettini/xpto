package br.com.xptosystems.ws;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/status")
public class StatusWS  {

    @GET
    @Path("/active")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response active() {
        return Response.status(200).entity("{\"status\":\"1\"}").build();
    }
}
