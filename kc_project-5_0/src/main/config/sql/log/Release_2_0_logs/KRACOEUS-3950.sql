DELETE FROM KRNS_PARM_T t WHERE t.parm_nm IN ('ACTION_LIST_DOCUMENT_POPUP_IND', 'ACTION_LIST_ROUTE_LOG_POPUP_IND', 'DOCUMENT_SEARCH_POPUP_IND', 'DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND') AND t.appl_nmspc_cd <> 'KUALI';
UPDATE KRNS_PARM_T t set t.txt = 'N' WHERE t.parm_nm IN ('ACTION_LIST_DOCUMENT_POPUP_IND', 'ACTION_LIST_ROUTE_LOG_POPUP_IND', 'DOCUMENT_SEARCH_POPUP_IND', 'DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND') AND t.appl_nmspc_cd = 'KUALI';