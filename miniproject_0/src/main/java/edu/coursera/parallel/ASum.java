package edu.coursera.parallel;

import java.util.concurrent.RecursiveAction;

public class ASum extends RecursiveAction {
    int[] A;
    int LO, HI;
    int SUM;

    public ASum(int[] src, int start, int end, int sum){
        A = src;
        LO = start;
        HI = end;
        SUM = sum;
    }

    @Override
    protected void compute() {
        SUM = 0;
        System.out.println("This result - - was processed by "
                + Thread.currentThread().getName());
        for (int i = LO; i <= HI; i++) SUM += A[i];

        int splitIntoTwo = (LO + HI)/2;

        ASum L = new ASum(A,LO, splitIntoTwo, SUM);
        ASum R = new ASum(A, splitIntoTwo, HI, SUM);

        L.fork();
        R.fork();

        L.join();
        R.join();
    }
}
