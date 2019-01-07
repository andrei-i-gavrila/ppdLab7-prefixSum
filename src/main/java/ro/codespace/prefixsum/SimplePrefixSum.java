package ro.codespace.prefixsum;

public class SimplePrefixSum {
    public int[] prefixSum(int[] numbers) {
        var result = new int[numbers.length];
        result[0] = numbers[0];
        for (var i = 1; i < numbers.length; i++) {
            result[i] = (result[i-1] + numbers[i]);
        }

        return result;
    }
}
