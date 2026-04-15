* 핵심 : visited 배열로 재방문 방지

* 수도 코드
visited[] 배열 준비 (전부 false)
adj[][] 인접 행렬 입력 받기

function DFS(currentCity):
visited[currentCity] = true
for nextCity from 1 to N:
if adj[currentCity][nextCity] == 1 AND visited[nextCity] == false:
DFS(nextCity)

// 메인 로직
DFS(여행_계획[0]) // 첫 번째 도시부터 시작
for city in 여행_계획:
if visited[city] == false:
return "NO"
return "YES"