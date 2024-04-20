package com.example.soundtracks;

import com.example.soundtracks.spotify.config.SpotifyClientConfig;
import com.example.soundtracks.spotify.config.SpotifyCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.soundtracks.spotify.config")
public class SoundtracksApplication {

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		SpringApplication.run(SoundtracksApplication.class, args);
	}
}
