package com.example.soundtracks.spotify.config;

import com.example.soundtracks.spotify.SpotifyTokenStore;
import com.example.soundtracks.spotify.model.AccessToken;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spotify")
@Slf4j
public class SpotifyClientConfig {

    private String authUrl;
    private String apiUrl;

    @Bean
    RestClient spotifyAuthClient() {
        return RestClient.builder().baseUrl(authUrl).build();
    }

    @Bean
    RestClient spotifyApiClient() {
        return RestClient.builder().baseUrl(apiUrl).build();
    }

}
