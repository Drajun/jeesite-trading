package org.smart4j.framework;

/**
 * @author   李家俊(Drajun)
 * @Date     2018年3月28日下午7:11:40
 * @QQ       1071211919
 * @Emali    longlou.d@foxmail.com
 * @Describe 提供相关配置项常量
 */
public interface ConfigConstant {
	String CONFIG_FILE = "smart.properties";
	
	String JDBC_DRIVER = "smart.framework.jdbc.driver";
	String JDBC_URL = "smart.framework.jdbc.url";
	String JDBC_USERNAME = "smart.framework.jdbc.username";
	String JDBC_PASSWORD = "smart.framework.jdbc.password";
	
	String APP_BASE_PACKAGE = "smart.framework.app.base_package";
	String APP_JSP_PATH = "smart.framework.app.jsp_path";
	String APP_ASSET_PATH = "smart.framework.app.asset_path";
	
}
