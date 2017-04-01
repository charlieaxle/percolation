import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Percolation{
 
    private WeightedQuickUnionUF qf;
    
    private boolean[] opened;
    
    private int length;
    private int size;
    
    private int openCount;
    
    private boolean isInvalid(int ID){
        return (ID < 1 || ID >size -2) ;
    }
    
    private int getIndex(int row, int col){
     
        return (length*(row-1) + col);
        
    }
    
    public Percolation(int n){
        length = n;
        size = n*n+2;
        
        qf = new WeightedQuickUnionUF(size);
        
        opened = new boolean[size];
        
        opened[0] = true;
        
        opened[n*n+1] = true;
        
        for (int i = 1; i<n+1; i++){
            for (int j=1; j<n+1; j++){
                int ID = getIndex (i,j);
                opened[ID] = false;
            }
        }
        
        for (int k = 1; k<n+1; k++){
            qf.union(0, k);
        }
        for (int k = n*n -n +1; k< n*n +1; k++){
            qf.union(n*n+1, k);
        }      
    }
    
    public boolean isOpen(int row, int col) {
        int index = getIndex(row, col);
        if (isInvalid(index)) throw new IndexOutOfBoundsException("Index out of bounds");
        return opened[index];
    }
      
    public boolean isFull(int row, int col) {
        int index = getIndex(row, col);
        if (isInvalid(index)) throw new IndexOutOfBoundsException("Index out of bounds");
        return !opened[index];
    }
    
    public int numberOfOpenSites() {
        
        return openCount;
    }
    
    public boolean percolates() {
        return qf.connected(0, size -1);
    }
    
    public void open(int row, int col){
        
        int ID = getIndex(row, col);
        if (isInvalid(ID)) throw new IndexOutOfBoundsException("Index out of bounds");
        if (opened[ID]) return;
        openCount++;
        opened[ID] = true;
        int nearRow;
        int nearCol;
        //left and right
        for (int i=-1; i<2; i++){

            nearRow = row;
            nearCol = col + i;
            if (nearRow >0 && nearRow < length+1 && nearCol>0 && nearCol<length+1){
                 
                int nearID = getIndex(nearRow, nearCol);
                if (opened[nearID]) qf.union(ID, nearID);
                    
                }
            
        }
               for (int j=-1; j<2; j++){

            nearRow = row + j;
            nearCol = col;
            if (nearRow >0 && nearRow < length+1 && nearCol>0 && nearCol<length+1){
                 
                int nearID = getIndex(nearRow, nearCol);
                if (opened[nearID]) qf.union(ID, nearID);
                    
                }
            
        }
    }
    
  public static void main(String[] args) {
    int grid_size = 3;
    Percolation perc = new Percolation(grid_size);
    //initialize the grid to something
//    for(int i=0;i<grid_size;i++){
//      for (int j=0;j<grid_size;j++){
//        System.out.println(perc.isOpen(i,j));
//      }
//    }

//  let's test if the openning of a site works. 

    perc.open(1,1);
    perc.open(2,1);
    perc.open(2,2);
    perc.open(3,2);
    perc.open(3,2);
    System.out.println(perc.percolates());
    System.out.println(perc.isOpen(1,1));
    System.out.println(perc.openCount);
  }
     
}