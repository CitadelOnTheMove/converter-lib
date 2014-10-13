package eu.citadel.converter;

import java.io.File;
import java.util.Date;
import java.util.List;

import eu.citadel.converter.exceptions.ConverterException;
import eu.citadel.converter.io.index.CitadelCityInfo;
import eu.citadel.converter.io.index.CitadelIndexConfig;
import eu.citadel.converter.io.index.CitadelIndexConstants;
import eu.citadel.converter.io.index.CitadelIndexUtil;

public class citadeltest {
	private static final String SampleSaveFileUrl 	= "http://www.citadelonthemove.eu/DesktopModules/DatasetLibrary/API/Service/SaveFile";
	private static final String SampleSaveIndexUrl 	= "http://www.citadelonthemove.eu/DesktopModules/DatasetLibrary/API/Service/SaveToIndex";
	private static final String SampleCityInfoUrl 	= "http://demos.citadelonthemove.eu/app-generator/cityInfo.php";
	
	public static void main(String[] args) {
		testCityInfoList();
		testUploadFile();
	}

	private static void testCityInfoList() {
		try {
			List<CitadelCityInfo> list = CitadelIndexUtil.getCitadelCityInfo(SampleCityInfoUrl);
			for (CitadelCityInfo citadelCityInfo : list) {
				System.out.println(citadelCityInfo.getName());
			}
		} catch (ConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testUploadFile(){
		CitadelIndexConfig config = new CitadelIndexConfig();
		File uploadFile = new File("/home/thomas/test/palermo_turismo.csv.json");
		config.setCharset("UTF-8");
		config.setSaveFileUrl(SampleSaveFileUrl);
		config.setSaveIndexUrl(SampleSaveIndexUrl);
		config.setDatasetFile("test-citadel-file");
		config.setDescription("description");
		config.setTitle("test citadel");
		config.setLanguage("IT");
		config.setLatitude(5.123123F);
		config.setLongitude(6.123542F);
		config.setLicence("GPL");
		config.setPublisher("TTrap");
		config.setReleaseDate(new Date());
		config.setType(CitadelIndexConstants.TYPE_EDUCATION);
		
		try {
			CitadelIndexUtil.uploadToCitadelIndex(config, uploadFile );
		} catch (ConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
