package tenkici;
import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class Igra extends Frame{

	private Mreza mreza=new Mreza(this);
	protected int rezim=1; // 0-izmena,1-igranje
	protected Checkbox trava,zid;
	protected boolean radi=false;
	protected Label lblPoeni;
	protected CheckboxGroup cbGroup;
	protected TextField txtNovcici;
	
	public Igra() {
		super("Igra");
		setTitle("Igra");
		setResizable(false);
		
		Menu rezimMeni=new Menu("Rezim");
		MenuItem izmena=new MenuItem("Rezim izmena");
		MenuItem igranje=new MenuItem("Rezim igranja");
		izmena.addActionListener((ae)->{
			if(radi)mreza.zaustaviMrezu();
			rezim=0;
		});
		igranje.addActionListener((ae)->{
			rezim=1;
		});
		rezimMeni.add(izmena);
		rezimMeni.add(igranje);
		MenuBar menubar=new MenuBar();
		menubar.add(rezimMeni);
		setMenuBar(menubar);
		//----------------------------
		Panel checkboxPanel=new Panel(new GridLayout(0,1));
		cbGroup=new CheckboxGroup();
		trava=new Checkbox("Trava",true,cbGroup);
		zid=new Checkbox("Zid",false,cbGroup);
		trava.setBackground(Color.GREEN);
		zid.setBackground(Color.GRAY);
		checkboxPanel.add(trava);
		checkboxPanel.add(zid);
		//----------------------------------
		Panel donjiPanel=new Panel();
		Label lblNovcici=new Label("Novcica:");
		txtNovcici=new TextField(6);
		if(rezim==1)txtNovcici.setEnabled(true);
		else if(rezim==0)txtNovcici.setEnabled(false);
		lblPoeni=new Label("Poena: "+mreza.poeni);
		
		Button pocni=new Button("Pocni");
		
		
		if(rezim==1)pocni.setEnabled(true);
		else if(rezim==0)pocni.setEnabled(false);
		pocni.addActionListener((ae)->{
			if(!radi) 
				if(rezim==1)
					mreza.zaustaviMrezu();
			if(rezim==1) {
			mreza.pokreniMrezu();
			radi=true;
			}
		});
		
		donjiPanel.add(lblNovcici);
		donjiPanel.add(txtNovcici);
		donjiPanel.add(lblPoeni);
		donjiPanel.add(pocni);
		//-----------------------------
		Panel podloga=new Panel(new BorderLayout());
		Label lblPodloga=new Label("Podloga: ");
		lblPodloga.setAlignment(Label.CENTER);
		podloga.add(lblPodloga);
		//-----------------------------
		Panel desno=new Panel(new GridLayout(0,2));
		desno.add(podloga);
		desno.add(checkboxPanel);
		//-----------------------------
		add(mreza,BorderLayout.CENTER);
		add(desno,BorderLayout.EAST);
		add(donjiPanel,BorderLayout.SOUTH);
		//-----------------------------
		setSize(420,400);
		addWindowListener(new WindowAdapter() {
			
				@Override
				public void windowClosing(WindowEvent e) {
					mreza.zaustaviMrezu();
					dispose();
				}
			});
			setVisible(true);
		}
	
	
	public static void main(String[] args) {
		new Igra();
	}
}
