package _14_init;

public class OracleSQL {
	public static final String host = "127.0.0.1";
	public static final String DB_ORACLE = "ORACLE";

	public static final String DB_TYPE = DB_ORACLE;


	private static final String DBURL_Oracle = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	public static final String USERID_Oracle = "group4";
	public static final String PSWD_Oracle = "oracle";


	private static final String DROP_Table_ArtProduct = "DROP TABLE ARTPRODUCT";

	private static final String DROP_Table_APCart = "DROP TABLE APCART";

	private static final String DROP_Table_APMember = "DROP TABLE APMEMBER";
	
	private static final String DROP_Table_ORDERITEMSAP = "DROP TABLE ORDERITEMSAP";

	private static final String DROP_Table_ORDERSAP = "DROP TABLE ORDERSAP";
	
	public static String getDropTableOrderitemsap() {
		return DROP_Table_ORDERITEMSAP;
	}

	public static String getDropTableOrdersap() {
		return DROP_Table_ORDERSAP;
	}

	private static final String CREATE_APMember = " CREATE TABLE APMEMBER " 
			
			+ "(memberID			varchar2(20), " 
			+ " name    			varchar2(32), "
			+ " password			varchar2(32), " 
			+ " address 			varchar2(64), "
			+ " email 				varchar2(64), " 
			+ " tel  				varchar2(15), "
			+ " PRIMARY KEY(ACT_NO))";
	
	private static final String CREATE_ArtProduct = "CREATE TABLE ARTPRODUCT " 
			+ "(APId 				varchar2(32), "
			+ " aptitle				varchar2(200), " 
			+ " aptype				varchar2(50),  "
			+ " apprice				number(12,2),  " 
			+ " apimg				varchar2(200),  "
			+ " apdes				CLOB,  "		
			+ " apnum				number(8,2), "
			+ " PRIMARY KEY(APId))";
	
	private static final String CREATE_APCart = "CREATE TABLE APCART " 
			+ "(APCId 					number(8,2), "
//			+ " memberID				varchar2(100), " 
			+ " apctitle				varchar2(100), " 
			+ " apprice				number(12,2),  " 
			+ " apcnum				number(12,2),  "			
			+ " PRIMARY KEY(APCId))";
	
	
	private static final String CREATE_OrderItemsAP = "Create TABLE ORDERITEMSAP "
			+ "(orderIdAP number generated as identity constraint ORDERITEMSAP_PK primary key, "
			+ " ordersAPNo           int, "
			+ " productTitleAP           varchar2(172), "
			+ " productNumAP           int, "
			+ " productPriceAP        int "
			+ " ) ";
	
	private static final String CREATE_Orders_Oracle = "Create Table ORDERSAP "
			+ "(orderNoAP  INT GENERATED as IDENTITY constraint ORDERSAP_PK primary key, "
			+ " memberId          varchar2(20), "
			+ " shippingAddress   varchar2(164), "
			+ " BNO               varchar2(20), " 
			+ " invoiceTitle      varchar2(72), " 
			+ " TOTAL      int, " 
			+ " orderDate         Date "
			+ " )";

	public static String getCreateOrderitemsap() {
		return CREATE_OrderItemsAP;
	}

	public static String getCreateOrdersOracle() {
		return CREATE_Orders_Oracle;
	}

	public static String getHost() {
		return host;
	}

	public static String getDbOracle() {
		return DB_ORACLE;
	}

	public static String getDbType() {
		return DB_TYPE;
	}

	public static String getDburlOracle() {
		return DBURL_Oracle;
	}

	public static String getUseridOracle() {
		return USERID_Oracle;
	}

	public static String getPswdOracle() {
		return PSWD_Oracle;
	}

	public static String getDropTableArtproduct() {
		return DROP_Table_ArtProduct;
	}

	public static String getDropTableApcart() {
		return DROP_Table_APCart;
	}

	public static String getDropTableApmember() {
		return DROP_Table_APMember;
	}

	public static String getCreateApmember() {
		return CREATE_APMember;
	}

	public static String getCreateArtproduct() {
		return CREATE_ArtProduct;
	}

	public static String getCreateApcart() {
		return CREATE_APCart;
	}
	
	
}
