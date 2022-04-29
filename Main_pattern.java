public static int orient = 0;

    public static boolean directionalSearch(char[][] crossword, int xIdx, int yIdx, String target) {
        int len = target.length();
        int[] horiz = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] vert = {0, 1, 1, 1, 0, -1, -1, -1};
        if (crossword[xIdx][yIdx] != target.charAt(0)) {
            return false;
        }
        for (orient = 0; orient < 8; orient++) {
            int k = 0;
            int xOrient = xIdx + horiz[orient];
            int yOrient = yIdx + vert[orient];
            for (k = 1; k < len; k++) {
                if (xOrient >= crossword.length || xOrient < 0 || yOrient >= crossword.length || yOrient < 0) {
                    break;
                }
                if (crossword[xOrient][yOrient] != target.charAt(k)) {
                    break;
                }
                xOrient += horiz[orient];
                yOrient += vert[orient];
                }
                if (k == len) {
                    return true;
                }
            }
            return false;
        }

    public static int[] find(final char[][] crossword, final String target) {
        int [] answer = new int[3];
        char[][] doubleUp = new char[crossword.length * 2][crossword.length * 2];
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword.length; j ++) {
                doubleUp[i][j] = crossword[i][j];
                doubleUp[i][crossword.length + j] = crossword[i][j];
                doubleUp[crossword.length + i][j] = crossword[i][j];
                doubleUp[crossword.length + i][crossword.length + j] = crossword[i][j];
            }
        }
        for (int row = 0; row < doubleUp.length; row++) {
            for (int col = 0; col < doubleUp.length; col++) {
                if (doubleUp[row][col]== target.charAt(0)  && directionalSearch(doubleUp, row, col, target)) {
                    if (row >= crossword.length ) {
                        row = row - crossword.length;
                    }
                    if (col >= crossword.length) {
                        col = col - crossword.length;
                    }
                    answer[0] = row;
                    answer[1] = col;
                    answer[2] = orient;
                    return answer;
                }
            }
        }
        return new int[]{-1,-1,-1};
    }
