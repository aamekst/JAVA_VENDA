CREATE TABLE USERS (
        ID INTEGER PRIMARY KEY AUTO_INCREMENT,  -- Coluna ID é a chave primária, do tipo INTEGER, e será auto-incrementada automaticamente pelo banco de dados.
        NAME VARCHAR(255) ,
        EMAIL VARCHAR(255),
        PASSWORD VARCHAR(8),
        CPFCNPJ CHAR(14)
    -- Coluna NAME armazena o nome do usuário, com um máximo de 255 caracteres.
);
