package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum{
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int res = 0;
//        System.out.println(res);

        Thread[] threads = new Thread[numberGroups.length];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new OneGroupSum(numberGroups[i]));
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        res = Arrays.stream(numberGroups)
                .map(i -> OneGroupSum(numberGroups[i])::run) // не получается правильно написать поток...
                .sum(OneGroupSum::getSum);

//        for (int i = 0; i < numberGroups.length; i++) {
//            OneGroupSum oneGroupSum = new OneGroupSum(numberGroups[i]);
//            oneGroupSum.run();
//            res = oneGroupSum.getSum();
//        }
        //// тоже не получается правильно написать поток...
        // TODO Homework: reduce sum numbers of numberGroups, use Threads
        return res;
    }
}
