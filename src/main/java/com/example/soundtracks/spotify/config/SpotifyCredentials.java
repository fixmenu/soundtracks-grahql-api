package com.example.soundtracks.spotify.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;


@ConfigurationProperties(prefix = "spotify.credentials")
public record SpotifyCredentials(String clientId, String clientSecret) {
    public String encodedCredentials() {
        return java.util.Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
    }
}
