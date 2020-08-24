package tenkici;

import java.awt.Color;
import java.awt.Graphics;


public class Igrac extends Figura{
	
	public Igrac(Polje polje_) {
		super(polje_);
	}
	

	@Override
	public void iscrtaj() {
		Graphics g=dohvatiPolje().getGraphics();
		g.setColor(Color.RED);
		g.drawLine(dohvatiPolje().getWidth() / 2, 0, dohvatiPolje().getWidth() / 2, dohvatiPolje().getHeight());
		g.drawLine(0, dohvatiPolje().getHeight() / 2, dohvatiPolje().getWidth(), dohvatiPolje().getHeight() / 2);
	}
}
