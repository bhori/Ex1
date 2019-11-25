package myMath;
import java.util.*;
/**
 * This class represents a set of simple examples for using java Data Structures
 */
public class CollectionTests {
   // public static MyRandom _rand = new MyRandom();
    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * A simple example of using Collections
     */
    public static void test1() {
        List<String> l1 = new ArrayList<String>();
        int size = 10;
        for(int i=0;i<size;i++) {
            String s = ""+Math.random();
            s = s.substring(2,5);
            l1.add("s_"+s);
        }
        printList(l1);
        Collections.shuffle(l1);
        printList(l1);
        Collections.sort(l1);
        printList(l1);
     }
     public static void printList(List l) {
        Iterator itr = l.iterator();
         System.out.println();
        while(itr.hasNext()) {
            System.out.print(itr.next()+",");
        }
     }

    /**
     * A simple example for using HashMap<K>,<V>
     */
    public static void test2() {
        System.out.println();
        String[] arr = {"one", "two", "three"};
        HashMap<Integer, String> map = new  HashMap<Integer, String>();
        for(int i=0;i<arr.length;i++) {
            map.put(i, arr[i]);
        }
        String a = map.get(2);
        System.out.print("in the map[2]="+a);
        Set<Integer> keys = map.keySet();
        Iterator<Integer> iter = keys.iterator();
        System.out.println();
        while(iter.hasNext()) {
            int key = iter.next();
            String v = map.get(key);
            System.out.print("["+key+","+v+"], ");
        }
    }
}
