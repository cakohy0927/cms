package com.cako.project.column.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cako.basic.platform.attachment.entity.Attachment;
import com.cako.project.column.entity.Column;
import com.cako.project.column.entity.News;
import com.orm.commons.service.CakoHyJpaRepostiory;

public interface NewsDao extends CakoHyJpaRepostiory<News, String> {

	/**
	 * 根据新闻id查询栏目
	 * @param id
	 * @return
	 */
	@Query("select c from News n join n.column c where n.id = ?1")
	public Column getColumnByNewsId(String id);

	/**
	 * 根据新闻id查询图片集合
	 * @param id
	 * @return
	 */
	@Query("select v from News n join n.versions v where n.id = ?1")
	public List<Attachment> getVersionList(String id);
	
}
