import java.util.*;
class Solution {
        HashMap<Integer, Integer> map = new HashMap<>();
        public int solution(int[] nums) {
            int answer = 0;
            int total = nums.length;
            int n = total /2;
            for(int i = 0; i < total; i++){
                map.put(nums[i], map.getOrDefault(nums[i],0)+1);
            }
            if(map.keySet().size() < n){
                answer = map.keySet().size();
            }else{
                answer = n;
            }
            return answer;
        }
    }