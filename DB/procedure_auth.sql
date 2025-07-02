DELIMITER $$ 
CREATE PROCEDURE sp_register_user (
    IN p_user_name VARCHAR(255),
    IN p_phone VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_password VARCHAR(255),
    IN p_cover_image VARCHAR(255),
    IN p_biography TEXT
)
BEGIN
    INSERT INTO users (user_name, phone,email,password,cover_image, biography)
    VALUES (p_user_name, p_phone,p_email,p_password,p_cover_image, p_biography);
END $$
DELIMITER ;