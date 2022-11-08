package main.game;

import main.cards.minion.Minion;

import java.util.ArrayList;

public class Game {
    private ArrayList<ArrayList<Minion>> board;
    private Deck player1Deck;
    private Deck player2Deck;
    int playerTurn;

    public ArrayList<ArrayList<Minion>> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<ArrayList<Minion>> board) {
        this.board = board;
    }

    public Deck getPlayer1Deck() {
        return player1Deck;
    }

    public void setPlayer1Deck(Deck player1Deck) {
        this.player1Deck = player1Deck;
    }

    public Deck getPlayer2Deck() {
        return player2Deck;
    }

    public void setPlayer2Deck(Deck player2Deck) {
        this.player2Deck = player2Deck;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Game(ArrayList<ArrayList<Minion>> board, Deck player1Deck, Deck player2Deck, int playerTurn) {
        this.board = board;
        this.player1Deck = player1Deck;
        this.player2Deck = player2Deck;
        this.playerTurn = playerTurn;
    }
}
