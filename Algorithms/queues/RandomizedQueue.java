import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class RandomizedQueue<Item> implements Iterable<Item> {
    
private Item[] a;  // stack items
private Item[] newA;
   private int N;                          // number of items

    public RandomizedQueue()    {
        // construct an empty randomized queue
        a= (Item[]) new Object[1];
        N=0;
}
    public boolean isEmpty()    {
        // is the randomized queue empty?
        return N==0;}
    
    public int size()   {
        return N;
// return the number of items on the randomized queue
    }
    
    private void resize(int max)
   {  // Move stack to a new array of size max.
      Item[] temp = (Item[]) new Object[max];
      for (int i = 0; i < N; i++)
         temp[i] = a[i];
      a = temp;
   }

    public void enqueue(Item item)     {      // add the item
        if (item==null){
            throw new java.lang.IllegalArgumentException("Illegal item");
        }
        if (N == a.length) resize(2*a.length);

        a[N++]=item;
    }
    public Item dequeue()       {             // remove and return a random item
        if (N==0){
             throw new  java.util.NoSuchElementException("No element in array");
           }
        int randItem = StdRandom.uniform(N);
        Item item = a[randItem];
        a[randItem] = a[--N];
        a[N]=null;
        if (N > 0 && N == a.length/4) resize(a.length/2);

        return item;
    
    }
    public Item sample()       {              // return a random item (but do not remove it)
        if (N==0){
             throw new  java.util.NoSuchElementException("No element in array");
           }
    int randItem = StdRandom.uniform(N);
        return a[randItem];
    }
public Iterator<Item> iterator(){
           // return an iterator over items in order from front to end
          newA= (Item[]) new Object[N];
                for (int i = 0; i < N; i++)
         newA[i] = a[i];


                StdRandom.shuffle(newA);
             return new ListIterator();  
       }
       private class ListIterator implements Iterator<Item> {  // Support LIFO iteration.
      private int i = N;


      public boolean hasNext() {  return i > 0;   }
      public    Item next()    {  if (i==0) throw new java.util.NoSuchElementException();
          return newA[--i];  }
      public    void remove()  {  throw new UnsupportedOperationException(); }
   }



public static void main(String[] args) {  // unit testing (optional)
//RandomizedQueue<String> stack = new RandomizedQueue<String>();
//        StdOut.println("length"+stack.size());
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            StdOut.println("itemread"+item);
//            stack.enqueue(item);
//            StdOut.println("length"+stack.size());
//            StdOut.println("printing stack");
//            for(String s : stack){
//            StdOut.println(s);
//            }
//            
//            
//        }
//        StdOut.println("HEre");
//        while (!stack.isEmpty())    {
//            StdOut.println("removelast"+stack.dequeue()+stack.size());
//             }
}
}
