package com.mycompany.models.Views;

import java.util.Scanner;

public class CajeroView <T> {
 private T contenido;
    private Scanner scanner;

    // Constructor para inicializar un scanner
    public CajeroView() {
        scanner = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        System.out.println("-------------------------------------------");
        System.out.println("Bienvenido al cajero automático del banco");
        System.out.println("-------------------------------------------");
    }

    public String solicitarNumeroCuenta() {
        System.out.print("Ingresa tu número de cuenta: ");
        return scanner.nextLine();
    }

    public String solicitarPin() {
        System.out.print("Ingresa tu PIN: ");
        return scanner.nextLine();
    }

   public void mostrarMenuPrincipal(T titular) {
        System.out.println("-------------------------------------------");
        System.out.println("         Bienvenid@ " + titular);
        System.out.println("-------------------------------------------");
        System.out.println("1.- Consultar saldo");
        System.out.println("2.- Retirar");
        System.out.println("3.- Depositar");
        System.out.println("4.- Transferir");
        System.out.println("5.- Cambiar PIN");
        System.out.println("9.- Salir");
        System.out.println("-------------------------------------------");
        System.out.print("Selecciona una opción: ");
    }

    // Retornar la opción
    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // opción inválida
        }
    }

   public void mostrarSaldo(T saldo) {
        System.out.println("-------------------------------------------");
        System.out.println("     Tu saldo actual es de: $" + saldo);
        System.out.println("-------------------------------------------");
    }

    public double solicitarCantidad(String operacion) {
        System.out.print("Ingresa la cantidad a " + operacion + ": ");
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String solicitarCuentaDestino() {
        System.out.print("Ingresa el número de cuenta destino: ");
        return scanner.nextLine();
    }

    public String solicitarNuevoPin() {
        System.out.print("Ingresa tu nuevo PIN: ");
        return scanner.nextLine();
    }

    public String solicitarPinActual() {
        System.out.print("Ingresa tu PIN actual: ");
        return scanner.nextLine();
    }

    // Mensajes personalizados
   
 public void mostrarMensaje(T mensaje) {
        System.out.println("" + mensaje);
    }
   

    // Método para salir
    public void cerrar() {
        System.out.println("-------------------------------------------");
        System.out.println("Gracias por usar el cajero automático. ¡Hasta pronto!");
        System.out.println("-------------------------------------------");
        scanner.close();
    }

    
    
}
