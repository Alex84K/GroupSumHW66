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

//        res = Arrays.stream(numberGroups)
//                .forEach(s -> new OneGroupSum(numberGroups[s]))
//                .map(s -> new OneGroupSum(s)::run) // не получается правильно написать поток...
//                .sum(OneGroupSum::getSum);

        for (int i = 0; i < numberGroups.length; i++) {
            OneGroupSum oneGroupSum = new OneGroupSum(numberGroups[i]);
            oneGroupSum.run();
            res = oneGroupSum.getSum();
        }
        // TODO Homework: reduce sum numbers of numberGroups, use ExecutorService
        return res;
    }
}