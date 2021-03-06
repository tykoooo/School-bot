package schoolbot.natives.util.operations;

public class MatrixOps {//kk thtas lit
// im am going to run it watch
    public static double[][] add(double[][] a, double[][] b) {
        double[][] c = a;
        if (a.length == b.length && a[0].length == b[0].length) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    c[i][j] = a[i][j] + b[i][j];
                }
            }
            return c;
        } else {
            // invalid sizes, undefined matrix
            return zero(c.length, c[0].length);
        }
    }

    public static double[][] subtract(double[][] a, double[][] b) {
        double[][] c = a;
        if (a.length == b.length && a[0].length == b[0].length) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    c[i][j] = a[i][j] - b[i][j];
                }
            }
            return c;
        } else {
            // invalid sizes, undefined matrix
            return zero(c.length, c[0].length);
        }
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];
        if (a[0].length == b.length) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        c[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
            return c;
        } else {
            // invalid sizes, undefined matrix
            return zero(a.length, b[0].length);
        }
    }

    public static double[][] multiply(double[][] a, double b) {
        double[][] c = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                c[i][j] = a[i][j] * b;
            }
        }
        return c;
    }

    public static double[][] multiplyElementWise(double[][] a, double[][] b) {
        double[][] c = new double[a.length][a[0].length];
        if (a.length == b.length && a[0].length == b[0].length) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    c[i][j] = a[i][j] * b[i][j];
                }
            }
            return c;
        } else {
            // undefined matrix
            return zero(a.length, a[0].length);
        }
    }

    public static double[][] dot(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];
        if (a.length == b[0].length) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < b.length; k++) {
                        c[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
            return c;
        } else {
            // invalid size undefined
            return zero(a.length, a[0].length);
        }
    }

    public static double[][] transpose(double[][] a) {
        double[][] b = new double[a[0].length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                b[j][i] = a[i][j];
            }
        }
        return b;
    }

    public static double[][] rref(double[][] a) {
        double[][] rref = new double[a.length][a[0].length];

        for (int r = 0; r < rref.length; ++r) {
            for (int c = 0; c < rref[r].length; ++c) {
                rref[r][c] = a[r][c];
            }
        }

        for (int p = 0; p < rref.length; ++p) {
            double pv = rref[p][p];
            if (pv != 0) {
                double pvInv = 1.0 / pv;
                for (int i = 0; i < rref[p].length; ++i) {
                    rref[p][i] *= pvInv;
                }
            }

            for (int r = 0; r < rref.length; ++r) {
                if (r != p) {
                    double f = rref[r][p];
                    for (int i = 0; i < rref[r].length; ++i) {
                        rref[r][i] -= f * rref[p][i];
                    }
                }
            }
        }
        return rref;
    }

    public static double mean(double[][] a) {
        double b = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                b += a[i][j];
            }
        }
        return b / (a.length * a[0].length);
    }

    public static double[][] zero(int rows, int cols) {
        double[][] c = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                c[i][j] = 0;
            }
        }
        return c;
    }
}
