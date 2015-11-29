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
    JRadioButton rbtnSmarts = new JRadioButton("Medium");
    JRadioButton rbtnUnbeatable = new JRadioButton("Harder");
    ButtonGroup rbtnGroup = new ButtonGroup();
    //JLabel jlblUsedCells = new JLabel("0");
    
    Random r = new Random();
    
    int playFirst;
    int rndRow;
    int rndCol;
    
    public TicTacToeGame(){}
    
    public void init()
    {
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
        
        
        rbtnGroup.add(rbtnSmarts);
        rbtnGroup.add(rbtnUnbeatable);
        
        // http://docs.oracle.com/javase/7/docs/api/java/awt/GridLayout.html
        frmWindow.setLayout(new GridLayout(4, 3));
        frmWindow.add(jlblTurns); 
        frmWindow.add(rbtnSmarts);
        frmWindow.add(rbtnUnbeatable);
        frmWindow.setBounds(200, 300, 300, 400);
        frmWindow.pack();
        frmWindow.setSize(300, 400);
        frmWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmWindow.setResizable(false);
        frmWindow.setVisible(true);
        
        player[COMPUTER] = new Player();
        player[HUMAN] = new Player();
        player[COMPUTER].setName("Computer");
        player[HUMAN].setName(JOptionPane.showInputDialog(frmWindow, "Please enter your name.", "Player 1"));
        JOptionPane.showMessageDialog(frmWindow, "Welcome " + player[HUMAN].getName() + ".\n\nYou are the X.");        
        
        launch();
    }
    
    public void launch()
    {
        playFirst = r.nextInt(NUMBER_OF_PLAYERS);
        
        JOptionPane.showMessageDialog(frmWindow, player[playFirst].getName() + " will go first.");
        
        if (playFirst == COMPUTER)
        {
            currentPlayer = Letter.O;
            randomComputerPlay();
        }
        else
        {
            currentPlayer = Letter.X;
            jlblTurns.setText(player[playFirst].getName() + "'s turn.");
        }
    }
    
    public void randomComputerPlay()
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
            randomComputerPlay();
        }
    }
    
    public void smartComputerPlay()
    {
        if (btnCells[0][0].getText().equals("X") &&
            btnCells[0][1].getText().equals("X") &&
            btnCells[0][1].getText().isEmpty())
        {
            System.out.println(btnCells[0][1].getText());
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][2].setText("O");
            btnCells[0][2].fill();
            checkWin(0, 2);
            currentPlayer = Letter.X;
        }
        else if (btnCells[0][0].getText().equals("X") &&
                 btnCells[1][0].getText().equals("X") &&
                 btnCells[2][0].getText().isEmpty())
        {
            System.out.println(btnCells[0][1].getText());
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][0].setText("O");
            btnCells[2][0].fill();
            checkWin(2, 0);
            currentPlayer = Letter.X;            
        }
        else if (btnCells[0][0].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[2][2].getText().isEmpty())
        {
            System.out.println(btnCells[0][1].getText());
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][2].setText("O");
            btnCells[2][2].fill();
            checkWin(2, 2);
            currentPlayer = Letter.X;            
        }
        else
        {
            randomComputerPlay();
        }
        
    }
    
    public void smarterComputerPlay()
    {
        // First row from left
        if (btnCells[0][0].getText().equals("X") &&
            btnCells[0][1].getText().equals("X") &&
            btnCells[0][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][2].setText("O");
            btnCells[0][2].fill();
            checkWin(0, 2);
            currentPlayer = Letter.X;
        }
        // Second row from left
        else if (btnCells[1][0].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[1][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[1][2].setText("O");
            btnCells[1][2].fill();
            checkWin(1, 2);
            currentPlayer = Letter.X;
        }
        // Third row from left
        else if (btnCells[2][0].getText().equals("X") &&
                 btnCells[2][1].getText().equals("X") &&
                 btnCells[2][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][2].setText("O");
            btnCells[2][2].fill();
            checkWin(2, 2);
            currentPlayer = Letter.X;            
        }  
        // First row from right
        else if (btnCells[0][2].getText().equals("X") &&
            btnCells[0][1].getText().equals("X") &&
            btnCells[0][0].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][0].setText("O");
            btnCells[0][0].fill();
            checkWin(0, 0);
            currentPlayer = Letter.X;
        }
        // Second row from right
        else if (btnCells[1][2].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[1][0].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[1][0].setText("O");
            btnCells[1][0].fill();
            checkWin(1, 0);
            currentPlayer = Letter.X;
        }
        // Third row from right
        else if (btnCells[2][2].getText().equals("X") &&
                 btnCells[2][1].getText().equals("X") &&
                 btnCells[2][0].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][0].setText("O");
            btnCells[2][0].fill();
            checkWin(2, 0);
            currentPlayer = Letter.X;            
        }           
        // First column from top
        else if (btnCells[0][0].getText().equals("X") &&
                 btnCells[1][0].getText().equals("X") &&
                 btnCells[2][0].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][0].setText("O");
            btnCells[2][0].fill();
            checkWin(2, 0);
            currentPlayer = Letter.X;            
        }
        // Second column from top
        else if (btnCells[0][1].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[2][1].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][1].setText("O");
            btnCells[2][1].fill();
            checkWin(2, 1);
            currentPlayer = Letter.X;            
        }   
        // Third column from top
        else if (btnCells[0][2].getText().equals("X") &&
                 btnCells[1][2].getText().equals("X") &&
                 btnCells[2][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][2].setText("O");
            btnCells[2][2].fill();
            checkWin(2, 2);
            currentPlayer = Letter.X;            
        }         
        // First column from bottom
        else if (btnCells[2][0].getText().equals("X") &&
                 btnCells[1][0].getText().equals("X") &&
                 btnCells[0][0].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][0].setText("O");
            btnCells[0][0].fill();
            checkWin(0, 0);
            currentPlayer = Letter.X;
        }
        // Second column from bottom
        else if (btnCells[2][1].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[0][1].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][1].setText("O");
            btnCells[0][1].fill();
            checkWin(0, 1);
            currentPlayer = Letter.X;
        } 
        // Third column from bottom
        else if (btnCells[2][2].getText().equals("X") &&
                 btnCells[1][2].getText().equals("X") &&
                 btnCells[0][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][2].setText("O");
            btnCells[0][2].fill();
            checkWin(0, 2);
            currentPlayer = Letter.X;
        }
        // Top left bottom right diagonal
        else if (btnCells[0][0].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[2][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][2].setText("O");
            btnCells[2][2].fill();
            checkWin(2, 2);
            currentPlayer = Letter.X;            
        }           
        // Bottom left top right diagonal
        else if (btnCells[2][0].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[0][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][2].setText("O");
            btnCells[0][2].fill();
            checkWin(0, 2);
            currentPlayer = Letter.X;
        }
        // Top right bottom left diagonal
        else if (btnCells[0][2].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[2][0].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][0].setText("O");
            btnCells[2][0].fill();
            checkWin(2, 0);
            currentPlayer = Letter.X;            
        }           
        // Bottom right top left diagonal
        else if (btnCells[2][2].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[0][0].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][0].setText("O");
            btnCells[0][0].fill();
            checkWin(0, 0);
            currentPlayer = Letter.X;
        }
        // Second column from bottom
        else if (btnCells[2][1].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[0][1].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][1].setText("O");
            btnCells[0][1].fill();
            checkWin(0, 1);
            currentPlayer = Letter.X;
        } 
        // Third column from bottom
        else if (btnCells[2][2].getText().equals("X") &&
                 btnCells[1][2].getText().equals("X") &&
                 btnCells[0][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][2].setText("O");
            btnCells[0][2].fill();
            checkWin(0, 2);
            currentPlayer = Letter.X;
        }
        // Top left bottom right diagonal
        else if (btnCells[0][0].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[2][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][2].setText("O");
            btnCells[2][2].fill();
            checkWin(2, 2);
            currentPlayer = Letter.X;            
        }           
        // Bottom left top right diagonal
        else if (btnCells[2][0].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[0][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][2].setText("O");
            btnCells[0][2].fill();
            checkWin(0, 2);
            currentPlayer = Letter.X;
        }
        // Top right bottom left diagonal
        else if (btnCells[0][2].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[2][0].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][0].setText("O");
            btnCells[2][0].fill();
            checkWin(2, 0);
            currentPlayer = Letter.X;            
        }           
        // Bottom right top left diagonal
        else if (btnCells[2][2].getText().equals("X") &&
                 btnCells[1][1].getText().equals("X") &&
                 btnCells[0][0].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][0].setText("O");
            btnCells[0][0].fill();
            checkWin(0, 0);
            currentPlayer = Letter.X;
        }
        // First row corners
        else if (btnCells[0][0].getText().equals("X") &&
                 btnCells[0][2].getText().equals("X") &&
                 btnCells[0][1].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[0][1].setText("O");
            btnCells[0][1].fill();
            checkWin(0, 1);
            currentPlayer = Letter.X;
        } 
        // Second row corners
        else if (btnCells[1][0].getText().equals("X") &&
                 btnCells[1][2].getText().equals("X") &&
                 btnCells[1][1].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[1][1].setText("O");
            btnCells[1][1].fill();
            checkWin(1, 1);
            currentPlayer = Letter.X;
        } 
        // Third row corners
        else if (btnCells[2][0].getText().equals("X") &&
                 btnCells[2][2].getText().equals("X") &&
                 btnCells[2][1].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[2][1].setText("O");
            btnCells[2][1].fill();
            checkWin(2, 1);
            currentPlayer = Letter.X;
        }
        // First column corners
        else if (btnCells[0][0].getText().equals("X") &&
                 btnCells[2][0].getText().equals("X") &&
                 btnCells[1][0].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[1][0].setText("O");
            btnCells[1][0].fill();
            checkWin(1, 0);
            currentPlayer = Letter.X;            
        }           
        // Second column corners
        else if (btnCells[0][1].getText().equals("X") &&
                 btnCells[2][1].getText().equals("X") &&
                 btnCells[1][1].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[1][1].setText("O");
            btnCells[1][1].fill();
            checkWin(1, 1);
            currentPlayer = Letter.X;
        }
        // Third column corners
        else if (btnCells[0][2].getText().equals("X") &&
                 btnCells[2][2].getText().equals("X") &&
                 btnCells[1][2].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[1][2].setText("O");
            btnCells[1][2].fill();
            checkWin(1, 2);
            currentPlayer = Letter.X;            
        }           
        // Top left bottom right diagonal corners
        else if (btnCells[0][0].getText().equals("X") &&
                 btnCells[2][2].getText().equals("X") &&
                 btnCells[1][1].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[1][1].setText("O");
            btnCells[1][1].fill();
            checkWin(1, 1);
            currentPlayer = Letter.X;
        }            
        // Top right bottom left diagonal
        else if (btnCells[0][2].getText().equals("X") &&
                 btnCells[2][0].getText().equals("X") &&
                 btnCells[1][1].getText().isEmpty())
        {
            jlblTurns.setText(player[HUMAN].getName() + "'s turn.");
            btnCells[1][1].setText("O");
            btnCells[1][1].fill();
            checkWin(1, 1);
            currentPlayer = Letter.X;
        }          
        else
        {
            randomComputerPlay();
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
                JOptionPane.showMessageDialog(frmWindow, player[HUMAN].getName() + " won!");
            }
            else if (currentPlayer.equals(Letter.O))
            {
                JOptionPane.showMessageDialog(frmWindow, player[COMPUTER].getName() + " won!");
            }
            
            repeat();
            
            return true;
        } 
        else if (getUsedCells() == 9)
        {
            JOptionPane.showMessageDialog(frmWindow, "It's a tie!");
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
            JOptionPane.showMessageDialog(frmWindow, "Goodbye " + player[HUMAN].getName() + "!");
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
                    
                    if (rbtnSmarts.isSelected())
                    {
                        smartComputerPlay();                        
                    }
                    else if (rbtnUnbeatable.isSelected())
                    {
                        smarterComputerPlay();
                    }
                    else
                    {
                        randomComputerPlay(); 
                    }
                }
            }

        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please click an empty cell!");
        }
    }
}
