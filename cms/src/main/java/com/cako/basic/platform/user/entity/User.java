package com.cako.basic.platform.user.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cako.basic.platform.department.entity.Depart;
import com.cako.basic.platform.role.entity.Role;
import com.orm.commons.utils.IdEntity;

@Entity
@Table(name = "system_user")
public class User extends IdEntity {
	public interface ParameterUtils {
		public final static String MAN = "man";
		public final static String WOMEN = "women";
	}

	/**
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

	private String address;
	/**
	 * 部门
	 */
	private List<Depart> departments = new ArrayList<Depart>();
	private String email;
	private String idCard;// 身份证号码
	private Boolean isDelete = Boolean.FALSE;
	private String loginName;
	private String password;
	private String phone;
	private String QQ;
	private String realName;

	/**
	 * 角色
	 */
	private List<Role> roles = new ArrayList<Role>();

	private String sex;

	public User.Status status = Status.INIT;

	private User.UserType type = UserType.GENERAL;

	public User() {

	}

	public User(String loginName, String password) {
		this.loginName = loginName;
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "system_user_dept", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "dept_id"))
	public List<Depart> getDepartments() {
		return departments;
	}

	public String getEmail() {
		return email;
	}

	public String getIdCard() {
		return idCard;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getQQ() {
		return QQ;
	}

	public String getRealName() {
		return realName;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "system_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Fetch(FetchMode.SUBSELECT)
	public List<Role> getRoles() {
		return roles;
	}

	public String getSex() {
		return sex;
	}

	public Status getStatus() {
		return status;
	}

	public User.UserType getType() {
		return type;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDepartments(List<Depart> departments) {
		this.departments = departments;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setType(User.UserType type) {
		this.type = type;
	}

}
