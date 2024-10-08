package com.iechor.clothesdesigner.chat.api.pojo.response;

import com.iechor.clothesdesigner.chat.api.pojo.request.RecommendedQuestionReq;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecommendQuestionResp {

    private Long modelId;
    private List<RecommendedQuestionReq> recommendedQuestions;
}
