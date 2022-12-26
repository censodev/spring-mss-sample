#! /bin/bash -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER users WITH password 'P@ssw0rd';
    CREATE DATABASE users WITH OWNER users;
    CREATE USER orders WITH password 'P@ssw0rd';
    CREATE DATABASE orders WITH OWNER orders;
EOSQL
