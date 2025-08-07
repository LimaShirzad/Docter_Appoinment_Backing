CREATE TABLE patient(

        patient_id INT  PRIMARY KEY AUTO_INCREMENT,

        blood_group VARCHAR(50) NOT NULL ,

        user_id_fk INT,

        FOREIGN KEY(user_id_fk) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE





);