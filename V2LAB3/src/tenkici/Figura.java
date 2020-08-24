package tenkici;



public abstract class Figura {
	private Polje polje;
	
	public Figura(Polje polje_) {
		polje=polje_;
	}
	
	public Polje dohvatiPolje() {
		return polje;
	}
	
	public void pomeriNaPolje(Polje p) {
		if(p.mozeFigura())polje=p;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==this)return true;
		if(!(o instanceof Figura))return false;
		Figura f=(Figura) o;
		return this.polje.equals(f.polje);
	}
	
	public abstract void iscrtaj();
	
}
