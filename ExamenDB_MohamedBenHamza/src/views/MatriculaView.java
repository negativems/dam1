package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.AlumnoDAO;
import dao.ProfeDAO;
import models.Alumno;
import models.Profesor;
import javax.swing.SwingConstants;

public class MatriculaView {

	private JFrame frame;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfCiclo;
	private JTextField tfMedia;
	private JLabel lblMatricula;
	private JButton btnAtras;
	private JButton btnSiguiente;
	private AlumnoDAO alumnoDAO;
	private ProfeDAO profeDAO;
	private ArrayList<Alumno> alumnos;
	private int indice;
	private JButton btnCrear;
	private JButton btnBorrar;
	private JButton btnUpdate;
	private JButton btnCancelarActualizar;
	private JButton btnGuardar;
	private JButton btnCrearProfe;
	private JLabel lblProfe1;
	private JLabel lblProfe2;
	private JLabel idLabel;
	private JComboBox<String> cbProfe1;
	private JComboBox<String> cbProfe2;

	private ArrayList<Profesor> profes;
	private JLabel mediaLabel;
	private JLabel maximaLabel;
	private JLabel minimaLabel;
	
	private double media = 0;
	private double maxima = 0;
	private double minima = 0;

	/**
	 * Create the application.
	 */
	public MatriculaView() {
		this.profeDAO = new ProfeDAO();
		this.profes = profeDAO.getAll();
		this.alumnoDAO = new AlumnoDAO();
		this.alumnos = alumnoDAO.getAll();
		loadAverages();
		initialize();
		indice = 0;
		if (this.alumnos.size() > 0) {
			printAlumno();
		}
	}

