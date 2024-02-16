package Oppgave_1_ParantesSjekk;

class MainParantes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String skalBliTrue = "{[()]}";
		String skalBliFalse = "{[(()]}";
		
		System.out.println(Parantes_Sjekk_Stack.sjekkParantes(skalBliTrue));
		System.out.println(Parantes_Sjekk_Stack.sjekkParantes(skalBliFalse));
	}

}
