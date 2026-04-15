* 핵심 : N의 크기가 작을 때 가능 & 모든 곳에서 모든 곳을 방문 가능한 지 
* 필요 상황 : 모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구해야 하는 상황(모든 노드 간의 길이를 앎)

* 수도코드

dist[][] 배열을 입력받은 인접 행렬로 초기화
(단, dist[i][i]는 항상 1로 설정)

for k from 1 to N:       // 거쳐가는 도시
for i from 1 to N:   // 출발 도시
for j from 1 to N: // 도착 도시
if dist[i][k] == 1 AND dist[k][j] == 1:
dist[i][j] = 1 // 연결성 업데이트

// 메인 로직
for i from 0 to M-2:
start = 여행_계획[i]
end = 여행_계획[i+1]
if dist[start][end] == 0:
return "NO"
return "YES"