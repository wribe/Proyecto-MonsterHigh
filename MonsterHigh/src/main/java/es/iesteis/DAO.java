package es.iesteis;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;

public class DAO {
    private String url;
    private String usuario;
    private String password;

    public DAO(String url, String usuario, String password) {
        this.url =  "jdbc:mysql://localhost:3306/MonsterHigh";
        this.usuario =  "root";
        this.password = "Abc123."; //password; // casa: 122436 clase: Abc123.
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

    public HashMap<String, Monstruito> devolverMonstruito(boolean colmillos, boolean gafas, boolean zombie, String especie){ // cambiarlo por string para decir si/no??
        HashMap<String, Monstruito> monstruitos = new HashMap<>();
        String sql = "SELECT m.nombre, m.especie, e.descipcion, m.color_piel, m.color_pelo, m.colmillos, m.gafas, m.alas, zombie FROM monstruito as m join subespecies as e on (m.especie = e.subespecie) WHERE m.colmillos = ? AND m.gafas = ? AND m.zombie = ? and m.especie = ?";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
             PreparedStatement sentencia = conexion.prepareStatement(sql)){
            sentencia.setBoolean(1, colmillos);
            sentencia.setBoolean(2, gafas);
            sentencia.setBoolean(3, zombie);
            sentencia.setString(4, especie);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Monstruito monstruito = new Monstruito(
                        resultado.getString("nombre"),
                        new Especies(especie, resultado.getString("descipcion")),
                        ColorPiel.valueOf(resultado.getString("color_piel").toUpperCase()),
                        ColorPelo.valueOf(resultado.getString("color_pelo").toUpperCase().replace(" ", "_")),
                        resultado.getBoolean("colmillos"),
                        resultado.getBoolean("gafas"),
                        resultado.getBoolean("alas")
                ) {
                    @Override
                    public void describir() {

                    }
                };
                monstruitos.put(resultado.getString("nombre"), monstruito);
            }
        } catch (SQLException e) {
            System.out.println("Error ao conectar á base de datos: " + e.getMessage());
        }
        System.out.println(monstruitos);
        return monstruitos;
    }

    public ArrayList<Monstruito> devolverMonstruitoFiltrado(boolean colmillos, boolean gafas, boolean zombie, String especie){
        ArrayList<Monstruito> monstruitos = new ArrayList<>();
        String sql = "SELECT m.nombre, m.especie, e.descipcion, m.color_piel, m.color_pelo, m.colmillos, m.gafas, m.alas, zombie FROM monstruito as m join subespecies as e on (m.especie = e.subespecie) WHERE colmillos = ? AND gafas = ? AND zombie = ? and m.especie = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, password);
             PreparedStatement prs = conn.prepareStatement(sql)) {

            prs.setBoolean(1, colmillos);
            prs.setBoolean(2, gafas);
            prs.setBoolean(3, zombie);
            prs.setString(4, especie);

            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                String e = rs.getString("especie");;
                ColorPiel colorPiel = ColorPiel.valueOf(rs.getString("color_piel").toUpperCase());
                ColorPelo pelo = ColorPelo.valueOf(rs.getString("color_pelo").toUpperCase().replace(" ", "_"));

                Monstruito m = switch (e.toLowerCase()) {
                    case "vampiro" -> new Vampiro(rs.getString("nombre"), new Especies(e,rs.getString("descipcion") ), colorPiel, pelo,
                            rs.getBoolean("colmillos"), rs.getBoolean("gafas"), rs.getBoolean("alas"));
                    case "zombie" -> new Zombie(rs.getString("nombre"),new Especies(e, rs.getString("descipcion")), colorPiel, pelo,
                            rs.getBoolean("colmillos"), rs.getBoolean("gafas"), rs.getBoolean("alas"));
                    case "licantropo" -> new Licantropo(rs.getString("nombre"),new Especies(e, rs.getString("descipcion")), colorPiel, pelo,
                            rs.getBoolean("colmillos"), rs.getBoolean("gafas"), rs.getBoolean("alas"));
                    case "monstruo" -> new Monstruo(rs.getString("nombre"),new Especies(e, rs.getString("descipcion")), colorPiel, pelo,
                            rs.getBoolean("colmillos"), rs.getBoolean("gafas"), rs.getBoolean("alas"));
                    case "ghoul" -> new Ghoul(rs.getString("nombre"),new Especies(e, rs.getString("descipcion")), colorPiel, pelo,
                            rs.getBoolean("colmillos"), rs.getBoolean("gafas"), rs.getBoolean("alas"));
                    case "momia" -> new Momia(rs.getString("nombre"),new Especies(e, rs.getString("descipcion")), colorPiel, pelo,
                            rs.getBoolean("colmillos"), rs.getBoolean("gafas"), rs.getBoolean("alas"));
                    case "gorgona" -> new Gorgona(rs.getString("nombre"),new Especies(e, rs.getString("descipcion")), colorPiel, pelo,
                            rs.getBoolean("colmillos"), rs.getBoolean("gafas"), rs.getBoolean("alas"));
                    default -> null;
                };

                if (m != null) monstruitos.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (monstruitos.isEmpty()) {
            throw new PersonajeNoEncontrado("❌ No se encontraron personajes con esas características");
        }

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

    public HashMap<String, LocalDate> devolverMatriculados(){
        HashMap<String, LocalDate> matriculados = new HashMap<>();
        String sql = "select alumno, fecha_inicio, fecha_fin from monsterhigh ";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password);
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()) {
            // Period p = Period.between(resultado.getDate("fecha_inicio").toLocalDate(), resultado.getDate("fecha_fin").toLocalDate());
            while (resultado.next()) {
                matriculados.put(resultado.getString("alumno"), resultado.getDate("fecha_incio").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println("Error ao conectar á base de datos: " + e.getMessage());
        }
        System.out.println(matriculados);
        return matriculados;
    }

    public boolean eliminarMonstriuto(String nombre) {
        String query = "DELETE FROM Monstruito  WHERE nombre like ?";

        try (Connection connection = DriverManager.getConnection(url, usuario, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombre);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarAlumno(String nombre) {
        String query = "DELETE FROM MonsterHigh  WHERE alumno like ?";

        try (Connection connection = DriverManager.getConnection(url, usuario, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombre);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertarMonstriuto(String nombre, String especie, Forma forma) {
        String query = "insert into Monstruito(nombre, especie, forma) values (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, usuario, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, especie);
            preparedStatement.setString(3, forma.name());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean matricular(String nombre, LocalDate fecha) {
        String query = "insert into MonsterHigh(alumno, matriculado, fecha_inicio) values (?, true, ?)";

        try (Connection connection = DriverManager.getConnection(url, usuario, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setDate(2, Date.valueOf(fecha));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean graduar(String nombre) {
        String query = "update MonsterHigh set fecha_fin = ?, matriculado = false where alunmo like ?";

        try (Connection connection = DriverManager.getConnection(url, usuario, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(2, nombre);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}