import java.util.Scanner;
import java.io.*;

/**
Author: Kwesi Daniel
**/

/**
v1
**/
class Main{

		public static void main(String[] args){
		String puzzle;
		//String answer;
		Table table;
		//Table ans;
		Scanner scanner = null;
		PrintWriter log = null;

		try{
			scanner = new Scanner(new File("sudoku.txt"));
			log = new PrintWriter("log.txt");
			scanner.useDelimiter(",");
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}

		while(scanner.hasNext()){
			puzzle = scanner.next().trim();
			//answer = scanner.next().trim();
			table = new Table(puzzle);
			table.solve();
			System.out.println(table);
			//ans = new Table(answer);
			
		}

		//log.close();
			/*
			System.out.println(i);
			if(table.solve().equals(ans)){
				log.println(i + " - complete\n");
				System.out.println(table.toString());
			}
			else{
				log.println(i + " error+++++++++++++++++++++++++++++++\n");
				log.println("Start:\n" + new Table(puzzle).toString());
				log.println("Algorithm:\n" + table.solve().toString());
				log.println("Solution:\n" + ans.toString());
			}*/
	}
}