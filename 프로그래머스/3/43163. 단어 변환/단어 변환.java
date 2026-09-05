import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean exist = false;
        for(String s : words){
            if(s.equals(target)){
                exist = true;
                break;
            }
        }
        if(!exist){return 0;}
        
        Deque<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        
        queue.offer(new Node(begin,0));
        
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            
            if(curr.word.equals(target)){
                return curr.count;
            }
            
            for(int i =0; i<words.length;i++){
                if(!visited[i] && diff(curr.word,words[i])){
                    visited[i] = true;
                    queue.offer(new Node(words[i],curr.count +1));
                }
            }
        }
        return 0;
    }
    
    
    boolean diff(String s1, String s2){
        int count = 0;
        for(int i = 0; i<s1.length();i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
            }
        }
        return count == 1;
    }
    class Node{
        String word;
        int count;
        
        public Node(String w, int c){
            this.word = w;
            this.count = c;
        }
    }
}