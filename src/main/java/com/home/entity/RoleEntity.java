package com.home.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "Role")
public class RoleEntity extends BaseEntity{	
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	Set<UserEntity> users;
}
