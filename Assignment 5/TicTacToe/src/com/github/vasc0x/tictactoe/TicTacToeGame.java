//******************************************************************************
// PANTHER ID: 2923148
// FIU EMAIL: avasc007@fiu.edu
// CLASS: COP 2210 Fall 2015
// ASSIGNMENT # 5
// DATE: 11/28/2015
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else.
//******************************************************************************

package com.github.vasc0x.tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;

public class TicTacToeGame implements ActionListener {
    
    private final int NUMBER_OF_PLAYERS = 2;
    private final int NUMBER_OF_ROWS    = 3;
    private final int NUMBER_OF_COLS    = 3;
    private final int COMPUTER          = 0;
    private final int HUMAN             = 1;
    private final String APP_NAME       = "Tic-Tac-Toe Game";

    int usedCells;
    
    Player[] player = new Player[NUMBER_OF_PLAYERS];
    Cell[][] btnCells = new Cell[NUMBER_OF_ROWS][NUMBER_OF_COLS];
    
    public enum Letter {X, O};
    Letter currentPlayer = Letter.X;
    
    JFrame frmWindow = new JFrame(APP_NAME);
    JLabel jlblTurns = new JLabel();
    //JLabel jlblUsedCells = new JLabel("0");
    
    Random r = new Random();
    
    int playFirst;
    int rndRow;
    int rndCol;
    
    public TicTacToeGame(){}
    
    public void init()
    {
        player[COMPUTER] = new Player();
        player[HUMAN] = new Player();
        player[COMPUTER].setName("Computer");
        player[HUMAN].setName(JOptionPane.showInputDialog(null, "Please enter your name.", "Player 1"));
        JOptionPane.showMessageDialog(null, "Welcome " + player[HUMAN].getName() + ".\n\nYou are the X.");
        
        for (int row = 0; row < NUMBER_OF_ROWS; row++)
        {
            for (int col = 0; col < NUMBER_OF_COLS; col++)
            {
                btnCells[row][col] = new Cell();
                btnCells[row][col].setRow(row);
                btnCells[row][col].setCol(col);
                btnCells[row][col].putClientProperty("column", col);
                btnCells[row][col].putClientProperty("row", row);
                btnCells[row][col].setFont(new Font("Arial", Font.PLAIN, 50));
                // https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
                btnCells[row][col].addActionListener(this);
                frmWindow.add(btnCells[row][col]);
            }
        } 
        // http://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
        frmWindow.setLayout(new GridLayout(4, 3));
        frmWindow.add(jlblTurns); 
        //frmWindow.add(jlblUsedCells);
        frmWindow.setBounds(200, 300, 300, 400);
        frmWindow.pack();
        frmWindow.setSize(300, 400);
        frmWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmWindow.setResizable(false);
        frmWindow.setVisible(true);
        
        launch();
    }
    
    public void launch()
    {
        playFirst = r.nextInt(NUMBER_OF_PLAYERS);
        
        JOptionPane.showMessageDialog(frmWindow, player[playFirst].getName() + " will go first.");
        
        if (playFirst == COMPUTER)
        {
            currentPlayer = Letter.O;
            computerPlay();
        }
        else
        {
            currentPlayer = Letter.X;
            jlblTurns.setText(player[playFirst].getName() + "'s turn.");
        }
    }
    
    public void computerPlay()
    {
        rndRow = r.nextInt(NUMBER_OF_ROWS);
        rndCol = r.nextInt(NUMBER_OF_COLS);
       
        if (btnCells[rndRow][rndCol].isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[rndRow][rndCol].setText("O");
            btnCells[rndRow][rndCol].fill();
            checkWin(rndRow, rndCol);
            currentPlayer = Letter.X;
            //jlblUsedCells.setText(String.valueOf(getUsedCells()));
        }   
        else if (getUsedCells() <= 9)
        {
            computerPlay();
        }
    }
    
    public int getUsedCells()
    {
        usedCells = 0;
        
        for (int row = 0; row < NUMBER_OF_ROWS; row++)
        {
            for (int col = 0; col < NUMBER_OF_COLS; col++)
            {
                if (!btnCells[row][col].isEmpty())
                {
                    usedCells++;
                }
            }
        }
        
        return usedCells;
    }
    
    public boolean checkWin(int row, int col)
    {
        if (btnCells[row][0].getText().equals(currentPlayer.toString()) &&      // 3 in a row
            btnCells[row][1].getText().equals(currentPlayer.toString()) &&      // 3 in a row
            btnCells[row][2].getText().equals(currentPlayer.toString())         // 3 in a row
            ||
            btnCells[0][col].getText().equals(currentPlayer.toString()) &&      // 3 in a column
            btnCells[1][col].getText().equals(currentPlayer.toString()) &&      // 3 in a column
            btnCells[2][col].getText().equals(currentPlayer.toString())         // 3 in a column 
            ||
            btnCells[0][0].getText().equals(currentPlayer.toString()) &&        // 3 in a diagonal
            btnCells[1][1].getText().equals(currentPlayer.toString()) &&        // 3 in a diagonal
            btnCells[2][2].getText().equals(currentPlayer.toString())           // 3 in a diagonal
            ||
            btnCells[0][2].getText().equals(currentPlayer.toString()) &&        // 3 in the opposite diagonal
            btnCells[1][1].getText().equals(currentPlayer.toString()) &&        // 3 in the opposite diagonal
            btnCells[2][0].getText().equals(currentPlayer.toString())           // 3 in the opposite diagonal                
            )
        {            
            if (currentPlayer.equals(Letter.X))
            {
                JOptionPane.showMessageDialog(null, player[HUMAN].getName() + " won!");
            }
            else if (currentPlayer.equals(Letter.O))
            {
                JOptionPane.showMessageDialog(null, player[COMPUTER].getName() + " won!");
            }
            
            repeat();
            
            return true;
        } 
        else if (getUsedCells() == 9)
        {
            JOptionPane.showMessageDialog(null, "It's a tie!");
            repeat();
            
            return true;
        }
        
        return false;
    }
    
    public void repeat()
    {
        int input = JOptionPane.showConfirmDialog(frmWindow, "Play Again?", "Keep Playing", JOptionPane.YES_NO_OPTION);

        if (input == JOptionPane.YES_OPTION)
        {
            clear();
            launch();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Goodbye " + player[HUMAN].getName() + "!");
            System.exit(0);                
        }        
    }
    
    public void clear()
    {        
        for (int row = 0; row < NUMBER_OF_ROWS; row++)
        {
            for (int col = 0; col < NUMBER_OF_COLS; col++)
            {
                btnCells[row][col].clear();
                btnCells[row][col].setText("");
            }
        } 
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JButton btn = (JButton) e.getSource();
        int btnRow = Integer.parseInt(btn.getClientProperty("row").toString());
        int btnCol = Integer.parseInt(btn.getClientProperty("column").toString());
        
        if (btnCells[btnRow][btnCol].isEmpty())
        {
            if (currentPlayer.equals(Letter.X))
            {
                jlblTurns.setText(player[COMPUTER].getName() + "'s turn.");
                btnCells[btnRow][btnCol].setText("X");
                btnCells[btnRow][btnCol].fill();
                //jlblUsedCells.setText(String.valueOf(getUsedCells()));
                
                if (!checkWin(btnRow, btnCol))
                {
                    currentPlayer = Letter.O;
                    computerPlay();
                }
            }

        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please click an empty cell!");
        }
    }
}
