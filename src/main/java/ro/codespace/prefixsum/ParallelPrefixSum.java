package ro.codespace.prefixsum;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class ParallelPrefixSum {

    public int[] prefixSum(int[] numbers) throws InterruptedException {
        int maxI = 32 - Integer.numberOfLeadingZeros(numbers.length);
        var executorService = Executors.newFixedThreadPool(1);


        var nextNumbers = new int[numbers.length];

        for (var i = 0; i < maxI; i++) {
            var cutoffJ = (int) Math.pow(2, i);
            CountDownLatch latch = new CountDownLatch(numbers.length);
            for (var j = 0; j < numbers.length; j++) {
                int[] finalNextNumbers = nextNumbers;
                int[] finalNumbers = numbers;
                int finalJ = j;
                executorService.submit(() -> {
                    if (finalJ < cutoffJ) {
                        finalNextNumbers[finalJ] = finalNumbers[finalJ];
                    } else {
                        finalNextNumbers[finalJ] = finalNumbers[finalJ] + finalNumbers[finalJ - cutoffJ];
                    }
                    latch.countDown();
                });
            }
            latch.await();
            var temp = numbers;
            numbers = nextNumbers;
            nextNumbers = temp;
        }
        executorService.shutdown();
        return numbers;
    }
}
