package org.cazter.api.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class ChannelFilterBean {

	private @DefaultValue("0") @QueryParam("offset") int offset;
	private @DefaultValue("0") @QueryParam("limit") int limit;
	private @DefaultValue("0") @QueryParam("owner") int owner;
	
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

	public int getOwner() {
		return owner;
	}

	public void setOwnerId(int owner) {
		this.owner = owner;
	}
}
