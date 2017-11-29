package edu.tjhsst.literaturequizshay;

/**
 * Created by 2019kle on 11/1/2017.
 */

public class Storage {
    private int hs;
    private int currentscore;

    public Storage(int hs, int currentscore){
        this.hs=hs;
        this.currentscore=currentscore;
    }

    public int getHs(){
        return hs;
    }

    public int getCurrentscore(){
        return currentscore;
    }

    public void setHs(int hs){
        this.hs = hs;
    }

    public void setCurrentscore(int currentscore){
        this.currentscore=currentscore;
    }
}
