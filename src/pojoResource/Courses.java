package pojoResource;

import java.util.List;

public class Courses {
	
	private List<WebAutomation> webAutomation; // Array of JSON Object
	private List<Api> api; // Array of JSON Object
	private List<Mobile> mobile; // Array of JSON Object
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	
	public List<Api> getApi() {
		return api;
	}
	
	public void setApi(List<Api> api) {
		this.api = api;
	}
	
	public List<Mobile> getMobile() {
		return mobile;
	}
	
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	
}
