
package com.geekcattle.domain.entity.console;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * author
 */
@Entity
@Table(name = "log")
public class Log  implements Serializable {//extends BaseEntity
	/**
	 * 
	 */
	private static final long serialVersionUID = -6029055483747416629L;

	@Id
	@Column(name = "log_id")
	private String logId;

	@Column(name = "log_user")
	private String logUser;
	@Column(name = "log_time")
	private String logTime;
	@Column(name = "log_ip")
	private String logIp;
	@Column(name = "log_action")
	private String logAction;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogUser() {
		return logUser;
	}

	public void setLogUser(String logUser) {
		this.logUser = logUser;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public String getLogAction() {
		return logAction;
	}

	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}

	@Override
	public String toString() {
		return "Log [logId=" + logId + ", logUser=" + logUser + ", logTime=" + logTime + ", logIp=" + logIp
				+ ", logAction=" + logAction + "]";
	}

}
