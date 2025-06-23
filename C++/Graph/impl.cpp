// Use of Arrays and Structures
// 2 Implementation of Searching Algorithms
// 3 Implementation of Sorting Algorithms – Part 1
// • Bubble Sort
// • Insertion Sort
// • Selection Sort
// • Counting Sort
// • Radix Sort
// 4 Implementation of Sorting Algorithms – Part 2
// • Merge Sort
// • Quick Sort
// 5 Implementation of operations on Single Linked List.
// 6 Implementation of operations on Stack and Queue ADTs.
// 7 Implementation of various applications of Stack data structures.
// 8 Implementation of various applications of Queue data structures.
// 9 Implementation of tree traversals.
// • Pre-order
// • In-order
// 33
// 10 11 12 13 14 • Post-order
// Implementation of Binary Search Tree operations.
// • Insert
// • Delete
// • Search
// Implementation of Priority queue using Heap.
// Implementation of Hashing Techniques.
// • Chaining
// • Linear Probing
// Implementation of Breadth First Search (BFS) and Depth First Search (DFS) on a graph.
// Solve the single-source shortest path problem using Dijkstra‘s algorithm.

#include <iostream>
#include <vector>
#include <queue>
#include <stack>
using namespace std;

struct Edge {
    int src, des, wt;
    Edge(int s, int d, int w) : src(s), des(d), wt(w) {}
};

typedef vector<Edge> GraphList;
typedef vector<GraphList> Graph;

// ---------- Graph Creation ----------
void createGraph(Graph &graph) {
    graph.resize(4);
    graph[0].push_back(Edge(0, 1, 10));
    graph[0].push_back(Edge(0, 2, 15));
    graph[0].push_back(Edge(0, 3, 30));
    
    graph[1].push_back(Edge(1, 0, 10));
    graph[1].push_back(Edge(1, 3, 40));

    graph[2].push_back(Edge(2, 0, 15));
    graph[2].push_back(Edge(2, 3, 50));

    graph[3].push_back(Edge(3, 1, 40));
    graph[3].push_back(Edge(3, 2, 50));
}

// ---------- BFS ----------
void bfsUtil(const Graph &graph, vector<bool> &vis, int start) {
    queue<int> q;
    q.push(start);

    while (!q.empty()) {
        int curr = q.front(); q.pop();
        if (!vis[curr]) {
            cout << curr << " ";
            vis[curr] = true;
            for (auto &e : graph[curr]) {
                q.push(e.des);
            }
        }
    }
}

void bfs(const Graph &graph) {
    vector<bool> vis(graph.size(), false);
    for (int i = 0; i < graph.size(); ++i) {
        if (!vis[i]) bfsUtil(graph, vis, i);
    }
}

// ---------- DFS ----------
void dfsUtil(const Graph &graph, int curr, vector<bool> &vis) {
    cout << curr << " ";
    vis[curr] = true;
    for (auto &e : graph[curr]) {
        if (!vis[e.des]) dfsUtil(graph, e.des, vis);
    }
}

void dfs(const Graph &graph) {
    vector<bool> vis(graph.size(), false);
    for (int i = 0; i < graph.size(); ++i) {
        if (!vis[i]) dfsUtil(graph, i, vis);
    }
}

// ---------- Has Path ----------
bool hasPath(const Graph &graph, int src, int des, vector<bool> &vis) {
    if (src == des) return true;
    vis[src] = true;
    for (auto &e : graph[src]) {
        if (!vis[e.des] && hasPath(graph, e.des, des, vis)) return true;
    }
    return false;
}

// ---------- Cycle Detection (Undirected) ----------
bool detectCycleUtil(const Graph &graph, vector<bool> &vis, int curr, int parent) {
    vis[curr] = true;
    for (auto &e : graph[curr]) {
        if (!vis[e.des]) {
            if (detectCycleUtil(graph, vis, e.des, curr)) return true;
        } else if (e.des != parent) {
            return true;
        }
    }
    return false;
}

bool detectCycle(const Graph &graph) {
    vector<bool> vis(graph.size(), false);
    for (int i = 0; i < graph.size(); ++i) {
        if (!vis[i] && detectCycleUtil(graph, vis, i, -1)) return true;
    }
    return false;
}

// ---------- Bipartite Check ----------
bool isBipartite(const Graph &graph) {
    vector<int> color(graph.size(), -1);
    queue<int> q;
    for (int i = 0; i < graph.size(); ++i) {
        if (color[i] == -1) {
            q.push(i);
            color[i] = 0;
            while (!q.empty()) {
                int curr = q.front(); q.pop();
                for (auto &e : graph[curr]) {
                    if (color[e.des] == -1) {
                        color[e.des] = 1 - color[curr];
                        q.push(e.des);
                    } else if (color[e.des] == color[curr]) {
                        return false;
                    }
                }
            }
        }
    }
    return true;
}

// ---------- Cycle Detection (Directed) ----------
bool isCycleUtil(const Graph &graph, int curr, vector<bool> &vis, vector<bool> &stack) {
    vis[curr] = stack[curr] = true;
    for (auto &e : graph[curr]) {
        if (stack[e.des]) return true;
        if (!vis[e.des] && isCycleUtil(graph, e.des, vis, stack)) return true;
    }
    stack[curr] = false;
    return false;
}

bool isCycle(const Graph &graph) {
    vector<bool> vis(graph.size(), false), stack(graph.size(), false);
    for (int i = 0; i < graph.size(); ++i) {
        if (!vis[i] && isCycleUtil(graph, i, vis, stack)) return true;
    }
    return false;
}

