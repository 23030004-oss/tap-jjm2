
import com.mycompany.models.cajeroModel;
import com.mycompany.models.Views.CajeroView;
import com.mycompany.models.Views.CajeroView;

public class CajeroControllers {

    private cajeroModel model;
    private CajeroView view;
    private boolean sistemaActivo;

    public CajeroControllers(cajeroModel model, CajeroView view) {
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
    }

    public void iniciarSistema() {
        view.mostrarBienvenida();
        while (sistemaActivo) {
            if (autenticarUsuario()) {
                ejecutarMenuPrincipal();
            } else {
                view.mostrarMensaje("Credenciales incorrectas");
            }
        }
        view.mostrarMensaje("Gracias por usar nuestro cajero, esperamos tu regreso");
    }

    private boolean autenticarUsuario() {
        String numeroCuenta = view.solicitarNumeroCuenta();
        String pin = view.solicitarPin();
        return model.autenticar(numeroCuenta, pin);
    }

    private void ejecutarMenuPrincipal() {
        boolean sessionActiva = true;
        while (sessionActiva) {
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
            int opcion = view.leerOpcion();
            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    retirarSaldo();
                    break;
                case 3:
                    realizarDeposito();
                    break;
                case 4:
                    realizarTransferencia();
                    break;
                case 5:
                    cambiarNip();
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:
                    view.mostrarMensaje("Cerrando sesión...");
                    sessionActiva = false; // salir del bucle
                    break;
                default:
                    view.mostrarMensaje("Opción no válida, intenta de nuevo.");
            }
        }
    }

    public void consultarSaldo() {
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }

    public void retirarSaldo() {
        double cantidad = view.solicitarCantidad("retirar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad");
            return;
        }
        if (model.realizarRetiro(cantidad)) {
            view.mostrarMensaje("retiro exitoso de " + cantidad);

        } else {
            view.mostrarMensaje("Fondos insuficientes");
        }
    }

    public void realizarDeposito() {
        double cantidad = view.solicitarCantidad("deposito");
        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad ");
            return;
        }
        if (model.realizarRetiro(cantidad)) {
            view.mostrarMensaje("Deposito exitoso" + cantidad);

        } else {
            view.mostrarMensaje("fondos insuficientes");
        }
    }
    //////////////////////////////////////////////////////////////////////

    public void realizarTransferencia() {
        String cuentaDestino = view.solicitarCuentaDestino();
        double cantidad = view.solicitarCantidad("transferir");

        if (cantidad <= 0) {
            view.mostrarMensaje("Cantidad inválida");
            return;
        }

        if (model.realizarTransferencia(cuentaDestino, cantidad)) {
            view.mostrarMensaje("Transferencia exitosa de " + cantidad + " a la cuenta " + cuentaDestino);
        } else {
            view.mostrarMensaje("No se pudo realizar la transferencia. Verifica saldo o cuenta destino.");
        }
    }

    // 🔹 Nueva opción: Cambio de NIP
    public void cambiarNip() {
        String nipActual = view.solicitarPinActual();
        String nuevoNip = view.solicitarNuevoPin();

        if (model.cambiarNip(nipActual, nuevoNip)) {
            view.mostrarMensaje("NIP cambiado exitosamente.");
        } else {
            view.mostrarMensaje("Error al cambiar el NIP. Verifica que el actual sea correcto.");
        }
    }
}
