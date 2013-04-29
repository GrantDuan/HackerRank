import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BotClean {

    /* Head ends here */

    static int bx;
    static int by;
    static int dx;
    static int dy;
    static boolean found;
    static String[] grid;

    static void next_move(int posx, int posy, String[] board) {
        bx = posx;
        by = posy;
        grid = board;
        if (grid[bx].toCharArray()[by] == 'd') {
            clean();
        } else {
            findDirty();

            if (bx < dx)
                down();
            else if (bx > dx)
                up();
            else if (by < dy)
                right();
            else if (by > dy)
                left();
        }
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

    public static void clean() {
        System.out.print("CLEAN\n");
    }

    public static void findDirty() {
        int r = 1;
        found = false;
        while (!found && r < 5) {
            found = findDirty(r++);
        }
    }

    public static boolean findDirty(int n) {        

        for (int x = bx - n; x <= bx + n; x++) {
            if (x >= 0 && x < 5) {
                for (int y = by - n; y <= by + n; y++) {
                    if (y >= 0 && y < 5) {
                        if (Math.abs(bx - x) + Math.abs(by - y) == n) {
                            if (grid[x].toCharArray()[y] == 'd') {
                                dx = x;
                                dy = y;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /* Tail starts here */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] pos = new int[2];
        String board[] = new String[5];
        for (int i = 0; i < 2; i++)
            pos[i] = in.nextInt();
        for (int i = 0; i < 5; i++)
            board[i] = in.next();
        next_move(pos[0], pos[1], board);
    }
}
