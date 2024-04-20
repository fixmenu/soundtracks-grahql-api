package com.example.soundtracks.models;

import com.example.soundtracks.generated.types.AlbumSearchResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MappedAlbumSearchResult extends AlbumSearchResult {
}
