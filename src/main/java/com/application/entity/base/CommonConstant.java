package com.application.entity.base;

/**
 *全局常量类
 */
public class CommonConstant {

	
	/**
     * 默认成功消息
     */
    public static final String DEF_SUCC_MSG = "操作成功";
    
    /**
     * 默认失败消息
     */
    public static final String DEF_FAIL_MSG = "操作失败";
    
    
    /**
     * 返回代码：成功
     */
    public static final int RES_CODE_OK = 200;
    
    /**
     * 返回代码：失败
     */
    public static final int RES_CODE_ERROR = 500;
    
    /**
     * 返回代码：token过期
     */
    public static final int RES_CODE_TOKEN = 600;
    
    /**
     * 返回代码：用户名重复
     */
    public static final int RES_CODE_EXISTS_USERNAME = 601;
    
    /**
     * 返回代码：手机号重复
     */
    public static final int RES_CODE_EXISTS_PHONE = 602;
    
    /**
     * 返回代码：用户信息不存在
     */
    public static final int RES_CODE_USERINFO = 603;
    
    /**
     * 返回代码：密码错误
     */
    public static final int RES_CODE_PASSWORD_MISTAKE = 604;
    
    /**
     * 返回代码：账号冻结
     */
    public static final int RES_CODE_USER_FREEZE = 605;
    
    /**
	 * 分隔符
	 */
	public static final String FILE_SEPARATOR = "/";
	
	/**
	 * 系统前缀
	 */
	public static final String SYS_PREFIX = "emoticon/";
	
	/**
	 * 字符编码
	 */
	public static final String UTF8 = "UTF-8";


	/**
	 * 环境常量
	 */
	public static final String ENVIRONMENT = "env";
	public static final String ENVIRONMENT_DEV = "dev";
	public static final String ENVIRONMENT_TEST = "test";
	public static final String ENVIRONMENT_PROD = "prod";

    
}