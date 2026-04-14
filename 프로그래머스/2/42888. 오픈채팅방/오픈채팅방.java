import java.util.*;
class Solution {
        public String[] solution(String[] record) {
            HashMap<String,String> nickname = new HashMap<>();
            String[] method = new String[record.length];
            String[] ids = new String[record.length];
            int count = 0;

            for (int i = 0; i < record.length; i++) {
                String[] content = record[i].split(" ");
                method[i] = content[0];
                ids[i] = content[1];

                if(!content[0].equals("Leave")){
                    nickname.put(content[1],content[2]);
                }
                if(!method[i].equals("Change")){
                    count++;
                }
            }

            String[] answer = new String[count];
            int idx = 0;
            for (int i = 0; i < record.length; i++) {
                if(method[i].equals("Change")){
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                String current_nickname = nickname.get(ids[i]);
                sb.append(current_nickname).append("님이 ");

                if(method[i].equals("Enter")){
                    sb.append("들어왔습니다.");
                }
                if(method[i].equals("Leave")){
                    sb.append("나갔습니다.");
                }
                answer[idx] = sb.toString();
                idx++;
            }
            return answer;
        }

    }