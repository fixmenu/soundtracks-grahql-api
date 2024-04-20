package com.example.soundtracks.models;

import com.example.soundtracks.generated.types.PlayListWithTracks;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class MappedPlaylistWithTracks extends PlayListWithTracks {

    @JsonSetter("tracks")
    public void setTracks(JsonNode tracks) {
        ObjectMapper mapper = new ObjectMapper();
        List<MappedTrack> mappedTracks = mapper.convertValue(tracks.get("items"), new com.fasterxml.jackson.core.type.TypeReference<>() {});
        this.setTracks(mappedTracks.stream().map(MappedTrack::getTrack).toList());
    }
}
