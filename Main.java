import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    static int[][]A;
    static int[][]B;
    static int[][]C;
    public static void main(String args[]) {

        int aDim1=5;
        int aDim2=6;
        int bDim2=12;
        A=new int[aDim1][aDim2];
        B=new int[aDim2][bDim2];
        C=new int[aDim1][bDim2];

        for(int i=0; i<A.length; i++){
            for(int j=0; j<A[0].length; j++){
                A[i][j]=i+j+2;
            }
        }
        for(int i=0; i<B.length; i++){
            for(int j=0; j<B[0].length; j++){
                B[i][j]=3+i+j*i;
            }
        }


        ExecutorService threadPool = Executors.newFixedThreadPool(aDim1);
        for(int i=0; i<A.length; i++){
            threadPool.execute(new Counter(i));
        }

       try {
           threadPool.shutdown();
           threadPool.awaitTermination(5, TimeUnit.SECONDS);
       } catch(InterruptedException e){
           System.out.println("\n+Threads interrupted!");
       }

        System.out.println("Result matrix: ");
        for(int i=0; i<C.length; i++){
            for(int j=0; j<C[0].length; j++){
                System.out.print(C[i][j]+"\t");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println("A matrix: ");
        for(int i=0; i<A.length; i++){
            for(int j=0; j<A[0].length; j++){
                System.out.print(A[i][j]+"\t");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println("B matrix: ");
        for(int i=0; i<B.length; i++){
            for(int j=0; j<B[0].length; j++){
                System.out.print(B[i][j]+"\t");
            }
            System.out.println(" ");
        }

    }


    static class Counter implements Runnable {
        int number;

        public Counter(int number) {
            this.number = number;
        }

        public void run() {//подсчет строки матрицы
            System.out.println("started "+number);
            for(int i=0; i<C[number].length; i++){
                for(int j=0; j<B.length;j++){
                    System.out.println(number+" ");
                    C[number][i]+=A[number][j]*B[j][i];
                }
            }
            System.out.println("           ready "+number);
        }
    }
}