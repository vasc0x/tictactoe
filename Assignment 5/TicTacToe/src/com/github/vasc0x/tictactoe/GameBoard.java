//******************************************************************************
// PANTHER ID: 2923148
// FIU EMAIL: avasc007@fiu.edu
// CLASS: COP 2210 Fall 2015
// ASSIGNMENT # 1
// DATE: 08/25/2015
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else.
//******************************************************************************

package com.github.vasc0x.tictactoe;

public class GameBoard {
    
    final int NUMBER_OF_ROWS = 3;
    final int NUMBER_OF_COLS = 3;
    
    int currentRow;
    int currentColumn;
    
    Cell[][] cells;
    
    public GameBoard()
    {
        cells = new Cell[NUMBER_OF_ROWS][NUMBER_OF_COLS];
    }
    
    public void init()
    {
        for (int row = 0; row < NUMBER_OF_ROWS; row++)
        {
            for (int col = 0; col < NUMBER_OF_COLS; col++)
            {
                cells[row][col] = new Cell();
                //cells[row][col].clear();
                
                //System.out.println(cells[row][col]);
            }
        }
        
        draw();
    }
    
    public void draw()
    {
        for (int row = 0; row < NUMBER_OF_ROWS; row++)
        {
            for (int col = 0; col < NUMBER_OF_COLS; col++)
            {
                //cells[row][col].fill();
                
                if (col < NUMBER_OF_COLS - 1)
                {
                    System.out.print("|");
                }
            }
            
            System.out.println();
            
            if (row < NUMBER_OF_ROWS - 1)
            {
                System.out.print("------------");
            }
        }
    }

}
