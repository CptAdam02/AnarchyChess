package com.company;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

public class Main {

    //main
    public static void main(String[] args) {
        /*                      definitions                                          */
        //scanner
        Scanner sc = new Scanner(System.in);
        //arrays
        String[][] chess_board = new String[8][8];
        chessPiece[][] chessBoard = new chessPiece[8][8];
        chessPiece[][] newChessBoard = new chessPiece[8][8];

        //booleans
        boolean checkmate = true;
        //integers
        int playerTurn = 1;
        int gameMode ;
        int i = 0;
        //lists
        List<String> Moves = new ArrayList<String>();

        //setup board
        chessBoard = testSetup(newChessBoard);

        do{
            //checking valid moves
            Moves.addAll(findMoves(chessBoard, playerTurn));
            if(Moves.size() == 0){
                i = 100;
            }else{
                chessBoard = moveApplication(chessBoard, moveDecide(Moves));
            }
            Moves.clear();
            printBoard(chessBoard);

            //selecting and performing the move

            //checking conditions




            if (playerTurn == 1 ){
                playerTurn= 2;
            }else {
                playerTurn = 1;
            }
            i++;
        }
        while(i <100);
    }

    /*      to do
    en passant
    promotion







    */
    public static chessPiece[][] testSetup (chessPiece[][] chessBoard){
        for (int x = 0; x < chessBoard.length; x ++){
            for (int y = 0; y < chessBoard.length; y ++){
                chessBoard[y][x] = new chessPiece(".",0,". ", y , x);
            }
        }
        chessBoard[0][4] = new chessPiece("queen",1 , "q1",0, 1);
        chessBoard[7][4] = new chessPiece("queen",2 , "q2",0, 1);
        printBoard(chessBoard);
        return chessBoard;
    }

