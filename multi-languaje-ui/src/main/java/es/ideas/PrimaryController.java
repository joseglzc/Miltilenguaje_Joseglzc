package es.ideas;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

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
    
    //Cargamos le FXMLLoader para recrgar la página con el idioma nuevo
    private FXMLLoader getFXMLLoader() {
        FXMLLoader loader = new FXMLLoader();
        //selecionamos el idioma predeterminado
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/cadenas",
                Locale.getDefault()));
        //cargamos el fxml
        loader.setLocation(getClass().getResource("primary.fxml"));
        return loader;
    }

    public void setMainWindow(MultiLenguajeUI main) {
        this.aplicacionPrincipal = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Inicialización del ComboBox con los días de la semana
        String dias_semana[] = {rb.getString("lunes"),
            rb.getString("martes"),
            rb.getString("miercoles"),
            rb.getString("jueves"),
            rb.getString("viernes"),
            rb.getString("sabado"),
            rb.getString("domingo")};
        cbSemana.setItems(FXCollections.observableArrayList(dias_semana));

        //Creación del ToggleGroup para poder cambiar de idioma
        ToggleGroup tg = new ToggleGroup();
        tg.getToggles().addAll(btnES, btnUSA, btnFR, btnIT, btnEN);

        /**
         * Listener para cambiar el idioma
         */
        tg.selectedToggleProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                ToggleButton tb = (ToggleButton) newValue.getToggleGroup()
                        .getSelectedToggle();
                //Se comprueba el valor del Texto del ToggleButton y cambiamos
                //el idioma predeterminado
                switch (tb.getText()) {
                    case "Ingles":
                        Locale.setDefault(Locale.ENGLISH);
                        System.out.println("Has seleccionado idioma INGLÉS");

                        break;
                    case "Frances":
                        Locale.setDefault(Locale.FRENCH);
                        System.out.println("Has seleccionado idioma FRANCÉS");
                        break;
                    case "EEUU":
                        Locale.setDefault(Locale.US);
                        System.out.println("Has seleccionado idioma EEUU");
                        break;
                    case "Italiano":
                        Locale.setDefault(Locale.ITALIAN);
                        System.out.println("Has seleccionado idioma Italiano");
                        break;
                    case "Español":
                        Locale.setDefault(new Locale("es"));
                        System.out.println("Has seleccionado idioma Español");
                        break;
                }
                //Para que no haya excepciones intentamos que se recargue la app
                try {
                    Parent pane = getFXMLLoader().load();
                    MultiLenguajeUI.getPrimaryStage().getScene().setRoot(pane);
                } catch (IOException ieo) {
                }
                MultiLenguajeUI.getPrimaryStage().show();
                newValue.getToggleGroup().getSelectedToggle().setSelected(false);
            }

        });

    }

}
