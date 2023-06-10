create table public.m_expert_department
(
    expert_department_id bigserial                           not null
        constraint m_expert_department_pkey
            primary key,
    name                 text,
    description          text,
    created              timestamp default CURRENT_TIMESTAMP not null,
    changed              timestamp default CURRENT_TIMESTAMP not null,
    status               varchar   default 'ENABLE':: character varying not null
);

comment
on table public.m_expert_department is 'The table for departmnets of SFEC (State Forensic Examination Committee).
Catalog, but can be modified
';

alter table public.m_expert_department
    owner to postgres;

create table public.m_expert_role
(
    expert_role_id bigserial                              not null
        constraint m_expert_role_pkey
            primary key,
    role_name      varchar(100),
    created        timestamp    default CURRENT_TIMESTAMP not null,
    changed        timestamp    default CURRENT_TIMESTAMP not null,
    status         varchar(100) default 'ENABLE':: character varying not null
);

comment
on table public.m_expert_role is 'The table for expert roles (used for security)';

alter table public.m_expert_role
    owner to postgres;

create table public.m_expert
(
    expert_id            bigserial                              not null
        constraint m_expert_pkey
            primary key,
    username             varchar(100),
    password             varchar(200),
    first_name           varchar(100),
    second_name          varchar(100),
    last_name            varchar(100),
    created              timestamp    default CURRENT_TIMESTAMP not null,
    changed              timestamp    default CURRENT_TIMESTAMP not null,
    expert_role_id       bigint
        constraint role_id
            references public.m_expert_role,
    expert_department_id bigint
        constraint department_id
            references public.m_expert_department,
    status               varchar(100) default 'ENABLE':: character varying,
    email                varchar(100)
);

comment
on table public.m_expert is 'The table for expert description.
Include the expert department and expert status';

comment
on constraint role_id on public.m_expert is 'Reference on m_status_expert table';

comment
on constraint department_id on public.m_expert is 'Reference on m_expert_department table';

alter table public.m_expert
    owner to postgres;

create
unique index m_expert_email_uindex
    on public.m_expert (email);

create
unique index m_expert_username_index
    on public.m_expert (username);

create table public.m_crime
(
    crime_id              bigserial                              not null
        constraint m_crime_pkey
            primary key,
    police_reg_number     bigint,
    case_number           bigint,
    description           varchar(200)                           not null,
    criminal_code_article bigint                                 not null,
    crime_date            timestamp,
    created               timestamp    default CURRENT_TIMESTAMP not null,
    changed               timestamp    default CURRENT_TIMESTAMP not null,
    status                varchar(100) default 'ENABLE':: character varying,
    crime_status          varchar(100)
);

alter table public.m_crime
    owner to postgres;

create table public.m_address
(
    address_id        bigserial                              not null
        constraint m_adress_pkey
            primary key,
    region            varchar(100)                           not null,
    district          varchar(100)                           not null,
    locality          varchar(100),
    building          varchar(100),
    corps             varchar(100),
    apartment         varchar(100),
    comment           varchar(100),
    police_department varchar(100)                           not null,
    created           timestamp    default CURRENT_TIMESTAMP not null,
    changed           timestamp    default CURRENT_TIMESTAMP not null,
    status            varchar(100) default 'enable':: character varying not null,
    crime_id          bigint
        constraint m_address_m_crime_crime_id_fk
            references public.m_crime
            on update set null on delete set null,
    street            varchar(100)
);

comment
on table public.m_address is 'The table for adresses of crimes.
';

alter table public.m_address
    owner to postgres;

create table public.m_image
(
    image_id       bigserial                  not null
        constraint m_image_pkey
            primary key,
    image_name     uuid                       not null
        constraint image_name
            unique,
    created        timestamp    default CURRENT_TIMESTAMP,
    changed        timestamp    default CURRENT_TIMESTAMP,
    status         varchar(100) default 'ENABLE':: character varying,
    contains_image boolean      default false not null
);

alter table public.m_image
    owner to postgres;

create table public.m_fingerprint
(
    fingerprint_id        bigserial not null
        constraint m_fingerprint_pkey
            primary key,
    reg_number            varchar(100),
    fingerprint_find_date timestamp,
    created               timestamp    default CURRENT_TIMESTAMP,
    changed               timestamp    default CURRENT_TIMESTAMP,
    serial_number         bigint,
    expert_id             bigint
        constraint expert_id
            references public.m_expert,
    crime_id              bigint
        constraint crime_id
            references public.m_crime,
    status                varchar(100) default 'ENABLE':: character varying,
    image_id              bigint
        constraint m_fingerprint_m_image_image_id_fk
            references public.m_image,
    constraint reg_number_and_fingerpint_number
        unique (reg_number, serial_number)
);

alter table public.m_fingerprint
    owner to postgres;

create
unique index m_fingerprint_image_id_uindex
    on public.m_fingerprint (image_id);

create table public.m_identity
(
    identity_id        bigserial not null
        constraint m_identity_pkey
            primary key,
    identity_date      timestamp,
    person_first_name  text,
    person_second_name text,
    person_last_name   text,
    person_birth_date  timestamp,
    comment            text,
    fingerprint_id     bigint
        constraint fingerprint_id
            references public.m_fingerprint,
    created            timestamp    default CURRENT_TIMESTAMP,
    changed            timestamp    default CURRENT_TIMESTAMP,
    status             varchar(100) default 'ENABLE':: character varying
);

alter table public.m_identity
    owner to postgres;

create
unique index m_identity_fingerprint_id_uindex
    on public.m_identity (fingerprint_id);