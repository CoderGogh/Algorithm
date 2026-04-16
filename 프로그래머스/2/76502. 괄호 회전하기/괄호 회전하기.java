class Solution {
        public int solution(String s) {
            int answer = 0;
            Stack stack = new Stack(s.length());
            for (int i = 0; i < s.length(); i++) {
                String rotate  = s.substring(i) + s.substring(0,i);
                if(isValid(rotate,stack)){
                    answer++;
                }
                stack.clear();
            }

            return answer;
        }
        private boolean isValid(String s, Stack stack){
            for (char c: s.toCharArray()) {
                if(c == '(' || c =='{' || c == '['){
                    stack.push(c);
                }else{
                    if(stack.isEmpty()){
                        return false;
                    }
                    char top = stack.peek();
                    if(c == ')' && top != '('){
                        return false;
                    }
                    if(c == '}' && top != '{'){
                        return false;
                    }
                    if(c == ']' && top != '['){
                        return false;
                    }
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
        public static class Stack{
            int top = -1;
            char[] stack;

            public Stack(int value) {
                stack = new char[value];
            }
            private char pop(){
                if(isEmpty()){
                   throw new RuntimeException("Stack is empty");
                }
                return stack[top--];
            }
            private void push(char x){
                if(isFull()){
                    throw new RuntimeException("Stack Overflow");
                }
                stack[++top] = x;
            }
            private char peek(){
                if(isEmpty()){
                    throw new RuntimeException("Stack is empty");
                }
                return stack[top];
            }
            private boolean isFull(){
                return top == stack.length - 1;
            }
            private boolean isEmpty(){
                return top == -1;
            }
            private void clear(){
                top = -1;
            }
        }
    }