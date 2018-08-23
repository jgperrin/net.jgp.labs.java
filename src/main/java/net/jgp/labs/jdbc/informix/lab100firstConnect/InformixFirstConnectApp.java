package net.jgp.labs.jdbc.informix.lab100firstConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InformixFirstConnectApp {

  private static final String JDBC_DRIVER =
      "com.informix.jdbc.IfxDriver";
  private static final String DB_URL =
      "jdbc:informix-sqli://ubuntumix:9088/reodb:INFORMIXSERVER=ol_umix;IFX_LOCK_MODE_WAIT=9;IFX_ISOLATION_LEVEL=1;IFX_AUTOFREE=1;IFX_TRIMTRAILINGSPACES=1";
  private static final String USER = "informix";
  private static final String PASS = "in4mix";

  public static void main(String[] args) {
    Connection conn = null;

    try {
      Class.forName(JDBC_DRIVER);
    } catch (ClassNotFoundException e) {
      System.err.println(
          "! Error while loading Informix JDBC driver: "
              + e.getMessage());
      return;
    }
    System.out.println("+ Informix JDBC driver loaded");
    try {
      conn = DriverManager.getConnection(DB_URL, USER,
          PASS);
    } catch (SQLException e) {
      System.err.println(
          "! Error while connecting to the database enginer: "
              + e.getMessage());
      return;
    }
    System.out.println("+ Connection to the database ok");
    try {
      conn.setAutoCommit(false);
    } catch (SQLException e) {
      System.err
          .println(
              "! Error while setting parameters (autocommit to false): "
                  + e.getMessage());
      return;
    }
    System.out.println("+ Setting parameters done");

    PreparedStatement findCountryStatement;
    try {
      findCountryStatement = conn
          .prepareStatement(
              "SELECT cty_id,cty_name,cty_sort_order FROM country ORDER BY cty_sort_order");
    } catch (SQLException e) {
      System.err.println(
          "! Error while preparing statement: "
              + e.getMessage());
      return;
    }
    System.out.println("+ Prepared statement done");

    ResultSet rs;
    try {
      rs = findCountryStatement.executeQuery();
    } catch (SQLException e) {
      System.err.println("! Error while executing query: "
          + e.getMessage());
      return;
    }
    System.out.println("+ Executing query");

    int countryId = 0;
    String countryName = "";
    try {
      while (rs.next()) {
        try {
          countryId = rs.getInt(1);
        } catch (SQLException e) {
          System.err.println(
              "! Error while reading country id: "
                  + e.getMessage());
          return;
        }
        try {
          countryName = rs.getString(2);
        } catch (SQLException e) {
          System.err.println(
              "! Error while reading country name: "
                  + e.getMessage());
          return;
        }
        System.out.println("Country #" + countryId + ": "
            + countryName);
      }
    } catch (SQLException e) {
      System.err
          .println("! Error while reading result set: " + e
              .getMessage());
    }
    System.out.println("+ Result set parsed");

    try {
      rs.close();
    } catch (SQLException e) {
      System.err
          .println("! Error while closing result set: " + e
              .getMessage());
    }
    System.out.println("+ Result set closed");
    try {
      findCountryStatement.close();
    } catch (SQLException e) {
      System.err.println(
          "! Error while closing prepared statement: "
              + e.getMessage());
      System.out.println("+ Prepared statement close");
    }
    try {
      conn.close();
    } catch (SQLException e) {
      System.err
          .println("! Error while closing connection: " + e
              .getMessage());
      return;
    }
    System.out.println("+ Connection closed");
  }

}
