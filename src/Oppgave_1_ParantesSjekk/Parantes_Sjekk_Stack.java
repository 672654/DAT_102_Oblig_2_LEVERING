package Oppgave_1_ParantesSjekk;

import java.util.Stack;

public class Parantes_Sjekk_Stack {

	public static boolean sjekkParantes(String tekst) {
		char[] tab = tekst.toCharArray();
		Stack<Character> stabel = new Stack<>();

		for (char i : tab) {
			// Dersom første tegn er korrekt parantes pushes den til stack.
			if (i == '(' || i == '[' || i == '{') {
				stabel.push(i);

			} else if (i == ')' || i == ']' || i == '}') {

				if (stabel.isEmpty()) {
					return false;
				}

				char top = stabel.pop();
				if ((i == ')' & top != '(') || (i == ']' & top != '[') || (i == '}' & top != '{')) {
					return false;
				}
			}
		}
		return stabel.isEmpty();

	}

}
