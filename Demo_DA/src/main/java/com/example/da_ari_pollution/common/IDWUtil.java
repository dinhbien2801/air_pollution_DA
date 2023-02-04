package com.example.da_ari_pollution.common;

public class IDWUtil {
    private static final double p = -2;

    public static double[][] distanceInterpolate(double[][] input) {
        int totalRow = input.length;
        int totalCol = input[0].length;
        double[][] result = new double[totalRow][totalCol];
        for (int x = 0; x < totalRow; x++)
            for (int y = 0; y < totalCol; y++) {
                if (input[x][y] != 0  ||  indexCheck(x,y)==1 ) result[x][y] = input[x][y];
//                if (input[x][y] != 0) result[x][y] = input[x][y];
                else result[x][y] = inverseDistanceWeight(x, y, input);
            }
        return result;
    }

    private static double inverseDistanceWeight(int x, int y, double[][] input) {
        int totalRow = input.length;
        int totalCol = input[0].length;
        double currentValue = 0;
        for (int x1 = 0; x1 < totalRow; x1++)
            for (int y1 = 0; y1 < totalCol; y1++) {
                if (Math.abs(x - x1) <= 2 && Math.abs(y - y1) <= 2) {
                    double ptu = ptuCalculate(x, y, x1, y1, input);
                    currentValue += ptu * input[x1][y1];
                }
            }
        return currentValue;
    }

    private static double ptuCalculate(int x, int y, int x1, int y1, double[][] input) {
        double totalWeight = totalWeightCalculate(x, y, input);
        double currentWeight = weightCalculate(x, y, x1, y1);
        return currentWeight / totalWeight;
    }
    private  static  int indexCheck(int i, int j){
        if(i%2 ==1 && j%2 ==1  && i!=49 && j!= 49){
            return 1;
        }
        return 0;
    }

    private static double totalWeightCalculate(int x, int y, double[][] input) {
        int totalRow = input.length;
        int totalCol = input[0].length;
        double totalWeight = 0;
        for (int x1 = 0; x1 < totalRow; x1++)
            for (int y1 = 0; y1 < totalCol; y1++) {
                if (input[x1][y1] != 0 && Math.abs(x - x1) <= 2 && Math.abs(y - y1) <= 2) {
                    double currentWeight = weightCalculate(x, y, x1, y1);
                    totalWeight += currentWeight;
                }
            }
        return totalWeight;
    }

    private static double weightCalculate(int x1, int y1, int x2, int y2) {
        double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        if (distance == 0) return 0;
        return Math.pow(distance, p);
    }
}
