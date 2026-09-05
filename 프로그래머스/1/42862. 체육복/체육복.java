import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 학생 당 체육복 갯수
        int[] student = new int[n];
        Arrays.fill(student,1);
        // 여유분 처리
        for(int surplus : reserve){
            student[surplus-1]++;
        }
        // 잃음 처리
        for(int minus : lost){
            student[minus-1]--;
        }
        // 빌려주기
        int count = 0;        
        for(int i = 0; i<n; i++){
            if(student[i] == 0){
                // 앞 학생에게 빌리는 경우
                if(i-1 >= 0 && student[i-1] == 2){
                    student[i-1]--;
                    student[i]++;
                }
                // 뒷 학생에게 빌리는 경우
                else if(i+1 < n && student[i+1] == 2){
                    student[i]++;
                    student[i+1]--;
                }
            }
            // 수업 참가 가능여부
            if(student[i]>0){
                count++;
            }
        }
        return count;
    }
}