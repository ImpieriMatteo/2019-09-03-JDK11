package it.polito.tdp.food.model;

public class Arco {
	
	private String porzione1;
	private String porzione2;
	private Integer peso;
	
	public Arco(String porzione1, String porzione2, Integer peso) {
		this.porzione1 = porzione1;
		this.porzione2 = porzione2;
		this.peso = peso;
	}

	public String getPorzione1() {
		return porzione1;
	}

	public void setPorzione1(String porzione1) {
		this.porzione1 = porzione1;
	}

	public String getPorzione2() {
		return porzione2;
	}

	public void setPorzione2(String porzione2) {
		this.porzione2 = porzione2;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((porzione1 == null) ? 0 : porzione1.hashCode());
		result = prime * result + ((porzione2 == null) ? 0 : porzione2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		if (porzione1 == null) {
			if (other.porzione1 != null)
				return false;
		} else if (!porzione1.equals(other.porzione1))
			return false;
		if (porzione2 == null) {
			if (other.porzione2 != null)
				return false;
		} else if (!porzione2.equals(other.porzione2))
			return false;
		return true;
	}
	

}
