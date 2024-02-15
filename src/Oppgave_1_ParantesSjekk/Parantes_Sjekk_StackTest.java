package Oppgave_1_ParantesSjekk;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Parantes_Sjekk_StackTest {

	@Test
	void testSjekkParantes() {
		assertTrue(Parantes_Sjekk_Stack.sjekkParantes("{ [ ( ) ] } "));
		assertFalse(Parantes_Sjekk_Stack.sjekkParantes("{ [ ( ) } "));
		assertFalse(Parantes_Sjekk_Stack.sjekkParantes("[ ( ) ] } "));
		assertFalse(Parantes_Sjekk_Stack.sjekkParantes("{ [ ( ] ) }"));

		String korrekt = ("""
				class HelloWorld {
				public static void main(String[] args) {
				System.out.println("Hello World!");
				}
				}
				""");
		assertTrue(Parantes_Sjekk_Stack.sjekkParantes(korrekt));
		

	}

}

//String a4 = "{ [ ( ) ] } ";
//String a5 = "{ [ ( ) } ";
//String a6 = "[ ( ) ] } ";
//String a7 = "{ [ ( ] ) } ";