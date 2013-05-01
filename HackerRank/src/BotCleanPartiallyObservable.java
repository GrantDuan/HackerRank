import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.regex.*;
import java.nio.file.*;

public class BotCleanPartiallyObservable {

    /* Head ends here */
    static int bx;
    static int by;
    static int dx;
    static int dy;
    static int ox;
    static int oy;
    static String[] grid;
    static boolean found;
    static ArrayList<Coordinate> path;

    static void next_move(int posx, int posy, String[] board) {
        bx = posx;
        by = posy;
        grid = board;
        loadPath();
        Coordinate co = new Coordinate(bx, by);
        if (!path.contains(co))
            path.add(co);

        if (grid[bx].toCharArray()[by] == 'd') {
            clean();
        } else {
            findDirty();

            if (found) {
                if (bx < dx)
                    down();
                else if (bx > dx)
                    up();
                else if (by < dy)
                    right();
                else if (by > dy)
                    left();
            } else {
                find('o');
                if (bx < ox)
                    down();
                else if (bx > ox)
                    up();
                else if (by < oy)
                    right();
                else if (by > oy)
                    left();

                /*
                 * if (by > 0 && !path.contains(new Coordinate(bx, by - 1)))
                 * left(); else if (bx < 4 && !path.contains(new Coordinate(bx +
                 * 1, by))) down(); else if (by < 4 && !path.contains(new
                 * Coordinate(bx, by + 1))) right(); else if (bx > 0 &&
                 * !path.contains(new Coordinate(bx - 1, by))) up();
                 */

            }
        }

        savePath();
    }

    private static void loadPath() {
        Scanner s = null;
        if (path == null) {
            path = new ArrayList<Coordinate>();
        }

        try {

            s = new Scanner(new BufferedReader(new FileReader("path.txt")));
            s.useLocale(Locale.US);

            while (s.hasNext()) {
                if (s.hasNextInt()) {
                    int x = s.nextInt(); 
                    int y = s.nextInt();
                    Coordinate c = new Coordinate(x, y);
                    path.add(c);
                } else {
                    s.next();
                }
            }
        } catch (Exception e) {

        } finally {
            if (s != null)
                s.close();
        }

    }

    private static void savePath() {
        String pathString = "";
        for (Coordinate c : path) {
            pathString += c.x + " " + c.y + "\n";
        }
        File fileName = new File("path.txt");
        if (!fileName.exists()) {
            try {
                fileName.createNewFile();
                FileWriter fileWrite = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);
                bufferedWriter.write(pathString);
                bufferedWriter.close();
            } catch (IOException e) {
                // catch exception
            }
        }
    }

    // only search adjacent cells
    public static void findDirty() {
        found = false;
        for (int x = bx - 1; x <= bx + 1; x++) {
            if (x >= 0 && x < 5) {
                for (int y = by - 1; y <= by + 1; y++) {
                    if (y >= 0 && y < 5) {

                        if (grid[x].toCharArray()[y] == 'd') {
                            if (Math.abs(bx - x) + Math.abs(by - y) == 1) {
                                dx = x;
                                dy = y;
                                found = true;
                                return;
                            } else {
                                found = true;
                                dx = x;
                                dy = y;
                            }
                        } else if (grid[x].toCharArray()[y] == '-') {

                            path.add(new Coordinate(x, y));
                        }
                    }
                }
            }
        }
    }

    public static void find(char c) {
        int r = 1;
        found = false;
        while (!found && r < 5) {
            found = find(r++, c);
        }
    }

    public static boolean find(int n, char c) {

        for (int x = bx - n; x <= bx + n; x++) {
            if (x >= 0 && x < 5) {
                for (int y = by - n; y <= by + n; y++) {
                    if (y >= 0 && y < 5) {
                        if (Math.abs(bx - x) + Math.abs(by - y) == n) {
                            if (grid[x].toCharArray()[y] == c
                                    && !path.contains(new Coordinate(x, y))) {
                                ox = x;
                                oy = y;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void right() {
        bx++;
        path.add(new Coordinate(bx, by));
        System.out.print("RIGHT\n");
    }

    public static void left() {
        bx--;
        path.add(new Coordinate(bx, by));
        System.out.print("LEFT\n");
    }

    public static void up() {
        by--;
        path.add(new Coordinate(bx, by));
        System.out.print("UP\n");
    }

    public static void down() {
        by++;
        path.add(new Coordinate(bx, by));
        System.out.print("DOWN\n");
    }

    public static void clean() {
        System.out.print("CLEAN\n");
    }

    private static class Coordinate {
        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object that) {
            if (that == null)
                return false;
            if (that.getClass() != this.getClass())
                return false;
            return this.x == ((Coordinate) that).x
                    && this.y == ((Coordinate) that).y;
        }
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
