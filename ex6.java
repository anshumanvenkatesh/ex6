import java.util.*;
class parser //extends parserconvenience
{
	public static void main(String[] args) 
	{
		int j = 0, k = 0;
		HashMap<String, Integer> column = new HashMap<String, Integer>();
		column.put("(", 3);
		column.put(")", 4);
		column.put("*", 2);
		column.put("+", 1);
		column.put("i", 0);
		column.put("$", 5);
		column.put("E", 6);
		column.put("T", 7);
		column.put("F", 8);
		//System.out.println(column.get("i"));
		Scanner sc = new Scanner(System.in);
		String[][] table = new String[12][9];
		for (j = 0; j < 12; ++j ) 
			for (k = 0; k < 9; ++k) 
				table[j][k]	= " ";	
		j = 0;
		table[0][0] = "s5" ;
		table[0][3] = "s4" ;
		table[0][6] =  "1";
		table[0][7] =  "2";
		table[0][8] = "3" ;
		table[1][1] =  "s6";
		table[1][5] =  "Accept";
		table[2][1] = "r2";
		table[2][2] = "s7";
		table[2][4] = "r2";
		table[2][5] = "r2";
		table[3][1] = "r4";
		table[3][2] = "r4";
		table[3][4] = "r4";
		table[3][5] = "r4";
		table[4][0] = "s5";
		table[4][3] = "s4";
		table[4][6] = "8";
		table[4][7] = "2";
		table[4][8] = "3";
		table[5][1] = "r6";
		table[5][2] = "r6";
		table[5][4] = "r6";
		table[5][5] = "r6";
		table[6][0] = "s5";
		table[6][3] = "s4";
		table[6][7] = "9";
		table[6][8] = "3";
		table[7][0] = "s5";
		table[7][3] = "s4";
		table[7][8] = "10";
		table[8][1] = "s6";
		table[8][4] = "s11";
		table[9][1] = "r1";
		table[9][2] = "s7";
		table[9][4] = "r1";
		table[9][5] = "r1";
		table[10][1] = "r3";
		table[10][2] = "r3";
		table[10][4] = "r3";
		table[10][5] = "r3";
		table[11][1] = "r5";
		table[11][2] = "r5";
		table[11][4] = "r5";
		table[11][5] = "r5";
		String[] productions = new String[8];
		productions[0] = null;
		productions[1] = "E->E+T";
		productions[2] = "E->T";
		productions[3] = "T->T*F";
		productions[4] = "T->F";
		productions[5] = "F->(E)";
		productions[6] = "F->i";
		System.out.println("Entegitr the input string: ");
		String inputstring = sc.nextLine();
		Stack<String> stack = new Stack<String>();
		stack.push("0");
		ArrayDeque<String> input = new ArrayDeque<String>();   // Input Column 
		for(int i = 0; i < inputstring.length(); ++i)
			input.add(inputstring.charAt(i) + "");
		input.add("$");
		while(true)
		{
			System.out.println("Action " + (++j) + ": " + table[Integer.parseInt(stack.lastElement())][column.get(input.getFirst() + "")]);
			if(table[Integer.parseInt(stack.lastElement())][column.get(input.getFirst() + "")].charAt(0) == 's')
			{
				String temp = table[Integer.parseInt(stack.lastElement())][column.get(input.getFirst() + "")].substring(1);
				stack.add(input.getFirst());
				stack.add(temp + "");
				input.removeFirst();
			}

			else if(table[Integer.parseInt(stack.lastElement())][column.get(input.getFirst() + "")].charAt(0) == 'r')
			{
				String temp_string = productions[Integer.parseInt(table[Integer.parseInt(stack.lastElement())][column.get(input.getFirst())].substring(1))] + "";
				for(int i = 0; i < 2 * (temp_string.substring(3,temp_string.length()).length()); ++i)
					stack.pop();
				String temp = stack.peek();
				stack.add(temp_string.charAt(0) + "");
				stack.add(table[Integer.parseInt(temp)][column.get(stack.peek())]);
			}
			else if(table[Integer.parseInt(stack.lastElement())][column.get(input.getFirst())] == " ")
			{
				System.out.println("Failure. Given String cannot be derived from Expression grammar!");
				break;
			}
			else if(table[Integer.parseInt(stack.lastElement())][column.get(input.getFirst())] == "Accept")
			{
				System.out.println("Success!");
				break;
			}
		}
	}
}