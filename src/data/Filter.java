package data;

import java.util.ArrayList;
import java.util.List;

public class Filter {

	private List<Document> docs = new ArrayList<>();

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
