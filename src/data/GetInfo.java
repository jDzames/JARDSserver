package data;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/info")
public class GetInfo {
	
	private Data data = Data.INSTANCE;


	@Path("{folder}/")
	@GET
	@Produces("application/json")
	public Response getInfo(@PathParam("folder") String folder, 
			@QueryParam("q") String queryParam) throws JSONException {

		Adresar adresar = data.docs.get(folder);
		if (adresar == null) {
			return Response.status(404).entity("Unknown folder.").build();
		}
		
		if ("status".equals(queryParam)) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("root", adresar.getRoot());
			jsonObject.put("changed", adresar.getChanged());
			jsonObject.put("invalidated", adresar.isInvalidated());

			String result = "" + jsonObject;
			return Response.status(200).entity(result).build();
		}
		else if ("describe".equals(queryParam)) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("root", adresar.getRoot());
			jsonObject.put("changed", adresar.getChanged());
			jsonObject.put("invalidated", adresar.isInvalidated());
			jsonObject.put("schema", adresar.getSchema());
			jsonObject.put("readonly", adresar.isReadOnly());
			jsonObject.put("id-generator", adresar.getIdGenerator());

			String result = ""+jsonObject;
			return Response.status(200).entity(result).build();
		} 
		
		return Response.status(404).entity("Unknown command").build();
	}
	
}
