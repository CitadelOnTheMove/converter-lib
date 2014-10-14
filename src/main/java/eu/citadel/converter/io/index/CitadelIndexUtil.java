package eu.citadel.converter.io.index;

import static eu.citadel.converter.io.index.CitadelIndexConstants.COORDINATE_FORMAT;
import static eu.citadel.converter.io.index.CitadelIndexConstants.DATE_FORMAT;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_DATASET_FILE;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_DESCRIPTION;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_LANGUAGE;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_LATITUDE;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_LICENSE;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_LOCATION;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_LONGITUDE;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_PUBLISHER;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_RELEASE_DATE;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_SOURCE;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_TITLE;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_TYPE;
import static eu.citadel.converter.io.index.CitadelIndexConstants.FIELD_USER_ID;
import static eu.citadel.converter.io.index.CitadelIndexConstants.REQUEST_PARAM_DATASET_INFO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import eu.citadel.converter.exceptions.ConverterException;
import eu.citadel.converter.io.MultipartUtility;
import eu.citadel.converter.io.index.response.IndexSaveFileResponse;
import eu.citadel.converter.io.index.response.IndexSaveToIndexResponse;

public class CitadelIndexUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger log = LoggerFactory.getLogger(CitadelIndexUtil.class);

	/**
	 * User agent for request
	 */
	private static final String USER_AGENT = "Citadel-Converter";
	
	/**
	 * Get the list of city allowed by citadel index
	 * @param url The url that host the city list
	 * @return List of allowed city sorted by name
	 * @throws ConverterException
	 */
	public static List<CitadelCityInfo> getCitadelCityInfo(String url) throws ConverterException {
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost request = new HttpPost(url);
			CloseableHttpResponse response = httpclient.execute(request);
			String jsonResponse = EntityUtils.toString(response.getEntity());
			Gson gson = new GsonBuilder().create();
			if(jsonResponse.length() < 11) {
				log.error("Invalid response from server: " + jsonResponse + ".");
				throw new ConverterException("Invalid response from server: " + jsonResponse + ".");
			}
			jsonResponse = jsonResponse.substring(10, jsonResponse.length() -1 );
			List<CitadelCityInfo> infos = gson.fromJson(jsonResponse, new TypeToken<List<CitadelCityInfo>>(){}.getType());
			Collections.sort(infos);
			return infos;
		} catch (ParseException | IOException e) {
			log.error(e.getMessage());
			throw new ConverterException(e.getMessage());
		}
	}
	/**
	 * Upload a file to citadel index
	 * @param config Upload configuration
	 * @param uploadFile File to be uploaded
	 * @throws ConverterException
	 */
	public static void uploadToCitadelIndex(CitadelIndexConfig config, File uploadFile) throws ConverterException {
		String jsonResponse;
		try {
			jsonResponse = uploadFile(config.getSaveFileUrl(), uploadFile, config.getCharset(), USER_AGENT );

			Gson gson = new GsonBuilder().create();
        	IndexSaveFileResponse rs = gson.fromJson(jsonResponse, IndexSaveFileResponse.class);

        	if(!rs.isOk()) {
				log.error("Invalid response from server: " + rs.getResponse() + ", " + rs.getMessage() + ".");
				throw new ConverterException("Invalid response from server: " + rs.getResponse() + ", " + rs.getMessage() + ".");
			}
        	jsonResponse = saveToIndex(config, rs.getStoredfileName());

        	IndexSaveToIndexResponse ri = gson.fromJson(jsonResponse, IndexSaveToIndexResponse.class);

        	if(!ri.isOk()) {
				log.error("Invalid response from server: " + ri.getResponse() + ", " + ri.getMessage() + ".");
				throw new ConverterException("Invalid response from server: " + ri.getResponse() + ", " + ri.getMessage() + ".");
			}
		} catch (ClientProtocolException e) {
			log.error(e.getMessage());
			throw new ConverterException(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new ConverterException(e.getMessage());
		};
	}
	
	/**
	 * Upload file on citadel index
	 * @param requestURL Url for request
	 * @param uploadFile File to be uploaded
	 * @param charset Charset
	 * @param userAgent User Agent of request
	 * @return	Response as String
	 * @throws IOException
	 */
	private static String uploadFile(String requestURL, File uploadFile, String charset, String userAgent) throws IOException {
        MultipartUtility multipart = new MultipartUtility(requestURL, charset, userAgent);
         
        multipart.addHeaderField("User-Agent", "Converter");
        multipart.addFilePart("fileUpload", uploadFile);

		List<String> response = multipart.finish();

		log.trace("uploadFile server reply: ");
         
        for (String line : response) {
			log.trace(line);
        }
        return StringUtils.join(response.toArray());
	}

	/**
	 * Index an uploaded file
	 * @param config Index publish configuration
	 * @param datasetFile File name of file stored in the dataset
	 * @return The response string
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static String saveToIndex(CitadelIndexConfig config, String datasetFile) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost request = new HttpPost(config.getSaveIndexUrl());

		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair(REQUEST_PARAM_DATASET_INFO, generateJson(config, datasetFile)));
		request.setEntity(new UrlEncodedFormEntity(postParameters));

		CloseableHttpResponse response = httpclient.execute(request);
		return EntityUtils.toString(response.getEntity());
	    
	}

	/**
	 * Generate the Json for saveToIndex request
	 * @param config Index publish configuration
	 * @param datasetFile File name of file stored in the dataset
	 * @return The string contains the json request
	 */
	private static String generateJson(CitadelIndexConfig config, String datasetFile){
		Map<String, String> resourceMap = new LinkedHashMap<String, String>();
		resourceMap.put(FIELD_DATASET_FILE	, datasetFile);
		resourceMap.put(FIELD_TYPE			, String.valueOf(config.getType()));
		resourceMap.put(FIELD_TITLE			, config.getTitle());
		resourceMap.put(FIELD_DESCRIPTION	, config.getDescription());
		resourceMap.put(FIELD_RELEASE_DATE	, DATE_FORMAT.format(config.getReleaseDate()));
		resourceMap.put(FIELD_LANGUAGE		, config.getLanguage());
		resourceMap.put(FIELD_PUBLISHER		, config.getPublisher());
		resourceMap.put(FIELD_LOCATION		, config.getLocation());
		resourceMap.put(FIELD_SOURCE		, config.getSource());
		resourceMap.put(FIELD_LICENSE		, config.getLicence());
		resourceMap.put(FIELD_LATITUDE		, COORDINATE_FORMAT.format(config.getLatitude()));
		resourceMap.put(FIELD_LONGITUDE		, COORDINATE_FORMAT.format(config.getLongitude()));
		long userId = config.getUserId();
		//the api require that conversion.... :(
		if(config.getUserId() == -1){
			userId = 1;
		}
		resourceMap.put(FIELD_USER_ID		, String.valueOf(userId));
		List<String> entries = new ArrayList<String>();
		for (java.util.Map.Entry<String, String> e : resourceMap.entrySet()) {
			entries.add("\"" + e.getKey() + "\":" + "\"" + e.getValue() + "\"");
		}
		
		return "{" + Joiner.on(",").join(entries) + "}";

	}
	
}
