package com.cako.project.column.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cako.basic.util.SysContent;
import com.orm.commons.utils.IdEntity;
import com.orm.enums.SysEnum;

/**
 * 栏目实体
 *
 * @author nick
 *
 */
@Entity
@Table(name = "pro_column")
public class Column extends IdEntity {

	/**
	 * 上级栏目
	 */
	private Column column;

	/**
	 * 是否是子节点
	 */
	private Boolean flag = false;

	/**
	 * 是否可用
	 */
	private String isDelete = SysContent.IsDisable.NODISABLE;
	/**
	 * 栏目名称
	 */
	private String name;

	private SysEnum.ColumnType type = SysEnum.ColumnType.COMMON;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_id")
	public Column getColumn() {
		return column;
	}

	public Boolean getFlag() {
		return flag;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public String getName() {
		return name;
	}

	public SysEnum.ColumnType getType() {
		return type;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(SysEnum.ColumnType type) {
		this.type = type;
	}
}
