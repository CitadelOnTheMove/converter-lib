package eu.citadel.converter.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

/**
 * Utils for Input-Output.
 * @author Leonardo Dal Zovo
 */
public class IOUtils {
	/**
	 * Return the name of the charset that best matches the supplied input data.
	 * The name will be one that can be used with other APIs on the platform that accept 
	 * charset names. It is the "Canonical name" as defined by the class java.nio.charset.Charset;
	 * for charsets that are registered with the IANA charset registry, this is the MIME-preferred
	 * registerd name.
	 * 
	 * Note though, that because the detection only looks at the start of the input data, there 
	 * is a possibility that the returned charset will fail to handle the full set of input data.
	 * 
	 * The input stream that supplies the character data must have markSupported() == true;
	 * 
	 * @param stream the stream to analyze
	 * @return the name of the charset that best matches the supplied input data or null
	 * @throws IOException
	 */
	public static String detectCharset(InputStream stream) throws IOException {
		Logger logger = LoggerFactory.getLogger(IOUtils.class.getName() + ".detectCharset");
		logger.trace("detectCharset(InputStream) - start");

		CharsetDetector detector = new CharsetDetector();
	    detector.setText(stream);
	    CharsetMatch match = detector.detect();
	    String returnString = null;
	    if (match == null) {
	    	logger.debug("detectCharset(InputStream) - return: null");
	    }
	    else {
	    	returnString = match.getName();
	    	logger.debug("detectCharset(InputStream) - return: {}", returnString);
	    }
		
		logger.trace("detectCharset(InputStream) - end");
	    return returnString;
	}
	
	/**
	 * Return the BufferedReader to read the current path using the specified Charset if specified
	 * or guessing it.
	 * 
	 * @param path the path to the file
	 * @param loadCharset the charset to use for decoding or null to guess it
	 * @return new buffered reader, with default buffer size, to read text from the file
	 * @throws IOException
	 */
	public static BufferedReader getBufferedReader(Path path, Charset loadCharset) throws IOException {
		Logger logger = LoggerFactory.getLogger(IOUtils.class.getName() + ".getBufferedReader");
		logger.trace("getBufferedReader(Path, Charset) - start");
		if (path == null) {
			logger.warn("getBufferedReader(Path, Charset) - no path");
			logger.trace("getBufferedReader(Path, Charset) - end");
			return null;
		}
		BufferedReader returnBufferedReader = loadCharset == null ? newBufferedReader(path) : newBufferedReader(path, loadCharset);
		logger.trace("getBufferedReader(Path, Charset) - end");
		return returnBufferedReader;
	}
	
	/**
	 * Open a file for reading, returning a BufferedReader that may be used to read text
	 * from the file in an efficient manner. Bytes from the file are decoded into characters
	 * using the specified charset. Reading commences at the beginning of the file.
	 * 
	 * Alias of java.nio.file.Files.newBufferedReader(path, charset)
	 * 
	 * @param path the path to the file
	 * @param charset the charset to use for decoding
	 * @return new buffered reader, with default buffer size, to read text from the file
	 * @throws IOException
	 */
	protected static BufferedReader newBufferedReader(Path path, Charset charset) throws IOException {
		Logger logger = LoggerFactory.getLogger(IOUtils.class.getName() + ".newBufferedReader.PathCharset");
		logger.trace("newBufferedReader(Path, Charset) - start");
		BufferedReader returnBufferedReader = Files.newBufferedReader(path, charset);
		logger.debug("newBufferedReader(Path, Charset) - {}, {}", path, charset);
		logger.trace("newBufferedReader(Path, Charset) - end");
		return returnBufferedReader;
	}
	
	/**
	 * Autodetect the charset of an inputStream, and return a BufferedReader
	 * to access the converted input data.
	 * 
	 * @param path the path to the file
	 * @return new buffered reader, with default buffer size, to read text from the file
	 * @throws IOException
	 */
	protected static BufferedReader newBufferedReader(Path path) throws IOException {
		Logger logger = LoggerFactory.getLogger(IOUtils.class.getName() + ".newBufferedReader.Path");
		logger.trace("newBufferedReader(Path) - start");
		CharsetDetector detector = new CharsetDetector();
		detector.setText(new BufferedInputStream(Files.newInputStream(path)));
		CharsetMatch match = detector.detect();
		if (match == null) {
			logger.debug("newBufferedReader(Path) - CharsetMatch: no match");
		}
		else {
			logger.debug("newBufferedReader(Path) - CharsetMatch: {}", match.getName());
			String language = match.getLanguage();
			if (language == null) {
				logger.debug("newBufferedReader(Path) - Language: no match");
			}
			else {
				logger.debug("newBufferedReader(Path) - Language: {}", language);
				if ("fr".equalsIgnoreCase(match.getLanguage())) {
					BufferedReader returnBufferedReader = Files.newBufferedReader(path, Charset.forName("IBM863"));
					logger.warn("newBufferedReader(Path) - Charset: IBM863");
					logger.trace("newBufferedReader(Path) - end");
					return returnBufferedReader;
				}
			}
		}
		BufferedReader returnBufferedReader = new BufferedReader(detector.getReader(new BufferedInputStream(Files.newInputStream(path)), null));
		logger.trace("newBufferedReader(Path) - end");
		return returnBufferedReader;
	}
	
	/**
	 * Read a file using path and encoding and return a string
	 * @param path the path to the file
	 * @param charset the charset to use for decoding
	 * @return text from the file
	 * @throws IOException
	 */
	public static String readFile(String path, Charset charset) throws IOException {
		Logger logger = LoggerFactory.getLogger(IOUtils.class.getName() + ".readFile");
		logger.trace("readFile(String, Charset) - start");
		String returnString = com.google.common.io.Files.toString(new File(path), charset);
		logger.debug("readFile(String, Charset) - {}, {}", path, charset);
		logger.trace("readFile(String, Charset) - end");
		return returnString;
	}
}
