package data;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/data")
public class GetInfo {

	@Path("{folder}/{filter}/q=describe")
	@GET
	@Produces("application/json")
	public Response describeFolderFilter(@PathParam("folder") String folder, 
			@PathParam("filter") String filter) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("root", "");
		jsonObject.put("changed", "10");
		jsonObject.put("invalidated", "");
		jsonObject.put("schema", "");
		jsonObject.put("readonly", "");
		jsonObject.put("id-generator", "");

		//tie stringy asi este vymazat
		String result = "@Produces(\"application/json\") Description \n" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@Path("{folder}/q=describe")
	@GET
	@Produces("application/json")
	public Response describeFolder(@PathParam("folder") String folder) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("root", "");
		jsonObject.put("changed", "10");
		jsonObject.put("invalidated", "");
		jsonObject.put("schema", "");
		jsonObject.put("readonly", "");
		jsonObject.put("id-generator", "");

		//tie stringy asi este vymazat
		String result = "@Produces(\"application/json\") Description \n" + jsonObject;
		return Response.status(200).entity(result).build();
	}
	
	@Path("{folder}/{filter}/q=state")
	@GET
	@Produces("application/json")
	public Response stateOfFolderFilter(@PathParam("folder") String folder, 
			@PathParam("filter") String filter) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("root", "");
		jsonObject.put("changed", "10");
		jsonObject.put("invalidated", "");
		jsonObject.put("schema", "");
		jsonObject.put("readonly", "");
		jsonObject.put("id-generator", "");

		//tie stringy asi este vymazat
		String result = "@Produces(\"application/json\") State \n" + jsonObject;
		return Response.status(200).entity(result).build();
	}

	@Path("{folder}/q=state")
	@GET
	@Produces("application/json")
	public Response stateOfFolder(@PathParam("folder") String folder) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("root", "");
		jsonObject.put("changed", "10");
		jsonObject.put("invalidated", "");
		jsonObject.put("schema", "");
		jsonObject.put("readonly", "");
		jsonObject.put("id-generator", "");

		//tie stringy asi este vymazat
		String result = "@Produces(\"application/json\") State \n" + jsonObject;
		return Response.status(200).entity(result).build();
	}
	
}
