package com.perfumery.domain.models;

import java.util.UUID;

public class OrderModel {
    private String id;
    private String userId;
    private String perfumeId;
    private int quantity;
    //Outras propriedades que perfume deveria ter...

    public OrderModel(String userId, String perfumeId, int quantity){
    this.id = UUID.randomUUID().toString();
    this.userId = userId;
    this.perfumeId = perfumeId;
    this.quantity = quantity;
}
    public String getId(){
    return id;
    }
    public String getUserId(){
    return userId;
    }
    public String getPerfumeId(){
    return perfumeId;
    }
    public int getQuantity(){
    return quantity;
    }


    public void setPerfumeId(String perfumeId){
        this.perfumeId = perfumeId;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String[] toCSV(){
    return new String[]{
        id,
        userId,
        perfumeId,
        String.valueOf(quantity)
    };
}
    public static OrderModel fromCSV(String[] data){
        OrderModel order = new OrderModel(data[1], data[2], Integer.parseInt(data[3]));
        order.id = data[0];
        return order;
    }
}
