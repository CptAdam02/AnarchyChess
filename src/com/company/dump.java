package com.company;

public class dump {
    //removed code for a new form of movement
    /*
    if (playerTurn == 1){
                switch (chessBoard[y + j][x + i].getPieceId()) {
                    case ". " -> Moves.add(moveCode);
                    case "p2","r2","n2","b2","q2" -> {
                        Moves.add(moveCode);
                        cont = false;
                    }
                    case "p1","r1","n1","b1","q1" -> cont = false;

                }
            }
            else if (playerTurn ==2){
                switch (chessBoard[y + j][x + i].getPieceId()) {
                    case ". " -> Moves.add(moveCode);
                    case "p1","r1","n1","b1","q1" -> {
                        Moves.add(moveCode);
                        cont = false;
                    }
                    case "p2","r2","n2","b2","q2" -> cont = false;
                }
            }





             if (playerTurn == 1){
                switch (chessBoard[y + j][x + i].getPieceId()) {
                    case ". " -> Moves.add(moveCode);
                    case "p2","r2","n2","b2","q2" -> {
                        Moves.add(moveCode);
                        cont = false;
                    }
                    case "p1","r1","n1","b1","q1" -> cont = false;
                }
            }else if (playerTurn ==2){
                switch (chessBoard[y + j][x + i].getPieceId()) {
                    case ". " -> Moves.add(moveCode);
                    case "p1","r1","n1","b1","q1" -> {
                        Moves.add(moveCode);
                        cont = false;
                    }
                    case "p2","r2","n2","b2","q2" -> cont = false;
                }
            }


     */




















    //Variables
    /*
    import java.util.Scanner;
    Scanner sc = new Scanner(System.in);
    String[][] chess_board = new String[8][8];
    boolean checkmate = true;
    int gameMode ;

    */


    //setup(chess_board);

    //USER MODE
    /*
    //String
    String userInput;
    */
    /*
        if(gameMode == 0 ){



        }else if(gameMode == 1){



            //program starts here

            //turn start


            //user input && validition of input

            do {
                System.out.println("");
                System.out.println("Please enter a valid move:");
                userInput = sc.nextLine();
            }
            while (!inputValidation(userInput));

            //vlaidation of move -> performing move -> outputing move (maybe)

            movevalidation(chess_board,userInput,turnCount);

            // checking the board its self

            //turn end





        }
        */
    //SETTING UP KNIGHTS AND PAWNS
    /*
    if (y == 1){
        chessBoard[1][x] = new chessPiece("pawn",1,"p1", 1, 0 );
    }else if(y == 6){
        chessBoard[6][x] = new chessPiece("pawn",2,"p2", 6, 0 );
    }


    chessBoard[0][1] = new chessPiece("knight",1 , "n1",0, 1);
    chessBoard[0][6] = new chessPiece("knight",1 , "n1",0, 1);
    chessBoard[7][1] = new chessPiece("knight",2 , "n2",0, 1);
    chessBoard[7][6] = new chessPiece("knight",2 , "n2",0, 1);



        chessBoard[0][0] = new chessPiece("rook",1 , "r1",0, 1);
        chessBoard[0][7] = new chessPiece("rook",1 , "r1",0, 1);
        chessBoard[7][0] = new chessPiece("rook",2 , "r2",0, 1);
        chessBoard[7][7] = new chessPiece("rook",2 , "r2",0, 1);


        chessBoard[0][2] = new chessPiece("bishop",1 , "b1",0, 1);
        chessBoard[0][5] = new chessPiece("bishop",1 , "b1",0, 1);
        chessBoard[7][2] = new chessPiece("bishop",2 , "b2",0, 1);
        chessBoard[7][5] = new chessPiece("bishop",2 , "b2",0, 1);

        chessBoard[0][4] = new chessPiece("queen",1 , "q1",0, 1);
        chessBoard[7][4] = new chessPiece("queen",2 , "q2",0, 1);

        chessBoard[0][3] = new chessPiece("king",1 , "k1",0, 1);
        chessBoard[7][3] = new chessPiece("king",2 , "k2",0, 1);


    */
    //KNIGHT MOVEMENT
    /*
    if ((0<=x+3)&&(x+3<8)&&(0<=y+1)&&(y-1<8)){

        }else if ((0<=x-3)&&(x-3<8)&&(0<=y+1)&&(y-1<8))



     */
    //TESTING THE ROOKS
    /*int a = Integer.parseInt(move.substring(0,1));
        int b = Integer.parseInt(move.substring(1,2));
        int c = Integer.parseInt(move.substring(2,3));
        int d = Integer.parseInt(move.substring(3,4));
        if ( (a!=c)&&(b!=d)){
            System.out.println("BUGGGGGGG");
        }

     */

    //User Code

    /*

    //game setup - the set-up of the starting chessboard template
    //assuming white is the bottom of the chess board
    public static String[][] setup(String[][] chess_board){

        // r n b k q b n r   8
        // p p p p p p p p   7
        //                   6
        //                   5
        //                   4
        //                   3
        // p p p p p p p p   2
        // r n b k q b n r   1

        // a b c d e f g h

        for (int x = 0; x < chess_board.length ; x++){
            for (int y = 0; y < chess_board.length ; y++) {
                if (( x == 0) || (x == 7 )){
                    if (( y == 0 ) || ( y == 7 )){
                        chess_board[x][y] = "r ";
                    }else if (( y == 1 ) || ( y == 6 )){
                        chess_board[x][y] = "n ";
                    }else if (( y == 2 ) || ( y == 5 )){
                        chess_board[x][y] = "b ";
                    }else if ( y == 3 ){
                        chess_board[x][y] = "k ";
                    }else{
                        chess_board[x][y] = "q ";
                    }
                } else if (( x == 1) || (x == 6 )){
                    chess_board[x][y] = "p ";
                }else{
                    chess_board[x][y] = " ";
                }
            }
        }
        //printBoard(chess_board);
        return chess_board;
    }

    //simple print all statement
    /*
    public static void printBoard(String[][] chess_board){
        for (String[] strings : chess_board) {
            System.out.println("");
            for (int y = 0; y < chess_board.length; y++) {
                System.out.print(strings[y]);
            }
        }
    }
    */
    /*
    public static boolean inputValidation(String userInput){
        String[] xAxis = {"a","b","c","d","e","f","g","h"};
        if (userInput.length() == 2){
            String char1 = userInput.substring(0,1);
            String char2 = userInput.substring(1,2);
            for (String xAxi : xAxis) {
                for (int j = 1; j < 9; j++) {
                    if (char1.equals(xAxi) && char2.equals(Integer.toString(j))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String[][] movevalidation (String[][] chess_board,String userInput,int playerTurn){
        if (userInput.length() == 2) {
            String char1 = userInput.substring(0, 1);
            String char2 = userInput.substring(1, 2);
            int x = chartoint(char1) ;
            int y = Integer.parseInt(char2);

            if (playerTurn == 0){
                System.out.println("Placeholder");
            }



            return chess_board;
        }
        return chess_board;
    }

    public static int chartoint(String char1){
        return switch (char1) {
            case "a" -> 1;
            case "b" -> 2;
            case "c" -> 3;
            case "d" -> 4;
            case "e" -> 5;
            case "f" -> 6;
            case "g" -> 7;
            case "h" -> 8;
            default -> 0;
        };
    }


*/





    //funny
    /*
    public static void tab(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
     }

     */
}
