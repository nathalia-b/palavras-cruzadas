package palavrascruzadas;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		int tema = 0, opcao = 0, voltar = 1;
		Jogo jogo = new Jogo(); 
		do{
			try {
				jogo.Menu(0);
				System.out.print("\t\t> ");
				opcao = ler.nextInt();
				if(opcao == 1) {
						do { 	//LAÇO CASO A OPÇÃO ESCOLHIDA SEJA DIFERENTE DE 1 OU 2;
							try {
								jogo.Menu(1);
								System.out.print("\t\t> ");
								tema = ler.nextInt();
								if(tema < 1 || tema > 2) {
									System.out.println("Opção inválida, tente novamente.");
								}
							}catch(InputMismatchException e) {
								ler.nextLine();
								System.out.print("\nOpção inválida! Tente informar o NÚMERO do tema ;)\n");
							}
						}while(tema < 1 || tema > 2);
						jogo = new Jogo(tema);
						
				}else if(opcao == 2) {
					do {
						
						System.out.println("\n\t\t\t\t> C O M O  J O G A R ?\n");
						System.out.println("\tO objetivo é encontrar todas as palavras usando as dicas disponíveis.\n"
								+ "Conforme algumas palavra são preenchidas, algumas letras de outras palavras aparecem,\n"
								+ "\t\t\to que facilita bastante a resolução.");
						try {
							System.out.println("\n\t> Digite 0 para voltar");
							voltar = ler.nextInt();
							
						}catch(InputMismatchException e) {
							ler.nextLine();
							System.out.print("\t> Opção inválida! Digite 0 (zero) para voltar.\n");
						}
						
					}while(voltar != 0);
					
				}
				
				if(opcao < 1 || opcao > 3) {
					System.out.println("Opção inválida, tente novamente.");
				}
			}catch(InputMismatchException e) {
				ler.nextLine();
				System.out.print("Essa não é uma opção válida. Tente um número.\n");
			}
		}while(opcao != 3);
		System.out.print("\tSaindo...\n");
		System.out.print("\tAté mais! :)\n");

		ler.close();
	}
	
}

