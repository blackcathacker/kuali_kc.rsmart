--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

TRUNCATE TABLE FREQUENCY_BASE DROP STORAGE
/
INSERT INTO FREQUENCY_BASE (FREQUENCY_BASE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Execution Date','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO FREQUENCY_BASE (FREQUENCY_BASE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Project Start Date','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO FREQUENCY_BASE (FREQUENCY_BASE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Obligation End Date','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO FREQUENCY_BASE (FREQUENCY_BASE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Project End Date','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO FREQUENCY_BASE (FREQUENCY_BASE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Obligation Start Date','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO FREQUENCY_BASE (FREQUENCY_BASE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','As Required','admin',SYSDATE,SYS_GUID(),1)
/
