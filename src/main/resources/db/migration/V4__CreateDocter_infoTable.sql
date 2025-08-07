CREATE TABLE docter_info(

            docter_info_id INT PRIMARY KEY AUTO_INCREMENT,

            education VARCHAR(255) NOT NULL,

            experience_year INT NOT NULL,

            university_name VARCHAR(250) NOT NULL,

            graduation_year DATE NOT NULL,

            address VARCHAR(500) NOT NULL,

            cv VARCHAR(300) NOT NULL,

            accepted VARCHAR(50) DEFAULT 'PENDING',

            user_fk INT NOT NULL,

            specialty_fk INT NOT NULL,

            FOREIGN KEY(user_fk) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,

            FOREIGN KEY(specialty_fk) REFERENCES specialty(specialty_id) ON DELETE CASCADE ON UPDATE CASCADE









);