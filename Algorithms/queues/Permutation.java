import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Permutation{

public static void main(String[] args) {  
    
int k =  Integer.parseInt(args[0]);


RandomizedQueue<String> stack = new RandomizedQueue<String>();
while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            stack.enqueue(item);
}
int i=0;
while (i<k)    {
            StdOut.println(stack.dequeue());
            i++;
             }
}
}

