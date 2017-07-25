package common.helpers;

/**
 * Created by Joshua.McCann on 6/26/2017.
 */
public class StringHelper extends CommonHelper{

    //Notes:    this checks a string if it is null or empty returns TRUE, otherwise returns FALSE.
    public static boolean isNullOrEmpty(String s){
        return s==null || s.length()==0;
    }

    public static String compareAlphabetical(String s1, String s2){
        if (s1.charAt(0) < s2.charAt(0))
            return s1;
        else
            return s2;
    }

    public static String sortString(String s){
        char tempChar;
        char[] tempArray = s.toCharArray();
        StringBuilder returnString = new StringBuilder();

        for(int i = 0; i!=s.length()-1; i++) {
            if (i <= s.length() - 1 && tempArray[i] > tempArray[i + 1]) {
                tempChar = tempArray[i];
                tempArray[i] = tempArray[i + 1];
                tempArray[i + 1] = tempChar;
                i = -1;
            }
        }
        for(char test : tempArray) {
            returnString.append(Character.toString(test));
        }
        return returnString.toString();
    }
}
