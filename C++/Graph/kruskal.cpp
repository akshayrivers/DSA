#include <iostream>
#include <vector>
#include <queue>
#include<algorithm>
#include <stack>
#include <numeric>
using namespace std;

struct Edge {
    int src, des, wt;
    Edge(int s, int d, int w) : src(s), des(d), wt(w) {}
};

// ----------- Disjoint Set (Union-Find) -----------
class DSU {
    vector<int> parent, rank;

public:
    DSU(int n) {
        parent.resize(n);
        rank.resize(n, 0);
        iota(parent.begin(), parent.end(), 0);
    }

    int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    bool unite(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return false;

        // union by rank
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[py] = px;
            rank[px]++;
        }
        return true;
    }
};

// -------------- Kruskal’s Algorithm ----------------
void kruskalMST(vector<Edge> &edges, int V) {
    sort(edges.begin(), edges.end(), [](Edge &a, Edge &b) {
        return a.wt < b.wt;
    });

    DSU dsu(V);
    int mstCost = 0;
    vector<Edge> mst;

    for (Edge &e : edges) {
        if (dsu.unite(e.src, e.des)) {
            mst.push_back(e);
            mstCost += e.wt;
        }
    }

    cout << "Edges in MST (Kruskal):\n";
    for (auto &e : mst) {
        cout << e.src << " - " << e.des << " @ " << e.wt << "\n";
    }
    cout << "Total cost: " << mstCost << "\n";
}
int main() {
    int V = 5; // number of vertices
    vector<Edge> edges;

    // Add undirected edges
    edges.push_back(Edge(0, 1, 10));
    edges.push_back(Edge(0, 2, 6));
    edges.push_back(Edge(0, 3, 5));
    edges.push_back(Edge(1, 3, 15));
    edges.push_back(Edge(2, 3, 4));

    kruskalMST(edges, V);
    return 0;
}
