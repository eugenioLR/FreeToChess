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
     * @param initPos
     * @param newPos
     * @return
     */
    @Override
    public boolean verifyMove(Board board, int[] initPos, int[] newPos) {
        int delta_y = newPos[0] - initPos[0];
        int delta_x = newPos[1] - initPos[1];
        return (Math.abs(delta_x) == 1 || Math.abs(delta_y) == 1) && board.getPiece(initPos).getColor() != board.getPiece(newPos).getColor();
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
    
    /**
     *
     * @param board
     * @param posInit
     * @return
     */
    @Override
    public boolean canMove(Board board, int[] posInit) {        
        boolean canMove = false;
        int[][] offsets = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}, {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[] auxPos;
        int oponent = board.getPiece(posInit).getColor() == 0 ? 1 : 0;
        
        for (int i = 0; i < offsets.length && !canMove; i++) {
            auxPos = posInit.clone();
            auxPos[0] += offsets[i][0];
            auxPos[1] += offsets[i][1];
            canMove = board.getPiece(auxPos).getColor() == -1 || board.getPiece(auxPos).getColor() == oponent;
            canMove = canMove && !this.isInDanger(board, board.getPiece(posInit).getColor(), auxPos);
        }
        return canMove;
    }
    
    private boolean isInDangerDiagonal(Board board, int color, int[] posInit){
        boolean inDanger = false;
        int oponent = color == 0 ? 1 : 0;
        int[][] offsets = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        int[] auxPos;
        
        for(int i = 0; i < offsets.length && !inDanger; i++){
            auxPos = posInit.clone();
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
            auxPos = posInit.clone();
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
        
        if(isInDangerPawn(board, color, posInit)){
            System.out.println("A pawn is checking me");
        }
        
        if(isInDangerKnight(board, color, posInit)){
            System.out.println("A knight is checking me");
        }
        
        if(isInDangerStraight(board, color, posInit)){
            System.out.println("A bishop is checking me");
        }
        
        if(isInDangerDiagonal(board, color, posInit)){
            System.out.println("A rook is checking me");
        }
        
        return inDanger;
    }

    /**
     *
     * @param board
     * @param posInit
     * @return
     */
    @Override
    public boolean isInDanger(Board board, int[] posInit) {
        return this.isInDanger(board, board.getPiece(posInit).getColor(), posInit);
    }   
}
