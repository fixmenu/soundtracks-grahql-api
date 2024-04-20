package com.example.soundtracks.spotify.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
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