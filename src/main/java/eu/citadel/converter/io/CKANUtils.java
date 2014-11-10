package eu.citadel.converter.io;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.AbstractContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import eu.citadel.converter.exceptions.CKANException;

/**
 * Utils for CKAN Input-Output.
 * @author Leonardo Dal Zovo
 */
public class CKANUtils {
	/**
	 * CKAN API Header Key
	 */
	private static final String CKAN_API_HEADER = "X-CKAN-API-Key";
	
	/**
	 * packaged_id key
	 */
	public static final String FIELD_NEW_RESOURCE_PACKAGE_ID = "package_id";
	
	/**
	 * name key
	 */
	public static final String FIELD_NEW_RESOURCE_NAME = "name";
	
	/**
	 * description key
	 */
	public static final String FIELD_NEW_RESOURCE_DESCRIPTION = "description";
	
	/**
	 * upload key
	 */
	public static final String FIELD_NEW_RESOURCE_UPLOAD = "upload";
	
	/**
	 * url key
	 */
	public static final String FIELD_NEW_RESOURCE_URL = "url";
	
	/**
	 * format key
	 */
	public static final String FIELD_NEW_RESOURCE_FORMAT = "format";
	
	/**
	 * json format value
	 */
	public static final String FIELD_NEW_RESOURCE_FORMAT_JSON = "json";
	
	/**
	 * csv format value
	 */
	public static final String FIELD_NEW_RESOURCE_FORMAT_CSV = "csv";
	
	/**
	 * resource_id key
	 */
	private static final String FIELD_UPSERT_RESOURCE_ID = "resource_id";
	
	/**
	 * force key
	 */
	private static final String FIELD_UPSERT_RESOURCE_FORCE = "force";
	
	/**
	 * records key
	 */
	private static final String FIELD_UPSERT_RESOURCE_RECORDS = "records";
	
	/**
	 * methods key
	 */
	private static final String FIELD_UPSERT_RESOURCE_METHOD = "method";
	
	/**
	 * upsert value
	 */
	@SuppressWarnings("unused")
	private static final String FIELD_UPSERT_RESOURCE_METHOD_UPSERT = "upsert";
	
	/**
	 * insert value
	 */
	private static final String FIELD_UPSERT_RESOURCE_METHOD_INSERT = "insert";
	
	/**
	 * update value
	 */
	@SuppressWarnings("unused")
	private static final String FIELD_UPSERT_RESOURCE_METHOD_UPDATE = "update";
	
	/**
	 * resource_create action
	 */
	private static final String ACTION_FILESTORE_RESOURCE_CREATE = "/action/resource_create";
	
	/**
	 * datastore_create action
	 */
	@SuppressWarnings("unused")
	private static final String ACTION_DATASTORE_CREATE = "/action/datastore_create";
	
	/**
	 * datastore_upsert action
	 */
	private static final String ACTION_DATASTORE_UPSERT = "/action/datastore_upsert";
	
	
	/**
	 * Create a new Resource in the DataStore using the specified parameters.
	 * @param host the URL host of the CKAN server
	 * @param apiVersion the version of the API to use or null to use the default version
	 * @param apiKey the API key to perform the operation
	 * @param resourceMap a map containing values to create the Resource based on a key
	 * @return
	 * @throws IOException 
	 * @throws CKANException 
	 */
	public static boolean filestoreResourceCreate(URL host, String apiVersion, String apiKey, Map<String, Object> resourceMap) throws IOException, CKANException {
		CloseableHttpResponse response = null;
		
		if (resourceMap == null) {
			resourceMap = Maps.newHashMap();
		}
		
		// package_id and name are mandatory
		if (resourceMap.get(FIELD_NEW_RESOURCE_PACKAGE_ID) == null || resourceMap.get(FIELD_NEW_RESOURCE_NAME) == null) {
			return false;
		}
		
		if (resourceMap.get(FIELD_NEW_RESOURCE_UPLOAD) == null) {
			List<String> entries = new ArrayList<String>();
			for (String key : resourceMap.keySet()) {
				Object objValue =  resourceMap.get(key);
				if (objValue instanceof String) {
					String value = (String) objValue;
					entries.add("\"" + key + "\":" + "\"" + value + "\"");
				}
				else if (objValue instanceof Path) {
					Path value = (Path) objValue;
					entries.add("\"" + key + "\":" + "\"@" + value.toString() + "\"");
				}
				else if (objValue instanceof URL) {
					URL value = (URL) objValue;
					entries.add("\"" + key + "\":" + "\"" + value.toString() + "\"");
				}
			}
			
			String data = null;
			if (!entries.isEmpty()) {
				data = "{" + Joiner.on(",").join(entries) + "}";
			}
			
			response = getHttpResponse(host, apiVersion, apiKey, ACTION_FILESTORE_RESOURCE_CREATE, data);
		}
		else {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			for (String key : resourceMap.keySet()) {
				Object objValue =  resourceMap.get(key);
				AbstractContentBody part = null;
				if (objValue instanceof String) {
					String value = (String) objValue;
					part = new StringBody(value, ContentType.TEXT_PLAIN);
				}
				else if (objValue instanceof Path) {
					Path value = (Path) objValue;
					part = new FileBody(value.toFile());
				}
				else if (objValue instanceof URL) {
					URL value = (URL) objValue;
					part = new StringBody(value.toString(), ContentType.TEXT_PLAIN);
				}
				if (part != null) {
					builder.addPart(key, part);
				}
			}

            HttpEntity entity = builder.build();
            
            response = getHttpResponse(host, apiVersion, apiKey, ACTION_FILESTORE_RESOURCE_CREATE, entity);
		}
		
		return response != null ? isSuccessfulAndClose(response) : false;
	}
	
