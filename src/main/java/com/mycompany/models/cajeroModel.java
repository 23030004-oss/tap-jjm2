package com.mycompany.models;

import java.util.HashMap;
import java.util.Map;

public class cajeroModel {

    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;

    public cajeroModel() {
        cuentas = new HashMap<>();
        inicializarCuentas();
    }

    private void inicializarCuentas() {
        cuentas.put("12345", new Cuenta("12345", "1111", 5000, "Joaquin"));
        cuentas.put("56789", new Cuenta("56789", "6789", 7000, "Adrian"));
        cuentas.put("54321", new Cuenta("54321", "2412", 8600, "Ambar"));
    }

    public boolean autenticar(String numeroCuenta, String pin) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null && cuenta.validarPin(pin)) {
            this.cuentaActual = cuenta;
            return true;
        }
        return false;
    }

    public Cuenta getCuentaActual() {
        return this.cuentaActual;
    }

    public double consultarSaldo() {
        return this.cuentaActual != null ? cuentaActual.getSaldo() : 0;
    }

    public boolean realizarRetiro(double cantidad) {
        return this.cuentaActual != null && cuentaActual.retirar(cantidad);
    }

    public boolean realizarDeposito(double cantidad) {
        if (cuentaActual != null && cantidad > 0) {
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }

    public boolean cuentaExistente(String numeroCuenta) {
        return cuentas.containsKey(numeroCuenta);
    }

    // --- Nuevo método: transferencias ---
    public boolean realizarTransferencia(String numeroDestino, double cantidad) {
        if (cuentaActual != null && cuentaExistente(numeroDestino)) {
            Cuenta destino = cuentas.get(numeroDestino);
            if (cuentaActual.transferir(destino, cantidad)) {
                return true;
            }
        }
        return false;
    }

    public boolean cambiarNip(String nipActual, String nuevoNip) {
        if (cuentaActual != null) {
            return cuentaActual.cambiarNip(nipActual, nuevoNip);
        }
        return false;
    }
}
