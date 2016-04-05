package data;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

@Path("/post")
public class PostDocs {

	
	@Path("{folder}/q=push")
	@POST
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dataFromFolder(@PathParam("folder") String folder, String json)
			throws JSONException {
		
		String in = "Data post: "+json;
		System.out.println(in);
		
		return Response.status(201).entity("OK").build(); 
	}
	
}
