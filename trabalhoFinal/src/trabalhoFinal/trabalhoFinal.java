/*
Objetivo de programa

	Criar um sistema de gerenciamento de estoque, venda e lucro de uma loja.

Programadores
	Ana Luiza De Sousa Silva
	Ricardo Cardoso Alencar
	Gustavo Lara Marçal
	
Data de escrita: 22/06/2022
Data da ultima Atualização: 01/06/2022

*/

package trabalhoFinal;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class trabalhoFinal {
	static Scanner entrada = new Scanner(System.in);

	static int i = 0, totalVendas, comprar;;
	static int[][] estoque = new int[4][4];

	public static boolean temEstoque(int[][] estoque, int variavel) { // metodo que verifica com boolean se o produto
																		// está em
																		// estoque ou não e da o return
		boolean temEstoque;

		if (estoque[variavel][0] > 0) {
			temEstoque = true;
		} else {
			temEstoque = false;
		}

		return temEstoque;
	}

	public static void comprar(int[][] estoque) { // metodo criado para simular um cliente comprando
		int venda, i = 1;

		while (i == 1) {
			// o cliente escolhe se deseja fazer uma compra ou não
			System.out.println(
					"Esolha qual mascara deseja comprar:\n 0-Infantil lisa\n 1- intantil com estampa\n 2- adulto lisa"
							+ "\n 3- adulto com estampa ");
			venda = entrada.nextInt();
			// se tiver estoque do produto entra no if
			boolean temEstoque = temEstoque(estoque, venda);
			if (temEstoque == true) {
				estoque[venda][0] = estoque[venda][0] - 1;
				System.out.println("Produto vendido");
				estoque[venda][3] += 1;
				System.out.println("Agora a quantidade de mascara " + venda + " em estoque é de: " + estoque[venda][0]);
			} else {
				System.out.println("Produto não está disponivel no estoque");
			}

			System.out.println("Digite 1 caso queira fazer com nova compra, ou 0 caso queira parar de comprar");
			i = entrada.nextInt();
		}
	}

	public static void main(String[] args) throws IOException { // Metodo main para exibir um menu de escolha para o
																// usuario

		int controle = 0;
		Scanner ler = inicializar();

		while (controle != 10) { // menu de escolha

			System.out.println("Sistema de Vendas");
			System.out.println("Digite 1 para exibir relatorio de vendas e lucro");
			System.out.println("Digite 2 para exibir relatorio de mascaras em estoque");
			System.out.println("Digite 3 para realizar venda");
			System.out.println("Digite 10 para sair");
			controle = entrada.nextInt();
			switch (controle) { // switch chamando cada metodo de acordo com a escolha do usuario

			case 1:
				relatorioVendasLucro();
				break;
			case 2:
				relarioEstoque();
				break;
			case 3:
				comprar(estoque);
				break;
			case 10:
				System.out.println("Saindo");

			}
		}
		ler.close();
		{

		}
		entrada.close();

	}
		//metodo calculando o lucro e retornando o resultado
	private static int lucro(int[] produto) { 
		int lucro = (produto[2] - produto[1]) * produto[3];

		return lucro;
	}
		// metodo para visualizar o relatorio do Estoque de mascaras e enviar para o arquivo
	private static void relarioEstoque() { 
		try {
			//imprimindo o relatorio
			System.out.println("O estoque de mascaras infantil lisa é: " + estoque[0][0]
					+ " \nO estoque de mascaras infantil estampada é: " + estoque[1][0]
					+ " \nO estoque de mascaras adulto lisa é: " + estoque[2][0]
					+ " \nO estoque de mascaras adulto estampada é: " + estoque[3][0]);
			
			//enviando relatorio para arquivo
			FileWriter relatorios = new FileWriter("C:\\temp\\relatorioEstoque.txt");
			relatorios.write("O estoque de mascaras infantil lisa é: " + estoque[0][0]
					+ " \nO estoque de mascaras infantil estampada é: " + estoque[1][0]
					+ " \nO estoque de mascaras adulto lisa é: " + estoque[2][0]
					+ " \nO estoque de mascaras adulto estampada é: " + estoque[3][0]);
			relatorios.close();
		} catch (IOException e) {
			System.out.println("Invalido");
			e.printStackTrace();
		}
	}
		// metodo para visualizar o relatorio de Vendas e Lucro do dia e enviar para o arquivo
	public static void relatorioVendasLucro() { 
		totalVendas = estoque[0][3] + estoque[1][3] + estoque[2][3] + estoque[3][3];

		try {
			//enviando relatorio para arquivo
			FileWriter relatorios = new FileWriter("C:\\temp\\relatorioVendaLucro.txt");
			relatorios.write(
					"O total de mascaras infantil lisa vendidas hoje foi de: " + estoque[0][3] + " e o lucro foi de: "
							+ lucro(estoque[0]) + " \nO total de mascaras infantil estampada vendidas hoje foi de: "
							+ estoque[1][3] + " e o lucro foi de: " + lucro(estoque[1])
							+ " \nO total de mascaras adulto lisa vendidas hoje foi de: " + estoque[2][3]
							+ " e o lucro foi de: " + lucro(estoque[2])
							+ " \nO total de mascaras adulto estampada vendidas hoje foi de: " + estoque[3][3]
							+ " e o lucro foi de: " + lucro(estoque[3])
							+ " \nQuantidade de mascaras de todos os tipos vendidas hoje: " + totalVendas);

			//imprimindo o relatorio
			System.out.println(
					"O total de mascaras infantil lisa vendidas hoje foi de: " + estoque[0][3] + " e o lucro foi de: "
							+ lucro(estoque[0]) + " \nO total de mascaras infantil estampada vendidas hoje foi de: "
							+ estoque[1][3] + " e o lucro foi de: " + lucro(estoque[1])
							+ " \nO total de mascaras adulto lisa vendidas hoje foi de: " + estoque[2][3]
							+ " e o lucro foi de: " + lucro(estoque[2])
							+ " \nO total de mascaras adulto estampada vendidas hoje foi de: " + estoque[3][3]
							+ " e o lucro foi de: " + lucro(estoque[3])
							+ " \nQuantidade de mascaras de todos os tipos vendidas hoje: " + totalVendas);
			relatorios.close();
		} catch (IOException e) {
			System.out.println("Invalido");
			e.printStackTrace();
		}
	}
		//metodo para inicializar as varivaies e ler o arquivo com os dados de estoque, preço de compra e preço de venda
	private static Scanner inicializar() throws IOException {
		
		//as linhas são : 0 = infantil lisa, 1 = infantil estampada, 2 = adulta lisa, 3 = adulta estampada
		//as colunas são quantida de estoque, preço de custo, preço de venda e total de vendas que vai somar sempre que tiver uma
		//nova compra
		estoque[0][3] = 0;
		estoque[1][3] = 0;
		estoque[2][3] = 0;
		estoque[3][3] = 0;
		//arquivo com os dados
		Path arquivo = Paths.get("C:\\temp\\dadosMascara.txt");
		Scanner ler = new Scanner(arquivo);
		while (ler.hasNextLine()) {
			String[] dadosProdutos = ler.nextLine().split(";");
			estoque[i][0] = Integer.parseInt(dadosProdutos[0]);
			estoque[i][1] = Integer.parseInt(dadosProdutos[1]);
			estoque[i][2] = Integer.parseInt(dadosProdutos[2]);
			i++;
		}
		//retornar para main
		return ler;
	}
}
