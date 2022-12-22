import java.util.Arrays;

public class fibonacciDp {
    public static int climbingways(int n,int[] dp){
        
        if(n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        dp[n]=climbingways(n-1, dp)+climbingways(n-2, dp);
        return dp[n];
    }
    public static int climbingwaysTab(int n){
        int dp[]=new int[n+1];
        dp[0]=1;
        //tabulation loop
        for(int i=1;i<=n;i++){
            if(i==1){
                dp[i]=dp[i-1]+0;
            }
            else{
           dp[i]= dp[i-1]+dp[i-2];
            }
        }
        return dp[n];
    }
    public static void fibTab(int n){
        int dp[]=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        System.out.println("the final answer s "+dp[n]);
    }

    public static int knacpsack(int W,int val[],int wt[],int i,int[][] dp){
        int ans=0;
        if(W==0||i==0){
            return 0;
        }
        if(dp[i][W]!=-1){
            return dp[i][W];
        }
        if(wt[i-1]<=W){
            //include
      int ans1= val[i-1]+knacpsack(W-wt[i-1], val, wt, i-1,dp);
      //exclude
      int ans2=knacpsack(W, val, wt, i-1,dp);
      dp[i][W]= Math.max(ans1,ans2);
      return dp[i][W];
        
        }
        else{
           dp[i][W]=  knacpsack(W, val, wt, i-1,dp);
           return dp[i][W];
        }
    }public static boolean targetSumSet(int ar[],int sum){
        int n=ar.length;
        boolean dp[][]=new boolean[n+1][sum+1];
        for(int i=0;i<n+1;i++){
            dp[i][0]=true;
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<sum+1;j++){
                int v=ar[i-1];
                if(v<=sum){
                    if(dp[i-1][j-v]==true){
                        dp[i][j]=true;
                    }
                    //exclude
                    if(dp[i-1][j]==true){
                        dp[i][j]=true;
                    }
                }
                else{
                    if(dp[i-1][j]==true){
                        dp[i][j]=true;
                    }

                }
            }
        }
  return dp[n][sum];


        }
        public static int unboundedKnapsack(int val[],int wt[],int W){
            int n=val.length;
            int dp[][]=new int[n+1][W+1];
            for(int i=0;i<n+1;i++){
                dp[i][0]=0;
            }
            for(int j=0;j<W+1;j++){
                dp[0][j]=0;
            }
            for(int i=1;i<n+1;i++){
                for(int j=1;j<W+1;j++){
                    if(wt[i-1]<=j){
                        dp[i][j]=Math.max(val[i-1]+dp[i][j-wt[i-1]],dp[i-1][j]);
                    }
                    else{
                        dp[i][j]= dp[i-1][j];
                    }
                }
            }
            return dp[n][W];
        }

        public static int rodCutting(int[] prices,int[] length,int totRod){
            int n=prices.length;
            int[][] dp=new int[n+1][totRod+1];
            for(int i=1;i<n+1;i++){
                for(int j=1;j<totRod+1;j++){
                    if(length[i-1]<=j){
                        dp[i][j]=Math.max((prices[i-1]+dp[i-1][j-length[i-1]]), dp[i-1][j]);
                    }
                    else{
                        dp[i][j]=dp[i-1][j];
                    }
                }
            }
            return dp[n][totRod];
        }
    public static int LCS(String str1,String str2,int n,int m){
        if(n==0||m==0){
            return 0;
        }
        if(str1.charAt(n-1)==str2.charAt(m-1)){
            return LCS(str1, str2, n-1, m-1)+1;
        }
        else{
            int ans1=LCS(str1, str2, n-1, m);
            int ans2=LCS(str1, str2, n, m-1);
            return Math.max(ans1, ans2);
        }
    }
    public static int longestsubseqmemoization(String str1,String str2,int n,int m, int[][] dp){
        if(n==0||m==0){
            return 0;
        }

        if(dp[n][m]!=-1){
            return dp[n][m];
        }
        if(str1.charAt(n-1)==str2.charAt(m-1)){
            return dp[n][m]=longestsubseqmemoization(str1, str2, n-1, m-1,dp)+1;
        }
        else{
            int ans1=LCS(str1, str2, n-1, m);
            int ans2=LCS(str1, str2, n, m-1);
            return  dp[n][m]=Math.max(ans1, ans2);

        }

    }
    public static int longestsubseqTabulation(String str1,String str2,int n,int m){
        int[][] dp=new int[n+1][m+1];
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                   dp[i][j]=dp[i-1][j-1]+1;
                }
                else{
                    int ans1=dp[i-1][j];
                    int ans2=dp[i][j-1];
                    dp[i][j]=Math.max(ans1, ans2);
                }
            }
        }
        return dp[n][m];
    }
    public static void main(String[] args){
        // int n=5;
        // int dp[]=new int[n+1];
        // Arrays.fill(dp,-1);
        // System.out.println(climbingwaysTab(n));
      //System.out.println(climbingways(n,dp));
       // fibTab(n);
       int val[]={15,14,10,45,30};
       int wt[]={2,5,1,3,4};
       int W=7;
    //    int dp[][]=new int[val.length][W+1];
    //    System.out.println(knacpsack(W, val, wt, val.length,dp));
    //    int ar[]={4,2,7,1,3};
    //    System.out.println(targetSumSet(ar, 10));
   // System.out.println(unboundedKnapsack(val, wt, W));
    int length[]={1,2,3,4,5,6,7,8};
    int prices[]={1,5,8,9,10,17,17,20};
    //System.out.println(rodCutting(prices, length, 8));
    String str1="abcd";
    String str2="abce";
    int n=str1.length();
    int m=str2.length();
    int dp[][]=new int[n+1][m+1];
    //Arrays.fill(dp, -1);
    for(int i=0;i<n+1;i++){
        for(int j=0;j<m+1;j++){
            dp[i][j]=-1;
        }
    }
   // System.out.println(longestsubseqmemoization(str1, str2, n, m, dp));
   System.out.println(longestsubseqTabulation(str1, str2, n, m));
    }
}
