# CLASSRD
    INSERT INTO <SCH>CLASSRD (CLASS_NAME, RELATION_NAME, CORR_CLASS_NAME, OUTER_JOIN, ONLY_SELECT, XMLEXPORTABLE, GRID_DISPLAYABLE, DEF_SETTING_POS, USER_ID, CHECK_DISABLED) VALUES ('YDocTraToModula', 'Azienda', 'AziendaEstesa', 'N', 'N', 'N', 'N', 14, <USER>, 'N');
    INSERT INTO <SCH>CLASSRD (CLASS_NAME, RELATION_NAME, CORR_CLASS_NAME, OUTER_JOIN, ONLY_SELECT, XMLEXPORTABLE, GRID_DISPLAYABLE, DEF_SETTING_POS, USER_ID, CHECK_DISABLED) VALUES ('YDocTraToModula', 'RelArticolo', 'Articolo', 'Y', 'N', 'N', 'Y', 15, <USER>, 'N');
# CLASSRD_NLS
    INSERT INTO <SCH>CLASSRD_NLS (CLASS_NAME, RELATION_NAME, LANGUAGE, NLS_RELATION_NAME, NLS_COLUMN_TITLE, NLS_LABEL) VALUES ('YDocTraToModula', 'Azienda', 'it', 'Azienda', 'Azienda', null);
    INSERT INTO <SCH>CLASSRD_NLS (CLASS_NAME, RELATION_NAME, LANGUAGE, NLS_RELATION_NAME, NLS_COLUMN_TITLE, NLS_LABEL) VALUES ('YDocTraToModula', 'RelArticolo', 'it', 'RelArticolo', 'Articolo', null);
# CLASSRD_ROW
    INSERT INTO <SCH>CLASSRD_ROW (CLASS_NAME, RELATION_NAME, SEQUENCE, ATTRIBUTE_NAME, CD_NAME, CORR_CLASS_NAME, CORR_ATTR_NAME, DEF_SEARCH) VALUES ('YDocTraToModula', 'Azienda', 2, null, null, 'AziendaEstesa', 'Descrizione', 'Y');
    INSERT INTO <SCH>CLASSRD_ROW (CLASS_NAME, RELATION_NAME, SEQUENCE, ATTRIBUTE_NAME, CD_NAME, CORR_CLASS_NAME, CORR_ATTR_NAME, DEF_SEARCH) VALUES ('YDocTraToModula', 'Azienda', 1, 'IdAzienda', null, 'AziendaEstesa', 'IdAzienda', 'N');
    INSERT INTO <SCH>CLASSRD_ROW (CLASS_NAME, RELATION_NAME, SEQUENCE, ATTRIBUTE_NAME, CD_NAME, CORR_CLASS_NAME, CORR_ATTR_NAME, DEF_SEARCH) VALUES ('YDocTraToModula', 'RelArticolo', 3, null, 'DescrizioneArticolo.Descrizione', 'DescrizioneArticolo', 'Descrizione', 'Y');
    INSERT INTO <SCH>CLASSRD_ROW (CLASS_NAME, RELATION_NAME, SEQUENCE, ATTRIBUTE_NAME, CD_NAME, CORR_CLASS_NAME, CORR_ATTR_NAME, DEF_SEARCH) VALUES ('YDocTraToModula', 'RelArticolo', 2, 'RArticolo', null, 'Articolo', 'IdArticolo', 'N');
    INSERT INTO <SCH>CLASSRD_ROW (CLASS_NAME, RELATION_NAME, SEQUENCE, ATTRIBUTE_NAME, CD_NAME, CORR_CLASS_NAME, CORR_ATTR_NAME, DEF_SEARCH) VALUES ('YDocTraToModula', 'RelArticolo', 1, 'IdAzienda', null, 'Articolo', 'IdAzienda', 'N');