	private void loadAverages() {
		List<Double> mediaList = alumnos.stream().map(Alumno::getMedia).toList();
		System.out.println(mediaList);
		double suma = 0;
		this.minima = mediaList.get(0);
		for (int i = 0; i < mediaList.size(); i++) {
			suma += mediaList.get(i);
			this.maxima = Math.max(maxima, mediaList.get(i));
			this.minima = Math.min(minima, mediaList.get(i));
		}
		
		this.media = suma / mediaList.size();
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

		idLabel = new JLabel("ID: 0");
		idLabel.setBounds(398, 85, 115, 14);
		frame.getContentPane().add(idLabel);
		
		lblMatricula = new JLabel("Matr\u00EDculas");
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblMatricula.setBounds(203, 11, 192, 45);
		frame.getContentPane().add(lblMatricula);

		btnAtras = new JButton("<");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printAtras();
			}

		});
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAtras.setBounds(10, 11, 59, 45);
		frame.getContentPane().add(btnAtras);

		btnSiguiente = new JButton(">");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printDelante();
			}
		});
		btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSiguiente.setBounds(496, 16, 59, 45);
		frame.getContentPane().add(btnSiguiente);

		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(101, 123, 243, 26);
		frame.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		tfApellidos = new JTextField();
		tfApellidos.setEditable(false);
		tfApellidos.setColumns(10);
		tfApellidos.setBounds(101, 170, 243, 26);
		frame.getContentPane().add(tfApellidos);

		tfCiclo = new JTextField();
		tfCiclo.setEditable(false);
		tfCiclo.setColumns(10);
		tfCiclo.setBounds(101, 217, 243, 26);
		frame.getContentPane().add(tfCiclo);

		tfMedia = new JTextField();
		tfMedia.setFont(new Font("Tahoma", Font.PLAIN, 26));
		tfMedia.setEditable(false);
		tfMedia.setBounds(398, 123, 86, 70);
		frame.getContentPane().add(tfMedia);
		tfMedia.setColumns(10);

		btnCrear = new JButton("Crear alumno");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CrearAlumnoView();
				frame.dispose();
			}
		});
		btnCrear.setBounds(56, 351, 115, 23);
		frame.getContentPane().add(btnCrear);
		
		btnCrearProfe = new JButton("Crear profesor");
		btnCrearProfe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CrearProfesorView();
				frame.dispose();
			}
		});
		btnCrearProfe.setBounds(181, 351, 110, 23);
		frame.getContentPane().add(btnCrearProfe);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(btnBorrar,
						"¿Estás seguro de que quieres borrar este registro?"
				);
				
				if (confirmar == 0) { // Quiere borrar
					alumnoDAO.delete(alumnos.get(indice));
					alumnos.remove(alumnos.get(indice));
					if (alumnos.size() > 0) {
						printAtras();
					} else {
						JOptionPane.showMessageDialog(btnBorrar, "Te has quedado sin alumnos");
						printVacio();
					}
				}
			}
		});
		btnBorrar.setBounds(424, 351, 89, 23);
		frame.getContentPane().add(btnBorrar);

		btnUpdate = new JButton("Actualisar");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interruptorEditar();

			}
		});
		btnUpdate.setBounds(301, 351, 115, 23);
		frame.getContentPane().add(btnUpdate);

		btnCancelarActualizar = new JButton("Deshacer cambios");
		btnCancelarActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interruptorEditar();
			}
		});
		btnCancelarActualizar.setBounds(330, 385, 154, 23);
		frame.getContentPane().add(btnCancelarActualizar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (updateAlumno()) interruptorEditar();
			}
		});
		btnGuardar.setBounds(118, 385, 145, 23);
		frame.getContentPane().add(btnGuardar);

		lblProfe1 = new JLabel("");
		lblProfe1.setBounds(398, 206, 115, 14);
		frame.getContentPane().add(lblProfe1);

		lblProfe2 = new JLabel("");
		lblProfe2.setBounds(398, 229, 115, 14);
		frame.getContentPane().add(lblProfe2);
		
		btnCancelarActualizar.setVisible(false);
		btnGuardar.setVisible(false);

		cbProfe1 = new JComboBox<String>();
		cbProfe1.setEditable(true);
		cbProfe1.setBounds(101, 301, 151, 22);
		cbProfe1.setVisible(false);

		frame.getContentPane().add(cbProfe1);

		cbProfe2 = new JComboBox<String>();
		cbProfe2.setEditable(true);
		cbProfe2.setBounds(333, 301, 151, 22);
		cbProfe2.setVisible(false);
		frame.getContentPane().add(cbProfe2);
		
		mediaLabel = new JLabel("Media: " + media);
		mediaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mediaLabel.setBounds(162, 60, 77, 14);
		frame.getContentPane().add(mediaLabel);
		
		maximaLabel = new JLabel("Maxima: " + maxima);
		maximaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		maximaLabel.setBounds(244, 60, 100, 14);
		frame.getContentPane().add(maximaLabel);
		
		minimaLabel = new JLabel("Minima: " + minima);
		minimaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minimaLabel.setBounds(355, 60, 77, 14);
		frame.getContentPane().add(minimaLabel);
		fillProfes();
	}

	private void printAlumno() {
		if (alumnos.size() > 0) {
			Alumno a = alumnos.get(indice);
			idLabel.setText("ID: " + a.getId());
			tfNombre.setText(a.getNombre());
			tfApellidos.setText(a.getApellidos());
			tfCiclo.setText(a.getCiclo());
			tfMedia.setText(String.valueOf(a.getMedia()));
			if (a.getProfe1() != null)
				lblProfe1.setText(a.getProfe1().getNombre());
			if (a.getProfe2() != null)
				lblProfe2.setText(a.getProfe2().getNombre());
			if (a.getProfe1() != null)
				cbProfe1.setSelectedIndex(getIndexProfe(a.getProfe1()));
			if (a.getProfe2() != null)
				cbProfe2.setSelectedIndex(getIndexProfe(a.getProfe2()) + 1);
		}
	}

	private void printVacio() {
		tfNombre.setText("");
		tfApellidos.setText("");
		tfCiclo.setText("");
		tfMedia.setText("");
	}

	private void printAtras() {
		if (indice == 0) {
			btnAtras.setEnabled(false);
			return;
		}
		
		if (!btnAtras.isEnabled()) btnAtras.setEnabled(true);
		indice--;
		if (!btnSiguiente.isEnabled() && indice < alumnos.size() - 1) btnSiguiente.setEnabled(true);
		printAlumno();
	}

	private void printDelante() {
		if (indice == alumnos.size() - 1) {
			btnSiguiente.setEnabled(false);
			return;
		}
		
		if (!btnSiguiente.isEnabled()) btnSiguiente.setEnabled(true);
		indice++;
		if (!btnAtras.isEnabled() && indice > 0) btnAtras.setEnabled(true);
		printAlumno();
	}

	private void interruptorEditar() {
		tfNombre.setEditable(!tfNombre.isEditable());
		tfApellidos.setEditable(!tfApellidos.isEditable());
		tfCiclo.setEditable(!tfCiclo.isEditable());
		tfMedia.setEditable(!tfMedia.isEditable());
		btnAtras.setVisible(!btnAtras.isVisible());
		btnSiguiente.setVisible(!btnSiguiente.isVisible());
		btnCrear.setVisible(!btnCrear.isVisible());
		btnBorrar.setVisible(!btnBorrar.isVisible());
		btnCancelarActualizar.setVisible(!btnCancelarActualizar.isVisible());
		btnGuardar.setVisible(!btnGuardar.isVisible());
		btnUpdate.setVisible(!btnUpdate.isVisible());
		lblProfe1.setVisible(!lblProfe1.isVisible());
		idLabel.setVisible(!idLabel.isVisible());
		cbProfe1.setVisible(!cbProfe1.isVisible());
		cbProfe2.setVisible(!cbProfe2.isVisible());
		printAlumno();
	}

	private boolean updateAlumno() {
		Alumno a = alumnos.get(indice);
		a.setNombre(tfNombre.getText());
		a.setApellidos(tfApellidos.getText());
		a.setCiclo(tfCiclo.getText());
		a.setMedia(Double.parseDouble(tfMedia.getText()));
		
		String profe1Nombre = cbProfe1.getSelectedItem().toString();
		String profe2Nombre = cbProfe2.getSelectedItem().toString();
		if (profe1Nombre == profe2Nombre) {
			JOptionPane.showMessageDialog(btnCrear, "El profesor 2 no puede ser igual que el 1");
			return false;
		}
		
		// Sacar el valor de los profes de los comboboxes
		Profesor profe1 = profes.get(cbProfe1.getSelectedIndex());
		a.setProfe1(profe1);
		if(cbProfe2.getSelectedIndex() != 0 && cbProfe1.getSelectedIndex() != cbProfe2.getSelectedIndex() - 1) {
			Profesor profe2 =  profes.get(cbProfe2.getSelectedIndex()-1); //-1 porque el primero es Ninguno
			a.setProfe2(profe2);
		}
		alumnoDAO.update(a);
		
		return true;
	}

	private void fillProfes() {
		cbProfe2.addItem("Ninguno");
		for (Profesor p : profes) {
			cbProfe1.addItem(p.getNombre());
			cbProfe2.addItem(p.getNombre());
		}
	}

	/**
	 * Devuelve el �ndice en la lista del profesor buscado por nombre
	 * 
	 * @param p
	 * @return �ndice si se encuentra, -1 en caso contrario.
	 */
	private int getIndexProfe(Profesor p) {
		for (int i = 0; i < profes.size(); i++) {
			if (profes.get(i).getNombre().equals(p.getNombre())) {
				return i;
			}
		}
		return -1;
	}
}
