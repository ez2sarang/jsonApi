package com.fast2.zimin.util;

public class Constants {
	/**
	 * 공통코드 관련 상수
	 * 
	 * @author HyungSeok Oh
	 */
	public static final String STATUS_TRUE = "TRUE";
	public static final String STATUS_FALSE = "FALSE";
	
	public static class COMMONCODE {
		public static final String ROOT_GROUP_CODE = "000000";

		/**
		 * user state
		 */
		public static final String GROUP_CODE_USER_STATE = "USR001";
		public static final int USER_STATE_NOMAL = 1;
		
		
		/**
		 * userType
		 */
		public static final String GROUP_CODE_USER_TYPE = "USR002";
		public static final String USER_TYPE_ADMIN = "1";
		public static final String USER_TYPE_GEN = "2";
		public static final String USER_TYPE_QC = "3";
		
		public static final String USER_TYPE_ROLE_USER_STRING = "ROLE_USER";
		public static final String USER_TYPE_ROLE_ROLE_ADMIN_STRING = "ROLE_ADMIN";
		public static final String USER_TYPE_ROLE_ROLE_SYSADMIN_STRING = "ROLE_SYSADMIN";
		public static final String USER_TYPE_ROLE_ROLE_QC = "ROLE_QC";
		
		/**
		 * bannerType
		 */
		public static final String GROUP_CODE_BANNER_TYPE = "BNR001";
		
		/**
		 * bannerLinkType
		 */
		public static final String GROUP_CODE_BANNER_LINK_TYPE = "BNR002";

        /**
         * SO Group Type
         */
        public static final String GROUP_CODE_SO_TYPE = "SER001";

        /**
         * STB Type
         */
        public static final String GROUP_CODE_STB_TYPE = "SER002";

        /**
         * Display Sort Type
         */
        public static final String GROUP_CODE_DP_TYPE = "SER003";
        
        /**
         * Content Type
         */
        public static final String GROUP_CODE_CONTENT_SUBSET_TYPE = "ASS001";
        public static final String CLASSIFICATION_CONTENT_SUBSET_TYPE_MOVIE = "media";
        public static final String CLASSIFICATION_CONTENT_SUBSET_TYPE_IMG = "image";
        public static final String CONTENT_SUBSET_TYPE_MOVIE = "11";
        public static final String CONTENT_SUBSET_TYPE_PREVIEW = "12";
        public static final String CONTENT_SUBSET_TYPE_POSTER = "21";
        public static final String CONTENT_SUBSET_TYPE_THUMBNAIL = "22";

        /**
         * provider Content tier
         */
        public static final String GROUP_CODE_CONTENT_TIER = "ASS002";
        
        /**
         * audio type
         */
        public static final String GROUP_CODE_AUDIO_TYPE = "ASS003";
        public static final String AUDIO_TYPE_STEREO = "3";
        
        /**
         * language codes
         */
        public static final String GROUP_CODE_LANGUAGE_CODE = "ASS004";
        
        /**
         * 3D MODE
         */
        public static final String GROUP_CODE_3D_MODE = "ASS005";
        public static final String THREE_D_MODE_3D = "1";
        public static final String THREE_D_MODE_2D = "0";
        
        /**
         * terms target
         */
        public static final String GROUP_CODE_TERMS_TARGET = "OFF001";
        
        /**
         * pricing type
         */
        public static final String GROUP_CODE_PRICING_TYPE = "OFF002";
        public static final String PRICING_TYPE_ONE_TIME = "OneTime";
        
        /**
         * royaltyType
         */
        public static final String GROUP_CODE_ROYALTY_TYPE = "OFF003";
        public static final String ROYALTY_TYPE_PERCENT = "1";
        public static final String ROYALTY_TYPE_FLATRATE = "2";
        
        /**
         * stage code
         */
        public static final String GROUP_CODE_STAGE_CODE = "SFL001";
        public static final String STAGE_CODE_REJECTED = "05";
        public static final String STAGE_CODE_IN_ENCODING = "15";
        public static final String STAGE_CODE_COMPLETED_ENCODING = "25";
        public static final String STAGE_CODE_READY_APPROVAL = "35";
        public static final String STAGE_CODE_COMPLETE_QC = "40";
        public static final String STAGE_CODE_IN_ENCRYPTION = "45";
        public static final String STAGE_CODE_COMPLETED_ENCRYPTION = "55";
        public static final String STAGE_CODE_IN_DEPLOY_CDN = "65";
        public static final String STAGE_CODE_READY_DEPLOY_SDP = "75";
        public static final String STAGE_CODE_IN_SERVICE = "85";
        public static final String STAGE_CODE_DELETED = "99";
        
        /**
         * service status
         */
        public static final String GROUP_CODE_SERVICE_STATUS = "SFL002";
        public static final String SERVICE_STATUS_NOTYET_SERVICE = "0";
        public static final String SERVICE_STATUS_IN_SERVICE = "1";
        
