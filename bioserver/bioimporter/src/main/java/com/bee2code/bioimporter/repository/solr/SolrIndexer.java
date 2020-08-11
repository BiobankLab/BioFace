package com.bee2code.bioimporter.repository.solr;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.common.util.ContentStream;
import org.apache.solr.common.util.ContentStreamBase;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.exception.MappingToSolrException;
import com.bee2code.bioimporter.model.json.BMI;
import com.bee2code.bioimporter.model.json.BirthPlace;
import com.bee2code.bioimporter.model.json.BloodGroup;
import com.bee2code.bioimporter.model.json.CI;
import com.bee2code.bioimporter.model.json.ChestCirc;
import com.bee2code.bioimporter.model.json.DactylionHeight;
import com.bee2code.bioimporter.model.json.Donor;
import com.bee2code.bioimporter.model.json.DonorBirthDate;
import com.bee2code.bioimporter.model.json.DonorRelatedId;
import com.bee2code.bioimporter.model.json.EthnicGroup;
import com.bee2code.bioimporter.model.json.EyeColour;
import com.bee2code.bioimporter.model.json.FBreadth;
import com.bee2code.bioimporter.model.json.FLength;
import com.bee2code.bioimporter.model.json.FootBreadth;
import com.bee2code.bioimporter.model.json.FootLength;
import com.bee2code.bioimporter.model.json.HBreadth;
import com.bee2code.bioimporter.model.json.HCirc;
import com.bee2code.bioimporter.model.json.HLenght;
import com.bee2code.bioimporter.model.json.HairColor;
import com.bee2code.bioimporter.model.json.HandBreadth;
import com.bee2code.bioimporter.model.json.HandLength;
import com.bee2code.bioimporter.model.json.LowerLimbLen1;
import com.bee2code.bioimporter.model.json.LowerLimbLen2;
import com.bee2code.bioimporter.model.json.NCirc;
import com.bee2code.bioimporter.model.json.ResidencePlace;
import com.bee2code.bioimporter.model.json.Sample;
import com.bee2code.bioimporter.model.json.SampleInfo;
import com.bee2code.bioimporter.model.json.SampleMaterial;
import com.bee2code.bioimporter.model.json.SampleMaterialFFPE;
import com.bee2code.bioimporter.model.json.SampleMaterialNucleicAcid;
import com.bee2code.bioimporter.model.json.SampleMaterialPlasma;
import com.bee2code.bioimporter.model.json.SampleMaterialSaliva;
import com.bee2code.bioimporter.model.json.SampleMaterialSerum;
import com.bee2code.bioimporter.model.json.SampleMaterialStool;
import com.bee2code.bioimporter.model.json.SampleMaterialTissue;
import com.bee2code.bioimporter.model.json.SampleMaterialUrine;
import com.bee2code.bioimporter.model.json.SampleMaterialWholeBlood;
import com.bee2code.bioimporter.model.json.SampleQMS;
import com.bee2code.bioimporter.model.json.ShoulderHeight;
import com.bee2code.bioimporter.model.json.SkinTone;
import com.bee2code.bioimporter.model.json.StatureDeclarative;
import com.bee2code.bioimporter.model.json.StatureMeasured;
import com.bee2code.bioimporter.model.json.TibialHeight;
import com.bee2code.bioimporter.model.json.WHR;
import com.bee2code.bioimporter.model.json.WaistCirc;
import com.bee2code.bioimporter.model.json.WeightDeclarative;
import com.bee2code.bioimporter.model.json.WeightMeasured;
import com.bee2code.bioimporter.model.solr.SampleSolr;
import com.bee2code.bioimporter.model.solr.SampleSolr.SampleSolrBuilder;
import com.bee2code.bioimporter.repository.mongo.MongoRepository;
import com.bee2code.tools.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolrIndexer {

	private SolrClient solrClient;
	private List<Donor> donorsList;
	private MongoRepository mongoRepository;
	private static final String SAMPLE_ID_FIELD = "sampleId";
	private static final String COLLECTION_FIELD = "collection";
	private static final String BIOBANK_FIELD = "biobank";

	@SuppressWarnings("unused")
	private SolrIndexer() {
	}

	public SolrIndexer(Configuration configuration, List<Donor> donorsList, MongoRepository mongoRepository) {
		this.solrClient = new HttpSolrClient.Builder(configuration.getSolr().getUrl()).build();
		this.donorsList = donorsList;
		this.mongoRepository = mongoRepository;
	}

	public void index() throws Exception {
		try {
			for (Donor donor : donorsList) {
				ObjectMapper mapper = Utils.getMapperWithDateFormat();
				List<SampleSolr> solrSamples = mapToDonorSolr(donor);

				solrSamples.forEach(sample -> {
					String deleteQuery = SAMPLE_ID_FIELD + ":" + "\"" + sample.getSampleId() + "\" AND "
							+ COLLECTION_FIELD + ": " + "\"" + sample.getCollection() + "\"" + " AND " + BIOBANK_FIELD
							+ ": \"" + sample.getBiobank() + "\"";
					try {
						solrClient.deleteByQuery(deleteQuery.toString());

					} catch (Exception e) {
						log.error("Error while removing sample" + sample.toString(), e);
						throw new RuntimeException("Error while removing sample with id: " + sample.getSampleId());
					}

				});

				String donorSolrJson = mapper.writeValueAsString(solrSamples);
				ContentStreamUpdateRequest updateRequest = new ContentStreamUpdateRequest("/update/json/docs");
				ContentStream contentStream = new ContentStreamBase.StringStream(donorSolrJson);
				updateRequest.addContentStream(contentStream);
				updateRequest.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
				solrClient.commit();
				solrClient.request(updateRequest);
			}
		} catch (MappingToSolrException mtse) {
			log.error("Mapping to solr error" + mtse.getMessage());
			throw mtse;
		} catch (Exception e) {
			log.error("Error while indexing donors", e);
			throw new RuntimeException("Error while indexing donors");
		}

	}

	/**
	 * Mapping Donor object getted from JSON to fit solr schema format for future
	 * indexing
	 * 
	 * @param donor
	 * @return
	 * @throws Exception
	 */
	private List<SampleSolr> mapToDonorSolr(Donor donor) throws Exception {

		try {

			List<SampleSolr> resultSampleList = new ArrayList<>();

			SampleSolrBuilder sampleSolrBuilder = SampleSolr.builder();

			/**
			 * Mapping main donor info
			 */

			sampleSolrBuilder.donorId(donor.getDonorId());

			// parse related id's
			DonorRelatedId donorRelatedId = donor.getRelatedId();
			if (donorRelatedId != null) {
				sampleSolrBuilder.relatedIdChildren(donorRelatedId.getChildren());
				sampleSolrBuilder.relatedIdParents(donorRelatedId.getParents());
				sampleSolrBuilder.relatedIdSiblings(donorRelatedId.getSiblings());
				sampleSolrBuilder.relatedIdSpouse(donorRelatedId.getSpouse());
			}

			sampleSolrBuilder.gender(donor.getGender().toString());

			/* Birth date */
			if (donor.getBirthDate() != null) {
				DonorBirthDate birthDate = donor.getBirthDate();
				sampleSolrBuilder.birthDate(birthDate.getDate());
				sampleSolrBuilder.birthDateCorrectess(birthDate.getDateCorrectess());
				if (birthDate.getDataAvailability() != null) {
					sampleSolrBuilder.birthDateDataAvailability(birthDate.getDataAvailability().toString());
				}
			}

			/* Ethnic group */
			if (donor.getEthnicGroup() != null) {
				EthnicGroup ethnicGroup = donor.getEthnicGroup();
				if (ethnicGroup.getDataAvailability() != null) {
					sampleSolrBuilder.ethnicGroupDataAvailability(ethnicGroup.getDataAvailability().toString());
				}
				sampleSolrBuilder.ethnicGroupEgName(ethnicGroup.getEgName().toString());
			}

			/* Skin tone */
			if (donor.getSkinTone() != null) {
				SkinTone skinTone = donor.getSkinTone();
				if (skinTone.getDataAvailability() != null) {
					sampleSolrBuilder.skinToneDataAvailability(skinTone.getDataAvailability().toString());
				}
				sampleSolrBuilder.skinToneVonLuschan(skinTone.getVonLuschan());
				sampleSolrBuilder.skinToneMichalski(skinTone.getMichalski());

				if (skinTone.getCIELAB() != null) {
					sampleSolrBuilder.skinToneCielabA(skinTone.getCIELAB().getA());
					sampleSolrBuilder.skinToneCielabB(skinTone.getCIELAB().getB());
					sampleSolrBuilder.skinToneCielabL(skinTone.getCIELAB().getL());
				}

				if (skinTone.getTimestamp() != null) {
					sampleSolrBuilder.skinToneTimestamp(skinTone.getTimestamp().getDate());
					sampleSolrBuilder.skinToneTimestampCorrectess(skinTone.getTimestamp().getDateCorrectess());
				}
			}

			/* Hair color */
			if (donor.getHairColor() != null) {
				HairColor hairColor = donor.getHairColor();
				if (hairColor.getDataAvailability() != null) {
					sampleSolrBuilder.hairColorDataAvailability(hairColor.getDataAvailability().toString());
				}
				sampleSolrBuilder.hairColorHcPalet(hairColor.getHcPalet());
				sampleSolrBuilder.hairColorFisherSaller(hairColor.getFisherSaller().toString());
				sampleSolrBuilder.hairColorDescriptive(hairColor.getDescriptive());

				if (donor.getHairColor().getCIELAB() != null) {
					sampleSolrBuilder.hairColorCielabA(hairColor.getCIELAB().getA());
					sampleSolrBuilder.hairColorCielabB(hairColor.getCIELAB().getB());
					sampleSolrBuilder.hairColorCielabL(hairColor.getCIELAB().getL());
				}

				if (donor.getHairColor().getTimestamp() != null) {
					sampleSolrBuilder.hairColorTimestamp(donor.getHairColor().getTimestamp().getDate());
					sampleSolrBuilder
							.hairColorTimestampCorrectess(donor.getHairColor().getTimestamp().getDateCorrectess());
				}
			}

			/* Weight Declarative */
			if (donor.getWeightDeclarative() != null) {
				WeightDeclarative weightDeclarative = donor.getWeightDeclarative();
				if (weightDeclarative.getDataAvailability() != null) {
					sampleSolrBuilder
							.weightDeclarativeDataAvailability(weightDeclarative.getDataAvailability().toString());
				}
				sampleSolrBuilder.weightDeclarative(weightDeclarative.getWeight());
				if (weightDeclarative.getTimestamp() != null) {
					sampleSolrBuilder.weightDeclarativeTimestamp(weightDeclarative.getTimestamp().getDate());
					sampleSolrBuilder
							.weightDeclarativeTimestampCorrectess(weightDeclarative.getTimestamp().getDateCorrectess());
				}
			}

			/* Weight measured */
			if (donor.getWeightMeasured() != null) {
				WeightMeasured weightMeasured = donor.getWeightMeasured();
				if (weightMeasured.getDataAvailability() != null) {
					sampleSolrBuilder.weightMeasuredDataAvailability(weightMeasured.getDataAvailability().toString());
				}
				sampleSolrBuilder.weightMeasured(weightMeasured.getWeight());
				if (weightMeasured.getTimestamp() != null) {
					sampleSolrBuilder.weightMeasuredTimestamp(weightMeasured.getTimestamp().getDate());
					sampleSolrBuilder
							.weightMeasuredTimestampCorrectess(weightMeasured.getTimestamp().getDateCorrectess());
				}
			}

			/* Stature declarative */
			if (donor.getStatureDeclarative() != null) {
				StatureDeclarative statureDeclarative = donor.getStatureDeclarative();
				if (statureDeclarative.getDataAvailability() != null) {
					sampleSolrBuilder
							.statureDeclarativeDataAvailability(statureDeclarative.getDataAvailability().toString());
				}
				sampleSolrBuilder.statureDeclarative(statureDeclarative.getStature());
				if (statureDeclarative.getTimestamp() != null) {
					sampleSolrBuilder.statureDeclarativeTimestamp(statureDeclarative.getTimestamp().getDate());
					sampleSolrBuilder.statureDeclarativeTimestampCorrectess(
							statureDeclarative.getTimestamp().getDateCorrectess());
				}
			}

			/* Stature measured */
			if (donor.getStatureMeasured() != null) {
				StatureMeasured statureMeasured = donor.getStatureMeasured();
				if (statureMeasured.getDataAvailability() != null) {
					sampleSolrBuilder.statureMeasuredDataAvailability(statureMeasured.getDataAvailability().toString());
				}
				sampleSolrBuilder.statureMeasured(statureMeasured.getStature());
				if (statureMeasured.getTimestamp() != null) {
					sampleSolrBuilder.statureMeasuredTimestamp(statureMeasured.getTimestamp().getDate());
					sampleSolrBuilder
							.statureMeasuredTimestampCorrectess(statureMeasured.getTimestamp().getDateCorrectess());
				}
			}

			/* Eye color */
			if (donor.getEyeColor() != null) {
				EyeColour eyeColor = donor.getEyeColor();
				if (eyeColor.getDataAvailability() != null) {
					sampleSolrBuilder.eyeColorDataAvailability(eyeColor.getDataAvailability().toString());
				}
				sampleSolrBuilder.eyeColorMartinSchultz(eyeColor.getMartinSchultz());
				sampleSolrBuilder.eyeColorMichalski(eyeColor.getMichalski());
				if (eyeColor.getTimestamp() != null) {
					sampleSolrBuilder.eyeColorTimestamp(eyeColor.getTimestamp().getDate());
					sampleSolrBuilder.eyeColorTimestampCorrectess(eyeColor.getTimestamp().getDateCorrectess());
				}
			}

			/* Whr */
			if (donor.getWhr() != null) {
				WHR whr = donor.getWhr();

				if (whr.getDataAvailability() != null) {
					sampleSolrBuilder.whrDataAvailability(whr.getDataAvailability().toString());
				}

				sampleSolrBuilder.whr(whr.getWHR());
				if (whr.getTimestamp() != null) {
					sampleSolrBuilder.whrTimestamp(whr.getTimestamp().getDate());
					sampleSolrBuilder.whrTimestampCorrectess(whr.getTimestamp().getDateCorrectess());
				}
			}

			/* BMI */
			if (donor.getBmi() != null) {
				BMI bmi = donor.getBmi();
				if (bmi.getDataAvailability() != null) {
					sampleSolrBuilder.bmiDataAvailability(bmi.getDataAvailability().toString());
				}
				sampleSolrBuilder.bmi(bmi.getBMI());
				if (bmi.getTimestamp() != null) {
					sampleSolrBuilder.bmiTimestamp(bmi.getTimestamp().getDate());
					sampleSolrBuilder.bmiTimestampCorrectess(bmi.getTimestamp().getDateCorrectess());
				}
			}

			/* Blood group */
			if (donor.getBloodGroup() != null) {
				BloodGroup bloodGroup = donor.getBloodGroup();

				if (bloodGroup.getDataAvailability() != null) {
					sampleSolrBuilder.bloodGroupDataAvailability(bloodGroup.getDataAvailability().toString());
				}

				if (bloodGroup.getABO() != null) {
					sampleSolrBuilder.bloodGroupAB0(bloodGroup.getABO().toString());
				}
				if (bloodGroup.getRh() != null) {
					sampleSolrBuilder.bloodGroupRh(bloodGroup.getRh().toString());

				}
				if (bloodGroup.getLewis() != null) {
					sampleSolrBuilder.bloodGroupLewis(bloodGroup.getLewis().toString());

				}
				if (bloodGroup.getKell() != null) {
					sampleSolrBuilder.bloodGroupKell(bloodGroup.getKell().toString());

				}

				sampleSolrBuilder.bloodGroupOther(bloodGroup.getOther());
			}

			/* CI */
			if (donor.getCi() != null) {
				CI ci = donor.getCi();
				if (ci.getDataAvailability() != null) {
					sampleSolrBuilder.ciDataAvailability(ci.getDataAvailability().toString());
				}
				sampleSolrBuilder.ci(ci.getCI());
				if (ci.getTimestamp() != null) {
					sampleSolrBuilder.ciTimestamp(ci.getTimestamp().getDate());
					sampleSolrBuilder.ciTimestampCorrectess(ci.getTimestamp().getDateCorrectess());
				}
			}

			/* H length */
			if (donor.getHLenght() != null) {
				HLenght hLenght = donor.getHLenght();
				if (hLenght.getDataAvailability() != null) {
					sampleSolrBuilder.headLengthDataAvailability(hLenght.getDataAvailability().toString());
				}

				sampleSolrBuilder.headLength(hLenght.getHLen());

				if (hLenght.getTimestamp() != null) {
					sampleSolrBuilder.headLengthTimestamp(hLenght.getTimestamp().getDate());
					sampleSolrBuilder.headLengthTimestampCorrectess(hLenght.getTimestamp().getDateCorrectess());
				}
			}

			/* H breadth */
			if (donor.getHBreadth() != null) {
				HBreadth hbreadth = donor.getHBreadth();
				if (hbreadth.getDataAvailability() != null) {
					sampleSolrBuilder.headBreadthDataAvailability(hbreadth.getDataAvailability().toString());
				}
				sampleSolrBuilder.handBreadth(hbreadth.getHBre());

				if (hbreadth.getTimestamp() != null) {
					sampleSolrBuilder.headBreadthTimestamp(hbreadth.getTimestamp().getDate());
					sampleSolrBuilder.headBreadthTimestampCorrectess(hbreadth.getTimestamp().getDateCorrectess());
				}
			}

			/* F length */
			if (donor.getFLength() != null) {
				FLength fLength = donor.getFLength();

				if (fLength.getDataAvailability() != null) {
					sampleSolrBuilder.faceLengthDataAvailability(fLength.getDataAvailability().toString());
				}
				sampleSolrBuilder.faceLength(fLength.getFLen());

				if (fLength.getTimestamp() != null) {
					sampleSolrBuilder.faceLengthTimestamp(fLength.getTimestamp().getDate());
					sampleSolrBuilder.faceLengthTimestampCorrectess(fLength.getTimestamp().getDateCorrectess());
				}
			}

			/* F breadth */
			if (donor.getFBreadth() != null) {
				FBreadth fBreadth = donor.getFBreadth();

				if (fBreadth.getDataAvailability() != null) {
					sampleSolrBuilder.faceBreadthDataAvailability(fBreadth.getDataAvailability().toString());
				}
				sampleSolrBuilder.faceBreadth(fBreadth.getFBre());

				if (fBreadth.getTimestamp() != null) {
					sampleSolrBuilder.faceBreadthTimestamp(fBreadth.getTimestamp().getDate());
					sampleSolrBuilder.faceBreadthTimestampCorrectess(fBreadth.getTimestamp().getDateCorrectess());
				}
			}

			/* h circ */
			if (donor.getHCirc() != null) {
				HCirc hCirc = donor.getHCirc();

				if (hCirc.getDataAvailability() != null) {
					sampleSolrBuilder.headCircumferenceDataAvailability(hCirc.getDataAvailability().toString());
				}

				sampleSolrBuilder.headCircumference(hCirc.getHCirc());

				if (hCirc.getTimestamp() != null) {
					sampleSolrBuilder.headCircumferenceTimestamp(hCirc.getTimestamp().getDate());
					sampleSolrBuilder.headCircumferenceTimestampCorrectess(hCirc.getTimestamp().getDateCorrectess());
				}
			}

			/* n circ */
			if (donor.getNCirc() != null) {
				NCirc nCirc = donor.getNCirc();

				if (nCirc.getDataAvailability() != null) {
					sampleSolrBuilder.neckCircumferenceDataAvailability(nCirc.getDataAvailability().toString());
				}

				sampleSolrBuilder.neckCircumference(nCirc.getNCirc());

				if (nCirc.getTimestamp() != null) {
					sampleSolrBuilder.neckCircumferenceTimestamp(nCirc.getTimestamp().getDate());
					sampleSolrBuilder.neckCircumferenceTimestampCorrectess(nCirc.getTimestamp().getDateCorrectess());
				}
			}

			/* Dactylion height */
			if (donor.getDactylionHeight() != null) {
				DactylionHeight dactylionHeight = donor.getDactylionHeight();

				if (dactylionHeight.getDataAvailability() != null) {
					sampleSolrBuilder.dactylionHeightDataAvailability(dactylionHeight.getDataAvailability().toString());
				}

				sampleSolrBuilder.dactylionHeight(dactylionHeight.getDaHeight());

				if (dactylionHeight.getTimestamp() != null) {
					sampleSolrBuilder.dactylionHeightTimestamp(dactylionHeight.getTimestamp().getDate());
					sampleSolrBuilder
							.dactylionHeightTimestampCorrectess(dactylionHeight.getTimestamp().getDateCorrectess());
				}
			}

			/* Shoulder height */
			if (donor.getShoulderHeight() != null) {
				ShoulderHeight shoulderHeight = donor.getShoulderHeight();

				if (shoulderHeight.getDataAvailability() != null) {
					sampleSolrBuilder.shoulderHeightDataAvailability(shoulderHeight.getDataAvailability().toString());
				}

				sampleSolrBuilder.shoulderHeight(shoulderHeight.getEHeight());

				if (shoulderHeight.getTimestamp() != null) {
					sampleSolrBuilder.shoulderHeightTimestamp(shoulderHeight.getTimestamp().getDate());
					sampleSolrBuilder
							.shoulderHeightTimestampCorrectess(shoulderHeight.getTimestamp().getDateCorrectess());
				}
			}

			/* Lower limb len 1 */
			if (donor.getLowerLimbLen1() != null) {
				LowerLimbLen1 lowerLimbLen1 = donor.getLowerLimbLen1();

				if (lowerLimbLen1.getDataAvailability() != null) {
					sampleSolrBuilder.lowerLimbLength1DataAvailability(lowerLimbLen1.getDataAvailability().toString());
				}

				sampleSolrBuilder.lowerLimbLength1(lowerLimbLen1.getLll1());

				if (lowerLimbLen1.getTimestamp() != null) {
					sampleSolrBuilder.lowerLimbLength1Timestamp(lowerLimbLen1.getTimestamp().getDate());
					sampleSolrBuilder
							.lowerLimbLength1TimestampCorrectess(lowerLimbLen1.getTimestamp().getDateCorrectess());
				}
			}

			/* Lower limb len 2 */
			if (donor.getOwerLimbLen2() != null) {
				LowerLimbLen2 lowerLimbLen2 = donor.getOwerLimbLen2();

				if (lowerLimbLen2.getDataAvailability() != null) {
					sampleSolrBuilder.lowerLimbLength2DataAvailability(lowerLimbLen2.getDataAvailability().toString());
				}

				sampleSolrBuilder.lowerLimbLength2(lowerLimbLen2.getLll2());

				if (lowerLimbLen2.getTimestamp() != null) {
					sampleSolrBuilder.lowerLimbLength2Timestamp(lowerLimbLen2.getTimestamp().getDate());
					sampleSolrBuilder
							.lowerLimbLength2TimestampCorrectess(lowerLimbLen2.getTimestamp().getDateCorrectess());

				}
			}

			/* Tibial height */
			if (donor.getTibialHeight() != null) {
				TibialHeight tibialHeight = donor.getTibialHeight();

				if (tibialHeight.getDataAvailability() != null) {
					sampleSolrBuilder.tibialHeightDataAvailability(tibialHeight.getDataAvailability().toString());
				}

				sampleSolrBuilder.tibialHeight(tibialHeight.getTHeight());

				if (tibialHeight.getTimestamp() != null) {
					sampleSolrBuilder.tibialHeightTimestamp(tibialHeight.getTimestamp().getDate());
					sampleSolrBuilder.tibialHeightTimestampCorrectess(tibialHeight.getTimestamp().getDateCorrectess());
				}

			}

			/* Chest circ */
			if (donor.getChestCirc() != null) {
				ChestCirc chestCirc = donor.getChestCirc();

				if (chestCirc.getDataAvailability() != null) {
					sampleSolrBuilder.chestCircumferenceDataAvailability(chestCirc.getDataAvailability().toString());
				}

				sampleSolrBuilder.chestCircumference(chestCirc.getCCirc());

				if (chestCirc.getTimestamp() != null) {
					sampleSolrBuilder.chestCircumferenceTimestamp(chestCirc.getTimestamp().getDate());
					sampleSolrBuilder
							.chestCircumferenceTimestampCorrectess(chestCirc.getTimestamp().getDateCorrectess());
				}
			}

			/* Waist circ */
			if (donor.getWaistCirc() != null) {
				WaistCirc waistCirc = donor.getWaistCirc();

				if (waistCirc.getDataAvailability() != null) {
					sampleSolrBuilder.waistCircumferenceDataAvailability(waistCirc.getDataAvailability().toString());
				}

				sampleSolrBuilder.waistCircumference(waistCirc.getWCirc());

				if (waistCirc.getTimestamp() != null) {
					sampleSolrBuilder.waistCircumferenceTimestamp(waistCirc.getTimestamp().getDate());
					sampleSolrBuilder
							.waistCircumferenceTimestampCorrectess(waistCirc.getTimestamp().getDateCorrectess());
				}
			}

			/* Hand length */
			if (donor.getHandLength() != null) {
				HandLength handLength = donor.getHandLength();

				if (handLength.getDataAvailability() != null) {
					sampleSolrBuilder.handLengthDataAvailability(handLength.getDataAvailability().toString());
				}

				sampleSolrBuilder.handLength(handLength.getHLen());

				if (handLength.getTimestamp() != null) {
					sampleSolrBuilder.handLengthTimestamp(handLength.getTimestamp().getDate());
					sampleSolrBuilder.handLengthTimestampCorrectess(handLength.getTimestamp().getDateCorrectess());
				}
			}

			/* Hand breadth */
			if (donor.getHandBreadth() != null) {
				HandBreadth handBreadth = donor.getHandBreadth();

				if (handBreadth.getDataAvailability() != null) {
					sampleSolrBuilder.handBreadthDataAvailability(handBreadth.getDataAvailability().toString());
				}

				sampleSolrBuilder.handBreadth(handBreadth.getHBre());

				if (handBreadth.getTimestamp() != null) {
					sampleSolrBuilder.handBreadthTimestamp(handBreadth.getTimestamp().getDate());
					sampleSolrBuilder.handBreadthTimestampCorrectess(handBreadth.getTimestamp().getDateCorrectess());
				}
			}

			/* Foot length */
			if (donor.getFootLength() != null) {
				FootLength footLength = donor.getFootLength();

				if (footLength.getDataAvailability() != null) {
					sampleSolrBuilder.footLengthDataAvailability(footLength.getDataAvailability().toString());
				}

				sampleSolrBuilder.footLength(footLength.getFLen());

				if (footLength.getTimestamp() != null) {
					sampleSolrBuilder.footLengthTimestamp(footLength.getTimestamp().getDate());
					sampleSolrBuilder.footLengthTimestampCorrectess(footLength.getTimestamp().getDateCorrectess());
				}
			}

			/* Foot breadth */
			if (donor.getFootBreadth() != null) {
				FootBreadth footBreadth = donor.getFootBreadth();

				if (footBreadth.getDataAvailability() != null) {
					sampleSolrBuilder.footBreadthDataAvailability(footBreadth.getDataAvailability().toString());
				}

				sampleSolrBuilder.footBreadth(footBreadth.getFBre());

				if (footBreadth.getTimestamp() != null) {
					sampleSolrBuilder.footBreadthTimestamp(footBreadth.getTimestamp().getDate());
					sampleSolrBuilder.footBreadthTimestampCorrectess(footBreadth.getTimestamp().getDateCorrectess());
				}
			}

			/* Birth place */
			if (donor.getBirthPlace() != null) {
				BirthPlace birthPlace = donor.getBirthPlace();

				if (birthPlace.getDataAvailability() != null) {
					sampleSolrBuilder.birthPlaceDataAvailability(birthPlace.getDataAvailability().toString());
				}

				sampleSolrBuilder.birthPlace(birthPlace.getBP());
			}

			/* Residence place */
			if (donor.getResidencePlace() != null) {
				ResidencePlace residencePlace = donor.getResidencePlace();

				if (residencePlace.getDataAvailability() != null) {
					sampleSolrBuilder.residencePlaceDataAvailability(residencePlace.getDataAvailability().toString());
				}

				sampleSolrBuilder.residencePlace(residencePlace.getRP());

				if (residencePlace.getTimestamp() != null) {
					sampleSolrBuilder.residencePlaceTimestamp(residencePlace.getTimestamp().getDate());
					sampleSolrBuilder
							.residencePlaceTimestampCorrectess(residencePlace.getTimestamp().getDateCorrectess());
				}
			}

			/* Questionnaire */
			if (donor.getQuestionnaire() != null) {
				sampleSolrBuilder.questionnaire(donor.getQuestionnaire().toString());
			}

			/**
			 * Mapping diseases
			 */
			if (donor.getDiseases() != null) {
				List<String> diseasesDataAvailability = new ArrayList<>();
				List<String> diseasesIcd10 = new ArrayList<>();
				List<String> diseasesIcd10Str = new ArrayList<>();
				List<String> diseasesNoCode = new ArrayList<>();
				List<Integer> diseasesSnomed = new ArrayList<>();
				List<OffsetDateTime> diseasesTimestamp = new ArrayList<>();
				List<String> diseasesTimestampCorrectess = new ArrayList<>();
				List<String> diseasesTypes = new ArrayList<>();
				donor.getDiseases().forEach(disease -> {
					if (disease.getDataAvailability() != null) {
						diseasesDataAvailability.add(disease.getDataAvailability().toString());
					}
					if (disease.getIcd10() != null && !disease.getIcd10().isEmpty()) {
						diseasesIcd10.add(disease.getIcd10());
						diseasesTypes.add(mongoRepository.getIcd10MappingFirstLevel(disease.getIcd10()));
						diseasesIcd10Str.add(mongoRepository.getIcd10SecondLevelMapping(disease.getIcd10()));
					}
					if (disease.getNoCode() != null) {
						diseasesNoCode.add(disease.getNoCode());
					}
					if (disease.getSnomed() != null) {
						diseasesSnomed.add(disease.getSnomed());
					}
					if (disease.getTimestamp() != null) {
						if (disease.getTimestamp().getDate() != null) {
							diseasesTimestamp.add(disease.getTimestamp().getDate());
						}
						if (disease.getTimestamp().getDateCorrectess() != null
								&& !disease.getTimestamp().getDateCorrectess().isEmpty()) {
							diseasesTimestampCorrectess.add(disease.getTimestamp().getDateCorrectess());
						}
					}
				});

				sampleSolrBuilder.diseasesDataAvailability(diseasesDataAvailability);
				sampleSolrBuilder.diseasesIcd10(diseasesIcd10);
				sampleSolrBuilder.diseasesIcd10Str(diseasesIcd10Str);
				sampleSolrBuilder.diseasesNoCode(diseasesNoCode);
				sampleSolrBuilder.diseasesSnomed(diseasesSnomed);
				sampleSolrBuilder.diseasesTimestamp(diseasesTimestamp);
				sampleSolrBuilder.diseasesTimestampCorrectess(diseasesTimestampCorrectess);
				sampleSolrBuilder.diseasesTypeStr(diseasesTypes);

			}

			/**
			 * Mapping medical procedures
			 */

			if (donor.getMedicalProcedures() != null) {
				List<String> medicalProceduresDataAvailability = new ArrayList<>();
				List<String> medicalProceduresIcd9 = new ArrayList<>();
				List<String> medicalProceduresNoCode = new ArrayList<>();
				List<Integer> medicalProceduresSnomed = new ArrayList<>();
				List<OffsetDateTime> medicalProceduresTimestamp = new ArrayList<>();
				List<String> medicalProceduresTimestampCorrectess = new ArrayList<>();
				donor.getMedicalProcedures().forEach(mp -> {
					if (mp.getDataAvailability() != null) {
						medicalProceduresDataAvailability.add(mp.getDataAvailability().toString());
					}
					if (mp.getIcd9() != null) {
						medicalProceduresIcd9.add(mp.getIcd9());
					}
					if (mp.getNoCode() != null) {
						medicalProceduresNoCode.add(mp.getNoCode());
					}
					if (mp.getSnomed() != null) {
						medicalProceduresSnomed.add(mp.getSnomed());
					}
					if (mp.getTimestamp() != null) {
						if (mp.getTimestamp().getDate() != null) {
							medicalProceduresTimestamp.add(mp.getTimestamp().getDate());
						}
						if (mp.getTimestamp().getDateCorrectess() != null
								&& !mp.getTimestamp().getDateCorrectess().isEmpty()) {
							medicalProceduresTimestampCorrectess.add(mp.getTimestamp().getDateCorrectess());
						}
					}
				});

				sampleSolrBuilder.medicalProceduresDataAvailability(medicalProceduresDataAvailability);
				sampleSolrBuilder.medicalProceduresIcd9(medicalProceduresIcd9);
				sampleSolrBuilder.medicalProceduresNoCode(medicalProceduresNoCode);
				sampleSolrBuilder.medicalProceduresSnomed(medicalProceduresSnomed);
				sampleSolrBuilder.medicalProceduresTimestamp(medicalProceduresTimestamp);
				sampleSolrBuilder.medicalProceduresTimestampCorrectess(medicalProceduresTimestampCorrectess);
			}

			/**
			 * Mapping sample main info
			 */

			for (Sample sample : donor.getSample()) {
				SampleSolr sampleSolr = sampleSolrBuilder.build();
				sampleSolr.setTimestamp(OffsetDateTime.now());
				if (sample.getSampleInfo() != null) {
					SampleInfo sampleInfo = sample.getSampleInfo();
					sampleSolr.setSampleId(sampleInfo.getSampleId());
					sampleSolr.setParentSampleId(sampleInfo.getParentSampleId());
					if (sampleInfo.getAccesion() != null) {
						sampleSolr.setAccession(sampleInfo.getAccesion().toString());
					}
					sampleSolr.setCollection(sampleInfo.getCollection());
					sampleSolr.setBiobank(sampleInfo.getBiobank());
					sampleSolr.setOwner(sampleInfo.getOwner());
					sampleSolr.setComments(sampleInfo.getComments());
				}

				if (sample.getMaterialType() != null) {
					sampleSolr.setSampleMaterialType(sample.getMaterialType().toString());
					sampleSolr.setSampleMaterialTypeStr(sample.getMaterialType().toString());
				}
				if (sample.getStorageTemperature() != null) {
					sampleSolr.setSampleStorageTemperature(sample.getStorageTemperature().toString());
				}
				// Material
				if (sample.getSampleMaterial() != null) {
					SampleMaterial material = sample.getSampleMaterial();
					if (material.getTissue() != null) {
						SampleMaterialTissue tissue = material.getTissue();
						sampleSolr.setSampleMaterialTissueSource(tissue.getSource());
						sampleSolr.setSampleMaterialTissueKind(tissue.getKind());
						sampleSolr.setSampleMaterialTissueMethod(tissue.getMethod());
					}
					if (material.getWholeBlood() != null) {
						SampleMaterialWholeBlood wholeBlood = material.getWholeBlood();
						if (wholeBlood.getMethod() != null) {
							sampleSolr.setSampleMaterialWholeBloodMethod(wholeBlood.getMethod().toString());
						}
					}

					if (material.getSerum() != null) {
						SampleMaterialSerum serum = material.getSerum();
						sampleSolr.setSampleMaterialSerumInfo(serum.getInfo());
					}

					if (material.getPlasma() != null) {
						SampleMaterialPlasma plasma = material.getPlasma();
						sampleSolr.setSampleMaterialPlasmaInfo(plasma.getInfo());
					}
					// TODO: check, something wrong with model (mapped to object? )
					if (material.getBuffyCoat() != null) {

					}

					if (material.getFFPE() != null) {
						SampleMaterialFFPE ffpe = material.getFFPE();
						sampleSolr.setSampleMaterialFfpeMethod(ffpe.getMethod());
						sampleSolr.setSampleMaterialFfpeKind(ffpe.getKind());
						sampleSolr.setSampleMaterialFfpeSource(ffpe.getSource());
					}

					if (material.getUrine() != null) {
						SampleMaterialUrine urine = material.getUrine();
						sampleSolr.setSampleMaterialUrineInfo(urine.getInfo());
					}

					if (material.getSaliva() != null) {
						SampleMaterialSaliva saliva = material.getSaliva();
						sampleSolr.setSampleMaterialSalivaInfo(saliva.getInfo());
						sampleSolr.setSampleMaterialSalivaMethod(saliva.getMethods());
					}

					if (material.getStool() != null) {
						SampleMaterialStool stool = material.getStool();
						sampleSolr.setSampleMaterialStoolInfo(stool.getInfo());
					}

					if (material.getNucleicAcid() != null) {
						SampleMaterialNucleicAcid nucleicAcid = material.getNucleicAcid();
						if (nucleicAcid.getKind() != null) {
							sampleSolr.setSampleMaterialNucleidAcidKind(nucleicAcid.getKind().toString());
						}
						sampleSolr.setSampleMaterialNucleidAcidMethod(nucleicAcid.getMethod());
						sampleSolr.setSampleMaterialNucleidAcidSource(nucleicAcid.getSource());
					}

					sampleSolr.setSampleMaterialOther(material.getOther());

				}

				// SampleQMS
				if (sample.getSampleQMS() != null) {
					SampleQMS sampleQMS = sample.getSampleQMS();
					sampleSolr.setSampleQmsPreCt(sampleQMS.getPreCT());
					sampleSolr.setSampleQmsPostCt(sampleQMS.getPostCT());
					sampleSolr.setSampleQmsPrimaryContainer(sampleQMS.getPrimarycontainer());
					sampleSolr.setSampleQmsStorageTemperature(sampleQMS.getStoragetemp());
					sampleSolr.setSampleQmsLid(sampleQMS.getLid());
					sampleSolr.setSampleQmsMarkers(sampleQMS.getMarkers());
					sampleSolr.setSampleQmsFasting(sampleQMS.getFasting());
					sampleSolr.setSampleQmsDocumentation(sampleQMS.getDocumentation());
				}
				resultSampleList.add(sampleSolr);
			}

			return resultSampleList;
		} catch (Exception e) {
			log.error("Error while processing solr mapping", e);
			throw new MappingToSolrException("Error while processing solr mapping");
		}

	}

}
