package es.ideas;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class PrimaryController implements Initializable {

    @FXML
    private ChoiceBox<String> cbSemana;
    @FXML
    private ToggleButton btnES;
    @FXML
    private ToggleButton btnUSA;
    @FXML
    private ToggleButton btnFR;
    @FXML
    private ToggleButton btnIT;
    @FXML
    private ToggleButton btnEN;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAceptar;
    @FXML
    private AreaChart<?, ?> notasDiseño;

    
    private MultiLenguajeUI aplicacionPrincipal;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Inicialización del ComboBox con los días de la semana
        //También tiene que traducirse
        String dias_semana[] = {"lunes", "martes", "miércoles", "jueves",
           "viernes", "sábado", "domingo"};
       cbSemana.setItems(FXCollections.observableArrayList(dias_semana));

     //Muestra los valores de Locale cada vez que se carga la escena
        Locale localeActual = Locale.getDefault();
        System.out.println("Locale Seleccionado: \n" +
                " getLanguage: " + localeActual.getLanguage() +
                " - getCountry: " + localeActual.getCountry() +
                " - getDisplayName: " + localeActual.getDisplayName()+
                "\n - getDisplayVariant: " + localeActual.getDisplayVariant()+
                " - getDisplayCountry: " + localeActual.getDisplayCountry()+
                " - getDisplayLanguage: " + localeActual.getDisplayLanguage() +
                "\n - getISO3Country: " + localeActual.getISO3Country() + 
                " - getISO3Languaje: " + localeActual.getISO3Language() + 
                " - getDisplayScript: " + localeActual.getDisplayScript());
    
        //Crear un ToggleGroup para agrupar los ToggleButton
        //Sólo puede haber uno seleccionado cada vez.
        ToggleGroup tg= new ToggleGroup();
        tg.getToggles().addAll(btnES,btnUSA,btnFR,btnIT,btnEN);
    
         /**
         * Listener para cambiar el idioma
         */        
        tg.selectedToggleProperty().addListener((obs,oldValue,newValue)->{
            if (newValue != null ){
               ToggleButton tb = (ToggleButton) newValue.getToggleGroup()
                       .getSelectedToggle();               
               //Se comprueba el valor del Texto del ToggleButton y cambiamos el idioma predeterminado
               switch (tb.getText()){
                   case "btnEN":
                       Locale.setDefault(Locale.ENGLISH);
                       System.out.println("Has seleccionado idioma INGLÉS");
                       
                       break;  
                   case "btnFR":
                       Locale.setDefault(Locale.FRENCH);
                       System.out.println("Has seleccionado idioma FRANCÉS");
                       break;
                   case "btnUSA":
                       Locale.setDefault(Locale.ENGLISH.US);
                       System.out.println("Has seleccionado idioma FRANCÉS");
                       break;
                   case "btnIT":
                       Locale.setDefault(Locale.ITALIAN);
                       System.out.println("Has seleccionado idioma FRANCÉS");
                       break;
                   default:
                       Locale.setDefault(new Locale("es", "ES"));
                       System.out.println("Has seleccionado idioma ESPAÑOL");                       
               }
               try{
                Parent pane = getFXMLLoader().load();
                MultiLenguajeUI.getPrimaryStage().getScene().setRoot(pane);
               }catch(IOException ieo){                   
               }               
               MultiLenguajeUI.getPrimaryStage().show();
            }
               
        });
    
    }
    
   private FXMLLoader getFXMLLoader(){
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("es/ideas/i18n/cadenas",
                Locale.getDefault()));
        loader.setLocation(getClass().getResource("primary.fxml"));
        return loader;
    }

    public void setMainWindow(MultiLenguajeUI main){
        this.aplicacionPrincipal= main;
    }
    
    
    
    
    
}
