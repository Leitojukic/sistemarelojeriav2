package com.relojeria.ui;

import com.relojeria.utils.JWTUtil;
import javax.swing.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        // Crear los elementos del formulario
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar sesión");

        loginButton.addActionListener(e -> login());

        // Crear y configurar la interfaz 
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Nombre de usuario"));
        add(usernameField);
        add(new JLabel("Contraseña"));
        add(passwordField);
        add(loginButton);

        pack();
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

       
        if ("admin".equals(username) && "1234".equals(password)) {
            // Si el login es exitoso, generamos el token
            String token = JWTUtil.generateToken(username);
            JOptionPane.showMessageDialog(this, "Login exitoso! Token: " + token);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
