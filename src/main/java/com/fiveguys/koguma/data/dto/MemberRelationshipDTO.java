package com.fiveguys.koguma.data.dto;

import com.fiveguys.koguma.data.entity.Member;
import com.fiveguys.koguma.data.entity.MemberRelationship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor

public class MemberRelationshipDTO  {

    public Long id;
    public Member sourceMemberId;
    public Member targetMemberId;
    public String content;
    public boolean type;
    public LocalDateTime regDate;

    public MemberRelationshipDTO() {

    }

    public static MemberRelationshipDTO fromEntity(MemberRelationship memberRelationship){
        MemberRelationshipDTO memberRelationshipDTO = new MemberRelationshipDTO();
        memberRelationshipDTO.setId(memberRelationship.getId());
        memberRelationshipDTO.setSourceMemberId(memberRelationship.getSourceMemberId());
        memberRelationshipDTO.setTargetMemberId(memberRelationship.getTargetMemberId());
        memberRelationshipDTO.setContent(memberRelationship.getContent());
        memberRelationshipDTO.setType(memberRelationship.getType());
        return memberRelationshipDTO;

    }

    public MemberRelationship toEntity(){
        MemberRelationship memberRelationship = new MemberRelationship();
        memberRelationship.setId(id);
        memberRelationship.setSourceMemberId(sourceMemberId);
        memberRelationship.setTargetMemberId(targetMemberId);
        memberRelationship.setContent(content);
        memberRelationship.setType(type);
        return memberRelationship;
    }

}
