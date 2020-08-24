package tenkici;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Mreza extends Panel implements Runnable{
	private Polje[][] matricaPolja;
	private List<Tenk> listTenk;
	private List<Novcic> listNovcic;
	private List<Igrac> listIgrac;
	private int dimenzije;
	private Igra igra;
	private Igrac igrac;
	private Thread nitMreze;
	protected int poeni;
	
	public Mreza(Igra igra) {
		this(17,igra);
	}
	
	public Mreza(int dimenzije,Igra igra) {
		this.dimenzije=dimenzije;
		this.igra=igra;
		setSize(420,400);
		setLayout(new GridLayout(dimenzije,dimenzije));
		addKeyListener(new KeyHandler());
		listTenk=new LinkedList<Tenk>();
		listNovcic=new LinkedList<Novcic>();
		listIgrac=new LinkedList<Igrac>();
		matricaPolja=new Polje[dimenzije][dimenzije];
		for(int i=0;i<dimenzije;i++)
			for(int j=0;j<dimenzije;j++) {
				double r=Math.random();
				if(r>0.8)matricaPolja[i][j]=new Zid(this);
				else matricaPolja[i][j]=new Trava(this);
				matricaPolja[i][j].pozicija[0]=i;
				matricaPolja[i][j].pozicija[1]=j;
			}	
		for(int i=0;i<dimenzije;i++)
			for(int j=0;j<dimenzije;j++)
				this.add(matricaPolja[i][j]);
	}
	
	public int dohvatiDimenzije() {
		return dimenzije;
	}
	
	public Polje[][] dohvatiMatricu(){
		return matricaPolja;
	}
	
	public List<Tenk> dohvatiTenkove(){
		return listTenk;
	}
	
	public List<Novcic> dohvatiNovcice(){
		return listNovcic;
	}
	
	public List<Igrac> dohvatiIgraca(){
		return listIgrac;
	}
	
	protected void zameniPolje(int i,int j) {
		if(igra.rezim==0) 
		{
			remove(matricaPolja[i][j]);
			if(igra.cbGroup.getSelectedCheckbox()==igra.trava) {
				matricaPolja[i][j]=new Trava(this);
				add(matricaPolja[i][j],i*dimenzije+j);
			}
			else if(igra.cbGroup.getSelectedCheckbox()==igra.zid) {
				matricaPolja[i][j]=new Zid(this);
				add(matricaPolja[i][j],i*dimenzije+j);
			}
		}
		revalidate();
	}
	
	public void inicijalizujMrezu(int novcici) {
		if(igra.rezim==1) {
		igrac=null;
		listNovcic.clear();
		listTenk.clear();
		poeni=0;
		repaint();
		int brojTenkova=novcici/3;
		boolean[][] popunjen=new boolean[dimenzije][dimenzije];
		for(int i=0;i<dimenzije;i++)
			for(int j=0;j<dimenzije;j++)
				popunjen[i][j]=false;
		if(igra.rezim==1) {
			 while(novcici!=0){
				int p=(int)(Math.random()*dimenzije);
				int q=(int)(Math.random()*dimenzije);
				while((popunjen[p][q])||(!(matricaPolja[p][q] instanceof Trava))) {
					p=(int)(Math.random()*dimenzije);
					q=(int)(Math.random()*dimenzije);
				}
				Novcic n=new Novcic(matricaPolja[p][q]);
				popunjen[p][q]=true;
				listNovcic.add(n);
				novcici--;
			}
			while(brojTenkova!=0) {
				int p=(int)(Math.random()*dimenzije);
				int q=(int)(Math.random()*dimenzije);
				while((popunjen[p][q])||(!(matricaPolja[p][q] instanceof Trava))) {
					p=(int)(Math.random()*dimenzije);
					q=(int)(Math.random()*dimenzije);
				}
				Tenk t=new Tenk(matricaPolja[p][q]);
				popunjen[p][q]=true;
				listTenk.add(t);
				brojTenkova--;
			}
			int p=(int)(Math.random()*dimenzije);
			int q=(int)(Math.random()*dimenzije);
			while((popunjen[p][q])||(!(matricaPolja[p][q] instanceof Trava))) {
				p=(int)(Math.random()*dimenzije);
				q=(int)(Math.random()*dimenzije);
			}
			igrac=new Igrac(matricaPolja[p][q]);
			listIgrac.add(igrac);
			}
		}
	}

	private class KeyHandler extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(igra.rezim==1) {
			Polje p=igrac.dohvatiPolje();
			Polje novo=null;
					switch(e.getKeyCode()) {
					case KeyEvent.VK_A:
						novo=p.dohvatiUdaljenoPolje(0, -1);
						if(novo!=null && novo.mozeFigura())
						igrac.pomeriNaPolje(novo);
						break;
					case KeyEvent.VK_D:
						novo=p.dohvatiUdaljenoPolje(0, 1);
						if(novo!=null && novo.mozeFigura())
						igrac.pomeriNaPolje(novo);
						break;
					case KeyEvent.VK_W:
						novo=p.dohvatiUdaljenoPolje(-1, 0);
						if(novo!=null && novo.mozeFigura())
						igrac.pomeriNaPolje(novo);
						break;
					case KeyEvent.VK_S:	
						novo=p.dohvatiUdaljenoPolje(1, 0);
						if(novo!=null && novo.mozeFigura())
						igrac.pomeriNaPolje(novo);
						break;
				}
			p.repaint();
			}
		}
	}
	
	public void azuriraj() {
		igrac.iscrtaj();
		for(Tenk t:listTenk)
			t.iscrtaj();
		for(Novcic n:listNovcic)
			n.iscrtaj();
	}
	
	public void pokreniMrezu() {
		setFocusable(true);
		this.requestFocus();
		poeni=0;
		igra.lblPoeni.setText("Poeni: 0");
		while(!listTenk.isEmpty()) {
			for(int i=0;i<listTenk.size();i++) {
				Polje p=listTenk.get(i).dohvatiPolje();
				listTenk.get(i).zaustaviTenk();
				listTenk.remove(i);
				p.repaint();
			}
		}
		if(!listIgrac.isEmpty()) {
			Polje p=listIgrac.get(0).dohvatiPolje();
			listIgrac.clear();
			p.repaint();
		}
		while(!listNovcic.isEmpty()) {
			for(int i=0;i<listNovcic.size();i++) {
				Polje p=listNovcic.get(i).dohvatiPolje();
				listNovcic.remove(i);
				p.repaint();
			}
		}
		this.inicijalizujMrezu(Integer.parseInt(igra.txtNovcici.getText()));
		for(int i=0;i<listTenk.size();i++)
			listTenk.get(i).pokreniTenk();
		nitMreze=new Thread(this);
		nitMreze.start();
	}
	
	public synchronized void zaustaviMrezu() {
		if(igra.radi) {
		igra.radi=false;
		igrac=null;
		while(!listTenk.isEmpty()) {
			for(int i=0;i<listTenk.size();i++) {
				Polje p=listTenk.get(i).dohvatiPolje();
				listTenk.get(i).zaustaviTenk();
				listTenk.remove(i);
				p.repaint();
			}
		}
		if(!listIgrac.isEmpty()) {
			Polje p=listIgrac.get(0).dohvatiPolje();
			listIgrac.clear();
			p.repaint();
		}
		while(!listNovcic.isEmpty()) {
			for(int i=0;i<listNovcic.size();i++) {
				Polje p=listNovcic.get(i).dohvatiPolje();
				listNovcic.remove(i);
				p.repaint();
			}
		}
		if(nitMreze!=null)nitMreze.interrupt();
		for(int i=0;i<dimenzije;i++)
			for(int j=0;j<dimenzije;j++)
				matricaPolja[i][j].repaint();
		}
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Thread.sleep(40);
				azuriraj();
				for(int i=0;i<listNovcic.size();i++)
					if(listNovcic.get(i).dohvatiPolje().equals(igrac.dohvatiPolje())) {
						listNovcic.remove(i);
						poeni++;
						igra.lblPoeni.setText("Poeni: "+poeni);
					}
				for(int i=0;i<listTenk.size();i++)
					if(listTenk.get(i).dohvatiPolje().equals(igrac.dohvatiPolje())) {
						listIgrac.remove(igrac);
						zaustaviMrezu();
					}
				if(listNovcic.isEmpty()) {
					listIgrac.remove(igrac);
					zaustaviMrezu();
				}
				//azuriraj();
			}
		}catch(InterruptedException e) {}
		
	}
	
	
	
	
}
