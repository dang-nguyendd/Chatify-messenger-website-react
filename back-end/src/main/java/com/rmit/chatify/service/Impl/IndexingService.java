package com.rmit.chatify.service.Impl;


import lombok.RequiredArgsConstructor;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class IndexingService {

    @Autowired
    private  EntityManager em;

    @Transactional
    public void initiateIndexing() throws InterruptedException {
        //Start indexing
        SearchSession ss = Search.session(em);
        ss.massIndexer().startAndWait();
    }
}