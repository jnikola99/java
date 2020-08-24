package tenkici;

import java.awt.Color;

@SuppressWarnings("serial")
public class Zid extends Polje{
	
	public Zid(Mreza mreza_) {
		super(mreza_);
		setBackground(Color.GRAY);
	}

	@Override
	public boolean mozeFigura() {
		return false;
	}
	
	
}
