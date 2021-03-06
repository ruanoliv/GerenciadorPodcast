package controle;

import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modelo.EpPodcast;
import modelo.EpisodioOuvido;

public class EpisodiosOuvidosControle implements Initializable {
	
	private App application;
	
	public void setApp(App application) {
		this.application = application;
	}
	
	@FXML
	private AnchorPane AnchorPane;
	
	@FXML
    private Button btTirar;
	
	@FXML
    private Button btVoltar;

    @FXML
    private TableView<EpPodcast> tableView;

    @FXML
    private TableColumn<EpPodcast, String> tbcAutor;

    @FXML
    private TableColumn<EpPodcast, String> tbcFaixaEtaria;

    @FXML
    private TableColumn<EpPodcast, String> tbcGenero;

    @FXML
    private TableColumn<EpPodcast, String> tbcTema;

    @FXML
    private TableColumn<EpPodcast, String> tbcTitulo;
    
    @FXML
    void voltarTela(ActionEvent event) {
    	if(application == null) {
    		
    	}else {
    		application.goToInicial();
    	}
    }
    
    @FXML
    void tirarLista(ActionEvent event) {
    	EpisodioOuvido.deletar(tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex()).getId(), application.usuario.getId());
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	public void fillTable() {
		
		tbcTitulo.setCellValueFactory(new PropertyValueFactory<EpPodcast, String>("titulo"));
		tbcGenero.setCellValueFactory(new PropertyValueFactory<EpPodcast, String>("genero"));
		tbcAutor.setCellValueFactory(new PropertyValueFactory<EpPodcast, String>("autor"));
		tbcTema.setCellValueFactory(new PropertyValueFactory<EpPodcast, String>("tema"));
		tbcFaixaEtaria.setCellValueFactory(new PropertyValueFactory<EpPodcast, String>("faixaEtaria"));
		tableView.setItems(FXCollections.observableArrayList(EpisodioOuvido.episodios(application.usuario.getId())));
		tableView.setVisible(false);
		tableView.setVisible(true);

	}
}
