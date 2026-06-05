import java.util.*;
class Solution {
        public String solution(String s) {
            StringBuilder sb = new StringBuilder();
            boolean flag = true;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c == ' '){
                    sb.append(c);
                    flag = true;
                }else{
                    if(flag){
                        sb.append(Character.toUpperCase(c));
                        flag = false;
                    }else{
                        sb.append(Character.toLowerCase(c));
                    }
                }
            }
            return sb.toString();
        }
    }