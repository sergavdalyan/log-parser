select ip, count(ip) AS c
from log
where date between '2017-01-01 15:00:00' and '2017-01-01 15:59:59'
group by ip
having c > 200;


select *
from log
where ip = '192.168.106.134';