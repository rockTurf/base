package com.srj.web.datacenter.stock.model;

import java.io.Serializable;

import com.srj.common.base.BaseEntity;

public class StockPrice extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rise;//涨幅
	private String present_price;//现价
	private String rise_full;//涨跌
	private String buy_price;//买价
	private String sale_price;//卖价
	private String total_hand;//总量
	private String now_hand;//现量
	private String rising_speed;//涨速
	private String turnover;//换手
	private String open;//今开
	private String highest;//最高
	private String lowest;//最低
	private String prev_close;//昨收
	private String prowave;//市盈(动)
	private String total_amount;//总金额
	private String qrr;//量比
	
	private Integer stock_id;//股票id

	public String getRise() {
		return this.getString("rise");
	}

	public void setRise(String rise) {
		this.set("rise",rise);
	}

	public String getPresent_price() {
		return this.getString("presentPrice");
	}

	public void setPresent_price(String present_price) {
		this.set("present_price",present_price);
	}

	public String getRise_full() {
		return this.getString("riseFull");
	}

	public void setRise_full(String rise_full) {
		this.set("rise_full",rise_full);
	}

	public String getBuy_price() {
		return this.getString("buyPrice");
	}

	public void setBuy_price(String buy_price) {
		this.set("buy_price",buy_price);
	}

	public String getSale_price() {
		return this.getString("salePrice");
	}

	public void setSale_price(String sale_price) {
		this.set("sale_price",sale_price);
	}

	public String getTotal_hand() {
		return this.getString("totalHand");
	}

	public void setTotal_hand(String total_hand) {
		this.set("total_hand",total_hand);
	}

	public String getNow_hand() {
		return this.getString("nowHand");
	}

	public void setNow_hand(String now_hand) {
		this.set("now_hand",now_hand);
	}

	public String getRising_speed() {
		return this.getString("risingSpeed");
	}

	public void setRising_speed(String rising_speed) {
		this.set("rising_speed",rising_speed);
	}

	public String getTurnover() {
		return this.getString("turnover");
	}

	public void setTurnover(String turnover) {
		this.set("turnover",turnover);
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

	public String getPrev_close() {
		return this.getString("prevClose");
	}

	public void setPrev_close(String prev_close) {
		this.set("prev_close",prev_close);
	}

	public String getProwave() {
		return this.getString("prowave");
	}

	public void setProwave(String prowave) {
		this.set("prowave",prowave);
	}

	public String getTotal_amount() {
		return this.getString("totalAmount");
	}

	public void setTotal_amount(String total_amount) {
		this.set("total_amount",total_amount);
	}

	public String getQrr() {
		return this.getString("qrr");
	}

	public void setQrr(String qrr) {
		this.set("qrr",qrr);
	}

	public Integer getStock_id() {
		return this.getInteger("stockId");
	}

	public void setStock_id(Integer stock_id) {
		this.set("stock_id",stock_id);
	}
	
	
	
	
}
