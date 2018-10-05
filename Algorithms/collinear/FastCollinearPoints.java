import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Comparator;
public class FastCollinearPoints {
     private int numofseg=0;
    private LineSegment[] ls = new LineSegment[1];
    private    Point[] min_ls = new Point[1];
    private    Point[] max_ls = new Point[1];
            
    public FastCollinearPoints(Point[] points)  {   // finds all line segments containing 4 or more points
    Point min;
    Point max;
    int N=points.length;
    
    Arrays.sort(points, Point::compareTo);

        for (int i=0;i<N-1;i++){
         if (points[i]==null || points[N-1]==null || points[i].slopeTo(points[i+1])==Double.NEGATIVE_INFINITY) 
            throw new IllegalArgumentException("argument is null or same");
        }
    
            
    Point[] PS = new Point[N];
    
        for (int i=0;i<N;i++){

            PS = Arrays.copyOfRange(points,0,N);
            Arrays.sort(PS, points[i].slopeOrder());
            
            Double slope_ij_1 = null;
            Double slope_ij = null;
            min=points[i];
            max=points[i];
            int slope_count = 1;

            
            for (int j=1; j<N; j++){
            slope_ij = (points[i].slopeTo(PS[j]));
            
//           StdOut.println("1 points[i] "+points[i]+" PS[j] "+PS[j]);
//            StdOut.println("2 slope_ij "+slope_ij+" slope_ij_1 "+slope_ij_1+" slope_count "+slope_count+" min "+min+" max "+max); 
//            
           if (j==1){if (points[i].compareTo(PS[j])==1){min=PS[j];} else {min=points[i];}
            if (PS[j].compareTo(points[i])==1){max=PS[j];} else {max=points[i];}}
           
            if ((slope_ij_1 != null) && !(slope_ij_1.equals(slope_ij))){
                
                
                if (slope_count >= 3){

                numofseg++;

                min_ls[numofseg-1] = min;
                max_ls[numofseg-1] = max;

                if ((numofseg) >= min_ls.length) {resize_minLS(2*min_ls.length);resize_maxLS(2*max_ls.length);}
//                temp_slope = slope_ij;
//                 StdOut.println("5.1 ls"+(numofseg-1)+" "+ls[numofseg-1]+" ls.length "+ls.length+" min_ls "+min_ls.length+" max_ls "+max_ls.length);

            
            }
            
            


                

                if (points[i].compareTo(PS[j])==1){min=PS[j];} else {min=points[i];}
            if (PS[j].compareTo(points[i])==1){max=PS[j];} else {max=points[i];}
            slope_count = 1;
//           StdOut.println("3 not equals slope_ij "+slope_ij+" slope_ij_1 "+slope_ij_1+" slope_count "+slope_count+" min "+min+" max "+max); 
            }
            
            if ((slope_ij_1 != null) && slope_ij_1.equals(slope_ij)){
         

            slope_count++;
            if (min.compareTo(PS[j])==1){min=PS[j];}
            if (PS[j].compareTo(max)==1){max=PS[j];}

//          StdOut.println("4 equals slope_ij "+slope_ij+" slope_ij_1 "+slope_ij_1+" slope_count "+slope_count+" min "+min+" max "+max); 
            
            }
            
            
            
            
                           slope_ij_1 = slope_ij;   
        }
            
            
            if (slope_count >= 3){

                numofseg++;
//                StdOut.println(">3 slope_ij "+slope_ij+" temp_slope "+temp_slope+" slope_count "+slope_count+" min "+min+" max "+max+" numofseg "+numofseg); 
                if (min==max)continue;
//                LineSegment temp_line = new LineSegment(min,max);
                int numofseg_1 = numofseg-1;
//                StdOut.println("4.51 numofseg_1 "+numofseg_1+" numofseg "+numofseg);
//                ls[numofseg_1] = temp_line;
                min_ls[numofseg-1] = min;
                max_ls[numofseg-1] = max;

//                
//                StdOut.println("4.52 numofseg_1 "+numofseg_1+" numofseg "+numofseg);
//                StdOut.println("4.6 ls"+(numofseg-1)+" "+ls[numofseg-1]+" ls.length "+ls.length+" min_ls "+min_ls.length+" max_ls "+max_ls.length);
                
               
                        if ((numofseg) >= ls.length) {resize_LS(2*ls.length); resize_minLS(2*min_ls.length);resize_maxLS(2*max_ls.length);}

            
            }


         
    }
  
    }
    
     private void resize_LS(int max)
   {  // Move stack to a new array of size max.
//       StdOut.println("max LS "+max);
      LineSegment[] temp =  new LineSegment[max];
      for (int i = 0; i < ls.length; i++){
//          StdOut.println("i,numofseg+1 "+i+" "+(numofseg+1));
          temp[i] = ls[i];}
      ls = temp;
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
//       
//       
//       public static void main(String[] args) {
//
//    // read the n points from a file
//    In in = new In(args[0]);
//    int n = in.readInt();
//    Point[] points = new Point[n];
//    for (int i = 0; i < n; i++) {
//        int x = in.readInt();
//        int y = in.readInt();
//        points[i] = new Point(x, y);
//    }
//    
//   
//    // draw the points
//    StdDraw.enableDoubleBuffering();
//    StdDraw.setXscale(0, 32768);
//    StdDraw.setYscale(0, 32768);
//
//    for (Point p : points) {
//        p.draw();
//    }
//    StdDraw.show();
//
//    // print and draw the line segments
//    FastCollinearPoints collinear = new FastCollinearPoints(points);
//    StdOut.println("collinear segments"+collinear.numberOfSegments());
//    for (LineSegment segment : collinear.segments()) {
//        StdOut.println(segment);
//        segment.draw();
//    }
//    StdDraw.show();
//}

}