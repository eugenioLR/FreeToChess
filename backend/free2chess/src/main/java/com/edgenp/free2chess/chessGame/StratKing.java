/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.chessGame;

/**
 *
 * @author eugeniolr
 */
public class StratKing implements PieceStrat{
    
    private boolean moved;

    /**
     *
     */
    public StratKing() {
        this.moved = false;
    }

    /**
     * Verifica si el movimiento que se quiere realizar es validos
     * @param board
     * @param pos_init
     * @param pos_new
     * @return
     */
    @Override
    public boolean verifyMove(Board board, int[] pos_init, int[] pos_new) {
        int delta_y = pos_new[0] - pos_init[0];
        int delta_x = pos_new[1] - pos_init[1];
        return (Math.abs(delta_x) == 1 || Math.abs(delta_y) == 1) && board.getPiece(pos_init).getColor() != board.getPiece(pos_new).getColor();
    }
    
    /**
     *
     */
    @Override
    public void confirmMove() {
        moved = true;
    }   
    
    /**
     * Obtiene el nombre de la pieza 
     * @return
     */
    @Override
    public char getName() {
        return 'K';
    }
    
    @Override
    public boolean canMove(Board board, int[] posInit) {
        boolean isSafe = false;
        int[] newPos;
        for(int i = -1; i <= 1 && !isSafe; i++){
            for(int j = -1; j <= 1 && !isSafe; j++){
                newPos = posInit;
                newPos[0] += i;
                newPos[1] += j;
                if(i != 0 || j != 0){
                    isSafe = this.isInDanger(board, board.getPiece(posInit).getColor(), newPos);
                }
            }
        }
        return isSafe;
    }
    
    private boolean isInDangerDiagonal(Board board, int color, int[] posInit){
        boolean inDanger = false;
        int oponent = color == 0 ? 1 : 0;
        int[][] offsets = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        int[] auxPos;
        
        for(int i = 0; i < offsets.length && !inDanger; i++){
            auxPos = posInit;
            do{
                auxPos[0] += offsets[i][0];
                auxPos[1] += offsets[i][1];
            }while(board.getPiece(auxPos).getColor() == -1);
            
            if(board.getPiece(auxPos).getColor() == oponent){
                inDanger = board.getPiece(auxPos).getType() == 'B' || board.getPiece(auxPos).getType() == 'Q';
            }
        }
        
        return inDanger;
    }
    
    private boolean isInDangerStraight(Board board, int color, int[] posInit){    
        boolean inDanger = false;
        int oponent = color == 0 ? 1 : 0;
        int[][] offsets = {{1, 0}, {0, 1}, {-1, 0}, {0, 1}};
        int[] auxPos;
        
        for(int i = 0; i < offsets.length && !inDanger; i++){
            auxPos = posInit;
            do{
                auxPos[0] += offsets[i][0];
                auxPos[1] += offsets[i][1];
            }while(board.getPiece(auxPos).getColor() == -1);
            
            if(board.getPiece(auxPos).getColor() == oponent){
                inDanger = board.getPiece(auxPos).getType() == 'R' || board.getPiece(auxPos).getType() == 'Q';
            }
        }
        
        return inDanger;
    }
    
    private boolean isInDangerKnight(Board board, int color, int[] posInit){
        boolean inDanger = false;
        int oponent = color == 0 ? 1 : 0;
        int[][] offsets = {{1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {-1, -2}, {-2, -1}, {1, -2}, {2, -1}};
        int[] auxPos = new int[2];
        
        for(int i = 0; i < offsets.length && !inDanger; i++){
            auxPos[0] = posInit[0] + offsets[i][0];
            auxPos[1] = posInit[1] + offsets[i][1];
            inDanger = inDanger || (board.getPiece(auxPos).getColor() == oponent && board.getPiece(auxPos).getType() == 'N');
        }
        
        return inDanger;
    }
    
    private boolean isInDangerPawn(Board board, int color, int[] posInit){
        boolean inDanger = false;
        int oponent = color == 0 ? 1 : 0;
        int[][] offsets = {{1, 1}, {1, -1}};
        int[] auxPos = new int[2];
        
        
        if(color == 0){
            for (int[] offset : offsets) {
                offset[0] = -1;
            }
        }
        
        for(int i = 0; i < offsets.length && !inDanger; i++){
            auxPos[0] = posInit[0] + offsets[i][0];
            auxPos[1] = posInit[1] + offsets[i][1];
            inDanger = inDanger || (board.getPiece(auxPos).getColor() == oponent && board.getPiece(auxPos).getType() == 'N');
        }
        
        return inDanger;
    }    
    
    private boolean isInDanger(Board board, int color, int[] posInit){
        boolean inDanger;
        
        inDanger = isInDangerPawn(board, color, posInit) 
                   || isInDangerKnight(board, color, posInit)
                   || isInDangerStraight(board, color, posInit) 
                   || isInDangerDiagonal(board, color, posInit);
        
        return inDanger;
    }

    @Override
    public boolean isInDanger(Board board, int[] posInit) {
        return this.isInDanger(board, board.getPiece(posInit).getColor(), posInit);
    }   
}
