package holeclicker;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


@SuppressWarnings("serial")
public class Igra extends Frame{
	
	private Button kreni=new Button("Kreni");
	private Basta basta=new Basta(4,4);
	protected Label povrce=new Label("Povrce: "+Integer.toString(basta.kolicinaPovrca));
	private static Igra igra;
	private Checkbox lako,srednje,tesko;	
	private boolean postoji=false;
	
	public void populateWindow() {
		
		Panel meni=new Panel(new GridLayout(0,1));
		
		Panel checkboxPanel=new Panel(new GridLayout(0,1));
		Label tezina=new Label("Tezina:");
		tezina.setFont(new Font("Arial",Font.BOLD,13));
		tezina.setAlignment(Label.CENTER);
		checkboxPanel.add(tezina);
		CheckboxGroup biranjeTezine = new CheckboxGroup();
	
		lako=new Checkbox("Lako",false,biranjeTezine);
		srednje=new Checkbox("Srednje",true,biranjeTezine);
		tesko=new Checkbox("Tesko",false,biranjeTezine);
		checkboxPanel.add(lako);
		checkboxPanel.add(srednje);
		checkboxPanel.add(tesko);
		
		kreni.setEnabled(true);
		kreni.addActionListener((ae)->{
			if(kreni.getLabel()=="Kreni") {
				if(biranjeTezine.getSelectedCheckbox().getLabel().equals("Lako")) {
					basta.postaviBrojKoraka(10);
					basta.postaviIntervalCekanja(1000);
				}
				else if(biranjeTezine.getSelectedCheckbox().getLabel().equals("Srednje")) {
					basta.postaviBrojKoraka(8);
					basta.postaviIntervalCekanja(750);
				}
				else {
					basta.postaviBrojKoraka(6);
					basta.postaviIntervalCekanja(500);
				}
				lako.setEnabled(false);
				srednje.setEnabled(false);
				tesko.setEnabled(false);
				basta.pokreniBastu();
				kreni.setLabel("Stani");
			}
			else if(kreni.getLabel()=="Stani"){
				lako.setEnabled(true);
				srednje.setEnabled(true);
				tesko.setEnabled(true);
				basta.zaustaviBastu();
				kreni.setLabel("Kreni");
			}
		});
		
		checkboxPanel.add(kreni);
		meni.add(checkboxPanel);
		
		
		Panel povrcePanel=new Panel(new BorderLayout());
		povrce.setAlignment(Label.CENTER);
		povrce.setFont(new Font("Arial",Font.BOLD,18));
		povrcePanel.add(povrce);
		
		meni.add(povrcePanel);
		
		add(basta,BorderLayout.CENTER);
		add(meni,BorderLayout.EAST);
		
		
	}
	
	public static Igra getIgra() {
		return igra;
	}
	
	public void zaustaviIgru() {
		kreni.setLabel("Kreni");
		lako.setEnabled(true);
		srednje.setEnabled(true);
		tesko.setEnabled(true);
		basta.zaustaviBastu();
	}
	
	public void povrceText() {
		povrce.setText("Povrce: "+Integer.toString(basta.kolicinaPovrca));
	}
	
	public Igra() {
		super("Igra");
		if(!postoji)postoji=true;
		igra=this;
		setResizable(true);
		setTitle("Igra");
		setBounds(700, 200, 400, 300);
		
		populateWindow();
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				basta.zaustaviBastu();
				dispose();
			}
		});
			setVisible(true);
		}
	
	
	public static void main(String[] args) {
		new Igra();
	}

}
