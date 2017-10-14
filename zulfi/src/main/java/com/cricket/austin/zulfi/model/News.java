package com.cricket.austin.zulfi.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class News {

	private static final long serialVersionUID = 1L;
	private int id;
	private Date added;
	private Date featureexpire;
	private String user;
	private String title;
	private String author;
	private String article;
	private int IsFeature;
	private int IsPending;
	private String picture;
	private String picdesc;
	private String writer_info;
	private String news_year;

	private int views;
	private int DiscussID;
	private String SubTitle;
	private int MasterID;
	private int newstype;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public Date getFeatureexpire() {
		return featureexpire;
	}

	public void setFeatureexpire(Date featureexpire) {
		this.featureexpire = featureexpire;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public int getIsFeature() {
		return IsFeature;
	}

	public void setIsFeature(int isFeature) {
		IsFeature = isFeature;
	}

	public int getIsPending() {
		return IsPending;
	}

	public void setIsPending(int isPending) {
		IsPending = isPending;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPicdesc() {
		return picdesc;
	}

	public void setPicdesc(String picdesc) {
		this.picdesc = picdesc;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getDiscussID() {
		return DiscussID;
	}

	public void setDiscussID(int discussID) {
		DiscussID = discussID;
	}

	public String getSubTitle() {
		return SubTitle;
	}

	public void setSubTitle(String subTitle) {
		SubTitle = subTitle;
	}

	public int getMasterID() {
		return MasterID;
	}

	public void setMasterID(int masterID) {
		MasterID = masterID;
	}

	public int getNewstype() {
		return newstype;
	}

	public void setNewstype(int newstype) {
		this.newstype = newstype;
	}

	public String getWriter_info() {
		return writer_info;
	}

	public void setWriter_info(String writer_info) {
		this.writer_info = writer_info;
	}

	public String getNews_year() {
		return news_year;
	}

	public void setNews_year(String news_year) {
		this.news_year = news_year;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", added=" + added + ", featureexpire=" + featureexpire + ", user=" + user
				+ ", title=" + title + ", author=" + author + ", article=" + article + ", IsFeature=" + IsFeature
				+ ", IsPending=" + IsPending + ", picture=" + picture + ", picdesc=" + picdesc + ", writer_info="
				+ writer_info + ", news_year=" + news_year + ", views=" + views + ", DiscussID=" + DiscussID
				+ ", SubTitle=" + SubTitle + ", MasterID=" + MasterID + ", newstype=" + newstype + "]";
	}

}
