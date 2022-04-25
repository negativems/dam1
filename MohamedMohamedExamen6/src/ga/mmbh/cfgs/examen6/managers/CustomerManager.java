package ga.mmbh.cfgs.examen6.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ga.mmbh.cfgs.examen6.models.Customer;
import ga.mmbh.cfgs.examen6.utils.FileUtils;

public class CustomerManager {

	private List<Customer> customersList;
	
	public CustomerManager() {
		this.customersList = new ArrayList<>();
		loadCustomers();
		createSQLFile();
	}
	
	/**
	 * Carga los clientes al objeto Customer
	 * y lo añade a la lista customersList
	 */
	private void loadCustomers() {
		File file = new File("resources/customers.csv");
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file, "UTF-8");
			scanner.nextLine(); // Skip the first one

			// Itera cada línea del archivo csv
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				List<String> rowList = new ArrayList<>();
				Iterator<String> iterator = Arrays.asList(line.split(",")).iterator();
				
				// Itera cada elemento (separado por coma) de la línea
				while (iterator.hasNext()) {
					rowList.add(iterator.next());
				}
				
				Customer customer = new Customer(rowList.toArray(new String[0]));
				if (!customer.getEmail().contains("@aol.")) {
					customersList.add(customer);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		scanner.close();
	}
	
	/**
	 * Crea el archivo SQL en /resources/
	 */
	private void createSQLFile() {
		System.out.println("Generando archivo SQL...");
		String sql = "";
		
		sql += "DROP DATABASE IF EXISTS bdExamen;\n";
		sql += "CREATE DATABASE bdExamen;\n";
		sql += "USE bdExamen;\n";
		
		sql += "CREATE TABLE `bdExamen`.`customers` (\r\n"
				+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `name` VARCHAR(45) NOT NULL,\r\n"
				+ "  `email` VARCHAR(45) NOT NULL,\r\n"
				+ "  `datebirth` DATE NOT NULL,\r\n"
				+ "  `address` VARCHAR(80) NULL,\r\n"
				+ "  `balance` FLOAT NULL,\r\n"
				+ "  PRIMARY KEY (`id`)\r\n"
				+ ");\n\n";
		
		sql += "INSERT INTO `bdExamen`.`customers`(name, email, datebirth, address, balance)\r\n"
				+ "VALUES\n";
		
		Iterator<Customer> iterator = customersList.iterator();
		while (iterator.hasNext()) {
			Customer customer = iterator.next();
			String name = customer.getName().replaceAll("'", "''");
			String email = customer.getEmail().replaceAll("'", "''");
			String dateBirth = customer.getDatebirth().replaceAll("'", "''");
			dateBirth = dateBirth.split("-")[2] + "-" + dateBirth.split("-")[1] + "-" + dateBirth.split("-")[0];
			String address = customer.getAddress().replaceAll("'", "''");
			float balance = customer.getBalance();
			
			sql += "('" + name + "', '" + email + "', '" + dateBirth + "', '" +
					address + "', " + balance + ")" + (iterator.hasNext() ? ",\n" : ";");
		}
		
		File file = FileUtils.createFile("insertCustomers.sql");
		FileUtils.writeToFile(file, sql);
		
		System.out.println("Archivo SQL generado en /resources/");
	}
}
