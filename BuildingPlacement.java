// Problem Optimal Placement of Buildings in a grid
// Time Complexity : O()
// Space Complexity : O()
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.println(buildingPlacement.findMinDistance(4, 5, 3)); // Example test case
    }
}

class BuildingPlacement {
    int H, W, min;

    public int findMinDistance(int h, int w, int n) {
        this.H = h;
        this.W = w;
        this.min = Integer.MAX_VALUE;

        boolean[][] grid = new boolean[H][W];  // Grid for buildings
        backtrack(grid, 0, n);  // Start backtracking to place buildings

        return min;  // Return the minimum max distance found
    }

    private void bfs(boolean[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];

        // Add all buildings to the queue as starting points for BFS
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (grid[i][j]) {  // If there's a building
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int dist = 0;  // To track distance during BFS
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};  // Directions: right, down, left, up

        // Perform BFS from all buildings
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0], c = curr[1];

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nc >= 0 && nr < H && nc < W && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            dist++;  // Increment the distance for the next level of BFS
        }

        dist--;  // Final distance after BFS
        if (dist < min) {
            min = dist;  // Update min distance if this placement yields a better result
        }
    }

    private void backtrack(boolean[][] grid, int idx, int N) {
        // Base case: if all N buildings are placed, calculate the BFS result
        if (N == 0) {
            bfs(grid);  // Run BFS on the current grid configuration
            return;
        }

        // Try placing the building at each available position in the grid
        for (int i = idx; i < H * W; i++) {
            int r = i / W;  // Row in the grid
            int c = i % W;  // Column in the grid

            // Place the building at (r, c)
            grid[r][c] = true;
            backtrack(grid, i + 1, N - 1);  // Recur to place the next building
            grid[r][c] = false;  // Backtrack: remove the building
        }
    }
}
