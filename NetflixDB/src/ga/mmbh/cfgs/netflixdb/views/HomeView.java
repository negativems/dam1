package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.panels.ShowsPanel;
import ga.mmbh.cfgs.netflixdb.utils.JPanelBackground;
import ga.mmbh.cfgs.netflixdb.NetflixApp;

public class HomeView {

	private final NetflixApp ficherosApp;

	private Show show;

	private JFrame frame;
	private JPanelBackground panel;
	private JButton previousShowButton, nextShowButton;
	private JLabel errorLabel;
	private JTextField textField;
	
	private int page = 0;
	
	private final static int MAX_SHOWS_PER_PAGE = 0;

	/**
	 * Create the application.
	 */
	public HomeView(NetflixApp ficherosApp) {
		this.ficherosApp = ficherosApp;
		initialize();
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public HomeView() {
		this.ficherosApp = null;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		frame.setBounds(0, 0, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		try {
			panel = new JPanelBackground("resources/home_background.jfif");
			panel.setBounds(0, 0, 500, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setBounds(75, 50, 350, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel showsPanel = new ShowsPanel(frame, page);
		frame.getContentPane().add(showsPanel);
		
		nextShowButton = new JButton("Next");
		nextShowButton.setBounds(261, 377, 89, 23);
		frame.add(nextShowButton);
		
		previousShowButton = new JButton("Previous");
		previousShowButton.setBounds(0, 377, 89, 23);
		frame.add(previousShowButton);
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	private void updateView() {
		for(int i = 0; i < MAX_SHOWS_PER_PAGE; i++) {
			Show show = ficherosApp.getShowManager().getShows().get((page * MAX_SHOWS_PER_PAGE) + i);
		}
	}
	
	public void loadListeners() {	
		nextShowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ficherosApp.getShowManager().getShows().size() < page + MAX_SHOWS_PER_PAGE) {
					nextShowButton.setEnabled(false);
					return;
				}
				
				if (!nextShowButton.isEnabled()) {
					nextShowButton.setEnabled(true);
				}
				
				page++;
				updateView();
			}
		});
		
		previousShowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page == 1) {
					previousShowButton.setEnabled(false);
					return;
				}
				
				if (!previousShowButton.isEnabled()) {
					previousShowButton.setEnabled(true);
				}
				
				page--;
				updateView();
			}
		});
	}
	
}
