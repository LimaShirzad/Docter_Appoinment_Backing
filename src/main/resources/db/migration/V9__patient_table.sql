CREATE TABLE patient(

            patient_id BIGINT  PRIMARY KEY AUTO_INCREMENT,

            user_fk BIGINT,

            FOREIGN KEY(user_fk) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE




);