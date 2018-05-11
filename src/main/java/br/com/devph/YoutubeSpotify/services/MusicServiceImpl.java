package br.com.devph.YoutubeSpotify.services;

import org.springframework.stereotype.Service;

import br.com.devph.YoutubeSpotify.entities.Music;
import br.com.devph.YoutubeSpotify.entities.Youtube;
import br.com.devph.YoutubeSpotify.utils.MusicUtils;

@Service
public class MusicServiceImpl implements MusicService{

	@Override
	public Music retrieve(Youtube youtube) {
		
		Music music = new Music(youtube.getUrl());
System.out.println(music);
		music = MusicUtils.download(music);
		
		if(music != null) {
			return music;
		}
		
		return null;
	}

}
