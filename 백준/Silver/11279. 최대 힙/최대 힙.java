import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main{
        public static void main(String[] args) throws Exception{
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            MaxHeap heap = new MaxHeap(N);
            for (int i = 0; i < N; i++) {
                int v = Integer.parseInt(br.readLine());
                if(v == 0){
                    sb.append(heap.delete()).append("\n");
                }else{
                    heap.insert(v);
                }
            }
            System.out.println(sb);
        }
        public static class MaxHeap{
            int[] heap;
            int size = 0;
            
            public MaxHeap(int value) {
                this.heap = new int[value+1];
            }
            
            // insert
            private void insert(int x){
                if(size < heap.length-1){
                    heap[++size] = x;
                    int pointer = size;

                    while(pointer>1 && heap[pointer/2] < heap[pointer]){
                        swap(pointer/2, pointer);
                        pointer /= 2;
                    }
                }
            }
            // delete
            private int delete(){
                if(size == 0){
                    return 0;
                }
                // 제일 상단 값 저장
                int root = heap[1];
                // 상단값과 하단 값 swap -> 삭제
                heap[1] = heap[size--];
                // 상단부터 하단까지, 자신보다 큰 값으로 이동 & 같거나 작은 경우 스탑
                int pointer = 1;
                while(pointer * 2 <= size){
                    int child = pointer *2;
                    if(child < size && heap[child]< heap[child+1]){
                        child++;
                    }
                    if(heap[child] <= heap[pointer]){
                        break;
                    }
                    swap(pointer,child);
                    pointer = child;
                }
                return root;
            }
            
            //swap
            private void swap(int i, int j){
                int temp = heap[i];
                heap[i] = heap[j];
                heap[j] = temp;
            }
        }
    }