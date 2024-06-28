package com.quizzes;

/*

MA Moving Average

INPUT
int []v={1,3,2,3,1,2}
n=6
w=3 1<=w<=n

OUTPUT

int []={6/3,8/3,6/3,6/3}

6
3
4

5
3

*/

import java.util.Arrays;

public class MovingAverage
{
    public static void main(String[] args) {
        int[] v = {1,3,2,3,1,2};
        int n=v.length;
        int w=3;
        double[] output = MovingAverage.MA(v, n, w);
        System.out.println(Arrays.toString(output));
    }

    static double [] MA(int[] v, int n, int w){
        if (isWBiggerThanN(n, w))
            throw new RuntimeException("W is bigger than N");

        return calculateMA(v, n, w);
    }

    private static double[] calculateMA(int[] v, int n, int w) {
        double[] output = new double[n];
        double sum = 0;
        int lenght = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<w; j++){
                int index = i + j;
                if (isMADone(n, index)){
                    return getArraycopy(output, lenght);
                }
                sum+=v[index];
            }
            output[i]=sum/w;
            sum = 0;
            lenght++;
        }
        return output;
    }

    private static boolean isMADone(int n, int index) {
        return index >= n;
    }

    private static boolean isWBiggerThanN(int n, int w) {
        return w>n;
    }

    private static double[] getArraycopy(double[] origen, int lenght) {
        double[] output = new double[lenght];
        System.arraycopy(origen, 0, output, 0, lenght);
        return output;
    }

}
