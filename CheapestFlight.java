import java.util.*;
import java.util.LinkedList;
public class CheapestFlight {
        static class Edge{
            int src;
            int dest;
            int wt;
            public Edge(int s,int d,int w){
                this.src=s;
                this.dest=d;
                this.wt=w;
            }
        }
        public static void create(ArrayList<Edge>[] graph,int[][] flight){
            for(int i=0;i<graph.length;i++){
                graph[i]=new ArrayList<>();
            }
            for(int i=0;i<flight.length;i++){
                int src=flight[i][0];
                int des=flight[i][1];
                int wt=flight[i][2];
                Edge e=new Edge(src, des, wt);
                graph[src].add(e);
            }
        }
        static class Info{
            int v;
            int cost;
            int stops;
            public Info(int v,int c,int s){
                this.v=v;
                this.cost=c;
                this.stops=s;
            }
        }
        public static int cheapestFlight(int src,int des,int k,int[][] flight,int n){
            ArrayList<Edge> graph[]=new ArrayList[n];
            create(graph, flight);
            int[] dist=new int[n];
            for(int i=0;i<n;i++){
                if(i!=src){
                    dist[i]=Integer.MAX_VALUE;
                }
            }
            Queue<Info> q= new  LinkedList();
            q.add(new Info(0,0,0));
            while(!q.isEmpty()){
                Info curr=q.remove();
                if(curr.stops>k){
                    break;
                }
                for(int i=0;i<graph[curr.v].size();i++){
                    Edge e=graph[curr.v].get(i);
                    int u=e.src;
                    int v=e.dest;
                    int w=e.wt;
                    if(curr.cost+w<dist[v]&&curr.stops<=k){
                        dist[v]=curr.cost+w;
                        q.add(new Info(v,dist[v],curr.stops+1));
                    }
                }
            }
            if(dist[des]==Integer.MAX_VALUE){
                return -1;
            }
            else{
                return dist[des];
            }

        }

        public static void main(String[] args){
            int n=4;
            int flight[][]={{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,600}};
            int src=0;int des=3;int k=1;
           System.out.println(cheapestFlight(src, des, k, flight, n));
           
        }
    
}
