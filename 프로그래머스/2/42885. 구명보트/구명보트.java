import java.util.*;
class Solution {
            public int solution(int[] people, int limit) {
                Arrays.sort(people); // 일단 줄부터 세우자 (오름차순)

                int light = 0;              // 제일 가벼운 애 (왼쪽 끝)
                int heavy = people.length - 1; // 제일 무거운 애 (오른쪽 끝)
                int boat = 0;

                while (light <= heavy) {
                    // 둘이 합쳐서 무게 제한 안 넘으면? 가벼운 애도 같이 태워 보냄
                    if (people[light] + people[heavy] <= limit) {
                        light++;
                    }
                    // 무거운 애는 조건 상관없이 무조건 이번 보트에 탐
                    heavy--;
                    boat++;
                }

                return boat;
            }
        }