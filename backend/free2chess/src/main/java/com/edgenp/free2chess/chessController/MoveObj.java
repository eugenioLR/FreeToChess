package com.edgenp.free2chess.chessController;

/**
 *
 * @author eugeniolr
 */
public class MoveObj {
    private int[] init;
    private int[] last;

    /**
     *
     */
    public MoveObj() {
    }
    
    /**
     *
     * @param init
     * @param last
     */
    public MoveObj(int[] init, int[] last) {
        this.init = init;
        this.last = last;
    }

    /**
     * 
     * @return
     */
    public int[] getInit() {
        return init;
    }

    /**
     *
     * @return
     */
    public int[] getLast() {
        return last;
    }
    
}
