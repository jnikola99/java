package tenkici;

import java.awt.Color;

@SuppressWarnings("serial")
public class Trava extends Polje{
	
	public Trava(Mreza mreza_){
		super(mreza_);
		setBackground(Color.GREEN);
	}

	@Override
	public boolean mozeFigura() {
		return true;
	}
	
	
}
