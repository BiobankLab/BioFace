package com.bee2code.bioimporter.model.solr;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class SampleSolr {

	/**
	 * Sample (main part of record)
	 */

	@JsonProperty
	private String id;

	@JsonProperty
	private String sampleId;

	@JsonProperty
	private String parentSampleId;

	@JsonProperty
	private String accession;

	@JsonProperty
	private OffsetDateTime timestamp;

	@JsonProperty
	private String collection;

	@JsonProperty
	private String biobank;

	@JsonProperty
	private String owner;

	@JsonProperty
	private String comments;

	/**
	 * Main donor part
	 */
	@JsonProperty
	private String donorId;

	@JsonProperty
	private List<Integer> relatedIdParents;

	@JsonProperty
	private List<Integer> relatedIdSiblings;

	@JsonProperty
	private List<Integer> relatedIdChildren;

	@JsonProperty
	private List<Integer> relatedIdSpouse;

	@JsonProperty
	private String gender;

	@JsonProperty
	private OffsetDateTime birthDate;

	@JsonProperty
	private String birthDateCorrectess;

	@JsonProperty
	private String birthDateDataAvailability;

	@JsonProperty
	private String ethnicGroupEgName;

	@JsonProperty
	private String ethnicGroupDataAvailability;

	@JsonProperty
	private String skinToneDataAvailability;

	@JsonProperty
	private Integer skinToneVonLuschan;

	@JsonProperty
	private Integer skinToneMichalski;

	@JsonProperty
	private BigDecimal skinToneCielabL;

	@JsonProperty
	private BigDecimal skinToneCielabA;

	@JsonProperty
	private BigDecimal skinToneCielabB;

	@JsonProperty
	private OffsetDateTime skinToneTimestamp;

	@JsonProperty
	private String skinToneTimestampCorrectess;

	@JsonProperty
	private String hairColorDataAvailability;

	@JsonProperty
	private Integer hairColorHcPalet;

	@JsonProperty
	private String hairColorFisherSaller;

	@JsonProperty
	private BigDecimal hairColorCielabL;

	@JsonProperty
	private BigDecimal hairColorCielabA;

	@JsonProperty
	private BigDecimal hairColorCielabB;

	@JsonProperty
	private String hairColorDescriptive;

	@JsonProperty
	private OffsetDateTime hairColorTimestamp;

	@JsonProperty
	private String hairColorTimestampCorrectess;

	@JsonProperty
	private String weightDeclarativeDataAvailability;

	@JsonProperty
	private BigDecimal weightDeclarative;

	@JsonProperty
	private OffsetDateTime weightDeclarativeTimestamp;

	@JsonProperty
	private String weightDeclarativeTimestampCorrectess;

	@JsonProperty
	private String weightMeasuredDataAvailability;

	@JsonProperty
	private BigDecimal weightMeasured;

	@JsonProperty
	private OffsetDateTime weightMeasuredTimestamp;

	@JsonProperty
	private String weightMeasuredTimestampCorrectess;

	@JsonProperty
	private String statureDeclarativeDataAvailability;

	@JsonProperty
	private BigDecimal statureDeclarative;

	@JsonProperty
	private OffsetDateTime statureDeclarativeTimestamp;

	@JsonProperty
	private String statureDeclarativeTimestampCorrectess;

	@JsonProperty
	private String statureMeasuredDataAvailability;

	@JsonProperty
	private BigDecimal statureMeasured;

	@JsonProperty
	private OffsetDateTime statureMeasuredTimestamp;

	@JsonProperty
	private String statureMeasuredTimestampCorrectess;

	@JsonProperty
	private String eyeColorDataAvailability;

	@JsonProperty
	private Integer eyeColorMartinSchultz;

	@JsonProperty
	private Integer eyeColorMichalski;

	@JsonProperty
	private OffsetDateTime eyeColorTimestamp;

	@JsonProperty
	private String eyeColorTimestampCorrectess;

	@JsonProperty
	private String whrDataAvailability;

	@JsonProperty
	private BigDecimal whr;

	@JsonProperty
	private OffsetDateTime whrTimestamp;

	@JsonProperty
	private String whrTimestampCorrectess;

	@JsonProperty
	private String bmiDataAvailability;

	@JsonProperty
	private BigDecimal bmi;

	@JsonProperty
	private OffsetDateTime bmiTimestamp;

	@JsonProperty
	private String bmiTimestampCorrectess;

	@JsonProperty
	private String bloodGroupDataAvailability;

	@JsonProperty
	private String bloodGroupAB0;

	@JsonProperty
	private String bloodGroupRh;

	@JsonProperty
	private String bloodGroupLewis;

	@JsonProperty
	private String bloodGroupKell;

	@JsonProperty
	private String bloodGroupOther;

	@JsonProperty
	private String ciDataAvailability;

	@JsonProperty
	private BigDecimal ci;

	@JsonProperty
	private OffsetDateTime ciTimestamp;

	@JsonProperty
	private String ciTimestampCorrectess;

	@JsonProperty
	private String headLengthDataAvailability;

	@JsonProperty
	private BigDecimal headLength;

	@JsonProperty
	private OffsetDateTime headLengthTimestamp;

	@JsonProperty
	private String headLengthTimestampCorrectess;

	@JsonProperty
	private String headBreadthDataAvailability;

	@JsonProperty
	private BigDecimal headBreadth;

	@JsonProperty
	private OffsetDateTime headBreadthTimestamp;

	@JsonProperty
	private String headBreadthTimestampCorrectess;

	@JsonProperty
	private String faceLengthDataAvailability;

	@JsonProperty
	private BigDecimal faceLength;

	@JsonProperty
	private OffsetDateTime faceLengthTimestamp;

	@JsonProperty
	private String faceLengthTimestampCorrectess;

	@JsonProperty
	private String faceBreadthDataAvailability;

	@JsonProperty
	private BigDecimal faceBreadth;

	@JsonProperty
	private OffsetDateTime faceBreadthTimestamp;

	@JsonProperty
	private String faceBreadthTimestampCorrectess;

	@JsonProperty
	private String headCircumferenceDataAvailability;

	@JsonProperty
	private BigDecimal headCircumference;

	@JsonProperty
	private OffsetDateTime headCircumferenceTimestamp;

	@JsonProperty
	private String headCircumferenceTimestampCorrectess;

	@JsonProperty
	private String neckCircumferenceDataAvailability;

	@JsonProperty
	private BigDecimal neckCircumference;

	@JsonProperty
	private OffsetDateTime neckCircumferenceTimestamp;

	@JsonProperty
	private String neckCircumferenceTimestampCorrectess;

	@JsonProperty
	private String dactylionHeightDataAvailability;

	@JsonProperty
	private BigDecimal dactylionHeight;

	@JsonProperty
	private OffsetDateTime dactylionHeightTimestamp;

	@JsonProperty
	private String dactylionHeightTimestampCorrectess;

	@JsonProperty
	private String shoulderHeightDataAvailability;

	@JsonProperty
	private BigDecimal shoulderHeight;

	@JsonProperty
	private OffsetDateTime shoulderHeightTimestamp;

	@JsonProperty
	private String shoulderHeightTimestampCorrectess;

	@JsonProperty
	private String lowerLimbLength1DataAvailability;

	@JsonProperty
	private BigDecimal lowerLimbLength1;

	@JsonProperty
	private OffsetDateTime lowerLimbLength1Timestamp;

	@JsonProperty
	private String lowerLimbLength1TimestampCorrectess;

	@JsonProperty
	private String lowerLimbLength2DataAvailability;

	@JsonProperty
	private BigDecimal lowerLimbLength2;

	@JsonProperty
	private OffsetDateTime lowerLimbLength2Timestamp;

	@JsonProperty
	private String lowerLimbLength2TimestampCorrectess;

	@JsonProperty
	private String tibialHeightDataAvailability;

	@JsonProperty
	private BigDecimal tibialHeight;

	@JsonProperty
	private OffsetDateTime tibialHeightTimestamp;

	@JsonProperty
	private String tibialHeightTimestampCorrectess;

	@JsonProperty
	private String chestCircumferenceDataAvailability;

	@JsonProperty
	private BigDecimal chestCircumference;

	@JsonProperty
	private OffsetDateTime chestCircumferenceTimestamp;

	@JsonProperty
	private String chestCircumferenceTimestampCorrectess;

	@JsonProperty
	private String waistCircumferenceDataAvailability;

	@JsonProperty
	private BigDecimal waistCircumference;

	@JsonProperty
	private OffsetDateTime waistCircumferenceTimestamp;

	@JsonProperty
	private String waistCircumferenceTimestampCorrectess;

	@JsonProperty
	private String handLengthDataAvailability;

	@JsonProperty
	private BigDecimal handLength;

	@JsonProperty
	private OffsetDateTime handLengthTimestamp;

	@JsonProperty
	private String handLengthTimestampCorrectess;

	@JsonProperty
	private String handBreadthDataAvailability;

	@JsonProperty
	private BigDecimal handBreadth;

	@JsonProperty
	private OffsetDateTime handBreadthTimestamp;

	@JsonProperty
	private String handBreadthTimestampCorrectess;

	@JsonProperty
	private String footLengthDataAvailability;

	@JsonProperty
	private BigDecimal footLength;

	@JsonProperty
	private OffsetDateTime footLengthTimestamp;

	@JsonProperty
	private String footLengthTimestampCorrectess;

	@JsonProperty
	private String footBreadthDataAvailability;

	@JsonProperty
	private BigDecimal footBreadth;

	@JsonProperty
	private OffsetDateTime footBreadthTimestamp;

	@JsonProperty
	private String footBreadthTimestampCorrectess;

	@JsonProperty
	private String birthPlace;

	@JsonProperty
	private String birthPlaceDataAvailability;

	@JsonProperty
	private String residencePlace;

	@JsonProperty
	private String residencePlaceDataAvailability;

	@JsonProperty
	private OffsetDateTime residencePlaceTimestamp;

	@JsonProperty
	private String residencePlaceTimestampCorrectess;

	@JsonProperty
	private String questionnaire;

	/**
	 * Disease part
	 */

	@JsonProperty
	private List<String> diseasesDataAvailability;

	@JsonProperty
	private List<String> diseasesIcd10;

	@JsonProperty
	private List<String> diseasesNoCode;

	@JsonProperty
	private List<OffsetDateTime> diseasesTimestamp;

	@JsonProperty
	private List<String> diseasesTimestampCorrectess;

	@JsonProperty
	private List<Integer> diseasesSnomed;

	/**
	 * Medical procedures part
	 */

	@JsonProperty
	private List<String> medicalProceduresDataAvailability;

	@JsonProperty
	private List<String> medicalProceduresIcd9;

	@JsonProperty
	private List<String> medicalProceduresNoCode;

	@JsonProperty
	private List<OffsetDateTime> medicalProceduresTimestamp;

	@JsonProperty
	private List<String> medicalProceduresTimestampCorrectess;

	@JsonProperty
	private List<Integer> medicalProceduresSnomed;

	/**
	 * Sample material
	 */
	@JsonProperty
	private String sampleMaterialType;

	@JsonProperty
	private String sampleStorageTemperature;

	@JsonProperty
	private String sampleMaterialTissueSource;

	@JsonProperty
	private String sampleMaterialTissueKind;

	@JsonProperty
	private String sampleMaterialTissueMethod;

	@JsonProperty
	private String sampleMaterialWholeBloodMethod;

	@JsonProperty
	private String sampleMaterialSerumInfo;

	@JsonProperty
	private String sampleMaterialPlasmaInfo;

	@JsonProperty
	private String sampleMaterialBuffyCoatInfo;

	@JsonProperty
	private String sampleMaterialFfpeMethod;

	@JsonProperty
	private String sampleMaterialFfpeKind;

	@JsonProperty
	private String sampleMaterialFfpeSource;

	@JsonProperty
	private String sampleMaterialUrineInfo;

	@JsonProperty
	private String sampleMaterialSalivaInfo;

	@JsonProperty
	private String sampleMaterialSalivaMethod;

	@JsonProperty
	private String sampleMaterialStoolInfo;

	@JsonProperty
	private String sampleMaterialNucleidAcidKind;

	@JsonProperty
	private String sampleMaterialNucleidAcidSource;

	@JsonProperty
	private String sampleMaterialNucleidAcidMethod;

	@JsonProperty
	private String sampleMaterialOther;

	/**
	 * Sample QMS
	 */

	@JsonProperty
	private String sampleQmsPreCt;

	@JsonProperty
	private String sampleQmsPostCt;

	@JsonProperty
	private String sampleQmsPrimaryContainer;

	@JsonProperty
	private String sampleQmsStorageTemperature;

	@JsonProperty
	private String sampleQmsLid;

	@JsonProperty
	private String sampleQmsMarkers;

	@JsonProperty
	private String sampleQmsFasting;

	@JsonProperty
	private String sampleQmsDocumentation;

	/**
	 * All fields above are for facet privot purpose
	 */
	// value from dictionary (icd10 - diseaseType)
	@JsonProperty
	private List<String> diseasesTypeStr;

	// str version of diseases icd10
	@JsonProperty
	private List<String> diseasesIcd10Str;

	// str version of material type
	@JsonProperty
	private String sampleMaterialTypeStr;

}
