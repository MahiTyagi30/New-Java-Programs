public class prefifProblemTrie {

    static class Node{
        Node children[]=new Node[26];
        boolean eow=false;
        int freq;
        Node(){
            for(int i=0;i<26;i++){
                children[i]=null;
            }
            freq=1;
        }
    }

    public static Node root=new Node();
    public static void insert(String word){
        Node curr=root;
        for(int level=0;level<word.length();level++){
            int idx=word.charAt(level)-'a';
            if(curr.children[idx]==null){
                curr.children[idx]=new Node();
            }
            else{
              curr.children[idx].freq++;
            }
        }
    
    curr.eow=true;
    }
    public static void findPrefix(Node root,String ans){//o(L)
        if(root==null){
            return;
        }
        if(root.freq==1){
            System.out.println(ans);
            return;
        }
        for(int i=0;i<root.children.length;i++){
            if(root.children[i]!=null){
                findPrefix(root.children[i], ans+(char)(i+'a'));
            }
        }
    }

    public static void main(String[] args){
        String ar[]={"z","dog","dove","duck"};
        for(int i=0;i<ar.length;i++){
        insert(ar[i]);
        }
        root.freq=-1;
        findPrefix(root, "");
    }
    
}
