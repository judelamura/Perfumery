package com.perfumery.application;

import com.perfumery.application.console.UserConsole;
import com.perfumery.domain.enums.UserRole;
import com.perfumery.domain.models.UserModel;
import com.perfumery.utils.InputUtil;
import com.perfumery.application.console.OrderConsole;

public class AppRouter {
    private final UserConsole userConsole = new UserConsole();
    private final OrderConsole orderConsole = new OrderConsole();

    public void start() {
        UserModel user = null;

        while (user == null) {
            InputUtil.clearConsole();
            user = userConsole.autenticar();
            if (user == null) {
                System.out.println("Até logo!");
                return;
            }
        }

        if (user.getRole() == UserRole.ADMIN) {
            showAdminMenu(user);
        } else {
            showUserMenu(user);
        }
    }

    private void showUserMenu(UserModel user) {
        int option = -1;
        while (option != 4) {
            InputUtil.clearConsole();
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Meus pedidos");
            System.out.println("3 - Atualizar meu perfil");
            System.out.println("4 - Deslogar");
            System.out.print("Escolha: ");
            option = InputUtil.readInt();

            switch (option) {
                //case 1:
                    //Listar produtos
                    //break;
                case 2: 
                    orderConsole.menu(user);
                    break;
                case 3:
                    userConsole.update(user);
                    break;
                case 4:
                    System.out.println("Até logo, " + user.getName() + "!");
                    start();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void showAdminMenu(UserModel user) {
        int option = -1;
        while (option != 4) {
            InputUtil.clearConsole();
            System.out.println("\n=== MENU ADMINISTRATIVO ===");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Atualizar meu perfil");
            System.out.println("3 - Criar novo perfil administrador");
            System.out.println("4 - Deslogar");
            System.out.print("Escolha: ");
            option = InputUtil.readInt();

            switch (option) {
                //case 1:
                    //Listar produtos
                    //break;
                case 2:
                    userConsole.update(user);
                    break;
                case 3:
                    userConsole.registerAdmin();
                    break;
                case 4:
                    System.out.println("Até logo, " + user.getName() + "!");
                    start();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
