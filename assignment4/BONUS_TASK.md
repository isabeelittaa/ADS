# Bonus Task: Dijkstra's Algorithm (Shortest Path)

Implement Dijkstra's Algorithm to find the shortest path from a starting vertex to all other vertices in the graph.

## Requirements

- Extend your graph to support edge weights
- Modify your `Edge` class to include a `weight` field
- Update your graph structure to store weighted edges
- Implement the method: `void dijkstra(int start)`

## Functionality

Your implementation must:

- Take a starting vertex
- Compute the shortest distance to all other vertices
- Output the results clearly

## Implementation Notes

You may use:

- Arrays for distances and visited nodes
- Simple loops (no need for priority queue)

You can use:

- Adjacency list with weights **(used in this project)**
- Adjacency matrix

---

## Project Files

| File | Role |
|------|------|
| `src/Edge.java` | `weight` field, constructors, `getWeight()` |
| `src/Graph.java` | Weighted adjacency list, `addEdge(from, to, weight)`, `dijkstra(int start)` |
| `src/Bonus.java` | Entry point — builds sample weighted graph and runs Dijkstra |

## How It Works

### Weighted edges

- `Graph` stores `Map<Integer, List<Edge>>` (adjacency list with weights).
- `addEdge(from, to, weight)` creates a weighted `Edge`.
- `addEdge(from, to)` still works for the main assignment (default weight `1`).

### `dijkstra(int start)`

1. Initialize `dist[]` to infinity and `visited[]` to false; set `dist[start] = 0`.
2. Repeat `|V|` times: pick the unvisited vertex with minimum distance (simple loop, no priority queue).
3. Mark it visited and relax each outgoing edge: if `dist[u] + weight < dist[v]`, update `dist[v]`.
4. Print shortest distance from `start` to every vertex (or `unreachable`).

Time complexity: **O(V²)** with the array + linear minimum search approach.

## How to Run

From the `assignment4` directory:

```bash
javac -d out src/*.java
java -cp out Bonus
```

### Example output

```
=== Bonus: Dijkstra's Algorithm (weighted shortest paths) ===

--- Adjacency list ---
0 -> [1(w=4), 2(w=1)]
1 -> [3(w=1)]
2 -> [1(w=2), 3(w=5)]
3 -> [4(w=3)]
4 -> []

Dijkstra shortest distances from vertex 0:
  0 -> 0: 0
  0 -> 1: 3
  0 -> 2: 1
  0 -> 3: 4
  0 -> 4: 7
```

## Sample Graph

```
0 --4--> 1 --1--> 3 --3--> 4
|        ^
1        |
v        2
2 --2----+
 \--5--> 3
```

Shortest paths from vertex `0`:

| Vertex | Distance | Path |
|--------|----------|------|
| 0 | 0 | 0 |
| 1 | 3 | 0 → 2 → 1 |
| 2 | 1 | 0 → 2 |
| 3 | 4 | 0 → 2 → 1 → 3 |
| 4 | 7 | 0 → 2 → 1 → 3 → 4 |
