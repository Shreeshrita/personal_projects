import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item> {
     private Node first; // top of stack (most recently added node)
     private Node last; // bottom of stack (most last added node)
     
     private int N;      // number of items
        private  class Node
   {  // nested class to define nodes
      Item item;
      Node next;
      Node previous;
   }
    public Deque()      {
        // construct an empty deque
        
       first = new Node();
//       first = null;
       last = new Node();
       last=first;
       
       N=0;


    }
    public boolean isEmpty()    {
        // is the deque empty?
        return N==0;
    }
    public int size()   {
        // return the number of items on the deque
        return N;
    }
    
    
    public void addFirst(Item item) {
        // add the item to the front
        if (item==null){
            throw new java.lang.IllegalArgumentException("Illegal item");
        }
        if (N==0)
        {
            first.item = item;
            N++;
        }
        
        else{
            
        Node oldfirst =  first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        oldfirst.previous = first;
        N++;

        
        }        
        
    }
       public void addLast(Item item)   {
       // add the item to the end
           if (item==null){
            throw new java.lang.IllegalArgumentException("Illegal item");
        }

          if (N==0)
          {
              
            last.item = item;
            N++;
          }
          
          else{
              
          Node oldlast = last;
          last = new Node();
          last.item = item;
          last.previous = oldlast;
          oldlast.next = last;

          N++;
          
          }

   }
       public Item removeFirst()   {
           // remove and return the item from the front
           if (N==0){
             throw new  java.util.NoSuchElementException("No element in array");
           }
           Item item = first.item;
           N--;
           if (N==0){first.item=null;
               last=first;

           }
           else{
               first = first.next;}
           return item;
       }
       public Item removeLast(){
           if (N==0){
             throw new  java.util.NoSuchElementException("No element in array");
           }
           Item item = last.item;
           N--;
           if (N==0){first.item=null;
               last=first;
           }
           else{
           last = last.previous;
           }
           return item;
           // remove and return the item from the end
       }
       public Iterator<Item> iterator(){
           // return an iterator over items in order from front to end
             return new ListIterator();  
       }
       private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { if (current!=null){return current.item != null;  }
            else {return false;}}
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

       public static void main(String[] args){
//            unit testing (optional)
           
//        Deque<String> stack = new Deque<String>();
//        StdOut.println("length"+stack.size());
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            StdOut.println("itemread"+item);
//            stack.addLast(item);
//            StdOut.println("printing stack");
//            for(String s : stack){
//             
//            StdOut.println("there"+s);
//            }
//            
//            StdOut.println("removeFirst"+stack.removeFirst()+" "+stack.size());
//            StdOut.println("length"+stack.size());
//            StdOut.println("printing stack");
//            for(String s : stack){
//             
//            StdOut.println("there"+s);
//            }
//            
//            
//        }
//        StdOut.println("HEre");
//        while (!stack.isEmpty())    {
//            StdOut.println("removelast"+stack.removeFirst()+stack.size());
//             }
//        
    }
       
}