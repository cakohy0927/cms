package com.cako.project.column.tree;

import java.util.List;

import com.cako.basic.platform.attachment.entity.Attachment;
import com.cako.project.column.entity.Column;
import com.cako.project.column.entity.News;

public class NewsClass {

	private String id;
	private String columnName;
	private String title;
	private String columnId;
	private String content;
	private String deployStatus;
	private List<Attachment> versions;
	
	public NewsClass(News news,Column column,List<Attachment> versions) {
		this.id = news.getId();
		this.title = news.getTitle();
		this.content = news.getContent();
		this.versions = versions;
		this.deployStatus = news.getDeployStatus() == true ? "1" : "0";
		if (column != null) {
			this.columnName = column.getName();
			this.columnId = column.getId();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Attachment> getVersions() {
		return versions;
	}

	public void setVersions(List<Attachment> versions) {
		this.versions = versions;
	}
	
	public String getDeployStatus() {
		return deployStatus;
	}
	
	public void setDeployStatus(String deployStatus) {
		this.deployStatus = deployStatus;
	}
}
