package com.perfumery.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import com.perfumery.domain.models.OrderModel;

public class OrderService {

    private List<OrderModel> orders = new ArrayList<>();

    public void create(OrderModel order){
        try {
            orders.add(order);
            saveToFile(order);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar pedido");
        }
    }

    public List<OrderModel> getAll(){
        return orders;
    }

    private void saveToFile(OrderModel order) throws IOException {
        FileWriter writer = new FileWriter("data/orders.csv", true);

        writer.write(
            order.getId() + "," +
            order.getUserId() + "," +
            order.getPerfumeId() + "," +
            order.getQuantity() + "\n"
        );

        writer.close();
    }
}
