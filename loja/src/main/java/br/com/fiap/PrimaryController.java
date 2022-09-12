package br.com.fiap;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import br.com.fiap.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    @FXML
    TextField textFieldNome;
    @FXML
    TextField textFieldEmail;
    @FXML
    PasswordField passwordField;
    @FXML
    ChoiceBox<String> choiceBoxPerfil;
    @FXML
    CheckBox checkBoxAceite;
    @FXML
    Button buttonSalvar;

    String server = "sql719.main-hosting.eu";
    String database = "u553405907_fiap";
    String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    String username = "";
    String pass = "";
   
    public void salvar() {
        var usuario = carregarUsuarioDoFormulario();
        System.out.println(usuario);

        String sql = String.format("INSERT INTO USUARIOS VALUES (usuarios_seq.nextval, '%s', '%s', '%s', '%s')",
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getPerfil());

        System.out.println(sql);

        try {
            Connection con = DriverManager.getConnection(url, username, pass);
            Statement stm = con.createStatement();

            stm.execute(sql);

            con.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private Usuario carregarUsuarioDoFormulario() {
        String nome = textFieldNome.getText();
        String email = textFieldEmail.getText();
        String senha = passwordField.getText();
        String perfil = choiceBoxPerfil.getValue();

        return new Usuario(nome, email, senha, perfil);
    }

    public void handleCheckBoxAceite() {
        buttonSalvar.setDisable(!checkBoxAceite.isSelected());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        choiceBoxPerfil.getItems().addAll(List.of("USUARIO", "VENDEDOR", "GERENTE"));
    }

    public void abrirListaDeUsuarios() throws IOException {
        App.setRoot("secondary");
    }

}
