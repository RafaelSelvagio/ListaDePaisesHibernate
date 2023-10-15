package dao;

import model.Pais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO {
	/*
	 * Atributo do tipo privado e string
	 * Utilizamos o atributo url para determinar o caminho do banco de dados que vamos conectar.
	 * localhost: significa que nosso banco de dados está rodando no proprio computador, em ambiente local
	 * 3306: 	  é a porta que utilizaremos para ter acesso ao banco de dados
	 * PrimeiroBanco: é o nome da nossa base de dados
	 */
	private final String url = "jdbc:mysql://localhost:3306/PrimeiroBanco";
	
	/*
	 * Atributos privados e do tipo string
	 * usuario e senha: são os dados para acessar a base de dados
	 */
    private final String usuario = "root";
    private final String senha = "aluno";
    
    /*
     * Atributo privado do tipo connection
     * Atributo que receberá a conexão com o banco de dados
     */
    private Connection connection;
    
    /*
     * Método que adiciona um país, recebido por parâmetro, a listaDePaises
     */
    public void adicionarPais(Pais pais) {
    	// Estabelece uma conexão com o banco de dados usando as informações fornecidas.
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {

            // Cria uma string SQL que representa uma instrução de inserção na tabela 'pais' com duas colunas: 'nome' e 'capital'.
            String sql = "INSERT INTO pais (nome, capital) VALUES (?, ?)";

            // Cria um objeto PreparedStatement a partir da conexão estabelecida e da string SQL.
            // Isso permite que você execute consultas parametrizadas, evitando injeções de SQL.
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Define o primeiro parâmetro da instrução SQL preparada como o valor do 'nome' do objeto 'pais'.
            preparedStatement.setString(1, pais.getNome());

            // Define o segundo parâmetro da instrução SQL preparada como o valor da 'capital' do objeto 'pais'.
            preparedStatement.setString(2, pais.getCapital());

            // Executa a instrução SQL preparada, que neste caso é uma operação de inserção no banco de dados.
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            // Captura exceções do tipo SQLException que podem ocorrer durante a execução do código no bloco 'try'.

            // Se ocorrer uma exceção SQL, imprime informações detalhadas sobre a exceção, incluindo a mensagem de erro e a pilha de chamadas.
            e.printStackTrace();
        }

    }

    /*
     * Método que retorna a lista de países
     */
    public List<Pais> obterListaDePaisess() {
    	// Cria uma lista para armazenar objetos do tipo 'Pais'.
    	List<Pais> listaDePaises = new ArrayList<>();

    	// Tenta estabelecer uma conexão com o banco de dados usando as informações fornecidas.
    	try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
    	    
    	    // Define uma string SQL que representa uma consulta de seleção (SELECT) que obtém todos os registros da tabela 'pais'.
    	    String sql = "SELECT * FROM pais";
    	    
    	    // Cria um objeto 'Statement' a partir da conexão estabelecida.
    	    Statement statement = connection.prepareStatement(sql);
    	    
    	    // Executa a consulta SQL e obtém um conjunto de resultados (ResultSet).
    	    ResultSet resultSet = statement.executeQuery(sql);
    	    
    	    // Itera sobre o conjunto de resultados para processar cada registro retornado.
    	    while (resultSet.next()) {
    	        // Obtém os valores das colunas 'nome' e 'capital' do registro atual no conjunto de resultados.
    	        String nome = resultSet.getString("nome");
    	        String capital = resultSet.getString("capital");
    	        
    	        // Cria um objeto 'Pais' com os valores obtidos do banco de dados.
    	        Pais pais = new Pais(nome, capital);
    	        
    	        // Adiciona o objeto 'Pais' à lista 'listaDePaises'.
    	        listaDePaises.add(pais);
    	    }
    	} catch (SQLException e) {
    	    // Captura exceções do tipo SQLException que podem ocorrer durante a execução do código no bloco 'try'.

    	    // Se ocorrer uma exceção SQL, imprime informações detalhadas sobre a exceção, incluindo a mensagem de erro e a pilha de chamadas.
    	    e.printStackTrace();
    	}
		

    	// Retorna a lista
    	return listaDePaises;
    }
    
    public List<Pais> obterListaDePaises() {
    	// Cria uma lista para armazenar objetos do tipo 'Pais'.
    	List<Pais> listaDePaises = new ArrayList<>();

    	// Tenta estabelecer uma conexão com o banco de dados usando as informações fornecidas.
    	try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
    	    
    	    // Define uma string SQL que representa uma consulta de seleção (SELECT) que obtém todos os registros da tabela 'pais'.
    	    String sql = "SELECT * FROM pais";
    	    
    	    // Cria um objeto 'PreparedStatement' a partir da conexão estabelecida, que é mais eficiente para consultas parametrizadas.
    	    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    	    
    	    // Executa a consulta SQL e obtém um conjunto de resultados (ResultSet).
    	    ResultSet resultSet = preparedStatement.executeQuery();
    	    
    	    // Itera sobre o conjunto de resultados para processar cada registro retornado.
    	    while (resultSet.next()) {
    	        // Obtém os valores das colunas 'nome' e 'capital' do registro atual no conjunto de resultados.
    	        String nome = resultSet.getString("nome");
    	        String capital = resultSet.getString("capital");
    	        
    	        // Cria um objeto 'Pais' com os valores obtidos do banco de dados.
    	        Pais pais = new Pais(nome, capital);
    	        
    	        // Adiciona o objeto 'Pais' à lista 'listaDePaises'.
    	        listaDePaises.add(pais);
    	    }
    	} catch (SQLException e) {
    	    // Captura exceções do tipo SQLException que podem ocorrer durante a execução do código no bloco 'try'.

    	    // Se ocorrer uma exceção SQL, imprime informações detalhadas sobre a exceção, incluindo a mensagem de erro e a pilha de chamadas.
    	    e.printStackTrace();
    	}

    	// Retorna a lista de países preenchida com os dados obtidos do banco de dados.
    	return listaDePaises;

    }


    /*
     * Método que remove um pais, recebido por parâmetro, da listaDePaises
     */
    public void removerPais(Pais pais) {
    	// Tenta estabelecer uma conexão com o banco de dados usando as informações fornecidas.
    	try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
    	    
    	    // Define uma string SQL que representa uma consulta de exclusão (DELETE) para remover um registro da tabela 'pais' com base no nome.
    	    String sql = "DELETE FROM pais WHERE nome = ?";
    	    
    	    // Cria um objeto 'PreparedStatement' a partir da conexão estabelecida, que é mais eficiente para consultas parametrizadas.
    	    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    	    
    	    // Define o valor do parâmetro na consulta SQL com o nome obtido do objeto 'pais' fornecido como argumento.
    	    preparedStatement.setString(1, pais.getNome());
    	    
    	    // Executa a consulta SQL para excluir o registro correspondente com base no nome especificado.
    	    preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    	    // Captura exceções do tipo SQLException que podem ocorrer durante a execução do código no bloco 'try'.

    	    // Se ocorrer uma exceção SQL, imprime informações detalhadas sobre a exceção, incluindo a mensagem de erro e a pilha de chamadas.
    	    e.printStackTrace();
    	}
    }

    /*
     * Método que limpa a lista de países
     */
    public void limparListaDePaises() {
    	// Tenta estabelecer uma conexão com o banco de dados usando as informações fornecidas.
    	try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
    	    
    	    // Define uma string SQL que representa uma consulta para truncar (remover todos os registros) a tabela 'pais'.
    	    String sql = "TRUNCATE TABLE pais";
    	    
    	    // Cria um objeto 'PreparedStatement' a partir da conexão estabelecida para executar a consulta SQL.
    	    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    	    
    	    // Executa a consulta SQL para truncar a tabela 'pais', ou seja, remover todos os registros.
    	    preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    	    // Captura exceções do tipo SQLException que podem ocorrer durante a execução do código no bloco 'try'.

    	    // Se ocorrer uma exceção SQL, imprime informações detalhadas sobre a exceção, incluindo a mensagem de erro e a pilha de chamadas.
    	    e.printStackTrace();
    	}

    }

    /*
     * Método que obtem e retorna o primeiro país da lista
     */
    public Pais obterPrimeiroPais() {
    	// Tenta estabelecer uma conexão com o banco de dados usando as informações fornecidas.
    	try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
    	    
    	    // Define uma string SQL que representa uma consulta para selecionar o primeiro registro da tabela 'pais'.
    	    String sql = "SELECT * FROM pais LIMIT 1;";
    	    
    	    // Cria um objeto 'PreparedStatement' a partir da conexão estabelecida para executar a consulta SQL.
    	    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    	    
    	    // Executa a consulta SQL e armazena o resultado em um objeto 'ResultSet'.
    	    ResultSet resultSet = preparedStatement.executeQuery();
    	    
    	    // Verifica se há algum registro no resultado (selecionamos apenas um) e processa-o.
    	    if (resultSet.next()) {
    	        // Extrai os valores das colunas 'nome' e 'capital' do resultado.
    	        String nome = resultSet.getString("nome");
    	        String capital = resultSet.getString("capital");
    	        
    	        // Cria um objeto 'Pais' com os valores obtidos e o retorna.
    	        return new Pais(nome, capital);
    	    }
    	} catch (SQLException e) {
    	    // Captura exceções do tipo SQLException que podem ocorrer durante a execução do código no bloco 'try'.

    	    // Se ocorrer uma exceção SQL, imprime informações detalhadas sobre a exceção, incluindo a mensagem de erro e a pilha de chamadas.
    	    e.printStackTrace();
    	}

    	// Retorna 'null' se não houver registros na tabela ou se ocorrer uma exceção.
    	return null;

    }

    /*
     * Os métodos obterTerceiroPais e obterQuartoPais são semelhantes  ao  método
     * obterPrimeiroPais.
     */
    public Pais obterTerceiroPais() {
    	// Tenta estabelecer uma conexão com o banco de dados usando as informações fornecidas.
    	try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
    	    
    	    // Define uma string SQL que representa uma consulta para selecionar o terceiro registro (índice 2) da tabela 'pais'.
    	    String sql = "SELECT * FROM pais LIMIT 2, 1;";
    	    
    	    // Cria um objeto 'PreparedStatement' a partir da conexão estabelecida para executar a consulta SQL.
    	    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    	    
    	    // Executa a consulta SQL e armazena o resultado em um objeto 'ResultSet'.
    	    ResultSet resultSet = preparedStatement.executeQuery();
    	    
    	    // Verifica se há algum registro no resultado (selecionamos apenas um) e processa-o.
    	    if (resultSet.next()) {
    	        // Extrai os valores das colunas 'nome' e 'capital' do resultado.
    	        String nome = resultSet.getString("nome");
    	        String capital = resultSet.getString("capital");
    	        
    	        // Cria um objeto 'Pais' com os valores obtidos e o retorna.
    	        return new Pais(nome, capital);
    	    }
    	} catch (SQLException e) {
    	    // Captura exceções do tipo SQLException que podem ocorrer durante a execução do código no bloco 'try'.

    	    // Se ocorrer uma exceção SQL, imprime informações detalhadas sobre a exceção, incluindo a mensagem de erro e a pilha de chamadas.
    	    e.printStackTrace();
    	}

    	// Retorna 'null' se não houver registros na tabela ou se ocorrer uma exceção.
    	return null;

    }
    
    public Pais obterQuartoPais() {
    	// Verifica se a conexão com o banco de dados foi aberta com sucesso.
    	if (abreConexao()) {
    	    try {
    	        // Define uma string SQL que representa uma consulta para selecionar o quarto registro (índice 3) da tabela 'pais'.
    	        String sql = "SELECT * FROM pais LIMIT 3, 1;";
    	        
    	        // Cria um objeto 'PreparedStatement' a partir da conexão estabelecida para executar a consulta SQL.
    	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
    	        
    	        // Executa a consulta SQL e armazena o resultado em um objeto 'ResultSet'.
    	        ResultSet resultSet = preparedStatement.executeQuery();
    	        
    	        // Verifica se há algum registro no resultado (selecionamos apenas um) e processa-o.
    	        if (resultSet.next()) {
    	            // Extrai os valores das colunas 'nome' e 'capital' do resultado.
    	            String nome = resultSet.getString("nome");
    	            String capital = resultSet.getString("capital");
    	            
    	            // Cria um objeto 'Pais' com os valores obtidos e o retorna.
    	            return new Pais(nome, capital);
    	        }
    	    } catch (SQLException e) {
    	        // Captura exceções do tipo SQLException que podem ocorrer durante a execução do código no bloco 'try'.
    	        
    	        // Se ocorrer uma exceção SQL, imprime informações detalhadas sobre a exceção, incluindo a mensagem de erro e a pilha de chamadas.
    	        e.printStackTrace();
    	    } finally {
    	        // O bloco 'finally' garante que a conexão com o banco de dados seja sempre fechada, independentemente de ocorrer ou não uma exceção.
    	        fechaConexao(); // Certifique-se de fechar a conexão quando terminar.
    	    }
    	}

    	// Retorna 'null' se não houver registros na tabela, se ocorrer uma exceção ou se a conexão não foi aberta com sucesso.
    	return null;

    }
    



    
    
    
    
    public boolean testaConexao() {
    	// Tenta abrir uma conexão com o banco de dados e armazena se a conexão foi bem-sucedida
    	boolean conectou = abreConexao();

    	// Fecha a conexão com o banco de dados, liberando recursos
    	fechaConexao();

    	// Retorna se a conexão foi estabelecida com sucesso (true) ou não (false)
    	return conectou;

    }
    
    public boolean abreConexao() {
    	try {
    	    // Carrega o driver JDBC específico (substitua pelo driver do seu banco de dados)
    	    Class.forName("com.mysql.cj.jdbc.Driver");

    	    // Estabelece a conexão com o banco de dados
    	    connection = DriverManager.getConnection(url, usuario, senha);

    	    // Verifica se a conexão foi estabelecida com sucesso
    	    if (connection != null) {
    	        return true; // Retorna 'true' se a conexão foi bem-sucedida
    	    } else {
    	        return false; // Retorna 'false' se a conexão não foi bem-sucedida
    	    }
    	} catch (ClassNotFoundException | SQLException e) {
    	    // Captura exceções do tipo ClassNotFoundException (quando não é possível carregar o driver JDBC) e SQLException (quando ocorre um erro de SQL).
    	    
    	    // Imprime informações detalhadas sobre a exceção, incluindo a mensagem de erro e a pilha de chamadas.
    	    e.printStackTrace();
    	    
    	    return false; // Retorna 'false' se ocorrer uma exceção
    	}

    }
    
    public void fechaConexao() {
    	try {
    	    // Verifica se a conexão não é nula (ou seja, se a conexão foi previamente estabelecida)
    	    if (connection != null) {
    	        // Fecha a conexão com o banco de dados
    	        connection.close();
    	    }
    	} catch (SQLException e) {
    	    // Captura exceções do tipo SQLException (quando ocorre um erro de SQL).

    	    // Imprime informações detalhadas sobre a exceção, incluindo a mensagem de erro e a pilha de chamadas.
    	    e.printStackTrace();
    	}

    }
}
