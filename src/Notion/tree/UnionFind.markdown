* 유니온 파인드 
Union(합치다) + find(찾다)
하나의 뭉치로 묶여있는 지 탐색

* 방법
1. find : 노드 x가 속한 집합의 대표 루트를 찾음
2. union : x와 y가 속한 두 집합을 하나로 합침

* 수도코드
parent[] 배열을 자기 자신으로 초기화 (parent[i] = i)

function find(x):
if parent[x] == x:
return x
return parent[x](조상님) = find(parent[x]) // 경로 압축 (속도 최적화)

function union(x, y):
rootX = find(x)
rootY = find(y)
if rootX != rootY:
parent[rootY] = rootX // 한쪽의 부모를 다른 쪽에 연결
