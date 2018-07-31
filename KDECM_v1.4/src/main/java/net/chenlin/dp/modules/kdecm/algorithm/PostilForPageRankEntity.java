package net.chenlin.dp.modules.kdecm.algorithm;

import java.util.ArrayList;
import java.util.List;

public class PostilForPageRankEntity {
	/* 外链(本批注链接的其他批注) */  
    private List<Integer> outLinks = new ArrayList<Integer>();  
  
    /* 内链(另外批注链接本批注) */  
    private List<Integer> inLinks = new ArrayList<Integer>();
    
    private Integer postilId;
    
    private double pr;
    

	public double getPr() {
		return pr;
	}

	public void setPr(double pr) {
		this.pr = pr;
	}

	public Integer getPostilId() {
		return postilId;
	}

	public void setPostilId(Integer postilId) {
		this.postilId = postilId;
	}

	public List<Integer> getOutLinks() {
		return outLinks;
	}

	public void setOutLinks(List<Integer> outLinks) {
		this.outLinks = outLinks;
	}

	public List<Integer> getInLinks() {
		return inLinks;
	}

	public void setInLinks(List<Integer> inLinks) {
		this.inLinks = inLinks;
	} 
}
