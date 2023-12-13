package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorGroupSum extends GroupSum{
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int poolSize = Runtime.getRuntime().availableProcessors();
        OneGroupSum[] oneGroupSums = new OneGroupSum[numberGroups.length];
                ;

        for (int i = 0; i < numberGroups.length; i++) {
            oneGroupSums[i] = new OneGroupSum(numberGroups[i]);
        }
//        System.out.println(poolSize);
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        int res = 0;

        for (int i = 0; i < numberGroups.length; i++) {
            executorService.execute(new OneGroupSum(numberGroups[i]));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        res = Arrays.stream(oneGroupSums)
//                .map(OneGroupSum::run) // не получается правильно написать поток...
//                .sum(OneGroupSum::getSum);

        for (int i = 0; i < numberGroups.length; i++) {
                oneGroupSums[i].run();
                res = oneGroupSums[i].getSum();
        }
        // TODO Homework: reduce sum numbers of numberGroups, use ExecutorService
        return res;
    }
}
