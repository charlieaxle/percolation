import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats{
    
    private double testPercent;
                              
    private double mean;
    
    private double stddev;
    
    private double confidenceLo;
    
    public double totalNodes;
    
    public double[] percThresh;
    
    private double confidenceHi;
    
    public double trialOpens;
    
    private Percolation perc;
    
    public PercolationStats(int n, int trials){
        
        totalNodes = n*n;
        
        percThresh = new double[trials];
        
        for (int i=0; i<trials; i++){
            
            perc = new Percolation(n);
            
            while (!perc.percolates()){
                
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                
                perc.open(row, col);
                trialOpens=perc.numberOfOpenSites();
                
                
            }
            testPercent = trialOpens/(n*n);
                percThresh[i] = testPercent;
            
        }
            mean = StdStats.mean(percThresh);
            stddev= StdStats.stddev(percThresh);
            
            confidenceLo= mean - ((1.96* stddev)/java.lang.Math.sqrt(trials));
            confidenceHi= mean +  ((1.96* stddev)/java.lang.Math.sqrt(trials));
        }
        
    
    
    public double mean() {
        return mean;
    }
    public double stddev() {
        return stddev;
    }
    public double confidenceLo() {
        return confidenceLo;
    }
    public double confidenceHi(){
        return confidenceHi;
    }
    
    
    public static void main(String[] args){
        
        int length = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);
     
        PercolationStats percStats = new PercolationStats(length, trials);
        
        System.out.println("Mean = "+ percStats.mean());
         System.out.println("stddev = "+ percStats.stddev());
         System.out.println("95% Confidence Interval = ["+percStats.confidenceLo()+", "+percStats.confidenceHi()+"]");
        
        
    }
    
                 
                              
}
