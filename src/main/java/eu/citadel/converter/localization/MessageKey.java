package eu.citadel.converter.localization;

/**
 * Contains static keys for messages.
 * @author Leonardo Dal Zovo
 */
public class MessageKey {
	// DEFAULT EXCEPTION KEYS
	/**
	 * Default key for {@link eu.citadel.converter.exceptions.ConverterException}.
	 */
	public static final String EXCEPTION_CONVERTER = "exception-converter";
	
	/**
	 * Default key for {@link eu.citadel.converter.exceptions.CustomSubruleSchemaException}.
	 */
	public static final String EXCEPTION_CUSTOMSUBRULESCHEMA = "exception-customsubruleschema";
	
	/**
	 * Default key for {@link eu.citadel.converter.exceptions.DatasetException}.
	 */
	public static final String EXCEPTION_DATASET = "exception-dataset";
	
	/**
	 * Default key for {@link eu.citadel.converter.exceptions.ExcelDatasetException}.
	 */
	public static final String EXCEPTION_EXCELDATASET = "exception-exceldataset";
	
	/**
	 * Default key for {@link eu.citadel.converter.exceptions.DatatypeException}.
	 */
	public static final String EXCEPTION_DATATYPE = "exception-datatype";
	
	/**
	 * Default key for {@link eu.citadel.converter.exceptions.MetadataException}.
	 */
	public static final String EXCEPTION_METADATA = "exception-metadata";
	
	/**
	 * Default key for {@link eu.citadel.converter.exceptions.SchemaException}.
	 */
	public static final String EXCEPTION_SCHEMA = "exception-schema";
	
	/**
	 * Default key for {@link eu.citadel.converter.exceptions.TransformationConfigException}.
	 */
	public static final String EXCEPTION_TRANSFORMATIONCONFIG = "exception-transformationconfig";
	
	/**
	 * Default key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_TRANSFORM = "exception-transform";
	
	
	// CUSTOM EXCEPTION KEYS
	/**
	 * Custom key for all applicable exceptions.
	 */
	public static final String EXCEPTION_NOT_IMPLEMENTED = "exception-not-implemented";
	
	/**
	 * Custom key for all applicable exceptions.
	 */
	public static final String EXCEPTION_NOT_PROVIDED = "exception-not-provided";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.CustomSubruleSchemaException}.
	 */
	public static final String EXCEPTION_CUSTOMSUBRULESCHEMA_EXPLAINED = "exception-customsubruleschema-explained";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.DatasetException}.
	 */
	public static final String EXCEPTION_DATASET_SAVE_NO_CONTENT = "exception-dataset-save-no-content";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_VALUE_NULL_UNSPECIFIED_IF_NULLABLE = "exception-value-null-unspecified-if-nullable";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_VALUE_CANNOT_BE_NULL = "exception-value-cannot-be-null";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_VALUE_NULL_EMPTY_POLICY_NULL = "exception-value-null-empty-policy-null";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_VALUE_NULL_EMPTY_POLICY_STRING_UNSUPPORTED = "exception-value-null-empty-policy-string-unsupported";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_VALUE_NULL_EMPTY_POLICY_TYPE_UNSUPPORTED = "exception-value-null-empty-policy-type-unsupported";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_VALUE_NULL_UNSUPPORTED_IF_NULLABLE = "exception-value-null-unsupported-if-nullable";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_ACCESS_METADATA_UNAVAILABLE = "exception-access-metadata-unavailable";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_ACCESS_METADATA_UNSUPPORTED = "exception-access-metadata-unsupported";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_TRANSFORM_OPERATION_UNSUPPORTED = "exception-transform-operation-unsupported";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_TRANSFORM_OPERATION_SPECIFIED_UNSUPPORTED = "exception-transform-operation-specified-unsupported";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_EXCEL_INVALID_FORMAT = "exception-excel-invalid-format";
	
	/**
	 * Custom key for {@link eu.citadel.converter.exceptions.TransformException}.
	 */
	public static final String EXCEPTION_CANNOT_GET_CONTENT = "exception-cannot-get-content";
}
