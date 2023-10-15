package main;
import java.util.Scanner;
import controller.PaisController;

public class Main {
	public static void main(String[] args) {
		PaisController paisController = new PaisController();
		Scanner scanner = new Scanner(System.in);
		
		int opcao = -1;
		
		while (opcao != 0) {

			// Menu da nossa aplicação
			System.out.println("==== Menu ====");
			System.out.println("1. Adicionar país");
			System.out.println("2. Listar países");
			System.out.println("3. Buscar país");
			System.out.println("4. Remover país da lista");
			System.out.println("5. Atualizar país");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");
			
			try {

				opcao = scanner.nextInt();
	
				switch (opcao) {
	
				case 1:
					System.out.print("Digite o nome do país: ");
					scanner.nextLine();
					String nome = scanner.nextLine();
	
					System.out.print("Digite a capital do país " + nome + ": ");
					String capital = scanner.nextLine();
	
					paisController.adicionarPais(nome, capital);
					break;
					
				case 2:
					paisController.listarPaises();
					break;
					
				case 3:
					System.out.print("Digite o nome do país que deseja buscar: ");
					scanner.nextLine();
					String nomeBusca = scanner.nextLine();
					
					paisController.buscaPaisPorNome(nomeBusca);
					break;
	
				case 4:
					System.out.println("Digite o ID do país a ser removido: ");

					Integer idPaisRemover = scanner.nextInt();
	
					paisController.removerPais(idPaisRemover);
					break;
					
				case 5:
					System.out.println("Digite o ID do país que deseja atualizar: ");
					Integer idPaisAtualizar = scanner.nextInt();
					
					System.out.print("Digite o nome do país: ");
					scanner.nextLine();
					String nomeAtualizar = scanner.nextLine();
	
					System.out.print("Digite a capital do país " + nomeAtualizar + ": ");
					String capitalAtualizar = scanner.nextLine();
	
					paisController.atualizarPais(idPaisAtualizar, nomeAtualizar, capitalAtualizar);
					break;
	
				case 0:
					System.out.println("Saindo...");
					break;
	
				default:
					System.out.println("Opção inválida.");
					break;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Opção inválida. Por favor, digite um número inteiro válido.");
                scanner.nextLine();
			}
		}
	}
}
