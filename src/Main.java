import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {

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
    }
}