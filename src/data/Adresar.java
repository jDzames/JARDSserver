package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author jDzama
 * TODO get dokument nejaky, query... 
 */
public class Adresar {

	private String root;
	private Date changed;
	private boolean invalidated;
	private String schema;
	private boolean readOnly;
	private String idGenerator;
	
	private Map<String, Filter> filters = new HashMap<>();
	
	private List<Document> docs = new ArrayList<>();
	
	

	public Adresar(String root, boolean invalidated, String schema, boolean readOnly,
			String idGenerator) {
		super();
		this.root = root;
		this.changed = new Date();
		this.invalidated = invalidated;
		this.schema = schema;
		this.readOnly = readOnly;
		this.idGenerator = idGenerator;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public Date getChanged() {
		return changed;
	}

	public void setChanged(Date changed) {
		this.changed = changed;
	}

	public boolean isInvalidated() {
		return invalidated;
	}

	public void setInvalidated(boolean invalidated) {
		this.invalidated = invalidated;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getIdGenerator() {
		return idGenerator;
	}

	public void setIdGenerator(String idGenerator) {
		this.idGenerator = idGenerator;
	}

	public Filter getFilter(String uri) {
		return filters.get(uri);
	}

	public void addFilter(String name, Filter filter) {
		this.filters.put(name, filter);
	}
	
	public List<Document> getDocuments() {
		return docs;
	}
	
	
	public void setDocuments(List<Document> docs) {
		this.docs = docs;
	}

	public void addDocument(Document doc) {
		this.docs.add(doc);
	}
	
}
