-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3334
-- Время создания: Апр 26 2021 г., 19:12
-- Версия сервера: 10.3.13-MariaDB
-- Версия PHP: 7.1.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `cinema`
--

-- --------------------------------------------------------

--
-- Структура таблицы `movies`
--

CREATE TABLE `movies` (
  `id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `premiere` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `movies`
--

INSERT INTO `movies` (`id`, `name`, `premiere`) VALUES
(1, 'Кот Гром', '2021-05-16 10:00:00'),
(2, 'Кот Леопольд', '2021-05-17 10:00:00'),
(11, 'Гарфилд', '2021-05-16 10:00:00'),
(15, 'Кошки против собак', '2021-05-16 10:00:00');

-- --------------------------------------------------------

--
-- Структура таблицы `tickets`
--

CREATE TABLE `tickets` (
  `id` int(255) NOT NULL,
  `id_user` int(255) DEFAULT NULL,
  `id_movie` int(255) NOT NULL,
  `place` int(255) NOT NULL,
  `price` float(255,3) NOT NULL,
  `exist` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tickets`
--

INSERT INTO `tickets` (`id`, `id_user`, `id_movie`, `place`, `price`, `exist`) VALUES
(1, NULL, 1, 1, 3.000, 1),
(2, NULL, 1, 2, 3.000, 1),
(3, NULL, 1, 3, 3.000, 1),
(4, NULL, 1, 4, 3.000, 1),
(5, NULL, 2, 1, 4.200, 1),
(6, NULL, 2, 2, 4.200, 1),
(7, NULL, 2, 3, 4.200, 1),
(8, NULL, 2, 4, 4.200, 1),
(47, 3, 11, 1, 5.200, 0),
(48, 3, 11, 2, 5.200, 0),
(49, NULL, 11, 3, 5.200, 1),
(50, NULL, 11, 4, 5.200, 1),
(51, 3, 11, 5, 5.200, 0),
(68, NULL, 15, 1, 4.600, 1),
(69, NULL, 15, 2, 4.600, 1),
(70, NULL, 15, 3, 4.600, 1),
(71, NULL, 15, 4, 4.600, 1),
(72, NULL, 15, 5, 4.600, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `role`) VALUES
(1, 'adam', '202cb962ac59075b964b07152d234b70', 'admin'),
(2, 'manager', '202cb962ac59075b964b07152d234b70', 'manager'),
(3, 'qwe', '202CB962AC59075B964B07152D234B70', 'simple'),
(12, 'login', '5F4DCC3B5AA765D61D8327DEB882CF99', 'simple');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_movie` (`id_movie`),
  ADD KEY `id_user` (`id_user`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT для таблицы `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`id_movie`) REFERENCES `movies` (`id`),
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
