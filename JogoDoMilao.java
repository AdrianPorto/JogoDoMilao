import java.io.*;
import java.util.*;
// import javax.sound.sampled.Line;

public class JogoDoMilao {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		// rhuan: alterei o tipo da variável de "String" para "int" para que seja feita
		// a verificação laço while
		int opcao = 0;// Variavel para controle de escolha do MENU

		while (opcao < 1 || opcao > 4) {

			System.out.println("==== BEM VINDO AO SHOW DO MILAO =====");
			System.out.println("Escolha uma opção do MENU abaixo: ");

			System.out.println("");
			System.out.println("********** MENU ***********");
			System.out.println(" 1 - JOGAR                 ");
			System.out.println(" 2 - REGRAS                ");
			System.out.println(" 3 - RANKING               ");
			System.out.println(" 4 - PERGUNTAS             ");
			System.out.println("***************************");

			System.out.print("Digite aqui sua opção: ");
			opcao = scan.nextInt();
			System.out.println("Opção Selecionada: " + opcao);

			cleanScreen();

			switch (opcao) {
				case 1:
					System.out.println("Esperamos que esteja pronto!!! INICIANDO JOGO.....");
					vamosJogar();
					break;
				case 2:
					// regras
					System.out.println("Apresentando As Regras.......\n");
					mostraRegras(listaRegras(new File("arquivos/regras.txt")));
					break;
				case 3:
					System.out.println("Ranking Atual:");
					// ranking();
					break;
				case 4:
					System.out.println("Listagem das Perguntas Dispníveis:");
					// perguntas();
					break;
				default:
					System.out.println("Opção Inválida! Digite Novamente...");
			}
		}

