#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Edge {
    int src, des, wt;
    Edge(int s, int d, int w) : src(s), des(d), wt(w) {}
};

bool cmp(Edge &a, Edge &b) {
    return a.wt < b.wt;
}

vector<int> parent;

int find(int x) {
    if (parent[x] != x)
        parent[x] = find(parent[x]); // path compression
    return parent[x];
}

void unite(int a, int b) {
    int pa = find(a);
    int pb = find(b);
    if (pa != pb) {
        parent[pa] = pb;
    }
}

void kruskal(int V, vector<Edge> &edges) {
    sort(edges.begin(), edges.end(), cmp);
    parent.resize(V);
    for (int i = 0; i < V; i++) parent[i] = i;

    int mstCost = 0;
    cout << "Edges in MST:\n";
    for (auto &e : edges) {
        if (find(e.src) != find(e.des)) {
            unite(e.src, e.des);
            mstCost += e.wt;
            cout << e.src << " - " << e.des << " @ " << e.wt << "\n";
        }
    }
    cout << "Total MST cost: " << mstCost << endl;
}

int main() {
    int V = 5;
    vector<Edge> edges = {
        {0, 1, 10},
        {0, 2, 15},
        {1, 3, 40},
        {2, 3, 50},
        {3, 4, 20}
    };

    kruskal(V, edges);
    return 0;
}
