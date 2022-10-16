package by.itacademy.menu;

import by.itacademy.exceptions.NullTicketException;
import by.itacademy.model.Movie;
import by.itacademy.model.Ticket;
import by.itacademy.model.User;
import by.itacademy.servese.SimpleUserService;
import by.itacademy.servese.SimpleUserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class SimpleUserController {
    private static SimpleUserService simpleUserService = new SimpleUserServiceImpl();
    private String input;

    public void run(User user) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите 1 что бы зайти в меню сеансов");
                System.out.println("Введите 2 что бы зайти в меню редактирования аккаутна");
                System.out.println("Ввведите 0 что бы выйти из аккаунта");
                input = scanner.nextLine();
                chekInput(input, 3);
                if ("1".equals(input)){
                    while (true){
                        System.out.println("Введите 1 что бы посмотреть фильмы");
                        System.out.println("Введите 2 что бы купить билет");
                        System.out.println("Введите 3 что бы вернуть билет");
                        System.out.println("Введите 4 что бы просмотреть мои билеты");
                        System.out.println("Введите 0 что бы выйти из меню");
                        input = scanner.nextLine();
                        chekInput(input, 5);
                        if ("1".equals(input)) {
                            List<Movie> movies = simpleUserService.viewMovies(user);
                            for (int i = 0; i < movies.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + movies.get(i).getName()
                                        + movies.get(i).getPlaceAndPrice()
                                        + ", Премьера: " + movies.get(i).getDate());
                            }
                            continue;
                        }
                        if ("2".equals(input)) {
                            List<Movie> movies = simpleUserService.viewMovies(user);
                            for (int i = 0; i < movies.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + movies.get(i).getName()
                                        + movies.get(i).getPlaceAndPrice()
                                        + ", Премьера: " + movies.get(i).getDate());
                            }
                            System.out.println("Ведите номер фильма");
                            input = scanner.nextLine();
                            chekInput(input, movies.size());
                            System.out.println("введите место");
                            Ticket ticket = new Ticket(user, Integer.parseInt(scanner.nextLine()),
                                    movies.get(Integer.parseInt(input) - 1));
                            if (!simpleUserService.buyTicket(user, ticket))
                                throw new NullTicketException("билет не куплен");
                            continue;
                        }
                        if ("3".equals(input)) {
                            List<Ticket> tickets = simpleUserService.viewMyTickets(user);
                            for (int i = 0; i < tickets.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + tickets.get(i).getMovie().getName()
                                        + ", Место: " + tickets.get(i).getPlace()
                                        + ", Цена: " + tickets.get(i).getPrice()
                                        + ", Премьера: " + tickets.get(i).getMovie().getDate());
                            }
                            System.out.println("Ведите номер фильма");
                            input = scanner.nextLine();
                            chekInput(input, tickets.size());
                            if (!simpleUserService.returnTicket(user, tickets.get(Integer.parseInt(input) - 1)))
                                throw new NullTicketException("билет не приняли");
                            continue;
                        }
                        if ("4".equals(input)) {
                            List<Ticket> tickets = simpleUserService.viewMyTickets(user);
                            for (int i = 0; i < tickets.size(); i++) {
                                System.out.println((i + 1) + ") " +
                                        "Название фильма: " + tickets.get(i).getMovie().getName()
                                        + ", Место: " + tickets.get(i).getPlace()
                                        + ", Цена: " + tickets.get(i).getPrice()
                                        + ", Премьера: " + tickets.get(i).getMovie().getDate());
                            }
                            continue;
                        }
                        if ("0".equals(input)) {
                            input = null;
                            break;
                        }
                    }
                }
                if ("2".equals(input)){
                    while (true){
                        System.out.println("Введите 1 что бы обновить логин");
                        System.out.println("Введите 2 что бы обновить пароль");
                        System.out.println("Введите 3 что бы удалить аккаунт");
                        System.out.println("Введите 0 что бы выйти из меню");
                        input = scanner.nextLine();
                        chekInput(input, 4);
                        if ("1".equals(input)) {
                            System.out.println("Ввведите новый логин");
                            input = scanner.nextLine();
                            simpleUserService.updateLogin(user, input);
                            continue;
                        }
                        if ("2".equals(input)) {
                            System.out.println("Ввведите новый пароль");
                            input = scanner.nextLine();
                            simpleUserService.updatePassword(user, input);
                            continue;
                        }
                        if ("3".equals(input)) {
                            System.out.println("вы уверены что хотите удалить аккаунт?");
                            System.out.println("1-да   2-нет");
                            input = new Scanner(System.in).nextLine();
                            chekInput(input, 2);
                            if ("1".equals(input)) {
                                simpleUserService.delete(user);
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
