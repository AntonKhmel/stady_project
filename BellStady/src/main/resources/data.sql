--Authentication
INSERT INTO Authentication (version, login, pass, name) VALUES (0, 'user', 'password', 'name');

--Requisite
INSERT INTO Requisite (version, inn, cpp) VALUES (0, '6449013711', '644901001');
INSERT INTO Requisite (version, inn, cpp) VALUES (0, '7715805253', '771501001');
INSERT INTO Requisite (version, inn, cpp) VALUES (0, '7203253271', '720301001');

--Country
INSERT INTO Country (version, name, code) VALUES (0, 'Russia', 172);
INSERT INTO Country (version, name, code) VALUES (0, 'Belorusiya', 143);
INSERT INTO Country (version, name, code) VALUES (0, 'Kazahstan', 159);

--Region
INSERT INTO Region (version, name) VALUES (0, 'Saratovskaya oblast');
INSERT INTO Region (version, name) VALUES (0, 'Samarskaya oblast');
INSERT INTO Region (version, name) VALUES (0, 'Voronezhskaya oblast');

--City
INSERT INTO City (version, name) VALUES (0, 'Saratov');
INSERT INTO City (version, name) VALUES (0, 'Samara');
INSERT INTO City (version, name) VALUES (0, 'Voronezh');

--Street
INSERT INTO Street (version, name) VALUES (0, 'Gorkovo');
INSERT INTO Street (version, name) VALUES (0, 'Lenina');
INSERT INTO Street (version, name) VALUES (0, 'Zagorodnaya');

--House
INSERT INTO House (version, number_house, number_flat, number_office) VALUES (0, '122A', 14, '7');
INSERT INTO House (version, number_house, number_flat, number_office) VALUES (0, '15B', 122, '16T');
INSERT INTO House (version, number_house, number_flat, number_office) VALUES (0, '124/7M', 7, '24');

--Address
INSERT INTO Address (version, country_id, region_id, city_id, street_id, house_id) VALUES (0, 1, 1, 1, 1, 1);
INSERT INTO Address (version, country_id, region_id, city_id, street_id, house_id) VALUES (0, 1, 2, 2, 2, 2);
INSERT INTO Address (version, country_id, region_id, city_id, street_id, house_id) VALUES (0, 1, 3, 3, 3, 3);

--Phone
INSERT INTO Phone (version, number_phone) VALUES (0, '8(111)111-11-11');
INSERT INTO Phone (version, number_phone) VALUES (0, '8(111)222-22-22');
INSERT INTO Phone (version, number_phone) VALUES (0, '8(222)333-33-33');
INSERT INTO Phone (version, number_phone) VALUES (0, '8(444)444-44-44');
INSERT INTO Phone (version, number_phone) VALUES (0, '8(333)555-55-55');
INSERT INTO Phone (version, number_phone) VALUES (0, '8(333)777-77-77');
INSERT INTO Phone (version, number_phone) VALUES (0, '8(444)888-88-88');
INSERT INTO Phone (version, number_phone) VALUES (0, '8(444)999-99-99');
INSERT INTO Phone (version, number_phone) VALUES (0, '8(444)001-01-01');

--Organization
INSERT INTO Organization (version, short_name, full_name, recvisit_id, address_id, phone_id, is_active) VALUES (0, 'Primer', 'OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU "PRIMER"', 1, 1, 1, true);
INSERT INTO Organization (version, short_name, full_name, recvisit_id, address_id, phone_id, is_active) VALUES (0, 'Abradoks', 'OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU "ABRADOKS"', 2, 2, 2, true);
INSERT INTO Organization (version, short_name, full_name, recvisit_id, address_id, phone_id, is_active) VALUES (0, 'Milena', 'OBSHHESTVO S OGRANICHENNOJ OTVETSTVENNOSTYU "MILENA"', 3, 3, 3, true);

--Position
INSERT INTO Position (version, name) VALUES (0, 'Worker');

--Citizenship
INSERT INTO Citizenship (version, name, code) VALUES (0, 'Russia', 654);

--Doc_type
INSERT INTO Doc_type (version, doc_code, doc_name, doc_number) VALUES (0, 377, 'Nacladnaya', 17);
INSERT INTO Doc_type (version, doc_code, doc_name, doc_number) VALUES (0, 140, 'Podtverzhdenie', 24);

--Office table
INSERT INTO Office (version, name, address_id, organization_id, phone_id, is_active) VALUES (0, 'Rivera', 1, 1, 6, true);
INSERT INTO Office (version, name, address_id, organization_id, phone_id, is_active) VALUES (0, 'Milex', 2, 2, 7, true);
INSERT INTO Office (version, name, address_id, organization_id, phone_id, is_active) VALUES (0, 'Prime', 3, 3, 8, true);

--User
INSERT INTO Employee (version, first_name, second_name, middle_name, position_id, document_id, citizenship_id, phone_id, office_id, is_identified) VALUES (0, 'Anton', 'Khmel', 'Vasilievich', 1, 1, 1, 4, 1, true);
INSERT INTO Employee (version, first_name, second_name, middle_name, position_id, document_id, citizenship_id, phone_id, office_id, is_identified) VALUES (0, 'Ivan', 'Ivanov', 'Ivanovich', 1, 1, 1, 5, 1, true);
INSERT INTO Employee (version, first_name, second_name, middle_name, position_id, document_id, citizenship_id, phone_id, office_id, is_identified) VALUES (0, 'Petr', 'Petrov', 'Petrovich', 1, 1, 1, 9, 1, true);