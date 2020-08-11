package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * based on MIABIS 2.0 Attribute code:39-44
 */
@ApiModel(description = "based on MIABIS 2.0 Attribute code:39-44")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SampleQMS {
	@JsonProperty("preCT")
	private String preCT = null;

	@JsonProperty("postCT")
	private String postCT = null;

	@JsonProperty("primarycontainer")
	private String primarycontainer = null;

	@JsonProperty("storagetemp")
	private String storagetemp = null;

	@JsonProperty("lid")
	private String lid = null;

	@JsonProperty("markers")
	private String markers = null;

	@JsonProperty("fasting")
	private String fasting = null;

	@JsonProperty("documentation")
	private String documentation = null;

	public SampleQMS preCT(String preCT) {
		this.preCT = preCT;
		return this;
	}

	/**
	 * pre centrifugation time
	 * @return preCT
	**/
	@ApiModelProperty(value = "pre centrifugation time")

	public String getPreCT() {
		return preCT;
	}

	public void setPreCT(String preCT) {
		this.preCT = preCT;
	}

	public SampleQMS postCT(String postCT) {
		this.postCT = postCT;
		return this;
	}

	/**
	 * post centrifugation time
	 * @return postCT
	**/
	@ApiModelProperty(value = "post centrifugation time")

	public String getPostCT() {
		return postCT;
	}

	public void setPostCT(String postCT) {
		this.postCT = postCT;
	}

	public SampleQMS primarycontainer(String primarycontainer) {
		this.primarycontainer = primarycontainer;
		return this;
	}

	/**
	 * list of the most common primary tubes
	 * @return primarycontainer
	**/
	@ApiModelProperty(value = "list of the most common primary tubes")

	public String getPrimarycontainer() {
		return primarycontainer;
	}

	public void setPrimarycontainer(String primarycontainer) {
		this.primarycontainer = primarycontainer;
	}

	public SampleQMS storagetemp(String storagetemp) {
		this.storagetemp = storagetemp;
		return this;
	}

	/**
	 * long storage temperature after preparation
	 * @return storagetemp
	**/
	@ApiModelProperty(value = "long storage temperature after preparation")

	public String getStoragetemp() {
		return storagetemp;
	}

	public void setStoragetemp(String storagetemp) {
		this.storagetemp = storagetemp;
	}

	public SampleQMS lid(String lid) {
		this.lid = lid;
		return this;
	}

	/**
	 * lid tightness control
	 * @return lid
	**/
	@ApiModelProperty(value = "lid tightness control")

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public SampleQMS markers(String markers) {
		this.markers = markers;
		return this;
	}

	/**
	 * measured stable markers (sodium, potassium)
	 * @return markers
	**/
	@ApiModelProperty(value = "measured stable markers (sodium, potassium)")

	public String getMarkers() {
		return markers;
	}

	public void setMarkers(String markers) {
		this.markers = markers;
	}

	public SampleQMS fasting(String fasting) {
		this.fasting = fasting;
		return this;
	}

	/**
	 * if the sample is a fasting sample
	 * @return fasting
	**/
	@ApiModelProperty(value = "if the sample is a fasting sample")

	public String getFasting() {
		return fasting;
	}

	public void setFasting(String fasting) {
		this.fasting = fasting;
	}

	public SampleQMS documentation(String documentation) {
		this.documentation = documentation;
		return this;
	}

	/**
	 * if pre-analysis is documented
	 * @return documentation
	**/
	@ApiModelProperty(value = "if pre-analysis is documented")

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SampleQMS sampleQMS = (SampleQMS) o;
		return Objects.equals(this.preCT, sampleQMS.preCT) && Objects.equals(this.postCT, sampleQMS.postCT)
				&& Objects.equals(this.primarycontainer, sampleQMS.primarycontainer)
				&& Objects.equals(this.storagetemp, sampleQMS.storagetemp) && Objects.equals(this.lid, sampleQMS.lid)
				&& Objects.equals(this.markers, sampleQMS.markers) && Objects.equals(this.fasting, sampleQMS.fasting)
				&& Objects.equals(this.documentation, sampleQMS.documentation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(preCT, postCT, primarycontainer, storagetemp, lid, markers, fasting, documentation);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SampleQMS {\n");

		sb.append("    preCT: ").append(toIndentedString(preCT)).append("\n");
		sb.append("    postCT: ").append(toIndentedString(postCT)).append("\n");
		sb.append("    primarycontainer: ").append(toIndentedString(primarycontainer)).append("\n");
		sb.append("    storagetemp: ").append(toIndentedString(storagetemp)).append("\n");
		sb.append("    lid: ").append(toIndentedString(lid)).append("\n");
		sb.append("    markers: ").append(toIndentedString(markers)).append("\n");
		sb.append("    fasting: ").append(toIndentedString(fasting)).append("\n");
		sb.append("    documentation: ").append(toIndentedString(documentation)).append("\n");
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
