import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final int N;   // one side of the grid (N)
  private final WeightedQuickUnionUF WQUF;    // Union find data structure representing the grid
  private final WeightedQuickUnionUF WQUF2;    // Union find data structure representing the grid
  private boolean[][] grid;  // data structure for tracking open/closed sites

  private int no_of_open_sites = 0;

  public Percolation(int n) {
    // Constructor should take N^2 time
      /**
   * Create N-by-N grid, with all sites blocked.
   */
      if (n<1)
         throw new java.lang.IllegalArgumentException("Illegal parameter value.");
      N = n;
      grid = new boolean[N][N];
      WQUF = new WeightedQuickUnionUF((N * N) + 2);
      WQUF2 = new WeightedQuickUnionUF((N * N) + 1);
  }
  
  
  public void open(int row, int col) {
      if (row < 1 || row > N || col < 1 || col >N)
         throw new java.lang.IllegalArgumentException("Illegal parameter value.");
      
      if (grid[row-1][col-1]!=true)
          no_of_open_sites += 1;
      
      grid[row-1][col-1]=true;
      
      if (row == 1)
      {
          WQUF.union(0,col);
          WQUF2.union(0,col);
          
     }
      
      if (row==N)
      {
                    WQUF.union(N*(N-1)+col, N*N+1);
        
      }
      
      if (row>1)
      {   
          if (grid[row-2][col-1] == true)
          {
          WQUF.union(N*(row-1)+col,N*(row-2)+col);
          WQUF2.union(N*(row-1)+col,N*(row-2)+col);
          }
      }
          
      if (row<N)
      {
          if (grid[row][col-1] == true)
          {
          WQUF.union(N*(row-1)+col,N*(row)+col);
          WQUF2.union(N*(row-1)+col,N*(row)+col);
          }
      }
      
      if (col>1)
      {
          if (grid[row-1][col-2] == true)
          {
          WQUF.union(N*(row-1)+col,N*(row-1)+col-1);
          WQUF2.union(N*(row-1)+col,N*(row-1)+col-1);
                }
      }
          
      if (col<N)
      {
         if (grid[row-1][col] == true)
         {
          WQUF.union(N*(row-1)+col,N*(row-1)+col+1);
          WQUF2.union(N*(row-1)+col,N*(row-1)+col+1);
         }
      }
      

      
      
  }
  
  
  public boolean isOpen (int row, int col){
      // is site (row, col) open?
      if (row < 1 || row > N || col < 1 || col >N)
          throw new java.lang.IllegalArgumentException("Illegal parameter value.");
      return grid[row-1][col-1] == true;
  }
  
  public boolean isFull(int row, int col){
      // is site (row, col) full?
      if (row < 1 || row > N || col < 1 || col >N)
          throw new java.lang.IllegalArgumentException("Illegal parameter value.");

      return isOpen(row,col) && WQUF2.connected(0,(N*(row-1)+col));
                                                                                                                                     }

  
  
  public int numberOfOpenSites(){
      // number of open sites
  return no_of_open_sites;
//     return WQUF.count();
  }
  
  public boolean percolates(){
      // does the system percolate?
  return WQUF.connected(0,N*N+1);
  }
  
  public static void main(String[] args){
      // To be empty
    }
  
}