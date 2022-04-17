package ga.mmbh.cfgs.netflixdb.views;

import javax.swing.JFrame;

import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;

public class ExportView {

	private CustomFrame frame;

	/**
	 * @wbp.parser.constructor
	 */
	public ExportView() {
		initialize();
	}

	private void initialize() {
		frame = new CustomFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
