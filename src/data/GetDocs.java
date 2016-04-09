package data;

import java.util.ArrayList;
import java.util.Date;
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
		Adresar folder = new Adresar("auta", true, "JSON",(new Date()).getTime(), false, "UUID random");
		Filter filter = new Filter();
		List<Document> listFilt = new ArrayList<>();
		List<Document> listFold = new ArrayList<>();
		
		JSONObject json = new JSONObject();
		json.put("znacka", "mercedes");
		Document d = new Document(UUID.randomUUID(), 012, false, "mercedes", json);
		listFilt.add(d);
		listFold.add(d);
		
		json = new JSONObject();
		json.put("znacka", "audi");
		d = new Document(UUID.randomUUID(), 1502, false, "audi", json);
		listFold.add(d);
		
		filter.setDocuments(listFilt);
		folder.addFilter("mercedes", filter);
		folder.setDocumentsList(listFold);
		
		data.docs.put("cars", folder);
	}


	@Path("{folder}/")
	@GET
	@Produces("application/json")
	public Response dataFromFolder(@PathParam("folder") String folder)
			throws JSONException {

		Adresar adresar = data.docs.get(folder);
		if (adresar == null) {
			return Response.status(404).entity("Unknown folder").build();
		}
		
		List<Document> docs = adresar.getDocumentsList();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("changed", adresar.getChanged().toString());
		jsonObject.put("invalidated", adresar.isInvalidated());
		JSONObject jsonData = new JSONObject();
		for (int i = 0; i < docs.size(); i++) {
			Document d = docs.get(i);
			
			if (d.isDeleted()) {
				continue;
			}
			
			JSONObject jsonDoc = new JSONObject();
			jsonDoc.put("changed", d.getDate().toString());
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
