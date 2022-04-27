public class Main_pattern {

    /**
     * Finds a word in a matrix.
     * @param crossword
     * @param target
     * @return an array of length 3.
     */


    public static int[] find(final char[][] crossword, final String target) {

        boolean found = false;

        int[] answer = new int[3];

        for (int i = 0; i < crossword.length; i++) {//row

            StringBuilder sbrow = new StringBuilder();
            StringBuilder sbrowreversed = new StringBuilder();

            char[] row = crossword[i];

//            System.out.println(row);

            sbrow.append(row);
            sbrow.append(row);

//            System.out.println(sbrow);

            int checkrow = KMP.find(String.valueOf(sbrow), target);
            System.out.println(checkrow);

            if (checkrow == -1) {

                sbrowreversed.append(sbrow);
//                sbrowreversed.append(row);

                sbrowreversed.reverse();
//                System.out.println("before reversing: " + sbrow);
//                System.out.println(sbrowreversed);

                int checkrowrev = KMP.find(String.valueOf(sbrowreversed), target);

                if (checkrowrev == -1) {
                    continue;
                } else {
                    found = true;
                    answer[0] = i;
                    answer[1] = checkrowrev;
                    answer[2] = 6;
                    break;
                }
                //reversed bottom to top
            } else {
                found = true;
                answer[0] = i;
                answer[1] = checkrow;
                answer[2] = 2;
                break;
            }

//            char[] this_row = crossword[i];
//            char diagonal = crossword[i][i];
//
//            char anti_diagonal = crossword[i][crossword.length - i - 1];

//                if (checkrow == -1 || checkcol == -1 || checkdiag == -1) {
//                    continue;
//                } else {
//                    answer[0] = i;
//                    answer[1] = j;
//                   // answer[2] = bruh;
//                }


        }

        //row side looks fine

        for (int j = 0; j < crossword.length; j++) {

            StringBuilder sbcol = new StringBuilder();
            StringBuilder sbcolreversed = new StringBuilder();

            char[] col = getColumn(crossword, j);

//            System.out.println(col);

            sbcol.append(col);
            sbcol.append(col);

            int checkcol = KMP.find(String.valueOf(sbcol), target);

            if (checkcol == -1) {

                sbcolreversed.append(sbcol);
//                sbcolreversed.append(col);

                sbcolreversed.reverse();

//                System.out.println("before reversing: " + sbcol);

//                System.out.println(sbcolreversed);

                int checkcolrev = KMP.find(String.valueOf(sbcolreversed), target);
//                System.out.println("checkcolrev: " + checkcolrev);

                if (checkcolrev == -1) {
                    continue;
                } else {
                    found = true;
                    answer[0] = checkcolrev;
                    answer[1] = j;
                    answer[2] = 4;
                    break;
                }
                //reversed bottom to top
            } else {
                found = true;
                answer[0] = checkcol;
                answer[1] = j;
                answer[2] = 0;
                break;
            }


        }//column


        int count = 0;
        for (int r = 0; r < crossword.length; r++) {

                StringBuilder sbdiag = new StringBuilder();
                StringBuilder sbdiagreversed = new StringBuilder();
                int l = 0;

                StringBuilder sbrow = new StringBuilder();

            for(int k = count; k < crossword.length * 2 ; k++) {
                for( int j = 0 ; j <= k ; j++ ) {
                    int i = k - j;
                    if( i < crossword.length && j < crossword.length ) {
                        sbdiag.append(crossword[i][j]);
//                        System.out.println("sbdiag is " + sbdiag);

                    }

                }
                break;
//                System.out.println("sbdiag is " + sbdiag);
//                System.out.println("hi");
            }

//                for(int i = k; i < k; i++) {
//                    sbrow.append(crossword[k][l]);
//                }
//                char column = crossword[l][k];
//
//                l++;
//            char[] diag = getDiagonal(crossword, k);

//            System.out.println(loopDiagonally(crossword));
//            System.out.println(diag);

//            char diagonal = crossword[k][k];

//            System.out.println(col);
//                sbdiag.append(row);
//                sbdiag.append(column);

                System.out.println(sbdiag);

                int checkdiag = KMP.find(String.valueOf(sbdiag), target);

                if (checkdiag == -1) {

//                System.out.println("Before reversing: " + sbdiag);
                    sbdiagreversed.append(sbdiag);

                    sbdiagreversed.reverse();

//                System.out.println(sbdiagreversed);

//                System.out.println("before reversing: " + sbcol);

//                System.out.println(sbcolreversed);

                    int checkdiagrev = KMP.find(String.valueOf(sbdiagreversed), target);
//                System.out.println("checkcolrev: " + checkcolrev);

                    if (checkdiagrev == -1) {
                        continue;
                    } else {
                        found = true;
//                    answer[0] = checkcolrev;
//                    answer[1] = j;
//                    answer[2] = 4;
                        break;
                    }
                    //reversed bottom to top
                } else {
                    found = true;
//                answer[0] = checkcol;
//                answer[1] = j;
//                answer[2] = 0;
                    break;
                }
        }//column

        //column looks fine



        if(found == false){
            return new int[]{-1, -1, -1};
        }

        return answer;


    }
    public static char[] getColumn ( char[][] crossword, int column){
        char[] column_array = new char[crossword.length];

        for (int i = 0; i < crossword.length; i++) {
            column_array[i] = crossword[i][column];
        }

        return column_array;
    }

    public static char[] getDiagonal(char[][] crossword, int i) {

        char[] diagonal_array = new char[crossword.length];

        for (int j = i; j < crossword.length; j++) {
            diagonal_array[j] = crossword[j][j];
        }
        return diagonal_array;
    }

    public static String loopDiagonally(char[][] twoDArray) {

        int length = twoDArray.length;
        int diagonalLines = (length + length) - 1;
        int itemsInDiagonal = 0;
        int midPoint = (diagonalLines / 2) + 1;
        StringBuilder output = new StringBuilder();

        for (int i = 1; i <= diagonalLines; i++) {

            StringBuilder items = new StringBuilder();
            int rowIndex;
            int columnIndex;

            if (i <= midPoint) {
                itemsInDiagonal++;
                for (int j = 0; j < itemsInDiagonal; j++) {
                    rowIndex = (i - j) - 1;
                    columnIndex = j;
                    items.append(twoDArray[rowIndex][columnIndex]);
                }
            } else {
                itemsInDiagonal--;
                for (int j = 0; j < itemsInDiagonal; j++) {
                    rowIndex = (length - 1) - j;
                    columnIndex = (i - length) + j;
                    items.append(twoDArray[rowIndex][columnIndex]);
                }
            }

//            if (i != diagonalLines) {
//                output.append(items).append(" ");
//            } else {
//                output.append(items);
//            }
        }

        return output.toString();
    }


    
}