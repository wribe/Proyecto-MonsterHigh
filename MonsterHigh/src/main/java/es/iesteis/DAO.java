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
        HashMap<Integer, String> animales = new HashMap<>();
        String sql = "SELECT id, subespecie FROM subespecies where especiePrincipal like 'Animal'";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                animales.put(resultado.getInt("id"), resultado.getString("sub"));
            }
        } catch (SQLException e) {
            System.out.println("Error ao conectar á base de datos: " + e.getMessage());
        }
        System.out.println(animales);

        HashMap<Integer, String> muertosVivientes = new HashMap<>();
        String sql1 = "SELECT id, subespecie FROM subespecies where especiePrincipal like 'Muerto Viviente'";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
             PreparedStatement sentencia = conexion.prepareStatement(sql1);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                muertosVivientes.put(resultado.getInt("id"), resultado.getString("sub"));
            }
        } catch (SQLException e) {
            System.out.println("Error ao conectar á base de datos: " + e.getMessage());
        }
        System.out.println(muertosVivientes);

        HashMap<Integer, String> otros = new HashMap<>();
        String sql2 = "SELECT id, subespecie FROM subespecies where especiePrincipal like 'Otro'";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
             PreparedStatement sentencia = conexion.prepareStatement(sql2);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                otros.put(resultado.getInt("id"), resultado.getString("sub"));
            }
        } catch (SQLException e) {
            System.out.println("Error ao conectar á base de datos: " + e.getMessage());
        }
        System.out.println(otros);

        HashMap<String, HashMap<Integer, String>> especies = new HashMap<>();
        especies.put("Animales", animales);
        especies.put("Muertos Vivientes", muertosVivientes);
        especies.pùt("Otros", otros);
    }