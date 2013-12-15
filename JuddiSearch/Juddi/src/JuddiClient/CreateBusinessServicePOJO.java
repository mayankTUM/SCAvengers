package JuddiClient;

public class CreateBusinessServicePOJO {
	private String businessEntityName;
	private String url;
	private String businessServiceName;
	private String modelName;
	private String endpoint;
	private String description;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBusinessEntityName() {
		return businessEntityName;
	}

	public void setBusinessEntityName(String businessEntityName) {
		this.businessEntityName = businessEntityName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBusinessServiceName() {
		return businessServiceName;
	}

	public void setBusinessServiceName(String businessServiceName) {
		this.businessServiceName = businessServiceName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String businessModelName) {
		this.modelName = businessModelName;
	}
	
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getEndpoint() {
		return endpoint;
	}
}