/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailencripterfinal;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author nerea
 */
public class EmailEncriptController {
    
    protected Stage stage;
     
    @FXML
    PasswordField txtKey;
    @FXML
    TextField txtEmail;
    @FXML
    PasswordField txtPassword;
    @FXML
    Button btnEncript;
    
    public void setStage(Stage primaryStage) {
        stage=primaryStage;
    }
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        btnEncript.setOnAction(this::encript);
        stage.show();
        
    }
    public void encript(javafx.event.ActionEvent event) {
         String emailS="email";
         String passS="pass";
         String privKey="privKey";
	int iterationCount = 65000;
	String skfAlgorithm = "PBKDF2WithHmacSHA1";
	byte[] salt = "Hello world!!!!!".getBytes(); // Exactly 16 bytes
	KeySpec spec = new PBEKeySpec(txtKey.getText().toCharArray(), salt, iterationCount, 128); // AES-128
	SecretKeyFactory factory;
	ObjectOutputStream oos = null;
	try {
            factory = SecretKeyFactory.getInstance(skfAlgorithm);
            byte[] key = factory.generateSecret(spec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");
            oos = new ObjectOutputStream(new FileOutputStream(privKey));
            oos.writeObject(privateKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
	    byte[] emailE = cipher.doFinal(txtEmail.getText().getBytes());
	    byte[] iv1 = cipher.getIV();
				
	    //Save both the encoded message and the initialization vector into a file
	    oos = new ObjectOutputStream(new FileOutputStream(emailS));
	    oos.writeObject(iv1);
	    oos.writeObject(emailE);
                                
            byte[] passE = cipher.doFinal(txtPassword.getText().getBytes());
	    byte[] iv2 = cipher.getIV();
				
				
            //Save both the encoded message and the initialization vector into a file
            oos = new ObjectOutputStream(new FileOutputStream(passS));
            oos.writeObject(iv2);
            oos.writeObject(passE);
	
				
	} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
	} catch (InvalidKeySpecException e) {
				e.printStackTrace();
	} catch (NoSuchPaddingException e) {
				e.printStackTrace();
	} catch (InvalidKeyException e) {
				e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
	} catch (BadPaddingException e) { //Clave introducida no es correcta
				e.printStackTrace();
	} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	}finally {
            if(oos!=null) {
		try {
                	oos.close();
                    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
                    }
            }
				
	}
    }
}
