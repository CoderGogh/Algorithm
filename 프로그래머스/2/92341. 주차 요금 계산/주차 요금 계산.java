import java.util.*;
class Solution {
        HashMap<String,Integer> totalTime = new HashMap<>();
        // key : 차량번호 & value: 누적시간
        public int[] solution(int[] fees, String[] records) {
            HashMap<String, String> map = new HashMap<>();
            // key: 차량번호  & value : 입출차시간
            List<Integer> answer = new ArrayList<>();
            for (int i = 0; i < records.length; i++) {
                String clock = records[i].split(" ")[0];
                String car_code = records[i].split(" ")[1];
                String InOut = records[i].split(" ")[2];
                // In 이면 map에 put함
                if(InOut.equals("IN")){
                    map.put(car_code,clock);    // 입차시간
                }
                // out 이면 map에서 해당 차량번호를 get -> 요금 계산 로직
                else if(InOut.equals("OUT")){
                    totalTime.put(car_code,totalTime.getOrDefault(car_code,0)+makeReceit(map.get(car_code),clock)); // 누적시간 더하기
                    map.remove(car_code);   // 그 다음 입차를 위해 키 제거
                }
            }
            // out 기록이 없는 차량에 대한 시간 추가
            for (String key: map.keySet()
                 ) {
                totalTime.put(key,totalTime.getOrDefault(key,0) + makeReceit(map.get(key),"23:59"));
            }
            // 계산 로직 --> 차량번호 순서대로 꺼내서 정산 -> list에 담기
            // in-out 기록의 차량에 대한 계산 로직
            List<String> keys  = new ArrayList<>(totalTime.keySet());
            Collections.sort(keys);
            for (String key: keys
                 ) {
                answer.add(fee(key,fees));
            }
            // 혹은 TreeMap 사용하기
            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
        // 누적 주차시간만 계산
        private int makeReceit(String inTime, String outTime){
            int in_time = Integer.parseInt(inTime.split(":")[0]) * 60 +Integer.parseInt(inTime.split(":")[1]);
            int out_time = Integer.parseInt(outTime.split(":")[0]) * 60 +Integer.parseInt(outTime.split(":")[1]);
            return out_time-in_time;
        }
        private int fee(String key,int[]fees){
            int time = totalTime.get(key);
            if(time<=fees[0]){
                return fees[1];
            }
            else{
                int extra = time - fees[0];
                int units = (int) Math.ceil((double)extra/fees[2]);
                return fees[1] + units * fees[3];
            }
//            int basic_fee = 0;
//            int added_fee = 0;
//            if(totalTime.get(key) > fees[0]) {
//                basic_fee = fees[1];
//                int remain = Math.round((totalTime.get(key) - fees[0]) / fees[2]);
//                if (remain >= 0) {
//                    added_fee = remain * fees[3];
//                }
//            }
        }
    }