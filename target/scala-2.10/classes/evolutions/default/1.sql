# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  constraint pk_admin primary key (id))
;

create table applied_jobs (
  id                        integer(11) auto_increment not null,
  manadatory_skill          varchar(255),
  desired_skil              varchar(255),
  job_status                varchar(255),
  username                  varchar(255),
  jobno                     varchar(255),
  location                  varchar(255),
  positionname              varchar(255),
  constraint pk_applied_jobs primary key (id))
;

create table certification_details (
  id                        integer(11) auto_increment not null,
  cert_name                 varchar(255),
  cert_year                 varchar(255),
  user_details_email        varchar(255),
  constraint pk_certification_details primary key (id))
;

create table education_details (
  id                        integer(11) auto_increment not null,
  institute_name            varchar(255),
  degree                    varchar(255),
  startdate                 varchar(255),
  enddate                   varchar(255),
  user_details_email        varchar(255),
  constraint pk_education_details primary key (id))
;

create table employment_details (
  id                        integer(11) auto_increment not null,
  company_name              varchar(255),
  position                  varchar(255),
  startdate                 varchar(255),
  enddate                   varchar(255),
  user_details_email        varchar(255),
  constraint pk_employment_details primary key (id))
;

create table store_excel_file (
  id                        bigint auto_increment not null,
  request_number            varchar(255),
  request_type              varchar(255),
  labour_category           varchar(255),
  performance_level         varchar(255),
  position_type             varchar(255),
  clearance_required        varchar(255),
  work_location             varchar(255),
  work_description          varchar(255),
  manadatory_skills         varchar(255),
  desired_skill             varchar(255),
  certification_required    varchar(255),
  conus_travel              varchar(255),
  oconus_travel             varchar(255),
  reghrper_year             varchar(255),
  schedule_comments         varchar(255),
  nonpub_comments           varchar(255),
  mission_critical          varchar(255),
  night_work                varchar(255),
  local_travalusingpub      varchar(255),
  pager_duty                varchar(255),
  pagerduty_comments        varchar(255),
  workon_holiday            varchar(255),
  workon_weekends           varchar(255),
  shift_work                varchar(255),
  warzone                   varchar(255),
  coop                      varchar(255),
  dueto_pmo                 varchar(255),
  update_date               varchar(255),
  dueto_govt                varchar(255),
  constraint pk_store_excel_file primary key (id))
;

create table user_clearance (
  id                        integer(11) auto_increment not null,
  clearance                 varchar(255),
  constraint pk_user_clearance primary key (id))
;

create table user_details (
  email                     varchar(255) not null,
  password                  varchar(255),
  fullname                  varchar(255),
  gender                    varchar(255),
  dob                       varchar(255),
  constraint pk_user_details primary key (email))
;

create table user_position (
  id                        integer(11) auto_increment not null,
  position                  varchar(255),
  constraint pk_user_position primary key (id))
;

create table user_skill (
  id                        integer(11) auto_increment not null,
  skill_name                varchar(255),
  constraint pk_user_skill primary key (id))
;


create table user_details_user_skill (
  user_details_email             varchar(255) not null,
  user_skill_id                  integer(11) not null,
  constraint pk_user_details_user_skill primary key (user_details_email, user_skill_id))
;

create table user_details_user_clearance (
  user_details_email             varchar(255) not null,
  user_clearance_id              integer(11) not null,
  constraint pk_user_details_user_clearance primary key (user_details_email, user_clearance_id))
;

create table user_details_user_position (
  user_details_email             varchar(255) not null,
  user_position_id               integer(11) not null,
  constraint pk_user_details_user_position primary key (user_details_email, user_position_id))
;
alter table certification_details add constraint fk_certification_details_user_details_1 foreign key (user_details_email) references user_details (email) on delete restrict on update restrict;
create index ix_certification_details_user_details_1 on certification_details (user_details_email);
alter table education_details add constraint fk_education_details_user_details_2 foreign key (user_details_email) references user_details (email) on delete restrict on update restrict;
create index ix_education_details_user_details_2 on education_details (user_details_email);
alter table employment_details add constraint fk_employment_details_user_details_3 foreign key (user_details_email) references user_details (email) on delete restrict on update restrict;
create index ix_employment_details_user_details_3 on employment_details (user_details_email);



alter table user_details_user_skill add constraint fk_user_details_user_skill_user_details_01 foreign key (user_details_email) references user_details (email) on delete restrict on update restrict;

alter table user_details_user_skill add constraint fk_user_details_user_skill_user_skill_02 foreign key (user_skill_id) references user_skill (id) on delete restrict on update restrict;

alter table user_details_user_clearance add constraint fk_user_details_user_clearance_user_details_01 foreign key (user_details_email) references user_details (email) on delete restrict on update restrict;

alter table user_details_user_clearance add constraint fk_user_details_user_clearance_user_clearance_02 foreign key (user_clearance_id) references user_clearance (id) on delete restrict on update restrict;

alter table user_details_user_position add constraint fk_user_details_user_position_user_details_01 foreign key (user_details_email) references user_details (email) on delete restrict on update restrict;

alter table user_details_user_position add constraint fk_user_details_user_position_user_position_02 foreign key (user_position_id) references user_position (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table admin;

drop table applied_jobs;

drop table certification_details;

drop table education_details;

drop table employment_details;

drop table store_excel_file;

drop table user_clearance;

drop table user_details;

drop table user_details_user_skill;

drop table user_details_user_clearance;

drop table user_details_user_position;

drop table user_position;

drop table user_skill;

SET FOREIGN_KEY_CHECKS=1;

