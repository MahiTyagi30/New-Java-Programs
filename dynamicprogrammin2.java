import java.util.*;

import StackB.stack;
public class dynamicprogrammin2 {
    public static int longestCommonSubstring(String str1,String str2,int n,int m){
        int[][] dp=new int[n+1][m+1];

        int ans=0;
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                    ans=Math.max(ans, dp[i][j]);
                }
                else{
                    dp[i][j]=0;
                }

            }
        }
        return ans;
    }
    public static int  LIS(int[] ar1){
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<ar1.length;i++){
            set.add(ar1[i]);
        }
        int[] ar2=new int[set.size()];
        int i=0;
        for(int num:set){
            ar2[i]=num;
            i++;
        }
        Arrays.sort(ar2);//asending
        return LCS(ar1,ar2);

    }
    public static int LCS(int[] ar1,int[] ar2){

        int n=ar1.length;
        int m=ar2.length;
        int[][] dp=new int[n+1][m+1];
        for(int i=0;i<n+1;i++){
            dp[i][0]=0;
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(ar1[i-1]==ar2[j-1]){
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

    public static int editDistance(String str1,String str2){
        int n=str1.length();
        int m=str2.length();
        int[][] dp=new int[n+1][m+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<m+1;j++){
                if(i==0){
                    dp[i][j]=j;
                }
                if(j==0){
                    dp[i][j]=i;
                }
            }
        }

        for(int i=1;i<n+1;i++){
            for(int j =1;j<m+1;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                    int a=dp[i][j-1]+1;
                    int b=dp[i-1][j]+1;
                    int c=dp[i-1][j-1]+1;
                    int d=Math.min(a, b);
                dp[i][j]=Math.min(d, c);
                }
            }
        }
        return dp[n][m];
    }
     public static boolean isMatch(String str,String p){
        int n=str.length();
        int m=p.length();
        boolean[][] dp=new boolean[n+1][m+1];
        //initialisation
        dp[0][0]=true;
        for(int i=1;i<n+1;i++){
            dp[i][0]=false;
        }
        for(int j=1;j<m+1;j++){
            if(p.charAt(j-1)=='*'){
                dp[0][j-1]=dp[0][j];
            }
        }
     

     for(int i=1;i<n+1;i++){
        for(int j=1;j<m+1;j++){
            if(str.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='?'){
                dp[i][j]=dp[i-1][j-1];
            }
            if(p.charAt(j-1)=='*'){
                dp[i][j]=dp[i][j-1]||dp[i-1][j];
            }
            else{
                dp[i][j]=false;
            }
        }
     }
     return dp[n][m];
    }
    public static int Catalans(int n){
        int[] dp=new int[n+1];
        dp[0]=dp[1]=1;
        for(int i=2;i<n+1;i++){
            for(int j=0;j<i;j++){
                dp[i] += dp[j]*dp[i-j-1];
            }
        }
       return dp[n];
    }
    public static int countingBST(int n){
        int[] dp=new int[n+1];
        dp[0]=dp[1]=1;
        for(int i=2;i<n+1;i++){
            for(int j=0;j<i;j++){
                int left=dp[j];
                int right=dp[i-j-1];
                dp[i] += left*right;
            }
        }
       return dp[n];

    }

    public static int mountainRanges(int n){
        int[] dp=new int[n+1];
        dp[0]=dp[1]=1;
        for(int i=2;i<n+1;i++){
            for(int j=0;j<i;j++){
                int inside =dp[j];
                int outside=dp[i-j-1];
                dp[i] +=inside*outside;
            }
        }
        return dp[n];
    }
    public static int minimumArrayJumps(int ar[]){
        int n=ar.length;

        int[] dp= new int[n];
        Arrays.fill(dp, -1);
        dp[n-1]=0;
        for(int i=n-2;i>=0;i--){
            int ans=Integer.MAX_VALUE;
            int steps=ar[i];
            for(int j=i+1;j<=i+steps&&j<n;j++){
                if(dp[j]!=-1){
                    ans=Math.min(ans,dp[j]+1);
                }
            }
            if(ans!=Integer.MAX_VALUE){
                dp[i]=ans;
            }
        }
        return dp[0];

    }
    public static int minPartition(int num[]){
        int sum=0;
        int n=num.length;
        for(int i=0;i<n;i++){
            sum=sum+num[i];
        }
        int W=sum/2;
        int[][] dp=new int[n+1][W+1];
        for(int i=1;i<n+1;i++){
            for(int j=1;j<W+1;j++){
                if(num[i-1]<=j){
                    dp[i][j]=Math.max(num[i-1]+dp[i-1][j-num[i-1]],dp[i-1][j]);
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }

            }
        }
        int sum1=dp[n][W];
        int sum2=sum-sum1;
        int diff=Math.abs(sum1-sum2);
        return diff;
    }
    public static void main(String[] args){
        //System.out.println(longestCommonSubstring("ABCDE", "ABGCE", 5, 5));
        //System.out.println(editDistance("intention", "execution"));
       // System.out.println(isMatch("baaabab", "*****ba*****ab"));
      // System.out.println(Catalans(4));
      //System.out.println(mountainRanges(4));
      int ar[]={2,3,1,1,4};
     // System.out.println(minimumArrayJumps(ar));
     int num[]={1,6,11,5};
     System.out.println(minPartition(num));
    }
    
}
