/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.chessController;

/**
 *
 * @author eugeniolr
 */
public class MoveObj {
    private int[] init;
    private int[] last;

    public MoveObj() {
    }
    
    public MoveObj(int[] init, int[] last) {
        this.init = init;
        this.last = last;
    }

    public int[] getInit() {
        return init;
    }

    public int[] getLast() {
        return last;
    }
    
}
