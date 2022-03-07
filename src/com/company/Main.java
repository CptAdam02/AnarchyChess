package com.company;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.util.Random;

public class Main {

    //main
    public static void main(String[] args) {
        /*                      definitions                                          */
        chessPiece[][] chessBoard;
        chessPiece[][] newChessBoard = new chessPiece[8][8];
        int playerTurn = 1;
        int i = 0;
        List<String> Moves = new ArrayList<>();

        //setup board
        chessBoard = testSetup(newChessBoard);



        do{
            //checking valid moves and selecting and performing the move
            Moves.addAll(findMoves(chessBoard, playerTurn));
            if(Moves.size() == 0){
                i = 100000000;
            }else{
                chessBoard = moveApplication(chessBoard, moveDecide(Moves),playerTurn);
            }
            Moves.clear();
            printBoard(chessBoard);








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
                if (y == 1){
                    chessBoard[1][x] = new chessPiece("pawn",1,"p1", 1, 0 );
                }else if(y == 6){
                    chessBoard[6][x] = new chessPiece("pawn",2,"p2", 6, 0 );
                }
            }
        }

        chessBoard[0][0] = new chessPiece("rook",1 , "r1",0, 1);
        chessBoard[0][7] = new chessPiece("rook",1 , "r1",0, 1);
        chessBoard[7][0] = new chessPiece("rook",2 , "r2",0, 1);
        chessBoard[7][7] = new chessPiece("rook",2 , "r2",0, 1);

        chessBoard[0][1] = new chessPiece("knight",1 , "n1",0, 1);
        chessBoard[0][6] = new chessPiece("knight",1 , "n1",0, 1);
        chessBoard[7][1] = new chessPiece("knight",2 , "n2",0, 1);
        chessBoard[7][6] = new chessPiece("knight",2 , "n2",0, 1);

        chessBoard[0][2] = new chessPiece("bishop",1 , "b1",0, 1);
        chessBoard[0][5] = new chessPiece("bishop",1 , "b1",0, 1);
        chessBoard[7][2] = new chessPiece("bishop",2 , "b2",0, 1);
        chessBoard[7][5] = new chessPiece("bishop",2 , "b2",0, 1);

        chessBoard[0][4] = new chessPiece("queen",1 , "q1",0, 1);
        chessBoard[7][4] = new chessPiece("queen",2 , "q2",0, 1);

        chessBoard[0][3] = new chessPiece("king",1 , "k1",0, 1);
        chessBoard[7][3] = new chessPiece("king",2 , "k2",0, 1);

        chessBoard[0][3] = new chessPiece("king",1 , "k1",0, 1);
        chessBoard[7][3] = new chessPiece("king",2 , "k2",0, 1);

