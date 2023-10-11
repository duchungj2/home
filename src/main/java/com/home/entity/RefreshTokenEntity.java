package com.home.entity;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "refreshtoken")
public class RefreshTokenEntity extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String token;

	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	private Timestamp expiryDate;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserEntity user;
}