    public static void printBoard (chessPiece[][] chessBoard ){
        for (chessPiece[] chessPieces : chessBoard) {
            for (int y = 0; y < chessBoard.length; y++) {
                System.out.print(chessPieces[y]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static List<String> findMoves (chessPiece[][] chessBoard, int playerTurn){
        List<String> Moves = new ArrayList<String>();
        for (int x = 0; x < chessBoard.length; x++){
            for (int y = 0; y < chessBoard.length; y++){
                if (chessBoard[y][x].getColour() == playerTurn){
                    switch (chessBoard[y][x].getPieceType()) {
                        case "pawn" -> Moves.addAll(pawnCheck(chessBoard, playerTurn, y, x));
                        case "rook" -> Moves.addAll(rookCheck(chessBoard, playerTurn, y, x));
                        case "knight" -> Moves.addAll(knightCheck(chessBoard, playerTurn, y, x));
                        case "bishop" -> Moves.addAll(bishopCheck(chessBoard, playerTurn, y, x));
                        case "king" -> Moves.addAll(kingCheck(chessBoard, playerTurn, y, x));
                        case "queen" -> Moves.addAll(queenCheck(chessBoard, playerTurn, y, x));
                        default -> System.out.println("default");
                    }
                }
            }
        }
        return Moves;
    }

    public static List<String> pawnCheck(chessPiece[][] chessBoard, int playerTurn, int y, int x){
        List<String> Moves = new ArrayList<>();
        if (playerTurn == 1){
            if ((y<7) && (Objects.equals(chessBoard[y + 1][x].getPieceId(), ". "))){
                Moves.add(String.valueOf(y + 1) + String.valueOf(x) + String.valueOf(y) + String.valueOf(x));
                if ((y < 6) && (Objects.equals(chessBoard[y + 2][x].getPieceId(), ". ")) && (y == 1)){
                    Moves.add(String.valueOf(y + 2) + String.valueOf(x) + String.valueOf(y) + String.valueOf(x));
                }
            }
            if ((y<7) && (x!=0) && (Objects.equals(chessBoard[y + 1][x - 1].getPieceId(), "p2"))) {
                Moves.add(String.valueOf(y + 1) + String.valueOf(x - 1) + String.valueOf(y) + String.valueOf(x));
            }
            if ((y<7) && (x!=7) && (Objects.equals(chessBoard[y + 1][x + 1].getPieceId(), "p2" ))) {
                Moves.add(String.valueOf(y + 1) + String.valueOf(x + 1) + String.valueOf(y) + String.valueOf(x));
            }

        }else if (playerTurn == 2 ){
            if ((y>0) && (Objects.equals(chessBoard[y - 1][x].getPieceId(), ". "))){
                Moves.add(String.valueOf(y - 1) + String.valueOf(x) + String.valueOf(y) + String.valueOf(x));
                if ((y > 1 ) && (Objects.equals(chessBoard[y - 2][x].getPieceId(), ". ")) && (y == 6)){
                    Moves.add(String.valueOf(y - 2) + String.valueOf(x) + String.valueOf(y) + String.valueOf(x));
                }
            }
            if ((y>0) && (x!=0) && (Objects.equals(chessBoard[y - 1][x - 1].getPieceId(), "p1"))) {
                Moves.add(String.valueOf(y - 1) + String.valueOf(x - 1) + String.valueOf(y) + String.valueOf(x));
            }
            if ((y>0) && (x!=7) && (Objects.equals(chessBoard[y - 1][x + 1].getPieceId(), "p1"))) {
                Moves.add(String.valueOf(y - 1) + String.valueOf(x + 1) + String.valueOf(y) + String.valueOf(x));
            }
        }

        return Moves ;
    }

    public static List<String> rookCheck(chessPiece[][] chessBoard, int playerTurn, int y, int x){
        List<String> Moves = new ArrayList<>();
        for (int i= -1;i<2;i++){
            for ( int j =-1;j<2;j++){
                if ((i!=j) && (i!=-j)){
                   Moves.addAll(directionCheckFlat(chessBoard,playerTurn,x,y,j,i));
                }
            }
        }
        return Moves ;
    }

    public static List<String> knightCheck(chessPiece[][] chessBoard, int playerTurn, int y, int x ){
        List<String> Moves = new ArrayList<>();
        for(int i = -2; i < 3;i++ ) {
            for (int j = -2; j < 3; j++) {
                if ((!(i == j)) && (!(i == -j)) && (i!=0) && (j!=0)) {
                    if ((-1 < x + i && x + i < 8) && (-1 < y + j && y + j < 8)) {

                        if (playerTurn == 1) {
                            switch (chessBoard[y + j][x + i].getPieceId()) {

                                case ". ", "k2","p2" -> Moves.add(String.valueOf(y + j) + String.valueOf(x + i) + String.valueOf(y) + String.valueOf(x));
                            }
                        } else if (playerTurn == 2) {
                            switch (chessBoard[y + j][x + i].getPieceId()) {
                                case ". ", "k1","p1" -> Moves.add(String.valueOf(y + j) + String.valueOf(x + i) + String.valueOf(y) + String.valueOf(x));
                            }
                        }
                    }
                }
            }
        }
        return Moves;
    }

    public static List<String> bishopCheck(chessPiece[][] chessBoard, int playerTurn, int y, int x ){
        List<String> Moves = new ArrayList<>();
        for(int i = -1; i < 2;i++ ) {
            for (int j = -1; j < 2;j++) {
                if (((i==j) || (i==-j))&&((i!=0))){
                    Moves.addAll(directionCheckDiagonal(chessBoard,playerTurn,x,y,j,i));
                }
            }
        }
        return Moves;
    }

    public static List<String> kingCheck(chessPiece[][] chessBoard, int playerTurn, int y, int x ){
        List<String> Moves = new ArrayList<>();
        for(int i = -1; i < 2;i++ ) {
            for (int j = -1; j < 2;j++) {
                if (((i==j) || (i==-j))&&((i!=0))){
                    Moves.addAll(directionCheckDiagonal(chessBoard,playerTurn,x,y,j,i));
                }
            }
        }
        return Moves;
    }

    public static List<String> queenCheck(chessPiece[][] chessBoard, int playerTurn, int y, int x ){
        List<String> Moves = new ArrayList<>();
        for(int i = -1; i < 2;i++ ) {
            for (int j = -1; j < 2;j++) {
                if (((i==j) || (i==-j))&&((i!=0))){
                    Moves.addAll(directionCheckDiagonal(chessBoard,playerTurn,x,y,j,i));
                } else if (i != j){
                    Moves.addAll(directionCheckFlat(chessBoard,playerTurn,x,y,j,i));
                }
            }
        }
        return Moves;
    }



    public static List<String> directionCheckFlat(chessPiece[][] chessBoard,int playerTurn,int x,int y,int i,int j){
        List<String> Moves = new ArrayList<>();
        boolean cont = true;
        while((cont) && ((-1<x+i)&&(x+i<8)) && ((-1<y+j)&&(y+j<8)) ){
            if (playerTurn == 1){
                switch (chessBoard[y + j][x + i].getPieceId()) {
                    case ". " -> {
                        Moves.add(String.valueOf(y + j) + String.valueOf(x + i) + String.valueOf(y) + String.valueOf(x));

                    }
                    case "k2", "p2", "r2" -> {
                        Moves.add(String.valueOf(y + j) + String.valueOf(x + i) + String.valueOf(y) + String.valueOf(x));
                        cont = false;

                    }
                    case "k1", "p1", "r1" -> {
                        cont = false;
                    }
                }
            }
            
            else if (playerTurn ==2){
                switch (chessBoard[y + j][x + i].getPieceId()) {
                    case ". " -> {
                        Moves.add(String.valueOf(y + j) + String.valueOf(x + i) + String.valueOf(y) + String.valueOf(x));
                    }
                    case "k1", "p1", "r1" -> {
                        Moves.add(String.valueOf(y + j) + String.valueOf(x + i) + String.valueOf(y) + String.valueOf(x));
                        cont = false;
                    }
                    case "k2", "p2", "r2" -> {
                        cont = false;
                    }
                }
            }
            if (i == 0 ){
                j++;
            }
            else if (j ==0){
                i++;
            }
        }
        return Moves ;
    }
    public static List<String> directionCheckDiagonal(chessPiece[][] chessBoard,int playerTurn,int x,int y,int i,int j){
        List<String> Moves = new ArrayList<>();
        boolean cont = true;

        while((cont) && ((-1<x+i)&&(x+i<8)) && ((-1<y+j)&&(y+j<8)) ){
            if (playerTurn == 1){
                switch (chessBoard[y + j][x + i].getPieceId()) {
                    case ". " -> {
                        Moves.add(String.valueOf(y + j) + String.valueOf(x + i) + String.valueOf(y) + String.valueOf(x));
                    }
                    case "k2", "p2", "r2","b2" -> {
                        Moves.add(String.valueOf(y + j) + String.valueOf(x + i) + String.valueOf(y) + String.valueOf(x));
                        cont = false;
                    }
                    case "k1", "p1", "r1","b1" -> {
                        cont = false;
                    }
                }
            }else if (playerTurn ==2){
                switch (chessBoard[y + j][x + i].getPieceId()) {
                    case ". " -> {
                        Moves.add(String.valueOf(y + j) + String.valueOf(x + i) + String.valueOf(y) + String.valueOf(x));
                    }
                    case "k1", "p1", "r1","b1" -> {
                        Moves.add(String.valueOf(y + j) + String.valueOf(x + i) + String.valueOf(y) + String.valueOf(x));
                        cont = false;
                    }
                    case "k2", "p2", "r2","b2" -> {
                        cont = false;
                    }
                }
            }
            
            if (i > 0 ){
                i++;
            }
            else if (i<0){
                i--;
            }
            if (j > 0){
                j++;
            }
            else if (j < 0){
                j--;
            }
        }
        return Moves ;
    }

    public static String moveDecide(List<String> Moves){
        Random rand = new Random();
        return Moves.get(rand.nextInt(Moves.size()));
    }

    public static chessPiece[][] moveApplication(chessPiece[][] chessBoard, String move){
        System.out.println(move);
        chessBoard[Integer.parseInt(move.substring(0,1))][Integer.parseInt(move.substring(1,2))] = chessBoard[Integer.parseInt(move.substring(2,3))][Integer.parseInt(move.substring(3,4))];
        chessBoard[Integer.parseInt(move.substring(2,3))][Integer.parseInt(move.substring(3,4))] = new chessPiece(". ",0,". ", Integer.parseInt(move.substring(2,3)) , Integer.parseInt(move.substring(3,4)));
        return chessBoard;
    }



































































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



    public static void tab(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
}
