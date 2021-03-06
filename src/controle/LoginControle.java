package controle;



import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import dao.DaoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Usuario;

public class LoginControle implements Initializable {
	private App application;
	
	public void setApp(App application) {
		this.application = application;
		
	}

	@FXML
    private AnchorPane AnchorPane;
	
    @FXML
    private Button btEntrar;

    @FXML
    private Label btTelaCadastro;

    @FXML
    private Label lbAlert;

    @FXML
    private TextField txtEmail;
       
    @FXML
    void Entrar(ActionEvent event) {
    	String email = txtEmail.getText();
    	if(buscarPorEmail(email).getId() != null) {
    		if (application == null) {
    		} else {
    			application.usuario.setId(buscarPorEmail(email).getId());
    			application.usuario.setNome(buscarPorEmail(email).getNome());
    			application.usuario.setEmail(email);
    			application.usuario.setSenha(buscarPorEmail(email).getSenha());
    			application.usuario.setAdministrador(buscarPorEmail(email).getAdministrador());
    			application.goToInicial();
    		}
    	}else {
    		lbAlert.setVisible(true);
    	}
		
    }

    
	


	@FXML
    void TelaCadastro(MouseEvent event) {
    	if (application == null) {
		} else {
			application.goToCadastrarUsuario();
		}
    }
    
    public static Usuario buscarPorEmail(String email){
        
        return DaoFactory.getUsuarioDao().buscarPorEmail(email);
        
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbAlert.setVisible(false);
		
	}
    

}
