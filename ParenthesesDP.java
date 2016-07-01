

import java.util.Scanner;

public class ParenthesesDP {

    static char[] expr;
    static int Smax[][];
    static int Smin[][];
    static int[] exprnum;
    static String[] operator;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String temp = input.nextLine();
        String[] t = temp.split(" ");							// store the input expression

        exprnum = new int[(t.length / 2) + 1];
        operator = new String[t.length - ((t.length / 2) + 1)];
        int j = 0;
        int x = 0;
        for (int i = 0; i < t.length; i++) {
            if (i % 2 == 0) {
                exprnum[j++] = Integer.parseInt(t[i]);			// store all numeric values
            } else {
                operator[x++] = t[i];							// store all operators
            }
        }


        Smax = new int[exprnum.length + 1][exprnum.length + 1];
        Smin = new int[exprnum.length + 1][exprnum.length + 1];


        for (int k = 1; k <= exprnum.length; k++) {				// base cases for Smax and Smin
            Smax[k][k] = (exprnum[k - 1]);
            Smin[k][k] = (exprnum[k - 1]);
        }
        for (int d = 1; d <= exprnum.length; d++) {
            for (int L = 1; L <= exprnum.length - d; L++) {
                int R = L + d;
                Smax[L][R] = -99999;
                Smin[L][R] = 99999;

                for (int z = L; z <= R - 1; z++) {

                    int temp4 = calculate(Smax[L][z], Smax[z + 1][R], operator[z - 1]);
                    int temp5 = calculate(Smin[L][z], Smin[z + 1][R], operator[z - 1]);
                    int temp6 = calculate(Smin[L][z], Smax[z + 1][R], operator[z - 1]);
                    int temp7 = calculate(Smax[L][z], Smin[z + 1][R], operator[z - 1]);


                    if (Smax[L][R] < max(temp4, temp5, temp6, temp7)) {	// calculate the maximum value and replace the value in Smax if required
                        Smax[L][R] = max(temp4, temp5, temp6, temp7);
                    }

                    if (Smin[L][R] > min(temp4, temp5, temp6, temp7)) { // calculate the minimum value and replace the value in Smax if required
                        Smin[L][R] = min(temp4, temp5, temp6, temp7);
                    }
                }

            }
        }


        int ans = Smax[1][exprnum.length];								// final answer
        System.out.println(ans);
    }


    public static int max(int a, int b, int c, int d) {					// function to find the maximum value

        int first = Math.max(a, b);
        int sec = Math.max(first, c);
        int third = Math.max(sec, d);


        return third;
    }

    public static int min(int a, int b, int c, int d) {					// function to find the minimum value

        int first = Math.min(a, b);
        int sec = Math.min(first, c);
        int third = Math.min(sec, d);


        return third;
    }


    public static int calculate(int a, int b, String c) {				// function to calculate the expression value
        if (c.equals("+")) {
            return a + b;
        } else {
            return a - b;
        }
    }

}
