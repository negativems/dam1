package ga.mmbh.cfgs.ficheros.views;

import ga.mmbh.cfgs.ficheros.panels.CustomFrame;

public abstract class AbstractView {

	private CustomFrame frame;
	
	
	
	public abstract void initialize();
	
	public abstract void updateView();
	
	public abstract void loadListeners();
	
}
