package com.almundo.callretainer.domain;

/**
 * @author axel.flores
 */
public class Call {
	
	/**
	 * Numero del cual se llama.
	 */
	private String transmitterPhone;
	
	/**
	 * Nombre del emisor de la llamada.
	 */
	private String transmitterName;

	/** */
	public Call() {}
	
	/**
	 * @param transmitterPhone
	 * @param transmitterName
	 */
	public Call(String transmitterPhone, String transmitterName) {
		this.transmitterName = transmitterName;
		this.transmitterPhone = transmitterPhone;
	}
	
	/**
	 * @return the transmitterPhone
	 */
	public String getTransmitterPhone() {
		return transmitterPhone;
	}

	/**
	 * @param transmitterPhone the transmitterPhone to set
	 */
	public void setTransmitterPhone(String transmitterPhone) {
		this.transmitterPhone = transmitterPhone;
	}

	/**
	 * @return the transmitterName
	 */
	public String getTransmitterName() {
		return transmitterName;
	}

	/**
	 * @param transmitterName the transmitterName to set
	 */
	public void setTransmitterName(String transmitterName) {
		this.transmitterName = transmitterName;
	}
}