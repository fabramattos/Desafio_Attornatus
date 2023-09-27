INSERT INTO pessoas (nome, data_nascimento) VALUES ('Melon Husk', '19870326');
INSERT INTO pessoas (nome, data_nascimento) VALUES ('Reanu Keaves', '19870327');
INSERT INTO pessoas (nome, data_nascimento) VALUES ('Rusbeh Jackson', '19870328');

INSERT INTO enderecos (cep, cidade, logradouro, numero, principal, pessoa_id)
VALUES
    ('12345001', 'cidade 1', 'rua 1', 1, true, 1),
    ('12345001', 'cidade 1', 'rua 1', 2, false, 1),
    ('12345002', 'cidade 2', 'rua 2', 1, true, 2),
    ('12345003', 'cidade 3', 'rua 3', 1, true, 3);
