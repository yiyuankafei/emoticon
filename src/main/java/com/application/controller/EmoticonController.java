package com.application.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.application.config.OssConfig;
import com.application.entity.Emoticon;
import com.application.entity.EmoticonExample;
import com.application.entity.base.CommonConstant;
import com.application.entity.base.ResEnv;
import com.application.service.EmoticonService;
import com.application.util.DateUtil;
import com.application.util.OssUtil;

@Controller
@RequestMapping("/emoticon")
public class EmoticonController {
	
	@Autowired
	OssConfig ossConfig;
	
	@Autowired
	EmoticonService emoticonService;	
	
	@PostMapping
	public String upload(String tag, MultipartFile file) {
		
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
		
		Emoticon emoticon = new Emoticon();
		emoticon.setTag(tag);
		emoticon.setUrl(webPath);
		
		emoticonService.insert(emoticon);
		return "upload";
		
	}
	
	@GetMapping
	@ResponseBody
	public ResEnv<?> list(String keywords) {
		
		EmoticonExample example = new EmoticonExample();
		example.createCriteria().andTagLike("%" + keywords + "%");
		
		List<Emoticon> list = emoticonService.selectByExample(example);
		
		return ResEnv.success(list);
		
	}

}
