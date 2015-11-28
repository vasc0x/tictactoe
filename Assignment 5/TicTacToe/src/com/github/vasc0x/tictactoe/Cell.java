//******************************************************************************
// PANTHER ID: 2923148
// FIU EMAIL: avasc007@fiu.edu
// CLASS: COP 2210 Fall 2015
// ASSIGNMENT # 1
// DATE: 11/28/2015
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else.
//******************************************************************************

package com.github.vasc0x.tictactoe;

import javax.swing.JButton;

public class Cell extends JButton {
    
    private int row;
    private int col;
    private boolean isEmpty = true;
    
    public Cell()
    {
        
    }
    
    public void setRow (int aRow)
    {
        this.row = aRow;
    }
    
    public int getRow()
    {
        return this.row;
    }
    
    public void setCol (int aCol)
    {
        this.col = aCol;
    }
    
    public int getCol()
    {
        return this.col;
    }    

    public void fill()
    {
        this.isEmpty = false;
    }
    
    public boolean isEmpty()
    {
        return this.isEmpty;
    }
    
    public void clear()
    {
        this.isEmpty = true;
    }

}
