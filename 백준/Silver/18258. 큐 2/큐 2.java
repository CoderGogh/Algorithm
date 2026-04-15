import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main { // 백준 제출 시 클래스명은 Main이어야 함
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();

            int N = Integer.parseInt(br.readLine());
            Queue q = new Queue(N);

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine()); // br.readLine()으로 수정
                String command = st.nextToken();

                switch (command) {
                    case "push":
                        q.push(Integer.parseInt(st.nextToken()));
                        break;
                    case "pop":
                        sb.append(q.pop()).append("\n");
                        break;
                    case "size":
                        sb.append(q.size()).append("\n");
                        break;
                    case "empty":
                        sb.append(q.empty()).append("\n");
                        break;
                    case "front":
                        sb.append(q.front()).append("\n");
                        break;
                    case "back":
                        sb.append(q.back()).append("\n");
                        break;
                }
            }
            System.out.println(sb);
        }

        // static을 붙여야 main에서 바로 인스턴스 생성이 가능합니다.
        public static class Queue {
            int front = 0;
            int rear = 0;
            int[] queue;

            public Queue(int value) {
                queue = new int[value];
            }

            public void push(int x) {
                queue[rear++] = x;
            }

            public int empty() {
                return (front == rear) ? 1 : 0;
            }

            public int pop() {
                if (empty() == 1) return -1;
                return queue[front++]; // 값을 반환하고 front 이동
            }

            public int size() {
                return rear - front;
            }

            public int front() {
                if (empty() == 1) return -1;
                return queue[front];
            }

            public int back() {
                if (empty() == 1) return -1;
                return queue[rear - 1];
            }
        }
    }
