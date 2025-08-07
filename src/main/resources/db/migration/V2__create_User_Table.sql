CREATE TABLE users(

            user_id INT PRIMARY KEY AUTO_INCREMENT,

            first_name VARCHAR(100) NOT NULL ,

            last_name VARCHAR(100) NOT NULL,

            email VARCHAR(250) NOT NULL UNIQUE,

            user_name VARCHAR(100) NOT NULL UNIQUE,

            password INT NOT NULL UNIQUE,

            profile_picture VARCHAR(350) ,

            role_fk INT NOT NULL,

            gender ENUM("MALE","FEMALE") NOT NULL,


            CONSTRAINT fk_user_role FOREIGN KEY(role_fk) REFERENCES user_role(role_id)
            ON UPDATE CASCADE ON DELETE CASCADE




);

