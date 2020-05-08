package oracle.java.smyBatis3.model;

import java.util.Date;

public class Crawling {
	private int    crawl_no;  
	private String url;
	private String title;
	private String content;
	private Date   regdate;

	public int getCrawl_no() {
		return crawl_no;
	}
	public void setCrawl_no(int crawl_no) {
		this.crawl_no = crawl_no;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	
}
