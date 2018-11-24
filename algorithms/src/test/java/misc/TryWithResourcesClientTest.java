package misc;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TryWithResourcesClientTest {
    @Test
    void testException() throws Exception {
        assertThrows(RuntimeException.class, () -> {
            try (TryWithResourcesClient client = new TryWithResourcesClient()) {
                throw new RuntimeException("Internal exception");
            }
        }, () -> "Internal exception");
    }
}
