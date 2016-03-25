package data;

import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;

public class Document {

	private UUID id;
	private String data;
	private JSONObject json;
	private boolean deleted;
	private Date createdDate;
	
	public Document(UUID id, String data, JSONObject json) {
		super();
		this.id = id;
		this.data = data;
		this.json = json;
		this.deleted = false;
		this.createdDate = new Date();
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getData() {
		return data;
	}

	public JSONObject getJson() {
		return json;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	
}
