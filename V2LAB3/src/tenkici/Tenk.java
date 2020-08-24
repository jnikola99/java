package tenkici;

import java.awt.Color;
import java.awt.Graphics;

public class Tenk extends Figura implements Runnable{
	
	private Thread thread;
	
	public Tenk(Polje polje_) {
		super(polje_);
	}
	
	@Override
	public void iscrtaj() {
		Graphics g=dohvatiPolje().getGraphics();
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, dohvatiPolje().getWidth(), dohvatiPolje().getHeight());
		g.drawLine(dohvatiPolje().getWidth(), 0, 0, dohvatiPolje().getHeight());
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				int rand=-1;
				int x=dohvatiPolje().odrediPoziciju()[0];
				int y=dohvatiPolje().odrediPoziciju()[1];
				Polje p=dohvatiPolje();
				if(dohvatiPolje().dohvatiUdaljenoPolje(1, 0)!=null || dohvatiPolje().dohvatiUdaljenoPolje(-1, 0)!=null || dohvatiPolje().dohvatiUdaljenoPolje(0, 1)!=null || dohvatiPolje().dohvatiUdaljenoPolje(0, -1)!=null) {
					lbl:while (true) {
						rand=(int)(Math.random()*4);
						switch(rand) {
						case 0:
							if(dohvatiPolje().dohvatiUdaljenoPolje(1, 0)!=null && dohvatiPolje().dohvatiUdaljenoPolje(1, 0).mozeFigura()) {
								pomeriNaPolje(dohvatiPolje().getMreza().dohvatiMatricu()[x+1][y]);
								break lbl;
							}else continue;
						case 1:
							if(dohvatiPolje().dohvatiUdaljenoPolje(-1, 0)!=null && dohvatiPolje().dohvatiUdaljenoPolje(-1, 0).mozeFigura()) {
								pomeriNaPolje(dohvatiPolje().getMreza().dohvatiMatricu()[x-1][y]);
								break lbl;
							}continue;
						case 2:
							if(dohvatiPolje().dohvatiUdaljenoPolje(0, 1)!=null && dohvatiPolje().dohvatiUdaljenoPolje(0, 1).mozeFigura()) {
								pomeriNaPolje(dohvatiPolje().getMreza().dohvatiMatricu()[x][y+1]);
								break lbl;
							}continue;
						case 3:
							if(dohvatiPolje().dohvatiUdaljenoPolje(0, -1)!=null && dohvatiPolje().dohvatiUdaljenoPolje(0, -1).mozeFigura()) {
								pomeriNaPolje(dohvatiPolje().getMreza().dohvatiMatricu()[x][y-1]);
								break lbl;
							}continue;
						}
					}
				}
			/*	int rand=-1;
				Polje p=dohvatiPolje();
				if(dohvatiPolje().dohvatiUdaljenoPolje(1, 0)!=null || dohvatiPolje().dohvatiUdaljenoPolje(-1, 0)!=null || dohvatiPolje().dohvatiUdaljenoPolje(0, 1)!=null || dohvatiPolje().dohvatiUdaljenoPolje(0, -1)!=null) {
					skok:while (true) {
						rand=(int)(Math.random()*4);
						switch(rand) {
						case 0:
							if(dohvatiPolje().dohvatiUdaljenoPolje(1, 0)!=null && dohvatiPolje().dohvatiUdaljenoPolje(1, 0).mozeFigura()) {
								pomeriNaPolje(dohvatiPolje().dohvatiUdaljenoPolje(1, 0));
								break skok;
							}else continue;
						case 1:
							if(dohvatiPolje().dohvatiUdaljenoPolje(-1, 0)!=null && dohvatiPolje().dohvatiUdaljenoPolje(-1, 0).mozeFigura()) {
								pomeriNaPolje(dohvatiPolje().dohvatiUdaljenoPolje(-1, 0));
								break skok;
							}continue;
						case 2:
							if(dohvatiPolje().dohvatiUdaljenoPolje(0, 1)!=null && dohvatiPolje().dohvatiUdaljenoPolje(0, 1).mozeFigura()) {
								pomeriNaPolje(dohvatiPolje().dohvatiUdaljenoPolje(0, 1));
								break skok;
							}continue;
						case 3:
							if(dohvatiPolje().dohvatiUdaljenoPolje(0, -1)!=null && dohvatiPolje().dohvatiUdaljenoPolje(0, -1).mozeFigura()) {
								pomeriNaPolje(dohvatiPolje().dohvatiUdaljenoPolje(0, -1));
								break skok;
							}continue;
						}
					}
				}
			*/
				p.repaint();
				Thread.sleep(500);
			}
		}catch(InterruptedException e) {}
	}
	
	public void pokreniTenk() {
		thread=new Thread(this);
		thread.start();
	}
	
	public void zaustaviTenk() {
		if(thread!=null)thread.interrupt();
	}
	
}
