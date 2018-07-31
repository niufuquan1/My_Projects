package net.chenlin.dp.modules.kdecm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set; 

public class LiteratureSimilarityEntity implements Serializable{

	private String literatureSimPercent;
	private Set<String> sameWords;
	private String postilSimPercent;
	private int literatureId1;
	private int literatureId2;
	
	
	public int getLiteratureId1() {
		return literatureId1;
	}
	public void setLiteratureId1(int literatureId1) {
		this.literatureId1 = literatureId1;
	}
	public int getLiteratureId2() {
		return literatureId2;
	}
	public void setLiteratureId2(int literatureId2) {
		this.literatureId2 = literatureId2;
	}
	public String getPostilSimPercent() {
		return postilSimPercent;
	}
	public void setPostilSimPercent(String postilSimPercent) {
		this.postilSimPercent = postilSimPercent;
	}
	public String getLiteratrueSimPercent() {
		return literatureSimPercent;
	}
	public void setLiteratureSimPercent(String literatureSimPercent) {
		this.literatureSimPercent = literatureSimPercent;
	}
	public Set<String> getSameWords() {
		return sameWords;
	}
	public void setSameWords(Set<String> sameWords) {
		this.sameWords = sameWords;
	}
	
}
