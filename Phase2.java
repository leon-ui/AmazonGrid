import java.util.Random;

public class Phase2 {
    public static int steps = 0;
    public static void main(String[] args) {
        Random rand = new Random();
        char[][] grid = {
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '0', '0', '0'},
                {'.', '.', '.', '.', '.', '.', '.', '0', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
        };

        System.out.print("[");
        for(int i = 1; i < 21; i++){
            int t;
            int v;
            t = rand.nextInt(10);
            v = rand.nextInt(10);
            if(t==9 && v==9){
                i = i-1;
            } else if(t==0 && v==0){
                i = i-1;
            } else if(grid[t][v] == '0'){
                i = i-1;
            } else if(i == 20) {
                System.out.print("(" + t + "," + v + ")");
            } else {
                grid[t][v] = '0';
                System.out.print("(" + t + "," + v + "), ");
            }
        }
        System.out.print("]");

        print(grid);

        if(traverseGrid(grid,0,0)) {
            System.out.println("]");
            System.out.println(Phase2.steps);
        }

        print(grid);
    }

    private static void print(char[][] grid) {
        System.out.printf("%n%s%n","-----------------------");
        for(int x = 0; x < 10; x++) {
            System.out.print("| ");
            for(int y = 0; y < 10; y++) {
                System.out.print(grid[x][y] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------------------");
    }

    public static boolean isValidPosition(char[][] grid, int r, int c) {
        if(r >= 0 && r < 10 && c >= 0 && c < 10) {
            return grid[r][c] == '.';
            //Checks if the position on the grid is valid
        }
        return false;
        //If position is invalid and outside of the grid, return false
    }

    public static boolean traverseGrid(char[][] grid, int r, int c){
        if(isValidPosition(grid, r, c)){
            //If position is valid and returned true, do if loop
            if(r == 9 && c == 9){
                //If 'vehicle' reaches coordinates (9,9), return true
                System.out.print("[");
                return true;
            }

            grid[r][c] = '!';
            //up
            //Runs traverseGrid function again to validate position and check if delivery point is reached when going up
            boolean move = traverseGrid(grid, r-1, c);

            //down
            if(!move){
                move = traverseGrid(grid, r+1, c);
            }

            //left
            if(!move){
                move = traverseGrid(grid, r, c-1);
            }

            //right
            if(!move){
                move = traverseGrid(grid, r, c+1);
            }

            if(move) {
                if(r==0 && c==0){
                    System.out.print("(" + r + "," + c + ")");
                    Phase2.steps = Phase2.steps + 1;
                } else {
                    System.out.print("(" + r + "," + c + "), ");
                    Phase2.steps = Phase2.steps + 1;
                }
                grid[r][c] = '*';
                //Marks successful path with an asterix
            }

            return move;
        }

        return false;
    }
}
