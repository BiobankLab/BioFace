package com.bee2code.bioimporter.model.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Donor
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class Donor {
	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("donorId")
	private String donorId = null;

	@JsonProperty("relatedId")
	private DonorRelatedId relatedId = null;

	/**
	 * Donor's sex
	 */
	public enum GenderEnum {
		MALE("Male"),

		FEMALE("Female"),

		TRANSGENDER("Transgender"),

		OTHER("Other"),

		NA("NA");

		private String value;

		GenderEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static GenderEnum fromValue(String text) {
			for (GenderEnum b : GenderEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("gender")
	private GenderEnum gender = null;

	@JsonProperty("birth_date")
	private DonorBirthDate birthDate = null;

	@JsonProperty("ethnic_group")
	private EthnicGroup ethnicGroup = null;

	@JsonProperty("skinTone")
	private SkinTone skinTone = null;

	@JsonProperty("hairColor")
	private HairColor hairColor = null;

	@JsonProperty("weight_declarative")
	private WeightDeclarative weightDeclarative = null;

	@JsonProperty("weight_measured")
	private WeightMeasured weightMeasured = null;

	@JsonProperty("stature_declarative")
	private StatureDeclarative statureDeclarative = null;

	@JsonProperty("stature_measured")
	private StatureMeasured statureMeasured = null;

	@JsonProperty("eyeColor")
	private EyeColour eyeColor = null;

	@JsonProperty("whr")
	private WHR whr = null;

	@JsonProperty("bmi")
	private BMI bmi = null;

	@JsonProperty("blood_group")
	private BloodGroup bloodGroup = null;

	@JsonProperty("ci")
	private CI ci = null;

	@JsonProperty("h_lenght")
	private HLenght hLenght = null;

	@JsonProperty("h_breadth")
	private HBreadth hBreadth = null;

	@JsonProperty("f_length")
	private FLength fLength = null;

	@JsonProperty("f_breadth")
	private FBreadth fBreadth = null;

	@JsonProperty("h_circ")
	private HCirc hCirc = null;

	@JsonProperty("n_circ")
	private NCirc nCirc = null;

	@JsonProperty("dactylion_height")
	private DactylionHeight dactylionHeight = null;

	@JsonProperty("shoulder_height")
	private ShoulderHeight shoulderHeight = null;

	@JsonProperty("lower_limb_len_1")
	private LowerLimbLen1 lowerLimbLen1 = null;

	@JsonProperty("ower_limb_len_2")
	private LowerLimbLen2 owerLimbLen2 = null;

	@JsonProperty("tibial_height")
	private TibialHeight tibialHeight = null;

	@JsonProperty("chest_circ")
	private ChestCirc chestCirc = null;

	@JsonProperty("waist_circ")
	private WaistCirc waistCirc = null;

	@JsonProperty("hand_length")
	private HandLength handLength = null;

	@JsonProperty("hand_breadth")
	private HandBreadth handBreadth = null;

	@JsonProperty("foot_length")
	private FootLength footLength = null;

	@JsonProperty("foot_breadth")
	private FootBreadth footBreadth = null;

	@JsonProperty("birth_place")
	private BirthPlace birthPlace = null;

	@JsonProperty("residence_place")
	private ResidencePlace residencePlace = null;

	@JsonProperty("questionnaire")
	private Questionnaire questionnaire = null;

	@JsonProperty("diseases")
	@Valid
	private List<Disease> diseases = null;

	@JsonProperty("medicalProcedures")
	@Valid
	private List<MedicalProcedure> medicalProcedures = null;

	@JsonProperty("sample")
	@Valid
	private List<Sample> sample = null;

	public Donor id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * @return id
	**/
	@ApiModelProperty(value = "")

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Donor donorId(String donorId) {
		this.donorId = donorId;
		return this;
	}

	/**
	 * Get donorId
	 * @return donorId
	**/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getDonorId() {
		return donorId;
	}

	public void setDonorId(String donorId) {
		this.donorId = donorId;
	}

	public Donor relatedId(DonorRelatedId relatedId) {
		this.relatedId = relatedId;
		return this;
	}

	/**
	 * Get relatedId
	 * @return relatedId
	**/
	@ApiModelProperty(value = "")

	@Valid

	public DonorRelatedId getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(DonorRelatedId relatedId) {
		this.relatedId = relatedId;
	}

	public Donor gender(GenderEnum gender) {
		this.gender = gender;
		return this;
	}

	/**
	 * Donor's sex
	 * @return gender
	**/
	@ApiModelProperty(value = "Donor's sex")

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public Donor birthDate(DonorBirthDate birthDate) {
		this.birthDate = birthDate;
		return this;
	}

	/**
	 * Get birthDate
	 * @return birthDate
	**/
	@ApiModelProperty(value = "")

	@Valid

	public DonorBirthDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(DonorBirthDate birthDate) {
		this.birthDate = birthDate;
	}

	public Donor ethnicGroup(EthnicGroup ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
		return this;
	}

	/**
	 * Get ethnicGroup
	 * @return ethnicGroup
	**/
	@ApiModelProperty(value = "")

	@Valid

	public EthnicGroup getEthnicGroup() {
		return ethnicGroup;
	}

	public void setEthnicGroup(EthnicGroup ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	public Donor skinTone(SkinTone skinTone) {
		this.skinTone = skinTone;
		return this;
	}

	/**
	 * Get skinTone
	 * @return skinTone
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SkinTone getSkinTone() {
		return skinTone;
	}

	public void setSkinTone(SkinTone skinTone) {
		this.skinTone = skinTone;
	}

	public Donor hairColor(HairColor hairColor) {
		this.hairColor = hairColor;
		return this;
	}

	/**
	 * Get hairColor
	 * @return hairColor
	**/
	@ApiModelProperty(value = "")

	@Valid

	public HairColor getHairColor() {
		return hairColor;
	}

	public void setHairColor(HairColor hairColor) {
		this.hairColor = hairColor;
	}

	public Donor weightDeclarative(WeightDeclarative weightDeclarative) {
		this.weightDeclarative = weightDeclarative;
		return this;
	}

	/**
	 * Get weightDeclarative
	 * @return weightDeclarative
	**/
	@ApiModelProperty(value = "")

	@Valid

	public WeightDeclarative getWeightDeclarative() {
		return weightDeclarative;
	}

	public void setWeightDeclarative(WeightDeclarative weightDeclarative) {
		this.weightDeclarative = weightDeclarative;
	}

	public Donor weightMeasured(WeightMeasured weightMeasured) {
		this.weightMeasured = weightMeasured;
		return this;
	}

	/**
	 * Get weightMeasured
	 * @return weightMeasured
	**/
	@ApiModelProperty(value = "")

	@Valid

	public WeightMeasured getWeightMeasured() {
		return weightMeasured;
	}

	public void setWeightMeasured(WeightMeasured weightMeasured) {
		this.weightMeasured = weightMeasured;
	}

	public Donor statureDeclarative(StatureDeclarative statureDeclarative) {
		this.statureDeclarative = statureDeclarative;
		return this;
	}

	/**
	 * Get statureDeclarative
	 * @return statureDeclarative
	**/
	@ApiModelProperty(value = "")

	@Valid

	public StatureDeclarative getStatureDeclarative() {
		return statureDeclarative;
	}

	public void setStatureDeclarative(StatureDeclarative statureDeclarative) {
		this.statureDeclarative = statureDeclarative;
	}

	public Donor statureMeasured(StatureMeasured statureMeasured) {
		this.statureMeasured = statureMeasured;
		return this;
	}

	/**
	 * Get statureMeasured
	 * @return statureMeasured
	**/
	@ApiModelProperty(value = "")

	@Valid

	public StatureMeasured getStatureMeasured() {
		return statureMeasured;
	}

	public void setStatureMeasured(StatureMeasured statureMeasured) {
		this.statureMeasured = statureMeasured;
	}

	public Donor eyeColor(EyeColour eyeColor) {
		this.eyeColor = eyeColor;
		return this;
	}

	/**
	 * Get eyeColor
	 * @return eyeColor
	**/
	@ApiModelProperty(value = "")

	@Valid

	public EyeColour getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(EyeColour eyeColor) {
		this.eyeColor = eyeColor;
	}

	public Donor whr(WHR whr) {
		this.whr = whr;
		return this;
	}

	/**
	 * Get whr
	 * @return whr
	**/
	@ApiModelProperty(value = "")

	@Valid

	public WHR getWhr() {
		return whr;
	}

	public void setWhr(WHR whr) {
		this.whr = whr;
	}

	public Donor bmi(BMI bmi) {
		this.bmi = bmi;
		return this;
	}

	/**
	 * Get bmi
	 * @return bmi
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BMI getBmi() {
		return bmi;
	}

	public void setBmi(BMI bmi) {
		this.bmi = bmi;
	}

	public Donor bloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
		return this;
	}

	/**
	 * Get bloodGroup
	 * @return bloodGroup
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Donor ci(CI ci) {
		this.ci = ci;
		return this;
	}

	/**
	 * Get ci
	 * @return ci
	**/
	@ApiModelProperty(value = "")

	@Valid

	public CI getCi() {
		return ci;
	}

	public void setCi(CI ci) {
		this.ci = ci;
	}

	public Donor hLenght(HLenght hLenght) {
		this.hLenght = hLenght;
		return this;
	}

	/**
	 * Get hLenght
	 * @return hLenght
	**/
	@ApiModelProperty(value = "")

	@Valid

	public HLenght getHLenght() {
		return hLenght;
	}

	public void setHLenght(HLenght hLenght) {
		this.hLenght = hLenght;
	}

	public Donor hBreadth(HBreadth hBreadth) {
		this.hBreadth = hBreadth;
		return this;
	}

	/**
	 * Get hBreadth
	 * @return hBreadth
	**/
	@ApiModelProperty(value = "")

	@Valid

	public HBreadth getHBreadth() {
		return hBreadth;
	}

	public void setHBreadth(HBreadth hBreadth) {
		this.hBreadth = hBreadth;
	}

	public Donor fLength(FLength fLength) {
		this.fLength = fLength;
		return this;
	}

	/**
	 * Get fLength
	 * @return fLength
	**/
	@ApiModelProperty(value = "")

	@Valid

	public FLength getFLength() {
		return fLength;
	}

	public void setFLength(FLength fLength) {
		this.fLength = fLength;
	}

	public Donor fBreadth(FBreadth fBreadth) {
		this.fBreadth = fBreadth;
		return this;
	}

	/**
	 * Get fBreadth
	 * @return fBreadth
	**/
	@ApiModelProperty(value = "")

	@Valid

	public FBreadth getFBreadth() {
		return fBreadth;
	}

	public void setFBreadth(FBreadth fBreadth) {
		this.fBreadth = fBreadth;
	}

	public Donor hCirc(HCirc hCirc) {
		this.hCirc = hCirc;
		return this;
	}

	/**
	 * Get hCirc
	 * @return hCirc
	**/
	@ApiModelProperty(value = "")

	@Valid

	public HCirc getHCirc() {
		return hCirc;
	}

	public void setHCirc(HCirc hCirc) {
		this.hCirc = hCirc;
	}

	public Donor nCirc(NCirc nCirc) {
		this.nCirc = nCirc;
		return this;
	}

	/**
	 * Get nCirc
	 * @return nCirc
	**/
	@ApiModelProperty(value = "")

	@Valid

	public NCirc getNCirc() {
		return nCirc;
	}

	public void setNCirc(NCirc nCirc) {
		this.nCirc = nCirc;
	}

	public Donor dactylionHeight(DactylionHeight dactylionHeight) {
		this.dactylionHeight = dactylionHeight;
		return this;
	}

	/**
	 * Get dactylionHeight
	 * @return dactylionHeight
	**/
	@ApiModelProperty(value = "")

	@Valid

	public DactylionHeight getDactylionHeight() {
		return dactylionHeight;
	}

	public void setDactylionHeight(DactylionHeight dactylionHeight) {
		this.dactylionHeight = dactylionHeight;
	}

	public Donor shoulderHeight(ShoulderHeight shoulderHeight) {
		this.shoulderHeight = shoulderHeight;
		return this;
	}

	/**
	 * Get shoulderHeight
	 * @return shoulderHeight
	**/
	@ApiModelProperty(value = "")

	@Valid

	public ShoulderHeight getShoulderHeight() {
		return shoulderHeight;
	}

	public void setShoulderHeight(ShoulderHeight shoulderHeight) {
		this.shoulderHeight = shoulderHeight;
	}

	public Donor lowerLimbLen1(LowerLimbLen1 lowerLimbLen1) {
		this.lowerLimbLen1 = lowerLimbLen1;
		return this;
	}

	/**
	 * Get lowerLimbLen1
	 * @return lowerLimbLen1
	**/
	@ApiModelProperty(value = "")

	@Valid

	public LowerLimbLen1 getLowerLimbLen1() {
		return lowerLimbLen1;
	}

	public void setLowerLimbLen1(LowerLimbLen1 lowerLimbLen1) {
		this.lowerLimbLen1 = lowerLimbLen1;
	}

	public Donor owerLimbLen2(LowerLimbLen2 owerLimbLen2) {
		this.owerLimbLen2 = owerLimbLen2;
		return this;
	}

	/**
	 * Get owerLimbLen2
	 * @return owerLimbLen2
	**/
	@ApiModelProperty(value = "")

	@Valid

	public LowerLimbLen2 getOwerLimbLen2() {
		return owerLimbLen2;
	}

	public void setOwerLimbLen2(LowerLimbLen2 owerLimbLen2) {
		this.owerLimbLen2 = owerLimbLen2;
	}

	public Donor tibialHeight(TibialHeight tibialHeight) {
		this.tibialHeight = tibialHeight;
		return this;
	}

	/**
	 * Get tibialHeight
	 * @return tibialHeight
	**/
	@ApiModelProperty(value = "")

	@Valid

	public TibialHeight getTibialHeight() {
		return tibialHeight;
	}

	public void setTibialHeight(TibialHeight tibialHeight) {
		this.tibialHeight = tibialHeight;
	}

	public Donor chestCirc(ChestCirc chestCirc) {
		this.chestCirc = chestCirc;
		return this;
	}

	/**
	 * Get chestCirc
	 * @return chestCirc
	**/
	@ApiModelProperty(value = "")

	@Valid

	public ChestCirc getChestCirc() {
		return chestCirc;
	}

	public void setChestCirc(ChestCirc chestCirc) {
		this.chestCirc = chestCirc;
	}

	public Donor waistCirc(WaistCirc waistCirc) {
		this.waistCirc = waistCirc;
		return this;
	}

	/**
	 * Get waistCirc
	 * @return waistCirc
	**/
	@ApiModelProperty(value = "")

	@Valid

	public WaistCirc getWaistCirc() {
		return waistCirc;
	}

	public void setWaistCirc(WaistCirc waistCirc) {
		this.waistCirc = waistCirc;
	}

	public Donor handLength(HandLength handLength) {
		this.handLength = handLength;
		return this;
	}

	/**
	 * Get handLength
	 * @return handLength
	**/
	@ApiModelProperty(value = "")

	@Valid

	public HandLength getHandLength() {
		return handLength;
	}

	public void setHandLength(HandLength handLength) {
		this.handLength = handLength;
	}

	public Donor handBreadth(HandBreadth handBreadth) {
		this.handBreadth = handBreadth;
		return this;
	}

	/**
	 * Get handBreadth
	 * @return handBreadth
	**/
	@ApiModelProperty(value = "")

	@Valid

	public HandBreadth getHandBreadth() {
		return handBreadth;
	}

	public void setHandBreadth(HandBreadth handBreadth) {
		this.handBreadth = handBreadth;
	}

	public Donor footLength(FootLength footLength) {
		this.footLength = footLength;
		return this;
	}

	/**
	 * Get footLength
	 * @return footLength
	**/
	@ApiModelProperty(value = "")

	@Valid

	public FootLength getFootLength() {
		return footLength;
	}

	public void setFootLength(FootLength footLength) {
		this.footLength = footLength;
	}

	public Donor footBreadth(FootBreadth footBreadth) {
		this.footBreadth = footBreadth;
		return this;
	}

	/**
	 * Get footBreadth
	 * @return footBreadth
	**/
	@ApiModelProperty(value = "")

	@Valid

	public FootBreadth getFootBreadth() {
		return footBreadth;
	}

	public void setFootBreadth(FootBreadth footBreadth) {
		this.footBreadth = footBreadth;
	}

	public Donor birthPlace(BirthPlace birthPlace) {
		this.birthPlace = birthPlace;
		return this;
	}

	/**
	 * Get birthPlace
	 * @return birthPlace
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BirthPlace getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(BirthPlace birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Donor residencePlace(ResidencePlace residencePlace) {
		this.residencePlace = residencePlace;
		return this;
	}

	/**
	 * Get residencePlace
	 * @return residencePlace
	**/
	@ApiModelProperty(value = "")

	@Valid

	public ResidencePlace getResidencePlace() {
		return residencePlace;
	}

	public void setResidencePlace(ResidencePlace residencePlace) {
		this.residencePlace = residencePlace;
	}

	public Donor questionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
		return this;
	}

	/**
	 * Get questionnaire
	 * @return questionnaire
	**/
	@ApiModelProperty(value = "")

	@Valid

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Donor diseases(List<Disease> diseases) {
		this.diseases = diseases;
		return this;
	}

	public Donor addDiseasesItem(Disease diseasesItem) {
		if (this.diseases == null) {
			this.diseases = new ArrayList<Disease>();
		}
		this.diseases.add(diseasesItem);
		return this;
	}

	/**
	 * Get diseases
	 * @return diseases
	**/
	@ApiModelProperty(value = "")

	@Valid

	public List<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}

	public Donor medicalProcedures(List<MedicalProcedure> medicalProcedures) {
		this.medicalProcedures = medicalProcedures;
		return this;
	}

	public Donor addMedicalProceduresItem(MedicalProcedure medicalProceduresItem) {
		if (this.medicalProcedures == null) {
			this.medicalProcedures = new ArrayList<MedicalProcedure>();
		}
		this.medicalProcedures.add(medicalProceduresItem);
		return this;
	}

	/**
	 * Get medicalProcedures
	 * @return medicalProcedures
	**/
	@ApiModelProperty(value = "")

	@Valid

	public List<MedicalProcedure> getMedicalProcedures() {
		return medicalProcedures;
	}

	public void setMedicalProcedures(List<MedicalProcedure> medicalProcedures) {
		this.medicalProcedures = medicalProcedures;
	}

	public Donor sample(List<Sample> sample) {
		this.sample = sample;
		return this;
	}

	public Donor addSampleItem(Sample sampleItem) {
		if (this.sample == null) {
			this.sample = new ArrayList<Sample>();
		}
		this.sample.add(sampleItem);
		return this;
	}

	/**
	 * Get sample
	 * @return sample
	**/
	@ApiModelProperty(value = "")

	@Valid

	public List<Sample> getSample() {
		return sample;
	}

	public void setSample(List<Sample> sample) {
		this.sample = sample;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Donor donor = (Donor) o;
		return Objects.equals(this.id, donor.id) && Objects.equals(this.donorId, donor.donorId)
				&& Objects.equals(this.relatedId, donor.relatedId) && Objects.equals(this.gender, donor.gender)
				&& Objects.equals(this.birthDate, donor.birthDate)
				&& Objects.equals(this.ethnicGroup, donor.ethnicGroup) && Objects.equals(this.skinTone, donor.skinTone)
				&& Objects.equals(this.hairColor, donor.hairColor)
				&& Objects.equals(this.weightDeclarative, donor.weightDeclarative)
				&& Objects.equals(this.weightMeasured, donor.weightMeasured)
				&& Objects.equals(this.statureDeclarative, donor.statureDeclarative)
				&& Objects.equals(this.statureMeasured, donor.statureMeasured)
				&& Objects.equals(this.eyeColor, donor.eyeColor) && Objects.equals(this.whr, donor.whr)
				&& Objects.equals(this.bmi, donor.bmi) && Objects.equals(this.bloodGroup, donor.bloodGroup)
				&& Objects.equals(this.ci, donor.ci) && Objects.equals(this.hLenght, donor.hLenght)
				&& Objects.equals(this.hBreadth, donor.hBreadth) && Objects.equals(this.fLength, donor.fLength)
				&& Objects.equals(this.fBreadth, donor.fBreadth) && Objects.equals(this.hCirc, donor.hCirc)
				&& Objects.equals(this.nCirc, donor.nCirc)
				&& Objects.equals(this.dactylionHeight, donor.dactylionHeight)
				&& Objects.equals(this.shoulderHeight, donor.shoulderHeight)
				&& Objects.equals(this.lowerLimbLen1, donor.lowerLimbLen1)
				&& Objects.equals(this.owerLimbLen2, donor.owerLimbLen2)
				&& Objects.equals(this.tibialHeight, donor.tibialHeight)
				&& Objects.equals(this.chestCirc, donor.chestCirc) && Objects.equals(this.waistCirc, donor.waistCirc)
				&& Objects.equals(this.handLength, donor.handLength)
				&& Objects.equals(this.handBreadth, donor.handBreadth)
				&& Objects.equals(this.footLength, donor.footLength)
				&& Objects.equals(this.footBreadth, donor.footBreadth)
				&& Objects.equals(this.birthPlace, donor.birthPlace)
				&& Objects.equals(this.residencePlace, donor.residencePlace)
				&& Objects.equals(this.questionnaire, donor.questionnaire)
				&& Objects.equals(this.diseases, donor.diseases)
				&& Objects.equals(this.medicalProcedures, donor.medicalProcedures)
				&& Objects.equals(this.sample, donor.sample);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, donorId, relatedId, gender, birthDate, ethnicGroup, skinTone, hairColor,
				weightDeclarative, weightMeasured, statureDeclarative, statureMeasured, eyeColor, whr, bmi, bloodGroup,
				ci, hLenght, hBreadth, fLength, fBreadth, hCirc, nCirc, dactylionHeight, shoulderHeight, lowerLimbLen1,
				owerLimbLen2, tibialHeight, chestCirc, waistCirc, handLength, handBreadth, footLength, footBreadth,
				birthPlace, residencePlace, questionnaire, diseases, medicalProcedures, sample);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Donor {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    donorId: ").append(toIndentedString(donorId)).append("\n");
		sb.append("    relatedId: ").append(toIndentedString(relatedId)).append("\n");
		sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
		sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
		sb.append("    ethnicGroup: ").append(toIndentedString(ethnicGroup)).append("\n");
		sb.append("    skinTone: ").append(toIndentedString(skinTone)).append("\n");
		sb.append("    hairColor: ").append(toIndentedString(hairColor)).append("\n");
		sb.append("    weightDeclarative: ").append(toIndentedString(weightDeclarative)).append("\n");
		sb.append("    weightMeasured: ").append(toIndentedString(weightMeasured)).append("\n");
		sb.append("    statureDeclarative: ").append(toIndentedString(statureDeclarative)).append("\n");
		sb.append("    statureMeasured: ").append(toIndentedString(statureMeasured)).append("\n");
		sb.append("    eyeColor: ").append(toIndentedString(eyeColor)).append("\n");
		sb.append("    whr: ").append(toIndentedString(whr)).append("\n");
		sb.append("    bmi: ").append(toIndentedString(bmi)).append("\n");
		sb.append("    bloodGroup: ").append(toIndentedString(bloodGroup)).append("\n");
		sb.append("    ci: ").append(toIndentedString(ci)).append("\n");
		sb.append("    hLenght: ").append(toIndentedString(hLenght)).append("\n");
		sb.append("    hBreadth: ").append(toIndentedString(hBreadth)).append("\n");
		sb.append("    fLength: ").append(toIndentedString(fLength)).append("\n");
		sb.append("    fBreadth: ").append(toIndentedString(fBreadth)).append("\n");
		sb.append("    hCirc: ").append(toIndentedString(hCirc)).append("\n");
		sb.append("    nCirc: ").append(toIndentedString(nCirc)).append("\n");
		sb.append("    dactylionHeight: ").append(toIndentedString(dactylionHeight)).append("\n");
		sb.append("    shoulderHeight: ").append(toIndentedString(shoulderHeight)).append("\n");
		sb.append("    lowerLimbLen1: ").append(toIndentedString(lowerLimbLen1)).append("\n");
		sb.append("    owerLimbLen2: ").append(toIndentedString(owerLimbLen2)).append("\n");
		sb.append("    tibialHeight: ").append(toIndentedString(tibialHeight)).append("\n");
		sb.append("    chestCirc: ").append(toIndentedString(chestCirc)).append("\n");
		sb.append("    waistCirc: ").append(toIndentedString(waistCirc)).append("\n");
		sb.append("    handLength: ").append(toIndentedString(handLength)).append("\n");
		sb.append("    handBreadth: ").append(toIndentedString(handBreadth)).append("\n");
		sb.append("    footLength: ").append(toIndentedString(footLength)).append("\n");
		sb.append("    footBreadth: ").append(toIndentedString(footBreadth)).append("\n");
		sb.append("    birthPlace: ").append(toIndentedString(birthPlace)).append("\n");
		sb.append("    residencePlace: ").append(toIndentedString(residencePlace)).append("\n");
		sb.append("    questionnaire: ").append(toIndentedString(questionnaire)).append("\n");
		sb.append("    diseases: ").append(toIndentedString(diseases)).append("\n");
		sb.append("    medicalProcedures: ").append(toIndentedString(medicalProcedures)).append("\n");
		sb.append("    sample: ").append(toIndentedString(sample)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
