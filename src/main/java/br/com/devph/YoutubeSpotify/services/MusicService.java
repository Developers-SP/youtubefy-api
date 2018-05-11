package br.com.devph.YoutubeSpotify.services;

import br.com.devph.YoutubeSpotify.entities.Music;
import br.com.devph.YoutubeSpotify.entities.Youtube;

public interface MusicService {
	public Music retrieve(Youtube youtube);
}
