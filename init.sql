create schema my_schema

    create table my_schema.applications
    (
        id            bigserial,
        name          varchar(30)  not null,
        description   varchar(100) not null,
        creation_data date         not null,
        status        varchar(30)  not null check (status in ('NOT_STARTED', 'IN_PROGRESS', 'COMPLETE')),
        primary key (id)
    );

    create table my_schema.execution_times
    (
        id             bigserial,
        method_name    varchar(50) not null,
        execution_time bigint      not null,
        primary key (id)
    );