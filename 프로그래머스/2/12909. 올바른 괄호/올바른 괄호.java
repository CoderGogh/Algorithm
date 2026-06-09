class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack stack = new Stack(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '('){
                stack.push(c);
            }else{
                if(!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }else{
                    answer = false;
                    break;
                }
            }
        }
        return stack.isEmpty() ? answer : false;
    }
    class Stack{
        int top = -1;
        char[] stack;
        public Stack(int n){
            stack = new char[n+1];
        }
        void push(char x){
            stack[++top] = x;
        }
        char pop(){
            return stack[top--];
        }
        boolean isEmpty(){
            return top == -1;
        }
        char peek(){
            return stack[top];
        }

    }

}