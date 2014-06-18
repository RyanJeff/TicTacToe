package com.badlogic.androidgames.uttt;

public class TicTacToeGrid 
{
	public short[] grid = new short[9];
	
	public boolean makeMove(int id, short move)
	{
		if(grid[id] == 0)
		{
			grid[id] = move;
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public void present()
    {
		
    }
}
