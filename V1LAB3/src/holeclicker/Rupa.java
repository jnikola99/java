package holeclicker;
import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class Rupa extends Canvas implements Runnable{
	private Zivotinja zivotinja;
	protected Thread thread=null;
	protected Basta basta;
	private long sleepTime=100;
	private Color braon=new Color(140,50,0);
	private int brojKoraka;
	protected int trenutniKoraci=1;
	private boolean zgazena;
	
	public Rupa(Basta basta) {
		this.basta=basta;
		zivotinja=null;
		setBackground(braon);
		addMouseListener(new MouseEventHandler());
	}
	
	public void postaviZivotinju(Zivotinja novaZivotinja) {
		this.zivotinja=novaZivotinja;
	}
	
	public Zivotinja dohvatiZivotinju() {
		return this.zivotinja;
	}
	
	public void postaviBrojKoraka(int noviKoraci) {
		brojKoraka=noviKoraci;
	}
	
	public int dohvatiBrojKoraka() {
		return brojKoraka;
	}
	
	public synchronized void stvoriNit() {
		thread=new Thread(this);
		trenutniKoraci=1;
		zgazena=false;
	}
	public synchronized void pokreniNit() {
		if(thread!=null)
			thread.start();
	}
	
	public synchronized void zgaziRupu() {
		if(zivotinja!=null) {
		zivotinja.efekatUdareneZivotinje();
		zgazena=true;
		}
	}
	
	private class MouseEventHandler extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			zgaziRupu();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		if(zivotinja!=null)zivotinja.iscrtaj();
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				if(trenutniKoraci==brojKoraka) {
					Thread.sleep(2000);
					this.zaustaviRupu();
				}
				Thread.sleep(sleepTime);
				repaint();
				trenutniKoraci++;
			}
		}catch(InterruptedException e) {}
		
		
		synchronized (this) {
			//thread = null;
			if(!zgazena)zivotinja.efekatPobegleZivotinje();
			zivotinja=null;
			repaint();
			trenutniKoraci=1;
			basta.obavesti(this);
		}
	}
	
	public synchronized boolean nitPokrenuta() {
		if (thread!=null)return true;
		else return false;
	}
	
	public synchronized void zaustaviRupu() {
		if(thread != null)
			thread.interrupt();
	}
}
