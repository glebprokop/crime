create
or replace procedure delete_before_date_pr(IN delete_date timestamp without time zone)
    language sql
as
$$
update m_crime
set status='DELETED'
where crime_date <= delete_date;
$$;
