package es.iesteis;

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

    public HashMap<String, HashMap<Integer, String>> devolverEspecies() {
        HashMap<Integer, String> animales = new HashMap<>();
        String sql = "SELECT id, subespecie FROM subespecies where especiePrincipal like 'Animal'";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                animales.put(resultado.getInt("id"), resultado.getString("subespecie"));
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
                muertosVivientes.put(resultado.getInt("id"), resultado.getString("subespecie"));
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
                otros.put(resultado.getInt("id"), resultado.getString("subespecie"));
            }
        } catch (SQLException e) {
            System.out.println("Error ao conectar á base de datos: " + e.getMessage());
        }
        System.out.println(otros);

        HashMap<String, HashMap<Integer, String>> especies = new HashMap<>();
        especies.put("Animales", animales);
        especies.put("Muertos Vivientes", muertosVivientes);
        especies.put("Otros", otros);

        return especies;
    }

    public HashMap<Integer, String> devolverMonstruitos(String nombre){
        HashMap<Integer, String> monstruitos = new HashMap<>();
        String sql = "SELECT nombre, especie, forma FROM monstruito where nombre like ?";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, nombre);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                monstruitos.put(resultado.getInt("id"), resultado.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error ao conectar á base de datos: " + e.getMessage());
        }
        System.out.println(monstruitos);
        return monstruitos;
    }

    public String devolverHabilidadesMonstruito(String nombre){
        StringBuilder habilidades = new StringBuilder();
        String sql = "SELECT habilidad_id FROM personaje_habilidad_dominio where personaje_id like ?";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setString(1, nombre);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                habilidades.append(resultado.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error ao conectar á base de datos: " + e.getMessage());
        }
        System.out.println(habilidades);
        return nombre + ": " + habilidades;
    }

    public HashMap<String, String> devolverHabilidades(){
        HashMap<String, String> habilidades = new HashMap<>();
        String sql = "SELECT habilidad, descripcion FROM habilidades";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                habilidades.put(resultado.getString("habilidad"), resultado.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println("Error ao conectar á base de datos: " + e.getMessage());
        }
        System.out.println(habilidades);
        return habilidades;
    }

    public boolean eliminarMonstriuto(int id) {
        String query = "DELETE FROM Monstruito  WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, usuario, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() >= 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertarMonstriuto(String nombre, String especie, Forma forma) {
        String query = "insert itno Monstruito(nombre, especie, forma) values (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, usuario, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, especie);
            preparedStatement.setString(3, forma.name());
            return preparedStatement.executeUpdate() < 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}