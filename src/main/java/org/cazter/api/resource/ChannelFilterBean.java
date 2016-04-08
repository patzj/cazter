package org.cazter.api.resource;

import javax.ws.rs.QueryParam;

public class ChannelFilterBean {

	private @QueryParam("offset") int offset;
	private @QueryParam("limit") int limit;
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
