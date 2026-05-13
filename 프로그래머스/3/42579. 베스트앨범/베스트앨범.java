import java.util.*;
class Solution {
        HashMap<String, Integer> map = new HashMap<>();  // 장르별 총 재생횟수
        HashMap<String, List<Music>> genreMap = new HashMap<>();  // 장르

        public int[] solution(String[] genres, int[] plays) {

            for(int i=0;i<genres.length;i++){
                String g = genres[i];
                int p = plays[i];

                // 장르별 총 합산용
                map.put(g, map.getOrDefault(g, 0) + p);

                // 장르별 곡 리스트
                genreMap.putIfAbsent(g,new ArrayList<>());
                genreMap.get(g).add(new Music(i,p));
            }
            List<String> sorted = new ArrayList<>(map.keySet());
            sorted.sort((a,b) -> map.get(b) - map.get(a));  // 내림차순

            List<Integer> result = new ArrayList<>();
            for (String g : sorted) {
                List<Music> musics = genreMap.get(g);
                Collections.sort(musics);

                result.add(musics.get(0).id);
                if(musics.size() > 1){
                    result.add(musics.get(1).id);
                }
            }
            return result.stream().mapToInt(Integer::intValue).toArray();
        }
        class Music implements Comparable<Music>{
            int id;
            int play;

            public Music(int id, int play) {
                this.id = id;
                this.play = play;
            }
            public int compareTo(Music other){
                if(this.play == other.play){
                    return this.id - other.id;
                }
                return other.play - this.play;
            }
        }
    }