ALTER TABLE AWARD_IDC_RATE
ADD CONSTRAINT FK_AWARD_IDC_RATE 
FOREIGN KEY (AWARD_ID) 
REFERENCES AWARD (AWARD_ID);

