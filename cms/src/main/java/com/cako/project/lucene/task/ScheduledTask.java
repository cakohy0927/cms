package com.cako.project.lucene.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cako.basic.platform.user.service.IUserService;
import com.cako.project.column.entity.News;
import com.cako.project.column.service.NewsService;
import com.cako.project.lucene.index.LuceneUtil;
import com.orm.commons.exception.ServiceException;

@Component
public class ScheduledTask {
	@Autowired
	private NewsService newsService;

	@Autowired
	private IUserService userService;
	@Scheduled(cron = "0/5 * * * * ? ")
	// 间隔5秒执行
	public void taskCycle() {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			List<News> list = newsService.queryByMap(paramMap);
			LuceneUtil.createIndex(list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
