import java.util.*;
class Solution {
        public int solution(String s) {
            int answer = 0;
            String rotated = s;
            for (int i = 0; i < s.length(); i++) {
                if(isValid(rotated)){
                    answer++;
                }
                rotated = rotated.substring(1)+ rotated.charAt(0);
            }
            return answer;
        }
        private boolean isValid(String s){
            Stack<Character> stack = new Stack<>();

            for (char c :
                    s.toCharArray()) {
                if(c == '(' || c == '{' || c == '['){
                    stack.push(c);
                }else{
                    if(stack.isEmpty()){
                        return false;
                    }
                    char top = stack.pop();
                    if(c == ')' && top != '('){
                        return false;
                    }
                    if(c == '}' && top != '{') {
                        return false;
                    }
                    if(c == ']' && top != '['){
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
    }