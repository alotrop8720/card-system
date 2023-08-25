create table m_client
(
    id     bigserial not null
        constraint m_client_pk
            primary key,
    full_name   varchar   not null,
    phone  varchar,
    email  varchar,
    status varchar
);

alter table m_client
    owner to postgres;

create table m_card
(
    id        bigserial                                    not null
        constraint m_card_pk
            primary key,
    number    varchar                                      not null,
    currency  varchar default 'BYN'::character varying     not null,
    type_card varchar default 'Classic'::character varying not null,
    id_client bigserial                                    not null
        constraint m_card_by_client_fk
            references m_client
            on update cascade on delete cascade
);

alter table m_card
    owner to postgres;

