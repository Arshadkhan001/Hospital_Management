package HospitalManagementSystem;

import java.awt.geom.RectangularShape;
import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import  java.util.Scanner;

public class Patient {

    private Connection connection;
    private Scanner scanner;

    //defining constructor
    public  Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    //patient method user input
    public void addPatient(){
        System.out.println("Enter Patient Name: ");
        String name = scanner.next();

        System.out.println("Enter Patient Age: ");
        int age = scanner.nextInt();

        System.out.println("Enter Patient Gender: ");
        String gender = scanner.next();

        try{
            //writing query
            String query = "INSERT INTO patients(name, age, gender) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);

            int affectedRaw = preparedStatement.executeUpdate();
            if (affectedRaw > 0){
                System.out.println("Patient added successfully");
            }
            else{
                System.out.println("Failed to add patients:");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void viewPatients(){
        String query = "SELECT * FROM patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultset = preparedStatement.executeQuery();
            System.out.println("patients: ");

            System.out.println("+------------+--------------------+-------+----------+");
            System.out.println("| Patient ID | Name               | Age   | Gender   |");
            System.out.println("+------------+--------------------+-------+----------+");

            //sql data now stored in a java variables below
            while (resultset.next()){
                int id = resultset.getInt("id");
                String name = resultset.getString("name");
                int age = resultset.getInt("age");
                String geneder = resultset.getString("geneder");

                System.out.printf("|%-12s|%-20s|%-7s|%-10s");
                System.out.println("+------------+--------------------+-------+----------+");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
