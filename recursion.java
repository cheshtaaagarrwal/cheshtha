import java.util.ArrayList;

public class recursion {
    public static boolean IsValidMove(final boolean Board[][], final int r, final int c) {
        final int[][] dirA = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        // int [][] dirA={{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};
        for (int d = 0; d < dirA.length; d++) {

            for (int rad = 1; rad <= Board.length; rad++) {
                final int x = r + rad * dirA[d][0];
                final int y = c + rad * dirA[d][1];
                if (x >= 0 && y >= 0 && x < Board.length && y < Board[0].length) {
                    if (Board[x][y])
                        return false;
                } else
                    break;
            }
        }
        return true;
    }

    public static int Nqueen(final boolean[][] Board, final int lqps, final int tnq, final String Myans) {
        if (tnq == 0) {
            System.out.println(Myans);
            return 1;
        }
        int count = 0;
        for (int r = lqps; r < Board.length * Board[0].length; r++) {
            final int x = r / Board[0].length;
            final int y = r % Board[0].length;
            if (IsValidMove(Board, x, y)) {
                Board[x][y] = true;
                count += Nqueen(Board, r + 1, tnq - 1, Myans + "(" + x + "," + y + ")");
                Board[x][y] = false;
            }
        }
        return count;
    }

    // ========================================================
    static boolean[] ROW;
    static boolean[] COL;
    static boolean[] DIAG;
    static boolean[] ADIAG;

    public static int Nqueen5(final int n, final int m, final int idx, final int tnq, final String Myans) {
        if (tnq == 0) {
            System.out.println(Myans);
            return 1;
        }
        int count = 0;
        
        for (int r = idx; r < n * m; r++) {
            final int x = r / m;
            final int y = r % m;
            if (!ROW[x] && !COL[y] && !DIAG[x + y] && !ADIAG[x - y + m - 1]) {
                ROW[x] = true;
                COL[y] = true;
                DIAG[x + y] = true;
                ADIAG[x - y + m - 1] = true;
                count += Nqueen5(n, m, r + 1, tnq - 1, Myans + "(" + x + "," + y + ")");
                ROW[x] = false;
                COL[y] = false;
                DIAG[x + y] = false;
                ADIAG[x - y + m - 1] = false;
            }
        }
        return count;
    }

    // ===========================================================
    static int row;
    static int col;
    static int diag;
    static int adiag;

    public static int Nqueen6(final int n, final int m, final int idx, final int tnq, final String Myans) {
        if (tnq == 0) {
            System.out.println(Myans);
            return 1;
        }
        int count = 0;

        for (int r = idx; r < n * m; r++) {
            final int x = r / m;
            final int y = r % m;
            if ((row & (1 << x)) == 0 && (col & (1 << y)) == 0 && (diag & (1 << (x + y))) == 0
                    && (adiag & (1 << (x - y + m - 1))) == 0) {
                row ^= 1 << x;
                col ^= 1 << y;
                diag ^= 1 << (x + y);
                adiag ^= 1 << (x - y + m - 1);
                count += Nqueen6(n, m, r + 1, tnq - 1, Myans + "(" + x + "," + y + ")");
                row ^= 1 << x;
                col ^= 1 << y;
                diag ^= 1 << (x + y);
                adiag ^= 1 << (x - y + m - 1);
            }
        }
        return count;
    }

    public static void main(final String[] args) {
        final boolean[][] Board = new boolean[4][4];
        final int tnq = 4;
        final int n = 4;
        ROW = new boolean[n];
        COL = new boolean[n];
        DIAG = new boolean[n + n - 1];
        ADIAG = new boolean[n + n - 1];
        System.out.println(Nqueen6(4, 4, 0, 4, ""));
    }
}