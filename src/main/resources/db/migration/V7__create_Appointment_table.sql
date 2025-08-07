CREATE TABLE appointment(

    appointment_id INT PRIMARY KEY AUTO_INCREMENT,

    appointment_date DATE ,

    appointment_time TIME ,

    patientId_fk INT,

    docterId_fk INT,

    patientDeseaseId_fk INT,


    FOREIGN KEY(patientId_fk) REFERENCES patient(patient_id) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY(docterId_fk) REFERENCES docter_info(docter_info_id) ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY(patientDeseaseId_fk) REFERENCES diseas(diseas_id) ON DELETE CASCADE ON UPDATE CASCADE






);


