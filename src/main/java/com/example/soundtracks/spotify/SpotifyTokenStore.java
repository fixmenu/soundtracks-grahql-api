package com.example.soundtracks.spotify;

import com.example.soundtracks.spotify.api.auth.SpotifyAuth;
import com.example.soundtracks.spotify.model.AccessToken;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpotifyTokenStore {
    private AccessToken token;
    private final SpotifyAuth spotifyAuth;

    public SpotifyTokenStore(SpotifyAuth spotifyAuth) {
        this.spotifyAuth = spotifyAuth;
    }

    @PostConstruct
    public void init() {
        try {
            AccessToken accessToken = refreshToken();
            log.info("Access token generated: {}", accessToken);
        } catch (Exception e) {
            log.error("Failed to generate access token", e);
        }
    }

    public AccessToken refreshToken() {
        this.token = spotifyAuth.getAccessToken();
        return this.token;
    }

    public String getAccessToken() {
        return token.getAccessToken();
    }
}
