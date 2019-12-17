package entity;

import exception.WarehouseException;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy {
    private String name;
    private List<Client> clients = new ArrayList<>();
    private List<Warehouse> warehouses = new ArrayList<>();

    public Pharmacy() {
    }

    public Pharmacy(String name) {
        this.name = name;
    }

    public Pharmacy(String name, List<Client> clients, List<Warehouse> warehouses) {
        this.name = name;
        this.clients = clients;
        this.warehouses = warehouses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void addWarehouse(Warehouse warehouse) throws WarehouseException {
        if (warehouses.size() < 2) {
            this.warehouses.add(warehouse);
        } else {
            throw new WarehouseException("Cannot add more than two warehouses.");
        }
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "name='" + name + '\'' +
                ", clients=" + clients.size() +
                ", warehouses=" + warehouses.size() +
                '}';
    }
}
