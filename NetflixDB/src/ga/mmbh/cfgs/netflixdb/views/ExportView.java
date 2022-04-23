package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;
import ga.mmbh.cfgs.netflixdb.handlers.EscKeyPress;
import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;
import ga.mmbh.cfgs.netflixdb.utils.FileUtils;

public class ExportView {
	
	private final NetflixApp netflixApp = NetflixApp.getInstance();
	private final List<Show> shows;
	private CustomFrame frame;
	
	private JTextField separatorField;
	private JButton exportButton;
	private JLabel titleLabel, directorLabel, yearLabel, errorLabel;
	private JComboBox countryComboBox, directorComboBox, yearComboBox;

	/**
	 * @wbp.parser.constructor
	 */
	public ExportView(List<Show> shows) {
		this.shows = shows;
		
		initialize();
		loadListeners();
	}

	private void initialize() {
		frame = new CustomFrame();
		frame.setBounds(250, 250, 400, 200);
		
		titleLabel = new JLabel("Introduce el carácter especial");
		titleLabel.setBounds(0, 50, 400, 20);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(titleLabel);
		
		separatorField = new JTextField(";");
		separatorField.setBackground(Color.WHITE);
		separatorField.setBounds(175, 100, 50, 20);
		separatorField.setHorizontalAlignment(SwingConstants.CENTER);
		separatorField.setBorder(BorderFactory.createLineBorder(AppUtils.ACCENT_COLOR));
		frame.getContentPane().add(separatorField);

		exportButton = new JButton("Exportar");
		exportButton.setFocusable(false);
		exportButton.setBackground(AppUtils.ACCENT_COLOR);
		exportButton.setBounds(125, 150, 150, 30);
		frame.getContentPane().add(exportButton);
		
		errorLabel = new JLabel("");
		errorLabel.setBounds(0, 140, 400, 20);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.RED);
		frame.getContentPane().add(errorLabel);
		
		separatorField.addKeyListener(new EscKeyPress(frame));
		frame.addKeyListener(new EscKeyPress(frame));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void loadListeners() {
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String separator = separatorField.getText();
				if (separator.length() == 0 || separator.length() > 1) {
					errorLabel.setText("Debes introducir un único caracter");
					return;
				}
				
				frame.dispose();
				
				String data = AppUtils.HEADER_CSV.replaceAll(",", separator) + "\n";
				for (Show show : shows) {
					data += show.getFormattedCSV(separator.toCharArray()[0]) + "\n";
				}
				
				File file = FileUtils.createFile("export.csv");
				FileUtils.writeToFile(file, data);
			}
		});
	}
}
