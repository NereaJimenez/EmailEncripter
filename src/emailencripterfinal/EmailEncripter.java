/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import emailencripterfinal.EmailEncriptController;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

/**
 *
 * @author nerea
 */
public class EmailEncripter extends Application {
      
    
    /**
     * Main method 
     * @param args 
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * Method start for the JavaFX Application
     * @param primaryStage
     * @throws Exception 
     */
   
    @Override
    public void start(Stage primaryStage) throws Exception {
         FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/emailencripterfinal/EmailEncripter.fxml"));
            Parent root = loader.load();
            EmailEncriptController controller = loader.getController();
            //Get controller from the loader
            controller.setStage(primaryStage);
            //Initialize the primary stage of the application
            controller.initStage(root);
            
    }

    

    
    
}

   