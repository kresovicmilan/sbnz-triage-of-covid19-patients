-- APP USER --
insert into app_user(username, password, role)
values ('admin', 'admin', 2);
insert into app_user(username, password, role)
values ('lekar1', 'lekar1', 1);
insert into app_user(username, password, role)
values ('lekar2', 'lekar2', 1);

-- COUNTRY --
insert into country(country_name, covid_positive, idv_index, country_development_level)
values ('Serbia', 'true', '0.7', 'HIGH');
insert into country(country_name, covid_positive, idv_index, country_development_level)
values ('Croatia', 'true', '0.4', 'HIGH');
insert into country(country_name, covid_positive, idv_index, country_development_level)
values ('Somalia', 'true', '0.2', 'LOW');

-- PATIENT --
insert into patient(name, lastname, covid19positive, alc, cold, contact_risk, cough, cough_or_fever_risk, covid_positive_contact, covid_status, curing_measures, dyspnea, has_cold_sore_throat_or_cough, has_dyspnea_or_hypoxia, has_fever, has_low_lymphocytes, has_non_hospital_pneumonia, has_pneumonia, hypoxia, last_fever, lymphocyte_count, non_hospital_pneumonia, oxygen_risk, oxygen_saturation, pneumonia, respiratory_rate, risk_of_covid, should_do_test, sore_throat, tachnypnea, test_results, country_id)
values ('Ime1', 'Prezime1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);