package br.com.devph.YoutubeSpotify.entities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

public class Music {
	
	private String id;
	private File folder;
	private URL inputUrl;  
	private File output;
	
	public Music() {
		// TODO Auto-generated constructor stub
	}
	
	public Music(String inputUrl) {
		this.id = UUID.randomUUID().toString();
		this.setInputUrl(inputUrl);
		this.folder = new File("/home/pdib/Development/java/youtube-spotify/YoutubeSpotify/src/main/resources/videos/" + this.id);
	}
	
	public String getId() {
		return this.id;
	}
	
	public File getFolder() {
		return this.folder;
	}
	
	public URL getInputUrl() {
		return this.inputUrl;
	}
	
	public void setInputUrl(String inputUrl) {
		try {
			this.inputUrl = new URL(inputUrl);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			this.inputUrl = null;
		}
	}
	
	public File getOutput() {
		return this.output;
	}
	
	public void setOutput(File output) {
		this.output = output;
	}
	
	@Override
	public String toString() {
		
		return "{ id: " + this.id + 
				", url: " + this.inputUrl.toString() + 
				", folder: " + this.folder.toString() + 
				" }";
	}
	
	
}
