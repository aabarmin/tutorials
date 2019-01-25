package graph;

public class Search {
    private final boolean[] marked;
    private final Graph graph;

    /**
     * Search for the {search} inside the {graph}.
     * @param graph graph to find through
     * @param search value to search for
     */
    public Search(Graph graph, int search) {
        this.marked = new boolean[graph.getVerticesCount()];
        this.graph = graph;
        // mark all the vertices connected to the given
        this.mark(search);
    }

    private void mark(int start) {
        final boolean[] connected = this.graph.getConnected(start);
        for (int i = 0; i < connected.length; i++) {
            if (connected[i] && !this.marked[i]) {
                marked[i] = true;
                this.mark(i);
            }
        }
    }

    /**
     * Is {vertex} connected to {search}.
     * @param vertex
     * @return
     */
    public boolean marked(int vertex) {
        return this.marked[vertex];
    }

    /**
     * How many vertices are connected to {search}.
     * @return
     */
    int count() {
        int count = 0;
        for (boolean b : marked) {
            if (b) {
                count++;
            }
        }
        return count;
    }
}
