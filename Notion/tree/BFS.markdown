* 핵심 : Queue 사용

* 수도코드

visited[] 배열 준비
queue 생성
adj[][] 입력 받기

queue.push(여행_계획[0])
visited[여행_계획[0]] = true

while queue가 비어있지 않음:
    current = queue.pop()
    for next from 1 to N:
        if adj[current][next] == 1 AND visited[next] == false:
            visited[next] = true
            queue.push(next)

// 이후 여행_계획의 모든 도시가 visited인지 확인 (DFS와 동일)