package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


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
	
	private Map<UUID, Document> docs = new HashMap<>();
	
	

	public Adresar(String root, boolean invalidated, String schema, long changed, boolean readOnly,
			String idGenerator) {
		super();
		this.root = root;
		this.changed = new Date(changed);
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
	
	public List<Document> getDocumentsList() {
		return new ArrayList<>(docs.values());
	}
	
	public void setDocuments(Map<UUID, Document> docs) {
		this.docs = docs;
	}
	
	public void setDocumentsList(List<Document> docs) {
		for (int i = 0; i < docs.size(); i++) {
			addDocument(docs.get(i));
		}
	}

	public void addDocument(Document doc) {
		this.docs.put(doc.getId(), doc);
	}
	
	public Document getDocument(UUID id){
		return docs.get(id);
	}
	
}
