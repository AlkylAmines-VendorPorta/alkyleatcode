package com.novelerp.eat.test;

import java.util.Arrays;
import java.util.Scanner;

public class test111 {
        // Complete the minimumBribes function below.
        static void minimumBribes(int[] q) {
            int minBribes = 0;
            boolean isChaotic = false;
            Integer[] initialQ = new Integer[q.length];
            for(int i=0;i<initialQ.length;i++){
                initialQ[i]=i+1;
            }
                    
            for(int i=0;i<q.length;i++){
                if(isChaotic){
                    break;
                }
                if(initialQ[i] == q[i]){
                    continue;
                }else if(initialQ[i]<q[i]){
                	if((q[i] - (i+1))<3){
                		isChaotic=true;
                        break;
                	}
                    int temp = q[i];
                    int index = Arrays.asList(initialQ).indexOf(q[i]); 
                    int bribeCount = 0;
                    for(int j=index;j>=i;j--){
                        if(j==i){
                            initialQ[j]=q[i];
                        }else{
                            initialQ[j]=initialQ[j-1];
                            bribeCount++;
                        }
                       
                    }
                    if(bribeCount>2){
                        isChaotic=true;
                        break;
                    }
                }
            }
             if(isChaotic){
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
