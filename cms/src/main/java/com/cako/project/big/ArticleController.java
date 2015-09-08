package com.cako.project.big;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cako.basic.platform.user.entity.User;
import com.cako.basic.platform.user.service.IUserService;
import com.cako.basic.util.MessageObject;
import com.cako.basic.util.WebUtils;
import com.cako.project.column.entity.News;
import com.cako.project.column.service.NewsService;
import com.cako.project.lucene.index.LuceneUtil;
import com.orm.commons.encryption.MD5Encryption;
import com.orm.commons.exception.LoginException;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.JsonMapper;

@Controller
@RequestMapping("/big")
public class ArticleController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/getKeyWord", method = RequestMethod.GET)
	public void getKeyWord(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = WebUtils.getRequestToMap(request);
		System.out.println(map.size());
		List<String> keyWordList = new ArrayList<String>();
		keyWordList.add("武汉");
		keyWordList.add("左边是图标右边是文本的样式,'custom': 自定义样式。须配合createItemHandler参数一起使用。");
		keyWordList.add("百度一下");
		keyWordList.add("轻松一下");
		keyWordList.add("明天下雨");
		keyWordList.add("上海东方之珠");
		keyWordList.add("海南岛上鲜花已经盛开");
		keyWordList.add("你猜猜");
		keyWordList.add("猜不出来");
		keyWordList.add("最近如何");
		keyWordList.add("如何得来");
		try {
			response.getWriter().write(new JsonMapper().toJson(keyWordList));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String keyWord = request.getParameter("keyWord");
			if (StringUtils.isNotEmpty(keyWord)) {
				LuceneUtil.query(keyWord);
				List<String> idsList = LuceneUtil.getStringIds();
				paramMap.put("id_in", idsList);
			}
			List<News> list = newsService.queryByMap(paramMap);
			model.addAttribute("list", list);
			model.addAttribute("keyWord", keyWord);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "big/article/list";
	}

	@RequestMapping(value = "/viewInfo/{id}", method = RequestMethod.GET)
	public String viewInfo(@PathVariable("id") String id, HttpServletRequest request, Model model) {
		try {
			if (StringUtils.isNotEmpty(id)) {
				News news = newsService.get(id);
				model.addAttribute("news", news);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "big/article/info";
	}
	@RequestMapping(value = "/newsList", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String newsList(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<NewsClass> newsClasses = new ArrayList<NewsClass>();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			List<News> list = newsService.queryByMap(paramMap);
			for (int i = 0; i < list.size(); i++) {
				News news = list.get(i);
				newsClasses.add(new NewsClass(news));
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		System.out.println(new JsonMapper().toJson(newsClasses));
		return new JsonMapper().toJson(newsClasses);
	}

	@RequestMapping(value = { "/android" }, method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody void android(String loginName, String password, HttpServletResponse response, HttpServletRequest request) throws LoginException,
			ServiceException {
		MessageObject message = new MessageObject();
		try {
			if (StringUtils.isEmpty(loginName)) {
				message.setResposeCode(MessageObject.ResposeCode.code_404);
				message.setResult("登陆失败");
				response.getWriter().write(new JsonMapper().toJson(message));
			} else {
				User user = userService.findUserByUsername(loginName);
				// 如果登陆成功
				if (user != null) {
					if (StringUtils.equals(user.getPassword(), MD5Encryption.MD5(password))) {
						setLogin(user.getLoginName(), user.getPassword());
						request.getSession().setAttribute("user", user);
						message.setInforamation(user.getLoginName());
						message.setResposeCode(MessageObject.ResposeCode.code_200);
						message.setResult("登陆成功");
						response.getWriter().write(new JsonMapper().toJson(message));
					} else {
						message.setResposeCode(MessageObject.ResposeCode.code_404);
						message.setResult("你输入的密码不对");
						response.getWriter().write(new JsonMapper().toJson(message));
					}
				} else {
					message.setResposeCode(MessageObject.ResposeCode.code_404);
					message.setResult("登陆名错误");
					response.getWriter().write(new JsonMapper().toJson(message));
				}
			}
		} catch (ServiceException | IOException e) {
			throw new ServiceException("查询用户失败", new Throwable());
		}
	}

	private static final void setLogin(String userId, String password) {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(userId, password);
			token.setRememberMe(true);
			currentUser.login(token);
		}
	}
}