		/*
		 * ==============TESTE: UTILIZANDO O METODO 1 - listaRegas==============
		 * System.out.println(listaRegras(new File("src/arquivos/regras.txt")));
		 */

	}// FIM MAIN

	public static Scanner Leitura = new Scanner(System.in);
	public static String Pergu;
	public static String Resposta;
	public static String NivelPergunta;

	public static boolean Confirm = true;
	public static boolean Correto = false;
	public static boolean Errado = false;

	public static Integer nIndex;
	public static char Caracter;''
	public static int Ranking;
	public static int Tentativas;
	public static int Contador = 0;
	public static int Erros = 0;
	public static int numeroTmp;
	public static int rodado = 0;
	public static int aleatorio = 0;
	public static int c, i = 0;
	public static int cont_y = 12;
	public static int cont = 0;
	public static int cont_x = 01;
	public static int NivelIndex = 3;
	public static int[] numeros = new int[cont_y];

	// METODO 1
	public static void vamosJogar() {

		for (Tentativas = 0; Tentativas < 12; Tentativas++) {
			if (Erros == 3)
				break;

			if (Tentativas == 12) {
				break;
			} else {

				Pergunta_E_Alternativas();
				AlternativaCorreta(nIndex, Resposta);
				System.out.println("erros: " + Erros);
				cleanScreen();
			}

		}
	}

	// METODO 1.1 - Pega um número aleatório
	public static int pegaAleatorio() {
		if (rodado == 1) {

		} else {
			Random random = new Random();

			int Encontrar = 0;

			// contruindo o array sem repetição

			for (i = 0; i < NivelIndex; i++) {

				Encontrar = random.nextInt(NivelIndex) + 1;
				if (i == 0) {
					numeros[i] = Encontrar;
				} else {
					c = 0;
					while (c < i) {
						if (numeros[c] == Encontrar) {
							Encontrar = random.nextInt(NivelIndex) + 1;
							c = 0;
						} else {
							c++;
						}
						// --------- Facil --------
						if (i == 0) {

						}
						// --------- Média --------
						if (i == 2) {
							NivelIndex = 7;
						}
						// --------- Dificíl -------
						if (i == 6) {
							NivelIndex = 12;
						}
					}
				}

				numeros[i] = Encontrar;
			}
		}

		/*
		 * ordernação: caso deesja ver os numeros gerados na ordem crescente.
		 * for(i = 0; i < numerongth ; i++)
		 * for(int j = i + 1; j < numerongth ; j++)
		 * {
		 * if (numero > numero)
		 * {
		 * int numero numero;
		 * numero = numero;
		 * numero = numero }
		 * }
		 */

		// Apresentar na tela o resultado
		// for(i = 0; i < numeros.length ; i++)
		// {
		// System.out.print(numeros[i] + " ");
		// }

		aleatorio = numeros[cont];

		if (cont < 11) {
			cont += 1;
		}

		if (Tentativas == 0) {
			NivelPergunta = "Facil";
		}

		if (Tentativas == 3) {
			NivelPergunta = "Media";
		}

		if (Tentativas == 8) {
			NivelPergunta = "Dificil";
		}

		rodado = 1;

		return aleatorio;

	}

	// Metodo 1.2 - Seleciona a Pergunta
	public static String Pergunta(int aleatorio) {

		try {
			File dir = new File("C:\\Users\\gusto\\Downloads\\Trabalho Faculdade");
			File Perguntas = new File(dir, "Perguntas.txt");

			@SuppressWarnings("resource")
			// Indicamos o arquivo que será lido
			BufferedReader ler = new BufferedReader(new FileReader(Perguntas));

			// while((Pergu =ler.readLine())!=null)
			// {
			// System.out.println(Pergu);
			// }

			// String que irá receber cada linha do arquivo

			int Contador = aleatorio;
			int i = 1;

			if (Contador <= 8) {
				while ((Pergu = ler.readLine()) != null) {
					if (i == Contador) {
						return Pergu;
					}

					i = i + 1;

				}
			}

			// -------------Perguntas Dificeis------------------

			if (Contador > 8) {
				while ((Pergu = ler.readLine()) != null) {
					if (i == Contador) {
						return Pergu;
					} else {
						i = i + 1;

					}
				}
			}
			// liberamos o fluxo do Scanner
			if (Erros == 3) {
				ler.close();
			}

		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;
	}

	// Método 1.3
	public static void Pergunta_E_Alternativas() {
		int nAleatorio = pegaAleatorio();
		String nPergunta = Pergunta(nAleatorio);

		nIndex = nAleatorio;

		Integer.valueOf(nIndex).intValue();

		Contador = 2;

		System.out.println("                          " + NivelPergunta + "\n");
		System.out.print(cont_x);
		cont_x++;

		while (nPergunta.charAt(Contador) != '/') {

			if (nPergunta.charAt(Contador) == ':') {
				System.out.println();
				System.out.println();
				Contador += 1;

			}
			if (nPergunta.charAt(Contador) == '.') {
				System.out.println();
				Contador += 1;
			}

			System.out.print(nPergunta.charAt(Contador));
			Contador += 1;

		}
		Contador = 0;
		System.out.println("\n");

		Confirm = true;

		System.out.printf("Ranking atual: %d", Ranking);
		System.out.println("\n");

		while (Confirm == true) {

			System.out.print("Resposta: ");
			Resposta = Leitura.nextLine();

			Resposta = Resposta.toUpperCase();
			// System.out.println(Resposta);

			switch (Resposta) {

				case "A":
					Confirm = false;
					break;

				case "B":
					Confirm = false;
					break;

				case "C":
					Confirm = false;
					break;

				case "D":
					Confirm = false;
					break;

				case "E":
					Confirm = false;
					break;

				default:
					System.out.println("Por favor , insira uma alternativa");

					Confirm = true;

			}

		}
	}

	// Metodo 1.4
	public static void AlternativaCorreta(Integer Index, String nAlternativa) {
		HashMap<Boolean, String> Alternativa = new HashMap<Boolean, String>();

		Alternativa.put(1, "C");
		Alternativa.put(2, "C");
		Alternativa.put(3, "A");
		Alternativa.put(4, "A");
		Alternativa.put(5, "B");
		Alternativa.put(6, "C");
		Alternativa.put(7, "C");
		Alternativa.put(8, "A");
		Alternativa.put(9, "D");
		Alternativa.put(10, "B");
		Alternativa.put(11, "B");
		Alternativa.put(12, "B");

		String AleternativaInserida = Alternativa.get(Index);
		if (AleternativaInserida == "A" || AleternativaInserida == "B" || AleternativaInserida == "C" ||
				AleternativaInserida == "D" || AleternativaInserida == "E") {

		}
		if (AleternativaInserida.equals(nAlternativa)) {

			Correto = true;
			ranking();
			Correto = false;

		} else {
			Errado = true;
			ranking();
			Errado = false;
		}

	}

	// METODO 2
	public static HashMap<Integer, String[]> listaRegras(File regras) {
		HashMap<Integer, String[]> regra = new HashMap<Integer, String[]>();
		int chave = 1;

		try {
			Scanner scan = new Scanner(regras);

			while (scan.hasNextLine()) {
				regra.put(chave, scan.nextLine().split(","));
				chave++;
			}
			scan.close();
			return regra;

		} catch (IOException e) {
			System.out.println("Erro ao Tentar ler o arquivo");
			e.printStackTrace();
			return null;
		}
	}

	// METODO 2.1
	public static void mostraRegras(HashMap<Integer, String[]> mRegras) {
		for (String[] regra : mRegras.values()) {
			System.out.print("ID: " + regra[0] + " =>");
			System.out.println(" Descricao:" + regra[1]);
		}
		System.out.println("\nFinal da apresentacao!!!");
	}

	// METODO 3
	public static void ranking() {
		if (Correto == true) {
			Ranking += 100;
		} else {
			Ranking += 0;
		}
		if (Errado == true) {
			Erros += 1;

		}
	}

	// METODO 4
	public static HashMap<Integer, String[]> listaPerguntas(File Perguntas) {
		HashMap<Integer, String[]> pergunta = new HashMap<Integer, String[]>();
		int chave = 1;
		try {
			Scanner scan = new Scanner(Perguntas);

			while (scan.hasNextLine()) {
				pergunta.put(chave, scan.nextLine().split(","));
				chave++;
			}
			scan.close();
			return pergunta;
		} catch (IOException e) {
			System.out.println("Erro ao Tentar ler o arquivo");
			e.printStackTrace();
			return null;
		}
	}

	// METODO 4.1
	public static void mostraPerguntas(HashMap<Integer, String[]> mPerguntas) {
		for (String[] pergunta : mPerguntas.values()) {
			System.out.print("Pergunta: " + pergunta[0] + " =>");
			System.out.println(" Alternativa 1: " + pergunta[1]);
			System.out.println(" Alternativa 1: " + pergunta[2]);
			System.out.println(" Alternativa 1: " + pergunta[3]);
			System.out.println(" Alternativa 1: " + pergunta[4]);
		}
		System.out.println("\nFinal da apresentacao!!!");
	}

	// METODO 5: Utilizado para limpar a tela
	public static void cleanScreen() {
		for (int i = 0; i <= 10; i++) {
			System.out.println("\n");
		}
	}

}