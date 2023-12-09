import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {


//Interviewbit Spiral Order Matrix I Solution - https://www.interviewbit.com/problems/spiral-order-matrix-i/
//Given a matrix of M * N elements (M rows, N columns), return all elements of the matrix in spiral order.
//1 <= M, N <= 1000
//Input Format - The first argument is a matrix A.  Output Format - Return an array of integers representing all elements of the matrix in spiral order.

    public static int[] spiralOrder(final int[][] A) {
        ArrayList<Integer> B = new ArrayList<Integer>();

        // Define the boundaries of the matrix
        int rowBegin = 0;
        int rowEnd = A.length - 1;
        int columnBegin = 0;
        int columnEnd = A[0].length -1;

        // Loop through the matrix in a spiral order
        while(rowBegin <= rowEnd && columnBegin <= columnEnd) {
            // Traverse right
            for(int i = columnBegin; i <= columnEnd; i++){
                B.add(A[rowBegin][i]);
            }
            rowBegin++;

            // Traverse down
            for(int i = rowBegin; i <= rowEnd; i++){
                B.add(A[i][columnEnd]);
            }
            columnEnd--;

            // Traverse left
            if(rowBegin <= rowEnd){
                for(int i = columnEnd; i >= columnBegin; i--){
                    B.add(A[rowEnd][i]);
                }
            }
            rowEnd--;

            // Traverse up
            if(columnBegin <= columnEnd){
                for(int i = rowEnd; i>= rowBegin; i--){
                    B.add(A[i][columnBegin]);
                }
            }
            columnBegin++;
        }

        // Convert the ArrayList to an array
        int[] C = new int[B.size()];
        for(int i = 0; i < C.length; i++)
            C[i] = B.get(i);

        return C;
    }

//Interviewbit Max Non Negative SubArray Solution - https://www.interviewbit.com/problems/max-non-negative-subarray/
//Problem Description : Given an array of integers, A of length N, find out the maximum sum sub-array of non negative numbers from A.
//The sub-array should be contiguous i.e., a sub-array created by choosing the second and fourth element and skipping the third element is invalid.
//Maximum sub-array is defined in terms of the sum of the elements in the sub-array. Find and return the required subarray.
//NOTE: If there is a tie, then compare with segment's length and return segment which has maximum length.
//If there is still a tie, then return the segment with minimum starting index.
//Input Format: The first and the only argument of input contains an integer array A, of length N.
//1 <= N <= 10^5  ; -10^9 <= A[i] <= 10^9


    public static int[] maxset(int[] A) {
        List<Map<Long, int[]>> setList = new ArrayList<Map<Long, int[]>>();
        int N = A.length;
        int retArr[] = new int[0];

        // Loop through the array to find all non-negative subarrays
        for (int i = 0; i < N; i++) {
            if (A[i] >= 0) {
                int j = i;
                // Find the end of the current non-negative subarray
                while (j < N && A[j] >= 0) {
                    j++;
                }
                // Create a subarray and calculate its sum
                int[] set = Arrays.copyOfRange(A, i, j);
                long setsum = 0;
                for (int k = 0; k < set.length; k++) {
                    setsum += set[k];
                }
                // Store the subarray and its sum in a map, and add the map to the list
                int setStartIndex = i;
                int[] finalset = new int[set.length + 2];
                finalset[0] = set.length;
                finalset[1] = setStartIndex;
                for (int k = 0; k < set.length; k++) {
                    finalset[k + 2] = set[k];
                }
                Map<Long, int[]> map = new HashMap<Long, int[]>();
                map.put(setsum, finalset);
                setList.add(map);
                i = j;
            }
        }

        // Find the subarray with the maximum sum
        long previousKey= 0;
        for (Map<Long, int[]> map : setList) {
            for (Map.Entry<Long, int[]> entry : map.entrySet()) {
                if (entry.getKey() >= 0) {
                    if (retArr.length == 0 || entry.getKey() > previousKey) {
                        previousKey = entry.getKey();
                        retArr = entry.getValue();
                    } else if (entry.getKey() == previousKey){
                        if (entry.getValue()[0] > retArr[0]) {
                            retArr = entry.getValue();
                        } else if (entry.getValue()[0] == retArr[0]) {
                            if (entry.getValue()[1] < retArr[1]) {
                                retArr = entry.getValue();
                            }
                        }
                    }
                }
            }
        }

        // Return the subarray with the maximum sum
        if (setList.size() == 0) {
            return new int[]{};
        }else {
            return Arrays.copyOfRange(retArr, 2, retArr.length);
        }
    }

//Interviewbit Min Steps in Infinite Grid Solution - https://www.interviewbit.com/problems/min-steps-in-infinite-grid/
//Problem Description : You are in an infinite 2D grid where you can move in any of the 8 directions :
//(x,y) to (x-1, y-1), (x-1, y), (x-1, y+1), (x  , y-1), (x  , y+1), (x+1, y-1),(x+1, y), (x+1, y+1)
//You are given a sequence of points and the order in which you need to cover the points.. Give the minimum number of steps in which you can achieve it. You start from the first point.

    public static int coverPoints(int[] A, int[] B) {
        int minsteps=0;

        for (int i = 0; i < A.length-1; i++) {
            int x1 = A[i];
            int y1 = B[i];
            int x2 = A[i + 1];
            int y2 = B[i + 1];
            int xdiff = Math.abs(x2 - x1);
            int ydiff = Math.abs(y2 - y1);
            minsteps += Math.max(xdiff, ydiff);

        }

        return minsteps;
    }

    public static void main(String[] args) {
        // Define a 2D array
        int[][] arr = { {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9} };

        // Call the spiralOrder method and print the result
        int[] arr2;
        arr2 = spiralOrder(arr);
        System.out.println(Arrays.toString(arr2));

        // Define an array
        int [] aar = {-63216, -75973, -82720, -81169, 15827, -16728, -20384, 89949, 16046, -68666, 60520, 41991, -33258, 68744, 85718, -66825, 58689, -73014, 2918, 33029, 6727, 33754, -8396, 3083, -46865, 15252, -77876, -32675, -82710, 28192, -54747 };

        // Call the maxset method
        int [] aar2 = maxset(aar);

        int [] A1 = {0, 1, 1,10000000};
        int [] B1 = {0, 1, 2, 10000000};
        System.out.println(coverPoints(A1, B1));
    }
}