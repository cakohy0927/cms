package com.cako.project.note.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.basic.platform.user.entity.User;
import com.cako.project.column.entity.Column;
import com.orm.commons.utils.IdEntity;

@Entity
@Table(name = "user_node")
public class UserNote extends IdEntity {
	private Column column;// 帖子所属的模块
	private String noteContent;// 帖子的内容
	private String noteTitle;// 帖子的标题
	private User user;// 写帖子的人,版主

	@ManyToOne
	@JoinColumn(name = "column_id")
	public Column getColumn() {
		return column;
	}

	@Lob
	public String getNoteContent() {
		return noteContent;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
