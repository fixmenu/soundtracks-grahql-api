package com.example.soundtracks.spotify.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpotifyCredentialsTest {
    @Autowired
    private SpotifyCredentials spotifyCredentials;

    @Test
    void clientId() {
        assertNotNull(spotifyCredentials.clientId());
    }

    @Test
    void secretId() {
        assertNotNull(spotifyCredentials.clientSecret());
    }
}