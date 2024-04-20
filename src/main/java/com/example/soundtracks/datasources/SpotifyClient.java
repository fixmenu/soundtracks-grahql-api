package com.example.soundtracks.datasources;

import com.example.soundtracks.generated.types.SearchFilter;
import com.example.soundtracks.models.*;
import com.example.soundtracks.spotify.SpotifyTokenStore;
import com.example.soundtracks.spotify.api.auth.SpotifyAuth;
import com.example.soundtracks.spotify.model.AccessToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class SpotifyClient {
    private final RestClient spotifyApiClient;
    private final SpotifyTokenStore spotifyTokenStore;

    public PlaylistCollection featuredPlaylistsRequest() {
        return spotifyApiClient
                .get()
                .uri("/browse/featured-playlists")
                .header("Authorization", "Bearer " + spotifyTokenStore.getAccessToken())
                .retrieve()
                .body(PlaylistCollection.class);
    }

    public MappedPlaylistWithTracks playlistRequest(String playlistId) {
        return spotifyApiClient
                .get()
                .uri("/playlists/{playlist_id}", playlistId)
                .header("Authorization", "Bearer " + spotifyTokenStore.getAccessToken())
                .retrieve()
                .body(MappedPlaylistWithTracks.class);
    }

    public Snapshot addItemsToPlaylist(String playlistId, String uris) {
        return spotifyApiClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/playlists/{playlist_id}/tracks")
                        .queryParam("uris", uris)
                        .build(playlistId))
                .header("Authorization", "Bearer " + spotifyTokenStore.getAccessToken())
                .retrieve()
                .body(Snapshot.class);
    }

    public SearchResultCollection search(SearchFilter filter) {
        final String query = getQuery(filter);

        return spotifyApiClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", query)
                        .queryParam("type", filter.getType().toString())
                        .build())
                .header("Authorization", "Bearer " + spotifyTokenStore.getAccessToken())
                .retrieve()
                .body(SearchResultCollection.class);
    }

    @NotNull
    private static String getQuery(SearchFilter filter) {
        String query = filter.getText() != null ? filter.getText() : "";
        query += filter.getAlbum() != null ? "album:" + filter.getAlbum() : "";
        query += filter.getArtist() != null ? " artist:" + filter.getArtist() : "";
        query += filter.getTrack() != null ? " track:" + filter.getTrack() : "";
        query += filter.getYear() != null ? " year:" + filter.getYear() : "";
        return query;
    }
}
