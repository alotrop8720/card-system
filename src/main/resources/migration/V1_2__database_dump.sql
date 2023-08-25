INSERT INTO m_client(full_name, phone, email, status, username, secure_password)VALUES ('Пункевич Владислав Викторович', '+3754489654123', 'vlad@mail.ru', 'Classic', 'login1','$2a$10$AZlrT5LTcCik0TAkf8qfgeWuduk8UK32O1A/0Nxa5XRsv.yrAkFPq');
INSERT INTO m_client(full_name, phone, email, status, username, secure_password)VALUES ('Вилова Анна Антоновна', '+3754478541236', 'nasta@mail.ru', 'Visa', 'login2', '$2a$10$AZlrT5LTcCik0TAkf8qfgeWuduk8UK32O1A/0Nxa5XRsv.yrAkFPq');

INSERT INTO m_card(number, currency, type_card, id_client) VALUES ('1234123412341234', 'USD', 'CLASSIC', 1);
INSERT INTO m_card(number, currency, type_card, id_client) VALUES ('1234123412341233', 'BYN', 'CLASSIC', 2);
