package by.itacademy.menu;

import by.itacademy.constant.Constant;
import by.itacademy.model.Movie;
import by.itacademy.model.Ticket;
import by.itacademy.model.User;
import by.itacademy.servese.ManagerService;
import by.itacademy.servese.ManagerServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ManagerController {
    public void run(User user) {
        String input;
        ManagerService managerService = new ManagerServiceImpl();
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите 1 что бы зайти в меню фильмов");
                System.out.println("Введите 2 что бы зайти в меню билетов");
                System.out.println("Введите 3 что бы зайти в меню редактирования аккаутна");
                System.out.println("Ввведите 0 что бы выйти из аккаунта");
                input = scanner.nextLine();
                chekInput(input, 4);
                if ("1".equals(input)) {
                    while (true) {
                        System.out.println("Введите 1 что бы посмотреть фильмы");
                        System.out.println("Введите 2 что бы создать фильм");
                        System.out.println("Введите 3 что бы редактировать фильм");
                        System.out.println("Введите 0 что бы выйти из меню");
                        input = scanner.nextLine();
                        chekInput(input, 4);
                        if ("1".equals(input)) {
                            List<Movie> movies = managerService.viewMovies(user);
                            for (int i = 0; i < movies.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + movies.get(i).getName()
                                        + movies.get(i).getPlaceAndPrice()
                                        + ", Премьера: " + movies.get(i).getDate());
                            }
                            continue;
                        }
                        if ("2".equals(input)) {
                            String name, date, numberOfTickets, priceOfTicket;
                            System.out.println("введите название фильма");
                            name = scanner.nextLine();
                            System.out.println("введите дату как yyyy-MM-dd HH:mm");
                            date = scanner.nextLine();
                            Movie movie = new Movie(name, date + ":00.0");
                            System.out.println("введите цену одного билета");
                            priceOfTicket = scanner.nextLine();
                            System.out.println("введите количество билетов");
                            numberOfTickets = scanner.nextLine();
                            managerService.createMovie
                                    (user, movie, Integer.parseInt(numberOfTickets), Float.parseFloat(priceOfTicket));
                            continue;
                        }
                        if ("3".equals(input)) {
                            List<Movie> movies = managerService.viewMovies(user);
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
                                managerService.updateMovie(user, movie, new Movie(name, movie.getDate()));
                            }
                            if ("2".equals(input)) {
                                System.out.println("введите новую дату как yyyy-MM-dd HH:mm");
                                date = scanner.nextLine();
                                managerService.updateMovie(user, movie, new Movie(movie.getName(), date + ":00.0"));
                            }
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
                        System.out.println("Введите 1 что бы просмотреть билеты");
                        System.out.println("Введите 2 что бы купить билет");
                        System.out.println("Введите 3 что бы вернуть билет");
                        System.out.println("Введите 0 что бы выйти из меню");
                        input = scanner.nextLine();
                        chekInput(input, 4);
                        if ("1".equals(input)) {
                            List<Ticket> tickets = managerService.viewTickets(user, Constant.VIEW_TICKETS);
                            for (int i = 0; i < tickets.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + tickets.get(i).getMovie().getName()
                                        + ", Место: " + tickets.get(i).getPlace()
                                        + ", Цена: " + tickets.get(i).getPrice()
                                        + ", Имя пользователя: " + tickets.get(i).getUser().getLogin()
                                        + ", Премьера: " + tickets.get(i).getMovie().getDate());

                            }
                            continue;
                        }
                        if ("2".equals(input)) {
                            List<Ticket> tickets = managerService.viewTickets(user, Constant.VIEW_NOT_ORDERED);
                            for (int i = 0; i < tickets.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + tickets.get(i).getMovie().getName()
                                        + ", Место: " + tickets.get(i).getPlace()
                                        + ", Цена: " + tickets.get(i).getPrice()
                                        + ", Премьера: " + tickets.get(i).getMovie().getDate());
                            }
                            System.out.println("Выберете номер билета");
                            input = scanner.nextLine();
                            chekInput(input, tickets.size());
                            System.out.println("Введите логин пользователя");
                            User buyer = new User(scanner.nextLine());
                            if (!"simple".equals(buyer.getRole())) {
                                System.err.println("этому пользователю нельзя покупать билеты");
                                continue;
                            }
                            tickets.get(Integer.parseInt(input) - 1).setUser(buyer);
                            managerService.buyTicket(user, tickets.get(Integer.parseInt(input) - 1));
                            continue;
                        }
                        if ("3".equals(input)) {
                            List<Ticket> tickets = managerService.viewTickets(user, Constant.VIEW_ORDERED);
                            for (int i = 0; i < tickets.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + tickets.get(i).getMovie().getName()
                                        + ", Место: " + tickets.get(i).getPlace()
                                        + ", Цена: " + tickets.get(i).getPrice()
                                        + ", Имя пользователя: " + tickets.get(i).getUser().getLogin()
                                        + ", Премьера: " + tickets.get(i).getMovie().getDate());
                            }
                            System.out.println("Выберете номер билета");
                            input = scanner.nextLine();
                            chekInput(input, tickets.size());
                            managerService.returnTicket(user, tickets.get(Integer.parseInt(input) - 1));
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
                        System.out.println("Введите 3 что бы удалить аккаунт");
                        System.out.println("Введите 0 что бы выйти из меню");
                        input = scanner.nextLine();
                        chekInput(input, 4);
                        if ("1".equals(input)) {
                            System.out.println("Ввведите новый логин");
                            input = scanner.nextLine();
                            managerService.updateLogin(user, input);
                            continue;
                        }
                        if ("2".equals(input)) {
                            System.out.println("Ввведите новый пароль");
                            input = scanner.nextLine();
                            managerService.updatePassword(user, input);
                            continue;
                        }
                        if ("3".equals(input)) {
                            System.out.println("вы уверены что хотите удалить аккаунт?");
                            System.out.println("1-да   2-нет");
                            input = new Scanner(System.in).nextLine();
                            chekInput(input, 2);
                            if ("1".equals(input)) {
                                managerService.delete(user);
                                break;
                            }
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
