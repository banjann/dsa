package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		char[][] board = 
			   {{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
		
		Questions q = new Questions();
		List<String> list = new ArrayList<>(Arrays.asList(new String[] {"we", "say", ":", "yes"}));
		List<String> decoded = q.decode(q.encode(list));
		System.out.println(decoded.toString());
		
	}

}
