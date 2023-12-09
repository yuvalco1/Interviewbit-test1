import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class Main {


//  Interviewbit Spiral Order Matrix I Solution - https://www.interviewbit.com/problems/spiral-order-matrix-i/
//Given a matrix of M * N elements (M rows, N columns), return all elements of the matrix in spiral order.
//1 <= M, N <= 1000
//Input Format - The first argument is a matrix A.  Output Format - Return an array of integers representing all elements of the matrix in spiral order.

    public static int[] spiralOrder(final int[][] A) {
        ArrayList<Integer> B = new ArrayList<Integer>();

// iterate normally through the matrix
//        int M = A.length;
//        int N = A[0].length;
//        for(int i=0; i < M; i++) {
//            for (int j=0; j < N; j++) {
//                B.add(A[i][j]);
//            }
//        }

//      iterate through the matrix in spiral order
        int rowBegin = 0;
        int rowEnd = A.length - 1;
        int columnBegin = 0;
        int columnEnd = A[0].length -1;

        while(rowBegin <= rowEnd && columnBegin <= columnEnd) {
            for(int i = columnBegin; i <= columnEnd; i++){
                B.add(A[rowBegin][i]);
            }
            rowBegin++;

            for(int i = rowBegin; i <= rowEnd; i++){
                B.add(A[i][columnEnd]);
            }
            columnEnd--;

            if(rowBegin <= rowEnd){
                for(int i = columnEnd; i >= columnBegin; i--){
                    B.add(A[rowEnd][i]);
                }
            }
            rowEnd--;

            if(columnBegin <= columnEnd){
                for(int i = rowEnd; i>= rowBegin; i--){
                    B.add(A[i][columnBegin]);
                }
            }
            columnBegin++;
        }

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
        for (int i = 0; i < N; i++) {
            if (A[i] >= 0) {
                int j = i;
                while (j < N && A[j] >= 0) {
                    j++;
                }
                int[] set = Arrays.copyOfRange(A, i, j);
                long setsum = 0;
                for (int k = 0; k < set.length; k++) {
                    setsum += set[k];
                }
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
        if (setList.size() == 0) {
            return new int[]{};
        }else {
            return Arrays.copyOfRange(retArr, 2, retArr.length);
        }

    }


        public static void main(String[] args) {


        int[][] arr = { {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9} };

        int[] arr2;
        arr2 = spiralOrder(arr);

        System.out.println(   Arrays.toString(arr2));

        //int [] aar = {-63216, -75973, -82720, -81169, 15827, -16728, -20384, 89949, 16046, -68666, 60520, 41991, -33258, 68744, 85718, -66825, 58689, -73014, 2918, 33029, 6727, 33754, -8396, 3083, -46865, 15252, -77876, -32675, -82710, 28192, -54747};
        int [] aar = {-63216, -75973, -82720, -81169, 15827, -16728, -20384, 89949, 16046, -68666, 60520, 41991, -33258, 68744, 85718, -66825, 58689, -73014, 2918, 33029, 6727, 33754, -8396, 3083, -46865, 15252, -77876, -32675, -82710, 28192, -54747 };
        int [] aar2 = maxset(aar);


    }
}