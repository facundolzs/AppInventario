package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import sorting.AscendingOrder;
import sorting.DescendingOrder;

public class Inventory {

    private static Scanner readin = new Scanner(System.in);
    private static int stock, currentStock, option;
    private static String product;

    private Map<String, Integer> inventory = new HashMap<>(); // key (String) = product name. value (Integer) = stock
    private TreeMap<String, Integer> sortedInventory;

    /**
     * Ejecuta la app
     */
    public void appRun() {

        do {

            displayMenu();

            try {
                option = Integer.parseInt(readin.next());
                switch (option) {
                    case 1 -> {
                        addProduct();
                    }

                    case 2 -> {
                        stockIncrease();
                    }

                    case 3 -> {
                        stockDecrease();
                    }

                    case 4 -> {
                        displayInventory(inventory);
                    }

                    case 5 -> {
                        removeProduct();
                    }

                    case 6 -> {
                        sortedInventory = new TreeMap<>(new AscendingOrder());
                        sortedInventory.putAll(inventory);

                        displayInventory(sortedInventory);
                    }

                    case 7 -> {
                        sortedInventory = new TreeMap<>(new DescendingOrder());
                        sortedInventory.putAll(inventory);

                        displayInventory(sortedInventory);
                    }

                    case 8 ->
                        System.out.println("Fin del programa.");

                    default ->
                        System.out.println("El dato de entrada no corresponde a ninguna de las opciones\n");
                }

            } catch (NumberFormatException numberFormatException) {
                System.out.println("Debe ingresar un número entero como dato de entrada");
            }
            System.out.println();
        } while (option != 8);
    }

    /**
     * Muestra menu en pantalla
     */
    private void displayMenu() {

        System.out.println("""
                               \t::MENU DE OPCIONES::
                               <<Inventario>>
                                1. Agregar producto
                                2. Aumentar stock producto
                                3. Eliminar stock producto
                                4. Mostrar productos y stock
                                5. Eliminar producto
                                6. Ordenar productos ascendentemente (alfabeto)
                                7. Ordenar productos descendentemente (alfabeto)
                                8. Salir del programa
                                Seleccione una opción ingresando su número
                               """);
    }

    /**
     * Solicita y asigna nombre a un producto
     *
     * @return
     */
    private String requestProductName() {

        readin.nextLine();
        System.out.print("Nombre del producto: ");
        product = readin.nextLine().toLowerCase();

        product = (product == null) ? "Producto no especificado" : product;
        return product;
    }

    /**
     * Verifica si un determinado producto existe en el inventario
     *
     * @param product
     * @param inventory
     * @return
     */
    private boolean validateProduct(String product, Map inventory) {
        return inventory.containsKey(product);
    }

    /**
     * Agrega un nuevo producto al inventario
     *
     * @param inventory
     */
    private void addProduct() {

        product = requestProductName();

        if (!validateProduct(product, inventory)) {
            inventory.put(product, 0);
            System.out.println("Agregado exitosamente. Producto: " + product);
        } else {
            System.out.println("El producto " + product + " ya existe en el inventario.");
        }
    }

    /**
     * Incrementa stock de un producto
     *
     * @param inventory
     */
    private void stockIncrease() {

        product = requestProductName();

        if (validateProduct(product, inventory)) {
            System.out.print("Cantidad a agregar: ");
            stock = readin.nextInt();
            stock = (stock < 0) ? 0 : stock;
            currentStock = (int) inventory.get(product); // obtener stock actual para sumarlo al stock ingresado en la linea 61
            inventory.put(product, currentStock + stock);
            System.out.println("Producto: " + product + ". Stock agregado: " + stock);
        } else {
            nonexistentProduct();
        }
    }

    /**
     * Decrementa stock de un producto
     *
     * @param inventory
     */
    private void stockDecrease() {

        product = requestProductName();

        if (validateProduct(product, inventory)) {
            System.out.print("Cantidad a eliminar: ");
            stock = readin.nextInt();
            stock = (stock < 1) ? 0 : stock;
            if (stock > (int) inventory.get(product)) {
                System.out.println("La cantidad a eliminar supera al stock actual.");
            } else {
                currentStock = (int) inventory.get(product);
                inventory.put(product, currentStock - stock);
                System.out.println("Producto: " + product + ". Stock eliminado: " + stock);
            }
        } else {
            nonexistentProduct();
        }
    }

    /**
     * Elimina un producto del inventario
     *
     * @param inventory
     */
    private void removeProduct() {

        product = requestProductName();

        if (validateProduct(product, inventory)) {
            inventory.remove(product);
            System.out.println("Eliminado exitosamente. Producto: " + product);
        } else {
            nonexistentProduct();
        }
    }

    /**
     * Imprime inventario en pantalla
     *
     * @param inventory
     */
    private void displayInventory(Map<String, Integer> map) {

        System.out.println("<<<Productos en el inventario>>>");
        if (!map.isEmpty()) {
            map.forEach((k, v) -> System.out.println("Producto: " + k + " -> Stock: " + v));
        } else {
            System.out.println("El inventario se encuentra sin productos.");
        }
    }

    /**
     * Informa la inexistencia de un producto en el inventario
     */
    private void nonexistentProduct() {
        System.out.println("El producto no existe en el inventario.");
    }

}
