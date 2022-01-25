package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.StudentDAO;
import models.Student;

public class MatriculaView {

	private JFrame frame;
	private JTextField tfNombre, tfApellidos, tfCiclo, tfMedia;
	private JLabel lblMatricula, lblProfe1, lblProfe2;
	private JButton btnAtras, btnSiguiente, btnCrear, btnBorrar, btnUpdate, btnCancelarActualizar, btnGuardar;
	private StudentDAO studentDAO;
	private ArrayList<Student> students;
	private int indice;

	/**
	 * Create the application.
	 */
	public MatriculaView() {
		initialize();
		this.studentDAO = new StudentDAO();
		this.students = studentDAO.getAll();
		indice = 0;
		if (this.students.size() > 0) {
			printAlumno();
		}
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

		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CrearAlumnoView();
				frame.dispose();
			}
		});
		btnCrear.setBounds(67, 351, 89, 23);
		frame.getContentPane().add(btnCrear);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(btnBorrar, "¿Estás seguro de que quieres borrar este registro?");
				if (confirmar == 0) {
					studentDAO.delete(students.get(indice));
					students.remove(students.get(indice));
					if (students.size() > 0) {
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
		btnUpdate.setBounds(244, 351, 115, 23);
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
				updateAlumno();
				interruptorEditar();
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
	}

	private void printAlumno() {
		if (students.size() > 0) {
			Student a = students.get(indice);
			tfNombre.setText(a.getNombre());
			tfApellidos.setText(a.getApellidos());
			tfCiclo.setText(a.getCiclo());
			tfMedia.setText(String.valueOf(a.getMedia()));
			if(a.getProfe1() != null)
				lblProfe1.setText(a.getProfe1().getNombre());
			if(a.getProfe2() != null)
				lblProfe2.setText(a.getProfe2().getNombre());
		}
	}

	private void printVacio() {
		tfNombre.setText("");
		tfApellidos.setText("");
		tfCiclo.setText("");
		tfMedia.setText("");
	}

	private void printAtras() {
		indice--;
		if (indice < 0) {
			indice = students.size() - 1;
		}
		printAlumno();
	}

	private void printDelante() {
		indice++;
		if (indice == students.size()) {
			indice = 0;
		}
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
		printAlumno();
	}
	
	private void updateAlumno() {
		Student a = students.get(indice);
		a.setNombre(tfNombre.getText());
		a.setApellidos(tfApellidos.getText());
		a.setCiclo(tfCiclo.getText());
		a.setMedia(Double.parseDouble(tfMedia.getText()));
		studentDAO.update(a);
	}
}
