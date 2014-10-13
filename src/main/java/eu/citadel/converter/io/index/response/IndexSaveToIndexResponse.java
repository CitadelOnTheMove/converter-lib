package eu.citadel.converter.io.index.response;

public class IndexSaveToIndexResponse extends IndexResponse {
	private String storedfileName;

	public String getStoredfileName() {
		return storedfileName;
	}

	public void setStoredfileName(String storedfileName) {
		this.storedfileName = storedfileName;
	}
}
