/**
Replace grid[][].setnumber blah to this.set/getnumber
**/


/**
Modify visibility
**/

/**
Author: Kwesi Daniel

Creates a "large" 3x3 grid to hold the smaller 3x3 sudoku grids (creating an overall 9x9 grid)

v1.1
**/
class Table{
	
private Grid[][] grid = new Grid[3][3]; //The "large" 3x3 grid
private boolean complete = false; //is this grid complete?


/**
Creates a table from a string. 
"." = a blank space, which is replaced by 0. Every set of 9 characters constitutes a row.
ie: ...8....44...136...3..95..7.4.......8..3..5...7.....86...9..3.8.....7.1.36.1..4.. is converted to 

|0|0|0|	|8|0|0|	|0|0|4|	
|4|0|0|	|0|1|3|	|6|0|0|	
|0|3|0|	|0|9|5|	|0|0|7|	

|0|4|0|	|0|0|0|	|0|0|0|	
|8|0|0|	|3|0|0|	|5|0|0|	
|0|7|0|	|0|0|0|	|0|8|6|	

|0|0|0|	|9|0|0|	|3|0|8|	
|0|0|0|	|0|0|7|	|0|1|0|	
|3|6|0|	|1|0|0|	|4|0|0|	


**/
Table(String puzzle){
	char temp;
		for(int i = 0,k=0;i < 9;i++){
			int[] nums = new int[9];
			for(int j = 0;j < 9;j++,k++){
				temp = puzzle.charAt(k);
				if(temp == '.'){
					nums[j] = 0;
				}
				else{
					nums[j] = Character.getNumericValue(temp);
				}

			}
			for(int a = 0;a < nums.length;a++){
				/*if(grid[i/3][a/3] == null){
					grid[i/3][a/3] = new Grid();
				}
				grid[i/3][a/3].setNumber(i%3,a%3,nums[a]);*/
				if(grid[i/3][a/3] == null){
					grid[i/3][a/3] = new Grid();
				}
				setNumber(i,a,nums[a]);


			}
		}
}


/**
A constructor to create a table object from another table.
**/
Table(Table t,int row,int col,int num){
	this.grid = t.getGrid();
	this.setNumber(row,col,num);
}


public Grid[][] getGrid(){
	return grid;
}


/**
Sets the number at the position (row,col) to 'num'
where row and col are the row and column of the overall 9x9 grid.
**/
public int setNumber(int row,int col,int num){
	return grid[row/3][col/3].setNumber(row%3,col%3,num);

}

public String toString(){
	String string = "";
	for(int i = 0;i < 3;i++){
		for(int k = 0;k < 3;k++){
			for(int j = 0;j < 3;j++){
				string += "|";
				for(int l = 0;l < 3;l++){
					string += grid[i][j].getNumber(k,l) + "|";
				}
				string += "\t";
			}
			string += "\n";
		}
		string += "\n";
	}



	return string;
}

/**
Returns the row indicated by rowNumber (from 0 - 8)
**/
private int[] getRow(int rowNumber){ 
	int[] row = new int[9];
	for(int i = 0;i < row.length;){
		for(int j = 0;j < 3;j++){
			for(int k = 0;k < 3;i++,k++){
				row[i] = grid[rowNumber/3][j].getNumber(rowNumber%3,k);
			}
			
		}
	}
	return row;
}

/**
Returns the col indicated by colNumber (from 0 - 8)
**/
private int[] getCol(int colNumber){
	int[] col = new int[9];
	for(int i = 0;i < col.length;){
		for(int j = 0;j < 3;j++){
			for(int k = 0;k < 3;i++,k++){
				col[i] = grid[j][colNumber/3].getNumber(k,colNumber%3);
			}
		}
	}
	return col;

}



/**
Uses backtracking to solve the sudoku puzzle.....
**/
public Table solve(){
	boolean valid;
	Table temp;
	for(int rowNum = 0;rowNum < 9;rowNum++){
		int[] row = getRow(rowNum);
		for(int colNum= 0;colNum < row.length;colNum++){
			if(row[colNum] == 0){
				for(int k = 1;k < 10;k++){
					valid = validate(rowNum,colNum,k);
					if(!valid && (k == 9)){
						setNumber(rowNum,colNum,0);
						return this;
					}
					else if(valid){
						temp = solve(new Table(this,rowNum,colNum,k));
						if(k == 9 && temp.equals(this) && !temp.complete){
							setNumber(rowNum,colNum,0);
							return this;
						}
						if(temp.complete){return temp;}
					}
					

				}

			}
			
		}
	}
	return this; 
}


/**
Helper method for solve()
**/
private Table solve(Table t){
	if(t.check()){
		return t;
	}

	return t.solve();


}

/*
public boolean validate(){
	for(int i = 0;i < 9;i++){
		for(int j = 0;j < 9;j++){
			if(!this.validate(i,j,this.getNumber(i,j))){
				return false;
			}

		}
	}
	System.out.println("true");
	return true;

}*/

/**
Checks if the table is complete
**/
public boolean check(){
	for(int i = 0;i < 9;i++){
		for(int j = 0;j < 9;j++){
			if(getNumber(i,j) == 0){
				complete = false;
				return complete;
			}
		}
	}

	complete = true;
	return complete;
}

/**
Validates the number 'num' in positition (row,col) where row and col 
are the row and column of the overall 9x9 grid.
**/
private boolean validate(int row,int col,int num){
	if(num == 0){return false;}
	if(contains(getRow(row),num) || contains(getCol(col),num)){return false;}
	if(grid[row/3][col/3].contains(num)){return false;}
	return true;
}


private boolean contains(int[] array,int num){
	for(int i = 0;i < array.length;i++){
		if(array[i] == num){return true;}
	}
	return false;
}

/**
Returns the number at position (row,col) where row and col
are the row and column of the overall 9x9 grid.
**/
public int getNumber(int row,int col){
	return grid[row/3][col/3].getNumber(row%3,col%3);
}

public boolean equals(Table t){
	for(int i = 0;i < 9;i++){
		for(int j = 0;j < 9;j++){
			if(t.getNumber(i,j) != this.getNumber(i,j)){
				return false;
			}

		}
	}


	return true;
}

}