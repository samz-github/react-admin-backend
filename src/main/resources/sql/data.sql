CREATE TABLE if not exists `user` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(255) NOT NULL COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '加密后的密码',
    PRIMARY KEY(id),
    UNIQUE (username)
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='客户表';

INSERT INTO user (username, password) VALUES ('zhangsan', '$3a$10$5Od1YnKYCvwBzhlBF5U0neMpvpbMZgoAnqik7o.ZhFckRy5SnfC.e');
INSERT INTO user (username, password) VALUES ('admin', '$2a$10$PjZ4xXcVX8G1JSMD0vR9l.Zdy99copZhQ7cSApfRWBL5GFkXrpnau');

CREATE TABLE if not exists `username_role` (
                                      `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                                      `username` VARCHAR(255) NOT NULL COMMENT '用户名',
                                      `role_name` VARCHAR(255) NOT NULL COMMENT '加密后的密码',
                                      PRIMARY KEY(id),
                                      UNIQUE (username)
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='角色表';

INSERT INTO username_role (username, role_name) VALUES ("admin", "admin"), ("zhangsan", "normal");
