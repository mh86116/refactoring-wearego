package com.greedy.TravelWithGuid.member.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Getter
@Builder @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER_HISTORY")
public class MemberHistory {
}
