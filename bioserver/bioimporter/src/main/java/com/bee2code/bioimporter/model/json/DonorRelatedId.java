package com.bee2code.bioimporter.model.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * DonorRelatedId
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class DonorRelatedId {
	@JsonProperty("parents")
	@Valid
	private List<Integer> parents = null;

	@JsonProperty("siblings")
	@Valid
	private List<Integer> siblings = null;

	@JsonProperty("children")
	@Valid
	private List<Integer> children = null;

	@JsonProperty("spouse")
	@Valid
	private List<Integer> spouse = null;

	public DonorRelatedId parents(List<Integer> parents) {
		this.parents = parents;
		return this;
	}

	public DonorRelatedId addParentsItem(Integer parentsItem) {
		if (this.parents == null) {
			this.parents = new ArrayList<Integer>();
		}
		this.parents.add(parentsItem);
		return this;
	}

	/**
	 * Get parents
	 * @return parents
	**/
	@ApiModelProperty(value = "")

	public List<Integer> getParents() {
		return parents;
	}

	public void setParents(List<Integer> parents) {
		this.parents = parents;
	}

	public DonorRelatedId siblings(List<Integer> siblings) {
		this.siblings = siblings;
		return this;
	}

	public DonorRelatedId addSiblingsItem(Integer siblingsItem) {
		if (this.siblings == null) {
			this.siblings = new ArrayList<Integer>();
		}
		this.siblings.add(siblingsItem);
		return this;
	}

	/**
	 * Get siblings
	 * @return siblings
	**/
	@ApiModelProperty(value = "")

	public List<Integer> getSiblings() {
		return siblings;
	}

	public void setSiblings(List<Integer> siblings) {
		this.siblings = siblings;
	}

	public DonorRelatedId children(List<Integer> children) {
		this.children = children;
		return this;
	}

	public DonorRelatedId addChildrenItem(Integer childrenItem) {
		if (this.children == null) {
			this.children = new ArrayList<Integer>();
		}
		this.children.add(childrenItem);
		return this;
	}

	/**
	 * Get children
	 * @return children
	**/
	@ApiModelProperty(value = "")

	public List<Integer> getChildren() {
		return children;
	}

	public void setChildren(List<Integer> children) {
		this.children = children;
	}

	public DonorRelatedId spouse(List<Integer> spouse) {
		this.spouse = spouse;
		return this;
	}

	public DonorRelatedId addSpouseItem(Integer spouseItem) {
		if (this.spouse == null) {
			this.spouse = new ArrayList<Integer>();
		}
		this.spouse.add(spouseItem);
		return this;
	}

	/**
	 * Get spouse
	 * @return spouse
	**/
	@ApiModelProperty(value = "")

	public List<Integer> getSpouse() {
		return spouse;
	}

	public void setSpouse(List<Integer> spouse) {
		this.spouse = spouse;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DonorRelatedId donorRelatedId = (DonorRelatedId) o;
		return Objects.equals(this.parents, donorRelatedId.parents)
				&& Objects.equals(this.siblings, donorRelatedId.siblings)
				&& Objects.equals(this.children, donorRelatedId.children)
				&& Objects.equals(this.spouse, donorRelatedId.spouse);
	}

	@Override
	public int hashCode() {
		return Objects.hash(parents, siblings, children, spouse);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DonorRelatedId {\n");

		sb.append("    parents: ").append(toIndentedString(parents)).append("\n");
		sb.append("    siblings: ").append(toIndentedString(siblings)).append("\n");
		sb.append("    children: ").append(toIndentedString(children)).append("\n");
		sb.append("    spouse: ").append(toIndentedString(spouse)).append("\n");
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
