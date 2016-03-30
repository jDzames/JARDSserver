package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/data")
public class GetDocs {
	
	private Data data = Data.INSTANCE;
	
	
	
	public GetDocs() {
		Adresar folder = new Adresar("auta", true, "JSON", false, "UUID random");
		Filter filter = new Filter();
		List<Document> list = new ArrayList<>();
		HashMap<String, Document> map = new HashMap<>();
		
		JSONObject json = new JSONObject();
		json.put("znacka", "mercedes");
		Document d = new Document(UUID.randomUUID(), "mercedes", json);
		list.add(d);
		map.put(""+d.getId(), d);
		
		json = new JSONObject();
		json.put("znacka", "audi");
		d = new Document(UUID.randomUUID(), "audi", json);
		map.put(""+d.getId(), d);
		
		filter.setDocuments(list);
		folder.addFilter("mercedes", filter);
		folder.setDocuments(list);
		
		data.docs.put("cars", folder);
	}

	@Path("{folder}/{filter}/")
	@GET
	@Produces("application/json")
	public Response dataFromFilter(@PathParam("folder") String folder, 
			@PathParam("filter") String filter) throws JSONException {

		Adresar adresar = data.docs.get(folder);
		if (adresar == null) {
			return Response.status(404).entity("Unknown folder").build();
		}
		Filter filt = adresar.getFilter(filter);
		if (filt == null) {
			return Response.status(404).entity("Unknown filter").build();
		}
		
		List<Document> docs = filt.getDocuments();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("changed", adresar.getChanged().toString());
		jsonObject.put("invalidated", adresar.isInvalidated());
		JSONObject jsonData = new JSONObject();
		for (int i = 0; i < docs.size(); i++) {
			Document d = docs.get(i);
			
			JSONObject jsonDoc = new JSONObject();
			jsonDoc.put("changed", d.getCreatedDate().toString());
			jsonDoc.put("deleted", d.isDeleted());
			jsonDoc.put("content", d.getJson());
			
			jsonData.put(""+d.getId(), jsonDoc);
		}
		jsonObject.put("documents", jsonData);

		//tie stringy asi este vymazat.
		String result = /*"@Produces(\"application/json\") Description \n" +*/ ""+jsonObject;
		return Response.status(200).entity(result).build();
	}

	

}