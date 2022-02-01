/**
 * This class serves as the game engine for my Voronoi Game
 * @author Matthew Yackiel
 */

public class VoronoiGame {

    private int numOfRounds;
    private int xDimension, yDimension;
    private char[][] gameBoard; // the game board

    /**
     * By default the game lasts 2 rounds on a 10x10 grid and is single player
     */
    public VoronoiGame()
    {
        this(2, 10, 10);
    }

    public VoronoiGame(int numOfRounds, int x, int y)
    {
        this.numOfRounds = numOfRounds;
        xDimension = x;
        yDimension = y;
        gameBoard = new char[xDimension][yDimension]; // declare an empty board

    }
}
