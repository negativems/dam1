package ga.mmbh.cfgs.netflixdb.graphic.panels.home;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.models.User;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class HomeShowElementPanel extends JPanel {

	private NetflixApp netflixApp = NetflixApp.getInstance();
	private final User user;
	private final Show show;
	
	private JLabel titleLabel, infoLabel;
	private JTextArea descriptionArea;
	private JButton favouriteButton;
	
	public HomeShowElementPanel(User user, Show show) {
		this.user = user;
		this.show = show;
		
		this.setBackground(null);
		this.setBorder(BorderFactory.createLineBorder(AppUtils.ACCENT_COLOR));
		this.setLayout(null);
		
		titleLabel = new JLabel(show.getTitle());
		titleLabel.setBounds(0, 20, 220, 30);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(true);
		titleLabel.setBackground(AppUtils.ACCENT_COLOR);
		titleLabel.setForeground(Color.BLACK);
		this.add(titleLabel);
		
		descriptionArea = new JTextArea(getFormatedDescription(show.getShortDescription()));
		descriptionArea.setEditable(false);
		descriptionArea.setBounds(10, 60, 200, 85);
		descriptionArea.setForeground(Color.WHITE);
		descriptionArea.setBackground(null);
		descriptionArea.setFocusable(false);
		this.add(descriptionArea);
		
		infoLabel = new JLabel((show.getDirector().isEmpty() ? "" : show.getDirector() + ", ") + show.getReleaseYear());
		infoLabel.setBounds(10, 140, 140, 50);
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setBackground(null);
		infoLabel.setFont(AppUtils.getFont("NetflixSansRegular.ttf", 10));
		this.add(infoLabel);
		
		boolean isFavourite = netflixApp.getUserManager().hasFavourite(user, show);
		
		favouriteButton = new JButton("F");
		favouriteButton.setForeground(isFavourite ? Color.BLACK : AppUtils.YELLOW);
		favouriteButton.setBackground(isFavourite ? AppUtils.YELLOW : AppUtils.DARK_BACKGROUND);
		favouriteButton.setContentAreaFilled(false);
		favouriteButton.setOpaque(true);
		favouriteButton.setBorder(BorderFactory.createLineBorder(AppUtils.ACCENT_COLOR));
		favouriteButton.setBounds(190, 150, 30, 30);
		favouriteButton.setFocusable(false);
		this.add(favouriteButton);
		
		loadListeners();
	}
	
	private void loadListeners() {
		favouriteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (netflixApp.getUserManager().hasFavourite(user, show)) {
					user.getFavouritesShows().remove(show);
					favouriteButton.setBackground(AppUtils.DARK_BACKGROUND);
					favouriteButton.setForeground(AppUtils.YELLOW);
					return;
				}

				user.getFavouritesShows().add(show);
				favouriteButton.setBackground(AppUtils.YELLOW);
				favouriteButton.setForeground(Color.BLACK);
			}
		});
	}
	
	private String getFormatedDescription(String description) {
		String result = "";
		
		for (String word : description.split(" ")) {			
			if (result.contains("\n")) {
				String[] lines = result.split("\n");
				String lastLine = lines[lines.length - 1];
				if (lastLine.length() > 25) result += "\n";
			} else if (result.length() > 25) {
				result += "\n";
			}
			
			result += word + " ";
		}
		
		return result;
	}
}