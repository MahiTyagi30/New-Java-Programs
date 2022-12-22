import java.io.ObjectInputStream.GetField;
import java.util.*;
import java.util.LinkedList;
public class graphClass {
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
    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 5));

        graph[1].add(new Edge(1, 0, 5));
        graph[1].add(new Edge(1, 2, 1));
        graph[1].add(new Edge(1, 3, 3));

        graph[2].add(new Edge(2, 1, 1));
        graph[2].add(new Edge(2, 3, 1));
        graph[2].add(new Edge(2, 4, 4));

        graph[3].add(new Edge(3, 1, 3));
        graph[3].add(new Edge(3, 2, 1));

        graph[4].add(new Edge(4, 2, 2));

    }

    public static void BFS(ArrayList<Edge>[] graph){
        boolean visit[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!visit[i]){
                BFSUtil(graph, visit);
            }
        }
        
    }
    public static void BFSUtil(ArrayList<Edge>[] graph,boolean visit[]){
        Queue<Integer> q=new LinkedList<>();
        q.add(0);//starting my add with source 0

        while(!q.isEmpty()){
            int curr=q.remove();
            if(!visit[curr]){
                System.out.print(curr+" ");//print the vertex
                visit[curr]=true;//mark true in visited array
                for(int i=0;i<graph[curr].size();i++){//add the neighbours of curr in queue
                    Edge e=graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph,int curr,boolean[] visit){
        //visit curr
        System.out.print(curr+" ");
        visit[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(!visit[e.dest]){
            dfs(graph, e.dest, visit);
            }
        }
    }
    public static boolean hasPath(ArrayList<Edge>[] graph,int src,int d,boolean[] visit){
        if(src==d){
            return true;
        }
        visit[src]=true;
        for(int i=0;i<graph[src].size();i++){
            Edge e=graph[src].get(i);
            if(!visit[e.dest]&&hasPath(graph, e.dest, d, visit)){
                return true;

            }
        }
        return false;
    }

    public static void main(String[] args){
        int v=5;
        ArrayList<Edge>[] graph=new ArrayList[v];
        createGraph(graph);
        BFS(graph);
        System.out.println();
        dfs(graph, 0, new boolean[v]);
        System.out.println(hasPath(graph, 2, 4, new boolean[v]));
        

    }
    
}
