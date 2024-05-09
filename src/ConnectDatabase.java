import java.sql.*;

public class ConnectDatabase {

    private String userName = "postgres";
    private String password = "12345";

    private String databaseName = "demo";
    private String host = "localhost";
    private int port = 5432;
    private Connection connection = null;

    private Statement statement = null ;



    public ConnectDatabase() {

        // jdbc:postgresql://localhost:5432/demo                                      //yazılan karekterleri utf8e göre işleme al veri eklerken tr karekterleri için sıkıntı çıkabiliyor
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName + "?useUnicode=true&characakterEncoding=utf8";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not Found");
        }

        try {
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    public void getTheEmployee(){

        String allEmployee = "Select * From employee";

        try {
            statement = connection.createStatement();

           ResultSet rs =  statement.executeQuery(allEmployee) ;

           while(rs.next()){

               int id = rs.getInt("id") ;
               String name = rs.getString("name") ;
               String surName = rs.getString("surname");
               String email = rs.getString("email") ;


               System.out.println("Id : " + id +
                  " Name :"  + name   +
                       " Surname :" + surName +
                        " Email : " + email);


           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertTheEmployee(){

        try {
            statement = connection.createStatement() ;
            String name = "Peace"  ;
            String surName = "saymış" ;
            String email = "peacedeli@gmail.com" ;
            String insertQuery = "INSERT INTO employee (id, name, surname, email) VALUES (6, '" + name + "', '" + surName + "', '" + email + "')";


            statement.executeUpdate(insertQuery) ;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTheEmployee() {

        try {
            statement = connection.createStatement() ;

            String updateQuery ="UPDATE employee SET email = 'barisyazman@gmail.com' where id = 5'";

            statement.executeUpdate(updateQuery) ;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void deleteTheEmployee(){
        try {
            statement = connection.createStatement();


            String deleteQuery = "Delete  From employee where id > 2" ;

        int value =      statement.executeUpdate(deleteQuery) ;

            System.out.println(value + "data was deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        ConnectDatabase connectDatabase = new ConnectDatabase();


        connectDatabase.getTheEmployee();

      //  connectDatabase.insertTheEmployee();
     //   connectDatabase.updateTheEmployee();

     //   connectDatabase.deleteTheEmployee();
    }
}
