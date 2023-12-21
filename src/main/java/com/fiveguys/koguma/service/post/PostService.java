package com.fiveguys.koguma.service.post;

import com.fiveguys.koguma.data.dto.CategoryDTO;
import com.fiveguys.koguma.data.dto.LocationDTO;
import com.fiveguys.koguma.data.dto.MemberDTO;
import com.fiveguys.koguma.data.dto.PostDTO;
import com.fiveguys.koguma.data.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Page<Post> listPost(PageRequest pageRequest);
    List<PostDTO> listPostByLocation(LocationDTO locationDTO, String keyword, Long categoryId) throws Exception;

    Page<Post> listPostByMember(MemberDTO memberDTO, PageRequest pageRequest);

    void addPost(PostDTO postDTO, MemberDTO memberDTO);

    PostDTO getPost(Long id);

    void updatePost(PostDTO postDTO, MemberDTO memberDTO);

    void deletePost(PostDTO postDTO, MemberDTO memberDTO);

    Page<Post> listPostByViews(Pageable pageRequest);

    Page<Post> listCategoryBySearch (CategoryDTO categoryDTO, PageRequest pageRequest);


    Page<Post> listSearchKeyword(String keyword, PageRequest pageRequest);


    //조회수 증가 메서드
    void increaseViews(Long id);

}
