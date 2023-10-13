import java.util.*
import kotlin.math.abs

enum class Piece {
    EMPTY, PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
}

// Define the ChessBoard class
class ChessBoard {
    private val board: Array<Array<Piece>> = Array(8) { Array(8) { Piece.EMPTY } }
    fun initialize() {
        // Initialize the board with the starting positions of pieces
        for (i in 0 until 8) {
            board[1][i] = Piece.PAWN
            board[6][i] = Piece.PAWN
        }
        board[0][0] = Piece.ROOK
        board[0][7] = Piece.ROOK
        board[7][0] = Piece.ROOK
        board[7][7] = Piece.ROOK

        board[0][1] = Piece.KNIGHT
        board[0][6] = Piece.KNIGHT
        board[7][1] = Piece.KNIGHT
        board[7][6] = Piece.KNIGHT

        board[0][2] = Piece.BISHOP
        board[0][5] = Piece.BISHOP
        board[7][2] = Piece.BISHOP
        board[7][5] = Piece.BISHOP

        board[0][3] = Piece.QUEEN
        board[7][3] = Piece.QUEEN

        board[0][4] = Piece.KING
        board[7][4] = Piece.KING

        printBoard()
    }

    fun movePiece(piece: Piece, fromX: Int, fromY: Int, toX: Int, toY: Int) {
        val deltaX = toX - fromX
        val deltaY = toY - fromY

        when (piece) {
            Piece.PAWN -> {
                // Pawn can move forward by one square
                if (deltaX == 1 && deltaY == 0) {
                    printBoard()
                }
                // Pawn can move forward by two squares on its first move
                if (fromX == 1 && deltaX == 2 && deltaY == 0) {
                    printBoard()
                }
                // Pawn can capture diagonally
                if (deltaX == 1 && abs(deltaY) == 1) {
                    printBoard()
                }
            }

            Piece.ROOK -> {
                // Rook can move horizontally or vertically
                if (deltaX == 0 || deltaY == 0) {
                    printBoard()
                }
            }

            Piece.KNIGHT -> {
                // Knight moves in an L-shape
                if ((abs(deltaX) == 1 && abs(deltaY) == 2) || (abs(deltaX) == 2 && abs(deltaY) == 1)) {
                    printBoard()
                }
            }

            Piece.BISHOP -> {
                // Bishop moves diagonally
                if (abs(deltaX) == abs(deltaY)) {
                    printBoard()
                }
            }

            Piece.QUEEN -> {
                // Queen combines rook and bishop moves
                if (deltaX == 0 || deltaY == 0 || abs(deltaX) == abs(deltaY)) {
                    printBoard()
                }
            }

            Piece.KING -> {
                // King can move in any direction by one square
                if (abs(deltaX) <= 1 && abs(deltaY) <= 1) {
                    printBoard()
                }
            }

            else -> return

        }

    }

    private fun printBoard() {
        // Print the current state of the chessboard
        for (i in 0 until 8) {
            for (j in 0 until 8) {
                print("${pieceToSymbol(board[i][j])} ")
            }
            println()
        }
    }

    private fun pieceToSymbol(piece: Piece): String {
        // Convert chess pieces to symbols for printing
        return when (piece) {
            Piece.EMPTY -> "."
            Piece.PAWN -> "P"
            Piece.ROOK -> "R"
            Piece.KNIGHT -> "N"
            Piece.BISHOP -> "B"
            Piece.QUEEN -> "Q"
            Piece.KING -> "K"
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val chessBoard = ChessBoard()

    chessBoard.initialize()

    while (true) {
        println("Enter your move (e.g., '2 1 to 4 1'): ")
        val input = scanner.nextLine().trim()

        if (input.equals("exit", ignoreCase = true)) {
            break
        }

        val move = input.split(" ")
        if (move.size == 4) {
            val fromX = move[0].toInt()
            val fromY = move[1].toInt()
            val toX = move[2].toInt()
            val toY = move[3].toInt()

            chessBoard.movePiece(Piece.PAWN, fromX, fromY, toX, toY)
        } else {
            println("Invalid input. Please enter a valid move or type 'exit' to quit.")
        }
    }
}
