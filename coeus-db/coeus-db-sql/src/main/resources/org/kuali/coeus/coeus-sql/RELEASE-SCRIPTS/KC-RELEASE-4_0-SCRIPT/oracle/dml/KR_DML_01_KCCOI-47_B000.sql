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

-- Change to 'Approve COI Disclosure' permission instead of 'Coi admin' permission
UPDATE KRIM_PERM_T SET NM = 'Approve Coi Disclosure' WHERE NM = 'Coi Admin'
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM= 'COI Administrator'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Approve Coi Disclosure'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM= 'KC Superuser'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM= 'Approve Coi Disclosure'), 'Y')
/
