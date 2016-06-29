import java.util.Scanner;
import java.io.*;

/**
Author: Kwesi Daniel
**/

/**
v1 - for testing
Compares puzzle to answer 
**/
class Main-test{

		public static void main(String[] args){
		String puzzle;
		String answer;
		Table table;
		Table ans;
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

		for(int i = 1;scanner.hasNext();i++){
			puzzle = scanner.next().trim();
			answer = scanner.next().trim();
			System.out.println(puzzle);
			table = new Table(puzzle);
			ans = new Table(answer);
			System.out.println(i);
			if(table.solve().equals(ans)){
				log.println(i + " - complete\n");
				/*log.println("Start:\n" + new Table(puzzle).toString());
				log.println("Algorithm:\n" + table.solve().toString());
				log.println("Solution:\n" + ans.toString());*/
			}
			else{
				log.println(i + " error+++++++++++++++++++++++++++++++\n");
				log.println("Start:\n" + new Table(puzzle).toString());
				log.println("Algorithm:\n" + table.solve().toString());
				log.println("Solution:\n" + ans.toString());
			}

		}

		log.close();
		
	}
}
