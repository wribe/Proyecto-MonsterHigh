package gal.iesteis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;

public class DAO {
    private String url;
    private String usuario;
    private String password;

    public DAO(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }

    public HashMap<String, Integer> devolverEspecies() {
        HashMap<String, String> Animales = new HashMap<>();
        String sql = "SELECT especiePrincipal, subespecie FROM subespecies";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                paises.put(resultado.getString("especiePrincipal"), resultado.getString("sub"));
            }
        } catch (SQLException e) {
            System.out.println("Error ao conectar á base de datos: " + e.getMessage());
        }

        return paises;
    }