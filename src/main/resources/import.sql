use database homework2;
INSERT INTO `homework2`.`user` (`phone_number`, `username`) VALUES ('18251838318', '王轩');
INSERT INTO `homework2`.`user` (`phone_number`, `username`) VALUES ('15996235505', '唐佳未');
INSERT INTO `homework2`.`user` (`phone_number`, `username`) VALUES ('18260199930', '许杨');
INSERT INTO `homework2`.`user` (`phone_number`, `username`) VALUES ('17805111986', '王轩');

INSERT INTO `homework2`.`combo` (`combo_id`, `message_over_expense`, `call_over_expense`, `combo_name`, `domestic_data_over_expense`, `local_data_over_expense`, `minute_of_call`, `month_expense`, `num_of_domestic_data`, `num_of_local_data`, `num_of_message`) VALUES ('1', '0.1', '0.5', '无套餐', '5', '2', '0', '0', '0', '0', '0');
INSERT INTO `homework2`.`combo` (`combo_id`, `message_over_expense`, `call_over_expense`, `combo_name`, `domestic_data_over_expense`, `local_data_over_expense`, `minute_of_call`, `month_expense`, `num_of_domestic_data`, `num_of_local_data`, `num_of_message`) VALUES ('2', '0.1', '0.5', '话费套餐', '5', '2', '100', '20', '0', '0', '0');
INSERT INTO `homework2`.`combo` (`combo_id`, `message_over_expense`, `call_over_expense`, `combo_name`, `domestic_data_over_expense`, `local_data_over_expense`, `minute_of_call`, `month_expense`, `num_of_domestic_data`, `num_of_local_data`, `num_of_message`) VALUES ('3', '0.1', '0.5', '短信套餐', '5', '2', '0', '10', '0', '0', '100');
INSERT INTO `homework2`.`combo` (`combo_id`, `message_over_expense`, `call_over_expense`, `combo_name`, `domestic_data_over_expense`, `local_data_over_expense`, `minute_of_call`, `month_expense`, `num_of_domestic_data`, `num_of_local_data`, `num_of_message`) VALUES ('4', '0.1', '0.5', '本地流量套餐', '5', '2', '0', '20', '0', '2048', '0');
INSERT INTO `homework2`.`combo` (`combo_id`, `message_over_expense`, `call_over_expense`, `combo_name`, `domestic_data_over_expense`, `local_data_over_expense`, `minute_of_call`, `month_expense`, `num_of_domestic_data`, `num_of_local_data`, `num_of_message`) VALUES ('5', '0.1', '0.5', '国内流量套餐', '5', '2', '0', '30', '3072', '0', '0');
INSERT INTO `homework2`.`combo` (`combo_id`, `message_over_expense`, `call_over_expense`, `combo_name`, `domestic_data_over_expense`, `local_data_over_expense`, `minute_of_call`, `month_expense`, `num_of_domestic_data`, `num_of_local_data`, `num_of_message`) VALUES ('6', '0.1', '0.5', '组合套餐', '5', '2', '100', '50', '3072', '2048', '100');

INSERT INTO `homework2`.`combo_used_record` (`id`, `end_date_time`, `start_date_time`, `combo_id`, `phone_number`) VALUES ('1', '2018-11-13 11:34:31', '2017-11-13 11:34:31', '3', '15996235505');
INSERT INTO `homework2`.`combo_used_record` (`id`, `end_date_time`, `start_date_time`, `combo_id`, `phone_number`) VALUES ('2', '2018-09-13 11:34:31', '2017-09-13 11:34:31', '2', '18260199930');
INSERT INTO `homework2`.`combo_used_record` (`id`, `end_date_time`, `start_date_time`, `combo_id`, `phone_number`) VALUES ('3', '2019-09-30 11:34:31', '2018-09-30 11:34:31', '5', '18251838318');
INSERT INTO `homework2`.`combo_used_record` (`id`, `end_date_time`, `start_date_time`, `combo_id`, `phone_number`) VALUES ('4', '2020-10-13 11:34:31', '2018-10-13 11:34:31', '6', '17805111986');
INSERT INTO `homework2`.`combo_used_record` (`id`, `end_date_time`, `start_date_time`, `combo_id`, `phone_number`) VALUES ('5', '2018-05-13 11:34:31', '2018-02-13 11:34:31', '1', '18251838318');
INSERT INTO `homework2`.`combo_used_record` (`id`, `end_date_time`, `start_date_time`, `combo_id`, `phone_number`) VALUES ('6', '2018-09-01 11:34:31', '2016-09-01 11:34:31', '6', '15996235505');
INSERT INTO `homework2`.`combo_used_record` (`id`, `end_date_time`, `start_date_time`, `combo_id`, `phone_number`) VALUES ('7', '2017-10-13 11:34:31', '2017-08-13 11:34:31', '1', '18260199930');
INSERT INTO `homework2`.`combo_used_record` (`id`, `end_date_time`, `start_date_time`, `combo_id`, `phone_number`) VALUES ('8', '2018-09-13 11:34:31', '2017-09-13 11:34:31', '3', '18260199930');
INSERT INTO `homework2`.`combo_used_record` (`id`, `end_date_time`, `start_date_time`, `combo_id`, `phone_number`) VALUES ('9', '2018-07-13 11:34:31', '2017-07-13 11:34:31', '2', '15996235505');

INSERT INTO `homework2`.`call_record` (`id`, `expense`, `minutes`, `start_date_time`, `phone_number`) VALUES ('1', '5', '10', '2018-03-13 11:34:31', '18251838318');
INSERT INTO `homework2`.`call_record` (`id`, `expense`, `minutes`, `start_date_time`, `phone_number`) VALUES ('2', '4', '8', '2018-10-13 11:34:31', '18251838318');
