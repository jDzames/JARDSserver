package data;

import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;

public class Document {

	private UUID id;
	private String data;
	private JSONObject json;
	private boolean deleted;
	private Date date;
	
	public Document(UUID id, long date, boolean deleted, String data, JSONObject json) {
		super();
		this.id = id;
		this.data = data;
		this.json = json;
		this.deleted = deleted;
		this.date = new Date(date);
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

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date updateDate){
		this.date = updateDate;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}
	
	
}
