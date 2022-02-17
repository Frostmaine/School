package edu.bloomu.knightcover

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

class MainActivity2 : AppCompatActivity() {

    // default dimensions
    private val rows = 6
    private val cols = 6

    // The game
    private val game: KnightCover = KnightCover(6, 6)
    private val buttons = Array(rows) { arrayOfNulls<Button>(cols) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set layout parameters for table rows.
        val tableLayout = findViewById<TableLayout>(R.id.table_layout)
        val tableRowParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        // Calculate size of each square so that the board
        // occupies 80% of the screen
        val displayWidth = resources.displayMetrics.widthPixels
        val displayHeight = resources.displayMetrics.heightPixels
        val n = min(displayWidth, displayHeight)
        val k = max(rows, cols)
        val buttonSize = (.8 * n / 10) / k

        // Create table rows and add them to the table.
        for (i in 0 until rows) {
            val tableRow = TableRow(this)
            tableRow.layoutParams = tableRowParams

            // Add a button to each cell of this row
            for (j in 0 until cols) {
                buttons[i][j] = Button(this)
                buttons[i][j]!!.tag = intArrayOf(i, j) // let the button know its position
                buttons[i][j]!!.width = buttonSize.toInt()
                buttons[i][j]!!.height = buttonSize.toInt()
                buttons[i][j]!!.textSize = 64f
                buttons[i][j]!!.setOnClickListener(this::toggleKnight)
                tableRow.addView(buttons[i][j])
            }
            tableLayout.addView(tableRow)
        }
        drawBoard()
    }

    /**
     * Sets the text for each button depending on whether the
     * corresponding square of the board is occupied, covered,
     * or neither.
     */
    private fun drawBoard() {
        val occupied = "\u2658" // chess knight symbol
        val covered = "\u00b7"  // middle dot
        val open = "\u2716"     // cross mark (X)

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                buttons[i][j]!!.text =
                    when {
                        game.occupied(i, j) -> occupied
                        game.covered(i, j)->covered
                        else -> open
                    }
            }
        }
    }

    /**
     * Toggles teh occupied state at a specified position. Called
     * When a button in the table layout is clicked.
     */
    private fun toggleKnight(view: View) {
        val pos = view.tag as IntArray
        game.toggleKnight(pos[0], pos[1])
        drawBoard()
    }

}