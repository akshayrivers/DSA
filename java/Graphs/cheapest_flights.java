import java.util.*;
import java.util.LinkedList;
public class cheapest_flights {
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
    public static void createGraph3(int flights[][],ArrayList<Edge> graph[]){
        for (int i = 0; i < graph.length; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int i = 0; i <flights.length; i++) {
            int src= flights[i][0];
            int des= flights[i][1];
            int wt= flights[i][2];

            Edge e= new Edge(src, des, wt);
            graph[src].add(e);
        }

    }
    static class Info{
        int v;
        int cost;
        int stops;

        public Info(int v, int c, int s){
            this.v= v;
            this.cost=c;
            this.stops=s;
        }
    }
    public static int cheapestFlight(int n, int flights[][],int src, int des, int k){
        ArrayList<Edge> graph[]= new ArrayList[n];
        createGraph3(flights, graph);

        int dist[]= new int[n];
        for (int i = 0; i < n; i++) {
            if (i!=src) {
                dist[i]=Integer.MAX_VALUE;
            }
        }
        Queue<Info> q= new LinkedList<>();
        q.add(new Info(src, 0, 0));

        while (!q.isEmpty()) {
            Info curr= q.remove();
            if(curr.stops>k){
                break;
            }

            for (int j = 0; j < graph[curr.v].size(); j++) {
                Edge e = graph[curr.v].get(j);
                int u= e.src;
                int v= e.des;
                int wt= e.wt;

                if ( curr.cost+wt<dist[v]&&curr.stops<=k) {
                    dist[v]= dist[u]+wt;
                    q.add(new Info(v, dist[v], curr.stops+1));
                }
            }
        }
        //dist[dest]
        if (dist[des]==Integer.MAX_VALUE) {
            return -1;
        }
        else{
            return  dist[des];
        }
    }

    public static void main(String[] args) {
        int n=4;
        int flights[][] = {{0,1,100}, {1,2,100}, {2,0, 100}, {1,3,600}, {2,3,200}};
        int src = 0, dst = 3, k = 1;
        int op =cheapestFlight(n, flights, src, dst, k);
        System.out.println(op);
    }
}

