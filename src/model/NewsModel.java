package model;

import java.sql.Date;
import java.sql.ResultSet;
import model.db.Connector;
import model.db.DbHelper;

/**
 * 数据库新闻模型
 * 
 * @author R.kyo
 */
public class NewsModel {
	private String title;
	private String content;
	private int id;
	private String type;
	private Date pubDate;
	static int label = 0;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public static String getRealTimeNews() {
		label = 10;
		DbHelper connector = Connector.getInstance();
		ResultSet rs = (ResultSet) connector.executeQuery("SELECT title, id, pubDate FROM app_News LIMIT 7");
		return DbHelper.resultSetToJson(rs);
	}

	public static void main(String args[]) {
		// TODO main 测试
		label = 20;
		System.out.print(getRealTimeNews());
	}

}
