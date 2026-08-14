package com.miniProject2.dto;

public class StateDto {
	private Integer stateId;
	private String stateName;
public StateDto() {}

	public StateDto(Integer stateId, String stateName) {
	super();
	this.stateId = stateId;
	this.stateName = stateName;
}

	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	@Override
	public String toString() {
		return "StateDto [stateId=" + stateId + ", stateName=" + stateName + "]";
	}
	
	
}
