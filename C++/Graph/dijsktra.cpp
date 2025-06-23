#include <iostream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

typedef pair<int, int> pii; // (distance, vertex)

class Graph {
private:
    int V; // number of vertices
    vector<vector<pii>> adj; // adjacency list: (neighbor, weight)

public:
    Graph(int V) {
        this->V = V;
        adj.resize(V);
    }

    void addEdge(int u, int v, int weight) {
        adj[u].push_back({v, weight});
        adj[v].push_back({u, weight}); // remove this line for directed graph
    }

    void dijkstra(int src) {
        vector<int> dist(V, INT_MAX);
        priority_queue<pii, vector<pii>, greater<pii>> pq;

        dist[src] = 0;
        pq.push({0, src});

        while (!pq.empty()) {
            int u = pq.top().second;
            int d = pq.top().first;
            pq.pop();

            for (auto [v, weight] : adj[u]) {
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.push({dist[v], v});
                }
            }
        }

        // Print shortest distances
        cout << "Shortest distances from vertex " << src << ":\n";
        for (int i = 0; i < V; i++) {
            cout << "To " << i << ": ";
            if (dist[i] == INT_MAX)
                cout << "Unreachable\n";
            else
                cout << dist[i] << "\n";
        }
    }
};
int main() {
    Graph g(6); // Graph with 6 vertices (0 to 5)

    g.addEdge(0, 1, 4);
    g.addEdge(0, 2, 1);
    g.addEdge(2, 1, 2);
    g.addEdge(1, 3, 1);
    g.addEdge(2, 3, 5);
    g.addEdge(3, 4, 3);
    g.addEdge(4, 5, 1);

    g.dijkstra(0); // Find shortest paths from node 0

    return 0;
}
