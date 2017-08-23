perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='GZ',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='GZ' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='SZ',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='SZ' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='DG',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='DG' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='FS',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='FS' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='ST',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='ST' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='ZH',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='ZH' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='HZ',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='HZ' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='ZS',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='ZS' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='JM',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='JM' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='ZJ',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='ZJ' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='SG',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='SG' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='HY',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='HY' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='MZ',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='MZ' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='SW',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='SW' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='YJ',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='YJ' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='MM',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='MM' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='ZQ',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='ZQ' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='QY',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='QY' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='CZ',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='CZ' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='JY',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='JY' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='YF',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='YF' AND month=201708;"
perl ~schadm/dssprog/bin/remote_cli.pl bd_mon beeline -e "use jcfw;INSERT OVERWRITE TABLE JCFW.TW_CMPT_USR_M_BAK PARTITION (branch='OTH',month=201708)SELECT CMPT_NBR,AREA_CD,STAT_MO,CMCC_BRANCH_CD,CMPT_OPER_CD,CMPT_BRND_CD,PKG_CD,USR_STS_CD,CM_NEW_USR_IND,CM_ACT_CMNCT_USR_IND,CM_ACTV_DAYS,INNET_MO_CNT,FST_CALL_DT,FST_CALL_TIM,LAST_CALL_DT,INNET_DT,OUTNET_DT,CM_OUTNET_USR_IND,FST_LOCAL_CD,LPAD(UPPER(TRIM(FST_CELL_CD)),8,'0'),FST_DEBET1_FEE,FST_DEBET2_FEE,FST_CALL_DUR,ACTVCALL_DUR,ACTVCALL_CNT,BYCALL_DUR,BYCALL_CNT,FWD_BYCALL_DUR,FWD_BYCALL_CNT,SMS_UP_CNT,SMS_DOWN_CNT,COOPR_CMCC_BRANCH_CD,DIST_D_LOCAL_CD,LPAD(UPPER(TRIM(DIST_D_CELL_CD)),8,'0') FROM JCFW.TW_CMPT_USR_M WHERE branch='OTH' AND month=201708;"

