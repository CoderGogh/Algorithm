import java.util.*;
import java.io.*;
public class Main{
        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());
            Stack stack = new Stack(N);
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                switch(command){
                    case "push":
                        stack.push(Integer.parseInt(st.nextToken()));
                        break;
                    case "top":
                        sb.append(stack.top()).append("\n");
                        break;
                    case "size":
                        sb.append(stack.size()).append("\n");
                        break;
                    case "empty":
                        sb.append(stack.empty()).append("\n");
                        break;
                    case "pop":
                        sb.append(stack.pop()).append("\n");
                        break;
                    default:
                        break;  
                }
            }
            System.out.println(sb);
        }
        public static class Stack{
            int top = 0;
            int[]stack; // 1번째 자리부터 값 존재

            public Stack(int n) {
                this.stack = new int[n+1];
            }

            private void push(int value){
                if(full() != 1){
                    stack[++top] = value;
                }
            }
            private int pop(){
                if(empty()!= 1){
                    int value = stack[top];
                    stack[top] = 0;
                    top--;
                    return value;
                }
                return -1;
            }
            private int size(){
                return top;
            }
            private int empty(){
                if(top == 0){
                    return 1;
                }
                return 0;
            }
            private int top(){
                if(empty() != 1){
                    return stack[top];
                }
                return -1;
            }
            private int full(){
                if(top == stack.length-1){
                    return 1;
                }
                return 0;
            }
        }
    }