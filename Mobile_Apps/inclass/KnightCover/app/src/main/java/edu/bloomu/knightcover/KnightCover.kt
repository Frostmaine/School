package edu.bloomu.knightcover

/**
 * This class is the game engine for our Knight Cover Game.  Keeps track of a board, and
 * the position of the knights.  As well as, the spaces that they cover.
 * https://oeis.org/A006075 // online encyclopedia of integer sequences
 * @author Matthew Yackiel
 */
class KnightCover(rows: Int, cols: Int) { // primary constructor, freeby

    //tracks the squares that are occupied by knights
    private val occupied: Array<BooleanArray> = Array(rows) {BooleanArray(cols)}

    constructor(n: Int) : this(n, n) // one-argument constructor for square board
    constructor(): this(6, 6) // no argument default board of 6x6

    /**
     * Toggles the occupied state of a specified square.
     */
    fun toggleKnight(r: Int, c: Int) {
        if (valid(r, c))
            occupied[r][c] = !occupied[r][c]
    }

    /**
     * Returns true if square (r, c) is covered by at least one knight.
     */
    fun covered(r: Int, c: Int): Boolean {
        return occupied(r - 1, c - 2) or
                occupied(r - 1, c + 2) or
                occupied(r + 1, c - 2) or
                occupied(r + 1, c + 2) or
                occupied(r - 2, c - 1) or
                occupied(r - 2, c + 1) or
                occupied(r + 2, c - 1) or
                occupied(r + 2, c + 1)
    }

    /**
     * Returns true if square r,c is occupied by a knight
     */
    fun occupied (r: Int, c:Int): Boolean {
        return valid(r, c) && occupied[r][c];
    }

    private fun valid(r: Int, c: Int): Boolean {
        return r in occupied.indices && c in occupied[r].indices
    }

}