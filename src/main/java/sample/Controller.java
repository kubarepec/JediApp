package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Controller {
    @FXML
    public void initialize(){

        JediList();
        KolorMieczaChoice.getItems().setAll(KolorMiecza.values());
        FreeJediList();
        ZakonList();



    }


    @FXML
    private GridPane root;

    @FXML
    private ListView<Jedi> JediTable;

    @FXML
    private TextField JediNameText;

    @FXML
    ComboBox<KolorMiecza> KolorMieczaChoice;

    @FXML
    private Slider MocSlider;

    @FXML
    private RadioButton JasnoRadio;

    @FXML
    private ToggleGroup stronaMocy;

    @FXML
    private RadioButton NieJasnaRadio;

    @FXML
    private Button ImportJediButton;

    @FXML
    private Button ExportJediButton;

    @FXML
    private TextField ZakonDbAdressText;

    @FXML
    private TextField JediDbAdressText;

    @FXML
    private Button ZarejestrujJediButton;

    @FXML
    private Button WyczyscJediButton;

    @FXML
    private ListView<Zakon> ZakonTable;

    @FXML
    private TextField ZakonNameText;

    @FXML
    private Button ImportZakonButton;

    @FXML
    private Button ExportZakonButton;

    @FXML
    private Button ZarejestrujZakonButton;

    @FXML
    private Button WyczyscZakonButton;

    @FXML
    private ListView<Jedi> FreeJediTable;

    @FXML
    private Button JediChoiceButton;

    @FXML
    void ZarejestrujJedi(ActionEvent event) throws SQLException, ClassNotFoundException {

        Jedi jedi=new Jedi(JediNameText.getText(),KolorMieczaChoice.getValue(), (int) MocSlider.getValue(),JasnoRadio.isSelected());
        long id=Database.JediInsertDB(jedi);
        jedi.setIdJedi((int) id);
        JediList();
        FreeJediList();
        JediNameText.clear();
        KolorMieczaChoice.setValue(null);

    }
    @FXML
    void JediList(){
        ObservableList<Jedi> JList = FXCollections.observableArrayList(Jedi.listaJedi);
        JediTable.setItems(JList);
    }

    @FXML
    void FreeJediList(){
        ObservableList<Jedi> FreeJList = FXCollections.observableArrayList();
        for(Jedi j: Jedi.listaJedi) {
            if (j.getZakon() == null)
                FreeJList.add(j);
        }
        FreeJediTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        FreeJediTable.setItems(FreeJList);

    }
    @FXML
    void ZakonList(){
        ObservableList<Zakon> ZakonList = FXCollections.observableArrayList(Zakon.listaZakon);
        ZakonTable.setItems(ZakonList);
    }
    @FXML
    void JediChoiceAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Zakon z=ZakonTable.getSelectionModel().getSelectedItem();
        ZakonAddJedi(z);




    }
    @FXML
    void ZakonRegisterButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        Zakon z=new Zakon(ZakonNameText.getText());
        long id=Database.ZakonInsertDB(z);
        z.setIdZakon((int) id);

        ZakonAddJedi(z);
        JediList();
        FreeJediList();
        ZakonList();


    }
    @FXML
    void ZakonAddJedi(Zakon z) throws SQLException, ClassNotFoundException {
        ObservableList<Jedi> SelectedFreeJedi =  FreeJediTable.getSelectionModel().getSelectedItems();
        for(Jedi j:SelectedFreeJedi) {
            z.dodajJedi(j);
            j.setZakon(z);
            Database.JediZakonInsertDB(z,j);
        }
    }
    @FXML
    void ExportJediAction(ActionEvent event) throws FileNotFoundException {
        FileImportExport.objectToFile(JediDbAdressText.getText());
    }
    @FXML
    void ImportJediAction(ActionEvent event) throws FileNotFoundException {
        FileImportExport.fileToObject(JediDbAdressText.getText());
    }
    @FXML
    void ExportZakonAction(ActionEvent event) throws FileNotFoundException {
        FileImportExport.objectToFileZakon(ZakonDbAdressText.getText());
    }
    @FXML
    void ImportZakonAction(ActionEvent event) throws FileNotFoundException {
        FileImportExport.fileToObject(ZakonDbAdressText.getText());
    }
    @FXML
    void ImportJediFile(ActionEvent event) throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(null);
        JediDbAdressText.setText(file.getAbsolutePath());

    }
}