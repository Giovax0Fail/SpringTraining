package it.corso.spring.dao;

import it.corso.spring.model.Customer;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao {

    static final String DB_URL = "jdbc:mysql://localhost/Gestionale";
    static final String USER = "root";
    static final String PASS = "";

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM utenti");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Customer customer = new Customer(id, nome, email);
                customers.add(customer);
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'accesso al database: " + e.getMessage());
        }

        if (customers.isEmpty()) {
            System.out.println("Nessun cliente trovato nel database.");
        } else {
            System.out.println("Trovati " + customers.size() + " clienti.");

            for (Customer customer : customers) {
                System.out.println("ID: " + customer.getId() + ", Nome: " + customer.getCustomerName() + ", Email: " + customer.getEmail());
            }
        }
        return customers;
    }


    public Customer addCustomer(Customer customer) {
        String sql = "INSERT INTO utenti (nome, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getEmail());


            int modifiedRows = pstmt.executeUpdate();


            if (modifiedRows > 0) {
                System.out.println("Cliente inserito con successo: " + customer.getCustomerName());
                return customer;
            } else {
                System.out.println("Errore nell'inserimento del cliente.");
                return null;
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento del cliente: " + e.getMessage());
        }
        return null; // Restituisci null se non è riuscito a inserire il cliente
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM utenti WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Imposta il parametro ID nella query
            pstmt.setInt(1, id);

            // Esegui la query di eliminazione
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cliente con ID " + id + " eliminato con successo.");
            } else {
                System.out.println("Nessun cliente trovato con l'ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione del cliente: " + e.getMessage());
        }
    }

    public Customer updateCustomer(Customer customer) {
        String sql = "UPDATE utenti SET nome = ?, email = ? WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Impostiamo i parametri per il PreparedStatement
            pstmt.setString(1, customer.getCustomerName());  // Impostiamo il nome del cliente
            pstmt.setString(2, customer.getEmail());         // Impostiamo l'email del cliente
            pstmt.setInt(3, customer.getId());               // Impostiamo l'ID del cliente da aggiornare

            // Eseguiamo la query di aggiornamento
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cliente con ID " + customer.getId() + " aggiornato con successo.");
                return customer;  // Restituiamo il cliente aggiornato
            } else {
                System.out.println("Nessun cliente trovato con l'ID: " + customer.getId());
                return null;  // Nessun cliente trovato, quindi restituiamo null
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento del cliente: " + e.getMessage());
        }

        return null;  // Restituiamo null in caso di errore
    }


}
