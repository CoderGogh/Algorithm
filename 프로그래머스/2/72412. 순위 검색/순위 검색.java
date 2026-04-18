import java.util.*;
class Solution {
        static Map<String, ArrayList<Integer>> allInfo = new HashMap<>();
        public int[] solution(String[] info, String[] query) {
            allInfo.clear();
            for (String s: info
                 ) {
                String[] person = s.split(" ");
                makeCombination(person,"",0);
            }
            for (ArrayList<Integer> list: allInfo.values()
                 ) {
                Collections.sort(list);
            }
            int[] answer = new int[query.length];
            for (int i = 0; i < query.length; i++) {
                String q = query[i].replace(" and ", "");
                String[] qParts = q.split(" ");
                String key = qParts[0];
                int targetScore = Integer.parseInt(qParts[1]);

                if(allInfo.containsKey(key)){
                    answer[i] = countByBinarySearch(allInfo.get(key), targetScore);
                }else{
                    answer[i] = 0;
                }
            }
            return answer;
        }
        public static void makeCombination(String[] person, String key, int index){
            if(index == 4){
                int score = Integer.parseInt(person[4]);
                Solution.allInfo.putIfAbsent(key, new ArrayList<>());
                Solution.allInfo.get(key).add(score);
                return;
            }
            makeCombination(person,key + person[index], index+1);
            makeCombination(person,key + "-", index+1);
        }
        public static int countByBinarySearch(ArrayList<Integer> scores, int target){
            int left = 0;
            int right = scores.size();
            while(left< right){
                int mid = (left + right) /2;
                if(scores.get(mid) >= target){
                    right = mid;
                }else{
                    left = mid +1;
                }
            }
            return scores.size() - left;
        }
        public static class Applicant{
            Language language;
            Job job;
            Career career;
            SoulFood soulFood;
            int score;

            public Applicant(String language, String job, String career, String soulFood, int score) {
                this.language = Language.fromString(language);
                this.job = Job.fromString(job);
                this.career = Career.fromString(career);
                this.soulFood = SoulFood.fromString(soulFood);
                this.score = score;
            }

            public Language getLanguage() {
                return language;
            }

            public Job getJob() {
                return job;
            }

            public Career getCareer() {
                return career;
            }

            public SoulFood getSoulFood() {
                return soulFood;
            }

            public int getScore() {
                return score;
            }
        }
        public enum Language{
            cpp(0), java(1), python(2), any(3);
            private final int index;
            Language(int index){this.index = index;}
            public int getIndex(){return index;}
            public static Language fromString(String s){
                if(s.equals("-")){ return any;}
                return Language.valueOf(s.toLowerCase());
            }
        }
        public enum Job{
            backend(0), frontend(1),any(2);
            private final int index;
            Job(int index){this.index = index;}
            public int getIndex(){return index;}
            public static Job fromString(String s){
                if(s.equals("-")){return any;}
                return Job.valueOf(s.toLowerCase());
            }
        }
        public enum Career{
            junior(0), senior(1),any(2);
            private final int index;
            Career(int index){this.index = index;}
            public int getIndex(){return index;}
            public static Career fromString(String s){
                if(s.equals("-")){return any;}
                return Career.valueOf(s.toLowerCase());
            }
        }
        public enum SoulFood{
            chicken(0), pizza(1),any(2);
            private final int index;
            SoulFood(int index){this.index = index;}
            public int getIndex(){return index;}
            public static SoulFood fromString(String s){
                if(s.equals("-")){return any;}
                return SoulFood.valueOf(s.toLowerCase());
            }
        }
    }