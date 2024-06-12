package org.example.list_staff_javafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseHelper
{
    private static final String url = "jdbc:sqlite:library.db";

    //получение объекта подключения
    public static Connection Connection()
    {
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void CreateNewDataBase()
    {
        //указан ресурс который автоматически закроется после блок try-catch
        try(Connection conn = DataBaseHelper.Connection())
        {
            if (conn != null)
            {
                System.out.println("Соединение установлено");
            }
            else
            {
                System.out.println("Соединение не установлено");
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void CreatePersonTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS Person (\n" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  name TEXT NOT NULL,\n" +
                "  age INTEGER NOT NULL,\n" +
                "  idNumber TEXT NOT NULL UNIQUE\n" +
                ");";
        try(Connection conn = DataBaseHelper.Connection(); PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
