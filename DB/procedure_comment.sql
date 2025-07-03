DELIMITER //

CREATE PROCEDURE sp_create_comment (
    IN p_user_id BIGINT,
    IN p_post_id BIGINT,
    IN p_content TEXT
)
BEGIN
    INSERT INTO comments (user_id, post_id, content, created_at)
    VALUES (p_user_id, p_post_id, p_content, CURRENT_TIMESTAMP);
END //

DELIMITER ;
