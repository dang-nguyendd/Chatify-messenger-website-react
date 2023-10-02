package com.rmit.chatify.service.Impl;

import com.rmit.chatify.dtos.ConversationDTO;
import com.rmit.chatify.dtos.MessageDTO;
import com.rmit.chatify.dtos.UserDTO;
import com.rmit.chatify.service.serviceInterface.SearchService;
import org.hibernate.search.engine.search.query.SearchQuery;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class SearchServiceImpl implements SearchService {

    @Autowired
    private EntityManager entityManager;

    //Search message by conversation id
    @Override
    @Cacheable("message")
    public List<MessageDTO> getMessageInConversation(Long conversation_id, String word, int page, int size) {
        SearchSession searchSession = Search.session(entityManager);
        System.out.printf("ConversationID" + conversation_id);

        //Search message by conversation id and keywords
        SearchQuery<MessageDTO> searchQuery = searchSession.search(MessageDTO.class)
                .where(f -> f.bool()
                        .must(f.match().field("content").matching(word).fuzzy(2,4))
                        .must(f.match().field("conversationDTO.id").matching(conversation_id))
                )
                .toQuery();
        System.out.println("Hits count");
        System.out.println(searchQuery.fetchTotalHitCount());
        if (searchQuery.fetchTotalHitCount() == 0) {
            return null;
        }
        return searchQuery.fetchHits(page * size, size);
    }


    //Query for conversation name
    @Override
    public List<ConversationDTO> getConversationByName(String word, int page, int size) {
        return (List<ConversationDTO>) searchEntity(word,"conversationName", page, size, ConversationDTO.class);
    }

    private List<?> searchEntity(String word, String field, int page, int size, Class<?> entityClass) {
        SearchSession searchSession = Search.session(entityManager);
        SearchQuery<?> searchQuery = searchSession.search(entityClass)
                .where(f -> f.match().field(field).matching(word).fuzzy(2))
                .toQuery();
        if (searchQuery.fetchTotalHitCount() == 0) {
            return null;
        }
        //Print out search result
        System.out.println();
        return searchQuery.fetchHits(page, size);
    }

    //Search for user email
    @Override
    public List<UserDTO> getUserBasedOnEmail(String email, int page, int size) {
        return (List<UserDTO>) searchEntity(email,"email" ,page, size, UserDTO.class);
    }

    @Override
    public List<UserDTO> getUserBasedOnName(String name, int page, int size) {
        return (List<UserDTO>) searchEntity(name,"username" ,page, size, UserDTO.class);
    }
}