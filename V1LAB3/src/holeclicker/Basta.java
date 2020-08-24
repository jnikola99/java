package holeclicker;
import java.awt.*;


@SuppressWarnings("serial")
public class Basta extends Panel implements Runnable{
	protected int kolicinaPovrca;
	private Rupa[][] matrica;
	private boolean[][] matBool;
	private long sleepTime;
	private int brojKoraka;
	private int redovi;
	private int kolone;
	private Thread nitBaste;
	protected boolean smePovrce=true;
	
	public Basta(int redovi,int kolone) {
		this.redovi=redovi;
		this.kolone=kolone;
		kolicinaPovrca=100;
		matrica=new Rupa[redovi][kolone];
		this.setLayout(new GridLayout(redovi,kolone,15,15));
		setBackground(Color.GREEN);
		for(int i=0;i<redovi;i++)
			for(int j=0;j<kolone;j++)
				matrica[i][j]=new Rupa(this);
		for(int i=0;i<redovi;i++)
			for(int j=0;j<kolone;j++)
				this.add(matrica[i][j]);
		matBool=new boolean[redovi][kolone];
		for(int i=0;i<redovi;i++)
			for(int j=0;j<kolone;j++)
				matBool[i][j]=false;
		
	}
	
	public synchronized void obavesti(Rupa r) {
		for(int i=0;i<redovi;i++)
			for(int j=0;j<kolone;j++)
				if(matrica[i][j]==r)matBool[i][j]=false;
	}
	
	public int dohvatiBrojKoraka() {
		return brojKoraka;
	}
	
	public synchronized void postaviBrojKoraka(int noviKoraci) {
		brojKoraka=noviKoraci;
		for(int i=0;i<redovi;i++)
			for(int j=0;j<kolone;j++)
				matrica[i][j].postaviBrojKoraka(noviKoraci);
	}
	
	public synchronized void smanjiPovrce() {
		if(kolicinaPovrca>0) {
			kolicinaPovrca=kolicinaPovrca-1;
		}
		Igra.getIgra().povrceText();
		if(kolicinaPovrca==0) {
			smePovrce=false;
			Igra.getIgra().zaustaviIgru();
			kolicinaPovrca=100;
			Igra.getIgra().povrceText();
		}
	}
	
	public synchronized void pokreniBastu() {
		/*kolicinaPovrca=100;
		Igra.getIgra().povrceText();*/
		smePovrce=true;
		nitBaste=new Thread(this);
		nitBaste.start();
	}
	
	public synchronized void zaustaviBastu() {
			for(int i=0;i<redovi;i++)
				for(int j=0;j<kolone;j++) {
					matrica[i][j].zaustaviRupu();
					matBool[i][j]=false;
				}
			if(nitBaste != null)
				nitBaste.interrupt();
	}
	
	public synchronized void postaviIntervalCekanja(long newSleep) {
		sleepTime=newSleep;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				int i=(int)(Math.random()*redovi);
				int j=(int)(Math.random()*kolone);
				while(matBool[i][j]) {
					i=(int)(Math.random()*redovi);
					j=(int)(Math.random()*kolone);
				}
				matrica[i][j].postaviZivotinju(new Krtica(matrica[i][j]));
				matrica[i][j].stvoriNit();
				matrica[i][j].pokreniNit();
				matBool[i][j]=true;
				Thread.sleep(sleepTime);
				sleepTime=sleepTime-sleepTime/100;
			}
		}catch(InterruptedException e) {}
		synchronized(this) {
			//nitBaste=null;
			for(int i=0;i<redovi;i++)
				for(int j=0;j<kolone;j++)
					matBool[i][j]=false;
		}
		
	}
	
	
	
}
