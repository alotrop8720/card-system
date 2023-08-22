INSERT INTO m_client(id, name, phone, email, status) VALUES (1, 'Punkevich Vladislav Viktorovich', '+3754489654123', 'vlad@mail.ru', 'Classic');
INSERT INTO m_client(id, name, phone, email, status) VALUES (2, 'Vilova Anastasia Antonodyf', '+3754478541236', 'nasta@mail.ru', 'Visa');

INSERT INTO m_card(id, number, currency, type_card, id_client) VALUES (1, '1234123412341234', 'USD', 'Classic', 1);
INSERT INTO m_card(id, number, currency, type_card, id_client) VALUES (2, '1234123412341233', 'BYN', 'Visa', 2);