// ---------- Topological Sort (DFS) ----------
void topSortUtil(const Graph &graph, int curr, vector<bool> &vis, stack<int> &s) {
    vis[curr] = true;
    for (auto &e : graph[curr]) {
        if (!vis[e.des]) topSortUtil(graph, e.des, vis, s);
    }
    s.push(curr);
}

void topSort(const Graph &graph) {
    vector<bool> vis(graph.size(), false);
    stack<int> s;
    for (int i = 0; i < graph.size(); ++i) {
        if (!vis[i]) topSortUtil(graph, i, vis, s);
    }
    while (!s.empty()) {
        cout << s.top() << " "; s.pop();
    }
    cout << endl;
}

// ---------- Topological Sort (Kahn’s Algo) ----------
void calcIndegree(const Graph &graph, vector<int> &indeg) {
    for (int i = 0; i < graph.size(); ++i) {
        for (auto &e : graph[i]) {
            indeg[e.des]++;
        }
    }
}

void kahnTopoSort(const Graph &graph) {
    vector<int> indeg(graph.size(), 0);
    calcIndegree(graph, indeg);
    queue<int> q;
    for (int i = 0; i < indeg.size(); ++i) {
        if (indeg[i] == 0) q.push(i);
    }
    while (!q.empty()) {
        int curr = q.front(); q.pop();
        cout << curr << " ";
        for (auto &e : graph[curr]) {
            if (--indeg[e.des] == 0) q.push(e.des);
        }
    }
    cout << endl;
}

// ---------- Print All Paths ----------
void printAllPaths(const Graph &graph, int src, int des, string path) {
    if (src == des) {
        cout << path + to_string(des) << endl;
        return;
    }
    for (auto &e : graph[src]) {
        printAllPaths(graph, e.des, des, path + to_string(src));
    }
}

// ---------- Dijkstra ----------
struct Pair {
    int n, path;
    bool operator>(const Pair &p) const { return path > p.path; }
};

void dijkstra(const Graph &graph, int src) {
    vector<int> dist(graph.size(), INT_MAX);
    vector<bool> vis(graph.size(), false);
    priority_queue<Pair, vector<Pair>, greater<Pair>> pq;
    dist[src] = 0;
    pq.push({src, 0});

    while (!pq.empty()) {
        Pair curr = pq.top(); pq.pop();
        if (!vis[curr.n]) {
            vis[curr.n] = true;
            for (auto &e : graph[curr.n]) {
                if (dist[curr.n] + e.wt < dist[e.des]) {
                    dist[e.des] = dist[curr.n] + e.wt;
                    pq.push({e.des, dist[e.des]});
                }
            }
        }
    }

    for (int d : dist) cout << d << " ";
    cout << endl;
}

// ---------- Bellman-Ford ----------
void bellmanFord(const vector<Edge> &edges, int V, int src) {
    vector<int> dist(V, INT_MAX);
    dist[src] = 0;

    for (int i = 0; i < V - 1; ++i) {
        for (auto &e : edges) {
            if (dist[e.src] != INT_MAX && dist[e.src] + e.wt < dist[e.des]) {
                dist[e.des] = dist[e.src] + e.wt;
            }
        }
    }

    for (int d : dist) cout << d << " ";
    cout << endl;
}

// ---------- Prim’s Algorithm ----------
struct PrimPair {
    int v, cost;
    bool operator>(const PrimPair &p) const { return cost > p.cost; }
};
void prims(const Graph &graph) {
    int V = graph.size();
    vector<bool> vis(V, false);
    vector<int> parent(V, -1);  // to track the MST tree structure
    vector<int> key(V, INT_MAX);
    key[0] = 0;

    // min heap -> {weight, vertex}
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    pq.push({0, 0});  // {cost, node}

    while (!pq.empty()) {
        int u = pq.top().second;
        pq.pop();

        if (vis[u]) continue;
        vis[u] = true;

        for (auto &e : graph[u]) {
            int v = e.des;
            int wt = e.wt;

            if (!vis[v] && wt < key[v]) {
                key[v] = wt;
                pq.push({wt, v});
                parent[v] = u;
            }
        }
    }

    // Print MST edges and total cost
    int totalCost = 0;
    cout << "Edges in MST (Prim's):\n";
    for (int i = 1; i < V; ++i) {
        cout << parent[i] << " - " << i << " @ " << key[i] << "\n";
        totalCost += key[i];
    }
    cout << "Total MST cost: " << totalCost << endl;
}

// ---------- Main ----------
int main() {
    int V = 4;
    Graph graph;
    createGraph(graph);

    bfs(graph);
    cout << "\n";
    dfs(graph);
    cout << "\n";

    // vector<bool> vis(V, false);
    // cout << hasPath(graph, 0, 3, vis) << endl;

    // cout << detectCycle(graph) << endl;
    // cout << isBipartite(graph) << endl;
    // cout << isCycle(graph) << endl;
    // topSort(graph);
    // kahnTopoSort(graph);
    // printAllPaths(graph, 0, 3, "");
    // dijkstra(graph, 0);
    // vector<Edge> edgeList = {Edge(0,1,2), Edge(1,2,-4), Edge(2,3,2), Edge(4,1,-1), Edge(3,4,4)};
    // bellmanFord(edgeList, 5, 0);
    prims(graph);
}
