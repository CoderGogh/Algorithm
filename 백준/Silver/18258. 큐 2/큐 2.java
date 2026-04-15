import java.util.*;
import java.io.*; // BufferedReader 등을 사용하기 위해 반드시 필요합니다.

public class Main { // 클래스 이름은 반드시 Main이어야 합니다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Queue q = new Queue(N);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
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

    // 작성하신 Queue 클래스를 Main 클래스 안에 넣었습니다.
    public static class Queue {
        int queue[];
        int rear = 0;
        int front = 0;

        public Queue(int N) {
            queue = new int[N];
        }

        public void push(int x) {
            if (!full()) {
                queue[rear++] = x;
            }
        }

        public int pop() {
            if (empty() == 0) {
                return queue[front++];
            }
            return -1;
        }

        public int size() {
            return rear - front;
        }

        public int empty() {
            return (front == rear) ? 1 : 0;
        }

        public int front() {
            if (empty() == 1) {
                return -1;
            }
            return queue[front];
        }

        public int back() {
            if (empty() == 1) {
                return -1;
            }
            return queue[rear - 1];
        }

        public boolean full() {
            return rear == queue.length;
        }
    }
}