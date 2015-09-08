package com.cako.basic.platform.attachment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cako.basic.platform.attachment.dao.AttachmentDao;
import com.cako.basic.platform.attachment.entity.Attachment;
import com.cako.basic.platform.attachment.service.AttachmentService;

@Component
@Transactional(readOnly = false)
public class VersionServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;
	
	@Override
	public Attachment findOne(String id) {
		return attachmentDao.findOne(id);
	}

	@Override
	public List<Attachment> findAll() {
		return attachmentDao.findAll();
	}

	@Override
	public Attachment save(Attachment version) {
		return attachmentDao.saveAndFlush(version);
	}

	@Override
	public Page<Attachment> queryPageByMap(Map<String, Object> map, Pageable pageable) {
		return attachmentDao.queryPageByMap(map, pageable);
	}

	@Override
	public List<Attachment> getVersions(List<String> versionIds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_in", versionIds);
		return attachmentDao.queryByMap(paramMap );
	}

}
