import java.util.*;

public class Graph {
    private final Map<Integer, Vertex> vertices = new HashMap<>();
    private final Map<Integer, List<Edge>> adjacency = new HashMap<>();

    public void addVertex(Vertex v) {
        int id = v.getId();
        vertices.put(id, v);
        adjacency.putIfAbsent(id, new ArrayList<>());
    }

    public void addEdge(int from, int to, int weight) {
        requireVertex(from);
        requireVertex(to);
        adjacency.get(from).add(new Edge(vertices.get(from), vertices.get(to), weight));
    }

    private void requireVertex(int id) {
        if (!vertices.containsKey(id)) {
            throw new IllegalArgumentException("Unknown vertex id: " + id);
        }
    }

    public void printGraph() {
        System.out.println("--- Weighted adjacency list ---");
        List<Integer> ids = new ArrayList<>(vertices.keySet());
        Collections.sort(ids);

        for (int id : ids) {
            List<String> neighbors = new ArrayList<>();
            for (Edge edge : adjacency.get(id)) {
                neighbors.add(edge.getDestination().getId() + "(w=" + edge.getWeight() + ")");
            }
            System.out.println(id + " -> " + neighbors);
        }

        System.out.println();
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public void dijkstra(int start) {
        requireVertex(start);

        int size = maxVertexId() + 1;
        int[] dist = new int[size];
        boolean[] visited = new boolean[size];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        dist[start] = 0;

        for (int count = 0; count < getVertexCount(); count++) {
            int u = minDistanceVertex(dist, visited);
            if (u == -1 || dist[u] == Integer.MAX_VALUE) {
                break;
            }

            visited[u] = true;

            for (Edge edge : adjacency.get(u)) {
                int v = edge.getDestination().getId();
                int weight = edge.getWeight();
                if (!visited[v] && dist[u] != Integer.MAX_VALUE
                        && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        System.out.println("Dijkstra shortest distances from vertex " + start + ":");
        List<Integer> ids = new ArrayList<>(vertices.keySet());
        Collections.sort(ids);

        for (int id : ids) {
            if (dist[id] == Integer.MAX_VALUE) {
                System.out.println("  " + start + " -> " + id + ": unreachable");
            } else {
                System.out.println("  " + start + " -> " + id + ": " + dist[id]);
            }
        }
        System.out.println();
    }

    private int minDistanceVertex(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minVertex = -1;

        for (int id : vertices.keySet()) {
            if (!visited[id] && dist[id] <= min) {
                min = dist[id];
                minVertex = id;
            }
        }

        return minVertex;
    }

    private int maxVertexId() {
        int max = 0;
        for (int id : vertices.keySet()) {
            if (id > max) {
                max = id;
            }
        }
        return max;
    }
}
