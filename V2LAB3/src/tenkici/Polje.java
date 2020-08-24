package tenkici;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public abstract class Polje extends Canvas{
	private Mreza mreza;
	protected int[] pozicija=new int[2];
	
	
	public Polje(Mreza mreza) {
		this.mreza=mreza;
		addMouseListener(new MouseEventHandler());
	}

	
	public Mreza getMreza() {
		return mreza;
	}
	
	public int[] odrediPoziciju() {
		return pozicija;
	}

	
	public Polje dohvatiUdaljenoPolje(int a,int b) {
		int[] tmp=odrediPoziciju();
		if((tmp[0]+a)>=0 && (tmp[1]+b)>=0 && (tmp[0]+a)<mreza.dohvatiDimenzije() && (tmp[1]+b)<mreza.dohvatiDimenzije()) {
		int i=tmp[0]+a;
		int j=tmp[1]+b;
		Polje[][] mat=mreza.dohvatiMatricu();
		return mat[i][j];
		}
		return null;
	}
	
	//public void iscrtaj(Graphics g)
	public abstract boolean mozeFigura();
	
	private class MouseEventHandler extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			mreza.zameniPolje(odrediPoziciju()[0],odrediPoziciju()[1]);
		}
	}
	
}
