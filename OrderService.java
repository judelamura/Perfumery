package com.perfumery.domain.services;

import java.util.ArrayList;
import java.util.List;

import com.perfumery.domain.models.OrderModel;

public class OrderService {
    private List<OrderModel> orders = new ArrayList<>();

    public void create(OrderModel order){
        orders.add(order);
    }

    public List<OrderModel>getAll(){
        return orders;
    }
}
