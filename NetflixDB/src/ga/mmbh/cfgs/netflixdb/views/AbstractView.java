package ga.mmbh.cfgs.netflixdb.views;

import ga.mmbh.cfgs.netflixdb.panels.CustomFrame;

public abstract class AbstractView {

	private CustomFrame frame;
	
	
	
	public abstract void initialize();
	
	public abstract void updateView();
	
	public abstract void loadListeners();
	
}
