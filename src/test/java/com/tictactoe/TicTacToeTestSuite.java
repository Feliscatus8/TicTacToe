package com.tictactoe;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTestSuite {
    @Nested
    class testBoard{
        @Test
        void testAddMove() throws SpaceTakenException{
            //Given
            Board board = new Board();

            //When
            board.addMove(0, 0, "X");
            board.addMove(1, 1, "O");

            //Then
            assertAll(
                    () -> assertDoesNotThrow(() -> board.addMove(0, 2, "X")),
                    () -> assertThrows(SpaceTakenException.class, () -> board.addMove(0, 2, "O")),
                    () -> assertEquals("X", board.getBoard()[0][2])
            );
        }
        @Test
        void testVictoryHorizontal() throws SpaceTakenException{
            //Given
            Board board = new Board();
            board.addMove(2, 0, "X");
            board.addMove(1, 1, "O");
            board.addMove(2, 1, "X");
            board.addMove(0, 0, "O");
            board.addMove(2, 2, "X");

            //When
            boolean victoryX = board.victory(2, 2, "X");
            boolean victoryO = board.victory(0, 0, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryO),
                    () -> assertTrue(victoryX)
            );
        }
        @Test
        void testVictoryVertical() throws SpaceTakenException{
            //Given
            Board board = new Board();
            board.addMove(2, 0, "X");
            board.addMove(1, 1, "O");
            board.addMove(2, 2, "X");
            board.addMove(0, 1, "O");
            board.addMove(0, 2, "X");
            board.addMove(2, 1, "O");

            //When
            boolean victoryX = board.victory(0, 2, "X");
            boolean victoryO = board.victory(2, 1, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryX),
                    () -> assertTrue(victoryO)

            );
        }
        @Test
        void testVictoryDiagonalLeft() throws SpaceTakenException{
            //Given
            Board board = new Board();
            board.addMove(0, 0, "X");
            board.addMove(0, 1, "O");
            board.addMove(2, 2, "X");
            board.addMove(0, 2, "O");
            board.addMove(1, 1, "X");

            //When
            boolean victoryX = board.victory(1, 1, "X");
            boolean victoryO = board.victory(0, 2, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryO),
                    () -> assertTrue(victoryX)

            );
        }
        @Test
        void testVictoryDiagonalRight() throws SpaceTakenException{
            //Given
            Board board = new Board();
            board.addMove(0, 0, "X");
            board.addMove(1, 1, "O");
            board.addMove(1, 0, "X");
            board.addMove(0, 2, "O");
            board.addMove(2, 2, "X");
            board.addMove(2, 0, "O");

            //When
            boolean victoryX = board.victory(0, 2, "X");
            boolean victoryO = board.victory(2, 1, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryX),
                    () -> assertTrue(victoryO)

            );
        }
    }
    @Nested
    class testBigBoard{
        @Test
        void testAddMove() throws SpaceTakenException{
            //Given
            BigBoard board = new BigBoard();

            //When
            board.addMove(5, 5, "X");
            board.addMove(5, 6, "O");
            board.addMove(6, 5, "X");
            board.addMove(6, 6, "O");
            board.addMove(6, 7, "X");
            board.addMove(6, 4, "O");
            board.addMove(6, 3, "X");
            board.addMove(7, 4, "O");

            //Then
            assertAll(
                    () -> assertDoesNotThrow(() -> board.addMove(8, 5, "X")),
                    () -> assertThrows(SpaceTakenException.class, () -> board.addMove(6, 7, "O")),
                    () -> assertEquals("X", board.getBoard()[6][3]),
                    () -> assertEquals("O", board.getBoard()[6][6])
            );
        }
        @Test
        void testVictoryHorizontal() throws SpaceTakenException{
            //Given
            BigBoard board = new BigBoard();
            board.addMove(4, 0, "X");
            board.addMove(1, 1, "O");
            board.addMove(4, 1, "X");
            board.addMove(1, 0, "O");
            board.addMove(4, 2, "X");
            board.addMove(1, 2, "O");
            board.addMove(4, 3, "X");
            board.addMove(1, 3, "O");
            board.addMove(4, 4, "X");

            //When
            boolean victoryX = board.victory(4, 4, "X");
            boolean victoryO = board.victory(1, 3, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryO),
                    () -> assertTrue(victoryX)
            );
        }
        @Test
        void testVictoryVertical() throws SpaceTakenException{
            //Given
            BigBoard board = new BigBoard();
            board.addMove(4, 0, "X");
            board.addMove(1, 1, "O");
            board.addMove(3, 0, "X");
            board.addMove(2, 1, "O");
            board.addMove(2, 0, "X");
            board.addMove(3, 1, "O");
            board.addMove(0, 0, "X");
            board.addMove(4, 1, "O");
            board.addMove(5, 0, "X");
            board.addMove(6, 1, "O");
            board.addMove(7, 0, "X");
            board.addMove(7, 1, "O");
            board.addMove(1, 0, "X");

            //When
            boolean victoryX = board.victory(1, 0, "X");
            boolean victoryO = board.victory(7, 1, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryO),
                    () -> assertTrue(victoryX)
            );
        }
        @Test
        void testVictoryDiagonalLeft() throws SpaceTakenException{
            //Given
            BigBoard board = new BigBoard();
            board.addMove(4, 0, "X");
            board.addMove(5, 0, "O");
            board.addMove(5, 1, "X");
            board.addMove(6, 1, "O");
            board.addMove(6, 2, "X");
            board.addMove(7, 2, "O");
            board.addMove(7, 3, "X");
            board.addMove(8, 3, "O");
            board.addMove(6, 3, "X");
            board.addMove(8, 4, "O");
            board.addMove(7, 4, "X");
            board.addMove(9, 4, "O");

            //When
            boolean victoryX = board.victory(7, 4, "X");
            boolean victoryO = board.victory(9, 4, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryX),
                    () -> assertTrue(victoryO)
            );
        }
        @Test
        void testVictoryDiagonalRight() throws SpaceTakenException{
            //Given
            BigBoard board = new BigBoard();
            board.addMove(8, 0, "X");
            board.addMove(5, 0, "O");
            board.addMove(7, 1, "X");
            board.addMove(4, 1, "O");
            board.addMove(6, 2, "X");
            board.addMove(3, 2, "O");
            board.addMove(5, 3, "X");
            board.addMove(2, 3, "O");
            board.addMove(3, 5, "X");
            board.addMove(0, 4, "O");
            board.addMove(4, 4, "X");

            //When
            boolean victoryX = board.victory(4, 4, "X");
            boolean victoryO = board.victory(0, 4, "O");

            //Then
            assertAll(
                    () -> assertFalse(victoryO),
                    () -> assertTrue(victoryX)
            );
        }
    }
}
