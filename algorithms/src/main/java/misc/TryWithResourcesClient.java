package misc;

public class TryWithResourcesClient implements AutoCloseable {
    @Override
    public void close() throws Exception {
        throw new RuntimeException("From close method");
    }
}
