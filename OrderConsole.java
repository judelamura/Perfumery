package com.perfumery.application.console;

import com.perfumery.domain.services.OrderService;
import com.perfumery.domain.models.OrderModel;
import com.perfumery.utils.AppException;
import com.perfumery.utils.InputUtil;

public class OrderConsole {
    private final OrderService _service = new OrderService();
    
    public void menu(){
        int option = -1;

        while (option != 3) {
            InputUtil.clearConsole();
            System.out.println("\n=== PEDIDOS ===");
            System.out.println("1 - Criar pedido");
            System.out.println("2 - Listar pedidos");
            System.out.println("3 - Voltar");
            System.out.println("Escolha: ");
            
            option = InputUtil.readInt();

            switch (option){
                case 1:
                    createOrder();
                    break;
                case 2:
                    listOrders();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opção Inválida!");
                }
        }
    }

    private void createOrder(){
        InputUtil.clearConsole();
        System.out.println("\n=== CRIAR PEDIDO ===");

        try{
            String userId = InputUtil.readRequired("ID do usuário");
            String perfumeId = InputUtil.readRequired("ID do perfume");
            int quantity = InputUtil.readInt();

             OrderModel order = new OrderModel(userId, perfumeId, quantity);

            _service.create(order);

            System.out.println("Pedido criado com sucesso!");
        } catch (AppException e){
            System.out.println("Erro: " + e.getMessage());
        }
     }

        private void listOrders(){
            InputUtil.clearConsole();
            System.out.println("\n=== LISTA DE PEDIDOS ===");

            var orders = _service.getAll();

            for (OrderModel order : orders) {
                System.out.println("Usuário: " + order.getUserId());
                System.out.println("Perfume: " + order.getPerfumeId());
                System.out.println("Quantidade: " + order.getQuantity());
                System.out.println("-----------------------");
            }
            //InputUtil.pause();
        }
    }

