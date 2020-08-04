package palavrascruzadas;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Jogo {
	
	String tema2[] = {"CONCEICAOEVARISTO", "SUBSTANTIVO", "CRIOULO", "LATIM", "HIATO", "SONETO", "BALEIA", "FONEMA", "ASSIS", "AUTA"};
	String tema1[] = {"ELFODOMESTICO", "ACCIO", "AZKABAN", "IMPERIUS", "POCOES", "HELENA", "VIKTOR", "GIGANTE", "SECTUMSEMPRA", "TACA"};
	String statusDica[] = {"OK!", "", "", "", "", "", "", "", "", ""};
	boolean continua = true;
		
	char positions[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
	boolean impressao = false;
	int rodada = 0;
	Scanner ler = new Scanner(System.in);
	char verificar[] = new char[10]; 
	
	Jogo(){
		
	}
	Jogo(int tema){
		if(tema == 1) {
			play(tema, tema1);
		}else if(tema == 2) {
			play(tema, tema2);

		}
	}
	
	
	void Menu(int opcao){
	
	if(opcao != 1) {
	System.out.println("\n\t\t- M E N U -\n\tInforme a opção desejada:\n");
	System.out.println("\t1. Iniciar jogo\n"
			+ 		   "\t2. Como jogar?\n"
			+		   "\t3. Sair");
	}else {
		System.out.println("\n\t\t- Informe o tema desejado: -");
		System.out.println("\tTema 1: Harry Potter\n"
				+ 		   "\tTema 2: Conhecimentos da Lingua Portuguesa"
				+		   "");
	}

}
	void play(int tema, String palavras[]) {
		verificar[0] = 1; //Indica que a primeira palavra já está preenchida;
		int posicao = 1, acertos = 0, erro = 0;
		Tabuleiro tabuleiro = new Tabuleiro(palavras, tema);
		String palavra, dfault;
		boolean aux = false; //AUXILIAR PARA AJUDAR NOS ACERTOS/ERROS E IMPRESSÕES DO PROGRAMA PARA O USUÁRIO
		
		if(acertos == 0) {
			System.out.println("\n\t\tVamos começar o jogo!\nLembre-se: As palavras devem ser escritas sem acentos.\n\t  O tabuleiro será impresso na tela: ");
			tabuleiro.print();
			Dicas(tema, palavras, statusDica); 
			
		}
		do { //LAÇO PRINCIPAL, FEITO PARA QUE O JOGO ACONTEÇA ATÉ QUE O USUÁRIO ACERTE AS 9 PALAVRAS.
			
				do { //PEDE A POSIÇÃO DA PALAVRA ATÉ QUE ELA SEJA VÁLIDA.
						
							
								if(aux == false) {
									try {
										System.out.println("\nDigite o número da palavra");
										System.out.print("\t> ");
										posicao = ler.nextInt();
										
										if(posicao > 0 || posicao < 10) {
											continua = false;
										}
									}catch(InputMismatchException e){
										ler.nextLine();
										System.out.print("Ei, cuidado! Você não pode digitar uma palavra ainda.\n");
									}
								
								}else {
									try {
										System.out.println("\nMuito bom! Vamos pra próxima! Digite um número novamente");
										System.out.print("\t> ");
										posicao = ler.nextInt();
										aux = false;
										if(posicao > 0 && posicao < 10) {
											continua = false;
										}
									}catch(InputMismatchException e){
										ler.nextLine();
										System.out.print("Ei, cuidado! Você não pode digitar uma palavra ainda.\n");
									}
								}
																			
							if(posicao == 0 || posicao < 0 || posicao > 10 ) {
								System.out.println("Poxa, posição inválida! Tente novamente.");
							}
							
				}while(posicao < 0 || posicao < 1 || posicao > 10 || continua == true); //PEDE A POSIÇÃO DA PALAVRA ATÉ QUE ELA SEJA VÁLIDA.
			
			while(!VerificaPosicao(posicao)) { //PEDE NOVAMENTE RESPOSTA DO USUÁRIO CASO A POSIÇÃO ESCOLHIDA JÁ ESTEJA OCUPADA
				
				System.out.print("\t>");
				posicao = ler.nextInt();
				
			}
			
			System.out.print("\t> Resposta: ");
			palavra = ler.next();
			dfault = palavra.toUpperCase(); //Feito para não diferenciar maiúsculas ou minúsculas do usuário; padroniza; 
			
			
			if(palavras[posicao-1].equals(dfault)) { //COMPARA A RESPOSTA DO USUÁRIO COM A RESPOSTA DA CRUZADA
				System.out.println("\tVocê acertou! Essa é a resposta correta!");
				verificar[posicao-1] = 1; //SINALIZA A POSIÇÃO COMO OCUPADA (1);
				aux = true;
				acertos++;
				tabuleiro.Substituir(positions[posicao-1], palavras[posicao-1], tabuleiro.getDirecao(positions[posicao-1], tema), tabuleiro.getLinha(positions[posicao-1], tema), tabuleiro.getColuna(positions[posicao-1], tema));
				statusDica[posicao-1] = "OK!";
				tabuleiro.print();
			}else {
				System.out.println("\tPoxa, você errou :( Tente novamente!");
				Dicas(tema, palavras, statusDica); 
				erro++;
			}
			if(erro == 5 || erro == 10 || erro == 20) {
				System.out.println("\tNão esquece, hein? As palavras NÃO devem ser acentuadas!");
			}
			if(erro == 2 || erro == 14 || erro == 21) { //QUANTIDADE DE ERROS PARA SABER SE O USUÁRIO QUER DESISTIR
				int continuar;
					do { //LAÇO QUE GARANTE QUE SERÁ PERGUNTADO SE O USUÁRIO QUER CONTINUAR ENQUANTO A RESPOSTA FOR INVÁLIDA
						
						System.out.println("Você quer continuar tentando?  1.SIM 2.NAO:");
						System.out.print("\t> ");
						continuar = ler.nextInt();
						if(continuar == 2) {
							System.out.println("\n\t Tudo bem, até a próxima! :)");
							acertos = 90; //MODIFICA O NÚMERO DE ACERTOS PARA SAIR DO LAÇO PRINCIPAL.
						}else if(continuar == 1) {
							System.out.println("Vamos continuar, então.");
						}
						
					}while(continuar != 1 && continuar != 2);//LAÇO QUE GARANTE QUE SERÁ PERGUNTADO SE O USUÁRIO QUER CONTINUAR ENQUANTO A RESPOSTA FOR INVÁLIDA
			}
			
		}while(acertos < 9); //LAÇO PRINCIPAL, FEITO PARA QUE O JOGO ACONTEÇA ATÉ QUE O USUÁRIO ACERTE AS 9 PALAVRAS.
		
		if(acertos == 90) {
			System.out.println("É uma pena que tenha desistido... Quem sabe na próxima!");
			System.out.println("\tAqui estão as palavras que faltavam:\n");
			Desistencia(tabuleiro, tema, palavras);
			tabuleiro.print();
			System.out.println("*\t-\t*\t-\tFIM DE JOGO\t-\t*\t-\t*\n");
			
		}else {
			System.out.println("\n\tParabéns! Você venceu o jogo!");
		}
		
	
	}
	
	boolean VerificaPosicao(int posicao) { //VERIFICA SE A POSIÇÃO JÁ ESTÁ OCUPADA
		if(verificar[posicao-1] == 1) {
			System.out.println("\t>Essa palavra já está preenchida! Escolha outro número! :)");
			return false;
		}else {
			return true;
		}
	}
	
	void Desistencia(Tabuleiro tabuleiro, int tema, String palavras[]) { //CASO O USUÁRIO DESISTA, O TABULEIRO É PREENCHIDO E IMPRESSO NA TELA
		char Positions[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		for(int i = 0; i < 10; i++) {
			tabuleiro.Substituir(Positions[i], palavras[i], tabuleiro.getDirecao(Positions[i], tema), tabuleiro.getLinha(Positions[i], tema), tabuleiro.getColuna(Positions[i], tema)); 

		}
	}
	void Dicas(int tema, String palavras[], String status[]) {
		System.out.print("\n");
		if(tema == 1) {
			System.out.println("1. Criatura devota e obediente responsável por serviços de antigas famílias bruxas; (" + palavras[0].length() + " letras) — "+status[0]+"\n"
					+ "2. Feitiço responsável por trazer um objeto até a pessoa que lançou; (" + palavras[1].length() + " letras) — "+status[1]+"\n"
					+ "3. Prisão bruxa localizada uma ilha-fortaleza no meio do Mar do Norte; (" + palavras[2].length() + " letras) — "+status[2]+"\n"
					+ "4. A primeira das três maldições imperdoáveis; (" + palavras[3].length() + " letras) — "+status[3]+"\n"
					+ "5. Matéria dada por Snape do 1º ao 6º ano; (" + palavras[4].length() + " letras) — "+status[4]+"\n"
					+ "6. Primeiro nome do fantasma da Corvinal; (" + palavras[5].length() + " letras) — "+status[5]+"\n"
					+ "7. Par de Hermione no Baile de Inverno ...... Krum (" + palavras[6].length() + " letras) — "+status[6]+"\n"
					+ "8. Hagrid é apresentado como um meio-humano e meio-......; (" + palavras[7].length() + " letras) — "+status[7]+"\n"
					+ "9. Feitiço criado por Snape que Harry usa contra Draco em O enigma do príncipe; (" + palavras[8].length() + " letras) — "+status[8]+"\n"
					+ "10. Item da lufa-lufa usado como horcrux por Voldemort; (" + palavras[9].length() + " letras) — "+status[9]+"\n");
		}else if(tema == 2) {
				
				System.out.println("1. Nome da literatura contemporânea cuja temática principal é a vivência de mulheres negras; (" + palavras[0].length() + " letras) — "+status[0]+"\n"
				+ "2. Classe de palavras responsável por nomeação; (" + palavras[1].length() + " letras) — "+status[1]+"\n"
				+ "3. O bom-......, primeiro romance homossexual da literatura ocidental; (" + palavras[2].length() + " letras) — "+status[2]+"\n"
				+ "4. Língua que originou o Português; (" + palavras[3].length() + " letras) — "+status[3]+"\n"
				+ "5. Nome dado ao contexto em que duas vogais vizinhas se encontram em sílabas distintas; (" + palavras[4].length() + " letras) — "+status[4]+"\n"
				+ "6. Poema composto por 14 versos distribuídos em 4 estrofes; (" + palavras[5].length() + " letras) — "+status[5]+"\n"
				+ "7. Cachorrinha do livro \"Vidas secas\", de Graciliano Ramos; (" + palavras[6].length() + " letras) — "+status[6]+"\n"
				+ "8. Unidade mínima de uma língua com valor distintivo; (" + palavras[7].length() + " letras) — "+status[7]+"\n"
				+ "9. Machado de ......, autor de Dom Casmurro; (" + palavras[8].length() + " letras) — "+status[8]+"\n"
				+ "10. Poetisa macaibense da 2ª geração romântica - ...... de Souza; (" + palavras[9].length() + " letras) — "+status[9]+"\n");
		}
		
	}
}
