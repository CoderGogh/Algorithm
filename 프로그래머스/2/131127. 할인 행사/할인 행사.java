import java.util.*;
class Solution {
        Map<String,Integer> map;
        int answer;
        public int solution(String[] want, int[] number, String[] discount) {
            answer = 0;

            // 원하는 상품 갯수의 총합보다 할인기간이 짧은 경우 -> return
            int wanted_items = 0;
            for (int s  :number ) {
                wanted_items += s;
            }
            if(discount.length <wanted_items){
                return answer;
            }

            // 해시에 값 넣기
            map = new HashMap<>();
            for (int i = 0; i < want.length; i++) {
                map.put(want[i],number[i]);
            }

            // 구매 가능한 요일들 찾기
            for(int i = 0; i< discount.length; i++){
                if(wanted_items > discount.length - i){
                    break; // 남은 할인기간 일정의 기간이, 상품 목록들을 사기에 짧음
                }
                if(!map.containsKey(discount[i])){
                    continue;   // 첫날 할인상품이 구매하려는 목록에 없는 경우 스킵
                }

                Map<String, Integer> temp = new HashMap<>(map); // 10일동안 검사 목적으로 쓰일 복사본

                // 10일 연속 구매가능한지 체크
//                for(int j = i; j < i + wanted_items; j++) { // 여기서 오류 나옴 -> ArrayOutOfBound
                for(int j = i; j < i + wanted_items && j < discount.length; j++) { // 여기서 오류 나옴 -> ArrayOutOfBound
                    String item = discount[j];
                    if(temp.containsKey(item)){
                        temp.put(item,temp.get(item) - 1);

                        if(temp.get(item) == 0){
                            temp.remove(item);
                        }
                    }else{
                        break;
                    }
                }
                if(temp.isEmpty()){
                    answer++;
                }

            }
            return answer;
        }
    }