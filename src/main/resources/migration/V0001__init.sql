create table bugtrackers
(
    id uuid not null constraint bugtrackers_pk primary key,
    text reporter not null,
    text created not null,
    text status not null,
    text assignee not null,
    text assignee not null
);