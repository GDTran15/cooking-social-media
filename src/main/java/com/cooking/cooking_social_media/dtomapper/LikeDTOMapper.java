package com.cooking.cooking_social_media.dtomapper;

import com.cooking.cooking_social_media.dto.LikeDTOResponse;
import com.cooking.cooking_social_media.model.Likes;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LikeDTOMapper implements Function<Likes, LikeDTOResponse> {

    @Override
    public LikeDTOResponse apply(Likes likes) {
        return new LikeDTOResponse(
                likes.getUser()
                        .getUserName()
        );
    }
}
