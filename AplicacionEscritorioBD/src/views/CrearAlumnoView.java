package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.StudentDAO;
import dao.TeacherDAO;
import models.Student;
import models.Teacher;

public class CrearAlumnoView {

	private JFrame frame;
	private JTextField nameField, lastNameField, gradeField, averageField;
	private JLabel enrollmentLabel;
	private StudentDAO studentDAO;
	private TeacherDAO teacherDAO;
	private JButton createButton;
	private JComboBox<String> teacher1CBox, teacher2CBox;
	private ArrayList<Teacher> teachers;

	/**
	 * Create the application.
	 */
	public CrearAlumnoView() {
		this.studentDAO = new StudentDAO();
		this.teacherDAO = new TeacherDAO();
		this.teachers = teacherDAO.getAll();
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

		enrollmentLabel = new JLabel("Crear alumno");
		enrollmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 37));
		enrollmentLabel.setBounds(152, 11, 243, 45);
		frame.getContentPane().add(enrollmentLabel);


		nameField = new JTextField();
		nameField.setText("nombre");
		nameField.setBounds(101, 123, 243, 26);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);

		lastNameField = new JTextField();
		lastNameField.setText("apellidos");
		lastNameField.setColumns(10);
		lastNameField.setBounds(101, 170, 243, 26);
		frame.getContentPane().add(lastNameField);

		gradeField = new JTextField();
		gradeField.setToolTipText("ciclo");
		gradeField.setText("ciclo");
		gradeField.setColumns(10);
		gradeField.setBounds(101, 217, 243, 26);
		frame.getContentPane().add(gradeField);

		averageField = new JTextField();
		averageField.setToolTipText("media");
		averageField.setFont(new Font("Tahoma", Font.PLAIN, 26));
		averageField.setBounds(398, 123, 86, 70);
		averageField.setColumns(10);
		frame.getContentPane().add(averageField);
		
		createButton = new JButton("Crear");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarAlumno();
			}
		});
		createButton.setBounds(219, 366, 89, 23);
		frame.getContentPane().add(createButton);
		
		teacher1CBox = new JComboBox<String>();
		teacher1CBox.setEditable(true);
		teacher1CBox.setBounds(101, 301, 151, 22);
		frame.getContentPane().add(teacher1CBox);
		
		teacher2CBox = new JComboBox<String>();
		teacher2CBox.setEditable(true);
		teacher2CBox.setBounds(333, 301, 151, 22);
		frame.getContentPane().add(teacher2CBox);
		fillProfes();
	}

	private void insertarAlumno() {
		if(lastNameField.getText().isEmpty()
			|| nameField.getText().isEmpty()
			|| gradeField.getText().isEmpty()
			|| averageField.getText().isEmpty()
		) {
			JOptionPane.showMessageDialog(createButton, "Revisa todos los campos");
			return;
		} else {
			try {
				double media = Double.parseDouble(averageField.getText());
				
				if (media < 0 || media > 10) {
					
				}
				
				Student student = new Student(0, nameField.getText(), lastNameField.getText(), gradeField.getText(), media);
				
				// Sacar el valor de los profes de los comboboxes
				Teacher profe1 = teachers.get(teacher1CBox.getSelectedIndex());
				student.setProfe1(profe1);
				if(teacher2CBox.getSelectedIndex() != 0 && teacher1CBox.getSelectedIndex() != teacher2CBox.getSelectedIndex() - 1) {
					Teacher profe2 =  teachers.get(teacher2CBox.getSelectedIndex()-1); //+2 porque el primero es Ninguno
					student.setProfe2(profe2);
				}
				
				studentDAO.insert(student);
				JOptionPane.showMessageDialog(createButton, "Alumno creado");
				new MatriculaView();
				frame.dispose();

			} catch(Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(createButton, "La calificaci�n debe ser decimal.");
			}
		}
	}
	
	private void fillProfes() {
		teacher2CBox.addItem("Ninguno");
		for(Teacher p : teachers) {
			teacher1CBox.addItem(p.getNombre());
			teacher2CBox.addItem(p.getNombre());
		}
	}
}
