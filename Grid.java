
/**
Author: Kwesi Daniel
**/

/**
v1
**/
class Grid{

private int [][] grid = new int[3][3];



Grid(int[] numbers){
	setGrid(numbers);
}

Grid(){}


public boolean contains(int num){
	for(int i = 0;i < 3;i++){
		for(int j = 0;j < 3;j++){
			if(grid[i][j] == num){return true;}
		}
	}
	return false;
}
public String toString(){
	String string = "";
	for(int i = 0;i < 3;i++){
		string += "|";
		for(int j = 0;j < 3;j++){
			string += grid[i][j] + "|";
		}
		string += "\n";
	}


	return string;
}

public String setGrid(int[] numbers){
	for(int i = 0,k = 0;i < 3;i++){
		for(int j = 0;j < 3;j++,k++){
			grid[i][j] = numbers[k];
		}
	}

	return this.toString();

}

public String setGrid(int[][] numbers){
	grid = numbers;
	return this.toString();
}
public int setNumber(int row,int col,int num){
	if((row < 0) || (row > 2) || (col < 0) || (col > 2)){
		System.out.println("Coordinates row= " + row + " col= " + col + "are invalid");
		return -1;
	}

	if(num < 0 || num > 9){
		System.out.println(num + " is invalid.");
		return -1;
	}

	grid[row][col] = num;
	return grid[row][col];

}

public int getNumber(int row,int col){
	if((row < 0) || (row > 2) || (col < 0) || (col > 2)){
		System.out.println("Coordinates row= " + row + " col= " + col + "are invalid");
		return -1;
	}
	return grid[row][col];

}

}