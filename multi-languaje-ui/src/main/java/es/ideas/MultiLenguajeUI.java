package es.ideas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class MultiLenguajeUI extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        
        MultiLenguajeUI.primaryStage = stage;
    
        //Cargamos la app con el fxml indicado y el idioma indicado.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("primary.fxml"));

        //Seleccionamos el idioma predeterminado
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/cadenas",
                Locale.getDefault()));
        Parent raiz = loader.load();

        //Cargamos la escena
        Scene scene = new Scene(raiz);  
        stage.setScene(scene);
        stage.show();
    }

      public static Stage getPrimaryStage(){
        return primaryStage;
    }


    public static void main(String[] args) {
        //Ejecutamos
        launch();
    }

}