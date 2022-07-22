package com.be1.plant4you.dto.request.board;

import com.be1.plant4you.enumerate.board.PostCat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.be1.plant4you.dto.request.ValidationGroup.*;

@Schema(description = "게시글 등록, 수정 정보 전달 DTO")
@Getter
public class PostRequest {

    @NotNull(groups = {PostUpload.class})
    @Schema(description = "게시글 카테고리", example = "질문|정보|사담", required = true)
    private PostCat cat;

    @NotBlank(groups = {PostUpload.class, PostUpdate.class})
    @Schema(description = "게시글 제목", example = "다육이 키우는 방법", required = true)
    private String title;

    @NotBlank(groups = {PostUpload.class, PostUpdate.class})
    @Schema(description = "게시글 내용", example = "다육이는 ~~~", required = true)
    private String content;
}