	/**
	 * Insert a list of new rows in the specified Resource in the DataStore.
	 * @param host the URL host of the CKAN server
	 * @param apiVersion the version of the API to use or null to use the default version
	 * @param apiKey the API key to perform the operation
	 * @param resourceId the resource
	 * @param rowsToInsert a list of a maps, representing the rows to insert, containing column name and values
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws CKANException 
	 */
	public static boolean datastoreResourceInsert(URL host, String apiVersion, String apiKey, String resourceId, List<Map<String, Object>> rowsToInsert) throws IllegalStateException, IOException, CKANException {
		CloseableHttpResponse response = null;
		
		if (rowsToInsert == null) {
			rowsToInsert = Lists.newArrayList();
		}
		
		// resource_id required and not empty row provided
		if (resourceId == null || resourceId.isEmpty() || rowsToInsert.isEmpty()) {
			return false;
		}
		
		String data = "{\"" + FIELD_UPSERT_RESOURCE_ID + "\":\"" + resourceId + "\",\"" + FIELD_UPSERT_RESOURCE_FORCE + "\":\"True\"," +
			"\"" + FIELD_UPSERT_RESOURCE_METHOD + "\":\"" + FIELD_UPSERT_RESOURCE_METHOD_INSERT + "\",\"" + FIELD_UPSERT_RESOURCE_RECORDS + "\":";
		List<String> rows = new ArrayList<String>();
		for (Map<String, Object> rowToInsert : rowsToInsert) {
			List<String> values = new ArrayList<String>();
			for (String key : rowToInsert.keySet()) {
				Object objValue = rowToInsert.get(key);
				if (objValue == null) {
					values.add("\"" + key + "\":null");
				}
				else if (objValue instanceof Boolean || objValue instanceof Number) {
					values.add("\"" + key + "\":" + objValue);
				}
				else if (objValue instanceof List<?>) {
					values.add("\"" + key + "\":" + encodeList(objValue));
				}
				else {
					values.add("\"" + key + "\":\"" + objValue + "\"");
				}
			}
			rows.add("{" + Joiner.on(",").join(values) + "}");
		}
		data = data + "[" + Joiner.on(",").join(rows) + "]}";
		
		response = getHttpResponse(host, apiVersion, apiKey, ACTION_DATASTORE_UPSERT, data);
		return isSuccessfulAndClose(response);
	}
	
	private static String encodeList(Object obj){
		if(obj == null) return "";
		if(!(obj instanceof List<?>)) return String.valueOf(obj);
		List<?> list = (List<?>) obj;
		if(list == null || list.isEmpty()) return "";

		StringBuilder ret = new StringBuilder();
		ret.append("[");

		for (Object o : list) {
			ret.append("\"");
			ret.append(encodeList(o));
			ret.append("\"");
			if(list.indexOf(o) < list.size()) {
				ret.append(", ");
			}
		}
		ret.append("]");
		return ret.toString();
	}
	
	/**
	 * Insert a new row in the specified Resource in the DataStore.
	 * @param host the URL host of the CKAN server
	 * @param apiVersion the version of the API to use or null to use the default version
	 * @param apiKey the API key to perform the operation
	 * @param resourceId the resource
	 * @param rowToInsert a map containing column name and values
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws CKANException 
	 */
	public static boolean datastoreResourceInsert(URL host, String apiVersion, String apiKey, String resourceId, Map<String, Object> rowToInsert) throws IllegalStateException, IOException, CKANException {
		CloseableHttpResponse response = null;
		
		if (rowToInsert == null) {
			rowToInsert = Maps.newHashMap();
		}
		
		// resource_id required and not empty row provided
		if (resourceId == null || resourceId.isEmpty() || rowToInsert.isEmpty()) {
			return false;
		}
		
		String data = "{\"" + FIELD_UPSERT_RESOURCE_ID + "\":\"" + resourceId + "\",\"" + FIELD_UPSERT_RESOURCE_FORCE + "\":\"True\"," +
			"\"" + FIELD_UPSERT_RESOURCE_METHOD + "\":\"" + FIELD_UPSERT_RESOURCE_METHOD_INSERT + "\",\"" + FIELD_UPSERT_RESOURCE_RECORDS + "\":";
		
		List<String> values = new ArrayList<String>();
		for (String key : rowToInsert.keySet()) {
			Object objValue = rowToInsert.get(key);
			if (objValue == null) {
				values.add("\"" + key + "\":null");
			}
			else if (objValue instanceof Boolean || objValue instanceof Number) {
				values.add("\"" + key + "\":" + objValue);
			}
			else {
				values.add("\"" + key + "\":\"" + objValue + "\"");
			}
		}
		String dataRow = "[{" + Joiner.on(",").join(values) + "}]";
		data = data + dataRow + "}";
		
		response = getHttpResponse(host, apiVersion, apiKey, ACTION_DATASTORE_UPSERT, data);
		return isSuccessfulAndClose(response);
	}