        /**
         * service Type
         */
        public static final String GROUP_CODE_SERVICE_TYPE = "SFL003";
        public static final String SERVICE_TYPE_OFFER = "1";
        public static final String SERVICE_TYPE_CG = "2";
        public static final String SERVICE_TYPE_ASSET = "3";

        /**
         * Sync Flag Type
         */
        public static final String SYNC_FLAG_NO = "N";
        public static final String SYNC_FLAG_CREATE = "C";
        public static final String SYNC_FLAG_UPDATE = "U";
        public static final String SYNC_FLAG_DELETE = "D";

        /**
         * Content Group Type
         */
        public static final String CG_CONTENT_TYPE = "CG0001";
        public static final String CG_EPISODE_TYPE = "CG0002";
        public static final String CG_RATING = "CG0003";
        public static final String CG_GENRE = "CG0004";
        public static final String CG_SHOW_TYPE = "CG0005";
        public static final String CG_COUNTRY_OF_ORIGIN = "CG0006";
        public static final String CG_AUDIENCE = "CG0007";
        
        public static final String CONTENT_TYPE_RVOD = "01";
        
        /**
         * BulkJob status
         */
        public static final String GROUP_CODE_BULK_JOB_STATUS = "BLK001";
        public static final String BULK_JOB_STATUS_IN_VERIFYING = "10";
        public static final String BULK_JOB_STATUS_SUCCESS_VERIFICATION = "20";
        public static final String BULK_JOB_STATUS_FAILED_VERIFICATION = "30";
        public static final String BULK_JOB_STATUS_IN_WORKING = "40";
        public static final String BULK_JOB_STATUS_SUCCESS_WORKING = "50";
        public static final String BULK_JOB_STATUS_FAILED_WORKING = "60";
	}
	
	public static class DRM
	{
		public static final String STATUS_WAIT_WORK = "0";
		public static final String STATUS_WORKING = "1";
	}
	
	public static class CONTROLLER
	{
		public static final String PROCESS_OK = "0";
		public static final String SYSTEM_FAIL = "100";
		public static final String DUPLICATED_ID = "200";
	}
	
	public static class SUBSCRIBERID
	{
		public static final String ID_TYPE_ID = "ID";
		public static final Object ID_TYPE_STB = "STB";
		
		public static final String SUBSCRIBER_ID_PROCESS_OK = "0";
		public static final String SUBSCRIBER_ID_SYSTEM_FAIL = "100";
		public static final String SUBSCRIBER_ID_DUPLICATED = "200";
	}
	
	public static class PROVIDERID
	{
		public static final String PROVIDER_ID_PROCESS_OK = "0";
		public static final String PROVIDER_ID_SYSTEM_FAIL = "100";
		public static final String PROVIDER_ID_DUPLICATED = "200";
	}

	public static class CONTENTPROVIDER
	{
		public static final String CONTENT_PROVIDER_PROCESS_OK = "0";
		public static final String CONTENT_PROVIDER_SYSTEM_FAIL = "100";
		public static final String CONTENT_PROVIDER_ID_DUPLICATED = "200";
	}

	public static class CONTENTGROUP
	{
		public static final String TITLE_UNKNOWN = "private:UNKNOWN";
	}

	public static class MARKETER
	{
		public static final String MARKETER_PROCESS_OK = "0";
		public static final String MARKETER_SYSTEM_FAIL = "100";
		public static final String MARKETER_DUPLICATED = "200";
	}

	public static class ENCRYPTION
	{
		public static final int ENCRYPTION_TYPE_BCRYPT = 1;
		public static final int ENCRYPTION_TYPE_SHA256 = 256;
	}
	
	public static class UTILITY
	{
		public static final String TABLE_NAME_SUBSTRIBER_ID = "SubscriberId";
		public static final String TABLE_NAME_CONTRACT = "Contract";
	}
	
	public static class FILEUTILS
	{
		public static final String TVOD_FILE_LOAD_METHOD_FTP = "FTP";
		public static final String TVOD_FILE_LOAD_METHOD_NAS = "NAS";
		
		public static final String FTP_MODE_ACTIVE = "ACT";
		public static final String FTP_MODE_PASSIVE = "PAS";
	}

	public static class TVODANDTSTV
	{
		public static final String TYPE_NAME_TVOD = "tvod";
		public static final String TYPE_NAME_TSTV = "tstv";
	}

    public enum ResultMessageType implements Types {
        NOT_FOUND("notFound")
        , NO_NAME("noName")
        , EMPTY("empty")
        ;
        public final String message;

        ResultMessageType(String message) {
            this.message = message;
        }
        public String getName() {
            return this.message;
        }
    }
}

interface Types {
    public String getName();
}