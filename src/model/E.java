package model;

import java.io.Serializable;

public class E implements Serializable {
	Long pageFrom = 1L;
	Long pageTo = 10L;
	String orderBy;

	public Long getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(Long pageFrom) {
		this.pageFrom = pageFrom;
	}

	public Long getPageTo() {
		return pageTo;
	}

	public void setPageTo(Long pageTo) {
		this.pageTo = pageTo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
