package by.itacademy.menu;

import by.itacademy.constant.Constant;
import by.itacademy.model.Movie;
import by.itacademy.model.User;
import by.itacademy.servese.AdminService;
import by.itacademy.servese.AdminServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AdminController {
    public void run(User user) {
        String input;
        AdminService adminService = new AdminServiceImpl();
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите 1 что бы зайти в меню фильмов");
                System.out.println("Введите 2 что бы зайти в меню пользователей");
                System.out.println("Введите 3 что бы зайти в меню редактирования аккаутна");
                System.out.println("Ввведите 0 что бы выйти из аккаунта");
                input = scanner.nextLine();
                chekInput(input, 4);
                if ("1".equals(input)) {
                    while (true) {
                        System.out.println("Введите 1 что бы посмотреть фильмы");
                        System.out.println("Введите 2 что бы редактировать фильм");
                        System.out.println("Введите 3 что бы удалить фильм");
                        System.out.println("Ввведите 0 что бы выйти из меню");
                        input = scanner.nextLine();
                        chekInput(input, 4);
                        if ("1".equals(input)) {
                            List<Movie> movies = adminService.viewMovies(user);
                            for (int i = 0; i < movies.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + movies.get(i).getName()
                                        + movies.get(i).getPlaceAndPrice()
                                        + ", Премьера: " + movies.get(i).getDate());
                            }
                            continue;
                        }
                        if ("2".equals(input)) {
                            List<Movie> movies = adminService.viewMovies(user);
                            for (int i = 0; i < movies.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + movies.get(i).getName()
                                        + movies.get(i).getPlaceAndPrice()
                                        + ", Премьера: " + movies.get(i).getDate());
                            }
                            String name, date;
                            System.out.println("Ведите номер фильма");
                            input = scanner.nextLine();
                            chekInput(input, movies.size());
                            Movie movie = movies.get(Integer.parseInt(input) - 1);
                            System.out.println("1-Изменить название фильма");
                            System.out.println("2-Изменить премьеру фильма");
                            input = scanner.nextLine();
                            if ("1".equals(input)) {
                                System.out.println("введите новое название фильма");
                                name = scanner.nextLine();
                                adminService.updateMovie(user, movie, new Movie(name, movie.getDate()));
                            }
                            if ("2".equals(input)) {
                                System.out.println("введите новую дату как yyyy-MM-dd HH:mm");
                                date = scanner.nextLine();
                                adminService.updateMovie(user, movie, new Movie(movie.getName(), date + ":00.0"));
                            }
                            continue;
                        }
                        if ("3".equals(input)) {
                            List<Movie> movies = adminService.viewMovies(user);
                            for (int i = 0; i < movies.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + movies.get(i).getName()
                                        + movies.get(i).getPlaceAndPrice()
                                        + ", Премьера: " + movies.get(i).getDate());
                            }
                            System.out.println("Ведите номер фильма");
                            input = scanner.nextLine();
                            chekInput(input, movies.size());
                            adminService.deleteMovie(user, movies.get(Integer.parseInt(input) - 1));
                            continue;
                        }
                        if ("0".equals(input)) {
                            input = null;
                            break;
                        }
                    }
                }
                if ("2".equals(input)) {
                    while (true) {
                        System.out.println("Введите 1 что бы посмотреть пользователей");
                        System.out.println("Введите 2 что бы обновить пользователя");
                        System.out.println("Введите 3 что бы удалить пользователя");
                        System.out.println("Ввведите 0 что бы выйти из меню");
                        input = scanner.nextLine();
                        chekInput(input, 4);
                        if ("1".equals(input)) {
                            List<User> users = adminService.viewUser(user, Constant.VIEW_USERS);
                            for (int i = 0; i < users.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Логин " + users.get(i).getLogin()
                                        + " Роль " + users.get(i).getRole());
                            }
                            continue;
                        }
                        if ("2".equals(input)) {
                            List<User> users = adminService.viewUser(user, Constant.VIEW_NOT_ADMIN_USERS);
                            for (int i = 0; i < users.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Логин " + users.get(i).getLogin()
                                        + " Роль " + users.get(i).getRole());

                            }
                            String newUser;
                            System.out.println("Выберете номер пользователя");
                            input = scanner.nextLine();
                            chekInput(input, users.size());
                            User userForUpdate = users.get(Integer.parseInt(input) - 1);
                            System.out.println("0-Изменить логин");
                            System.out.println("1-Изменить пароль");
                            System.out.println("2-Изменить роль");
                            input = scanner.nextLine();
                            chekInput(input, 3);
                            if ("0".equals(input)) {
                                System.out.println("Введите новый логин");
                                adminService.updateLogin(userForUpdate, scanner.nextLine());
                            }
                            if ("1".equals(input)) {
                                System.out.println("Введите новый пароль");
                                adminService.updatePassword(userForUpdate, scanner.nextLine());
                            }
                            if ("2".equals(input)) {
                                System.out.println("Введите новую роль пользователя");
                                System.out.println("Введите 1 -  manager");
                                System.out.println("Введите 2 - simple");
                                newUser = scanner.nextLine();
                                chekInput(newUser, 2);
                                if ("1".equals(newUser))
                                    users.get(Integer.parseInt(input) - 1).setRole("manager");
                                if ("2".equals(newUser))
                                    users.get(Integer.parseInt(input) - 1).setRole("simple");
                                adminService.updateUser(user, users.get(Integer.parseInt(input) - 1));
                            }
                            input=null;
                            continue;
                        }
                        if ("3".equals(input)) {
                            List<User> users = adminService.viewUser(user, Constant.VIEW_NOT_ADMIN_USERS);
                            for (int i = 0; i < users.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Логин " + users.get(i).getLogin()
                                        + " Роль " + users.get(i).getRole());
                            }
                            System.out.println("Выберете номер пользователя");
                            input = scanner.nextLine();
                            chekInput(input, users.size());
                            adminService.deleteUser(user, users.get(Integer.parseInt(input) - 1));
                            continue;
                        }
                        if ("0".equals(input)) {
                            input = null;
                            break;
                        }
                    }
                }
                if ("3".equals(input)) {
                    while (true) {
                        System.out.println("Введите 1 что бы обновить логин");
                        System.out.println("Введите 2 что бы обновить пароль");
                        System.out.println("Ввведите 0 что бы выйти из меню");
                        input = scanner.nextLine();
                        chekInput(input, 3);
                        if ("1".equals(input)) {
                            System.out.println("Ввведите новый логин");
                            input = scanner.nextLine();
                            adminService.updateLogin(user, input);
                            continue;
                        }
                        if ("2".equals(input)) {
                            System.out.println("Ввведите новый пароль");
                            input = scanner.nextLine();
                            adminService.updatePassword(user, input);
                            continue;
                        }
                        if ("0".equals(input)) {
                            input = null;
                            break;
                        }
                    }
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
