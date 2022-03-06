package com.company;

public class chessPiece {
    String pieceType;
    String pieceId;
    int colour;
    int y;
    int x;


    public chessPiece(String pieceType,int colour,String pieceId,int y,int x){
        this.pieceType = pieceType;
        this.colour = colour;
        this.pieceId = pieceId;
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return pieceId + " ";
    }

    public int getColour(){
        return this.colour;
    }
    
    public String getPieceId(){
        return this.pieceId;
    }
    
    public String getPieceType(){
        return this.pieceType;
    }
}
