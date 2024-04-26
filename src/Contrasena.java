import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contrasena {
    public static void main(String[] args) {
        String[] contraseñas = {"Contraseña1", "CArtas6!", "PEnhou2!", "PAtata4#", "456fv7$#",};

        // Crear un pool de hilos con 5 hilos
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Para cada contraseña, lanzar un hilo para validarla
        for (String contraseña : contraseñas) {
            executor.execute(new ValidadorContraseña(contraseña));
        }

        // Apagar el pool de hilos después de que se completen todas las tareas
        executor.shutdown();
    }
}

class ValidadorContraseña implements Runnable {
    private final String contraseña;
    private static final Pattern PATRON = Pattern.compile("^(?=.*[a-z].*[a-z].*[a-z])(?=.*[A-Z].*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");

    public ValidadorContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public void run() {
        System.out.println("Validando contraseña: " + contraseña);
        if (validarContraseña()) {
            System.out.println("La contraseña '" + contraseña + "' es válida.\n");
        } else {
            System.out.println("La contraseña '" + contraseña + "' no cumple con los requisitos.\n");
        }
    }

    private boolean validarContraseña() {
        Matcher matcher = PATRON.matcher(contraseña);
        return matcher.matches();
    }
}