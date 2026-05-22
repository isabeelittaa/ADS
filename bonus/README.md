Bonus Task
Implement Dijkstra's Algorithm to find the shortest path from a starting vertex to all other vertices in the graph.

Requirements
- Extend your graph to support edge weights
- Modify your Edge class to include a weight field
- Update your graph structure to store weighted edges
- Implement the method: void dijkstra(int start)

Functionality
Your implementation must:
- Take a starting vertex
- Compute the shortest distance to all other vertices
- Output the results clearly

Implementation Notes
You may use:
- Arrays for distances and visited nodes
- Simple loops (no need for priority queue)

You can use:
- Adjacency list with weights (used in this project)
- Adjacency matrix

Project Structure
bonus/
    src/
        Vertex.java    — vertex id
        Edge.java      — directed edge with weight
        Graph.java     — weighted adjacency list + dijkstra(int start)
        Main.java      — sample graph and demo
    README.md


Class Descriptions
| Class   | Description
| Vertex  | Stores vertex id
| Edge    | Directed edge with source, destination, and weight
| Graph   | Weighted adjacency list; implements dijkstra(int start)
| Main    | Builds sample graph and runs Dijkstra from vertex 0

Algorithm
1. Initialize dist[] to infinity and visited[] to false; set dist[start] = 0.
2. Repeat |V| times: pick the unvisited vertex with minimum distance (linear scan).
3. Mark it visited and relax outgoing edges: if dist[u] + weight < dist[v], update dist[v].
4. Print shortest distance from start to every vertex.

Time complexity: O(V²) (no priority queue).

How to Run
From the bonus directory:
```bash
javac -d out src/*.java
java -cp out Main
```

Example Output
```
--- Weighted adjacency list ---
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

Sample Graph
```
0 --4--> 1 --1--> 3 --3--> 4
|        ^
1        |
v        2
2 --2----+
 \--5--> 3
```

 Vertex | Shortest distance from 0 
 0 | 0 
 1 | 3 (path: 0 → 2 → 1) 
 2 | 1 
 3 | 4 (path: 0 → 2 → 1 → 3) 
 4 | 7 
