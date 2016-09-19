package data;

import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;

public class Document {

	private UUID id;
	private String data;
	private JSONObject content;
	private boolean deleted;
	//private Date date;
	private long changed; 
	
	public Document(UUID id, long changed, boolean deleted, String data, JSONObject json) {
		super();
		this.id = id;
		this.data = data;
		this.content = json;
		this.deleted = deleted;
		this.changed = changed;
	}
	
	public long getChanged() {
		return changed;
	}

	public void setChanged(long changed) {
		this.changed = changed;
	}

	public UUID getId() {
		return id;
	}
	
	public String getData() {
		return data;
	}

	public JSONObject getContent() {
		return content;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/*public Date getDate() {
		return date;
	}
	
	public void setDate(Date updateDate){
		this.date = updateDate;
	}*/

	public void setId(UUID id) {
		this.id = id;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setContent(JSONObject json) {
		this.content = json;
	}
	
	
}
