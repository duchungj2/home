package com.home.entity;

import java.util.Set;

import com.home.contant.ERole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "Role")
public class RoleEntity extends BaseEntity{
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private ERole name;
	
	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	Set<UserEntity> users;
}
