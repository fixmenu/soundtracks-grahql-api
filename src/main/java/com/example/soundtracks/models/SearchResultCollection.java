package com.example.soundtracks.models;

import com.example.soundtracks.generated.types.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultCollection extends SearchResults{

    @JsonSetter("albums")
    public void mapAlbums(JsonNode albums) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode albumsNode = albums.get("items");

        List<AlbumSearchResult> searchResults = mapper.convertValue(albumsNode, new TypeReference<>() {});

        this.setAlbums(searchResults);
    }

    @JsonSetter("artists")
    public void mapArtists(JsonNode albums) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode albumsNode = albums.get("items");

        List<ArtistSearchResult> searchResults = mapper.convertValue(albumsNode, new TypeReference<>() {});

        this.setArtist(searchResults);
    }

    @JsonSetter("tracks")
    public void mapTracks(JsonNode albums) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode albumsNode = albums.get("items");

        List<TrackSearchResult> searchResults = mapper.convertValue(albumsNode, new TypeReference<>() {});

        this.setTracks(searchResults);
    }

    @JsonSetter("playlists")
    public void mapPlaylists(JsonNode albums) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode albumsNode = albums.get("items");

        List<PlaylistSearchResult> searchResults = mapper.convertValue(albumsNode, new TypeReference<>() {});

        this.setPlaylists(searchResults);
    }
}
