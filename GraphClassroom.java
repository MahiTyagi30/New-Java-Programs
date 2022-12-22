import java.util.*;
import java.util.LinkedList;

public class GraphClassroom {

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
    public static boolean cycleDetection(ArrayList<Edge>[] graph){
        boolean[] visit=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!visit[i]){
                if(cycleDetectionutil(graph, i,-1, visit)){
                    return true;//cycle exist in one of the component
                }
            }

        }
        return false;
    }
    public static boolean cycleDetectionutil(ArrayList<Edge>[] graph,int curr,int parent,boolean[] visit){
        //visit curr
        //System.out.print(curr+" ");
        visit[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            //case:3
            if(!visit[e.dest]){
                if(cycleDetectionutil(graph, e.dest, curr, visit)){
                return true;
                }
            }

            //case 1:
            else if(visit[e.dest]&&e.dest!=parent){
                return true;
            }

            //cse 2:do nothing
        }
        return false;
    }

    public static boolean isCycle(ArrayList<Edge>[] graph1){
    boolean[] visit=new boolean[graph1.length];
    boolean[] stack=new boolean[graph1.length];
    for(int i=0;i<graph1.length;i++){
        if(isCycleUtil(graph1,i,visit,stack)){
            return true;
        }
    }
    return false;
    }
    public static void createGraph1(ArrayList<Edge>[] graph1){
        for(int i=0;i<graph1.length;i++){
            graph1[i]=new ArrayList<>();
        }
        graph1[0].add(new Edge(0, 2, 1));
        graph1[1].add(new Edge(1, 0, 1));
        graph1[2].add(new Edge(2, 3, 1));
        graph1[3].add(new Edge(3, 0, 1));
    }

  public static boolean isCycleUtil(ArrayList<Edge>[] graph1,int curr,boolean[] visit,boolean stack[]){
    visit[curr]=true;
    stack[curr]=true;
    for(int i=0;i<graph1[curr].size();i++){
        Edge e=graph1[curr].get(i);
        if(stack[e.dest]){//cycle exist
            return true;
        }
        if(!visit[e.dest]){
            if(isCycleUtil(graph1, e.dest, visit, stack)){
                return true;
            }
        }
    }
    stack[curr]=false;
    return false;

  }
 
 
public static void topSort(ArrayList<Edge>[] graph){
    boolean[] visit=new boolean[graph.length];
    Stack<Integer> s=new Stack<>();
    for(int i=0;i<graph.length;i++){
        if(!visit[i]){
            topSortUtil(graph,i,visit,s);
            
        }
    }
    while(!s.isEmpty()){
        System.out.print(s.pop()+" ");
    }
  }
  public static  void topSortUtil(ArrayList<Edge>[] graph,int curr,boolean[] visit,Stack<Integer> s){
    visit[curr]=true;
    for(int i=0;i<graph[curr].size();i++){
        Edge e=graph[curr].get(i);
        if(!visit[e.dest]){
            topSortUtil(graph,e.dest,visit,s);
        }
    }
    s.push(curr);
  }
  public static boolean isBipartite(ArrayList<Edge>[] graph){
    int[] col=new int[graph.length];
    for(int i=0;i<graph.length;i++){
        col[i]=-1;
    }
    Queue<Integer> q=new LinkedList<>();
    for(int i=0;i<graph.length;i++){
        if(col[i]==-1){
            q.add(i);
            col[i]=0;
            while(!q.isEmpty()){
              int curr=  q.remove();
            for(int j=0;j<graph[curr].size();j++){
                Edge e=graph[curr].get(j);
                if(col[e.dest]==-1){
                    int nextcol=col[curr]==0?1:0;
                    col[e.dest]=nextcol;
                    q.add(e.dest);
                }
                else if(col[curr]==col[e.dest]){
                    return false;
                }
               
            }
        }
    }
    }
    return true; 
  }
  public static void calculateIndegree(ArrayList<Edge>[] graph,int [] indeg){
    for(int i=0;i<graph.length;i++){
        int v=i;
        for(int j=0;j<graph[v].size();j++){
            Edge e=graph[v].get(j);
            indeg[e.dest]++;
        }
    }
  }
  public static void kahnAlgo(ArrayList<Edge>[] graph){
    int[] indeg=new int[graph.length];
    calculateIndegree(graph, indeg);
    Queue<Integer> q=new LinkedList<>();
    for(int i=0;i<indeg.length;i++){
        if(indeg[i]==0){
            q.add(i);
        }
    }
    //bfs
    while(!q.isEmpty()){
        int curr=q.remove();
        System.out.print(curr+" ");
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            indeg[e.dest]--;
            if(indeg[e.dest]==0){
                q.add(e.dest);
            }
        }
    }
    System.out.println();
  }

    public static void main(String[] args){
        int v=5;
        ArrayList<Edge>[] graph=new ArrayList[v];
        ArrayList<Edge>[] graph1=new ArrayList[4];
       

        createGraph1(graph);
       // System.out.println(cycleDetection(graph));
       //System.out.println(isCycle(graph1));
       topSort(graph);
       System.out.println();
       kahnAlgo(graph);
    }
    
}
