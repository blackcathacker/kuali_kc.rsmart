-- View for Coeus compatibility
CREATE OR REPLACE VIEW OSP$COMM_MEMBER_ROLES AS
SELECT 
    MEMBERSHIP_ID, 
    SEQUENCE_NUMBER, 
    MEMBERSHIP_ROLE_CODE, 
    START_DATE, 
    END_DATE, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM COMM_MEMBER_ROLES; 