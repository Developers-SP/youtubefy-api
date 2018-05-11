package br.com.devph.YoutubeSpotify.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devph.YoutubeSpotify.entities.Music;
import br.com.devph.YoutubeSpotify.entities.Youtube;
import br.com.devph.YoutubeSpotify.services.MusicService;

@RestController
@RequestMapping(path="/music")
public class MusicResource {
	
	@Autowired
	private MusicService service;
	
	public MusicResource(MusicService musicService) {
		this.service = musicService;
	}
	
	@GetMapping(produces="application/json")
	@ResponseStatus(code=HttpStatus.OK)
	@RequestMapping(path="/retrieve")
	public @ResponseBody ResponseEntity<?> retrieve(@RequestParam("url") String url) {
		System.out.println("\n------------");
		System.out.println("New Process");
		Youtube youtube = new Youtube(url);
		
		Music music = this.service.retrieve(youtube);
		
		if(music != null) {
			try {
				Path path = Paths.get(music.getOutput().getAbsolutePath());
				System.out.println(music.getOutput().getAbsolutePath());
				ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
				
				//return new ResponseEntity<ByteArrayResource>(resource, HttpStatus.OK);
				return ResponseEntity.ok()
						.contentLength(music.getOutput().length())
						.contentType(MediaType.parseMediaType("application/octet-steam"))
						.body(resource);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<String>("The request URL is blocked by Youtube", HttpStatus.UNAUTHORIZED);
	}
}
