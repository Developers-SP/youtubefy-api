package br.com.devph.YoutubeSpotify.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.github.axet.vget.*;
import com.github.axet.vget.info.VGetParser;
import com.github.axet.vget.info.VideoInfo;
import com.github.axet.wget.DirectMultipart;
import com.github.axet.wget.RetryWrap;
import com.github.axet.wget.SpeedInfo;
import com.github.axet.wget.info.*;

import br.com.devph.YoutubeSpotify.entities.Music;

public class MusicUtils {
	
	public static Music download(Music music) {
		configure();
		
		try {
			
			music.getFolder().mkdir();
			
			VGet downloader = new VGet(music.getInputUrl(), music.getFolder());
			
			downloader.download();
			System.out.println("Downloaded");
			
			encode(music.getFolder());
			
			
			File newFile = getFile(music.getFolder());
			music.setOutput(newFile);
			
			return music;
		} catch (Exception e) {
			System.out.println(e.getMessage());			
		}
		
		return null;
	}
	
	public static void configure() {
		DirectMultipart.THREAD_COUNT = 3;
        SpeedInfo.SAMPLE_LENGTH = 1000;
        SpeedInfo.SAMPLE_MAX = 20;
        BrowserInfo.USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.97 Safari/537.36";
        DownloadInfo.PART_LENGTH = 5 * 1024 * 1024; // bytes
        URLInfo.READ_TIMEOUT = 5 * 1000; // milliseconds
        URLInfo.CONNECT_TIMEOUT = 5 * 1000; // milliseconds
        RetryWrap.RETRY_COUNT = 5; /// 5 times then fail or -1 for infinite
        RetryWrap.RETRY_DELAY = 3; // seconds between retries

	}
	
	public static File getFile(File folder) {
		File[] files = folder.listFiles();
		
		if(files[0].isFile())
			return files[0];
		
		return null;
		
	}
	
	public static void encode(File folder) {
		File file = getFile(folder);
		file = fixName(file);
		System.out.println("File: " + file.getPath());
		if(file != null) {
			String[] command = new String[] {
					"ffmpeg", "-i", file.getPath(),
					"-vn", "-ab", "128k", "-ar", "44100", "-y",
					file.getPath().replace(".webm", ".mp3").replace(".mp4", ".mp3")
					
			};
			ProcessBuilder process = new ProcessBuilder(command);
			//String command = "ffmpeg -i '" + file.getPath() + "' -vn -ab 128k -ar 44100 -y '" + file.getPath().replace(".webm", ".mp3") + "' ";
			
			try {
				Process waiter = process.start();
				waiter.waitFor();
				System.out.println("Encoded");
				
				file.delete();
				System.out.println("Removed Video");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static File fixName(File folder) {
		File renamedFile = new File(folder.getPath().replace(" ", "_"));
		folder.renameTo(renamedFile);
		
		return renamedFile;
	}
			
}
