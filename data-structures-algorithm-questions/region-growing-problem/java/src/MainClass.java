import strategies.SimpleRottingOrange;

public class MainClass {

    public static void main(String[] args){
        runSimpleRottingOrangeExampleU();

    }

     private static void runSimpleRottingOrangeExampleU(){
         int[][] matrix = new int[][]{{0,1,2}, {1,0,0}, {1,2,2}};
         System.out.println(SimpleRottingOrange.getMinDaysToRotAllOrangeUsingBFS(matrix,matrix[0].length,matrix.length));
         System.out.println(SimpleRottingOrange.getMinDaysToRotAllOrangeUsingMatrixTraversal(matrix,matrix[0].length,matrix.length));

         matrix = new int[][]{{0,1,2,1}, {1,0,0,1}, {1,2,2,1}};
         System.out.println(SimpleRottingOrange.getMinDaysToRotAllOrangeUsingBFS(matrix,matrix[0].length,matrix.length));
         System.out.println(SimpleRottingOrange.getMinDaysToRotAllOrangeUsingMatrixTraversal(matrix,matrix[0].length,matrix.length));


     }
}

