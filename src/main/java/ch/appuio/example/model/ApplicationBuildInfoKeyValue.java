package ch.appuio.example.model;

public class ApplicationBuildInfoKeyValue {



	private String key;



	private String value;
	
	public ApplicationBuildInfoKeyValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getValue() {
		return value;
	}


	public String getKey() {
		return key;
	}

}
