package conway_gol;

public class ConwayLife {

    public static int[][] getGeneration(int[][] cells, int generations) {
        for (int generation = 0; generation < generations; generation++) {
            cells = cycle(cells);
        }
        return cells;
    }

    private static int[][] cycle(int[][] cells) {
        int[][] newCells = extend(cells);

        for (int x = 0; x < newCells.length; x++) {
            for (int y = 0; y < newCells[0].length; y++) {
                newCells[x][y] = applyRules(cells, x - 1, y - 1);
            }
        }

        return trim(newCells);
    }

    private static int applyRules(final int[][] cells, int x, int y) {
        boolean alive = !outOfBounds(cells, x, y) && cells[x][y] == 1;
        int neighbors = countNeighbors(cells, x, y);

        if (alive && (neighbors < 2 || neighbors > 3)) return 0;
        if (!alive && neighbors == 3) return 1;
        return alive ? 1 : 0;
    }

    private static int countNeighbors(final int[][] cells, int x, int y) {
        int count = 0;
        for (int xn = -1; xn <= 1; xn++) {
            for (int yn = -1; yn <= 1; yn++) {
                if (xn == 0 && yn == 0 || outOfBounds(cells, x + xn, y + yn)) continue;
                count += cells[x + xn][y + yn];
            }
        }
        return count;
    }

    private static boolean outOfBounds(final int[][] cells, int x, int y) {
        return x < 0 || x >= cells.length || y < 0 || y >= cells[0].length;
    }

    private static int[][] extend(final int[][] cells) {
        int[][] newCells = new int[cells.length + 2][cells[0].length + 2];
        for (int x = 0; x < cells.length; x++)
            System.arraycopy(cells[x], 0, newCells[x + 1], 1, cells[x].length);
        return newCells;
    }

    private static int[][] trim(final int[][] cells) {
        int minX = Math.max(0, findTrimSize(cells, true, true));
        int maxX = Math.max(0, findTrimSize(cells, true, false));
        int minY = Math.max(0, findTrimSize(cells, false, true));
        int maxY = Math.max(0, findTrimSize(cells, false, false));

        int newHeight = cells.length - minX - maxX;
        int newWidth = cells[0].length - minY - maxY;
        int[][] newCells = new int[newHeight][newWidth];

        for (int x = 0; x < newHeight; x++) {
            System.arraycopy(cells[x + minX], minY, newCells[x], 0, newWidth);
        }
        return newCells;
    }

    private static int findTrimSize(int[][] cells, boolean isRow, boolean start) {
        int limit = isRow ? cells.length : cells[0].length;
        int dir = start ? 1 : -1;
        int startIdx = start ? 0 : limit - 1;

        for (int i = startIdx; (start && i < limit) || (!start && i >= 0); i += dir) {
            for (int j = 0; j < (isRow ? cells[0].length : cells.length); j++) {
                if (cells[isRow ? i : j][isRow ? j : i] == 1) {
                    return start ? i : limit - i - 1;
                }
            }
        }
        return limit;
    }
}