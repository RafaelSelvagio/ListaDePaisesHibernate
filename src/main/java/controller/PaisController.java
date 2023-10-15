package controller;

import dao.PaisHibernate;
import model.Pais;
import view.PaisView;

public class PaisController {
	private PaisHibernate paisHibernate;
	private PaisView paisView;

	public PaisController() {
		paisHibernate = new PaisHibernate();
		paisView = new PaisView();
	}

	public void adicionarPais(String nome, String capital) {
		Pais pais = new Pais(nome, capital);

		paisHibernate.adicionarPais(pais);
	}
	
	public void atualizarPais(Integer id, String nome, String capital) {
		boolean paisAtualizado = paisHibernate.atualizarPais(id, nome, capital);
		
		paisView.atualizarPais(paisAtualizado);
	}
	
	public void buscaPaisPorNome(String nome) {
		paisView.exibirPais(paisHibernate.buscarPaisPorNome(nome));
	}
	
	public void listarPaises() {
		paisView.exibirListaDePaises(paisHibernate.listarPaises());
	}

	public void removerPais(Integer id) {
		Pais pais = paisHibernate.buscarPaisPorId(id);

		boolean paisRemovido = paisHibernate.removerPais(pais);

		paisView.removerPais(paisRemovido);
	}
}