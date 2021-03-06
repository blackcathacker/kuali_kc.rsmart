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

CREATE TABLE FIN_IDC_TYPE_CODE
  ( 
    FIN_IDC_CODE varchar2(5) not null,
    RATE_TYPE_CODE varchar2(3) not null,
    OBJ_ID VARCHAR2(36),
    VER_NBR NUMBER(8,0) NOT NULL,
    RATE_CLASS_CODE VARCHAR2(3) not null
  )
/
ALTER TABLE FIN_IDC_TYPE_CODE
    ADD CONSTRAINT PK_IDC_CODE
    PRIMARY KEY (RATE_CLASS_CODE, RATE_TYPE_CODE)
/
