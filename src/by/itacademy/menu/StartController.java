package by.itacademy.menu;

import by.itacademy.exceptions.NullUserException;
import by.itacademy.model.User;
import by.itacademy.servese.*;

import java.util.Scanner;

public class StartController {
    public static void start() {
        String input;
        String name;
        String password;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите 1 что бы зайти");
                System.out.println("Введите 2 что бы создать аккаунт");
                System.out.println("Ввведите 0 что бы выйти из программы");
                input = scanner.nextLine();
                chekInput(input, 2);
                if ("1".equals(input)) {
                    System.out.println("введите имя пользователя");
                    name = scanner.nextLine();
                    System.out.println("введите пароль пользователя");
                    password = scanner.nextLine();
                    User user = new User(name, password);
                    if ("simple".equals(user.getRole()))
                        new SimpleUserController().run(user);
                    if ("manager".equals(user.getRole()))
                        new ManagerController().run(user);
                    if ("admin".equals(user.getRole()))
                        new AdminController().run(user);
                }
                if ("2".equals(input)) {
                    System.out.println("введите имя пользователя");
                    name = scanner.nextLine();
                    System.out.println("введите пароль пользователя");
                    password = scanner.nextLine();
                    User user = new User(name, password, "simple");
                    if (!new SimpleUserServiceImpl().create(user))
                        throw new NullUserException("пользователь не создан");
                    new SimpleUserController().run(user);
                }
                if ("0".equals(input))
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void chekInput(String input, int illegal) {
        if (Integer.parseInt(input) > illegal || Integer.parseInt(input) < 0)
            throw new IllegalArgumentException("Ведено неверное число");
    }
}