	/**
	 * Create a new Resource in the DataStore using the specified parameters.
	 * @param host the URL host of the CKAN server
	 * @param apiVersion the version of the API to use or null to use the default version
	 * @param apiKey the API key to perform the operation
	 * @param resourceMap a map containing values to create the Resource based on a key
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public static boolean datastoreResourceCreate(URL host, String apiVersion, String apiKey, Map<String, Object> resourceMap) throws IOException {
		if (resourceMap == null) {
			resourceMap = Maps.newHashMap();
		}
		String packageId = (String) resourceMap.get(FIELD_NEW_RESOURCE_PACKAGE_ID);
		String name = (String) resourceMap.get(FIELD_NEW_RESOURCE_NAME);
		String description = (String) resourceMap.get(FIELD_NEW_RESOURCE_DESCRIPTION);
		
		return false;
		/*
		CloseableHttpResponse response = getHttpResponse(host, apiVersion, apiKey, ACTION_DATASTORE_CREATE,
			"{\"resource\":{\"package_id\":\"" + packageName + "\",\"name\":\"" + name + "\"}}");
		return isSuccessfulAndClose(response);
		*/
	}
	
	/**
	 * Return a response based on the specified parameters.
	 * @param host the URL host of the CKAN server
	 * @param apiVersion the version of the API to use or null to use the default version
	 * @param apiKey the API key if required to perform the operation or null
	 * @param action the action of the API to call
	 * @param entity the entity passing parameters
	 * @return the resulting response
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static CloseableHttpResponse getHttpResponse(URL host, String apiVersion, String apiKey, String action, HttpEntity entity) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost request = new HttpPost(host.toString() + "/api" + (apiVersion != null ? "/" + apiVersion : "") + action);
		if (apiKey != null) {
			request.setHeader(CKAN_API_HEADER, apiKey);
		}
		request.setEntity(entity);
		return httpclient.execute(request);
	}
	
	/**
	 * Return a response based on the specified parameters.
	 * @param host the URL host of the CKAN server
	 * @param apiVersion the version of the API to use or null to use the default version
	 * @param apiKey the API key if required to perform the operation or null
	 * @param action the action of the API to call
	 * @param stringEntity data in JSON format
	 * @return the resulting response
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static CloseableHttpResponse getHttpResponse(URL host, String apiVersion, String apiKey, String action, String stringEntity) throws IOException {
		StringEntity data = new StringEntity(stringEntity, StandardCharsets.UTF_8);
		data.setContentType("application/json");
		return getHttpResponse(host, apiVersion, apiKey, action, data);
	}
	
	/**
	 * Return if the response is successful and close it
	 * @param response the response to inspect
	 * @return if the response is successful or not
	 * @throws IOException 
	 * @throws CKANException 
	 */
	private static boolean isSuccessfulAndClose(CloseableHttpResponse response) throws IOException, CKANException {
		boolean success = false;
		String errorMessage = null;
		try {
			JsonElement je = new JsonParser().parse(getBody(response));
			if (response.getStatusLine().getStatusCode() == 200) {
				success = je.getAsJsonObject().get("success").getAsBoolean();
				if (!success) {
					errorMessage = je.getAsJsonObject().get("error").getAsJsonObject().get("data").getAsString();
				}
			}
			else {
				errorMessage = je.getAsJsonObject().get("error").getAsJsonObject().get("data").getAsString();
			}
			if (errorMessage != null) {
				throw new CKANException(errorMessage);
			}
		}
		catch (NullPointerException npe) {
			throw new CKANException();
		}
		finally {
			response.close();
		}
		return success;
	}
	
	/**
	 * Return the Response Body
	 * @param response the response
	 * @return the body of the response
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private static String getBody(HttpResponse response) throws IllegalStateException, IOException {
		return EntityUtils.toString(response.getEntity());
		/**
		String body = new String();
		BufferedReader br = new BufferedReader(
		new InputStreamReader((response.getEntity().getContent())));
		String line = "";
		while ((line = br.readLine()) != null) {
			body += line;
		}
		return body;*/
	}
}
