import java.io.*;
import java.util.*;
public class Main{
        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());
            minHeap heap = new minHeap(N);
            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(br.readLine());
                if(n == 0){
                    sb.append(heap.delete()).append("\n");
                }
                else{
                    heap.insert(n);
                }
            }
            System.out.println(sb);
        }
        public static class minHeap{
           private int[] heap;
           private int size;

            private minHeap(int capacity) {
                this.heap = new int[capacity+1];
                this.size = 0;
            }
            private void insert(int val){
                heap[++size] = val;
                int current = size;
                while(current > 1 && heap[current] < heap[current/2]){
                    swap(current, current/2);
                    current /= 2;
                }
            }
            private int delete(){
                if(size == 0){
                    return 0;
                }
                int root = heap[1];
                heap[1] = heap[size--];

                int current = 1;
                while(current * 2 <= size){
                    int child = current * 2;
                    if(child < size && heap[child] > heap[child+1]){
                        child++;
                    }
                    if(heap[current] <= heap[child]){
                        break;
                    }
                    swap(current, child);
                    current = child;
                }
                return root;
            }
            private void swap(int i, int j){
                int temp = heap[i];
                heap[i] = heap[j];
                heap[j] = temp; // 삭제 필요
            }
            private boolean empty(){
                if(size == 0){
                    return true;
                }
                return false;
            }
        }
    }