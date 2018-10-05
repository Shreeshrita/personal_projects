import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;


public class BruteCollinearPoints {


    private    Point[] min_ls = new Point[1];
    private    Point[] max_ls = new Point[1];

        private int numofseg=0;

    public BruteCollinearPoints(Point[] points)    {
         Arrays.sort(points, Point::compareTo);
        int N = points.length;
        for (int i=0;i<N-1;i++){
         if (points[i]==null || points[N-1]==null || points[i].slopeTo(points[i+1])==Double.NEGATIVE_INFINITY) 
            throw new IllegalArgumentException("argument is null or same");
        }

    

                
                Point min;
                Point max;
                
        for (int i=0;i<N;i++){
            
//            StdOut.println("1 i "+points[i]);

            for (int j=0;j<N;j++){
                int slope_count = 1;
                
//                 StdOut.println("2 j "+points[j]);
                
                if (j==i)continue;
                

                
                Double s1 = points[i].slopeTo(points[j]);
                
//               StdOut.println("2 s1 "+s1);
                
                if (points[i].compareTo(points[j])==1){min=points[j];} else {min=points[i];}
            if (points[j].compareTo(points[i])==1){max=points[j];} else {max=points[i];}
    
//            StdOut.println("3 min "+min+" max "+max);
            
            
                for (int k=0;k<N;k++){
                    
//                    StdOut.println("4 k "+points[k]);
                    
                    if (k==i || k==j)continue;
                    
                    Double s2 = points[i].slopeTo(points[k]);
//                    StdOut.println("5 s2 "+s2);
                    
                                           
                if (s1.equals(s2)){
                    
                 slope_count++;
                 
//                 StdOut.println("6 slope_count "+slope_count);
               
                  if (min.compareTo(points[k])==1){min=points[k];} 
            if (points[k].compareTo(max)==1){max=points[k];}
            
//                        StdOut.println("7 min "+min+" max "+max);
                
                }
                
            
                
            }
                
                if (slope_count >= 3){
                numofseg++;
                
//                StdOut.println("8 min "+min+" max "+max+" numofseg "+numofseg);
                
                
                if (min==max)continue;
                 min_ls[numofseg-1] = min;
                max_ls[numofseg-1] = max;
                if ((numofseg) >= min_ls.length) {resize_minLS(2*min_ls.length);resize_maxLS(2*max_ls.length);}

                
                }
            
        }
        
}
    }
    
   private void resize_minLS(int max)
   {  // Move stack to a new array of size max.
//       StdOut.println("min LS "+max);
      Point[] temp =  new Point[max];
      for (int i = 0; i < min_ls.length; i++){
//          StdOut.println("i,numofseg+1 "+i+" "+(numofseg+1));
          temp[i] = min_ls[i];}
      min_ls = temp;
   }

     private void resize_maxLS(int max)
   {  // Move stack to a new array of size max.
//       StdOut.println("max LS "+max);
      Point[] temp =  new Point[max];
      for (int i = 0; i < max_ls.length; i++){
//          StdOut.println("i,numofseg+1 "+i+" "+(numofseg+1));
          temp[i] = max_ls[i];}
      max_ls = temp;
   }
    
    public           int numberOfSegments()    {
        // the number of line segments
        return segments().length;
    }
   public LineSegment[] segments()            
   {// the line segments
       
//      StdOut.println("min_ls");
//                         for (Point min : min_ls){StdOut.println(min);}                  
       
       LineSegment[] segments = new LineSegment[min_ls.length];
       int k=0;
       Point[] min_ls_final = new Point[min_ls.length];
    Point[] max_ls_final = new Point[min_ls.length];
       min_ls_final[0] = min_ls[0];
       max_ls_final[0] = max_ls[0];
       if (min_ls[0] == null || max_ls[0] == null)return new LineSegment[0];
       segments[k++]=new LineSegment(min_ls[0],max_ls[0]);
//       StdOut.println("-2 "+min_ls.length);
       for (int i=1;i<min_ls.length;i++){
           int found = 0;
//           StdOut.println("-1 "+min_ls[i]+" "+max_ls[i]);
           for (int j=0;j<min_ls_final.length;j++){
//               StdOut.println("0 "+min_ls_final[j]+" "+max_ls_final[j]);
               if (min_ls_final[j]==null || min_ls[i]==null){break;}
//           StdOut.println("1 "+min_ls_final[j].slopeTo(min_ls[i]));
//           StdOut.println("2 "+max_ls_final[j].slopeTo(max_ls[i]));
           if ((min_ls_final[j].slopeTo(min_ls[i]) == Double.NEGATIVE_INFINITY) && (max_ls_final[j].slopeTo(max_ls[i]) == Double.NEGATIVE_INFINITY)){
//               StdOut.println("found");
               found=1;break;}
           
       }
           if (found==0){
//               StdOut.println("not found");
               if (min_ls[i]==null)continue;
               min_ls_final[k]=min_ls[i];
           max_ls_final[k]=max_ls[i];
           segments[k++]=new LineSegment(min_ls[i],max_ls[i]);
           }
           }
       
       segments = Arrays.copyOfRange(segments,0,k);
       return segments;

   }
   
   public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdOut.println("1");
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();


    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    StdOut.println("collinear segments"+collinear.numberOfSegments());
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
    
    
}

}