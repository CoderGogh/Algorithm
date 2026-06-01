import java.util.HashMap;
class Solution {
        HashMap<String,Integer> map = new HashMap<>();
        public int solution(String[][] clothes) {
            int answer = 1;
            for (String[]cloth :clothes ) {
                String type = cloth[1];
                map.put(type,map.getOrDefault(type,0) + 1);
            }
            // 상의 3
            // 하의 2
            //
            // 모든 상의마다, 안입는 경우의 수 존재
            // 모든 하의마다, 안입는 경우의 수 존재
            // (3+1) * (2+1) -1 = 전체 갯수(둘다 안 입는 경우 배제)
            for (int count : map.values()) {
                answer *= (count +1);                
            }
            return answer -1 ;
        }
    }