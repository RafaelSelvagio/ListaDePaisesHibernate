package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Pais {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    private String nome;
    private String capital;
    
    public Pais() {}
    
    public Pais(String nome, String capital) {
    	this.id = null;
        this.nome = nome;
        this.capital = capital;
    }

    public Pais(Integer id, String nome, String capital) {
    	this.id = id;
        this.nome = nome;
        this.capital = capital;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nome=" + nome + ", capital=" + capital + "]";
	}
	
	
}
