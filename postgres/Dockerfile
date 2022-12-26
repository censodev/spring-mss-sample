FROM postgres:13.6-alpine
COPY ./bootstrap/ /docker-entrypoint-initdb.d/
RUN sed -i -e 's/\r$//' docker-entrypoint-initdb.d/*.sh