INSERT INTO KIM_ROLE_TYPE_T(ROLE_TYPE_CODE, DESCRIPTION)
VALUES('A', 'AWARD');

COMMIT;

INSERT INTO KIM_ROLES_T(ID, NAME, DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG)
VALUES(15, 'Award Creator', 'Award Creator','A', 'Y');
INSERT INTO KIM_ROLES_T(ID,NAME, DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG)
VALUES(16, 'Award Aggregator', 'Award Aggregator','A', 'Y');
INSERT INTO KIM_ROLES_T(ID,NAME, DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG)
VALUES(17, 'Award Viewer', 'Award Viewer','A', 'Y');
INSERT INTO KIM_ROLES_T(ID,NAME, DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG)
VALUES(18, 'Award Unassigned', 'Award Unassigned - no permissions','A', 'Y');

COMMIT;

INSERT INTO KIM_PERMISSIONS_T(ID, NAME, DESCRIPTION, NAMESPACE_ID)
VALUES (42, 'CREATE_AWARD', 'Create Award Document', 2);
INSERT INTO KIM_PERMISSIONS_T(ID, NAME, DESCRIPTION, NAMESPACE_ID)
VALUES (43, 'MODIFY_AWARD', 'Modify Award Document', 2);
INSERT INTO KIM_PERMISSIONS_T(ID, NAME, DESCRIPTION, NAMESPACE_ID)
VALUES (44, 'VIEW_AWARD', 'View Award Document', 2);

COMMIT;

INSERT INTO KIM_ROLES_PERMISSIONS_T(ACTIVE_FLAG, ROLE_ID, PERMISSION_ID)
VALUES ('Y', 15, 42);
INSERT INTO KIM_ROLES_PERMISSIONS_T(ACTIVE_FLAG, ROLE_ID, PERMISSION_ID)
VALUES ('Y', 15, 43);
INSERT INTO KIM_ROLES_PERMISSIONS_T(ACTIVE_FLAG, ROLE_ID, PERMISSION_ID)
VALUES ('Y', 15, 44);
INSERT INTO KIM_ROLES_PERMISSIONS_T(ACTIVE_FLAG, ROLE_ID, PERMISSION_ID)
VALUES ('Y', 16, 42);
INSERT INTO KIM_ROLES_PERMISSIONS_T(ACTIVE_FLAG, ROLE_ID, PERMISSION_ID)
VALUES ('Y', 16, 43);
INSERT INTO KIM_ROLES_PERMISSIONS_T(ACTIVE_FLAG, ROLE_ID, PERMISSION_ID)
VALUES ('Y', 16, 44);
INSERT INTO KIM_ROLES_PERMISSIONS_T(ACTIVE_FLAG, ROLE_ID, PERMISSION_ID)
VALUES ('Y', 17, 43);
INSERT INTO KIM_ROLES_PERMISSIONS_T(ACTIVE_FLAG, ROLE_ID, PERMISSION_ID)
VALUES ('Y', 17, 44);
INSERT INTO KIM_ROLES_PERMISSIONS_T(ACTIVE_FLAG, ROLE_ID, PERMISSION_ID)
VALUES ('Y', 18, 44);

COMMIT;