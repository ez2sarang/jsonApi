package com.fast2.zimin.tvod.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fast2.zimin.util.JsonDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class TvodEpgLoadHistory {

	@Id
	private long idx;
	
	private String fileName;
	
	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateTimeSerializer.class)	
	private Date startTime;

	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateTimeSerializer.class)	
	private Date endTime;

	private String result;
	
	private String resultMessage;

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	
}
