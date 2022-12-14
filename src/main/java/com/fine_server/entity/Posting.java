package com.fine_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fine_server.entity.posting.TextDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.06.27
 * LastModifiedPerson : eunhye
 */

@Builder
@Entity
@Getter
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Posting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    @JsonProperty(value = "postingId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    @JsonIgnore
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("false")
    private Boolean closing_check;  // false -> 마감 X , true -> 마감

    @ColumnDefault("false")
    private Boolean group_check;    // false -> 일반 글(General), true -> 단체 글(Group)

    private Integer maxMember;

    @ColumnDefault("0")
    private Integer totalViews; //누적조회수
    
    @ColumnDefault("0")
    private Integer views; //시간조회수

    @Builder.Default
    @OneToMany(mappedBy = "posting")
    private List<Recruiting> recruitingList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "posting")
    private List<Comment> comments = new ArrayList<Comment>();


    // 포스팅 작성 시, member와의 관계 설정
    public void setMember(Member member) {
        this.member = member;
//        member.getPostings().add(this);
    }

    public void setText(TextDto textDto) {
        this.content = textDto.getText();
    }

    public void updateClosingCheck(Boolean check) {
        this.closing_check = check;
    }

    public void updateViews() {
        this.totalViews += 1;
        this.views += 1;
    }

    public void initViews() { this.views = 0; }

}