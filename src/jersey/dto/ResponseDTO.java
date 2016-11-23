package jersey.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseDTO {
	Object payload;
	String error;
	int countTotal;
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getCountTotal() {
		return countTotal;
	}
	public void setCountTotal(int countTotal) {
		this.countTotal = countTotal;
	}
	@Override
	public String toString() {
		return "ResponseDTO [payload=" + payload + ", error=" + error + ", countTotal=" + countTotal + "]";
	}
	
	
}
