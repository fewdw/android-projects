package com.champlain.trivia.data;

import com.champlain.trivia.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processedFinished(ArrayList<Question> questionArrayList);
}
