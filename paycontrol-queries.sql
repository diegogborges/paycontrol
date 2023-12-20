SELECT * FROM paycontrol.signed_plan;
SELECT * FROM paycontrol.person;
SELECT * FROM paycontrol.person_signed_plan;
SELECT * FROM paycontrol.invoice_paid;

select psp.sequence from paycontrol.invoice_paid i 
 inner join paycontrol.person_signed_plan psp 
		on psp.id = i.person_signed_plan_id
 inner join paycontrol.signed_plan sp
		on sp.id = psp.signed_plan_id
        where sp.id = 1
        order by 1 desc limit 1;
        
        
select i.*,  psp.sequence from paycontrol.invoice_paid i 
 inner join paycontrol.person_signed_plan psp 
		on psp.id = i.person_signed_plan_id
 inner join paycontrol.signed_plan sp
		on sp.id = psp.signed_plan_id
        where sp.id = 1
        order by 1 desc limit 1;        
 
SELECT * FROM paycontrol.person_signed_plan psp
	where psp.signed_plan_id = 1 order by sequence asc;