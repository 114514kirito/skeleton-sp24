import java.util.*;
import java.util.List;
import java.util.ArrayList;


public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum=0;
        for(int i:L)
        {
            sum=sum+i;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer>even =new ArrayList<>();
        for(int i:L){
            if(i%2==0)even.add(i);
        }
        return even;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // 1. 防御性编程：处理输入为 null 的情况
        if (L1 == null || L2 == null) {
            return new ArrayList<>();
        }

        List<Integer> L3 = new ArrayList<>();

        // 2. 使用 Integer 而不是 int，避免自动拆箱导致的空指针异常
        for (Integer num : L1) {
            // 3. 检查 num 是否为 null (如果列表中允许 null)
            if (num == null) {
                continue;
            }
            // 逻辑正确：L2包含它 且 L3还没加过它（去重）
            if (L2.contains(num) && !L3.contains(num)) {
                L3.add(num);
            }
        }
        return L3;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        if(words==null)
        {
            return 0;
        }
        int count=0;
        for (String i : words) {
            if (i==null)continue;
            for (int j=0;j<i.length();j++) {
                if (i.charAt(j)==c) {
                    count++;
                }
            }
        }
        return count;
    }

}
