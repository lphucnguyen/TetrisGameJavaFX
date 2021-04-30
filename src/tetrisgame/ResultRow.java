/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame;

/**
 *
 * @author ADMIN
 */
public class ResultRow {
    private int rank;
    private String name;
    private int score;
    
    public ResultRow(int Rank, String Name, int Score){
        this.rank = Rank;
        this.name = Name;
        this.score = Score;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }
}
