package cn.wymo.etc.common.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_admins")
public class Administrator extends User {
	public Administrator() {
		super();
	}
}
