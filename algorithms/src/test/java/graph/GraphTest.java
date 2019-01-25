package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    @Test
    void readGraphFromFile() throws Exception {
        try (final InputStream resource = getClass().getResourceAsStream("/largeGraph.txt")) {
            final Graph graph = new Graph(resource);
            assertAll(
                    () -> assertNotNull(graph),
                    () -> assertEquals(250, graph.getVerticesCount()),
                    () -> assertEquals(1273, graph.getEdgesCount())
            );
        }
    }

    @Test
    void searchThroughGraphFromFile() throws Exception {
        try (final InputStream resource = getClass().getResourceAsStream("/largeGraph.txt")) {
            final Graph graph = new Graph(resource);
            final Search search = new Search(graph, 244);

            assertAll(
                    () -> assertTrue(search.marked(246)),
                    () -> assertEquals(1, search.count())
            );
        }
    }
}
