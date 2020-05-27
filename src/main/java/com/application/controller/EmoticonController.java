package com.application.controller;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.application.config.OssConfig;
import com.application.entity.base.CommonConstant;
import com.application.entity.base.ResEnv;
import com.application.util.DateUtil;
import com.application.util.OssUtil;

@RestController
@RequestMapping("/emoticon")
public class EmoticonController {
	
	@Autowired
	OssConfig ossConfig;
	
	@PostMapping
	public ResEnv<?> upload(String tag, MultipartFile file) {
		
		OSSClient client = OssUtil.getInstance(ossConfig);
		String webPath = "";
		try {
			String originalName = file.getOriginalFilename();
			String path = CommonConstant.SYS_PREFIX + DateUtil.format(new Date(), DateUtil.dateFormat3) + CommonConstant.FILE_SEPARATOR + UUID.randomUUID() + 
																originalName.substring(originalName.lastIndexOf("."));
			webPath = OssUtil.upload(client, path, file.getBytes(), ossConfig.getBucketName());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.shutdown();
		}
		return ResEnv.success(CommonConstant.DEF_SUCC_MSG, webPath);
		
	}

}
