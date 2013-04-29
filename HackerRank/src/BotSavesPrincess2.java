import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BotSavesPrincess2 {
    /* Head ends here */

    static int bx;
    static int by;
    static int px;
    static int py;

    static void nextMove(int n, int x, int y, String[] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i].toCharArray()[j] == 'm') {
                    bx = j;
                    by = i;
                } else if (board[i].toCharArray()[j] == 'p') {
                    px = j;
                    py = i;
                }
            }
        }

        if (bx < px) 
            right();
        else if (bx > px)
            left();
        else if (by < py) 
            down();
        else if (by > py) 
            up();

        
    }

    public static void right() {
        bx++;
        System.out.print("RIGHT\n");
    }

    public static void left() {
        bx--;
        System.out.print("LEFT\n");
    }

    public static void up() {
        by--;
        System.out.print("UP\n");
    }

    public static void down() {
        by++;
        System.out.print("DOWN\n");
    }

    /* Tail starts here */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n, x, y;
        n = in.nextInt();
        x = in.nextInt();
        y = in.nextInt();
        in.useDelimiter("\n");
        String board[] = new String[n];

        for (int i = 0; i < n; i++) {
            board[i] = in.next();
        }

        nextMove(n, x, y, board);

    }
}