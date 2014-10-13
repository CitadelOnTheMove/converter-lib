package eu.citadel.converter.io.index.response;

public class IndexResponse {
	private static final String RESPONSE_OK = "ok";
	private String response, message;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isOk(){
		return RESPONSE_OK.equalsIgnoreCase(getResponse());
	}
}
