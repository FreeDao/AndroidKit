package com.hh.kit.hotsearch;

import java.io.Serializable;

public class HotSearchBean implements Serializable {

	/**
	 * 热搜
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String userid;
	private String time;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
