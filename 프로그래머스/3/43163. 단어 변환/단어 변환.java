import java.util.*;
class Solution {
        public int solution(String begin, String target, String[] words) {
            boolean hasTarget = false;
            for (String s : words ) {
                if(s.equals(target)){
                    hasTarget = true;
                    break;
                }
            }
            if(!hasTarget){
                return 0;
            }
            int answer = 0;
            Deque<WordInfo> queue = new ArrayDeque<>();
            queue.add(new WordInfo(begin,0));
            
            boolean[] visited = new boolean[words.length];
            
            while(!queue.isEmpty()){
                WordInfo curr = queue.poll();
                
                if(curr.word.equals(target)){
                    return curr.count;
                }

                for (int i = 0; i < words.length; i++) {
                    if(!visited[i] && check(curr.word, words[i])){
                        visited[i] = true;
                        queue.add(new WordInfo(words[i], curr.count+1));
                    }
                }
            }
            return 0;
        }

        boolean check(String word1, String word2){
            int count = 0;
            for (int i = 0; i < word1.length(); i++) {
                if(word1.charAt(i) != word2.charAt(i)){
                    count++;
                }
            }
            return count ==1;
        }
        class WordInfo{
            String word;
            int count;

            public WordInfo(String word, int count) {
                this.word = word;
                this.count = count;
            }
        }
    }