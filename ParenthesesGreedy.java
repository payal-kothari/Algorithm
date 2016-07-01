

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ParenthesesGreedy {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String str = input.nextLine();												// store the input expression

		List<String> str2 = new ArrayList<String>(Arrays.asList(str.split(" ")));   // store the input expression in an arraylist
		Stack<String> num = new Stack<>();											// stack for numbers
		Stack<String> oper = new Stack<>();											// stack for operators

		for (int i = 0; i < str2.size(); i++) {
			String temp = str2.get(i);
			if (!temp.equals("+") && !temp.equals("*")) {							// check if the value is a number 
				num.push(temp);
			} else if (temp.equals("+")) {											// check if the value is a "+" operator 
				i++;
				String prev = num.pop();
				num.push(String.valueOf(add(prev, str2.get(i))));
			} else if (temp.equals("*")) {											// check if the value is a "*" operator 
				oper.push("*");
			}
		}

		while (!oper.isEmpty()) {													// loop till all "*" operations are not done

			oper.pop();
			String first = num.pop();
			String sec = num.pop();

			num.push(String.valueOf(mult(first, sec)));

		}

		String ans = (String) num.pop();											// final answer
		System.out.println(ans);

	}

	private static int mult(String first, String sec) {								// function to find the multiplication of two values

		int multi = Integer.parseInt(first) * Integer.parseInt(sec);

		return multi;
	}

	private static int add(String prev, String next) {								// function to find the addition of two values

		int addition = Integer.parseInt(prev) + Integer.parseInt(next);

		return addition;
	}

}
