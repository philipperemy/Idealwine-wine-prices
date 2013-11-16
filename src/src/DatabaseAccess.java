package src;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

public class DatabaseAccess {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public DatabaseAccess() {
		readDataBase();
	}

	public static void main(String[] args) throws IOException {
		DatabaseAccess da = new DatabaseAccess();
		f1(da);
	}

	public static void f2(DatabaseAccess databaseAccess) {
		try {
			System.out.println(databaseAccess.getLastQuote("LAFITE_ROTHSCHILD",
					"1989", "Bouteille"));
		} catch (QuoteNotExistException e) {
			e.printStackTrace();
		}
	}

	public static void f1(DatabaseAccess databaseAccess) throws IOException {
		List<Entry> entries = databaseAccess.getMostRecentEntries();
		File file = new File("test-csv.csv");
		if (!file.exists()) {
			file.createNewFile();
		}
		PrintWriter pw = new PrintWriter(file);
		for (Entry entry : entries) {
			pw.println(entry.toCsv());
			System.out.println(entry.toCsv());
		}
		pw.close();
	}

	public String getLastQuote(String name, String millesime, String content)
			throws QuoteNotExistException {
		String query = "SELECT `quote` FROM `wine_quotes`" + "WHERE `name` = "
				+ "'" + name + "'" + " AND `millesime` = " + "'" + millesime
				+ "'" + " AND `content` = " + "'" + content + "'"
				+ " ORDER BY `timestamp` DESC LIMIT 1 ";
		try {
			ResultSet resultSet = connect.createStatement().executeQuery(query);
			while (resultSet.next()) {
				return resultSet.getString("QUOTE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Entry> getMostRecentEntries() {
		String query = "SELECT * FROM `wine_quotes` ORDER BY `timestamp` DESC";
		List<Entry> entries = new ArrayList<>();
		try {
			ResultSet resultSet = connect.createStatement().executeQuery(query);
			while (resultSet.next()) {
				Entry entry = new Entry();
				entry.quote = resultSet.getString("QUOTE");
				entry.name = resultSet.getString("NAME");
				entry.millesime = resultSet.getString("MILLESIME");
				entry.content = resultSet.getString("CONTENT");
				entry.timestamp = resultSet.getTimestamp("TIMESTAMP");
				insertEntry(entries, entry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return entries;
	}

	private void insertEntry(List<Entry> entries, Entry entryAdd) {

		boolean alreadyInEntries = false;
		for (Entry entry : entries) {
			if (entry.getKey().equalsIgnoreCase(entryAdd.getKey())) {
				alreadyInEntries = true;
				if (entryAdd.timestamp.after(entry.timestamp)) {
					entry = new Entry(entryAdd);
				}
				return;
			}
		}

		if(!alreadyInEntries) {			
			entries.add(entryAdd);
		}
	}

	public synchronized void insertWineQuote(String name, String millesime, String content,
			String quote) {
		String query = "INSERT INTO `wine`.`wine_quotes` (`ID`, `NAME`, `MILLESIME`, `QUOTE`, `CONTENT`, `TIMESTAMP`) VALUES (NULL, '"
				+ name
				+ "', '"
				+ millesime
				+ "', '"
				+ quote
				+ "', '"
				+ content
				+ "', CURRENT_TIMESTAMP);";

		try {

			String lastQuote = getLastQuote(name, millesime, content);
			if ((lastQuote == null) || (!quote.equalsIgnoreCase(lastQuote))) {
				System.out.println("( " + name + " , " + millesime + " , "
						+ content + " , " + quote
						+ " EUR ) ... inserting quote");
				connect.createStatement().execute(query);
			} else {
				System.out.println("( " + name + " , " + millesime + " , "
						+ content + " , " + quote + " EUR ) ... no update");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void readDataBase() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/wine?"
							+ "user=root&password=");

		} catch (CommunicationsException ce) {
			ce.printStackTrace();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}