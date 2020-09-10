package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Funcionario;

public class ExercicioPropostoPipeline {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<Funcionario> lista = new ArrayList<>();
		
		System.out.print("Informe o caminho do arquivo: ");
		String caminho = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
			String linha = br.readLine();
			while(linha!= null) {
				String[] campo = linha.split(",");
				lista.add(new Funcionario(campo[0], campo[1], Double.parseDouble(campo[2])));
				linha = br.readLine();
			}
		
			System.out.print("Entre o sal�rio: ");
			Double salario = Double.parseDouble(sc.next());
			
			//criando um Comparator para filtrar a lista de emails
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			//criar a lista de emails com salarios maiores que o informado
			List<String> email = lista.stream()
					.filter(f -> f.getSalario() > salario) // realiza o filtro com a express�o lambda
					.map(p -> p.getEmail()) //cria a lista somente dos email do filtro que foi executado
					.sorted(comp) //realiza a ordena��o e ordem alfabetica da lista de emails utilizando a classe comparator
					.collect(Collectors.toList()); //converte o stream em uma lista
			
			//imprimindo a lista dos email dos funcion�rios com sal�ro superior ao informado pelo usu�rio
			email.forEach(System.out::println);
			
			Double soma = lista.stream() //cria uma variavel que recebe a lista como stream
					.filter(p -> p.getNome().charAt(0)=='M') //filtra o conte�do da lista com os nomes que come�am com M
					.map(p -> p.getSalario()) //pega os sal�rio desta lista
					.reduce(0.0, (x,y) -> x + y); // realiza a soma dos itens da lista com a express�o lambda
			
			System.out.print("Soma dos sal�rios dos empregados que come��o com a letra M: "+String.format("%.2f",soma));
			
		}catch(IOException e) {
			System.out.println("Erro: "+e.getMessage());
		}
		
	}

}
