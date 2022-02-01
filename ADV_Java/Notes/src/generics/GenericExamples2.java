
package generics;

/**
 *
 * @author cjones
 */
public class GenericExamples2 {
    
public static class Util {
    // Generic static method
    public static <K, V> boolean pairEquals(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
               p1.getValue().equals(p2.getValue());
    }
}

public static class Pair<K, V> {

    private K key;
    private V value;

    // Generic constructor
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Generic methods
    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
    public K getKey()   { return key; }
    public V getValue() { return value; }
}

public static class Box<T> {
    private T t; 
    public void set(T t) { this.t = t; } 
    public T get() { return t; }
}

public static <T extends Comparable<T>> int countGreaterThan(T[] myArray, T elem) {
    int count = 0;
    for (T e : myArray)
        if (e.compareTo(elem) > 0)//Line that needs Comparable<T>
            ++count;
    return count;
}


public static void main(String [] args){
  Pair<Integer, String> p1 = new Pair<>(1, "apple");
  Pair<Integer, String> p2 = new Pair<>(2, "pear");
  boolean same = Util.<Integer, String>pairEquals(p1, p2);
  same = Util.pairEquals(p1, p2);//The compiler will infer the type that is needed:
  Pair<Double, String> p3 = new Pair<>(2.0,"blueberry");
  Pair<Double, String> p4 = new Pair<>(2.0,"blueberry");
  same = Util.pairEquals(p4, p3);
  //same = Util.pairEquals(p4, p1);//error -- types do not match
  Box<Double> doubleBox = new Box<>(); 
  Box rawBox = doubleBox; //Don't do this
  rawBox.set(true); // warning: unchecked invocation to set(T)
  System.out.println(rawBox.get());

  
}
    
}
