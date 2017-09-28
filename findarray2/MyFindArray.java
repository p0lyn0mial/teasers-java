package findarray2;
/*
Teaser:
Implement a method that given two arrays as parameters will find the starting index where the second parameter occurs as a sub-array in the array given as the first parameter.
If given sub-array (second parameter) occurs more than once, then the method should return the starting index of the last occurrence
Your implementations should return -1 if the sub-array cannot be found.
Your implementation must implement the FindArray interface.
For extra points: implement it in an efficient way for large input arrays.
Sample Input:
[4,9,3,7,8] and [3,7] should return 2.
[1,3,5] and [1] should return 0.
[7,8,9] and [8,9,10] should return -1.
[4,9,3,7,8,3,7,1] and [3,7] should return 5.
*/
public class MyFindArray implements FindArray {

    public int findArray(int[] array, int[] subArray) {

        if (array == null || subArray == null) {
            return -1;
        }
        if (subArray.length > array.length) {
            return -1;
        }
        if (array.length == 0 || subArray.length == 0) {
            return -1;
        }

        int skip = subArray.length;

        // iterate the array backwards, each time skipping some elements
        for (int i = array.length - skip; i >= 0; i = i - skip) {
            // first pass, if there is a match on the first index,
            // check the remaining positions
            if (array[i] == subArray[0]) {
                boolean foundSolution = true;
                for (int j = 1; j < subArray.length; j++) {
                    boolean areEqual = (array[i + j] == subArray[j]);
                    if (!areEqual) { foundSolution = false; break; }
                }
                if (foundSolution) {
                    return i;
                }
                skip = subArray.length;
                continue;
            }

            // second pass, the idea is to check whether numbers from the input array
            // overlap with the numbers from the sub-array on some position
            // in that case we need to recalculate our skip.
            int newSkip = 0;
            boolean potentialSolution = false;
            int nextArrayPosition = 0;
            for (int j = 0; j < subArray.length; j++) {
                boolean areEqual = array[i + nextArrayPosition] == subArray[j];
                if (areEqual) {
                    potentialSolution = true;
                    nextArrayPosition = nextArrayPosition + 1;
                }
                if (!areEqual && potentialSolution) {
                    break;
                }
                if (!areEqual) {
                    newSkip = newSkip + 1;
                }
                // TODO: going to deep into sub-array doesn't make sense
                // TODO: figure out how far can we go
            }

            if (potentialSolution) {
                skip = newSkip;
            } else {
                skip = subArray.length;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // scenario 1
        {
            int[] input = {8, 3, 7};
            int[] output = {3, 7};
            int expectedSolution = 1;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 1 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario  2
        {
            int[] input = new int[100000];
            for (int i = 0; i < input.length; i++) {
                input[i] = i;
            }
            int[] output = {99, 100, 101, 102, 103, 104, 105};
            int expectedSolution = 99;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 1 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 3
        {
            int[] input = {1, 3, 5};
            int[] output = {1};
            int expectedSolution = 0;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 3 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 4
        {
            int[] input = {4, 9, 3, 7, 8};
            int[] output = {3, 7};
            int expectedSolution = 2;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 4 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 5
        {
            int[] input = {7, 8, 9};
            int[] output = {8, 9, 10};
            int expectedSolution = -1;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 5 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 6
        {
            int[] input = {4, 9, 3, 7, 8, 3, 7, 1};
            int[] output = {3, 7};
            int expectedSolution = 5;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 6 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 7
        {
            int[] input = {8, 3, 7, 7, 1};
            int[] output = {3, 7};
            int expectedSolution = 1;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 7 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 8
        {
            int[] input = {3, 7};
            int[] output = {3, 7};
            int expectedSolution = 0;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 8 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 9
        {
            int[] input = {3};
            int[] output = {3, 7};
            int expectedSolution = -1;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 9 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 10
        {
            int[] input = {3, 7};
            int[] output = {};
            int expectedSolution = -1;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 10 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 11
        {
            int[] input = {};
            int[] output = {};
            int expectedSolution = -1;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 11 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 12
        {
            int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] output = {5, 6, 7, 8, 9};
            int expectedSolution = 4;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 12 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 13
        {
            int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] output = {5, 6, 7, 8, 9, 10};
            int expectedSolution = 4;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 13 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 14
        {
            int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] output = {11, 12, 13, 14, 15, 16, 17};
            int expectedSolution = 10;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 14 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 15
        {
            int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] output = {13, 14, 15, 16, 17, 18, 19};
            int expectedSolution = 12;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 15 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 16
        {
            int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] output = {1, 2, 3};
            int expectedSolution = 0;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 16 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 17
        {
            int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] output = {2, 3, 4};
            int expectedSolution = 1;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 17 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
        // scenario 18
        {
            int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] output = {2, 3, 4, 5};
            int expectedSolution = 1;

            MyFindArray mfa = new MyFindArray();
            int actualSolution = mfa.findArray(input, output);
            if (actualSolution != expectedSolution) {
                System.out.format("scenario 18 failed, expected %d, got %d", expectedSolution, actualSolution);
                System.exit(-1);
            }
        }
    }
}
