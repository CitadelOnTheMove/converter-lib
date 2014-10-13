package eu.citadel.converter.io.index;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("unchecked")
public class CitadelIndexConstants {
	public static final int TYPE_INFRASTRUCTURE 			= 7;
	public static final int TYPE_HEALTH 					= 8;
	public static final int TYPE_TOURISM 					= 9;
	public static final int TYPE_GOVERNANCE 				= 10;
	public static final int TYPE_POPULATION 				= 11;
	public static final int TYPE_CULTURE_SPORTS_FREETIME	= 12;
	public static final int TYPE_AUDIENCE 					= 13;
	public static final int TYPE_TERRITORY 					= 14;
	public static final int TYPE_ENVIRONMENT_NATURE 		= 15;
	public static final int TYPE_MOBILITY 					= 16;
	public static final int TYPE_EDUCATION 					= 17;
	public static final int TYPE_SECURITY 					= 18;
	public static final int TYPE_WELFARE 					= 19;

	public static final Map<Integer, String> TYPE_MAP		= new LinkedHashMap<Integer, String>();
	public static Map<String, String> LANGUAGE_MAP 	= new LinkedHashMap<String, String>();
	
	public static final String REQUEST_PARAM_DATASET_INFO	= "datasetInfo";

	public static final String FIELD_DATASET_FILE			= "datasetfile";
	public static final String FIELD_TYPE					= "type";
	public static final String FIELD_TITLE					= "title";
	public static final String FIELD_DESCRIPTION			= "description";
	public static final String FIELD_RELEASE_DATE			= "releaseDate";
	public static final String FIELD_LANGUAGE				= "language";
	public static final String FIELD_PUBLISHER				= "publisher";
	public static final String FIELD_LOCATION				= "location";
	public static final String FIELD_SOURCE					= "source";
	public static final String FIELD_LICENSE				= "license";
	public static final String FIELD_LATITUDE				= "latitude";
	public static final String FIELD_LONGITUDE				= "longitude";
	public static final String FIELD_USER_ID				= "userID";

	public static final SimpleDateFormat DATE_FORMAT		= new SimpleDateFormat("dd/MM/yyyy");
	public static final DecimalFormat COORDINATE_FORMAT		= new DecimalFormat( "#0.000000" );
	
	static {
		TYPE_MAP.put(TYPE_INFRASTRUCTURE			, "infrastructure"			);
		TYPE_MAP.put(TYPE_HEALTH					, "health"					);
		TYPE_MAP.put(TYPE_TOURISM					, "tourism"					);
		TYPE_MAP.put(TYPE_GOVERNANCE				, "governance"				);
		TYPE_MAP.put(TYPE_POPULATION				, "population"				);
		TYPE_MAP.put(TYPE_CULTURE_SPORTS_FREETIME	, "culture-sports-freetime"	);
		TYPE_MAP.put(TYPE_AUDIENCE					, "audience"				);
		TYPE_MAP.put(TYPE_TERRITORY					, "territory"				);
		TYPE_MAP.put(TYPE_ENVIRONMENT_NATURE		, "environment-nature"		);
		TYPE_MAP.put(TYPE_MOBILITY					, "mobility"				);
		TYPE_MAP.put(TYPE_EDUCATION					, "education"				);
		TYPE_MAP.put(TYPE_SECURITY					, "security"				);
		TYPE_MAP.put(TYPE_WELFARE					, "welfare"					);
	
		Locale[] localeList = Locale.getAvailableLocales();
		for (Locale l : localeList) {
			LANGUAGE_MAP.put(l.getLanguage(), l.getDisplayLanguage());
		}
		LANGUAGE_MAP = sortByValue(LANGUAGE_MAP);
	}
	
	@SuppressWarnings("rawtypes")
	private static Map sortByValue(Map map) {
	     List list = new LinkedList(map.entrySet());
	     Collections.sort(list, new Comparator() {
	          public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	              .compareTo(((Map.Entry) (o2)).getValue());
	          }
	     });

	    Map result = new LinkedHashMap();
	    for (Iterator it = list.iterator(); it.hasNext();) {
	        Map.Entry entry = (Map.Entry)it.next();
	        result.put(entry.getKey(), entry.getValue());
	    }
	    return result;
	} 
}
