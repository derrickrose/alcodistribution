package com.pushtech.ihm;

public class Form {

	private String linkToCrawl;
	private String dataBaseLink;
	private String dataBaseUser;
	private String userPassWord;
	
	Form(){
		this.linkToCrawl = "";
		this.dataBaseLink = "";
		this.dataBaseUser = "";
		this.userPassWord = "";
	}
	
	public String getLinkToCrawl() {
		return linkToCrawl;
	}
	public void setLinkToCrawl(String linkToCrawl) {
		this.linkToCrawl = linkToCrawl;
	}
	public String getDataBaseLink() {
		return dataBaseLink;
	}
	public void setDataBaseLink(String dataBaseLink) {
		this.dataBaseLink = dataBaseLink;
	}
	public String getDataBaseUser() {
		return dataBaseUser;
	}
	public void setDataBaseUser(String dataBaseUser) {
		this.dataBaseUser = dataBaseUser;
	}
	public String getUserPassWord() {
		return userPassWord;
	}
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	
	

}
