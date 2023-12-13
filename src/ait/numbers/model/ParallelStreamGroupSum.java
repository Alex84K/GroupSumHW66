package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelStreamGroupSum extends GroupSum{
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int res = 0;
//        System.out.println(res);

//        for (int i = 0; i < numberGroups.length; i++) {
//            res += Arrays.parallelStream(numberGroups[i]).parallel()
//                    .forEach(s -> new OneGroupSum(numberGroups[s]))
//                    .map(s -> new OneGroupSum(s)::run) // не получается правильно написать поток...
//                    .sum(OneGroupSum::getSum);
//        }

        // TODO Advanced Homework: reduce sum numbers of numberGroups, use Arrays.stream(...).parallel()
        return res;
    }
}
