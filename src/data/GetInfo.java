package data;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/info")
public class GetInfo {
	
	private Data data = Data.INSTANCE;

	@Path("{folder}/{filter}/q=describe")
	@GET
	@Produces("application/json")
	public Response describeFolderFilter(@PathParam("folder") String folder, 
			@PathParam("filter") String filter) throws JSONException {

		Adresar adresar = data.docs.get(folder);
		if (adresar == null) {
			return Response.status(404).entity("Unknown folder").build();
		}
		Filter filt = adresar.getFilter(filter);
		if (filt == null) {
			return Response.status(404).entity("Unknown filter").build();
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("root", adresar.getRoot());
		jsonObject.put("changed", adresar.getChanged().toString());
		jsonObject.put("invalidated", adresar.isInvalidated());
		jsonObject.put("schema", adresar.getSchema());
		jsonObject.put("readonly", adresar.isReadOnly());
		jsonObject.put("id-generator", adresar.getIdGenerator());

		//tie stringy asi este vymazat
		String result = "@Produces(\"application/json\") Description \n" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@Path("{folder}/q=describe")
	@GET
	@Produces("application/json")
	public Response describeFolder(@PathParam("folder") String folder) throws JSONException {

		Adresar adresar = data.docs.get(folder);
		if (adresar == null) {
			return Response.status(404).entity("Unknown folder").build();
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("root", adresar.getRoot());
		jsonObject.put("changed", adresar.getChanged().toString());
		jsonObject.put("invalidated", adresar.isInvalidated());
		jsonObject.put("schema", adresar.getSchema());
		jsonObject.put("readonly", adresar.isReadOnly());
		jsonObject.put("id-generator", adresar.getIdGenerator());

		//tie stringy asi este vymazat
		String result = "@Produces(\"application/json\") Description \n" + jsonObject;
		return Response.status(200).entity(result).build();
	}
	
	@Path("{folder}/{filter}/q=state")
	@GET
	@Produces("application/json")
	public Response stateOfFolderFilter(@PathParam("folder") String folder, 
			@PathParam("filter") String filter) throws JSONException {

		Adresar adresar = data.docs.get(folder);
		if (adresar == null) {
			return Response.status(404).entity("Unknown folder").build();
		}
		Filter filt = adresar.getFilter(filter);
		if (filt == null) {
			return Response.status(404).entity("Unknown filter").build();
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("root", adresar.getRoot());
		jsonObject.put("changed", adresar.getChanged().toString());
		jsonObject.put("invalidated", adresar.isInvalidated());

		//tie stringy asi este vymazat
		String result = "@Produces(\"application/json\") State \n" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@Path("{folder}/q=state")
	@GET
	@Produces("application/json")
	public Response stateOfFolder(@PathParam("folder") String folder) throws JSONException {

		Adresar adresar = data.docs.get(folder);
		if (adresar == null) {
			return Response.status(404).entity("Unknown folder").build();
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("root", adresar.getRoot());
		jsonObject.put("changed", adresar.getChanged().toString());
		jsonObject.put("invalidated", adresar.isInvalidated());

		//tie stringy asi este vymazat
		String result = "@Produces(\"application/json\") State \n" + jsonObject;
		return Response.status(200).entity(result).build();
	}
	
}
