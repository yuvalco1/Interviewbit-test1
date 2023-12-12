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
        ArrayList<Integer> B = new ArrayList<>();

        // Define the boundaries of the matrix
        int rowBegin = 0;
        int rowEnd = A.length - 1;
        int columnBegin = 0;
        int columnEnd = A[0].length - 1;

        // Loop through the matrix in a spiral order
        while (rowBegin <= rowEnd && columnBegin <= columnEnd) {
            // Traverse right
            for (int i = columnBegin; i <= columnEnd; i++) {
                B.add(A[rowBegin][i]);
            }
            rowBegin++;

            // Traverse down
            for (int i = rowBegin; i <= rowEnd; i++) {
                B.add(A[i][columnEnd]);
            }
            columnEnd--;

            // Traverse left
            if (rowBegin <= rowEnd) {
                for (int i = columnEnd; i >= columnBegin; i--) {
                    B.add(A[rowEnd][i]);
                }
            }
            rowEnd--;

            // Traverse up
            if (columnBegin <= columnEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    B.add(A[i][columnBegin]);
                }
            }
            columnBegin++;
        }

        // Convert the ArrayList to an array
        int[] C = new int[B.size()];
        for (int i = 0; i < C.length; i++)
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
        List<Map<Long, int[]>> setList = new ArrayList<>();
        int N = A.length;
        int[] retArr = new int[0];

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
                Map<Long, int[]> map = new HashMap<>();
                map.put(setsum, finalset);
                setList.add(map);
                i = j;
            }
        }

        // Find the subarray with the maximum sum
        long previousKey = 0;
        for (Map<Long, int[]> map : setList) {
            for (Map.Entry<Long, int[]> entry : map.entrySet()) {
                if (entry.getKey() >= 0) {
                    if (retArr.length == 0 || entry.getKey() > previousKey) {
                        previousKey = entry.getKey();
                        retArr = entry.getValue();
                    } else if (entry.getKey() == previousKey) {
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
        if (setList.isEmpty()) {
            return new int[]{};
        } else {
            return Arrays.copyOfRange(retArr, 2, retArr.length);
        }
    }

//Interviewbit Min Steps in Infinite Grid Solution - https://www.interviewbit.com/problems/min-steps-in-infinite-grid/
//Problem Description : You are in an infinite 2D grid where you can move in any of the 8 directions :
//(x,y) to (x-1, y-1), (x-1, y), (x-1, y+1), (x  , y-1), (x  , y+1), (x+1, y-1),(x+1, y), (x+1, y+1)
//You are given a sequence of points and the order in which you need to cover the points.. Give the minimum number of steps in which you can achieve it. You start from the first point.

    public static int coverPoints(int[] A, int[] B) {
        int minsteps = 0;

        for (int i = 0; i < A.length - 1; i++) {
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

//Interviewbit Max Sum Contiguous Subarray Solution - https://www.interviewbit.com/problems/max-sum-contiguous-subarray/
// Find the contiguous subarray within an array, A of length N which has the largest sum.
//Input Format: The first and the only argument contains an integer array, A. Output Format: Return an integer representing the maximum possible sum of the contiguous subarray.
// Solution is to use complexity N (not N^2 - double loop); The idea of Kadane’s algorithm is to maintain a variable max_ending_here that stores the maximum sum contiguous subarray ending at current index
// and a variable max_so_far stores the maximum sum of contiguous subarray found so far, Everytime there is a positive-sum value in max_ending_here compare it with max_so_far and update max_so_far if it is greater than max_so_far.
// see https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/ for more details


    public static int maxSubArray(final int[] A) {
        int maxsum = Integer.MIN_VALUE;
        int max_ending_here = 0;
        for (int i = 0; i < A.length; i++) {
            max_ending_here = max_ending_here + A[i];
            if (max_ending_here > maxsum)
                maxsum = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return maxsum;
    }

//Interviewbit Max Absolute Difference Solution - https://www.interviewbit.com/problems/max-absolute-difference/
//Problem Description : You are given an array of N integers, A1, A2 ,…, AN. Return maximum value of f(i, j) for all 1 ≤ i, j ≤ N.
//Here we have f(i, j) = |A[i] - A[j]| + |i-j|
//The 4 possible cases here are:
//Case 1: i>j and A[i] > A[j]: f(i,j) can be rewritten as: (A[i]+i) - (A[j]+j)
//Case 2: i<j and A[i] < A[j]: f(i,j) can be rewritten as: (A[j]+j) - (A[i]+i)
//Case 3: i<j and A[i] > A[j]: f(i,j) can be rewritten as: (A[i]-i) - (A[j]-j)
//Case 4: i>j and A[i] < A[j]: f(i,j) can be rewritten as: (A[j]-j) - (A[i]-i)
//The 4 noticeable things to compute here are:
//1. Maximum1 = max(A[i]+i)
//2. Minimum1 = min(A[i]+i)
//3. Maximum2 = max(A[i]-i)
//4. Minimum2 = min(A[i]-i)
// The function needs to return max(Maximum1 - Minimum1, Maximum2 - Minimum2)

    public static int maxArr(int[] A) {
        int N = A.length;
        // find max value of A[i]+i
        int aiplusimax = Integer.MIN_VALUE;
        int aiminusimax = Integer.MIN_VALUE;
        int aiplusimin = Integer.MAX_VALUE;
        int aiminusimin = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            aiplusimax = Math.max(aiplusimax, A[i] + i);
            aiminusimax = Math.max(aiminusimax, A[i] - i);
            aiplusimin = Math.min(aiplusimin, A[i] + i);
            aiminusimin = Math.min(aiminusimin, A[i] - i);
        }
        return Math.max(aiplusimax - aiplusimin, aiminusimax - aiminusimin);
    }


    // Interviewbit Plus One Solution - https://www.interviewbit.com/problems/add-one-to-number/
    //Problem Description : Given a non-negative number represented as an array of digits, add 1 to the number ( increment the number represented by the digits ).
    //The digits are stored such that the most significant digit is at the head of the list.
    //Input Format: First argument is an array of digits. Output Format: Return the array of digits after adding one.
    // Naive Solution is to convert the array to a string, then to a BigInteger, add 1 to the BigInteger, convert the BigInteger to a string, then to a char array, then to an int array
    // Better Solution is to loop through the array from the end, if the digit is less than 9, add 1 to the digit and return the array, if the digit is 9, set the digit to 0 and continue the loop
    // If the loop finishes, then all digits were 9, so create a new array with length+1, set the first digit to 1 and return the array

    public static int[] plusOne(int[] A) {
//        String strRet="";
//        for(int i : A) {
//            strRet += Integer.toString(i);
//        }
//
//        BigInteger num;
//        num = new BigInteger(strRet);
//        num = num.add(BigInteger.valueOf(1));
//        String outString = num.toString();
//        char[] ch = outString.toCharArray();
//        int [] resulta = new int[outString.length()];
//        for (int i=0; i< resulta.length ; i++) {
//            resulta[i] = Character.getNumericValue(ch[i]);
//        }
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] < 9) {

                A[i]++;
                break;
            } else if (A[i] == 9 && i != 0) {
                A[i] = 0;
                A[i - 1]++;
                if (A[i - 1] <= 9) {
                    break;
                }
            } else if (A[i] == 9 && i == 0) {
                A[i] = 10;
                break;
            } else if (A[i] == 10 && i != 0) {
                A[i] = 0;
                A[i - 1]++;
                if (i == 1 || A[i - 1] <= 9) {
                    break;
                }
            }
        }
        int[] copiedArray = new int[0];
        if (A[0] == 10) {
            A[0] = 0;
            copiedArray = new int[A.length + 1];
            System.arraycopy(A, 0, copiedArray, 1, A.length);
            copiedArray[0] = 1;
            return copiedArray;
        } else {
            int index = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] == 0) {
                    index++;

                } else break;
            }
            copiedArray = new int[A.length - index];

            System.arraycopy(A, index, copiedArray, 0, A.length - index);
            return copiedArray;
        }


    }


    public static void main(String[] args) {
        // Define a 2D array
        int[][] arr = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        // Call the spiralOrder method and print the result
        int[] arr2;
        arr2 = spiralOrder(arr);
        System.out.println(Arrays.toString(arr2));

        // Define an array
        int[] aar = {-63216, -75973, -82720, -81169, 15827, -16728, -20384, 89949, 16046, -68666, 60520, 41991, -33258, 68744, 85718, -66825, 58689, -73014, 2918, 33029, 6727, 33754, -8396, 3083, -46865, 15252, -77876, -32675, -82710, 28192, -54747};

        // Call the maxset method
        int[] aar2 = maxset(aar);
        System.out.println(Arrays.toString(aar2));

        int[] A1 = {0, 1, 1, 10000000};
        int[] B1 = {0, 1, 2, 10000000};
        System.out.println(coverPoints(A1, B1));


        int[] A2 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(A2));


        int[] A3 = {9};
        System.out.println(Arrays.toString(plusOne(A3)));

        int[] A4 = {1, 3, -1};
        System.out.println(maxArr(A4));
    }
}