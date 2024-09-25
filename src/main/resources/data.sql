CREATE TABLE USERS (
        ID INTEGER PRIMARY KEY AUTO_INCREMENT,  -- Coluna ID é a chave primária, do tipo INTEGER, e será auto-incrementada automaticamente pelo banco de dados.
        NAME VARCHAR(255) ,
        EMAIL VARCHAR(255),
        PASSWORD VARCHAR(8),
        CPFCNPJ CHAR(14),
        IS_ACTIVE BOOLEAN DEFAULT TRUE
    -- Coluna NAME armazena o nome do usuário, com um máximo de 255 caracteres.
);

CREATE TABLE PRODUTOS(
	ID INTEGER PRIMARY KEY AUTO_INCREMENT, -- coluna ID é a chave primaria, do tipo INTEGER, auto-incrementada
	NOME VARCHAR (255), -- armazena o nome do produto, com no maximo 255 caracteres.
	QUANTIDADE INTEGER,
	PRECO DECIMAL(5,2),
	USER_ID INTEGER  REFERENCES USERS (ID)
);

CREATE TABLE VENDAS(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    PRECO DECIMAL(10,2),
    QUANTIDADE INTEGER,
    USER_ID INTEGER  REFERENCES USERS (ID),
    PRODUTO_ID INTEGER  REFERENCES PRODUTOS (ID)

);