CREATE OR REPLACE VIEW OSP$PROTOCOL_EXPIDITED_CHKLST AS SELECT
    PROTOCOL_NUMBER,
    SEQUENCE_NUMBER,
    SUBMISSION_NUMBER,
    EXPEDITED_REV_CHKLST_CODE,
    UPDATE_TIMESTAMP,
    UPDATE_USER
FROM PROTOCOL_EXPIDITED_CHKLST;