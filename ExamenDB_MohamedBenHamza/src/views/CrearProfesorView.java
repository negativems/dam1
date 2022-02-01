package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.ProfeDAO;
import models.Profesor;

public class CrearProfesorView {

	private JFrame frame;
	private JTextField tfNombre;
	private ProfeDAO profeDAO;
	private JButton btnCrear;
	
	private List<Profesor> profes;

	/**
	 * Create the application.
	 */
	public CrearProfesorView() {
		this.profeDAO = new ProfeDAO();
		this.profes = profeDAO.getAll();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 581, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		tfNombre = new JTextField();
		tfNombre.setText("nombre");
		tfNombre.setBounds(101, 123, 243, 26);
		frame.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> nombresList = profes.stream().map(Profesor::getNombre).toList();
				if (nombresList.contains(tfNombre.getText())) {
					JOptionPane.showMessageDialog(btnCrear, "Ese profesor ya existe!");
					return;
				}
				
				insertarProfesor();
			}
		});
		btnCrear.setBounds(219, 366, 89, 23);
		frame.getContentPane().add(btnCrear);
	}

	private void insertarProfesor() {
		int id = profes.size() + 1;
		Profesor profesor = new Profesor(id, tfNombre.getText());
		
		profeDAO.insert(profesor);
		JOptionPane.showMessageDialog(btnCrear, "Profesor creado");
		new MatriculaView();
		frame.dispose();
	}
}
