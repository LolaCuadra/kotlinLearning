import java.util.*
import kotlin.math.abs

data class ChessPiece(val player: Int, val pieceType: String) {
    override fun toString(): String {
        val pieceSymbol = when (pieceType) {
            "Pawn" -> if (player == 1) "♙" else "♟"
            "Rook" -> if (player == 1) "♖" else "♜"
            "Knight" -> if (player == 1) "♘" else "♞"
            "Bishop" -> if (player == 1) "♗" else "♝"
            "Queen" -> if (player == 1) "♕" else "♛"
            "King" -> if (player == 1) "♔" else "♚"
            else -> " "
        }
        return pieceSymbol
    }
}

class ChessBoard {
    private val board: Array<Array<ChessPiece?>> = Array(8) { Array(8) { null } }
    private var currentPlayer: Int = 1
    private val scanner = Scanner(System.`in`)

    private fun initialize() {
        board[0][0] = ChessPiece(1, "Rook")
        board[0][7] = ChessPiece(1, "Rook")
        board[0][1] = ChessPiece(1, "Knight")
        board[0][6] = ChessPiece(1, "Knight")
        board[0][2] = ChessPiece(1, "Bishop")
        board[0][5] = ChessPiece(1, "Bishop")
        board[0][3] = ChessPiece(1, "Queen")
        board[0][4] = ChessPiece(1, "King")

        board[7][0] = ChessPiece(2, "Rook")
        board[7][7] = ChessPiece(2, "Rook")
        board[7][1] = ChessPiece(2, "Knight")
        board[7][6] = ChessPiece(2, "Knight")
        board[7][2] = ChessPiece(2, "Bishop")
        board[7][5] = ChessPiece(2, "Bishop")
        board[7][3] = ChessPiece(2, "Queen")
        board[7][4] = ChessPiece(2, "King")

        for (i in 0..7) {
            board[1][i] = ChessPiece(1, "Pawn")
            board[6][i] = ChessPiece(2, "Pawn")
        }
    }


    private fun printBoard() {
        for (row in 0..7) {
            for (col in 0..7) {
                val piece = board[row][col]
                print(piece?.toString() ?: " ")
                print(" ")
            }
            println()
        }
    }
    private fun makeMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean {
        val piece = board[fromRow][fromCol]

        if (piece == null || piece.player != currentPlayer) {
            println("Invalid move: No piece or wrong player")
            return false
        }

        if (!isValidMove(fromRow, fromCol, toRow, toCol)) {
            println("Invalid move: Move is not valid")
            return false
        }

        val destinationPiece = board[toRow][toCol]
        if (destinationPiece != null && destinationPiece.player == currentPlayer) {
            println("Invalid move: Destination is occupied by your own piece")
            return false
        }

        board[fromRow][fromCol] = null
        board[toRow][toCol] = piece

        currentPlayer = 3 - currentPlayer
        println("Player switched to $currentPlayer")

        // TODO:
        // Check for checkmate, stalemate, or other game-over conditions (you need to implement this)
        // If a game-over condition is met, return false

        return true
    }



    private fun isValidMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean {
        val piece = board[fromRow][fromCol] ?: return false

        val deltaX = toRow - fromRow
        val deltaY = toCol - fromCol


        if (fromCol < 0 || fromCol > 7 || toCol < 0 || toCol > 7) {
            return false
        }

        when (piece.pieceType) {
            "Pawn" -> {
                if (piece.player == 1) {
                    // Pawn can move forward by one square
                    if (deltaX == 1 && deltaY == 0) {
                        return true
                    }
                    // Pawn can move forward by two squares on its first move
                    if (fromRow == 1 && deltaX == 2 && deltaY == 0) {
                        return true
                    }
                    // Pawn can capture diagonally
                    if (deltaX == 1 && abs(deltaY) == 1) {
                        return true
                    }
                } else if (piece.player == 2) {
                    if (deltaX == -1 && deltaY == 0) {
                        return true
                    }
                    // Pawn can move backward by two squares on its first move
                    if (fromRow == 6 && deltaX == -2 && deltaY == 0) {
                        return true
                    }
                    // Pawn can capture diagonally
                    if (deltaX == -1 && abs(deltaY) == 1) {
                        return true
                    }
                }
            }
            "Rook" -> {
                // Rook can move horizontally or vertically
                if (deltaX == 0 || deltaY == 0) {
                    return true
                }
            }

            "Knight" -> {
                // Knight moves in an L-shape
                if ((abs(deltaX) == 1 && abs(deltaY) == 2) || (abs(deltaX) == 2 && abs(deltaY) == 1)) {
                    return true
                }
            }

            "Bishop" -> {
                // Bishop moves diagonally
                if (abs(deltaX) == abs(deltaY)) {
                    return true
                }
            }

            "Queen" -> {
                // Queen combines rook and bishop moves
                if (deltaX == 0 || deltaY == 0 || abs(deltaX) == abs(deltaY)) {
                    return true
                }
            }

            "King" -> {
                // King can move in any direction by one square
                if (abs(deltaX) <= 1 && abs(deltaY) <= 1) {
                    return true
                }
            }

            else -> return false
        }

        return false
    }

    fun startGame() {
        initialize()
        while (true) {
            printBoard()
            println("Player $currentPlayer's turn:")
            println("Enter your move (e.g., 'fromRow fromCol toRow toCol'): ")

            val fromRow = scanner.nextInt()
            val fromCol = scanner.nextInt()
            val toRow = scanner.nextInt()
            val toCol = scanner.nextInt()

            if (makeMove(fromRow, fromCol, toRow, toCol)) {
                currentPlayer =  3 - currentPlayer
            } else {
                println("Invalid move. Try again.")
            }
        }
    }
}

fun main() {
    ChessBoard().startGame()
}
