package palavrascruzadas;

public class Tabuleiro {
	char[][] tabuleiro = new char[11][18];
	int linhas = 11, colunas = 18;
	
	Tabuleiro(String palavras[], int tema) {
		
		for(int i = 0; i<11; i++) { //inicializando a matriz com vazios;
			for(int j = 0; j<18; j++) {
				tabuleiro[i][j] = ' ';
			}
		}	
			Substituir('1',  palavras[0].toUpperCase(), getDirecao('1', tema), getLinha('1', tema), getColuna('1', tema));
			Substituir('2', "", getDirecao('2', tema), getLinha('2', tema), getColuna('2', tema)); 
			Substituir('3', "", getDirecao('3', tema),  getLinha('3', tema), getColuna('3', tema)); 
			Substituir('4', "", getDirecao('4', tema),  getLinha('4', tema), getColuna('4', tema));
			Substituir('5', "", getDirecao('5', tema),  getLinha('5', tema), getColuna('5', tema)); 
			Substituir('6', "", getDirecao('6', tema),  getLinha('6', tema), getColuna('6', tema)); 
			Substituir('7', "", getDirecao('7', tema),  getLinha('7', tema), getColuna('7', tema)); 
			Substituir('8', "", getDirecao('8', tema),  getLinha('8', tema), getColuna('8', tema));
			Substituir('9', "", getDirecao('9', tema), getLinha('9', tema), getColuna('9', tema));
			Substituir('0', "", getDirecao('0', tema),  getLinha('0', tema), getColuna('0', tema));
	}	
	
	void print() {
		for(int i = 0; i<linhas; i++) {
			for(int j = 0; j<colunas; j++) {
			 
				if(tabuleiro[i][j] != ' ') {
					
						if(tabuleiro[i][j] == '0') { //O '0' é equivalente ao 10 no meu tabuleiro.
							
							System.out.print("1"+tabuleiro[i][j]+" | "); //Aqui imprime especialmente o 1 antes do 0;
						}else{
							
							System.out.print(tabuleiro[i][j]+" | "); //Imprime os números normalmente com uma barra ao lado;
						}
					
				}else{		
					
						System.out.print(" "+" . "); //Imprime a matriz. Os vazios possuem um . ao lado
				}
				
				if(j == 17) {
					
						System.out.println("\n"); //Quebra de linha para manter o padrão de tabuleiro
				}
			}
		}
				
	}
		
	
	
	void Substituir(char numero, String palavra, String direcao, int linha, int coluna) {
		int aux = palavra.length();
		int k = 0; 
		
		/*Determina se a posição é horizontal ou vertical. Isso muda o modo com que as palavras serão dispostas no tabuleiro.*/
		if(direcao == "vertical") { 
			tabuleiro[linha-1][coluna] = numero; 
			
			for(int i = linha; i<linha+aux; i++) {
				tabuleiro[i][coluna] = palavra.charAt(k);
				k++;
			}
			
		}else if(direcao == "horizontal") {
			tabuleiro[linha][coluna-1] = numero;
			
			for(int i = coluna; i<coluna+aux; i++) {
				tabuleiro[linha][i] = palavra.charAt(k);
				k++;
			}
		}
		
	}
	
	int getLinha(char numero, int tema) { //Aponta em qual linha a palavra começará.
		//Muda de acordo com o tema do caça-palavras!
		//Número = número da palavra no jogo. 
		
		char palavras[] = {'1','2','3','4','5','6','7','8','9','0'};
		int linha = 0;
		String lin[];		
		
		if(tema == 1) { //TEMA: HARRY POTTER
				String linhaDaPalavra[] = {"6", "4","9","5","2","4","5","4","2","4"};
				lin = linhaDaPalavra; 
				
		}else if(tema == 2){ //TEMA: PORTUGU�S
				String linhaDaPalavra[] = {"6", "3","4","8","5","1","5","5","1","4"};
				lin = linhaDaPalavra; 
				
		}else {
				String linhaDaPalavra[] = {"0","0","0","0","0","0","0","0","0","0"};
				lin = linhaDaPalavra; 
		}
				/*linhaDaPalavra = linha de início da palavra[i+1]
				 * linhaDaPalavra[0] = linha da palavra[1]*/
		
		
		for(int i  = 0; i<10; i++) {
			/*Percorre o vetor 'palavras' até que o elemento do vetor seja igual ao número recebido*/
			if(numero == palavras[i]) { 
			/*Após relacionar o número recebido com a palavra em questão, pega o elemento lin[i], que aponta a linha em que a palavra começa*/
				linha = Integer.parseInt(lin[i]);
			}
		}
				return linha;
	}
	
	
	int getColuna(char numero, int tema) { //Aponta em que coluna a palavra começaa;
		//Muda de acordo com o tema do caça-palavras!
		//Número = número da palavra no jogo. 
		int coluna = 0;
		char palavras[] = {'1', '2','3','4','5','6','7','8','9','0'};
		String col[];
		
		if(tema == 1) {		 //TEMA: HARRY POTTER
			String colunaDaPalavra[] = {"3","8","4","1","10","4","13","1","1","14"};
			col = colunaDaPalavra; 
			
		}else if(tema == 2){		 //TEMA: PORTUGU�S
			String colunaDaPalavra[] = {"1","3","14","4","6","9","12","17","8","16"};
			col = colunaDaPalavra; 
			
		}else {
			String colunaDaPalavra[] = {"0","0","0","0","0","0","0","0","0","0"};
			col = colunaDaPalavra; 
		}
		
		
		for(int i  = 0; i<10; i++) {
			/*Percorre o vetor 'palavras' até que o elemento do vetor seja igual ao número recebido*/
			if(numero == palavras[i]) {
			/*Após relacionar o número recebido com a palavra, pega o elemento col[i], que aponta a coluna em que a palavra começa*/
				coluna = Integer.parseInt(col[i]);
			}
		}
		
		return coluna;
	}
	
	
	String getDirecao(char position, int tema) { //Indica a posição (horizontal e vertical) de cada palavra.
		//Muda de acordo com o tema do caça-palavras!
		//Número = número da palavra no jogo. 
		char palavras[] = {'1', '2','3','4','5','6','7','8','9','0'};
		char dir[];
		String direction = "";

		
		if(tema == 1){ 	//TEMA: HARRY POTTER
			char direcoes[] = {'h', 'h', 'h', 'h', 'v', 'v', 'v', 'v', 'h', 'v'};
			dir = direcoes;
			
		}else if(tema == 2){ 	//TEMA: PORTUGUÊS
			char direcoes[] = {'h', 'h', 'v', 'h', 'v', 'v', 'v', 'v', 'h', 'v'};
			dir = direcoes;
			
		}else{
			char direcoes[] = {'h', 'h', 'h', 'h', 'v', 'v', 'v', 'v', 'h', 'v'};
			dir = direcoes;
		}
		
		
		for(int i  = 0; i<10; i++) {
			/*Percorre o vetor 'palavras' até que o elemento do vetor seja igual ao número recebido*/
			if(position == palavras[i]) {
			/*Após relacionar o número recebido com a palavra, pega o elemento dir[i], que aponta a orientação da palavra*/
				if(dir[i] == 'h') {
					direction = "horizontal";
				}else {
					direction = "vertical";
				}
					
				}
			}
			return direction;
			
		}
	
	}
	
		



