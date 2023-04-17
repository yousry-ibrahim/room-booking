-- insert the Rooms
insert into rooms (room_name,capacity,available_for_booking) values ('Amaze',3,true);
insert into rooms (room_name,capacity,available_for_booking) values ('Beauty',7,true);
insert into rooms (room_name,capacity,available_for_booking) values ('Inspire',12,true);
insert into rooms (room_name,capacity,available_for_booking) values ('Strive',20,true);

-- insert the maintenance_schedule
insert into maintenance_schedule (start_time,end_time) values ('09:00:00', '09:15:00');
insert into maintenance_schedule (start_time,end_time) values ('13:00:00', '13:15:00');
insert into maintenance_schedule (start_time,end_time) values ('17:00:00', '17:15:00');