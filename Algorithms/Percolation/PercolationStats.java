       import edu.princeton.cs.algs4.StdRandom;
       import edu.princeton.cs.algs4.StdStats;
       
 public class PercolationStats {
   private final double[] open_sites_in_trials;  // data structure for tracking open sites per trial
   private final int Trials;
     
    public PercolationStats(int n, int trials)   {
        // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("wrong args");
        }
        Trials=trials;
        open_sites_in_trials = new double[Trials];


        for (int trial=1;trial<=Trials;trial++)
        {
            int open_sites = 0;
       Percolation perc = new Percolation(n);
       int row, col;

       
       while (!perc.percolates()){

           row = StdRandom.uniform(n) + 1;
           col = StdRandom.uniform(n) + 1;
           if (!perc.isOpen(row,col)){
               
           perc.open(row,col);
           open_sites++;
           }
       }
       
       open_sites_in_trials[trial-1] = (double) open_sites/(n*n);
    }
    }
       
    public double mean()  {
        // sample mean of percolation threshold
        double mean = 0.0;
        mean = StdStats.mean(open_sites_in_trials);
        

        return mean;
            
    }
    public double stddev()  {
        // sample standard deviation of percolation threshold
        double std = 0.0;
        std = StdStats.stddev(open_sites_in_trials);
        

        return std;
    }
    public double confidenceLo()     {
        // low  endpoint of 95% confidence interval
        double mean, std;
        mean = mean();
        std = stddev();
        return (mean-1.96*std/Math.sqrt(Trials));
            
    }
    public double confidenceHi()     {
        // high endpoint of 95% confidence interval
     double mean, std;
        mean = mean();
        std = stddev();
        return (mean+1.96*std/Math.sqrt(Trials));
            
       
    }
    public static void main(String[] args){
              // To be empty
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n,trials);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + confidence);
    }
//
}
