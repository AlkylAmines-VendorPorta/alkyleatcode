package com.novelerp.eat.test;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class TEST123 {
	 // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int minBribes = 0;
        boolean isChoatic = false;
        for(int i=0;i<q.length;i++){
            if(i+1 >= q[i]){
                continue;
            }else if((q[i] - (i+1))<3){
                minBribes = minBribes + q[i] - (i+1);
            }else{
                isChoatic = true;
                break;
            }
        }
        if(isChoatic){
            System.out.println("Too chaotic");
        }else{
            System.out.println(minBribes);
        }
        
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}