package org.example.list_staff_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class ListStaffMain extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(ListStaffMain.class.getResource("ListStaffView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),700,350);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("List Staff");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[]args)
    {
        launch();
    }
}
