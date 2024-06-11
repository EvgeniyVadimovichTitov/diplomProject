 CREATE TABLE IF NOT EXISTS users (
    ID binary(16) PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    password TEXT NOT NULL,
    id_client binary(16)
    );

  CREATE TABLE IF NOT EXISTS authority (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    user_id binary(16)
    );