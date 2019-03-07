package model.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件 生成数据库连接
 * 
 * @author R.kyo
 *
 */
public class Connector {
	private static DbHelper connector = null;
	static int label = 0;

	public void loadConfig() {
		label = 10;
		// 读取数据库配置文件
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dbConfig.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			System.out.println("我是Connector中得loadConfig方法" + label);
			e.printStackTrace();
		}
		String ipAddress = p.getProperty("ip"); // ip:localhost
		int port = Integer.parseInt(p.getProperty("port")); // 端口号3306
		String user = p.getProperty("user"); // user:root
		String pwd = p.getProperty("pwd"); // pwd:root
		String dbName = p.getProperty("dbName"); // 数据库名字:music
		System.out.println("啊啊啊啊啊啊啊啊啊啊啊" + dbName);
		// 根据提供的参数连接数据库
		Connector.connector = DbHelper.getInstance();
		Connector.connector.connSQL(user, pwd, dbName, ipAddress, port);
	}

	public static DbHelper getInstance() {
		label = 20;
		if (Connector.connector == null) {
			Connector c = new Connector();
			c.loadConfig();
		}
		return Connector.connector;
	}

	/*
	 * public static void main(String args[]){ DbHelper db =
	 * Connector.getInstance(); db.showTable("app_Music"); }
	 */
}
