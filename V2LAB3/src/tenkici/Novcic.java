package tenkici;

import java.awt.Color;
import java.awt.Graphics;

public class Novcic extends Figura{
	
	public Novcic(Polje polje_) {
		super(polje_);
	}
	
	@Override
	public void iscrtaj() {
		Graphics g=dohvatiPolje().getGraphics();
		g.setColor(Color.YELLOW);
		g.fillOval(dohvatiPolje().getWidth() / 4, dohvatiPolje().getHeight() / 4, dohvatiPolje().getWidth() / 2, dohvatiPolje().getHeight() / 2);
	}
}
