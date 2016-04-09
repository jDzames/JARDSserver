package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

@Path("/post")
public class PostDocs {

	
	//TODO otestovat!!
	@POST
	@Path("/{folder}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dataFromFolder(@PathParam("folder") String folder, 
			@QueryParam("q") String queryParam, 
			String data)	
			throws JSONException {
		
		Adresar adresar = Data.INSTANCE.docs.get(folder);
		if (adresar == null) {
			return Response.status(404).entity("Unknown folder.").build();
		}
		
		if (!"push".equals(queryParam)) {
			return Response.status(404).entity("Unknown command.").build(); 
		}
		
		JSONObject jo = new JSONObject(data);
		//vyuzitie moze byt, zatial nic
		long syncedDate = jo.getLong("synced");
		//vyuzitie pre rozne mody pushovania
		String modePush = jo.getString("mode");
		System.out.println(syncedDate+" "+modePush);
		
		//zmeny, jsony dokumentov
		ArrayList<Document> deletedDocuments = new ArrayList<>();
		ArrayList<Document> newDocuments = new ArrayList<>();
		ArrayList<Document> updatedDocuments = new ArrayList<>();
		
		JSONArray ja = jo.getJSONArray("changes");
		for (int i = 0; i < ja.length(); i++) {  
		    JSONObject docJson = ja.getJSONObject(i);
		    UUID id = (UUID)docJson.get("id");
		    long syncedDocDate = docJson.getLong("synced");
		    boolean delete = docJson.getBoolean("deleted");
		    boolean newDoc = docJson.getBoolean("new");
		    if (delete) {
				Document doc = adresar.getDocument(id);
				if (doc == null) {
					return Response.status(404).entity("Unknown document id.").build(); 
				}
				doc.setDeleted(true);
				//mozno riesit neskorsi cas..? math.max?
				doc.setDate(new Date(syncedDocDate));
				deletedDocuments.add(doc);
			} else if (newDoc) {
				Document doc = new Document(
						id, syncedDocDate, 
						delete, docJson.getString("content"), 
						docJson);
				adresar.addDocument(doc);
				newDocuments.add(doc);
			} else {
				Document doc = adresar.getDocument(id);
				if (doc == null) {
					return Response.status(404).entity("Unknown document id.").build(); 
				}
				doc.setData(docJson.getString("content"));
				//math max..?
				doc.setDate(new Date(syncedDocDate));
				doc.setDeleted(delete);
				doc.setJson(docJson);
				updatedDocuments.add(doc);
			}
		}
		
		Data.INSTANCE.saveDocumentsIntoDB(newDocuments, deletedDocuments, updatedDocuments);
		
		return Response.status(201).entity("OK").build(); 
	}
	
}
