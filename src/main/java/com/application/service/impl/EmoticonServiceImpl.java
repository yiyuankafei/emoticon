package com.application.service.impl;

import org.springframework.stereotype.Service;

import com.application.entity.Emoticon;
import com.application.entity.EmoticonExample;
import com.application.mapper.EmoticonMapper;
import com.application.service.EmoticonService;

@Service
public class EmoticonServiceImpl extends BaseServiceImpl<EmoticonMapper, Emoticon, EmoticonExample> implements EmoticonService{

}
