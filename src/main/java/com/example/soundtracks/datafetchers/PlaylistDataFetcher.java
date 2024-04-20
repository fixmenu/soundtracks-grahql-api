package com.example.soundtracks.datafetchers;

import com.example.soundtracks.datasources.SpotifyClient;
import com.example.soundtracks.generated.types.*;
import com.example.soundtracks.models.*;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.graphql.dgs.InputArgument;

import java.util.Objects;

@DgsComponent
public class PlaylistDataFetcher {
    private final SpotifyClient spotifyClient;

    @Autowired
    public PlaylistDataFetcher(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }

    @DgsQuery
    public List<MappedPlaylist> featuredPlaylists() {
        PlaylistCollection response = spotifyClient.featuredPlaylistsRequest();
        return response.getPlaylists();
    }

    ;

    @DgsQuery
    public MappedPlaylistWithTracks playlist(@InputArgument String id) {
        return spotifyClient.playlistRequest(id);
    }

    @DgsQuery
    public SearchResultCollection search(@InputArgument SearchFilter filter) {
        return spotifyClient.search(filter);
    }

    @DgsMutation
    public AddItemsToPlaylistPayload addItemsToPlaylist(@InputArgument AddItemsToPlaylistInput input) {
        String playlistId = input.getPlaylistId();
        List<String> uris = input.getUris();

        Snapshot snapshot = spotifyClient.addItemsToPlaylist(playlistId, String.join(",", uris));

        AddItemsToPlaylistPayload payload = new AddItemsToPlaylistPayload();

        if (snapshot != null) {
            String snapshotId = snapshot.id();
            if (Objects.equals(snapshotId, playlistId)) {
                PlayListWithTracks playlist = new PlayListWithTracks();
                playlist.setId(playlistId);

                payload.setCode(200);
                payload.setMessage("success");
                payload.setSuccess(true);
                payload.setPlaylist(playlist);

                return payload;
            }
        }
        payload.setCode(500);
        payload.setMessage("could not update playlist");
        payload.setSuccess(false);
        payload.setPlaylist(null);

        return payload;
    }

    @DgsData(parentType = "AddItemsToPlaylistPayload", field = "playlist")
    public MappedPlaylistWithTracks getPayloadPlaylist(DgsDataFetchingEnvironment dfe) {
        AddItemsToPlaylistPayload payload = dfe.getSource();
        PlayListWithTracks playlist = payload.getPlaylist();

        if (playlist != null) {
            String playlistId = playlist.getId();
            return spotifyClient.playlistRequest(playlistId);
        }

        return null;
    }

}
