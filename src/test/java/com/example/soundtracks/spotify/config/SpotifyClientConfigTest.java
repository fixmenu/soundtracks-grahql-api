package com.example.soundtracks.spotify.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpotifyClientConfigTest {
    @Autowired
    private SpotifyClientConfig spotifyClientConfig;

    @Test
    void getAuthUrl() {
        assertEquals("https://accounts.spotify.com", spotifyClientConfig.getAuthUrl());
    }

    @Test
    void getApiUrl() {
        assertEquals("https://api.spotify.com/v1", spotifyClientConfig.getApiUrl());
    }
}