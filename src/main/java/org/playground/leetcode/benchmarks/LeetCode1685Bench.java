package org.playground.leetcode.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class LeetCode1685Bench {


    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    private int[] nums;

    @Setup(Level.Invocation)
    public void setup() {
        nums = IntStream.range(1, 100_001).toArray();
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    public int[] benchReuseInputArray() {
        return getSumAbsoluteDifferencesReuseInputArray(nums);
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    public int[] benchAllocateResultArray() {
        return getSumAbsoluteDifferencesAllocateResultArray(nums);
    }

    public int[] getSumAbsoluteDifferencesReuseInputArray(int[] nums) {
        var total = 0;
        for (int num : nums) {
            total += num;
        }
        int accum = 0;
        for (int i = 0; i < nums.length; i++) {
            int d = 0;
            if (i > 0) {
                d += nums[i] * i - accum;
            }
            if (i < (nums.length - 1)) {
                d += (total - accum - nums[i]) - nums[i] * (nums.length - 1 - i);
            }
            accum += nums[i];
            nums[i] = d;
        }
        return nums;
    }

    public int[] getSumAbsoluteDifferencesAllocateResultArray(int[] nums) {
        var total = 0;
        for (int num : nums) {
            total += num;
        }
        int[] result = new int[nums.length];
        int accum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                result[i] += nums[i] * i - accum;
            }
            if (i < (nums.length - 1)) {
                result[i] += (total - accum - nums[i]) - nums[i] * (nums.length - 1 - i);
            }
            accum += nums[i];
        }
        return result;
    }


}
