package com.cako.basic.platform.attachment.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cako.basic.platform.attachment.entity.Attachment;

public interface AttachmentService {

	
	public Attachment save(Attachment version);
	
	public Attachment findOne(String id);
	
	public List<Attachment> findAll();
	
	public List<Attachment> getVersions(List<String> versionIds);
	
	public Page<Attachment> queryPageByMap(Map<String, Object> map,Pageable pageable);
}
