# Graph
A series of Nodes (Vertices) with some number of connections, 
with no strict rules about connections, directionality and structure.

## Terms

- **cycle** - when you start at Node X, visit Y, Z, W and end up back at X
- **acyclic** - there are no cycles in a graph
- **connected** - every node can reach every other node
- **directed** - when there is a direction to the connections (arrows)
- **undirected** - no directions, bidirectional
- **weighted** - edges have weights associated with them (e.g. Google Maps)
- **dag** - directed, acyclic graph

### Implementation Terms

- **Vertex** - a canonical name for a Node in a graph
- **Edge** - a connection betwixt two vertices

## Big O
Big O is often expressed in terms of **V** (Vertices) and **E** (Edges)

## Implementations

### Adjacency List

- Vertices have indexes
- List stores complex objects for vertices
  - For each Vertex there's another list with tuples/objects representing connections & weights

```typescript
[
    [{ to: 1, w: 5}],
    [],
    [{ to: 2, w: 10}, { to: 3, w: 5 }],
    [{ to: 1 }]
]
```

### Adjacency Matrix
Memory-heavy, **O(N^2)**, rarely used in practice.

```typescript
[ // 0, 1, 2, 3
    [0, 10, 0, 5] // Vertex 0 has a connection to Vertex 1 with weigh 10 and so on
    [w, w, w, w] // Vertex 1
    [w, w, w, w] // Vertex 2
    [w, w, w, w] // Vertex 3
]
```