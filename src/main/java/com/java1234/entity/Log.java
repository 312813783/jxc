package com.java1234.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * <br>系统日志实体
 *
 */
@Entity
@Table(name="t_log")
public class Log {

	public final static String LOGIN_ACTION="Login Operation";
	public final static String LOGOUT_ACTION="Logout Operation";
	public final static String SEARCH_ACTION="Query Operation";
	public final static String UPDATE_ACTION="update Operation";
	public final static String ADD_ACTION="Add Operation";
	public final static String DELETE_ACTION="Delete Operation";
	
	
	@Id
	@GeneratedValue
	private Integer id; // 编号
	
	@Column(length=100)
	private String type; // 日志类型
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user; // 操作用户
	
	@Column(length=1000)
	private String content; // 操作内容
	
	@Temporal(TemporalType.TIMESTAMP) 
	private Date time; // 操作时间

	@Transient
	private Date btime; // 起始时间  搜索用到
	
	@Transient
	private Date etime; // 结束时间  搜索用到
	

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Log(String type,String content) {
		super();
		this.type = type;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonSerialize(using=CustomDateTimeSerializer.class)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getBtime() {
		return btime;
	}

	public void setBtime(Date btime) {
		this.btime = btime;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}
	
	
	
	
}
