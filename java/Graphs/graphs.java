//package Graphs;
import java.util.*;
import java.util.LinkedList;
/*
 * there are many methods to create graph:
 * 1.Adjacency list:
 * we use a hashmap<int,lists> to store the values, the int value to keep track of key or vertex and the list containing its neighbors 
 * advantages - 1. no extra info 2. tc to find the neigbor is O(k)
 * 
 * we have implemented graphs through adjacency lists here too albeit we have used array list to keep track of keys(vertex) and array list to track the neighbors 
 * 2.Adjacency Matrix
 * 
 * 3.Edge list 
 * 4. Implicit graph 
 */
public class graphs {
    static class Edge{
        int src;
        int des;
        int wt;
        public Edge(int s, int d,int w){
            this.src=s;
            this.des=d;
            this.wt=w;
        }
    }
    public static void createGraph(ArrayList<Edge> graph[]){
        for (int i = 0; i < graph.length; i++) {
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));
        
        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));

        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));

        //graph[4].add(new Edge(4, 1, -1));
        //graph[4].add(new Edge(4, 5, 5));

        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));
        //graph[5].add(new Edge(5, 2, 1));
        // graph[0].add(new Edge(0, 1, 1));
        // graph[0].add(new Edge(0, 2, 1));
        // graph[1].add(new Edge(1, 3, 1));
        // graph[2].add(new Edge(2, 3, 1));
        // graph[0].add(new Edge(0, 2, 1));
        // graph[0].add(new Edge(0, 1, 1));
        // graph[0].add(new Edge(0, 2, 1));

        // graph[1].add(new Edge(1, 0, 1));
        // graph[1].add(new Edge(1, 3, 1));

        // graph[2].add(new Edge(2, 0, 1));
        // graph[2].add(new Edge(2, 4, 1));

        // graph[3].add(new Edge(3, 1, 1));
        // graph[3].add(new Edge(3, 4, 1));
        // graph[3].add(new Edge(3, 5, 1));

        // graph[4].add(new Edge(4, 2, 1));
        // graph[4].add(new Edge(4, 3, 1));
        // graph[4].add(new Edge(4, 5, 1));

        // graph[5].add(new Edge(5, 4, 1));
        // graph[5].add(new Edge(5, 3, 1));
        // graph[5].add(new Edge(5, 6, 1));

        // graph[5].add(new Edge(6, 5, 1));

    }
    public static void createGraph2(ArrayList<Edge> graph){
        graph.add(new Edge(0, 1, 2));
        graph.add(new Edge(0, 2, 4));
        

        //graph[1].add(new Edge(1, 3, 7));
        graph.add(new Edge(1, 2, -4));

        graph.add(new Edge(2, 3, 2));

        graph.add(new Edge(4, 1, -1));
        //graph[4].add(new Edge(4, 5, 5));

        graph.add(new Edge(3, 4, 4));
    }
    /*
     * Breadth First Search :
     * GO to the immediate neighbor first 
     * we keep track of the visited nodes using a boolean array vis[] and queue to track down on the neighbours 
     * Note: we are using here bfs and bfsUtil because a graph can be disjointed in some cases 
     * 
     */
    public static void bfs(ArrayList<Edge>[] graph){
        boolean vis[]=new boolean[graph.length];
        for (int i = 0; i < vis.length; i++) {
            if (!vis[i]) {
                bfsUtil(graph, vis);
            }
        }
        
    }
    public static void bfsUtil(ArrayList<Edge>[] graph,boolean[]vis){//here(Adjaceny lists) ->O(V+E)|| adjaceny matrix->O(v^2)
        Queue<Integer> q= new LinkedList<>();
        q.add(0);// source =0
        while (!q.isEmpty()) {
            int curr=q.remove();
            if(!vis[curr]){
                //visit curr
                System.out.print(curr+" ");
                vis[curr]=true;
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e= graph[curr].get(i);
                    q.add(e.des);
                }
            }
        }
    }
    /*
     * Depth first search :
     * Keep going to the first neighbour 
     * we use recursion here, first we visit the source then we call the same function for it neigbour, then the neighbours does the same(recursion)
     * Note: we are using here dfs and dfsUtil because a graph can be disjointed in some cases
     */
    public static void dfs(ArrayList<Edge>[] graph){
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if(!vis[i]) 
            dfsUtil(graph, i, vis);
        } 
    }
    public static void dfsUtil(ArrayList<Edge>[] graph,int curr, boolean[]vis){
        //visit the node 
        System.out.print(curr+" ");
        vis[curr]=true;
        for (int i = 0; i <graph[curr].size(); i++) {
            Edge e= graph[curr].get(i);
            if (!vis[e.des]) {
                dfsUtil(graph, e.des, vis);
            }
            
        }
    }

    public static boolean hasPath(ArrayList<Edge>[]graph, int src, int des, boolean[]vis){//O(V+E) modified dfs 
        if(src==des){
            return true;
        }
        vis[src]=true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e= graph[src].get(i);
            if(!vis[e.des]&&hasPath(graph, e.des, des, vis)){
            return true;
        }
        }
        
        return false;
    }
    // cycle detection for undirected graphs can be done through dfs, bfs and disjoint set union 
    public static boolean detectCycle(ArrayList<Edge>[] graph){
        boolean vis[]=new boolean[graph.length];
        for (int i = 0; i < vis.length; i++) {
            if (!vis[i]) {
                if(detectCycleUtil(graph,vis,i,-1)){// we passed the first node which 
                    return true;
                }
            }
        } 
        return false ;
    }
    private static boolean detectCycleUtil(ArrayList<graphs.Edge>[] graph, boolean[] vis, int curr, int par) {
      vis[curr]=true;
      for (int i = 0; i < graph[curr].size(); i++) {
        Edge e= graph[curr].get(i);
        // case 3 where vis[neighbor] is false
        if (!vis[e.des] ) {
            if (detectCycleUtil(graph, vis, e.des, curr)) {
                return true;
            }
            
        }
        // case 1 where neghbor visited bi hai par parent nhi hai 
        else if(vis[e.des]==true && e.des!=par){
            return true;
        }
        // case 2 -> do nothing -> jab visited bi hai neighbor aur uska parent par hi hai 
      }
      return false;
    }
    /*
     * bipartite graph(coloring)
     * the thing it says is that neigbour and curr cannot exist in a single set or color
     */
    public static boolean isBipartite(ArrayList<Edge>[]graph){
        int col[]=new int[graph.length];
        for (int i = 0; i < col.length; i++) {
            col[i]=-1;//no color
        }
        Queue<Integer> q= new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if(col[i]==-1){
                //BFS
                q.add(i);
                col[i]=0;//yellow
                while (!q.isEmpty()) {
                    int curr = q.remove();
                    for (int j = 0; j <graph[curr].size(); j++) {
                        Edge e = graph[curr].get(j);//e.dest
                        if (col[e.des]==-1) {
                            int nextCol= col[curr]==0?1:0;
                            col[e.des]= nextCol;
                            q.add(e.des);
                        }else if(col[e.des]==col[curr]){
                            return false;//Not Bipartite 
                        }
                    }
                }
            }
            
        }
        return true;
    }
    // another way of finding if a graph is bipartite in directed graphs or not is to detect the cycle - as acyclic and 
    //even cyclic graph  are always bipartite. only graphs with an odd cycle is non- bipartite
    public static boolean isCycle(ArrayList<Edge>[] graph ){
        boolean vis[]=new boolean[graph.length];
        boolean stack[]=new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                if (isCycleUtil(graph,i,vis,stack)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean isCycleUtil(ArrayList<graphs.Edge>[] graph, int curr, boolean[] vis, boolean[] stack) {
        vis[curr]=true;
        stack[curr]=true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (stack[e.des]) {// cycle exists
                return true;
            }
            if (!vis[e.des]&& isCycleUtil(graph, e.des, vis, stack)) {
                return true;
            }
        }
        stack[curr]= false;
        return false;
    }
    //TC -> O(V+E)
    /*
     *Topological graph is used for Directed Acyclic graphs 
     *Used in dependency graphs
     */
    public static void topSort(ArrayList<Edge>[]graph){
        boolean[] vis= new boolean[graph.length];
        Stack<Integer> s= new Stack<>();
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                topSortUtil(graph,i,vis,s);//modified dfs
            }
        }
        while (!s.isEmpty()) {
            System.out.print(s.pop()+" ");
        }
    }
    private static void topSortUtil(ArrayList<graphs.Edge>[] graph, int curr, boolean[] vis, Stack<Integer> s) {
        vis[curr]=true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e= graph[curr].get(i);
            if (!vis[e.des]) {
                topSortUtil(graph, e.des, vis, s);
            }
        }
        s.push(curr);// backtrack step
    }
   

    /** Topological sort using BFS
     *kahn's Algorithm _> in this we apply the concept of indegree and outdegree 
      this code is mainly based on dependency graphs or directional graphs 
      for eg lets take a node 2 and asuumed that it has two neigbors and it directs towards them then indegree =0 and outdegree = 2
      lets take another example now there is node which can be approached by 2 of his neighbors and it can only approach one other then
      indegree = 1 and outdegree=2
      now kahn's algorithm is used to topologicaly sort the DAG- Directed Acyclic Graphs.
      Fact-> A DAG has atleast one vertex with indegree=0 and one vertex with outdegree =0;

      in the following appraoch we only keep in track the indegree of all the vertex in the array indeg 
      we keep track of the vertex with indegree =0 in  a queue as they should be done first so other ones could be approached 
      and then we apply the modified bfs on the queue by reaping its neighbors.indeg-- and checking if the neigbors became indeg==0 too
      Notice how there is no vis array this time that is because that will be handled by the indegreee array and the queue 
      */ 
    public static void calcIndeg(ArrayList<Edge>[]graph,int[] indeg){
        for (int i = 0; i < graph.length; i++) {
            int v=i;
            for (int j = 0; j < graph[v].size(); j++) {
                Edge e= graph[v].get(j);
                indeg[e.des]++;
            }
        }
    }
    public static void topsort(ArrayList<Edge>[] graph){
        int indeg[]= new int[graph.length];
        calcIndeg(graph, indeg);
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i]==0) {
                q.add(i);
            }
        }

        //bfs 
        while (!q.isEmpty()) {
            int curr= q.remove();
            System.out.print(curr+" ");

            for (int i = 0; i < graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                indeg[e.des]--;
                if (indeg[e.des]==0) {
                    q.add(e.des);
                }
            }
        }
        System.out.println();
    }
    /*
     * All path from source to target TC-> O(V^V)
     */
    public static void PrintALLPath(ArrayList<Edge>[] graph, int src, int des, String path){
        if (src==des) {
            System.out.println(path+des);
            return;
        }
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            PrintALLPath(graph, e.des, des, path+src);
        }
    }
    /*
     * Dijkstra's Algorithm 
     */
    static class Pair implements Comparable<Pair>{
        int n;
        int path;
        public Pair(int n , int path){
            this.n= n;
            this.path=path;
        }

        @Override
        public int compareTo(Pair p2){
            return this.path-p2.path;//path based on sorting for pairs 
        }
    }
    public static void Dijkstra(ArrayList<Edge> graph[],int src){
        int[] dist = new int[graph.length];//dist[i]-> src  se i tk ka dist
        for (int i = 0; i < graph.length; i++) {
            if (i!=src) {
                dist[i]=Integer.MAX_VALUE;
            }
        }
        boolean vis[]= new boolean[graph.length];
        PriorityQueue<Pair> pq= new PriorityQueue<>();
        pq.add(new Pair(src, 0));
        //loop 
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!vis[curr.n]) {
                vis[curr.n]=true;
                //neigbours
                for (int i = 0; i <graph[curr.n].size(); i++) {
                    Edge e = graph[curr.n].get(i);
                    int u=e.src;
                    int v=e.des;
                    int wt=e.wt;

                    if (dist[u]+wt<dist[v]) {
                        dist[v]=dist[u]+wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }
        //print all source to vertices path - shortest path
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }

    public static void bellmanFord(ArrayList<Edge> graph,int src,int V){//TC-> O(V*E)
        int dist[]= new int[V];
        for (int i = 0; i < dist.length; i++) {
            if (i!=src) {
                dist[i]=Integer.MAX_VALUE;
            }
        }
        //int V = graph.length;
        //algo -> O(V)
        for (int i = 0; i < V-1; i++) {
            //edges - O(E)
            for (int j = 0; j < graph.size(); j++) {
                
                    Edge e = graph.get(j);
                    // u, v, wt
                    int u = e.src;
                    int v= e.des;
                    int wt= e.wt;
                    //relaxation
                    if (dist[u]!= Integer.MAX_VALUE&&dist[u]+wt<dist[v]) {
                        dist[v]=dist[u]+wt;
                    }
                
            }
        }
        //print 
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }
    static class Pair1 implements Comparable<Pair1>{
        int v;
        int cost;

        public Pair1(int v, int cost){
            this.v=v;
            this.cost=cost;
        }
        @Override
        public int compareTo(Pair1 p2){
            return this.cost-p2.cost;
        }
    }
    public static void prims(ArrayList<Edge>graph[]){ //TC-> O(VlogV)
        boolean vis[]= new boolean[graph.length];
        PriorityQueue<Pair1> pq = new PriorityQueue<>();
        pq.add(new Pair1(0, 0));
        int finalCost=0;//MST Cosy/total min weight

        while (!pq.isEmpty()) {
            Pair1 curr= pq.remove();
            if (!vis[curr.v]) {
                vis[curr.v]=true;
                finalCost+=curr.cost;

                for (int i = 0; i < graph[curr.v].size(); i++) {
                    Edge e =graph[curr.v].get(i);
                    pq.add(new Pair1(e.des, e.wt));
                }
            }
        }
        System.out.println("final(min) cost of mst = "+ finalCost);
    }
    
        @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int V=4;
        ArrayList<Edge> graph[]=new ArrayList[V];
        //ArrayList<Edge> graph = new ArrayList<>();
        createGraph(graph);
        bfs(graph);
        //System.out.println();
        //dfs(graph);
        // dfs(graph);
        // System.out.println();
        // System.out.println(hasPath(graph, 0, 9, new boolean[V]));
        //System.out.println(detectCycle(graph));
        //System.out.println(isBipartite(graph));
        //System.out.println(isCycle(graph));
        //topsort(graph);
        // int src= 0;
        // int des=1;
        //PrintALLPath(graph, src, des,"");
        //Dijkstra(graph, src);
        //bellmanFord(graph, src,V);
        //prims(graph);
        // int n=4;
        // int flights[][] = {{0,1,100}, {1,2,100}, {2,0, 100}, {1,3,600}, {2,3,200}};
        // int src = 0, dst = 3, k = 1;
        // int op =cheapestFlight(n, flights, src, dst, k);
        // System.out.println(op);
    }
}
