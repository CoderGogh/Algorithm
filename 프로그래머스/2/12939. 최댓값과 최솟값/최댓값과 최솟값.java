class Solution {
        public String solution(String s) {
            StringBuilder sb = new StringBuilder();
            String[] list = s.split(" ");
            int max = Integer.parseInt(list[0]);
            int min = Integer.parseInt(list[0]);
            for (int i = 0; i < list.length; i++) {
                int a = Integer.parseInt(list[i]);
                if(max<=a){
                    max = a;
                }else if(min>a){
                    min= a;
                }
            }
            sb.append(Integer.toString(min)).append(" ").append(Integer.toString(max));
            return sb.toString();
        }
    }