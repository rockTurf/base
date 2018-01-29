package com.srj.web.datacenter.stock.model;

import java.io.Serializable;

import javax.persistence.Transient;

import com.srj.common.base.BaseEntity;

public class StockDetail extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String detail_date;
	private String open;//开盘
	private String highest;//最高
	private String lowest;//最低
	private String close;//收盘
	private String rise;//涨幅
	private String swing;//振幅
	private String total_hand;//总手
	private String amount;//金额
	private String change_hand;//换手
	private String deal;//成交次数
	
	private String stock_id;//股票id
	
	@Transient
	private String stock_name;//股票名
	@Transient
	private String stock_code;//股票代码
	
	public String getStock_name() {
		return this.getString("stockName");
	}
	public String getStock_code() {
		return this.getString("StockCode");
	}

	public Long getStock_id() {
		return this.getLong("stockId");
	}

	public void setStock_id(Long stock_id) {
		this.set("stock_id",stock_id);
	}
	public String getDetail_date() {
		return this.getString("detailDate");
	}
	public void setDetail_date(String detail_date) {
		this.set("detail_date",detail_date);
	}
	public String getOpen() {
		return this.getString("open");
	}
	public void setOpen(String open) {
		this.set("open",open);
	}
	public String getHighest() {
		return this.getString("highest");
	}
	public void setHighest(String highest) {
		this.set("highest",highest);
	}
	public String getLowest() {
		return this.getString("lowest");
	}
	public void setLowest(String lowest) {
		this.set("lowest",lowest);
	}
	public String getClose() {
		return this.getString("close");
	}
	public void setClose(String close) {
		this.set("close",close);
	}
	public String getRise() {
		return this.getString("rise");
	}
	public void setRise(String rise) {
		this.set("rise",rise);
	}
	public String getSwing() {
		return this.getString("swing");
	}
	public void setSwing(String swing) {
		this.set("swing",swing);
	}
	public String getTotal_hand() {
		return this.getString("totalHand");
	}
	public void setTotal_hands(String total_hand) {
		this.set("total_hand",total_hand);
	}
	public String getAmount() {
		return this.getString("amount");
	}
	public void setAmount(String amount) {
		this.set("amount",amount);
	}
	public String getChange_hands() {
		return this.getString("changeHand");
	}
	public void setChange_hand(String change_hand) {
		this.set("change_hand",change_hand);
	}
	public String getDeal() {
		return this.getString("deal");
	}
	public void setDeal(String deal) {
		this.set("deal",deal);
	}

	
}
