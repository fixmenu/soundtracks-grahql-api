package com.example.soundtracks.spotify.api.auth;

import com.example.soundtracks.spotify.config.SpotifyCredentials;
import com.example.soundtracks.spotify.model.AccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class SpotifyAuth {
    private final RestClient spotifyAuthClient;
    private final SpotifyCredentials spotifyCredentials;

    public AccessToken getAccessToken() {
        return spotifyAuthClient
                .post()
                .uri("/api/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " + spotifyCredentials.encodedCredentials())
                .body("grant_type=client_credentials")
                .retrieve()
                .body(AccessToken.class);
    }
}
