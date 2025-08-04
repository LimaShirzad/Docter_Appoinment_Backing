CREATE TABLE availabile_docter(

            id  BIGINT  PRIMARY KEY AUTO_INCREMENT,

            docter_fk BIGINT,

            availabile_fk BIGINT,


            FOREIGN KEY (docter_fk) REFERENCES docter_info(docter_id) ON UPDATE CASCADE ON DELETE CASCADE,

            FOREIGN KEY(availabile_fk)   REFERENCES avalibality(avalibale_id) ON UPDATE CASCADE ON DELETE CASCADE,

            start_time TIME,
            end_time TIME

);