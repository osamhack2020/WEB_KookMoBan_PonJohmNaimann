-- Admin
INSERT INTO admin(id, serviceNumber, password, name, signUpCode) VALUES (1, '20-123465', '1234', '중대장', '88888888');

-- Soldier
INSERT INTO soldier(id, serviceNumber, name, rank, unit, admin_id) VALUES (1, '11-111111', '강동민', '병장', '육군', 1);
INSERT INTO soldier(id, serviceNumber, name, rank, unit, admin_id) VALUES (2, '22-222222', '이진휘', '병장', '육군', 1);
INSERT INTO soldier(id, serviceNumber, name, rank, unit, admin_id) VALUES (3, '33-333333', '박찬정', '병장', '육군', 1);

-- Device
INSERT INTO device(id, serialNumber, type, manufacturer, phoneNumber, uuid, soldier_id) VALUES (1, 'S/N11111', 'PHONE' , 'Samsung', '010-1111-1111', '11111', 1);
INSERT INTO device(id, serialNumber, type, manufacturer, phoneNumber, uuid, soldier_id) VALUES (2, 'S/N22222', 'PHONE' , 'LG', '010-2222-2222', '22222', 2);
INSERT INTO device(id, serialNumber, type, manufacturer, phoneNumber, uuid, soldier_id) VALUES (3, 'S/N33333', 'PHONE' , 'Apple', '010-3333-3333', '33333', 3);
INSERT INTO device(id, serialNumber, type, manufacturer, phoneNumber, uuid, soldier_id) VALUES (4, 'S/N44444', 'TABLET' , 'Samsung', null, '44444', 3);
