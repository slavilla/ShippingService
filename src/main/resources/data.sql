insert into SHIPPING_COST_RULE(NAME,PRIORITY,TYPE,BASE_COST,LOWER_LIMIT,UPPER_LIMIT) values
('Reject',1,'WEIGHT',null,50,null),
('Heavy Parcel',2,'WEIGHT',20,10,50),
('Small Parcel',3,'VOLUME',0.03,0,1500),
('Medium Parcel',4,'VOLUME',0.04,1500,2500),
('Large Parcel',5,'VOLUME',0.05,2500,null);