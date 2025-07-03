DELIMITER $$

CREATE PROCEDURE sp_create_post (
    IN p_user_id BIGINT,
    IN p_content TEXT
)
BEGIN
    INSERT INTO posts (user_id, content,created_at, updated_at)
    VALUES (p_user_id, p_content, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
END $$

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_update_post(
    IN p_post_id BIGINT,
    IN p_content TEXT
)
BEGIN
    UPDATE posts
    SET content = p_content,
        updated_at = CURRENT_TIMESTAMP
    WHERE post_id = p_post_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_delete_post(
    IN p_post_id BIGINT
)
BEGIN
    DELETE FROM posts WHERE post_id = p_post_id;
END //

DELIMITER ;
