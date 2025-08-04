CREATE TABLE  docter_info(

            docter_id  BIGINT  PRIMARY KEY AUTO_INCREMENT,


            user_fk BIGINT,

            FOREIGN KEY(user_fk) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,

            new_speciality VARCHAR(250),

            docter_cv VARCHAR(350) NOT NULL,

            status_fk BIGINT,
            FOREIGN KEY(status_fk) REFERENCES docter_status(status_id)
            ON DELETE CASCADE ON UPDATE CASCADE


);


