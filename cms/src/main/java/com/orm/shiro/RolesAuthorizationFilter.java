package com.orm.shiro;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.cako.basic.platform.role.entity.Role;
import com.cako.basic.platform.role.service.IRoleService;
import com.cako.basic.platform.user.entity.User;
import com.cako.basic.platform.user.service.IUserService;
import com.orm.commons.encryption.MD5Encryption;
import com.orm.config.InitEnvironment;

public class RolesAuthorizationFilter extends AuthorizationFilter {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;
		String loginName = (String)subject.getPrincipal();
		User user = userService.findUserByLoginName(loginName);
		if (user != null) {
			InitEnvironment environment = InitEnvironment.getInitEnvironmentInstance();
			if (user.getLoginName().equals(MD5Encryption.MD5(environment.getInitPassword()))) {
				List<Role> roles = roleService.findAll();
				for (int i = 0; i < roles.size(); i++) {
					Role role = roles.get(i);
					rolesArray[i] = role.getName();
				}
			}else {
				List<Role> roleList = user.getRoles();
				for (int i = 0; i < roleList.size(); i++) {
					Role role = roleList.get(i);
					rolesArray[i] = role.getName();
				}
			}
		}
		if (rolesArray == null || rolesArray.length == 0) {
			return true;
		}

		for (int i = 0; i < rolesArray.length; i++) {
			if (subject.hasRole(rolesArray[i])) {
				return true;
			}
		}
		return false;
	}

}
