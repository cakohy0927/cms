package com.cako.project.utils;

import com.orm.commons.utils.IdEntity;
import com.orm.enums.SysEnum;

public class BaseEntity extends IdEntity {

	protected SysEnum.DeleteStatus deleteStatus = SysEnum.DeleteStatus.NO;
	protected SysEnum.Display display = SysEnum.Display.DISPLAY;

	public SysEnum.DeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

	public SysEnum.Display getDisplay() {
		return display;
	}

	public void setDeleteStatus(SysEnum.DeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public void setDisplay(SysEnum.Display display) {
		this.display = display;
	}

}
