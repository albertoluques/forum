alter table courses add column active tinyint;
update courses set active = 1;
alter table courses modify active tinyint not null;