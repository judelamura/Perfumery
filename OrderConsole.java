package com.perfumery.application.console;

import com.perfumery.domain.services.OrderService;
import com.perfumery.domain.models.OrderModel;
import com.perfumery.domain.models.UserModel;
import com.perfumery.utils.AppException;
import com.perfumery.utils.InputUtil;

public class OrderConsole {
    private final OrderService _service = new OrderService();
    
    public void menu(UserModel user){
        int option = -1;

        while (option != 3) {
            InputUtil.clearConsole();
            System.out.println("\n=== PEDIDOS ===");
            System.out.println("1 - Criar pedido");
            System.out.println("2 - Listar pedidos");
            System.out.println("3 - Voltar");
            System.out.print("Escolha: ");
            
            option = InputUtil.readInt();

            switch (option){
                case 1:
                    createOrder(user);
                    break;
                case 2:
                    listOrders(user);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }
    }

    private void createOrder(UserModel user){
        InputUtil.clearConsole();
        System.out.println("\n=== CRIAR PEDIDO ===");

        try{
            String perfumeId = InputUtil.readRequired("ID do perfume");
            int quantity;

            do {
                System.out.print("Quantidade: ");
                quantity = InputUtil.readInt();
                if (quantity <= 0) {
                    System.out.println("Quantidade inválida. Informe um número maior que zero.");
                }
            } while (quantity <= 0);

            OrderModel order = new OrderModel(user.getId(), perfumeId, quantity);
            _service.create(order);

            System.out.println("Pedido criado com sucesso!");
        } catch (AppException e){
            System.out.println("Erro: " + e.getMessage());
        }
     }

    private void listOrders(UserModel user){
        InputUtil.clearConsole();
        System.out.println("\n=== MEUS PEDIDOS ===");

        var orders = _service.getAll();
        boolean hasOrders = false;

        for (OrderModel order : orders) {
            if (!order.getUserId().equals(user.getId())) {
                continue;
            }
            hasOrders = true;
            System.out.println("ID do pedido: " + order.getId());
            System.out.println("Perfume: " + order.getPerfumeId());
            System.out.println("Quantidade: " + order.getQuantity());
            System.out.println("-----------------------");
        }

        if (!hasOrders) {
            System.out.println("Nenhum pedido encontrado para o usuário atual.");
        }
    }
}

