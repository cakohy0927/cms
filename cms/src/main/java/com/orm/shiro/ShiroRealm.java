package com.orm.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.cako.basic.platform.user.entity.User;
import com.cako.basic.platform.user.service.IUserService;
import com.orm.commons.exception.ServiceException;
import com.orm.config.InitEnvironment;

public class ShiroRealm extends AuthorizingRealm {

	private final static Logger logger = LoggerFactory.getLogger(InitEnvironment.class);

	@Autowired
	private IUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		try {
			String username = (String) principals.getPrimaryPrincipal();
			logger.info("用户名称：" + username);
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo.setRoles(userService.findRolesByLoginName(username));
			Set<String> set = userService.findPermissionsByLoginName(username);
			for (String string : set) {
				System.out.println(string);
			}
			authorizationInfo.setStringPermissions(userService
					.findPermissionsByLoginName(username));
			return authorizationInfo;
		} catch (BeansException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		try {
			String loginName = (String) authcToken.getPrincipal();
			User user = userService.findUserByLoginName(loginName);
			if (user == null) {
				throw new AuthorizationException();
			}
			if (user.getIsDelete().booleanValue()) {
				throw new UnknownAccountException();
			}
			if (user.getStatus() == User.Status.LOCKED) {
				throw new LockedAccountException();
			}
			if (userService.isRootUser(loginName)) {
				logger.info("超级管理员");
				return new SimpleAuthenticationInfo("root", user.getPassword(),
						ByteSource.Util.bytes("root" + user.getPassword()),
						getName());
			}
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo.setRoles(userService.findRolesByLoginName(loginName));
			Set<String> set = userService.findPermissionsByLoginName(loginName);
			for (String string : set) {
				System.out.println(string);
			}
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
					loginName, user.getPassword(), getName());
			authorizationInfo.setStringPermissions(userService
					.findPermissionsByLoginName(loginName));
			return authenticationInfo;
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}

}
