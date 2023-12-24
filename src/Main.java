import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;





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

//Interviewbit Max Non-Negative SubArray Solution - https://www.interviewbit.com/problems/max-non-negative-subarray/
//Problem Description : Given an array of integers, A of length N, find out the maximum sum sub-array of non-negative numbers from A.
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
                for (int value : set) {
                    setsum += value;
                }
                // Store the subarray and its sum in a map, and add the map to the list
                int setStartIndex = i;
                int[] finalset = new int[set.length + 2];
                finalset[0] = set.length;
                finalset[1] = setStartIndex;
                System.arraycopy(set, 0, finalset, 2, set.length);
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
//You are given a sequence of points and the order in which you need to cover the points. Give the minimum number of steps in which you can achieve it. You start from the first point.

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
            if (max_ending_here > maxsum) maxsum = max_ending_here;
            if (max_ending_here < 0) max_ending_here = 0;
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
        int[] copiedArray;
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

    public static int solve(int[] A, int B) {
        int lightcount = 0;
        int zeroCount = 0;
        int zerofail = 2;
        int N = A.length;
        for (int i = 0; i < N; i++) {
            if (A[i] == 0) {
                zeroCount++;
                if (zeroCount >= B) {
                    zerofail = -1;
                }
            } else {
                if (i >= N - (2 * B - 1)) {
                    zeroCount = 0;
                } else zeroCount = -(B - 1);
            }
        }

        for (int i = 0; i <= N; i++) {
            int lo = Math.max(0, (i - 1));
            int hi = Math.min(N - 1, i + (B - 1));
            for (int j = hi; j >= lo; j--) {
                if (A[j] == 1) {
                    lightcount++;
                    i = j + B - 1;
                    break;
                }
            }

        }

        if (zerofail == -1) {
            return zerofail;
        } else return Math.max(lightcount, 1);
    }

    public static int solve1(String[] A) {


        int Theight = A[0].length();
        int Tbase = A.length;
        double rBaseMin = 0, rBaseMax = 0, gBaseMin = 0, gBaseMax = 0, bBaseMin = 0, bBaseMax = 0;
        double rHeightMax = 0, gHeightMax = 0, bHeightMax = 0;

        for (int i = 0; i < Tbase; i++) {
            if (A[i].charAt(0) == 'r') {
                rBaseMin = i;
                break;
            }
        }
        for (int i = Tbase - 1; i > 0; i--) {
            if (A[i].charAt(0) == 'r') {
                rBaseMax = i;
                break;
            }
        }

        outer1:
        for (int j = Theight - 1; j > 0; j--) {
            for (int k = Tbase - 1; k > 1; k--) {
                if (A[k].charAt(j) == 'r') {
                    rHeightMax = j;
                    break outer1;
                }
            }
        }

        for (int i = 0; i < Tbase; i++) {
            if (A[i].charAt(0) == 'g') {
                gBaseMin = i;
                break;
            }
        }
        for (int i = Tbase - 1; i > 0; i--) {
            if (A[i].charAt(0) == 'g') {
                gBaseMax = i;
                break;
            }
        }

        outer2:
        for (int j = Theight - 1; j > 0; j--) {
            for (int k = Tbase - 1; k > 1; k--) {
                if (A[k].charAt(j) == 'g') {
                    gHeightMax = j;
                    break outer2;
                }
            }
        }

        for (int i = 0; i < Tbase; i++) {
            if (A[i].charAt(0) == 'b') {
                bBaseMin = i;
                break;
            }
        }
        for (int i = Tbase - 1; i > 0; i--) {
            if (A[i].charAt(0) == 'b') {
                bBaseMax = i;
                break;
            }
        }

        outer3:
        for (int j = Theight - 1; j > 0; j--) {
            for (int k = Tbase - 1; k > 1; k--) {
                if (A[k].charAt(j) == 'b') {
                    bHeightMax = j;
                    break outer3;
                }
            }
        }

        double areabgr = Math.max((gBaseMax - bBaseMin + 1), (bBaseMax - gBaseMin + 1)) * (rHeightMax + 1) / 2;
        double areargb = Math.max((rBaseMax - gBaseMin + 1), (gBaseMax - rBaseMin + 1)) * (bHeightMax + 1) / 2;
        double arearbg = Math.max((bBaseMax - rBaseMin + 1), (rBaseMax - bBaseMin + 1)) * (gHeightMax + 1) / 2;

        double area = Math.max(areabgr, areargb);
        double area2 = Math.max(area, arearbg);
        return (int) Math.ceil(area2);

    }

    public static int solve2(int A, int[] B) {
        int ways = 0, counts = 0;
        int indexfwd = 0, indexbwd = 0;
        long sumarray = 0, midsum = 0;
        long splitsum = 0;
        boolean allzero = true, onethird = false;
        for (int i = 0; i < A; i++) {
            sumarray += B[i];
        }

        for (int i = 0; i < A; i++) {
            midsum += B[i];
            if (sumarray == 0 && midsum != 0) {
                allzero = false;
                break;
            }
        }


        if (sumarray % 3 != 0) {
            return 0;   // no way to split the array into 3 equal parts
        } else {
            midsum = 0;
            splitsum = (sumarray / 3);
            for (int i = 0; i < A; i++) {
                midsum += B[i];
                if (midsum == splitsum) onethird = true;
                if ((midsum) == (splitsum * 2) && onethird) {
                    indexfwd = i;
                    break;
                }
            }
            midsum = 0;
            for (int i = A - 1; i > 0; i--) {
                midsum += B[i];
                if ((midsum) == splitsum) {
                    indexbwd = i;
                    break;
                }
            }
            if (indexfwd == indexbwd) {
                ways = 1;
            } else if (splitsum == 0 && B[0] == 0 && B[A - 1] == 0) {
                ways = 1;
            } else ways = 2;

        }
        if (allzero && sumarray == 0) {
            for (int i = 0; i < A; i++) {
                for (int j = i + 1; j < A - 1; j++) {
                    counts++;
                }
            }
            ways = counts;
        }
        if (sumarray != 0 && (indexfwd == 0 || indexbwd == 0)) ways = 0;

        return ways;
    }


    public static int solve5(int[] A) {
        int N = A.length;
        if (N < 3) return 0;
        int sum = 0, retsum = 0;
        int index1 = 0, index2 = 0, index3 = 0;
        int[] B = new int [N+1]; // create a new array to store the max values of the subarrays to the right from N downwords
        int[] C = new int [N]; // create a new array to store the max values of the subarrays to the left from 0 upwords
        B[N] = A[N-1];
        B[N-1] = A[N-1];
        C[0] = A[0];

        for (int i = 1; i < N ; i++) {
            C[i] = Math.max(A[i], C[i-1]);
        }


        int[][] sortedlist = new int[N][2];

        for (int i = N-2; i > 0; i--) {
//            sortedlist[i][1] = i;
//            sortedlist[i][0] = A[i];
            B[i] = Math.max(A[i], B[i+1]);

            if (A[i] < B[i+1] && A[i] > C[i-1]) {
                retsum = Math.max(retsum, ((B[i+1] + C[i-1]) + A[i]));
            } else if (A[i] < B[i+1] && A[i] < C[i-1]) {
                for (int j = i - 1; j >= 0; j--) {

                    if (A[j] < A[i]) {
                        retsum = Math.max(retsum, (B[i+1] + A[i] + A[j]));
                    }
                }
            }


        }

//        Arrays.sort(sortedlist, Comparator.comparingInt(a -> a[0]));



// working soultion that fails on time complexity  :-(

//        for (int i = N-1; i > 1; i--) {
//
//            sum = sortedlist[i][0];
//            index1 = sortedlist[i][1];
//            for (int j = i - 1; j > 0; j--) {
//                int midsum = 0;
//                index2 = sortedlist[j][1];
//                if (index2 < index1 && (sum + sortedlist[j][0]) > midsum && sum > sortedlist[j][0]) {
//                    midsum = sum + sortedlist[j][0];
//                    for (int k = j - 1; k >= 0; k--) {
//                        index3 = sortedlist[k][1];
//                        if (index3 < index2 && (midsum + sortedlist[k][0]) > retsum && sortedlist[j][0] > sortedlist[k][0] ) {
//                            retsum = midsum + sortedlist[k][0];
//                            System.out.println("retsum = " + retsum + " index1 = " + index1 + " index1 value = "+ sortedlist[i][0]+ " index2 = " + index2 + " index2 value = "+ sortedlist[j][0] + " index3 = " + index3 + " index3 value = "+ sortedlist[k][0]+" Midsum is "+ midsum +  " Total = " + (sortedlist[i][0] + sortedlist[j][0] + sortedlist[k][0]));
//                        }
//
//                    }
//                }
//                }
//            }

//  Trying new soultion - choose middle number and check left and right. steel not good enough :-(
//        for (int i = N-2; i > 0; i--) {
//
//
//            int midsum=0;
//            sum = sortedlist[i][0];
//            index1 = sortedlist[i][1];
//            for (int j = i - 1; j >= 0; j--) {
//                index2 = sortedlist[j][1];
//                if (index2 < index1 && (sum + sortedlist[j][0]) > midsum && sum > sortedlist[j][0]) {
//                    midsum = sum + sortedlist[j][0];
//                }
//            }
//            for (int k = i + 1; k < N; k++) {
//                index3 = sortedlist[k][1];
//                if (index3 > index1 && (midsum + sortedlist[k][0]) > retsum && sortedlist[i][0] < sortedlist[k][0]) {
//                    retsum = midsum + sortedlist[k][0];
//                }
//            }
//            System.out.println("retsum = " + retsum + " index1 = " + index1 +  " index2 = " + index2 +  " index3 = " + index3 +" Midsum is "+ midsum );



//        }


        return retsum;
    }

    // Interviewbit find min max - https://www.interviewbit.com/problems/max-min-05542f2f-69aa-4253-9cc7-84eb7bf739c4/

    public static int solve9(int[] A) {
        int min;
        int max;
        Arrays.sort(A);
        min = A[0];
        max = A[A.length-1];
        return min+max;
    }


    //  Interviewbit find the number of occurences of each number in array; https://www.interviewbit.com/problems/occurence-of-each-number/
    public static int[] findOccurences(int[] A) {
        Arrays.sort(A);
        HashMap<Integer, Integer> numofoccur = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            if (numofoccur.containsKey(A[i])) {
                numofoccur.put(A[i], numofoccur.get(A[i]) + 1);
            } else {
                numofoccur.put(A[i], 1);
            }
        }

        List<Integer> values = new ArrayList<>(numofoccur.keySet());
        Collections.sort(values);

        int[] B = new int[values.size()];
        int index = 0;
        for (Integer entry : values) {
            B[index] = numofoccur.get(entry);
            index++;
        }
        return B;
    }

    public static void main(String[] args) {
        // Define a 2D array
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

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

        int[] A5 = {0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0};
        System.out.println(solve(A5, 12));

        String[] A6 = {"bgrrrbbgrrbggbrrggrggrrbrgbgbrbggbgrrbbrggrgbgrbbggbrgggrbrbrbgbbbbbbr", "ggrgggrgbgrbrbgrrbgbbgbrrrrgbrrrrggrrrrbbggrrgggbgrrbrbgbbbgbrbbbbgrrg", "rrrbbbggbbbrbgbbrrgbbrrgrbgbbrgggrrbgrggggrgbbrbbggrggbggbrbbbgrbbbrrb", "rrrrbrrgrbrbbbgggrrrrbgrggrbrbrbgrrrgbrrbbbgbbbbgbrgbggbbbbgrgrbrrgbrr", "bbgrgggrrggbgbgbgbrrbgrbrggrggggggbggrbrrgrbrggggggbrbrgbgrrbrggrgrrbg", "rgrbrrgrbrbrrgbgrgbbbrrbbbrbgrrrrrggbrgrbrgrrrggbbrbbrgrgrbbggbbbbbbrg", "brrggrbbggbrrrrggrggrgrrbgbgrbbrgrbrrbgrrrbbrbrgrrgrbgrggrrbrgggrbrbgb", "rrgrgbbbbbbbggbggrrgbgggbrggbbgbbgrgggrgrrbrrggrbbrgbbrggrrgrbrrggbbgg", "rbgbgrbbgbgggbgbrbgggbbbgrbrgbrggbbrgbbrbbbrbgbgrbbgbrbgbgrgbrrrgrrrbg", "rbggbgbrggbgbgrrbbrbgbrbgrgggrrrrggbbbrgrrbgggrrbgrgbrgrrbrrbgbrrrbrgb", "rgrrrrgrbbrbrbggrrgbbbbgbbrbrgbgrggggrrbggrrrbgrgrgbbbgrgbbgbrbrbrbbrg", "rrgbgbggrbbbggrbbgbgrbbbgbgbgbrrbgrbbbgrrrbbgrrgggbbrbgrgbbbrbbgbbgbgr", "bbbggbbrbgggbrrrgrbgbrbrgrrrbbbgbgrgrggbbrbgbbbrrbrbgrggrbrbbgrgrgbrgb", "ggrrrrrgggrggbrrrrrrbbrggggbbgggbrggrbrrbbggrrrrbrbgrrbbrgrgrrrbrgrrbg", "gbrrgrrgbrggbrgrrrgbrrbgbbggbgrbbrgrbrrbbgbbgbgbbggrrggbbbgrrbggbbbggr", "ggbrrggrgbrbgggggrrrrbrbbrbgrggrrbbrgrrgbggbrgbgrbgbbbgrrrrrrgrbgggbrb", "grggrrgbggrggggrgbbggrrggbgrbrrrbbrrggbbgbrgbbgggbbbbbrrrbrrrgbggrrbrg", "rbgbbrgrbgrggbgbbrrrrrrbbrbbgrrbbbgggggrrgrgbbgbbbgrbbbbrbrgrbrbrrggrr", "gbrgbbbgbrbrbbbggrrgbbgrrbrrbrbrrbbrggrgggrbgrrggrggrbrbbbbrgbbbbbgrrg", "bgbggrrbgbrgbgbrrgrbgggrgrbbgrbrbrgrgbrrgbbgbrrbrbrrbbgbrrgrrrrbbrrbrg", "ggbbgrgrggbrgrrbrgbgbggrbrggbrrrbgbgbrbbgrgbbgrrrggrgbbrggrgrbggrrgrgr", "rrbbgrbrrbgrgrrbggrrgrrgrrgrbrggbrbbbbrgrrrbbrbgbbbrbrrbrrbrrggrgggrgb", "rbrrgrgrbbbgrrbggrrggrgggbrbrbrbgbrgrggrgggrrgggbrbrrbrrgbggrgbgrbrrrb", "rbbbrrgbrbrggrbgrgbbrrgrgbrgrgbrbrbrbgrbrbrgbrbgggbbbbrrgbgbrrrrgggrbb", "rrggbgbrrrrrrgrgbgggrbggbrrrbrbggrgrrbrrbrrgrbbrbrbgbbgggbggbrgggrrrbb", "rrbbbbrrbbbbrrgrbrbrggggggrgrrrbgrrrrbrrbbrrgbgbbrrbrbgbbbgrbgrgbggggb", "rbbbbgrrbbgrbgbggrggrggrbbrbrgbgrgggrrggbgrbrbrgggrrrbgrgrgrrbbbrgrbrb", "bbbbrbbrrbrbbgbrbgrgrgggbbgrrgggggrgrgrrrgggggrrrbrbgbgrbrbrbggbrbgbrg", "rrgrrbrrbbgbrgbrggrrbggbbbbggrrrrbbgrrgbrbrrbgggbrrgrrrgbrrgggbggrbrgg", "grggbgrggbgrrrgrggggrbrgrgbbggrggbrbggrggbggbbrggrbbrrrbrrbbrrrgbgrrrg", "bbbbrgbrggbgbrggbgbgrbbrgbbggbgbrgrgggggrbgbbggrggrbggggrbggrbrbrbbbgb", "grrbrgbbbgrrggrgbrggbrrrbrgbgbbggggrrbrbbrrbbbgbgbgbbrbgrbrbbrggbggbbb", "gbgrgrbbrbrbrrgbrbbbrbgrgbrbgrrrgrrgbrggrbbrrgbbbggggrbggrbbgbgrrgrgrg", "grrbrbgbgrrgrgrggrrgrbrggbbrbbgrbggggbggrggrbbrgbbgrgrbrggrrrgrbbrbrrg", "ggggrgbbbbbggbrbrgbrrbrgggggbbgrrgrbrgggbrgbbbgrrgrggbgbrrggbgbrgbrrrb", "gbgrbgrbbrrgbrgggrrbgrgggrgrbrbbbbrrrbrbgrgbbbrbbbbrrgbgrgbgbbrgbrrgbg", "bgrbggrrggbgggrgrbbgbrbrrgbgggggggbbbgbbrbrrrgrbrgbbbrrgrbggrbbggrrbrg", "bgrrrgbgrbrggbrrbgrggrgrbbbbbrbbbrgrbrrbrgbbgggrrgrbrrrbbrbbbbgbbbrrrg", "brgggbgrgrgrrgbrrbrggggrrbrrbbrgbbgrggbggbrbggrbgrgrbbgrrrgrgrbgbgrggr", "rrbrgrrrrrbggrbgrbrrrbbggrbgbgbrgbgrggbggrrrbggrgbbbrrbbrbbrrgrrbrgbrb", "rggggrbrgbrrgrgrrbbgbbgrggrgrbbbgrgbrbbrbbgrrgrbrbggbgbbgggrbgbrbbgrrg", "gbbbrgggbrrrbbgbgbbgrrgggbrrgrrggrrgggrggrgbbrrrrrrgrrrggbbbrbrgrgbgbg", "grrbbgbbbbgbbgbrbgbbrgrgbrrrbrrrbrbggbrbgrrrrrrrbbrbgrrbrggrbbgggbrrrb", "brbbggbgrgbrgrggrrgrrrrrrrrbgrbrrrgrbrrbbbgrbggrgbrrggrbbbbrgggbrbrbrg", "rbgbrrrgrbbgbgrbbgbbbbrgbrbrbbrgrbbrbrbgbgggrbrbgbbgrgrgbbgrrgbbbgrggb", "rrgbggrgrrgbggrggbgbrrgggrrggrbrgggggbbbrgbbbrgbrrggrggggrbbrgbgggbbrb", "bbrrbbbrbbrgbrbrrggrbrrrrbbbggrgrbbrbggrggggbbbrbbbrbrbggrbggrbbbggbrb", "gbbgbbbbggbrggbbgbrggbbbggbgbggggbrbbrrgrgbrrgbgbbggbggrrrrbbggrgbbrrg", "gbrrbrbggrrrbbrbrbgbrrrrbgrgggrbbgbbrgrbrrggrgbgrgggbrbbbgbbbgrbgbbbrr", "grrggbrrgrbgrrgrgrbrgrgrgbbrbbgrrbrgggggggbrggbrgbbbrggggggrbgrrgrrbgr", "brbrrgrgrbggbbrggbgrgggrgbgbbggrbrrbbrrrgrggbbrbrrggbgrbrrbrgbbrggrrrr", "rrgrbbbbbggbggrrggggrggrgbbbbbggrbrbrrrrrrrbgbrrgbrbgbrrggbbrbrrgrgrrr", "brgrgbbbbrrbrgrbbbbrbbrbbrgbrbgggbbrbgbrbgggbrbgggrrbrrggrrrgbrgggbggr", "brgggbbbrbbrrbrggbrbrggbgrbbgbbgbbrrrrbrrgbrbggrrbbggrrbgbggbgrbrgbgbg", "gbrrgbbgrgrgbbggrrbrbbgbgbggggbggrrgbgrgbbgrgbggbbrbggggrbrrrbrbgrgrgr", "rggrgbgbgggbbrrbgbrggbrgbrgrgrgrbbbggrbrbggrrbggggrbrbrrbbrbbbbrbrgrbg", "bggbbbrggrgbgggrrrrgrgbbbgbrggggbrrrrbrbgbrbgbggbbgbgrgbrgrbbbbrgbrrrr", "gbrrbggrbgbbbbbgrrbbggbbbrbgrbgrrrrbgbgbbggrgbbgbbrrggbbgrgrrggbrrbrgb", "rrbggbgbbgrbggbrggrrrbgrgrgbgrgggbrbrbrbgrrggrrgrrggbrgbrgrbbbbrbrbrgg", "gbbbbgbrgbbrrgrgggbbggbrbrrgrgrrgrbbgggrrbgrggrgbbrgrggbgrbbbgbrrrbbrg", "grbgbbrrrgbbrgrgrbgbbbgrrbrbbgbbbbbgggbbbrrggrgrbbbgbbgrrbgbbrrgrrbbgg", "bgrggggggbbrgbrrrrbgrrbrgbbgrgrgrrrrgggrbbrrrgrbgrrrgbggrrgbggrbbbrrgr", "rggbrggggbgbrbrggrrgbrbbbbrbggbbbrbbgggrrbrrbrrbrrgbgbggbggrgggrgbrbgg", "brrbrgggbrgbrrrggbrbrrbrgggrrgrrbgbrrrrrrbggrgrgrrrrbgrggrbbrgbrgbgbrb", "grggbbbgbrrrrggrrbgrrgrgbbrbgrggggrbggrbgggrrrrrbggrbbrbgggbggrrrgrrbg", "rrgrgrggbbbbgbbgrrggrrgrrgrgbbgrbgggbrgrbgrgggggrbbggbrbbgbbrbbgrrrggr", "ggbgbbgbbbbgrbbrggbbggrggggbbrrbrgbrbbbgbrbbbbrgrbbrrgrrbbbgrrrrggrgrr", "bgrgggbbggrbgrrgrrgbbbgbrrbrrrgrgbrgrgrrrgbgbrbrgrbrgbgggrrggrrbbgrgbb", "rgbgggbgrrrgbrbbbgbrgbbgbgbggrggrgbrgbbbggbrbrrgrgbbgggbgrrrgbrbrgggbb", "gbggbgbrrrbgbrrggrbbbbrbgbgbgrggbrrrbbrbggrrbrbrbbrgbbgrbrrbrbbgbrrrrb", "ggrgbrbbrbrbbbrbgbrbbggbbggrrrrrbbggbrgbgrgggbggbgbgbgbrrbrbbgbbbrgrbb", "bbbbrgrrbrrgbggbrrggbgrrrrgrrbggbbbrgbbgrrbgrrrgbbrgrbrbgbbbbbrrbbbggr", "rggggbgrrbrgrbggbbbgbbbbbrbbgrgbggbrrgbgrrgrbggrbggbbgrbbbbbbbbrgrgbrb", "rbrbrbbgrgbgggrbgrrbggrgbggrrrrbbbrbrbrrgbbbbggbrbbbgrbbggbgbgbggbbrrr", "rrbrbbbgggbggrrbgrgbrrbrggggrrrbrrggbrgrgbrgbgrbbbbrgbgggbrrgggrrgbgbg", "rrgrgbrbbbbbbggrgrrgrbbrggbgbbrrbbbbbgrgrgrrbrrrbrrgrbbbrggbrgbgrbgrrr", "gbgbgbbggrgggrbrrrbgrrbggbgbggbgbbgbbbbbbbrrrbgrbrbbgbggbrrgggrrbrbrbb", "bbrbbrggrbbgbrbbbbrbgbgrggbbrggbbgbbgbrrrrrbgrrbrgbbbrbbbgbrgrbrgbrggb", "gggrrggrbrgrbbgrrgrbrgbggbgbgrgbrrbggrgbgrbbrggbgbrgbrgrrbgrgrrrbggrbb", "bgrggrgbrrgbrbbbbbrgbbgggrrrbbrrrggrbbggrgrbgrrgrgrggbrbbgrgrbgrgbrrgg", "rbbbrbggbrrrggrrrgrbgrgrbggbggbrrgbbbbbbrbbrrgbgbbrbrrgrrrbgrrbrrbgrbr", "grgbbrggrgbrggbbbgbrbbbbgbggbrbrgrbrgrbgbbgrbbgrrggrrgrgbgrrrrgbrbbrrg", "bbggrbggrrrggrgrrgbbbgbgbrgbrggbggbbbbrrbgrgrbggggrgrggrbbgrbbbbbrgrgb", "rrgrrrgbrrgrggbgbrbrrbbrbrrrbrrggbrrgbgrrrrbrbgrrbbbbbbggrbrgbgrgrrrbg", "bgggggbbbbgggbbrrrgrgggrggbrrbbrgrgbgbrgggrbrrggbbrrbbgbbrrgrrggrrgrrg", "bbrbggbgrrggbbrbrgrrgrrbbgbrrrbrbrrrbgbrbgrrgggggrrrrbbggggbgbbbrbrbrr", "brbgrrrgbbgbgbgbggrrggbgrbrgrbbbgrrrrggbrrrrbgbbbgrrrrrrrgrbrrbrrrgbrg"};
//        for (String s:A6){
//            System.out.println(s);
//
//        }
//        System.out.println(A6.length);
//        System.out.println(A6[0].length());
        System.out.println(solve1(A6));

        int[] B7 = {2, 5, -2, 2, -3, -2, 3, 5, -5, -2};
        int A7 = 10;
        System.out.println(solve2(A7, B7));

//      int[] A8 = {31844, 16711, 14509, 26490, 9859, 10185, 18122, 10107, 6587, 23988, 31372, 32491, 24973, 10519, 2074, 32354, 1282, 2663, 27997, 28214, 9452, 29116, 8805, 19786, 20334, 21565, 10505, 12838, 11454, 13785, 16334, 16973, 26922, 31545, 15371, 13258, 89, 6130, 20298, 3341, 19924, 27902, 15344, 5877, 1732, 27501, 15649, 13614, 254, 20312, 9694, 6333, 27452, 1815, 31600, 217, 23213, 6835, 3850, 32253, 20913, 10142, 23210, 27451, 9181, 17570, 9536, 4563, 23366, 25112, 12818, 11012, 764, 31322, 32607, 13346, 31444, 9851, 22594, 18665, 14443, 27445, 15834, 30737, 6503, 21026, 27560, 17954, 11008, 9007, 31544, 21130, 2752, 21071, 27396, 21229, 17542, 30060, 3221, 22716, 20945, 22179, 7659, 5385, 7739, 30384, 4639, 3738, 13636, 5031, 19783, 32316, 32081, 2022, 22426, 17753, 9205, 5447, 19413, 13005, 8046, 28826, 1245, 2550, 26995, 8530, 20133, 10, 22317, 2430, 7762, 9134, 12931, 10747, 5533, 25284, 30874, 30440, 21881, 8836, 24988, 20571, 25590, 7630, 5561, 29522, 10598, 32190, 13164, 30445, 18622, 24706, 2816, 10714, 22335, 17000, 13138, 25616, 14165, 32059, 23512, 8384, 6847, 20944, 32738, 29832, 28656, 12252, 24352, 13291, 16053, 12296, 24421, 24062, 14361, 29768, 15274, 8035, 32134, 25192, 9302, 379, 25388, 27880, 13501, 6616, 12710, 24073, 25062, 19953, 10971, 22824, 18065, 32538, 22051, 1611, 28792, 12979, 22444, 7162, 4822, 2719, 22299, 15184, 16934, 8696, 14710, 21073, 7469, 8704, 12865, 30846, 1850, 9051, 28647, 31143, 22445, 12156, 26460, 769, 8886, 30302, 769, 9848, 32607, 3188, 14536, 10310, 28365, 17634, 19352, 19141, 18626, 31725, 15347, 3201, 20839, 26032, 11222, 3218, 23625, 13585, 14816, 2345, 8353, 12020, 6071, 11368, 8920, 32175, 24488, 7031, 22346, 22717, 28045, 31700, 25163, 21686, 16116, 25322, 6503, 28542, 7805, 25574, 10734, 11453, 7812, 12230, 2225, 22160, 9984, 31666, 3846, 13951, 6827, 8592, 26898, 23214, 22372, 11987, 14663, 15191, 5585, 21907, 5419, 21623, 27720, 29667, 16219, 1992, 30960, 9567, 17104, 23280, 30499, 4868, 21346, 26104, 17431, 30021, 6026, 1535, 5822, 31834, 16236, 10288, 28394, 4828, 3349, 16625, 32693, 11919, 12801, 17245, 6432, 9087, 9696, 11509, 21754, 11598, 23193, 31122, 2820, 8908, 29072, 5023, 20562, 1992, 22053, 5252, 1524, 28966, 24957, 26981, 15062, 28466, 27524, 12301, 25829, 29236, 27954, 24613, 13683, 23877, 6158, 5290, 3293, 15200, 1777, 2462, 20843, 19132, 27661, 8701, 11639, 21081, 24621, 2546, 30930, 30169, 14142, 18763, 20835, 31634, 32271, 23083, 10001, 14561, 1365, 6142, 16100, 6337, 7666, 6834, 29030, 28828, 27255, 21029, 10320, 5985, 27111, 30169, 24418, 29398, 8126, 7407, 11411, 28464, 32049, 4691, 4970, 5345, 28600, 14644, 10156, 12465, 14538, 5559, 17207, 16912, 29415, 19948, 21294, 9130, 26588, 14081, 86, 8765, 7235, 17077, 14016, 30119, 13385, 24848, 20649, 20548, 5556, 10833, 26782, 30838, 27428, 25897, 5860, 8325, 27800, 15654, 7702, 28842, 4405, 15730, 19216, 30566, 3651, 25533, 3084, 29720, 18297, 24440, 779, 12239, 5242, 20804, 6007, 22621, 32671, 7850, 19206, 4525, 25079, 11788, 21985, 12630, 140, 7899, 19974, 22639, 3816, 21003, 24042, 24486, 15064, 22212, 20067, 18828, 9584, 3931, 26566, 12083, 27983, 8832, 28236, 23291, 11813, 3257, 15803, 1449, 6514, 26127, 18498, 27065, 3216  };
        int[] A8 = {32592, 18763, 1656, 17411, 6360, 27625, 20538, 21549, 6484, 27596};

        System.out.println(solve5(A8));

        int[] A9 = {56, 35, 25, 32, 41, 50, 7, 52, 34, 41, 72, 12, 93, 50, 19, 94, 13, 19 };
        System.out.println(Arrays.toString(findOccurences(A9)));
    }
}