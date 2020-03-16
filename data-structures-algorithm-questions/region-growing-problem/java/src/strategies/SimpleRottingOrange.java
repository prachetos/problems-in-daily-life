package strategies;

import sun.misc.Queue;

import java.util.*;

public class SimpleRottingOrange {

    public static int getMinDaysToRotAllOrangeUsingBFS(int[][] orangeOrchard, int rowCount, int columnCount){
        if (rowCount<=0 || columnCount<=0 || orangeOrchard==null){
            return 0;
        }
        int minDays = 1;
        int[][] daysMatrixForOrchard = new int[columnCount][rowCount];
        int[][] orchardCopy = new int[columnCount][rowCount];
        for (int i =0; i<columnCount;i++) {
            for (int j = 0; j < rowCount; j++) {
                orchardCopy[i][j] = orangeOrchard[i][j];
            }
        }

        // lets do a traversal first, and find out all the rotten oranges,
        // and add them to a queue to start off with BFS
        // Java doesn't have a Queue Impl, we can use an ArrayList or PQ
        // 0 blank, 1 good orange, 2 bad orange
        LinkedList<int[]> pointCoordinatesOfRottenOranges = new LinkedList<int[]>();

        for (int i=0;i<columnCount;i++){
            for (int j=0;j<rowCount;j++){
                if(orchardCopy[i][j]==2){
                    // put in the neighbors
                    // lets look at top, left, bottom, right only
                    pointCoordinatesOfRottenOranges.addAll(getImmediateNeighborsOfType(j,i,rowCount,columnCount,
                            orchardCopy, 1, true, true, true, true));
                }
            }
        }
        pointCoordinatesOfRottenOranges.add(null);

        while(!pointCoordinatesOfRottenOranges.isEmpty()){
            int[] curPoint = pointCoordinatesOfRottenOranges.poll();
            if (curPoint==null){
                if(!pointCoordinatesOfRottenOranges.isEmpty()){
                    // detect level change, but prevent while lopp
                    pointCoordinatesOfRottenOranges.add(null);
                    minDays++;
                    continue;
                }
            }else{
                if(orchardCopy[curPoint[1]][curPoint[0]] == 1){
                    // good orange, going bad, will start infecting others,
                    orchardCopy[curPoint[1]][curPoint[0]]=2;
                    pointCoordinatesOfRottenOranges.addAll(getImmediateNeighborsOfType(
                            curPoint[0],curPoint[1],rowCount,columnCount, orchardCopy, 1,
                            true,true,true,true));
                }
            }
        }
        return minDays;

    }

    public static int getMinDaysToRotAllOrangeUsingMatrixTraversal(int[][] orangeOrchard, int rowCount, int columnCount){
        if (rowCount<=0 || columnCount<=0 || orangeOrchard==null){
            return 0;
        }
        int minDays = 0;
        int[][] daysMatrixForOrchardFirstSweep = new int[columnCount][rowCount];
        int[][] daysMatrixForOrchardSecondSweep = new int[columnCount][rowCount];
        int[][] orchardCop1 = new int[columnCount][rowCount];
        int[][] orchardCopy2 = new int[columnCount][rowCount];

        // don't want to change arguments, hence copying
        for (int i =0; i<columnCount;i++) {
            for (int j = 0; j < rowCount; j++) {
                orchardCop1[i][j] = orangeOrchard[i][j];
                orchardCopy2[i][j] = orangeOrchard[i][j];
            }
        }

        // browse left to right, top to bottom
        for (int i =0; i<columnCount;i++){
            for (int j=0;j<rowCount;j++){
                if(orchardCop1[i][j]==1){
                    // get neighbors
                    LinkedList<int[]> neigbors = getImmediateNeighborsOfType(j,i,rowCount,columnCount, orchardCop1,
                            2, true, true, false, false);
                    if (!neigbors.isEmpty()){
                        int minDaysCur = Integer.MAX_VALUE;
                        for (int[] nb : neigbors){
                            minDaysCur = Math.min(minDaysCur,( daysMatrixForOrchardFirstSweep[nb[1]][nb[0]] + 1));
                        }
                        minDays = Math.max(minDaysCur, minDays);
                        orchardCop1[i][j] =2;
                        daysMatrixForOrchardFirstSweep[i][j] = minDaysCur;
                    }

                }
            }
        }
        // browse right to left, bottom to top
        for (int i =columnCount-1; i>=0;i--){
            for (int j=rowCount-1;j>=0;j--){
                if(orchardCopy2[i][j]==1){
                    // get neighbors
                    LinkedList<int[]> neigbors = getImmediateNeighborsOfType(j,i,rowCount,columnCount, orchardCopy2,
                            2, false, false, true, true);
                    if (!neigbors.isEmpty()) {
                        int minDaysCur = Integer.MAX_VALUE;
                        for (int[] nb : neigbors){
                            minDaysCur = Math.min(minDaysCur,( daysMatrixForOrchardSecondSweep[nb[1]][nb[0]] + 1));
                        }
                        if(daysMatrixForOrchardFirstSweep[i][j]!=0) {
                            minDaysCur = Math.min(minDaysCur, daysMatrixForOrchardFirstSweep[i][j]);
                        }
                        minDays = Math.max(minDaysCur, minDays);
                        orchardCopy2[i][j] =2;
                        daysMatrixForOrchardSecondSweep[i][j] = minDaysCur;
                    }

                }
            }
        }
       return minDays;

    }

    private static LinkedList<int[]> getImmediateNeighborsOfType(int curRow, int cuRColumn, int maxRow, int maxColumn,
                                                         int[][] orangeOrchard, int type, boolean top,
                                                                 boolean left, boolean bottom, boolean right){
        LinkedList<int[]> neighbors = new LinkedList<>();
        if (curRow>0 && top){
            if(orangeOrchard[cuRColumn][curRow-1]==type){
                neighbors.add(new int[]{curRow-1, cuRColumn});
            }
        }
        if(cuRColumn>0 && left){
            if(orangeOrchard[cuRColumn-1][curRow]==type){
                neighbors.add(new int[]{curRow, cuRColumn-1});
            }
        }
        if(cuRColumn<maxColumn-1 && right){
            if(orangeOrchard[cuRColumn+1][curRow]==type){
                neighbors.add(new int[]{curRow, cuRColumn+1});
            }
        }
        if(curRow<maxRow-1 && bottom){
            if(orangeOrchard[cuRColumn][curRow+1]==type){
                neighbors.add(new int[]{curRow+1, cuRColumn});
            }
        }
        return neighbors;
    }

}