        printBoard(chessBoard);
        return chessBoard;
    }

    public static void printBoard (chessPiece[][] chessBoard ){
        for (chessPiece[] chessPieces : chessBoard) {
            for (int y = 0; y < chessBoard.length; y++) {
                System.out.print(chessPieces[y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static List<String> findMoves (chessPiece[][] chessBoard, int playerTurn){
        List<String> Moves = new ArrayList<>();
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
                Moves.add(String.valueOf(y + 1) + (x) + (y) + (x));
                if ((y < 6) && (Objects.equals(chessBoard[y + 2][x].getPieceId(), ". ")) && (y == 1)){
                    Moves.add(String.valueOf(y + 2) + (x) + (y) + (x));
                }
            }
            if ((y<7) && (x!=0)) {
                switch (chessBoard[y + 1][x - 1].getPieceId()){
                    case "p2","r2","n2","b2","q2" -> Moves.add(String.valueOf(y + 1) + (x - 1) + (y) + (x));
                }
            }
            if ((y<7) && (x!=7)) {
                switch (chessBoard[y + 1][x + 1].getPieceId()){
                    case "p2","r2","n2","b2","q2" -> Moves.add(String.valueOf(y + 1) + (x +1) + (y) + (x));
                }
            }
        }else if (playerTurn == 2 ){
            if ((y>0) && (Objects.equals(chessBoard[y - 1][x].getPieceId(), ". "))){
                Moves.add(String.valueOf(y - 1) + (x) + (y) + (x));
                if ((y > 1 ) && (Objects.equals(chessBoard[y - 2][x].getPieceId(), ". ")) && (y == 6)){
                    Moves.add(String.valueOf(y - 2) + (x) + (y) + (x));
                }
            }
            if ((y>0) && (x!=0)) {
                switch (chessBoard[y - 1][x - 1].getPieceId()){
                    case "p1","r1","n1","b1","q1" -> Moves.add(String.valueOf(y - 1) + (x - 1) + (y) + (x));
                }
            }
            if ((y>0) && (x!=7) && (Objects.equals(chessBoard[y - 1][x + 1].getPieceId(), "p1"))) {
                switch (chessBoard[y - 1][x + 1].getPieceId()){
                    case "p1","r1","n1","b1","q1" -> Moves.add(String.valueOf(y - 1) + (x + 1) + (y) + (x));
                }
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

    public static List<String> knightCheck(chessPiece[][] chessBoard, int playerTurn, int y, int x){
        List<String> Moves = new ArrayList<>();
        for(int i = -2; i < 3;i++ ) {
            for (int j = -2; j < 3; j++) {
                if ((!(i == j)) && (!(i == -j)) && (i!=0) && (j!=0)) {
                    if ((-1 < x + i && x + i < 8) && (-1 < y + j && y + j < 8)) {
                        String moveCode = String.valueOf(y + j) + (x + i) + (y) + (x);
                        if (playerTurn == 1) {
                            switch (chessBoard[y + j][x + i].getPieceId()) {
                                case ". ", "p2","r2","n2","b2","q2" -> Moves.add(moveCode);
                            }
                        } else if (playerTurn == 2) {
                            switch (chessBoard[y + j][x + i].getPieceId()) {
                                case ". ", "p1","r1","n1","b1","q1" -> Moves.add(moveCode);
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
                if((j!=0)&&(i!=0) ){
                    if ((-1 < x + i && x + i < 8) && (-1 < y + j && y + j < 8)) {
                        String moveCode = String.valueOf(y + j) + (x + i) + (y) + (x);
                        if (playerTurn == 1) {
                            switch (chessBoard[y + j][x + i].getPieceId()) {
                                case ". ", "p2","r2","n2","b2","q2" -> Moves.add(moveCode);
                            }
                        } else if (playerTurn == 2) {
                            switch (chessBoard[y + j][x + i].getPieceId()) {
                                case ". ", "p1","r1","n1","b1","q1" -> Moves.add(moveCode);
                            }
                        }
                    }
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
            String moveCode = String.valueOf(y + j) + (x + i) + (y) + (x);
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
            String moveCode = String.valueOf(y + j) + (x + i) + (y) + (x);
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
            if (i>0){
                i++;
            }
            else if (i<0){
                i--;
            }
            if (j>0){
                j++;
            }
            else if (j<0){
                j--;
            }
        }
        return Moves ;
    }

    public static String moveDecide(List<String> Moves){
        Random rand = new Random();
        return Moves.get(rand.nextInt(Moves.size()));
    }

    public static chessPiece[][] moveApplication(chessPiece[][] chessBoard, String move, int playerTurn){
        int finY = Integer.parseInt(move.substring(0,1));
        int finX = Integer.parseInt(move.substring(1,2));
        int inY = Integer.parseInt(move.substring(2,3));
        int inX = Integer.parseInt(move.substring(3,4));
        OutputMove(chessBoard, finY, finX, inY, inX, playerTurn);

        System.out.println(move);
        chessBoard[finY][finX] = chessBoard[inY][inX];
        chessBoard[inY][inX] = new chessPiece(". ",0,". ", inY , inX);
        return chessBoard;
    }

    public static void OutputMove (chessPiece[][] chessBoard ,int finY ,int finX ,int inY ,int inX, int playerTurn){
        String output = "";
        if (playerTurn == 1){
            switch (chessBoard[inY][inX].getPieceId()) {
                case "r1" -> output = output + "r";
                case "n1" -> output = output + "n";
                case "b1" -> output = output + "b";
                case "q1" -> output = output + "q";
                case "k1" -> output = output + "k";
            }
            switch (chessBoard[finY][finX].getPieceId()){
                case "p2","r2","n2","b2","q2" -> output = output + "x";
            }
            output = output + finX;
            output = output + (finY + 1);

        }else if (playerTurn == 2){

            switch (chessBoard[inY][inX].getPieceId()) {
                case "r2" -> output = output + "r";
                case "n2" -> output = output + "n";
                case "b2" -> output = output + "b";
                case "q2" -> output = output + "q";
                case "k2" -> output = output + "k";
            }
            switch (chessBoard[finY][finX].getPieceId()){
                case "p1","r1","n1","b1","q1" -> output = output + "x";
            }
            output = output + finX;
            output = output + (finY + 1);
        }





        System.out.println(output);
    }
































































}
