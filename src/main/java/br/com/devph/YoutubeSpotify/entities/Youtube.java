package br.com.devph.YoutubeSpotify.entities;

public class Youtube {
	private String url;
	private String term;
	
	public Youtube() {
		// TODO Auto-generated constructor stub
	}
	
	public Youtube(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTerm() {
		return term;
	}
	
	public void setTerm(String term) {
		this.term = term;
	}
	
	
}
