package model;

import java.sql.ResultSet;
import model.db.Connector;
import model.db.DbHelper;

/**
 * 数据库音乐模型
 * 
 * @author R.kyo
 */
public class MusicModel {
	private String name; // 音乐名
	private String src; // 音乐资源链接
	private String lyric; // 音乐类型
	private int music_id; // 音乐id
	private int listeners; // 收听的人数
	static int label = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public int getMusic_id() {
		return music_id;
	}

	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}

	public int getListeners() {
		return listeners;
	}

	public void setListeners(int listeners) {
		this.listeners = listeners;
	}

	/**
	 * 获取音乐列表
	 * 
	 * @return
	 */
	public static String getLatestMusic() {
		label = 10;
		DbHelper connector = Connector.getInstance();
		ResultSet rs = (ResultSet) connector
				.executeQuery("SELECT name, music_id, singer_name, src FROM app_singerRmusic"
						+ " NATURAL JOIN app_Singer NATURAL JOIN app_Music LIMIT 20");
		return DbHelper.resultSetToJson(rs);
	}

	/**
	 * 获取音乐排名列表
	 * 
	 * @param l
	 * @return
	 */
	public static String getRankMusic(int l) {
		label = 20;
		l++;
		DbHelper connector = Connector.getInstance();
		ResultSet rs = (ResultSet) connector.executeQuery(
				"	SELECT name, music_id FROM app_musicRclass "
						+ "NATURAL JOIN app_Music NATURAL JOIN app_Class WHERE class_id=? ORDER BY listeners DESC LIMIT 10",
				l);
		return DbHelper.resultSetToJson(rs);
	}

	/**
	 * 获取单条音乐详细信息
	 * 
	 * @param id
	 * @return
	 */
	public static String getMusicInfo(String id) {
		label = 30;
		DbHelper connector = Connector.getInstance();
		ResultSet rs = (ResultSet) connector.executeQuery("SELECT name, singer_name, src, music_id FROM "
				+ "app_singerRmusic NATURAL JOIN app_Music NATURAL JOIN app_Singer WHERE music_id=?", id);
		return DbHelper.resultSetToJson(rs);
	}

	public static String getMusicInfoSrc(String src) {
		label = 40;
		DbHelper connector = Connector.getInstance();
		ResultSet rs = (ResultSet) connector.executeQuery("SELECT name, singer_name, src, music_id FROM "
				+ "app_singerRmusic NATURAL JOIN app_Music NATURAL JOIN app_Singer WHERE src=?", src);
		return DbHelper.resultSetToJson(rs);
	}

	public static String colMusic(String uid, String mid) {
		label = 50;
		System.out.println(uid + " " + mid);
		DbHelper connector = Connector.getInstance();
		Boolean hasCollected = connector.isExist("SELECT id FROM app_collection WHERE user_id=? AND music_id=?", uid,
				mid);
		if (hasCollected) {
			return "已收藏过该歌曲";
		}
		connector.executeUpdate("INSERT INTO app_collection (user_id, music_id, colDate) VALUES (?, ?, NOW())", uid,
				mid);
		return "收藏成功";

	}

	public static void delColMusic(String uid, String mid) {
		System.out.println(uid + " " + mid);
		DbHelper connector = Connector.getInstance();
		connector.executeUpdate("DELETE FROM app_collection WHERE user_id = ? AND music_id=?", uid, mid);
	}

	/*
	 * public static void main(String args[]) { //TODO this need change DbHelper
	 * connector = Connector.getInstance(); Boolean hasCollected = connector.
	 * isExist("SELECT id FROM app_collection WHERE user_id=? AND music_id=?",
	 * "1575788652", "24"); if (hasCollected) { System.out.print("已收藏过该歌曲"); }
	 * connector.
	 * executeUpdate("INSERT INTO app_collection (user_id, music_id, colDate) VALUES (?, ?, NOW())"
	 * , "1575788652", "24"); System.out.print("收藏成功"); }
	 */
}
