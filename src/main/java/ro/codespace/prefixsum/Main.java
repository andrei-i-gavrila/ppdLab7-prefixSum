package ro.codespace.prefixsum;

import com.google.common.base.Stopwatch;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String... args) throws InterruptedException {
        int size = 100;
        var random = new Random(1124314);

        var numbers = random.ints(size, 0, 10).toArray();

//        System.out.println(Arrays.toString(numbers));

        var simpleSummer = new SimplePrefixSum();
        System.out.print("Started Simple prefix sum" + "...");
        var stopwatchSimple = Stopwatch.createStarted();
        var prefixSum = simpleSummer.prefixSum(numbers);
        stopwatchSimple.stop();
        System.out.println(Arrays.toString(prefixSum));
        System.out.println("took " + stopwatchSimple.elapsed(TimeUnit.MILLISECONDS) + "ms");


        var parallelSummer = new ParallelPrefixSum();
        System.out.print("Started Parallel prefix sum" + "...");
        var stopwatchParallel = Stopwatch.createStarted();
        var parallelPrefixSum = parallelSummer.prefixSum(numbers);
        stopwatchParallel.stop();
        System.out.println(Arrays.toString(parallelPrefixSum));
        System.out.println("took " + stopwatchParallel.elapsed(TimeUnit.MILLISECONDS) + "ms");


    }
}
