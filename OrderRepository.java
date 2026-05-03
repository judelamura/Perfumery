package com.perfumery.data.repositories;

import com.perfumery.data.database.CsvFileHandler;
import com.perfumery.domain.models.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private CsvFileHandler _fileHandler;
    private static final String HEADER = "id;userId;perfumeId;quantity";

    public OrderRepository(){
        String path = "data/orders.csv";

        _fileHandler = new CsvFileHandler(path, HEADER);
        _fileHandler.initializeFile();
    }

    public List<OrderModel> getAll(){
        return convertCsvToOrders();
    }

    public void save(OrderModel order){
        ArrayList<String[]> array = _fileHandler.read();
        array.add(order.toCSV());

        _fileHandler.write(array);
    }

    private ArrayList<OrderModel> convertCsvToOrders() {
        ArrayList<String[]> itens = _fileHandler.read();
        ArrayList<OrderModel> result = new ArrayList<>();

        for (String[] item : itens) {
            if (item.length < 4 || item[0].isBlank()) continue;

            OrderModel order = OrderModel.fromCSV(item);
            result.add(order);
        }

        return result;
    }
}
