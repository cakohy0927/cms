package com.orm.enums;

public class SysEnum {
	public static enum ColumnType {
		CMS, COMMON, NOTE;
		private ColumnType() {

		}
	}

	/**
	 * 用户的状态
	 *
	 * @描述：INIT 初始状态，LOCKED 锁定，NORMAL 普通状态
	 * @author HUANGYUAN
	 * @TIME:2015年9月11日 下午11:07:53
	 */
	public static enum Status {
		INIT, LOCKED, NORMAL;

		private Status() {
		}
	}

	/**
	 * 用户的类型
	 *
	 * @描述：ADMIN 管理员; GENERAL 普通用户;MEMBER 普通会员;LEAGUER_MEMBER 高级会员
	 * @author HUANGYUAN
	 * @TIME:2015年9月11日 下午11:08:34
	 */
	public static enum UserType {
		ADMIN, GENERAL, LEAGUER_MEMBER, MEMBER;

		private UserType() {
		}
	}
}
