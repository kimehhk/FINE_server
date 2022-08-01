package com.fine_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

/**
 * written by dahae
 * date: 22.05.27
 *
 * edit by dahae
 * date: 22.07.15
 */


@Entity
@Getter @Setter //이후 리팩토링 예정
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String userID;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    private int userImageNum;

    private String nickname;
    private String email;
    private String intro; //자기소개


    private String userIntroduction;
    private String userUniversity;//대학명
    private String userCollege;//단과대
    private String userPhoneNumber;
    private String userResidence;//거주지

    @Column(nullable = false)
    @ColumnDefault("1")
    private String level;//프로필 레벨
    private Long report; //신고 당한 횟수

    private String keyword1;
    private String keyword2;
    private String keyword3;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<RoomCollection> roomCollectionList = new ArrayList<>();
}
