class Solution {
        public int solution(String s) {
            int answer = 0;
            Stack stack = new Stack(s.length());
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(!stack.isEmpty() && stack.peek() == c){
                    stack.pop();
                }
                else{
                    stack.push(c);
                }
            }
            if(stack.isEmpty()) {
                answer = 1;
            }
            return answer;
        }
        public static class Stack{
            int top = 0;    // 첫번째 인덱스 = 0
            char[] stack;    // 0번째 자리부터 삽입

            public Stack(int value) {
                stack = new char[value + 1];
            }
            private char peek(){
                if(isEmpty()){
                    throw new RuntimeException("Stack is Empty");
                }
                return stack[top];
            }
            private boolean isEmpty(){
                return top == 0;
            }
            private boolean isFull(){
                return top == stack.length - 1;
            }
            private void push(char x){
                if(isFull()){
                    throw new RuntimeException("Stack Overflow");
                }
                stack[++top] = x;
            }
            private char pop(){
                if(isEmpty()){
                    throw new RuntimeException("Stack is Empty");
                }
                return stack[top--];
            }
        }
    }