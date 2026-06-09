import java.util.*;
class Solution {
        Deque<Character> stack = new ArrayDeque<>();
        boolean solution(String s) {
            boolean answer = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c == '('){
                    stack.push(c);
                }else if(c == ')'){
                    if(stack.isEmpty()){
                        return false;
                    }
                    if(stack.peek() == '('){
                        stack.pop();
                    }
                }
            }
            return stack.isEmpty() ? answer : false;
        }
    }