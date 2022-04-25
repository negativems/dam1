package ga.mmbh.cfgs.examen6.models;

public class Customer {

	private String name, email, datebirth, address;
	private float balance;

	/**
	 * Customer: Representa cada cliente del archivo customers.csv
	 * @param name Nombre del cliente
	 * @param email Correo del cliente
	 * @param datebirth Fecha de nacimiento del cliente
	 * @param address Dirección del cliente
	 * @param balance Cantidad monetaria del cliente
	 */
	public Customer(String name, String email, String datebirth, String address, String balance) {
		this.name = name;
		this.email = email;
		this.datebirth = datebirth;
		this.address = address;
		
		balance = balance.replaceAll("\"", "");
		balance = balance.replaceAll(",", "");
		balance = balance.replaceAll("€", "");
		float balanceFormated = Float.parseFloat(balance);
		this.balance = balanceFormated;
	}
	
	/**
	 * Genera un cliente a partir de las columnas del archivo csv
	 * @param columns Array de las columnas
	 */
	public Customer(String[] columns) {
		this(columns[0], columns[1], columns[2], columns[3], columns[4]);
	}

	
	// Getters
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getDatebirth() {
		return datebirth;
	}

	public String getAddress() {
		return address;
	}

	public float getBalance() {
		return balance;
	}
	
}
