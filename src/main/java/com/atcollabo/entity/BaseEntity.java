package com.atcollabo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * MappedSuperClass : Used to eliminate duplicated code when there are common fields per entity, but exclude PK of each entity
 * 
 * @author coedplay
 *
 */
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
abstract class BaseEntity {

	@CreatedDate
	@Column(updatable = false)
	protected LocalDateTime dateCreate;

	@LastModifiedDate
	@Column
	protected LocalDateTime dateUpdate;

	@Column(columnDefinition = "bit default 1")
	protected Boolean active;	// if null, retreive all
	
	public void onActive() {
		active = true;
	}
	
	public void deActive() {
		active = false;
	}
			
}
