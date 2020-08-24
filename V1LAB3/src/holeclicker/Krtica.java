package holeclicker;
import java.awt.*;

public class Krtica extends Zivotinja{
	
	public Krtica(Rupa rupa) {
		super(rupa);
	}

	@Override
	public void efekatUdareneZivotinje() {
		this.dohvatiRupu().zaustaviRupu();
	}

	@Override
	public void efekatPobegleZivotinje() {
		if(dohvatiRupu().basta.smePovrce)
		dohvatiRupu().basta.smanjiPovrce();
	}
	
	@Override
	public void iscrtaj() {
		super.iscrtaj();
		Graphics g=this.dohvatiRupu().getGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillOval(dohvatiRupu().getWidth()/2-width/2,dohvatiRupu().getHeight()/2-height/2,width,height);
	}
	

}
