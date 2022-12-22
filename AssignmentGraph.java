import java.util.*;
public class AssignmentGraph{
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
        } graph[0].add(new Edge(0, 2, 1));
        graph[1].add(new Edge(1, 0, 1));
        graph[2].add(new Edge(2, 3, 1));
        graph[3].add(new Edge(3, 0, 1));
        
        // graph[3].add(new Edge(3, 1, 3));
        // graph[3].add(new Edge(3, 2, 1));

        // graph[4].add(new Edge(4, 2, 2));

    }
    static class pair{
        int value;
        int par;
        public pair(int c,int par){
            this.value=c;
            this.par=par;
        }
    }

    public static void BFS(ArrayList<Edge>[] graph){
        boolean visit[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!visit[i]){
                BFSUtil(graph, visit,i);
            }
        }
        
    }
    public static boolean BFSUtil(ArrayList<Edge>[] graph,boolean visit[],int src){
        Queue<pair> q = new LinkedList();
        q.add(new pair(src,-1));//starting my add with source 0

        while(!q.isEmpty()){
            pair curr=q.remove();
            if(!visit[curr.value]){
                //System.out.print(curr+" ");//print the vertex
                visit[curr.value]=true;//mark true in visited array
                for(int i=0;i<graph[curr.value].size();i++){//add the neighbours of curr in queue
                    Edge e=graph[curr.value].get(i);
                    if(!visit[e.dest]){
                        q.add(new pair(e.dest,curr.value));
                    }
                    else if(e.dest!=curr.par){
                        return true;
                    }
                   
                }
            }
        }
        return false;
    }
    public static void rottenEgg(int[][] ar,int i,int j){
        boolean[][] visit=new boolean[ar.length][ar[0].length];
        int flag=0;
        if(i<0||j<0||i>ar.length-1||j>ar[0].length-1){
            return;
        }
        if(visit[i][j]==false&&ar[i][j]==1){
            ar[i][j]=2;
        }
        visit[i][j]=true;
        rottenEgg(ar, i-1, j);
        rottenEgg(ar,  i+1, j);
        rottenEgg(ar,  i, j-1);
        rottenEgg(ar,  i, j+1);

        for(int k=0;k<ar.length;k++){
            for(int m=0;m<ar[0].length;m++){
                if(ar[k][m]==1){
                    flag=1;
                    break;
                }
            }

        }
        if(flag==1){
            System.out.println("oranges are not rotten");
        }
        else{
            System.out.println("oranges are rotten");
        }
    }
    public static void main(String[] args){
        int ar[][]={{2,1,0,2,1},
                    {0,0,1,2,1},
                         {1,0,0,2,1}};

      
     rottenEgg(ar,  0, 0);
        // int v=4 ;
        // ArrayList<Edge>[] graph=new ArrayList[v];
        // createGraph(graph);
        // BFS(graph);
    }

}