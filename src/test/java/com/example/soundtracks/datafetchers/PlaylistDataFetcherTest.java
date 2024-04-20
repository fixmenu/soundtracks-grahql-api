package com.example.soundtracks.datafetchers;

import com.example.soundtracks.datasources.SpotifyClient;
import com.example.soundtracks.generated.client.FeaturedPlaylistsGraphQLQuery;
import com.example.soundtracks.generated.client.FeaturedPlaylistsProjectionRoot;
import com.example.soundtracks.models.MappedPlaylist;
import com.example.soundtracks.models.PlaylistCollection;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DgsAutoConfiguration.class, PlaylistDataFetcher.class})
class PlaylistDataFetcherTest {

    @MockBean
    SpotifyClient spotifyClient;

    @Autowired
    DgsQueryExecutor queryExecutor;

    @BeforeEach
    public void before() {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/featuredPlaylist_response.json");
        try {
            PlaylistCollection collection = mapper.readValue(is, PlaylistCollection.class);
            Mockito.when(spotifyClient.featuredPlaylistsRequest()).thenReturn(collection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void featuredPlaylists() {
        GraphQLQueryRequest request = new GraphQLQueryRequest(
                new FeaturedPlaylistsGraphQLQuery.Builder().build(),
                new FeaturedPlaylistsProjectionRoot<>().name()
        );

        List<String> titles = queryExecutor.executeAndExtractJsonPath(request.serialize(), "data.featuredPlaylists[*].name");
        assertThat(titles).contains("Top Hits NL");
        assertEquals(5,titles.size());
    }

    @Test
    void playlist() {
    }

    @Test
    void addItemsToPlaylist() {
    }

    @Test
    void getPayloadPlaylist() {
    }
}