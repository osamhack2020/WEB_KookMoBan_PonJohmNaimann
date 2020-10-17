-- Admin
INSERT INTO admin(id, serviceNumber, password, name, signUpCode) VALUES (1, '20-123456', '1234', '중대장1', '88888888');
INSERT INTO admin(id, serviceNumber, password, name, signUpCode) VALUES (2, '20-654321', '4321', '중대장2', '99999999');

-- Rule
INSERT INTO rule(id, dispensingTime, returnTime, admin_id) VALUES (1, PARSEDATETIME('17:30', 'HH:mm'), PARSEDATETIME('20:50', 'HH:mm'), 1);
INSERT INTO rule(id, dispensingTime, returnTime, admin_id) VALUES (2, PARSEDATETIME('17:30', 'HH:mm'), PARSEDATETIME('20:50', 'HH:mm'), 2);

-- Soldier
INSERT INTO soldier(id, serviceNumber, name, rank, unit, admin_id) VALUES (1, '11-111111', '강동민', '병장', '육군', 1);
INSERT INTO soldier(id, serviceNumber, name, rank, unit, admin_id) VALUES (2, '22-222222', '이진휘', '병장', '육군', 1);
INSERT INTO soldier(id, serviceNumber, name, rank, unit, admin_id) VALUES (3, '33-333333', '박찬정', '병장', '육군', 2);

-- Device
INSERT INTO device(id, serialNumber, type, manufacturer, phoneNumber, uuid, soldier_id) VALUES (1, 'S/N11111', 'PHONE' , 'Samsung', '010-1111-1111', '0083064714901929041789047456568262994694', 1);
INSERT INTO device(id, serialNumber, type, manufacturer, phoneNumber, uuid, soldier_id) VALUES (2, 'S/N22222', 'PHONE' , 'LG', '010-2222-2222', '0322916224103421907809789840587232299950', 2);
INSERT INTO device(id, serialNumber, type, manufacturer, phoneNumber, uuid, soldier_id) VALUES (3, 'S/N33333', 'PHONE' , 'Apple', '010-3333-3333', '0080989203580441318214195208856647457710', 3);
INSERT INTO device(id, serialNumber, type, manufacturer, phoneNumber, uuid, soldier_id) VALUES (4, 'S/N44444', 'TABLET' , 'Samsung', null, '0332655524119971223995431936078693886759', 3);
