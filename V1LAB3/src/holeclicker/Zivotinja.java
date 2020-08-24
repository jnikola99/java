package holeclicker;

public abstract class Zivotinja implements Cloneable{
	private Rupa rupa;
	protected int height,width;
	
	public Zivotinja(Rupa rupa) {
		this.rupa=rupa;
	}
	
	public abstract void efekatUdareneZivotinje();
	public abstract void efekatPobegleZivotinje();
	
	public void iscrtaj() {
		double percent=(double)rupa.trenutniKoraci/(double)rupa.dohvatiBrojKoraka();
		width=(int)(rupa.getWidth()*percent);
		height=(int)(rupa.getHeight()*percent);
	}
	
	public Rupa dohvatiRupu() {
		return this.rupa;
	}
	
}
