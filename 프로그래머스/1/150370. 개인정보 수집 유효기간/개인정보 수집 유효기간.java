import java.util.*;
class Solution {
        public int[] solution(String today, String[] terms, String[] privacies) {
            int[] answer = {};
            int now = totalDate(Date(today));
            HashMap<String,Integer> map = new HashMap<>();
            for (String s: terms
                 ) {
                String[] splitItem = s.split(" ");
                map.put(splitItem[0], Integer.parseInt(splitItem[1]));
            }
            List<Integer> resultList = new ArrayList<>();
            for (int i = 0; i < privacies.length; i++) {
                String s = privacies[i];
                int customer_date = totalDate(Date(s));

                String policy = s.substring(s.length()-1);
                if(map.containsKey(policy)){
                    int expiration = customer_date + map.get(policy) * 28;
                    if(expiration<=now){
                        resultList.add(i+1);
                    }
                }
            }
            return resultList.stream().mapToInt(Integer::intValue).toArray();
        }private String[] Date(String d){
            String[] date = new String[3];
                date[0] = d.substring(0,4);
                date[1] = d.substring(5,7);
                date[2] = d.substring(8,10);
            return date;
        }private int totalDate(String[]d){
            return Integer.parseInt(d[0]) * 12 * 28 + Integer.parseInt(d[1]) * 28 + Integer.parseInt(d[2]);
        }

    }