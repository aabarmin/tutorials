package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph {
    private static final Pattern ROW_PATTERN = Pattern.compile("^(\\d+)\\s(\\d+)");
    private final boolean[][] data;

    /**
     * Create a graph with a defined number of vertices.
     * @param v
     */
    public Graph(int v) {
        this.data = this.createMatrix(v);
    }

    /**
     * Read a graph from the input stream.
     * @param in - graph source, source should be closed outside the graph instance.
     */
    public Graph(InputStream in) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            // read amount of vertices form a first line of input stream
            final String firstLine = reader.readLine();
            final int numberOfVertices = Integer.parseInt(firstLine);
            this.data = this.createMatrix(numberOfVertices);

            // second line contains information about number of edges,
            // but I don't need it
            reader.readLine();

            // all other lines contains information about edges, read them
            // one by one
            String row;
            while ((row = reader.readLine()) != null) {
                final Matcher matcher = ROW_PATTERN.matcher(row);
                if (matcher.matches()) {
                    final int left = Integer.parseInt(matcher.group(1));
                    final int right = Integer.parseInt(matcher.group(2));

                    this.addEdge(left, right);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean[][] createMatrix(int countOfVertices) {
        final boolean[][] data = new boolean[countOfVertices][];
        for (int i = 0; i < countOfVertices; i++) {
            data[i] = new boolean[countOfVertices];
        }
        return data;
    }

    public boolean[] getConnected(int source) {
        return this.data[source];
    }

    public int getVerticesCount() {
        return this.data.length;
    }

    public int getEdgesCount() {
        int count = 0;
        for (boolean[] row : data) {
            for (boolean cell : row) {
                if (cell) {
                    count++;
                }
            }
        }
        return count;
    }

    public void addEdge(int from, int to) {
        this.data[from][to] = true;
    }
}
