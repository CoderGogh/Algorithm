import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별로 재생횟수 집계
        Map<String, Integer> genre = new HashMap<>();
        Map<String, List<Integer>> song = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            genre.put(
                genres[i],
                genre.getOrDefault(genres[i], 0) + plays[i]
            ); // 장르합산
            
            song.putIfAbsent(genres[i], new ArrayList<>());
            song.get(genres[i]).add(i); // 장르에 속한 노래
        }
        
        // 내림차순한 장르 정렬
        List<Map.Entry<String, Integer>> genreList = new ArrayList<>(genre.entrySet());
        genreList.sort((a, b) -> b.getValue() - a.getValue());
        
        List<Integer> answerList = new ArrayList<>();
        
        // 향상된 for 문 올바른 문법(: 사용) 및 변수명 일치화
        for (Map.Entry<String, Integer> entry : genreList) {
            String genKey = entry.getKey();
            List<Integer> songs = song.get(genKey); // songMap -> song으로 수정
            
            songs.sort((a, b) -> {
                if (plays[a] != plays[b]) {
                    return plays[b] - plays[a]; // 재생 횟수 내림차순
                }
                return a - b; // 고유 번호 오름차순
            });
            
            for (int i = 0; i < Math.min(songs.size(), 2); i++) {
                answerList.add(songs.get(i)); // answerList로 통일
            }
        }
        
        return answerList.stream().mapToInt(i -> i).toArray();
    }
}