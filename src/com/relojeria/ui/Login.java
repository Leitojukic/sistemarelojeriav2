package com.relojeria.ui;

import com.relojeria.service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaz gráfica para el inicio de sesión. ysql> -- Insertar datos de prueba
mysql> INSERT INTO usuarios (usuario, contrasena, rol) VALUES
    -> ('admin', '1234', 'ADMIN'),
    -> ('user', '5678', 'USER'); >>>estos valores ya fueron insertados!!
 */
public class Login extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private AuthService authService;

    public Login() {
        authService = new AuthService();

        // Configuración de la ventana
        setTitle("Login - Relojería");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Usuario:"));
        usuarioField = new JTextField();
        panel.add(usuarioField);

        panel.add(new JLabel("Contraseña:"));
        contrasenaField = new JPasswordField();
        panel.add(contrasenaField);

        JButton loginButton = new JButton("Iniciar Sesión");
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar
            autenticar();
            }
        });

        // Agregamos el panel a la ventana
        add(panel);
    }

    /**
     * Método para autenticar al usuario con las credenciales ingresadas.
     */
    private void autenticar() {
        String usuario = usuarioField.getText();
        String contrasena = new String(contrasenaField.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String token = authService.autenticarUsuario(usuario, contrasena);

        if (token != null) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Lógica para abrir el siguiente formulario (ejemplo: MainFrame)
            new MainFrame().setVisible(true);
            this.dispose(); // Cerramos la ventana de login
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales inválidas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método principal para ejecutar la aplicación de login.
     *
     * @param args Argumentos del programa.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }
}
