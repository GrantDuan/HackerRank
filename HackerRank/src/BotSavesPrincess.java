import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BotSavesPrincess {
    /* Head ends here */
    static int mx;
    static int my;
    static int bx;
    static int by;
    static int N;

    static void displayPathtoPrincess(int n, String[] grid) {
        N = n;
        if (n < 3)
            return;
        // find m
        mx = n / 2;
        my = n / 2;
        if (n % 2 == 0) {
            if ((grid[mx].toCharArray())[my] != 'm') {
                mx--;
                my--;
            }

            if ((grid[mx].toCharArray())[my] != 'm') {
                mx++;
            }

            if ((grid[mx].toCharArray())[my] != 'm') {
                mx--;
                my++;
            }
        }

        // find p
        bx = mx;
        by = my;



        if ((grid[0].toCharArray())[0] == 'p') {
            left2End();
            up2End();
        }
        
        else if ((grid[0].toCharArray())[n-1] == 'p') {
            right2End();
            up2End();
        }
        
        else if ((grid[n-1].toCharArray())[n-1] == 'p') {
            right2End();
            down2End();
        }
        else
        {
            left2End();
            down2End();
        }
    }

    public static void right() {
        bx++;
        System.out.print("RIGHT\n");
    }

    public static void right2End() {
        while (bx < N - 1) {
            right();
        }
    }

    public static void left() {
        bx--;
        System.out.print("LEFT\n");
    }

    public static void left2End() {
        while (bx > 0) {
            left();
        }
    }

    public static void up() {
        by--;
        System.out.print("UP\n");
    }

    public static void up2End() {
        while (by > 0) {
            up();
        }
    }

    public static void down() {
        by++;
        System.out.print("DOWN\n");
    }

    public static void down2End() {
        while (by < N - 1)
            down();
    }

    /* Tail starts here */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m;
        m = in.nextInt();
        String grid[] = new String[m];
        for (int i = 0; i < m; i++) {
            grid[i] = in.next();
        }

        displayPathtoPrincess(m, grid);
    }
}