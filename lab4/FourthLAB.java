import java.util.Scanner;

public class FourthLAB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int N = 11;
//        int[] arr = {200, 300, 300, 400, 350, 410, 600, 800, 500, 600, 350};
//        int[] arr1 = {20, 300, 3, 40, 350, 410, 600, 300, 1, 6, 6};
//
//        ninthTask nine = new ninthTask();
//        nine.eff(N, arr1);

        fifthtask five = new fifthtask();
        int N = 8;
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            System.out.println("Enter point");
            points[i] = new Point(sc.nextInt(), sc.nextInt());
        }
        five.maxLength(N, points);
    }
//       ^
//       | arr[i]
//       |
//  800  |                    *
//       |
//  600  |                 *        *
//       |                       *
//  400  |        *     *
//       |  *  *     *                 *
//  200  |
// -----------------------------------------> i
//       |  1  2  3  4  5  6  7  8  9  10
//
// (0;200)
// (1;300), (2;300),
// (3;400), (4;350),
// (5:410), (6; 600),
// (7; 800),(8; 500),
// (9; 600),(10; 350).

    public static class Point{
        int X;
        int Y;

        public Point(int x, int y){
            this.X = x;
            this.Y = y;
        }
        public int getX() {
            return X;
        }

        public int getY() {
            return Y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "X=" + X +
                    ", Y=" + Y +
                    '}';
        }
    }

    public static class Line{
        Point A, B;
        int length;

        public int getLength(){
            return Math.abs(A.getX() - B.getX());
        }

        public Line(Point A, Point B){
            this.A = A;
            this.B = B;
            if (A.getY() == B.getY()) {
                this.length = getLength();
            }
        }

    }

    public static class fifthtask{
        public void maxLength(int N, Point[] points){
            Line[] lines = new Line[N];

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
//                    System.out.println(points[i] + " TO " + points[j]);

                    if (points[i].getY() < points[j].getY()) {
//                        System.out.println("CANNOT ROPE " + points[i].getX() + " above " + points[j].getX());
                        break;
                    }

                    if (points[i].getY() == points[j].getY()) {
                        lines[i] = new Line(points[i], points[j]);
//                        System.out.println("ROPE between " + points[i]  + " and " + points[j] + " with length = " + lines[i].length);
                        break;
                    }
                }
//                System.out.println();
            }

            int maxLength = -1;

            for (int i = 0; i < N; i++) {
                if ((lines[i] != null ? lines[i].length : -1) > maxLength){
                    if (lines[i] != null) {
                        maxLength = lines[i].length;
                    }
                }
            }

            if (maxLength != -1) System.out.println("Maximum length of rope: " + maxLength);
            else System.out.println("No possible ropes!");
        }
    }



    public static class ninthTask{
        public void eff(int N, int[] arr){
            int[] efficiency = new int[N];
            double[] eff_diff = new double[N];

            for (int i = 0; i < N; i++) {
                if (i+1==N) break;
                else {
                    eff_diff[i] = arr[i + 1] - arr[i];
                    if (Math.abs(eff_diff[i] / arr[i]) < 0.05){
                        efficiency[i] = -1;
                    } else if (eff_diff[i] / arr[i] > 0.05) {
                        efficiency[i] = 2;
                    } else if (eff_diff[i] / arr[i] < 0.05){
                        efficiency[i] = -2;
                    }
                }
            }

            System.out.println("Efficiency growth list:");
            for (int e: efficiency) {
                System.out.print(e + " ");
            }
            System.out.println();



            int[] startIndex = new int[N];
            int[] endIndex = new int[N];
            int[] temp_count = new int[N];
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (j+1==N) break;
                    else {
                        if (efficiency[i] > 0) {
                            if (efficiency[j] > 0) {
                                startIndex[i] = i;
//                                System.out.println("Start: " + startIndex[i]);
                                temp_count[i]++;

                                if (efficiency[j + 1] < 0) {
                                    endIndex[i] = j + 1 ;
//                                    System.out.println("End: " + endIndex[i]);
                                    break;
                                }

                            } else break;
                        }

                    }
                }
            }

            int maxEff = maxIndex(N, temp_count);
            System.out.println("Max eff is on index => "+maxEff + ", with value =>" + temp_count[maxEff]);


            System.out.println("Most efficient period: FROM arr[" + startIndex[maxEff] + "]=" + arr[startIndex[maxEff]]
                    + " -> TO arr[" + endIndex[maxEff] + "]="  + arr[endIndex[maxEff]]);
        }
    }

    private static double min(int N, double[] arr){
        double min = arr[0];
        for (int i = 0; i < N ; i++) {

            if (arr[i]<min){
                min = arr[i];
            }
        }
        return min;
    }
    private static double max(int N, double[] arr){
        double max = arr[N-1];
        for (int i = 0; i < N; i++) {
            if (arr[i]>max){
                max = arr[i];
            }
        }
        return max;
    }
    private static int max(int N, int[] arr){
        int max = arr[N-1];
        for (int i = 0; i < N; i++) {
            if (arr[i]>max){
                max = arr[i];
            }
        }
        return max;
    }

    private static int minIndex(int N, int[] arr){
        int index = 0;
        double min = arr[0];
        for (int i = 0; i < N ; i++) {
            if (arr[i]<min){
                min = arr[i];
                index = i;
            }
        }
        return index;
    }
    private static int maxIndex(int N, int[] arr){
        int index = 0;
        int max = arr[N-1];
        for (int i = 0; i < N; i++) {
            if (arr[i]>max){
                max = arr[i];
                index = i;
            }
        }
        return index;
    }
}
