import java.util.Arrays;

class Result {

    public static void main(String[] args) {
        System.out.println(biggerIsGreater("abdc"));
    }



    public static String biggerIsGreater(String w) {
        /*
        Characters to the left of the string are more significant than those to the right. Just like numbers.
        As we move right through the string, the characters will have less and less impact on the sorting.

        We want to find a "pivot", this is the final inflection point in the string where we go from growing to
        shrinking. Because we're looking for the last of these inflections, we can start looking from the end, and
        work toward the beginning. We can stop when we find one, as that's the latest one in the string. We're going
        backwards through the string, so we will also reverse the "growing" and "shrinking". Now we will be looking for
        the first instance of an inflection from shrinking to growing as we move through the string backwards.

        If we never find a pivot, there is no solution. This means no permutation of the original string could be
        greater than the original.

        Once we find the pivot, we need to find the candidate to swap it with. Any character to the right of the pivot
        which is greater than the pivot is a candidate. The least of these candidates is the one we want. We also know
        at this point that every character to the right of the pivot should be a candidate, as we know the pivot is the
        first character we found that was lesser than the rest. So we will loop through the string some more, this time
        starting at the pivot and moving to the right, looking for the lowest character in the remaining substring.

        Once we have a pivot and our candidate, we need to swap them.

        We have now achieved a permutation of the original string which is greater than the original. The last thing to
        do is to make sure we have the least greater permutation. Of all the possible greater permutations, we want
        the least one. To do this, all we need to do now is sort the remaining substring to its lowest value. Any sort
        will do here.

        Finally just return the string we've built, it is the least greater permutation of the original.

        */

        char[] str = w.toCharArray();



        int pivotIndex = -1;
        //look backward to find the pivot
        for(int i = str.length-1; i > 0; i--) {
            if(str[i-1] < str[i]) {
                pivotIndex = i-1;
                break;
            }
        }

        //If the pivot index is unchanged, then we have no answer. The entire thing is descending left to right.
        if(pivotIndex == -1) {
            return "no answer";
        }

        //now find the candidate.
        int candidate = Integer.MAX_VALUE;
        int candidateIndex = -1;
        for(int i = pivotIndex; i < str.length; i++) {
            if(str[i] > str[pivotIndex] && str[i] < candidate) {
                candidate = str[i];
                candidateIndex = i;
            }
        }

        //do the swap
        char temp = str[pivotIndex];
        str[pivotIndex] = str[candidateIndex];
        str[candidateIndex] = temp;


        //re-order the substring
        Arrays.sort(str, pivotIndex+1, str.length);

        //return the string
        return String.copyValueOf(str);
    }



}