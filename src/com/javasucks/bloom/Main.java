package com.javasucks.bloom;

import java.util.HashSet;
import java.util.Random;

public class Main {

    public static String randomString(int length) {
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            Random rand = new Random();
            int idx = rand.nextInt(candidateChars.length());
            builder.append(candidateChars.charAt(idx));
        }
        return builder.toString();
    }

    public static byte[][] randomBytes(int num) {
        int m = 128;
        byte[][] bytes = new byte[num][m];
        for (int i = 0; i < num; i++) {
            bytes[i] = randomString(m).getBytes();
        }
        return bytes;
    }

    public static void main(String[] args) {
        int n = 1000000;
        byte[][] bytes = randomBytes(n * 2);
        Bloom filter = new Bloom(10, n * 8);

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            filter.add(bytes[i]);
        }
        long end = System.nanoTime();
        System.out.println("Time to add 1024 elements (ms): " + (end - start) / 1e6);

        start = System.nanoTime();
        int falsePos = 0;
        for (int i = n; i < n * 2; i++) {
            if (filter.contains(bytes[i])) {
                falsePos++;
            }
        }
        end = System.nanoTime();
        System.out.println("Time to check 1024 elements (ms): " + (end - start) / 1e6);
        System.out.println("False positives: " + falsePos);

        int falseNeg = 0;
        for (int i = 0; i < n; i++) {
            if (!filter.contains(bytes[i])) {
                falseNeg++;
            }
        }
        System.out.println("False negatives: " + falseNeg);

        /* ----------- */

        System.out.println("Hash table benchmarks");
        HashSet<byte[]> set = new HashSet<>();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            set.add(bytes[i]);
        }
        end = System.nanoTime();
        System.out.println("Time to add 1024 elements (ms): " + (end - start) / 1e6);

        start = System.nanoTime();
        for (int i = n; i < n * 2; i++) {
            if (set.contains(bytes[i])) {
                falsePos++;
            }
        }
        end = System.nanoTime();
        System.out.println("Time to check 1024 elements (ms): " + (end - start) / 1e6);

    }
}
